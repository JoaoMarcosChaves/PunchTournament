
create proc [dbo].[p_consultaComentsArbNgt](

@codAtleta int
,@codEvento int

)

AS 
SET NOCOUNT ON
-- Criando a tabela que coleciona todos os perdedores das duas tabelas de confronto.
create table #vencedores(codChvConf int , codInscricao int ,numRodada int,statusCBT varchar(8))

BEGIN

-- Realizando o insert dos perdedores da chave 1

insert into #vencedores (codChvConf, codInscricao ,numRodada,statusCBT)
(
select o.codChvConf,o.codInscricao,o.numRodada,o.statusCBT from TB_confrontos c inner join  ordemChave1 o
on o.codChvConf = c.codChvConf
inner  join TB_segmentos s
on s.codSegmento = c.codSegmento
and s.codEvento = @codEvento
and o.statusCBT = 'Perdedor'

)

-- Realizando o insert dos perdedores da chave 2
insert into #vencedores (codChvConf, codInscricao ,numRodada,statusCBT)
(
select o.codChvConf,o.codInscricao,o.numRodada,o.statusCBT from TB_confrontos c inner join  ordemChave2 o
on o.codChvConf = c.codChvConf
inner  join TB_segmentos s
on s.codSegmento = c.codSegmento
and s.codEvento = @codEvento
and o.statusCBT = 'Perdedor'

)

-- Estruturando a consulta a consulta

select i.codAtleta,s.nomeSegmento, c.nomeChvConf,mc.numRodada,mc.descricaoCBT,v.statusCBT from TB_segmentos s inner join TB_confrontos c
on s.codSegmento = c.codSegmento
inner join TB_metricasCBT mc
on mc.codChvConf = c.codChvConf
inner join TB_inscricaoAtleta i
on i.codInscricao = mc.codInscricao
inner join #vencedores v
on v.codInscricao = mc.codInscricao
and i.codAtleta = @codAtleta
and v.statusCBT = 'Perdedor' 
group by i.codAtleta,s.nomeSegmento, c.nomeChvConf,mc.numRodada,mc.descricaoCBT,v.statusCBT

END