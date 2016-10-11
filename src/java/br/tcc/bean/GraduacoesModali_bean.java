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
public class GraduacoesModali_bean {
    
    private int codModali;
    private int codGraduMod;
    private String identificacaoGradu;
    private String tipoGradu;
    private int qtd;  
    private boolean selected;

    
    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
    
    public int getCodModali() {
        return codModali;
    }

    public void setCodModali(int codModali) {
        this.codModali = codModali;
    }

    public int getCodGraduMod() {
        return codGraduMod;
    }

    public void setCodGraduMod(int codGraduMod) {
        this.codGraduMod = codGraduMod;
    }

    public String getIdentificacaoGradu() {
        return identificacaoGradu;
    }

    public void setIdentificacaoGradu(String identificacaoGradu) {
        this.identificacaoGradu = identificacaoGradu;
    }

    public String getTipoGradu() {
        return tipoGradu;
    }

    public void setTipoGradu(String tipoGradu) {
        this.tipoGradu = tipoGradu;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    
    
}
