
package br.tcc.controller;

import br.tcc.bean.Atletas_bean;
import br.tcc.bean.Categorias_bean;
import br.tcc.bean.InscricaoPRT_bean;
import br.tcc.dao.Atletas_dao;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author joãomarcos
 */

@ManagedBean(name="InscricaoManagedBean", eager = true)
@SessionScoped
public class InscricaoManagedBean implements Serializable{
    
     
private int codSegmento;
private int codAtleta;
private int codCategoria;
private int codInscricao;
private String statusInscricao;
private String nomeAtleta;
private String segmentoSelecionado;
private Atletas_bean atl;
String categoria;

InscricaoPRT_bean inscBean = new InscricaoPRT_bean();
Atletas_bean atlBean = new Atletas_bean();

private List<Categorias_bean> categs = new ArrayList<>();
private List<Atletas_bean> listaATL = new ArrayList<>();
private List<InscricaoPRT_bean> codInsc = new ArrayList<>();
private List<String> comboCategs = new ArrayList<>();
//Atletas_dao dao = new Atletas_dao();


public void inserirInscricao()throws SQLException{
    
      Atletas_dao dao = new Atletas_dao();
      
      inscBean.setCodAtleta(codAtleta);
      inscBean.setCodSegmento(codSegmento);
      inscBean.setCodCategoria(codCategoria);
      
      codInsc = dao.inserirInscAtleta(inscBean);
      
      codInscricao = codInsc.get(0).getCodInscricao();
      
      FacesContext context = FacesContext.getCurrentInstance();
    context.addMessage(null, new FacesMessage("Sucesso","Atleta "+ nomeAtleta +" inscrito"));
    
}









public void consultaATL()throws SQLException{
    
    Atletas_dao dao = new Atletas_dao();
    
    if(!segmentoSelecionado.equals("")){
    codSegmento = Integer.valueOf(segmentoSelecionado.substring(0, segmentoSelecionado.indexOf("-")).trim());
    }
    String sql = "select * from TB_atleta where ";

    
    if(codAtleta == 0 && nomeAtleta.equals("") && segmentoSelecionado.equals("")){
        
        sql = "select * from TB_atleta";
    }
    else if(codAtleta == 0 && nomeAtleta.equals("") && !segmentoSelecionado.equals("")){
        sql = "select * from TB_atleta where graduacaoAtleta in (select codGraduMod from TB_graduaModali where\n" +
"codModali = (select codModali from TB_evento\n" +
"where codEvento =\n" +
"(select codEvento from TB_segmentos where codSegmento =  "+codSegmento +"))\n" +
"and tipoGradu = 'Atleta')";
    }
    
    else if(codAtleta != 0 && segmentoSelecionado.equals("")){
        
        sql+= "codAtleta = "+codAtleta;
        
    }else if(!nomeAtleta.equals("") && codAtleta == 0 && segmentoSelecionado.equals("")){
        
        sql+= "nomeAtleta like  '"+nomeAtleta+"%'";
        
    }else if(nomeAtleta.equals("") && codAtleta != 0 && !segmentoSelecionado.equals("") || !nomeAtleta.equals("") && codAtleta != 0 && !segmentoSelecionado.equals("")){
        
        sql += "graduacaoAtleta in (select codGraduMod from TB_graduaModali where\n" +
"codModali = (select codModali from TB_evento\n" +
"where codEvento =\n" +
"(select codEvento from TB_segmentos where codSegmento =  "+codSegmento+"))\n" +
"and tipoGradu = 'Atleta')"+
                "and codAtleta = "+codAtleta;
    }else if(!nomeAtleta.equals("") && codAtleta == 0 && !segmentoSelecionado.equals("")){
        
        sql += "graduacaoAtleta in (select codGraduMod from TB_graduaModali where\n" +
"codModali = (select codModali from TB_evento\n" +
"where codEvento =\n" +
"(select codEvento from TB_segmentos where codSegmento =  "+codSegmento +"))\n" +
"and tipoGradu = 'Atleta')"+
                " and nomeAtleta like  '"+nomeAtleta+"%'";
    }
    
   
   listaATL = dao.pesquisaAtletas(sql);
}


public void editar()throws SQLException{
    if(atl != null){
        
        //codSegmento = Integer.valueOf(segmentoSelecionado.substring(0, segmentoSelecionado.indexOf("-")).trim());
        
        Atletas_dao dao = new Atletas_dao();
        
        codAtleta = atl.getCodAtleta(); 
        nomeAtleta = atl.getNomeAtleta();
        
       if(!segmentoSelecionado.equals("")) {
           codSegmento = Integer.valueOf(segmentoSelecionado.substring(0, segmentoSelecionado.indexOf("-")).trim());
        //  int num =  dao.consultaATLestaINSC(codAtleta, codSegmento);
         
//           if(num > 0){
//               categoria = "";
//               codInscricao = num;
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", "\nO atleta já esta inscrito\n"
//                    + "nesta modalidade, e não pode realizar nova inscrição na mesma."));
//
//           }else{
           categoria = "";
           codCategoria = 0;
           codInscricao = 0;
           comboCategs.clear();
           
        categs = dao.pesquisaCatATL(codAtleta,codSegmento);
        
        if(categs.isEmpty()){
         
            categoria = "";
            codCategoria = 0;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "\nO participante não pode competir\n"
                    + "pois não se encaixa em nenhuma das categorias disponíveis\n do segmento selecionado"));
            
        }else{
        comboCategs.clear();
        for(int i = 0; i< categs.size(); i++){
            comboCategs.add(categs.get(i).getCodCategoria()+" - "+ categs.get(i).getNomeCategoria());
        }
            
     //   categoria = categs.get(0).getCodCategoria()+" - "+ categs.get(0).getNomeCategoria();
     //   codCategoria = categs.get(0).getCodCategoria();
        }
 //   }
       }
    }
}

public void verificaParticip()throws SQLException{
     Atletas_dao dao = new Atletas_dao();
     codCategoria = Integer.valueOf(categoria.substring(0, categoria.indexOf("-")).trim());
      int num =  dao.consultaATLestaINSC(codAtleta, codSegmento,codCategoria);
         
           if(num > 0){
               categoria = "";
               codCategoria = 0;
//               codInscricao = num;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "\nO atleta já esta inscrito\n"
                    + "nesta modalidade, e não pode realizar nova inscrição na mesma."));

           }
}

public void limparCampos(){
    
    nomeAtleta = "";
    codAtleta = 0;
    categoria = "";
    codCategoria = 0;
    codInscricao = 0;
}




/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

    public int getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(int codCategoria) {
        this.codCategoria = codCategoria;
    }

    public int getCodInscricao() {
        return codInscricao;
    }

    public void setCodInscricao(int codInscricao) {
        this.codInscricao = codInscricao;
    }

    public String getStatusInscricao() {
        return statusInscricao;
    }

    public void setStatusInscricao(String statusInscricao) {
        this.statusInscricao = statusInscricao;
    }

    public InscricaoPRT_bean getInscBean() {
        return inscBean;
    }

    public void setInscBean(InscricaoPRT_bean inscBean) {
        this.inscBean = inscBean;
    }

    public List<Categorias_bean> getCategs() {
        return categs;
    }

    public void setCategs(List<Categorias_bean> categs) {
        this.categs = categs;
    }

    public List<Atletas_bean> getListaATL() {
        return listaATL;
    }

    public void setListaATL(List<Atletas_bean> listaATL) {
        this.listaATL = listaATL;
    }

    public List<InscricaoPRT_bean> getCodInsc() {
        return codInsc;
    }

    public void setCodInsc(List<InscricaoPRT_bean> codInsc) {
        this.codInsc = codInsc;
    }

    public String getNomeAtleta() {
        return nomeAtleta;
    }

    public void setNomeAtleta(String nomeAtleta) {
        this.nomeAtleta = nomeAtleta;
    }

    public String getSegmentoSelecionado() {
        return segmentoSelecionado;
    }

    public void setSegmentoSelecionado(String segmentoSelecionado) {
        this.segmentoSelecionado = segmentoSelecionado;
    }

    public Atletas_bean getAtl() {
        return atl;
    }

    public void setAtl(Atletas_bean atl) {
        this.atl = atl;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<String> getComboCategs() {
        return comboCategs;
    }

    public void setComboCategs(List<String> comboCategs) {
        this.comboCategs = comboCategs;
    }





}
