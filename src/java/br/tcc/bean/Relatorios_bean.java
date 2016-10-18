/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tcc.bean;

/**
 *
 * @author joãomarcos
 */
public class Relatorios_bean {

// quase todos tem
private String sexo; 
private int codSegmento;   

 // Atletas

private int codAtleta;
private String nomeAtleta;
private int idadeAtleta;
private float pesoAtleta;
private String graduacaoAtleta;
private String cpfAtleta;
private String emailAtleta;

// Categorias
private int codCategoria;
private float pesoMinCategoria;
private float pesoMaxCategoria;
private int graduacaoMinCategoria;
private int graduacaoMaxCategoria;
private int idadeMinCategoria;
private int idadeMaxCategoria;
private String nomeCategoria;    
    
// Confrontos

private int codChvConf;
private String nomeChvConf;
private String statusChvConf;
private int qtdRodadasChvConf;    
private int qtdChvs;
private int qtdCbtGeral;
private int totalVits;
private int totalDerr;

// metricas atleta

private int codInscricao;
private int totPontosPositivos;
private int totPontosNegativos;

// metricas combate

private int numRodada;
private String parteDoCorpoNGT;
private String parteDoCorpoPST;
private String descricaoCBT;
private String finalCBT;
private int totais;
private int totalFinalizacoes;
private int totalPontos;
private int totalNocautes;
// Segmentos 

private int codEvento;    
private String nomeSegmento;
private String descricaoSegmento;


// Eventos

private  int codModali;
private String dataEvento;
private String statusEvento;
private String nomeEvento;


// Árbitros

private int codArbitro;
private String nomeArbitro;
private String graduacaoArbitro;
private int codPontua;
private String nomePontua;
private int valorPontua;
private String tipoPontua;
private String descricaoPontua;
private String parteDoCorpo;

//  Acertos em partes do corpo

private int cabeca;
private int tronco;
private int bracos;
private int pernas;
private int quedagem;

// Estatisticas 

private float progCabecaPst;
private float progTroncoPst;
private float progBracoPst;
private float progPernaPst;
private float progQuedagemPst;
private float rendimentoPst;

private float progCabecaNgt;
private float progTroncoNgt;
private float progBracoNgt;
private float progPernaNgt;
private float progQuedagemNgt;
private float rendimentoNgt;


    public float getProgCabecaPst() {
        return progCabecaPst;
    }

    public void setProgCabecaPst(float progCabecaPst) {
        this.progCabecaPst = progCabecaPst;
    }

    public float getProgTroncoPst() {
        return progTroncoPst;
    }

    public void setProgTroncoPst(float progTroncoPst) {
        this.progTroncoPst = progTroncoPst;
    }

    public float getProgBracoPst() {
        return progBracoPst;
    }

    public void setProgBracoPst(float progBracoPst) {
        this.progBracoPst = progBracoPst;
    }

    public float getProgPernaPst() {
        return progPernaPst;
    }

    public void setProgPernaPst(float progPernaPst) {
        this.progPernaPst = progPernaPst;
    }

    public float getProgQuedagemPst() {
        return progQuedagemPst;
    }

    public void setProgQuedagemPst(float progQuedagemPst) {
        this.progQuedagemPst = progQuedagemPst;
    }

    public float getRendimentoPst() {
        return rendimentoPst;
    }

    public void setRendimentoPst(float rendimentoPst) {
        this.rendimentoPst = rendimentoPst;
    }

    public float getProgCabecaNgt() {
        return progCabecaNgt;
    }

    public void setProgCabecaNgt(float progCabecaNgt) {
        this.progCabecaNgt = progCabecaNgt;
    }

    public float getProgTroncoNgt() {
        return progTroncoNgt;
    }

    public void setProgTroncoNgt(float progTroncoNgt) {
        this.progTroncoNgt = progTroncoNgt;
    }

    public float getProgBracoNgt() {
        return progBracoNgt;
    }

    public void setProgBracoNgt(float progBracoNgt) {
        this.progBracoNgt = progBracoNgt;
    }

    public float getProgPernaNgt() {
        return progPernaNgt;
    }

    public void setProgPernaNgt(float progPernaNgt) {
        this.progPernaNgt = progPernaNgt;
    }

    public float getProgQuedagemNgt() {
        return progQuedagemNgt;
    }

    public void setProgQuedagemNgt(float progQuedagemNgt) {
        this.progQuedagemNgt = progQuedagemNgt;
    }

    public float getRendimentoNgt() {
        return rendimentoNgt;
    }

    public void setRendimentoNgt(float rendimentoNgt) {
        this.rendimentoNgt = rendimentoNgt;
    }



    public int getCabeca() {
        return cabeca;
    }

    public void setCabeca(int cabeca) {
        this.cabeca = cabeca;
    }

    public int getTronco() {
        return tronco;
    }

    public void setTronco(int tronco) {
        this.tronco = tronco;
    }

    public int getBracos() {
        return bracos;
    }

    public void setBracos(int bracos) {
        this.bracos = bracos;
    }

    public int getPernas() {
        return pernas;
    }

    public void setPernas(int pernas) {
        this.pernas = pernas;
    }

    public int getQuedagem() {
        return quedagem;
    }

    public void setQuedagem(int quedagem) {
        this.quedagem = quedagem;
    }

    public int getCodArbitro() {
        return codArbitro;
    }

    public void setCodArbitro(int codArbitro) {
        this.codArbitro = codArbitro;
    }

    public String getNomeArbitro() {
        return nomeArbitro;
    }

    public void setNomeArbitro(String nomeArbitro) {
        this.nomeArbitro = nomeArbitro;
    }

    public String getGraduacaoArbitro() {
        return graduacaoArbitro;
    }

    public void setGraduacaoArbitro(String graduacaoArbitro) {
        this.graduacaoArbitro = graduacaoArbitro;
    }

    public int getCodPontua() {
        return codPontua;
    }

    public void setCodPontua(int codPontua) {
        this.codPontua = codPontua;
    }

    public String getNomePontua() {
        return nomePontua;
    }

    public void setNomePontua(String nomePontua) {
        this.nomePontua = nomePontua;
    }

    public int getValorPontua() {
        return valorPontua;
    }

    public void setValorPontua(int valorPontua) {
        this.valorPontua = valorPontua;
    }

    public String getTipoPontua() {
        return tipoPontua;
    }

    public void setTipoPontua(String tipoPontua) {
        this.tipoPontua = tipoPontua;
    }

    public String getDescricaoPontua() {
        return descricaoPontua;
    }

    public void setDescricaoPontua(String descricaoPontua) {
        this.descricaoPontua = descricaoPontua;
    }

    public String getParteDoCorpo() {
        return parteDoCorpo;
    }

    public void setParteDoCorpo(String parteDoCorpo) {
        this.parteDoCorpo = parteDoCorpo;
    }


    public int getTotalFinalizacoes() {
        return totalFinalizacoes;
    }

    public void setTotalFinalizacoes(int totalFinalizacoes) {
        this.totalFinalizacoes = totalFinalizacoes;
    }

    public int getTotalPontos() {
        return totalPontos;
    }

    public void setTotalPontos(int totalPontos) {
        this.totalPontos = totalPontos;
    }

    public int getTotalNocautes() {
        return totalNocautes;
    }

    public void setTotalNocautes(int totalNocautes) {
        this.totalNocautes = totalNocautes;
    }

    public int getTotais() {
        return totais;
    }

    public void setTotais(int totais) {
        this.totais = totais;
    }
    public int getQtdChvs() {
        return qtdChvs;
    }

    public void setQtdChvs(int qtdChvs) {
        this.qtdChvs = qtdChvs;
    }

    public int getCodModali() {
        return codModali;
    }

    public void setCodModali(int codModali) {
        this.codModali = codModali;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getStatusEvento() {
        return statusEvento;
    }

    public void setStatusEvento(String statusEvento) {
        this.statusEvento = statusEvento;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getCodSegmento() {
        return codSegmento;
    }

    public void setCodSegmento(int codSegmento) {
        this.codSegmento = codSegmento;
    }

    public int getCodAtleta() {
        return codAtleta;
    }

    public void setCodAtleta(int codAtleta) {
        this.codAtleta = codAtleta;
    }

    public String getNomeAtleta() {
        return nomeAtleta;
    }

    public void setNomeAtleta(String nomeAtleta) {
        this.nomeAtleta = nomeAtleta;
    }

    public int getIdadeAtleta() {
        return idadeAtleta;
    }

    public void setIdadeAtleta(int idadeAtleta) {
        this.idadeAtleta = idadeAtleta;
    }

    public float getPesoAtleta() {
        return pesoAtleta;
    }

    public void setPesoAtleta(float pesoAtleta) {
        this.pesoAtleta = pesoAtleta;
    }

    public String getGraduacaoAtleta() {
        return graduacaoAtleta;
    }

    public void setGraduacaoAtleta(String graduacaoAtleta) {
        this.graduacaoAtleta = graduacaoAtleta;
    }

    public String getCpfAtleta() {
        return cpfAtleta;
    }

    public void setCpfAtleta(String cpfAtleta) {
        this.cpfAtleta = cpfAtleta;
    }

    public String getEmailAtleta() {
        return emailAtleta;
    }

    public void setEmailAtleta(String emailAtleta) {
        this.emailAtleta = emailAtleta;
    }

    public int getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(int codCategoria) {
        this.codCategoria = codCategoria;
    }

    public float getPesoMinCategoria() {
        return pesoMinCategoria;
    }

    public void setPesoMinCategoria(float pesoMinCategoria) {
        this.pesoMinCategoria = pesoMinCategoria;
    }

    public float getPesoMaxCategoria() {
        return pesoMaxCategoria;
    }

    public void setPesoMaxCategoria(float pesoMaxCategoria) {
        this.pesoMaxCategoria = pesoMaxCategoria;
    }

    public int getGraduacaoMinCategoria() {
        return graduacaoMinCategoria;
    }

    public void setGraduacaoMinCategoria(int graduacaoMinCategoria) {
        this.graduacaoMinCategoria = graduacaoMinCategoria;
    }

    public int getGraduacaoMaxCategoria() {
        return graduacaoMaxCategoria;
    }

    public void setGraduacaoMaxCategoria(int graduacaoMaxCategoria) {
        this.graduacaoMaxCategoria = graduacaoMaxCategoria;
    }

    public int getIdadeMinCategoria() {
        return idadeMinCategoria;
    }

    public void setIdadeMinCategoria(int idadeMinCategoria) {
        this.idadeMinCategoria = idadeMinCategoria;
    }

    public int getIdadeMaxCategoria() {
        return idadeMaxCategoria;
    }

    public void setIdadeMaxCategoria(int idadeMaxCategoria) {
        this.idadeMaxCategoria = idadeMaxCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public int getCodChvConf() {
        return codChvConf;
    }

    public void setCodChvConf(int codChvConf) {
        this.codChvConf = codChvConf;
    }

    public String getNomeChvConf() {
        return nomeChvConf;
    }

    public void setNomeChvConf(String nomeChvConf) {
        this.nomeChvConf = nomeChvConf;
    }

    public String getStatusChvConf() {
        return statusChvConf;
    }

    public void setStatusChvConf(String statusChvConf) {
        this.statusChvConf = statusChvConf;
    }

    public int getQtdRodadasChvConf() {
        return qtdRodadasChvConf;
    }

    public void setQtdRodadasChvConf(int qtdRodadasChvConf) {
        this.qtdRodadasChvConf = qtdRodadasChvConf;
    }

    public int getCodInscricao() {
        return codInscricao;
    }

    public void setCodInscricao(int codInscricao) {
        this.codInscricao = codInscricao;
    }

    public int getTotPontosPositivos() {
        return totPontosPositivos;
    }

    public void setTotPontosPositivos(int totPontosPositivos) {
        this.totPontosPositivos = totPontosPositivos;
    }

    public int getTotPontosNegativos() {
        return totPontosNegativos;
    }

    public void setTotPontosNegativos(int totPontosNegativos) {
        this.totPontosNegativos = totPontosNegativos;
    }

    public int getNumRodada() {
        return numRodada;
    }

    public void setNumRodada(int numRodada) {
        this.numRodada = numRodada;
    }

    public String getParteDoCorpoNGT() {
        return parteDoCorpoNGT;
    }

    public void setParteDoCorpoNGT(String parteDoCorpoNGT) {
        this.parteDoCorpoNGT = parteDoCorpoNGT;
    }

    public String getParteDoCorpoPST() {
        return parteDoCorpoPST;
    }

    public void setParteDoCorpoPST(String parteDoCorpoPST) {
        this.parteDoCorpoPST = parteDoCorpoPST;
    }

    public String getDescricaoCBT() {
        return descricaoCBT;
    }

    public void setDescricaoCBT(String descricaoCBT) {
        this.descricaoCBT = descricaoCBT;
    }

    public String getFinalCBT() {
        return finalCBT;
    }

    public void setFinalCBT(String finalCBT) {
        this.finalCBT = finalCBT;
    }

    public int getCodEvento() {
        return codEvento;
    }

    public void setCodEvento(int codEvento) {
        this.codEvento = codEvento;
    }

    public String getNomeSegmento() {
        return nomeSegmento;
    }

    public void setNomeSegmento(String nomeSegmento) {
        this.nomeSegmento = nomeSegmento;
    }

    public String getDescricaoSegmento() {
        return descricaoSegmento;
    }

    public void setDescricaoSegmento(String descricaoSegmento) {
        this.descricaoSegmento = descricaoSegmento;
    }

    public int getQtdCbtGeral() {
        return qtdCbtGeral;
    }

    public void setQtdCbtGeral(int qtdCbtGeral) {
        this.qtdCbtGeral = qtdCbtGeral;
    }

    public int getTotalVits() {
        return totalVits;
    }

    public void setTotalVits(int totalVits) {
        this.totalVits = totalVits;
    }

    public int getTotalDerr() {
        return totalDerr;
    }

    public void setTotalDerr(int totalDerr) {
        this.totalDerr = totalDerr;
    }





}
