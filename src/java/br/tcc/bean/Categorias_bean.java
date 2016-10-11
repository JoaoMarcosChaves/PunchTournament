
package br.tcc.bean;

/**
 *
 * @author joãomarcos
 */
public class Categorias_bean {

private int codCategoria;
private int codSegmento ;
private float pesoMinCategoria;
private float pesoMaxCategoria;
private int graduacaoMinCategoria;
private int graduacaoMaxCategoria;
private int idadeMinCategoria;
private int idadeMaxCategoria;
private String sexo;
private String nomeCategoria;
private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }



    public int getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(int codCategoria) {
        this.codCategoria = codCategoria;
    }

    public int getCodSegmento() {
        return codSegmento;
    }

    public void setCodSegmento(int codSegmento) {
        this.codSegmento = codSegmento;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }




}
