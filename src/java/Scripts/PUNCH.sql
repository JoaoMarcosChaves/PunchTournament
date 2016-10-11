--CREATE DATABASE PUNCH

--USE PUNCH

create table TB_modalidades(

codModali bigint identity constraint codModaliPK primary key (codModali),
domeModali varchar (max),
descricao varchar(max)

)

create table TB_graduaModali(

codGraduMod bigint identity constraint codGraduModPK primary key (codGraduMod),
codModali bigint constraint codModali_Gradu_FK foreign key (codModali) references TB_modalidades (codModali),
identificacaoGradu varchar (100),
tipoGradu varchar (8)

)

create table TB_evento(

codEvento bigint identity constraint codEventoPK primary key (codEvento),
codModali bigint constraint codModaliFK foreign key (codModali) references TB_modalidades (codModali),
dataEvento date,
dataFinal date, -- adicionado
statusEvento varchar (10),
nomeEvento varchar (500),

)

create table TB_segmentos(
codSegmento bigint identity constraint codSegmentoPK primary key (codSegmento),
codEvento bigint constraint codEvento_Seg_FK foreign key (codEvento) references TB_evento(codEvento),
nomeSegmento varchar (500),
descricaoSegmento varchar(max),

)

create table TB_categoria(

codCategoria bigint identity constraint codCategoriaPK primary key (codCategoria),
codSegmento bigint constraint codSegmento_Cat_FK foreign key (codSegmento) references TB_segmentos(codSegmento),
pesoMinCategoria float(4),
pesoMaxCategoria float(4),
graduacaoMinCategoria int,
graduacaoMaxCategoria int,
idadeMinCategoria int,
idadeMaxCategoria int,
nomeCategoria varchar(max),
sexo varchar (10)
)

create table TB_metodoPontua(

codPontua bigint identity constraint codPontuaPK primary key (codPontua),
codSegmento bigint constraint codSegmento_Pont_FK foreign key (codSegmento) references TB_segmentos(codSegmento),
nomePontua varchar(100),
valorPontua int,
tipoPontua varchar (8),
descricaoPontua varchar(max),
parteDoCorpo varchar(20),

)

create table TB_arbitro(

codArbitro bigint identity constraint codArbitroPK primary key (codArbitro),
codSegmento bigint constraint codSegmento_Arbit_FK foreign key (codSegmento) references TB_segmentos(codSegmento),
nomeArbitro varchar (200),
graduacaoArbitro varchar(100),

)

create table TB_pontua(

codArbitro bigint constraint codArbitro_PontPorArbit_FK foreign key (codArbitro) references TB_arbitro(codArbitro),
codPontua bigint constraint codPontua_PontPorArbit_FK foreign key (codPontua) references TB_metodoPontua(codPontua),

)

create table TB_atleta(
codAtleta bigint identity constraint codAtletaPK primary key (codAtleta),
nomeAtleta varchar (200),
idadeAtleta int not null,
sexo varchar (8) not null,
pesoAtleta float (4) not null,
graduacaoAtleta varchar(100) not null,
cpfAtleta varchar(11),
emailAtleta varchar(200),

)

create table TB_inscricaoAtleta(

codSegmento bigint constraint codSegmento_Insc_FK foreign key (codSegmento) references TB_segmentos(codSegmento),
codAtleta bigint constraint codAtleta_Insc_FK foreign key (codAtleta) references TB_atleta(codAtleta),
codCategoria bigint constraint codCategoria_Insc_FK foreign key (codCategoria) references TB_categoria(codCategoria),
codInscricao bigint identity constraint codInscricaoPK primary key (codInscricao),
statusInscricao varchar(10),

)

create table TB_confrontos(

codSegmento bigint constraint codSegmento_Conf_FK foreign key (codSegmento) references TB_segmentos(codSegmento),
codCategoria bigint constraint codCategoria_Conf_FK foreign key (codCategoria) references TB_categoria(codCategoria),
codChvConf bigint identity constraint codChvConfPK primary key (codChvConf),
nomeChvConf varchar (200),
statusChvConf varchar (10),
qtdRodadasChvConf int

)

create table rodadasConfrontos(

codChvConf bigint constraint codChvConf_Rodadas_FK foreign key (codChvConf) references TB_confrontos(codChvConf),
numRodada int,
statusRodada varchar (10)

)

create table TB_metricasAT(

codChvConf bigint constraint codChvConf_MetrAT_FK foreign key (codChvConf) references TB_confrontos(codChvConf),
codInscricao bigint constraint codInscricao_MetrAT_FK foreign key (codInscricao) references TB_inscricaoAtleta(codInscricao),
totPontosPositivos int,
totPontosNegativos int,


)

create table TB_metricasCBT(

codChvConf bigint constraint codChvConf_MetrCBT_FK foreign key (codChvConf) references TB_confrontos(codChvConf),
codInscricao bigint constraint codInscricao_MetrCBT_FK foreign key (codInscricao) references TB_inscricaoAtleta(codInscricao),
numRodada int,
parteDoCorpoNGT varchar (20),
parteDoCorpoPST varchar (20),
descricaoCBT varchar(max),
finalCBT varchar(15) -- adicionado
totPontosPositivos int, -- adicionado
totPontosNegativos int, -- adicionado
--statusCBT varchar(10)
)

create table ordemChave1( -- adicionado

codChvConf int,
numRodada int,
,codInscricao bigint
,statusCBT varchar(10) -- adicionado
)

create table ordemChave2(  --adicionado

codChvConf int,
numRodada int,
,codInscricao bigint
,statusCBT varchar(10) -- adicionado
)

create table TB_PerfisUsuarios( -- adicionado
codigoPRF  bigint identity constraint codigoPerfilPK  primary key(codigoPRF),
tipoPRF varchar (20) not null,
loginPRF varchar (max) not null,
senhaPRF varchar (max) not null,
emailPRF varchar (max) not null
)

create table TB_usersCNX( -- adicionado

codCNX bigint identity constraint codCNX_PK primary key (codCNX),
codUserCNX bigint,
nomeUserCNX varchar(max),
dtLoginCNX  datetime

)

/* as tabelas abaixo servem para montar uma estrutura solida para que a parte do árbitro e a disponibilização de informações de combates a serem julgados
 por eles, não sejam deficientes. Elas montam a estrutura do combate, associam o árbitro a estrutura, e associam as pontuações aos arbitros designados. Isso respectivamente na ordem que foram apresentadas abaixo. */

create table TB_estruCBT( -- adicionado

codConf	bigint identity constraint codConfPK primary key (codConf)
,codChvConf bigint constraint codChvConf_estruCBT_FK foreign key (codChvConf) references TB_confrontos(codChvConf)--> TB_confrontos
,numRodada int--> ordemChave ou rodadasConfrontos
,codInsc1 bigint  --> inscrição do atleta da chave 1
,codInsc2 bigint  --> inscrição do atleta da chave 2
,statusConf varchar(10)--> aberto ou concluído
,confrmsFnlz int --> arbitros que devem finalizar seus confrontos
)


create  table TB_arbCBT( -- adicionado
codConfArb bigint identity constraint codConfArbPK primary key (codConfArb)
,codConf bigint constraint codConf_arbCBT_FK foreign key (codConf) references TB_estruCBT(codConf) --> chave estrangeira da tabela de confronto moldado
,codArbitro bigint constraint codArbitro_arbCBT_FK foreign key (codArbitro) references TB_arbitro(codArbitro)--> chave estrangeira da tabela TB_arbitro
)

create  table TB_pontArbCBT( -- adicionado

codConfArb bigint constraint codConfArb_pontArb_FK foreign key (codConfArb) references TB_arbCBT(codConfArb)--> chave estrangeira para saber quais pontos o árbitro vai juldar em cada combate.
,codPontua bigint constraint codPontua__pontArb_FK foreign key (codPontua) references TB_metodoPontua(codPontua)--> chave estrangeira do codigo da pontuação que o árbitro vai julgar naquele combate.

)

create table TB_iniciarCBT( -- Adicionado

codConf bigint constraint codConf_inicioCBT_FK foreign key (codConf) references TB_estruCBT(codConf)
,codInsc1 bigint --> inscrição do atleta da estrutura 1
,statusInicio1 varchar(8) --> confirmação que pelo menos um arbitro vai julgar o atleta da estrutura 1
,codInsc2 bigint --> inscrição do atleta da estrutura 2
,statusInicio2 varchar(8) --> confirmação que pelo menos um arbitro vai julgar o atleta da estrutura 2
,confrmsInicio int --> confirmação de todos os arbitros designados ao confronto.
)


create table TB_acertosCBT( -- adicionado

codConf bigint constraint codConf_acertosCBT_FK foreign key (codConf) references TB_estruCBT(codConf)
,codInsc int
,acertosCabecaPst int
,acertosBracosPst int
,acertosTroncoPst int
,acertosPernasPst int
,acertosQuedagemPst int
,acertosCabecaNgt int
,acertosBracosNgt int
,acertosTroncoNgt int
,acertosPernasNgt int
,acertosQuedagemNgt int
)

/**

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


-- Na estrutura da consulta


select s.nomeSegmento,x.nomeCategoria,c.nomeChvConf,i.codInscricao ,a.codAtleta,a.nomeAtleta from TB_confrontos c inner join ordemChave2 o
on c.codChvConf = o.codChvConf
and c.qtdRodadasChvConf = o.numRodada
and o.statusCBT = 'Vencedor'
inner join TB_categoria x
on c.codCategoria = x.codCategoria
inner join TB_segmentos s
on c.codSegmento = s.codSegmento
inner join TB_inscricaoAtleta i
on o.codInscricao = i.codInscricao
inner join TB_atleta a
on i.codAtleta = a.codAtleta
inner join TB_evento e
on e.codEvento = s.codEvento
and s.codEvento = 0
and s.codSegmento = ?
and x.codCategoria = ?

select s.nomeSegmento,x.nomeCategoria,c.nomeChvConf,i.codInscricao ,a.codAtleta,a.nomeAtleta from TB_confrontos c inner join ordemChave1 o
on c.codChvConf = o.codChvConf
and c.qtdRodadasChvConf = o.numRodada
and o.statusCBT = 'Vencedor'
inner join TB_categoria x
on c.codCategoria = x.codCategoria
inner join TB_segmentos s
on c.codSegmento = s.codSegmento
inner join TB_inscricaoAtleta i
on o.codInscricao = i.codInscricao
inner join TB_atleta a
on i.codAtleta = a.codAtleta
inner join TB_evento e
on e.codEvento = s.codEvento
and s.codEvento = ?
and s.codSegmento = ?
and x.codCategoria = ?


-- de maneira completa para a tabela 1

select * from TB_confrontos c inner join ordemChave1 o
on c.codChvConf = o.codChvConf
and c.qtdRodadasChvConf = o.numRodada
and o.statusCBT = 'Vencedor'
inner join TB_categoria x
on c.codCategoria = x.codCategoria
inner join TB_segmentos s
on c.codSegmento = s.codSegmento
inner join TB_inscricaoAtleta i
on o.codInscricao = i.codInscricao
inner join TB_atleta a
on i.codAtleta = a.codAtleta
inner join TB_evento e
on e.codEvento = s.codEvento
and s.codEvento = ?
and s.codSegmento = ?
and x.codCategoria = ?

-- de maneira completa para a tabela 2


select * from TB_confrontos c inner join ordemChave2 o
on c.codChvConf = o.codChvConf
and c.qtdRodadasChvConf = o.numRodada
and o.statusCBT = 'Vencedor'
inner join TB_categoria x
on c.codCategoria = x.codCategoria
inner join TB_segmentos s
on c.codSegmento = s.codSegmento
inner join TB_inscricaoAtleta i
on o.codInscricao = i.codInscricao
inner join TB_atleta a
on i.codAtleta = a.codAtleta
inner join TB_evento e
on e.codEvento = s.codEvento
and s.codEvento = ?
and s.codSegmento = ?
and x.codCategoria = ?


-- query que traz todos os tops do evento selecionado. Estão em ordem de quem mais pontuou, até o que menos pontuou.

select e.codEvento, e.nomeEvento, a.nomeAtleta, i.codInscricao, i.codAtleta, m.totPontosPositivos, m.totPontosNegativos,c.finalCBT, total = count(c.finalCBT)
from TB_metricasAT m inner join TB_inscricaoAtleta i
on i.codInscricao = m.codInscricao
inner join TB_atleta a
on a.codAtleta = i.codAtleta
inner join TB_segmentos s
on s.codSegmento = i.codSegmento
inner join TB_evento e
on e.codEvento = s.codEvento
inner join TB_metricasCBT c
on i.codInscricao = c.codInscricao
and e.codEvento = 7 -- Isolamos o código do evento para que não aja confusão nas modalidades
-- aqui pode ser adicionado qualquer condição que isole a consulta
group by e.codEvento, e.nomeEvento, a.nomeAtleta, i.codInscricao, i.codAtleta, m.totPontosPositivos, m.totPontosNegativos, c.finalCBT
order by finalCBT,totPontosPositivos desc


-- Métricas atleta 

exec p_consultaMetricasNgtEve 'codigo atleta','código evento' -- Metricas negativas
exec p_consultaMetricasPstEve 'codigo atleta','código evento' -- Metricas positivas


-- Reseta a estrutura inteira de combate para teste com arbitros

select * from TB_estruCBT
select * from TB_iniciarCBT

select * from TB_metricasCBT where codChvConf = 24
select * from TB_metricasAt where codChvConf = 24 
select * from ordemChave1 where codChvConf = 24
select * from ordemChave2 where codChvConf = 24


update TB_iniciarCBT set statusInicio1 = null,statusInicio2= null , confrmsInicio = 0
update TB_estruCBT set statusConf = 'Iniciado', confrmsFnlz = 0

update TB_metricasAT set totPontosPositivos = 0,totPontosNegativos = 0 where codChvConf = 24
delete from TB_metricasCBT where codChvConf = 24
update ordemChave1 set statusCBT = 'Competindo' where codChvConf = 24
update ordemChave2 set statusCBT = 'Competindo' where codChvConf = 24

 **/







