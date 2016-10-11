
package br.tcc.controller;

import br.tcc.bean.Categorias_bean;
import br.tcc.bean.Eventoss_bean;
import br.tcc.bean.Relatorios_bean;
import br.tcc.bean.Segmentos_bean;
import br.tcc.dao.Confrontos_dao;
import br.tcc.dao.Eventos_dao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author joãomarcos
 */
@ManagedBean(name="VencedoresManagedBean", eager = true)
@SessionScoped
public class VencedoresManagedBean {
    
    private int codEvento;
    private  int codModali;
    private int codSegmento;
    private  int codCategoria;
    private String codModSelecionado;
    private String codEveSelecionado;
    private String codSegSelecionado;
    private String codCatSelecionado;
    
    private List<String> ComboSegs = new ArrayList<>();
    private List<String> comboEve = new ArrayList<>();
    private List<String> comboCats = new ArrayList<>();
    
    private List<Eventoss_bean> eve = new ArrayList<>();
    private List<Segmentos_bean> listaSegs = new ArrayList<>();
    private List<Categorias_bean> listaCatSegs = new ArrayList<>() ;
    private List<Relatorios_bean> ch1 = new ArrayList<>() ;
    private List<Relatorios_bean> ch2 = new ArrayList<>() ;
    private List<Relatorios_bean> listaVencedores = new ArrayList<>() ;
    
    
    
    ////////////////////////////////////////////////////////// Metodos consulta //////////////////////////////////////////////////////
    
    public void consultaVencedores(){
        
        ch1.clear();
        ch2.clear();
        listaVencedores.clear();

        // Verifico se a categoria foi selecionada. Caso tenha sido, já acerto seu valor na variavel
        if(!codCatSelecionado.equals("")){
            codCategoria = Integer.valueOf(codCatSelecionado.substring(0,codCatSelecionado.indexOf("-")).trim());
            }
        try {
            Confrontos_dao dao = new Confrontos_dao();
        if(codEvento != 0){
            // Realizo a consulta passando os parâmetros, e populo as listas
            ch1 = dao.relatorioVencedoresCHV1(codEvento,codSegmento, codCategoria);
            ch2 = dao.relatorioVencedoresCHV2(codEvento,codSegmento, codCategoria);
            
            // Adiciono na lista de vencedores o resultado
            for(int i = 0 ; i < ch1.size(); i++){
                listaVencedores.add(ch1.get(i));
                
            }
            for(int i = 0 ; i < ch2.size(); i++){
                listaVencedores.add(ch2.get(i));
                
            }
            
            if(listaVencedores.isEmpty()){
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção", "Não há vencedores com os dados desta consulta."));        
            }
        }else{
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "Selecione o evento que gostaria de consultar os vencedores."));                
        }
            
        } catch (SQLException ex) {
            Logger.getLogger(VencedoresManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    ////////////////////////////////////////////////////////// Metodos combos //////////////////////////////////////////////////////
    
    public void carregarComboEv() throws SQLException{
    
     Eventos_dao dao = new Eventos_dao();    
    comboEve.clear();
    ComboSegs.clear();
    if(codModSelecionado.equals("")){
        
        comboEve.clear();
        ComboSegs.clear();
       comboCats.clear();
       codCategoria = 0;
       codEvento = 0;
       codSegmento = 0;
    }
    else{
    
    //codModali = Integer.valueOf(codModSelecionado.replaceAll("\\D",""));
    codModali = Integer.valueOf(codModSelecionado.substring(0,codModSelecionado.indexOf(" ")));
    eve = dao.pesquisaEventos(codModali);
    
    for(int i = 0; i <eve.size(); i++){
        
      comboEve.add(eve.get(i).getCodEvento()+" - "+eve.get(i).getNomeEvento());
        
    }
    }
    }
    
    
    
    public void carregaCombos()throws SQLException{
      Eventos_dao dao = new Eventos_dao();    
        if(codEveSelecionado.equals("")){
        
        ComboSegs.clear();
        comboCats.clear();
        
       codCategoria = 0;
       codEvento = 0;
       codSegmento = 0;
    }else{
   
   ComboSegs.clear();
   codEvento = Integer.valueOf(codEveSelecionado.substring(0,codEveSelecionado.indexOf(" ")));   
   listaSegs = dao.consultaSegs(codEvento);
   
  if(listaSegs.isEmpty()){
        
        ComboSegs.clear();
   
    }else
      for(int i = 0; i< listaSegs.size(); i++){
      
          ComboSegs.add(listaSegs.get(i).getCodSegmento()+" - "+ listaSegs.get(i).getNomeSegmento());
          
      }
    }
        
    }
    
    
    public void carregaComboCat()throws SQLException{
   Eventos_dao dao = new Eventos_dao();
   comboCats.clear();
   if(codSegSelecionado.equals("")){
    comboCats.clear();
    codCategoria = 0;
    codEvento = 0;
    codSegmento = 0;
   }else{
   listaCatSegs.clear();
   codSegmento = Integer.valueOf(codSegSelecionado.substring(0,codSegSelecionado.indexOf("-")).trim());
   listaCatSegs = dao.consultaCategoriasSegmento(codSegmento);
   
   if(listaCatSegs.isEmpty()){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", "Não há categorias para este segmento"));
   }else{
   for(int i = 0; i < listaCatSegs.size();i++){
       comboCats.add(listaCatSegs.get(i).getCodCategoria()+" - "+ listaCatSegs.get(i).getNomeCategoria());
   }
   }
  
   }
    }
    
    ////////////////////////////////////////////////////////// Metodos adicionais //////////////////////////////////////////////////////
    
    
    public void limparTabela(){
        listaVencedores.clear();
    }
    
    
    public void linkVencedores() throws IOException{
    
  
    FacesContext.getCurrentInstance().getExternalContext().redirect("./Vencedores.xhtml");
    
} 
   
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int getCodEvento() {
        return codEvento;
    }

    public void setCodEvento(int codEvento) {
        this.codEvento = codEvento;
    }

    public int getCodModali() {
        return codModali;
    }

    public void setCodModali(int codModali) {
        this.codModali = codModali;
    }

    public String getCodModSelecionado() {
        return codModSelecionado;
    }

    public void setCodModSelecionado(String codModSelecionado) {
        this.codModSelecionado = codModSelecionado;
    }

    public String getCodEveSelecionado() {
        return codEveSelecionado;
    }

    public void setCodEveSelecionado(String codEveSelecionado) {
        this.codEveSelecionado = codEveSelecionado;
    }

    public List<String> getComboSegs() {
        return ComboSegs;
    }

    public void setComboSegs(List<String> ComboSegs) {
        this.ComboSegs = ComboSegs;
    }

    public List<String> getComboEve() {
        return comboEve;
    }

    public void setComboEve(List<String> comboEve) {
        this.comboEve = comboEve;
    }

    public List<Eventoss_bean> getEve() {
        return eve;
    }

    public void setEve(List<Eventoss_bean> eve) {
        this.eve = eve;
    }

    public List<Segmentos_bean> getListaSegs() {
        return listaSegs;
    }

    public void setListaSegs(List<Segmentos_bean> listaSegs) {
        this.listaSegs = listaSegs;
    }

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

    public String getCodSegSelecionado() {
        return codSegSelecionado;
    }

    public void setCodSegSelecionado(String codSegSelecionado) {
        this.codSegSelecionado = codSegSelecionado;
    }

    public String getCodCatSelecionado() {
        return codCatSelecionado;
    }

    public void setCodCatSelecionado(String codCatSelecionado) {
        this.codCatSelecionado = codCatSelecionado;
    }

    public List<String> getComboCats() {
        return comboCats;
    }

    public void setComboCats(List<String> comboCats) {
        this.comboCats = comboCats;
    }

    public List<Categorias_bean> getListaCatSegs() {
        return listaCatSegs;
    }

    public void setListaCatSegs(List<Categorias_bean> listaCatSegs) {
        this.listaCatSegs = listaCatSegs;
    }

    public List<Relatorios_bean> getCh1() {
        return ch1;
    }

    public void setCh1(List<Relatorios_bean> ch1) {
        this.ch1 = ch1;
    }

    public List<Relatorios_bean> getCh2() {
        return ch2;
    }

    public void setCh2(List<Relatorios_bean> ch2) {
        this.ch2 = ch2;
    }

    public List<Relatorios_bean> getListaVencedores() {
        return listaVencedores;
    }

    public void setListaVencedores(List<Relatorios_bean> listaVencedores) {
        this.listaVencedores = listaVencedores;
    }
    
    
    
}
