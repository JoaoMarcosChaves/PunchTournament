USE [PUNCH]
GO
/****** Object:  StoredProcedure [dbo].[p_consultaMetricasPstEve]    Script Date: 21/09/2016 21:55:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER proc [dbo].[p_consultaMetricasPstEve](

@codAtleta int
,@codEvento int

)

AS
SET NOCOUNT ON
-- Tabelas auxiliares que vamos necesistar para agrupar os valores de várias inscrições 
create table #ptPst(partePST int ,parteDoCorpoPST varchar(20) ,codInscricao int)
create table #totCbts(qtdCbtGeral int,codInscricao int)
create table #qtdVit (vits int,codInscricao int)
create table #aux (qtdVit int,codInscricao int)

declare @codInscricao int	


BEGIN
-- abro o cursor e coloco todas as incrições abertas do atleta lá
declare qtds_cursor CURSOR FOR

select codInscricao from TB_inscricaoAtleta where codAtleta = @codAtleta;

open qtds_cursor

fetch next from qtds_cursor
into @codInscricao

-- Enquanto todas as inscrições não passarem pelos seguintes procedimentos, o processo não continua

while @@FETCH_STATUS = 0
begin 

-- Faço a inserção de todos os pontos positivos para a inscrição referente no loop na tabela #ptPst

insert into #ptPst (partePST,parteDoCorpoPST,codInscricao)
(select top 1 count(*) partePST,parteDoCorpoPST,codInscricao  from TB_metricasCBT where codInscricao = @codInscricao
group by parteDoCorpoPST,codInscricao 
)

---- Faço a inserção do total de combates para a inscrição referente no loop na tabela #totCbts

insert into #totCbts(qtdCbtGeral,codInscricao)
(
select COUNT(codInscricao) qtdCbtGeral,codInscricao from TB_metricasCBT where codInscricao = @codInscricao
group by codInscricao
)

-- Quantidade de vitórias

insert into #aux (qtdVit,codInscricao)
(
select count(statusCBT) qtdDerr,codInscricao from ordemChave1 where codInscricao = @codInscricao and statusCBT = 'Vencedor'
group by codInscricao
)

insert into #aux (qtdVit,codInscricao)
(
select count(statusCBT) qtdDerr,codInscricao from ordemChave2 where codInscricao = @codInscricao and statusCBT = 'Vencedor'
group by codInscricao
)



if (select COUNT(codInscricao) from #aux where codInscricao = @codInscricao) > 0 -- caso a inscrição atual do loop esteja em auxiliar, a inserção é realizada na tb de vitórias. Caso contrário, não.
begin


  if (select count(codInscricao) from #aux) <> 0  -- Caso não aja vitorias, deve-se ser inserido o valor 0 na tb aux para que o select final não de erro
  begin 

   insert into #qtdVit (vits,codInscricao)
   (
   select sum(qtdVit) vits,codInscricao from #aux where codInscricao = @codInscricao group by codInscricao -- Realiza a soma de todas as vitorias nas duas tabelas e joga na tb #qtdVit
   )

   end
   else
   begin

   insert into #qtdVit (vits,codInscricao)
   values (0,@codInscricao)

   end

end
else
   begin

   insert into #qtdVit (vits,codInscricao)
   values (0,@codInscricao)

   end

fetch next from qtds_cursor
into @codInscricao
end
close qtds_cursor
DEALLOCATE qtds_cursor

-- Select Final

select  ev.nomeEvento, seg.nomeSegmento, c.nomeChvConf, ma.codInscricao, a.codAtleta, ma.totPontosPositivos
,ptps.parteDoCorpoPST,cbts.qtdCbtGeral, vit.vits
from TB_metricasAT ma inner join TB_confrontos c
on ma.codChvConf = c.codChvConf
inner join TB_inscricaoAtleta a
on a.codInscricao = ma.codInscricao
inner join TB_segmentos seg
on seg.codSegmento = c.codSegmento
inner join TB_evento ev
on ev.codEvento = seg.codEvento
inner join #ptPst ptps
on ptps.codInscricao = a.codInscricao
inner join #totCbts cbts
on cbts.codInscricao = a.codInscricao
inner join #qtdVit vit
on vit.codInscricao = a.codInscricao

where a.codAtleta = @codAtleta
and ev.codEvento = @codEvento

END