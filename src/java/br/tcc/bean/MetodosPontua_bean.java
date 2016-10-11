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
public class MetodosPontua_bean {

private int codPontua;
private int codSegmento;
private String nomePontua;
private int valorPontua;
private String tipoPontua;
private String descricaoPontua;
private String parteDoCorpo;
private boolean selected;

    public int getCodPontua() {
        return codPontua;
    }

    public void setCodPontua(int codPontua) {
        this.codPontua = codPontua;
    }

    public int getCodSegmento() {
        return codSegmento;
    }

    public void setCodSegmento(int codSegmento) {
        this.codSegmento = codSegmento;
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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    
}
