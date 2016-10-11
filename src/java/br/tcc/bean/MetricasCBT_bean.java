/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tcc.bean;

import java.io.Serializable;

/**
 *
 * @author joãomarcos
 */
public class MetricasCBT_bean implements Serializable{
    
private int codChvConf;
private int codInscricao;
private int numRodada;
private String parteDoCorpoNGT;
private String parteDoCorpoPST;
private String descricaoCBT;
private String finalCBT;
private int totPontosPositivos;
private int totPontosNegativos ;

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



    public String getFinalCBT() {
        return finalCBT;
    }

    public void setFinalCBT(String finalCBT) {
        this.finalCBT = finalCBT;
    }

    public int getCodChvConf() {
        return codChvConf;
    }

    public void setCodChvConf(int codChvConf) {
        this.codChvConf = codChvConf;
    }

    public int getCodInscricao() {
        return codInscricao;
    }

    public void setCodInscricao(int codInscricao) {
        this.codInscricao = codInscricao;
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
    


}
