USE [PUNCH]
GO
/****** Object:  StoredProcedure [dbo].[p_consultaResultadosConfNgt]    Script Date: 04/10/2016 02:29:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER proc [dbo].[p_consultaResultadosConfNgt](

@codAtleta int
,@codEvento int

)

AS 
SET NOCOUNT ON
-- Criando a tabela que coleciona todos os perdedores das duas tabelas de confronto.
create table #perdedores(codChvConf int , codInscricao int ,numRodada int,statusCBT varchar(8))

BEGIN 

-- Realizando o insert dos perdedores da chave 1

insert into #perdedores (codChvConf, codInscricao ,numRodada,statusCBT)
(
select o.codChvConf,o.codInscricao,o.numRodada,o.statusCBT from TB_confrontos c inner join  ordemChave1 o
on o.codChvConf = c.codChvConf
inner  join TB_segmentos s
on s.codSegmento = c.codSegmento
and s.codEvento = @codEvento
and o.statusCBT = 'Perdedor'

)

-- Realizando o insert dos perdedores da chave 2
insert into #perdedores (codChvConf, codInscricao ,numRodada,statusCBT)
(
select o.codChvConf,o.codInscricao,o.numRodada,o.statusCBT from TB_confrontos c inner join  ordemChave2 o
on o.codChvConf = c.codChvConf
inner  join TB_segmentos s
on s.codSegmento = c.codSegmento
and s.codEvento = @codEvento
and o.statusCBT = 'Perdedor'

)

-- Estruturando a consulta a consulta

select mc.codChvConf, p.numRodada, mc.finalCBT from TB_metricasCBT mc inner join #perdedores p
on mc.codChvConf = p.codChvConf
inner join TB_confrontos c
on mc.codChvConf = c.codChvConf
inner join TB_segmentos s
on s.codSegmento = c.codSegmento
inner join TB_inscricaoAtleta i
on i.codInscricao = p.codInscricao
and s.codEvento = @codEvento
and i.codAtleta = @codAtleta
group by mc.codChvConf, p.numRodada, mc.finalCBT
END
