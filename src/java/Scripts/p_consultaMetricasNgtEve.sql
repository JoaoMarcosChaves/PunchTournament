USE [PUNCH]
GO
/****** Object:  StoredProcedure [dbo].[p_consultaMetricasNgtEve]    Script Date: 21/09/2016 22:03:58 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER proc [dbo].[p_consultaMetricasNgtEve](

@codAtleta int
,@codEvento int

)

AS

SET NOCOUNT ON -- Este comando serve para que no momento que o jdbc for popular o result set da consulta, ele não se confundir por causa do numero de consultas e de linhas que a proc gerou

create table #ptNgt (parteNGT int,parteDoCorpoNGT varchar(20),codInscricao int)
create table #totCbts (qtdCbtGeral int,codInscricao int)
create table #qtdDerrotas(qtdDerr int,codInscricao int)
create table #aux (qtdDerr int,codInscricao int)

declare @codInscricao int

BEGIN

declare qtds_cursor CURSOR FOR

select codInscricao from TB_inscricaoAtleta where codAtleta = @codAtleta;

open qtds_cursor

fetch next from qtds_cursor
into @codInscricao

-- Enquanto todas as inscrições não passarem pelos seguintes procedimentos, o processo não continua

while @@FETCH_STATUS = 0
begin 

-- Select que pega qual parte do corpo foi mais impactada

insert into #ptNgt(parteNGT,parteDoCorpoNGT,codInscricao)
(
select top 1 count(*) parteNGT,parteDoCorpoNGT,codInscricao  from TB_metricasCBT where codInscricao = @codInscricao
group by parteDoCorpoNGT,codInscricao 
)

---- Select que pega o total de combates em geral

insert into #totCbts(qtdCbtGeral,codInscricao)
(
select COUNT(codInscricao) qtdCbtGeral,codInscricao from TB_metricasCBT where codInscricao = @codInscricao
group by codInscricao
)

-- Select que pega o total de combates perdidos

insert into #aux (qtdDerr,codInscricao)
(
select count(statusCBT) qtdDerr,codInscricao from ordemChave1 where codInscricao = @codInscricao and statusCBT = 'Perdedor'
group by codInscricao
)

insert into #aux (qtdDerr,codInscricao)
(
select count(statusCBT) qtdDerr,codInscricao from ordemChave2 where codInscricao = @codInscricao and statusCBT = 'Perdedor'
group by codInscricao
)

if (select COUNT(codInscricao) from #aux where codInscricao = @codInscricao) > 0 -- caso a inscrição atual do loop esteja em auxiliar, a inserção é realizada na tb de derrotas. Caso contrário, não.
begin


  if (select count(codInscricao) from #aux where codInscricao = @codInscricao) <> 0  -- Caso não aja derrotas, deve-se ser inserido o valor 0 na tb aux para que o select final não de erro
  begin 

  
  insert into #qtdDerrotas (qtdDerr,codInscricao)
  (
  select sum(qtdDerr) qtdDerr,codInscricao from #aux where codInscricao = @codInscricao group by codInscricao -- Realiza a soma de todas as derrotas nas duas tabelas e joga na tb #qtdDerrotas
  )
  end
  else
   begin
   
   insert into #qtdDerrotas (qtdDerr,codInscricao)
   values (0,@codInscricao)

   end


end
else
   begin
  
   insert into #qtdDerrotas (qtdDerr,codInscricao)
   values (0,@codInscricao)

   end
fetch next from qtds_cursor
into @codInscricao
end
close qtds_cursor
DEALLOCATE qtds_cursor

-- Select Final

select ev.nomeEvento, seg.nomeSegmento, c.nomeChvConf, ma.codInscricao, a.codAtleta, ma.totPontosNegativos,
ptng.parteDoCorpoNGT,cbts.qtdCbtGeral, derr.qtdDerr
from TB_metricasAT ma inner join TB_confrontos c
on ma.codChvConf = c.codChvConf
inner join TB_inscricaoAtleta a
on a.codInscricao = ma.codInscricao
inner join TB_segmentos seg
on seg.codSegmento = c.codSegmento
inner join TB_evento ev
on ev.codEvento = seg.codEvento
inner join #ptNgt ptng
on ptng.codInscricao = a.codInscricao
inner join #totCbts cbts
on cbts.codInscricao = a.codInscricao
inner join #qtdDerrotas derr
on derr.codInscricao = a.codInscricao

where a.codAtleta = @codAtleta
and ev.codEvento = @codEvento

END