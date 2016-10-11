--select * from TB_segmentos
--select * from TB_evento
--select * from TB_atleta
--select * from TB_inscricaoAtleta
--select * from TB_confrontos
--select * from TB_metricasAT
--select * from TB_metricasCBT
--select * from ordemChave1
--select * from ordemChave2
--select * from rodadasConfrontos

create proc p_consultaMetricasNgtEve(

@codAtleta int
,@codEvento int

)

AS

BEGIN


-- Select que pega qual parte do corpo foi mais impactada
select top 1 count(*) parteNGT,parteDoCorpoNGT,codInscricao into #ptNgt from TB_metricasCBT where codInscricao = 18
group by parteDoCorpoNGT,codInscricao order by parteNGT desc

-- Select que pega qual parte do corpo mais atingiu
select top 1 count(*) partePST,parteDoCorpoPST,codInscricao into #ptPst from TB_metricasCBT where codInscricao = 18
group by parteDoCorpoPST,codInscricao order by partePST desc

-- Select que pega o total de combates em geral
select COUNT(codInscricao) qtdCbtGeral,codInscricao into #totCbts from TB_metricasCBT where codInscricao = 18
group by codInscricao
-- Select que pega o total de combates perdidos

select count(statusCBT) qtdDerr,codInscricao into #qtdDerrotas from ordemChave1 where codInscricao = 20 and statusCBT = 'Perdedor'
group by codInscricao

insert into #qtdDerrotas (qtdDerr,codInscricao)
(
select count(statusCBT) qtdDerr,codInscricao from ordemChave2 where codInscricao = 20 and statusCBT = 'Perdedor'
group by codInscricao
)

-- Select Final

select ev.codEvento, ev.nomeEvento, ma.codInscricao, a.codAtleta, ma.totPontosNegativos, ma.totPontosPositivos,
ptng.parteDoCorpoNGT,ptps.parteDoCorpoPST,cbts.qtdCbtGeral, derr.qtdDerr
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
inner join #ptPst ptps
on ptps.codInscricao = a.codInscricao
inner join #totCbts cbts
on cbts.codInscricao = a.codInscricao
inner join #qtdDerrotas derr
on derr.codInscricao = a.codInscricao

where a.codAtleta = 44
and ev.codEvento = 7


--select * from TB_inscricaoAtleta where codAtleta = 44
END