
package br.tcc.controller;

import br.tcc.bean.Atletas_bean;
import br.tcc.bean.Categorias_bean;
import br.tcc.bean.Eventoss_bean;
import br.tcc.bean.InscricaoPRT_bean;
import br.tcc.bean.Modalidades_bean;
import br.tcc.bean.Segmentos_bean;
import br.tcc.dao.Atletas_dao;
import br.tcc.dao.Eventos_dao;
import br.tcc.dao.Modalidades_dao;
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
public class InscricaoManagedBean{
    
     
private int codSegmento;
private int codAtleta;
private int codCategoria;
private int codInscricao;
private String statusInscricao;
private String nomeAtleta;
private String segmentoSelecionado;
private Atletas_bean atl;
String categoria;

private int codInscPend;
private InscricaoPRT_bean inscPendSelec;


InscricaoPRT_bean inscBean = new InscricaoPRT_bean();
Atletas_bean atlBean = new Atletas_bean();


private List<Categorias_bean> categs = new ArrayList<>();
private List<Atletas_bean> listaATL = new ArrayList<>();
private List<InscricaoPRT_bean> codInsc = new ArrayList<>();
private List<String> comboCategs = new ArrayList<>();
private List<InscricaoPRT_bean> listaInscsPend = new ArrayList<>();


// VARIAVEIS NECESSÁRIAS PARA PREENCHER COMBOS DE MOD, EVE,E SEG.
private List<Modalidades_bean> modalidades = new ArrayList<>();
private List <String> comboMod = new ArrayList<>();
private List <String> comboEve = new ArrayList<>();
private String codModSelecionado;
private List<String> ComboSegs = new ArrayList<>();
private int codModali;
private List<Eventoss_bean> eve = new ArrayList<>();

private int codEvento;
private String codEveSelecionado;
private List<Segmentos_bean> listaSegs = new ArrayList<>();

public InscricaoManagedBean()throws SQLException{
    Modalidades_dao dao = new Modalidades_dao();
        
        modalidades = dao.PesqMod();
        for(int i = 0; i <modalidades.size(); i++){
        
      comboMod.add(modalidades.get(i).getCodModali()+" - "+modalidades.get(i).getNomeModali());
        
    }
}


public void carregarComboEv() throws SQLException{
    Eventos_dao dao = new Eventos_dao();
    comboEve.clear();
    ComboSegs.clear();
    
    if(codModSelecionado.equals("")){
        
        comboEve.clear();
        ComboSegs.clear();
       
    }
    else{
    
    
    codModali = Integer.valueOf(codModSelecionado.substring(0,codModSelecionado.indexOf(" ")));
    eve = dao.pesquisaEventos(codModali);
    
    for(int i = 0; i <eve.size(); i++){
        
      comboEve.add(eve.get(i).getCodEvento()+" - "+eve.get(i).getNomeEvento());
        
    }
    }
}

public void carregaComboSegEcategs() throws SQLException{
   
   Eventos_dao dao = new Eventos_dao();
    if(codEveSelecionado.equals("")){
        
        ComboSegs.clear();
        
        codEvento = 0;
        
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
  
  eve = dao.consultaDataEve(codEvento);
     
    }
    listaSegs.clear();
}



public void inserirInscricao()throws SQLException{
    
      Atletas_dao dao = new Atletas_dao();
      
      if(codCategoria != 0){
          inscBean.setCodAtleta(codAtleta);
      inscBean.setCodSegmento(codSegmento);
      inscBean.setCodCategoria(codCategoria);
      
      codInsc = dao.inserirInscAtleta(inscBean);
      
      codInscricao = codInsc.get(0).getCodInscricao();
      
      FacesContext context = FacesContext.getCurrentInstance();
    context.addMessage(null, new FacesMessage("Sucesso","Atleta "+ nomeAtleta +" inscrito"));
      }else{
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "Selecione evento, segmento e categoria para realizar a inscrição"));
      }
      
      
    
}

public void confirmaInscricao()throws SQLException{
    Atletas_dao dao = new Atletas_dao();
    dao.confirmaInscricao(codInscPend);
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Inscrição confirmada com sucesso."));
    inscBean.setCodAtleta(codAtleta);
    listaInscsPend = dao.consultaInscPend(inscBean);
}

public void consultaATL()throws SQLException{
    
    Atletas_dao dao = new Atletas_dao();
    
    if(!codModSelecionado.equals("")){
    Atletas_bean bean = new Atletas_bean();
    bean.setCodModalidade(codModali);
    bean.setCodAtleta(codAtleta);
    bean.setNomeAtleta(nomeAtleta);
    listaATL = dao.pesquisaAtletas(bean);
    }else{
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "Selecione uma modalidade antes de realizar a pesquisa."));
    }
}


public void editar()throws SQLException{
    if(atl != null){
        
        //codSegmento = Integer.valueOf(segmentoSelecionado.substring(0, segmentoSelecionado.indexOf("-")).trim());
        
        Atletas_dao dao = new Atletas_dao();
        
        codAtleta = atl.getCodAtleta(); 
        nomeAtleta = atl.getNomeAtleta();
        inscBean.setCodAtleta(codAtleta);
        listaInscsPend = dao.consultaInscPend(inscBean);
        
        
       if(!segmentoSelecionado.equals("")) {
           codSegmento = Integer.valueOf(segmentoSelecionado.substring(0, segmentoSelecionado.indexOf("-")).trim());
        
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
            
    
        }

       }
    }
}

public void linhaSelecionadaInscsPend(){
if(inscPendSelec != null){
codInscPend = inscPendSelec.getCodInscricao();
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
    
public int getCodInscPend() {
        return codInscPend;
    }

    public void setCodInscPend(int codInscPend) {
        this.codInscPend = codInscPend;
    }

    public List<InscricaoPRT_bean> getListaInscsPend() {
        return listaInscsPend;
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setListaInscsPend(List<InscricaoPRT_bean> listaInscsPend) {
        this.listaInscsPend = listaInscsPend;
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

    public InscricaoPRT_bean getInscPendSelec() {
        return inscPendSelec;
    }

    public void setInscPendSelec(InscricaoPRT_bean inscPendSelec) {
        this.inscPendSelec = inscPendSelec;
    }

    public Atletas_bean getAtlBean() {
        return atlBean;
    }

    public void setAtlBean(Atletas_bean atlBean) {
        this.atlBean = atlBean;
    }

    public List<Modalidades_bean> getModalidades() {
        return modalidades;
    }

    public void setModalidades(List<Modalidades_bean> modalidades) {
        this.modalidades = modalidades;
    }

    public List<String> getComboMod() {
        return comboMod;
    }

    public void setComboMod(List<String> comboMod) {
        this.comboMod = comboMod;
    }

    public List<String> getComboEve() {
        return comboEve;
    }

    public void setComboEve(List<String> comboEve) {
        this.comboEve = comboEve;
    }

    public String getCodModSelecionado() {
        return codModSelecionado;
    }

    public void setCodModSelecionado(String codModSelecionado) {
        this.codModSelecionado = codModSelecionado;
    }

    public List<String> getComboSegs() {
        return ComboSegs;
    }

    public void setComboSegs(List<String> ComboSegs) {
        this.ComboSegs = ComboSegs;
    }

    public int getCodModali() {
        return codModali;
    }

    public void setCodModali(int codModali) {
        this.codModali = codModali;
    }

    public List<Eventoss_bean> getEve() {
        return eve;
    }

    public void setEve(List<Eventoss_bean> eve) {
        this.eve = eve;
    }

    public int getCodEvento() {
        return codEvento;
    }

    public void setCodEvento(int codEvento) {
        this.codEvento = codEvento;
    }

    public String getCodEveSelecionado() {
        return codEveSelecionado;
    }

    public void setCodEveSelecionado(String codEveSelecionado) {
        this.codEveSelecionado = codEveSelecionado;
    }

    public List<Segmentos_bean> getListaSegs() {
        return listaSegs;
    }

    public void setListaSegs(List<Segmentos_bean> listaSegs) {
        this.listaSegs = listaSegs;
    }

   





}
