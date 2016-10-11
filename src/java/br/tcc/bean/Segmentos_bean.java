
package br.tcc.bean;

/**
 *
 * @author joãomarcos
 */
public class Segmentos_bean {

private int codEvento;    
private int codSegmento;
private String nomeSegmento;
private String descricaoSegmento;
private boolean selected;

    public int getCodEvento() {
        return codEvento;
    }

    public void setCodEvento(int codEvento) {
        this.codEvento = codEvento;
    }

    public int getCodSegmento() {
        return codSegmento;
    }

    public void setCodSegmento(int codSegmento) {
        this.codSegmento = codSegmento;
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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }





}
