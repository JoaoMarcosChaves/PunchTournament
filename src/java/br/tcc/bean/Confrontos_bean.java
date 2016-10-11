
package br.tcc.bean;

import java.io.Serializable;

public class Confrontos_bean implements Serializable{
private int codSegmento ;
private int codCategoria;
private int codChvConf;
private String nomeChvConf;
private String statusChvConf;
private int qtdRodadasChvConf;    
private boolean selected;

    public int getCodSegmento() {
        return codSegmento;
    }

    public void setCodSegmento(int codSegmento) {
        this.codSegmento = codSegmento;
    }

    public int getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(int codCategoria) {
        this.codCategoria = codCategoria;
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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }




}
