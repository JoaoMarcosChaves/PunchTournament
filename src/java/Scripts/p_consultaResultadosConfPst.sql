USE [PUNCH]
GO
/****** Object:  StoredProcedure [dbo].[p_consultaResultadosConf]    Script Date: 04/10/2016 01:35:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER proc [dbo].[p_consultaResultadosConfPst](

@codAtleta int
,@codEvento int

)

AS 
SET NOCOUNT ON
-- Criando a tabela que coleciona todos os vencedores das duas tabelas de confronto.
create table #vencedores(codChvConf int , codInscricao int ,numRodada int,statusCBT varchar(8))

BEGIN

-- Realizando o insert dos vencedores da chave 1

insert into #vencedores (codChvConf, codInscricao ,numRodada,statusCBT)
(
select o.codChvConf,o.codInscricao,o.numRodada,o.statusCBT from TB_confrontos c inner join  ordemChave1 o
on o.codChvConf = c.codChvConf
inner  join TB_segmentos s
on s.codSegmento = c.codSegmento
and s.codEvento = @codEvento
and o.statusCBT = 'Vencedor'

)

-- Realizando o insert dos vencedores da chave 2
insert into #vencedores (codChvConf, codInscricao ,numRodada,statusCBT)
(
select o.codChvConf,o.codInscricao,o.numRodada,o.statusCBT from TB_confrontos c inner join  ordemChave2 o
on o.codChvConf = c.codChvConf
inner  join TB_segmentos s
on s.codSegmento = c.codSegmento
and s.codEvento = @codEvento
and o.statusCBT = 'Vencedor'

)

-- Estruturando a consulta a consulta

select mc.codChvConf, p.numRodada, mc.finalCBT from TB_metricasCBT mc inner join #vencedores p
on mc.codChvConf = p.codChvConf
inner join TB_confrontos c
on mc.codChvConf = c.codChvConf
inner join TB_segmentos s
on s.codSegmento = c.codSegmento
inner join TB_inscricaoAtleta i
on i.codInscricao = p.codInscricao
and p.numRodada = mc.numRodada
and s.codEvento = @codEvento
and i.codAtleta = @codAtleta
group by mc.codChvConf, p.numRodada, mc.finalCBT
END
