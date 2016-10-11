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
public class Modalidades_bean {
    
    private int codModali;
    private String nomeModali;
    private String descricao;
    private boolean selected;
    
    public int getCodModali() {
        return codModali;
    }

    public void setCodModali(int codModali) {
        this.codModali = codModali;
    }

    public String getNomeModali() {
        return nomeModali;
    }

    public void setNomeModali(String nomeModali) {
        this.nomeModali = nomeModali;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    
    
}
