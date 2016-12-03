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
public class Atletas_bean {

private int codModalidade;
private int codSegmento;   
private int codAtleta;
private String nomeAtleta;
private int idadeAtleta;
private String sexo;
private float pesoAtleta;
private String graduacaoAtleta;
private String cpfAtleta;
private String emailAtleta;
private boolean selected;

    public int getCodModalidade() {
        return codModalidade;
    }

    public void setCodModalidade(int codModalidade) {
        this.codModalidade = codModalidade;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }



}
