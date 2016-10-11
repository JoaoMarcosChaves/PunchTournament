/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tcc.controller;

import br.tcc.bean.Arbitros_bean;
import br.tcc.bean.Categorias_bean;
import br.tcc.bean.Eventoss_bean;
import br.tcc.bean.GraduacoesModali_bean;
import br.tcc.bean.MetodosPontua_bean;
import br.tcc.bean.Modalidades_bean;
import br.tcc.bean.Segmentos_bean;
import br.tcc.dao.Eventos_dao;
import br.tcc.dao.Modalidades_dao;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.SQLException;

import java.time.LocalDate;
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
@ManagedBean(name="EventosManagedBean", eager = true)
@SessionScoped
public class EventosManagedBean {
    
boolean alteracao = false;
    
private int codEvento;
private  int codModali;
private Date dataEvento;
private String statusEvento;
private String nomeEvento;
private String codEveSelecionado;
private String codModSelecionado;
private String eventoDaLista;
private int codSegmento;
private String nomeSegmento;
private String descricaoSegmento;
private String dataEventoCons;
private String codSegSelecionado;

private List<String> ComboSegs = new ArrayList<>();
private List <String> comboMod = new ArrayList<>();
private List <String> comboEve = new ArrayList<>();
private List<Eventoss_bean> eve = new ArrayList<>();
private List<Modalidades_bean> modalidades = new ArrayList<>();
private List<Segmentos_bean> listaSegs = new ArrayList<>();


private String altNomeEvento;
private int altCodSegmento;
private String altNomeSegmento;
private String altDescricaoSegmento;
private Date altDataEventoCons;
private Segmentos_bean segSelecionadoTb;

private List<Segmentos_bean> altListaSegs = new ArrayList<>();
List<Integer> segsRemove = new ArrayList<>();
List<Segmentos_bean> segsAlterados = new ArrayList<>();
List<Segmentos_bean> segsAdicionados = new ArrayList<>();

private String nomeCat;
private String graduDeSelecionada;
private String graduAteSelecionada;
private int idadeDe;
private int idadeate;
private float pesoDe;
private float pesoAte;
private String sexo;


private List<GraduacoesModali_bean> graduacoesModaliARB = new ArrayList<>();
private List<GraduacoesModali_bean> graduacoesModaliATL = new ArrayList<>() ;
private List<String> comboGraduacoesATL = new ArrayList<>() ;
private List<Categorias_bean> listaCat = new ArrayList<>() ;
private List<Categorias_bean> listaCatSegs = new ArrayList<>() ;

private String nomePontua;
private int valorPontua;
private String tipoPontua;
private String ptCorpoPontua;
private String descricaoPontua;

private List<MetodosPontua_bean> listaPontos = new ArrayList<>();


private int codArbitro;
private String nomeArbitro;
private String graduArbSelecionada;
private String graduacaoArbitro;
private String pontuaSelecionado;
private String arbitroSelecionado;
private int codPontua;

private List<Arbitros_bean> ptPorARB = new ArrayList<>();
private List<Arbitros_bean> arbitro = new ArrayList<>();
private List<String> comboGraduacoesARB = new ArrayList<>() ;
private List<MetodosPontua_bean> listaPontosParaARB = new ArrayList<>();
private List<String> comboPontosParaARB = new ArrayList<>();
private List<String> comboArbitros = new ArrayList<>();

private List<MetodosPontua_bean> visListaPontos = new ArrayList<>();
private List<Arbitros_bean> visPtPorARB = new ArrayList<>();
private List<Arbitros_bean> visARB = new ArrayList<>();
private List<Categorias_bean> visListaCat = new ArrayList<>() ;
private List<Integer> visListaCatRemove = new ArrayList<>() ;
private List<Integer> visARBRemove = new ArrayList<>() ;
private List<Arbitros_bean> visPtPorARBRemove = new ArrayList<>() ;
private List<Integer> visListaPontosRemove = new ArrayList<>() ;
private MetodosPontua_bean metSelecionadoVis;
private Arbitros_bean ptPorARBSelecionadoVis;
private Arbitros_bean ARBSelecionadoVis;
private Categorias_bean catSelecionadaVis;


Arbitros_bean arbtBean = new Arbitros_bean();
MetodosPontua_bean metPontoBean = new MetodosPontua_bean();
Eventoss_bean bean = new Eventoss_bean();
Segmentos_bean segBean = new Segmentos_bean();
Eventos_dao dao = new Eventos_dao();
Categorias_bean catBean = new Categorias_bean();

public EventosManagedBean()throws SQLException{
    
    Modalidades_dao dao = new Modalidades_dao();
        
        modalidades = dao.PesqMod();
        for(int i = 0; i <modalidades.size(); i++){
        
      comboMod.add(modalidades.get(i).getCodModali()+" - "+modalidades.get(i).getNomeModali());
        
    }
}

//////////////////////////////////////////////////// métodos inserção //////////////////////////////////////////////////////

public void inserirEve(){
    
    try {
        SimpleDateFormat formatador = null;
        
        formatador = new SimpleDateFormat("dd/MM/yyyy");
        
        
        bean.setCodModali(codModali);
        bean.setNomeEvento(nomeEvento); 
        bean.setDataEvento(String.valueOf(formatador.format(dataEvento)));
        eve = dao.inserirEventos(bean);
        codEvento = eve.get(0).getCodEvento();
        
        if(!listaSegs.isEmpty()){
        for(int i =0; i< listaSegs.size(); i++){
          
        segBean.setCodEvento(codEvento);
        segBean.setNomeSegmento(listaSegs.get(i).getNomeSegmento());
        segBean.setDescricaoSegmento(listaSegs.get(i).getDescricaoSegmento());
        dao.inserirSegs(segBean);
        
        }
        }
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso","Evento "+ nomeEvento +" cadastrado"));
        carregarComboEv();
        limparCamposInseEve();
    } catch (SQLException ex) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "\n"+ ex));
    }
}

public void inserirCategoria() {
    
    try {

        for(int i = 0; i < listaCat.size();i++){
        
        catBean.setCodSegmento(Integer.valueOf(codSegSelecionado.substring(0,codSegSelecionado.indexOf("-")).trim()));
        catBean.setGraduacaoMinCategoria(listaCat.get(i).getGraduacaoMinCategoria());
        catBean.setGraduacaoMaxCategoria(listaCat.get(i).getGraduacaoMaxCategoria());
        catBean.setPesoMinCategoria(listaCat.get(i).getPesoMinCategoria());
        catBean.setPesoMaxCategoria(listaCat.get(i).getPesoMaxCategoria());
        catBean.setIdadeMinCategoria(listaCat.get(i).getIdadeMinCategoria());
        catBean.setIdadeMaxCategoria(listaCat.get(i).getIdadeMaxCategoria());
        catBean.setSexo(listaCat.get(i).getSexo());
        catBean.setNomeCategoria(listaCat.get(i).getNomeCategoria());
            
            dao.inserirCategoria(catBean);
        }
        
        
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso","Categoria "+ nomeCat +" cadastrada"));
        itensDeConfSeg();
    } catch (SQLException ex) {
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "\n"+ ex));
    }
    
}

public void inserirTpPontuacao(){
    
    try{
    for(int i = 0; i < listaPontos.size(); i++){
    
       metPontoBean.setCodSegmento(Integer.valueOf(codSegSelecionado.substring(0,codSegSelecionado.indexOf("-")).trim()));
       metPontoBean.setNomePontua(listaPontos.get(i).getNomePontua());
       metPontoBean.setValorPontua(listaPontos.get(i).getValorPontua());
       metPontoBean.setTipoPontua(listaPontos.get(i).getTipoPontua());
       metPontoBean.setParteDoCorpo(listaPontos.get(i).getParteDoCorpo());
       metPontoBean.setDescricaoPontua(listaPontos.get(i).getDescricaoPontua());
       
       dao.inserirModPonto(metPontoBean);
       
    }   
    
     FacesContext context = FacesContext.getCurrentInstance();
     comboPontosParaARB.clear();
     carregaComboMetPT();
     context.addMessage(null, new FacesMessage("Sucesso","Método de pontuação "+ nomePontua +" cadastrada"));
     itensDeConfSeg();
    } catch (SQLException ex) {
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "\n"+ ex));
    }
}

public void inserirArbitro(){
    
    try {
        // Inicia-se a inserão de um novo arbitro e suas respectivas pontuações, caso nenhum arbitr previamente cadastrado, seja selecionado.
        if(!nomeArbitro.equals("") && arbitroSelecionado.equals("")){
        
        arbtBean.setCodSegmento(codSegmento);
        arbtBean.setNomeArbitro(nomeArbitro);
        arbtBean.setGraduacaoArbitro(graduArbSelecionada.substring(0,graduArbSelecionada.indexOf("-")).trim());
        
       arbitro = dao.inserirARB(arbtBean);
        
        
       
       for(int i = 0; i < ptPorARB.size(); i++){
           
           arbtBean.setCodArbitro(arbitro.get(0).getCodArbitro());
           //arbtBean.setCodPontua(Integer.valueOf(pontuaSelecionado.substring(0,pontuaSelecionado.indexOf("-")).trim()));
           arbtBean.setCodPontua(ptPorARB.get(i).getCodPontua());
           
           dao.inserirPTporARB(arbtBean);
       }
       // Final do cadastro do arbitro e das pontuações
       
       // Caso existe um arbitro préviamente cadastrado selecionado, as pontuações serão inseridas e associadas a ele.
       }else{
            
            for(int i = 0; i < ptPorARB.size(); i++){
           
           arbtBean.setCodArbitro(Integer.valueOf(arbitroSelecionado.substring(0,arbitroSelecionado.indexOf("-")).trim()));
           //arbtBean.setCodPontua(Integer.valueOf(pontuaSelecionado.substring(0,pontuaSelecionado.indexOf("-")).trim()));
           arbtBean.setCodPontua(ptPorARB.get(i).getCodPontua());
           dao.inserirPTporARB(arbtBean);
            }
        }
       // Final da inserção das pontuações para o arbitro já existente
        
       FacesContext context = FacesContext.getCurrentInstance();
       context.addMessage(null, new FacesMessage("Sucesso","Arbitro "+ nomeArbitro +" cadastrado"));
       itensDeConfSeg();
    } catch (SQLException ex) {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "\n"+ ex));
    }
}




 /////////////////////////////////////////////////// Metodos Alteração /////////////////////////////////////////////////////

public void alteraEvento()throws SQLException{
    
    SimpleDateFormat formatador = null;
    formatador = new SimpleDateFormat("dd/MM/yyyy");     
    
    // É feita a atualização de todos os segmentos que foram alterados e adicionados a lista segsAlt
    
    if(!segsAlterados.isEmpty()){
       for(int i = 0; i < segsAlterados.size(); i++){
        
           segBean.setCodSegmento(segsAlterados.get(i).getCodSegmento());
           segBean.setNomeSegmento(segsAlterados.get(i).getNomeSegmento());
           segBean.setDescricaoSegmento(segsAlterados.get(i).getDescricaoSegmento());
        dao.alteraSegsEve(segBean);
    }
    }
    // Final da atualização de segmento
    
    
    // É feita a alteração da data do evento
    bean.setCodEvento(codEvento);
    bean.setNomeEvento(altNomeEvento);
    if(altDataEventoCons!=null){
    bean.setDataEvento(String.valueOf(formatador.format(altDataEventoCons)));
    }else{
    bean.setDataEvento(dataEventoCons);    
    }
     
 dao.alteraEve(bean);
 // Finaliza a alteração da data do evento   
 
 // É realizada a inserção de novos segmentos caso a lista de novos segs não esteja vazia
   
      if(!segsAdicionados.isEmpty())  {
        for(int i = 0; i< segsAdicionados.size(); i++){
            
            segBean.setCodEvento(codEvento);
            segBean.setNomeSegmento(segsAdicionados.get(i).getNomeSegmento());
            segBean.setDescricaoSegmento(segsAdicionados.get(i).getDescricaoSegmento());
            dao.inserirSegs(segBean);
            
        }
    }    
     
 //E a inserção de novos registros é finalizada.    
  
 //  É realizada a remoção dos itens da lista segsRemov.    
    if(!segsRemove.isEmpty()){
         for(int i = 0; i < segsRemove.size(); i++){
             segBean.setCodSegmento(segsRemove.get(i));
             dao.removeSeg(segBean);
         }
    }
    // É finalizada a remoção dos registros da lista.
    
    FacesContext context = FacesContext.getCurrentInstance();
    context.addMessage(null, new FacesMessage("Sucesso","Modalidade "+ altNomeEvento +" alterada"));
 comboEve.clear();
 ComboSegs.clear();
 segsAdicionados.clear();
 segsAlterados.clear();
 segsRemove.clear();
carregarComboEv();
carregaComboSegEcategs();
    
}

public void finalizaEvento(){
    
    try {
        dao.finalizaEvento(codEvento);
        FacesContext context = FacesContext.getCurrentInstance();
    context.addMessage(null, new FacesMessage("Sucesso","Evento "+ nomeEvento +" Finalizado"));
    
    carregarComboEv();
    
    } catch (SQLException ex) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "\n"+ ex));
    }
    
}

public void cancelaEvento(){
    
    try {
        dao.cancelaEvento(codEvento);
        FacesContext context = FacesContext.getCurrentInstance();
    context.addMessage(null, new FacesMessage("Sucesso","Evento "+ nomeEvento +" Cancelado"));
    
    carregarComboEv();
    
    } catch (SQLException ex) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "\n"+ ex));
    }
    
}
/////////////////////////////////////////////////// Metodos De exclusão /////////////////////////////////////////////////////

public void removerConfiguracoesSeg(){
    
    // inicia a remoção da lista de categorias
    try {
    if(!visListaCatRemove.isEmpty()){
        for(int i = 0; i< visListaCatRemove.size();i++){
            
                dao.removeCat(visListaCatRemove.get(i));            
        }
    }
    
    // Finaliza a remoção da lista de categorias
    
    // Inicia a remoção das metodologias de pontuação. caso uma metodologia esteja sendo usada por um arbitro, ela também será removida
    
    if(!visListaPontosRemove.isEmpty()){
        
        if(!visPtPorARBRemove.isEmpty()){
            System.out.println("veio pelo primeiro");
            for(int i = 0; i < visPtPorARBRemove.size();i++){
         dao.removePTporARB(visPtPorARBRemove.get(i).getCodArbitro(), visPtPorARBRemove.get(i).getCodPontua());
           
            }
            
            for(int i = 0; i < visListaPontosRemove.size();i++){
               dao.removeMTPontua(visListaPontosRemove.get(i));
               
            }
           
        }else{
             System.out.println("veio pelo Segundo");
            for(int i = 0; i < visListaPontosRemove.size();i++){
         
         dao.removeMTPontua(visListaPontosRemove.get(i));
            }
           
        }
        
    }
    // Finaliza a remoção das metodologias
    
    // Inicia a remoção dos arbitros 
    
    if(!visARBRemove.isEmpty()){
        
        if(!visPtPorARBRemove.isEmpty()){
            for(int i = 0; i < visPtPorARBRemove.size();i++){
            dao.removePTporARB(visPtPorARBRemove.get(i).getCodArbitro(), visPtPorARBRemove.get(i).getCodPontua());
            }
        for(int i = 0; i < visARBRemove.size(); i++){
            dao.removeARB(visARBRemove.get(i));
        }
        }else{
            for(int i = 0; i < visARBRemove.size(); i++){
            dao.removeARB(visARBRemove.get(i));
        } 
        }
    }
    
    // Finaliza a remoção dos arbitros
    
    // Inicia a remoção dos pontos por arbitro
    
    if(!visPtPorARBRemove.isEmpty()){
            for(int i = 0; i < visPtPorARBRemove.size();i++){
            dao.removePTporARB(visPtPorARBRemove.get(i).getCodArbitro(), visPtPorARBRemove.get(i).getCodPontua());
            }
    }
    // Finaliza a remoção dos pontos por arbitro
    
    FacesContext context = FacesContext.getCurrentInstance();
    context.addMessage(null, new FacesMessage("Sucesso","Configurações de segmentos removidas"));
    
    visListaCatRemove.clear();
    visARBRemove.clear();
    visPtPorARBRemove.clear();
    visListaPontosRemove.clear();
    
    } catch (SQLException ex) {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "\n"+ ex));
            }
}



//////////////////////////////////////////////////// métodos combos //////////////////////////////////////////////////////
public void carregarComboEv() throws SQLException{
    comboEve.clear();
    ComboSegs.clear();
    dataEventoCons = "";
    if(codModSelecionado.equals("")){
        
        comboEve.clear();
        ComboSegs.clear();
        comboGraduacoesATL.clear();
        listaCatSegs.clear();
      dataEventoCons = "";
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

public void carregaComboSegEcategs() throws SQLException{
   
   listaCatSegs.clear();
   SimpleDateFormat formatador = null;
   formatador = new SimpleDateFormat("dd/MM/yyyy");
    if(codEveSelecionado.equals("")){
        
        ComboSegs.clear();
        comboGraduacoesATL.clear();
        segsAdicionados.clear();
        segsAlterados.clear();
        segsRemove.clear();
        listaCatSegs.clear();
        altListaSegs.clear();
        dataEventoCons = "";
        altNomeEvento = "";
        altNomeSegmento = "";
        altDescricaoSegmento = "";
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
 dataEventoCons =  eve.get(0).getDataEvento().replaceAll("-", "/");
 
        
    }
    
      listaSegs.clear();
}

public void itensDeConfSeg() throws SQLException{
    
    //listaCatSegs.clear();
    comboGraduacoesARB.clear();
    comboPontosParaARB.clear();
    comboArbitros.clear();
    
    if(codSegSelecionado.equals("")){
        
    listaCat.clear();
    comboGraduacoesARB.clear();
    comboPontosParaARB.clear();
    comboArbitros.clear();
    listaCatSegs.clear();
    
    }else{
    codSegmento = Integer.valueOf(codSegSelecionado.substring(0,codSegSelecionado.indexOf("-")).trim());    
    carregaComboMetPT();
    comboGraduacoesATL.clear();
    
    Modalidades_dao dao2 = new Modalidades_dao();
     
     graduacoesModaliARB = dao2.PesqGraduARB(codModali);
     graduacoesModaliATL = dao2.PesqGraduATL(codModali);   
     arbitro = dao.pesquisaArbitro(codSegmento);
     
     for(int i=0; i< graduacoesModaliATL.size();i++){
         comboGraduacoesATL.add(graduacoesModaliATL.get(i).getCodGraduMod()+" - "+ graduacoesModaliATL.get(i).getIdentificacaoGradu());
     }
      
     for(int i=0; i< graduacoesModaliARB.size();i++){
         comboGraduacoesARB.add(graduacoesModaliARB.get(i).getCodGraduMod()+" - "+ graduacoesModaliARB.get(i).getIdentificacaoGradu());
     }
     
     for(int i=0; i< arbitro.size();i++){
         comboArbitros.add(arbitro.get(i).getCodArbitro()+" - "+arbitro.get(i).getNomeArbitro());
     }
     
     //realiza a consulta das categorias do segmento selecionado
   listaCatSegs.clear();
   listaCatSegs = dao.consultaCategoriasSegmento(codSegmento);
   //carregaComboMetPT();
    }
}

public void carregaComboMetPT() {
    
    try {
        listaPontosParaARB = dao.pesquisaMTPontua(Integer.valueOf(codSegSelecionado.substring(0,codSegSelecionado.indexOf("-")).trim()));
        for(int i = 0; i < listaPontosParaARB.size(); i++){
            comboPontosParaARB.add(listaPontosParaARB.get(i).getCodPontua()+" - "+ listaPontosParaARB.get(i).getNomePontua());
        }
    } catch (SQLException ex) {
        Logger.getLogger(EventosManagedBean.class.getName()).log(Level.SEVERE, null, ex);
    }
}

//////////////////////////////////////////////////// métodos de consultas //////////////////////////////////////////////////////

public void consultaConfsSegs()throws SQLException{
    
    visListaCatRemove.clear();
    visARBRemove.clear();
    visPtPorARBRemove.clear();
    visListaPontosRemove.clear();
    visListaCat.clear();
    visListaPontos.clear();
    visARB.clear();
    visPtPorARB.clear();
    
    visListaCat = dao.consultaCategoriasSegmento(codSegmento) ;
    visListaPontos = dao.pesquisaMTPontua(codSegmento);
    visARB = dao.pesquisaArbitro(codSegmento);
    visPtPorARB = dao.pesquisaPTporARB(codSegmento);

}

//////////////////////////////////////////////////// métodos adicionais //////////////////////////////////////////////////////

public void itensDeATL() throws SQLException{
    
    
      altNomeEvento = codEveSelecionado.substring(codEveSelecionado.indexOf("-")).replaceAll("-", "").trim();
     altListaSegs = dao.consultaSegs(codEvento);
 
}

public void linhaSelecionadaAltSeg(){
    if(segSelecionadoTb != null){
        
        altNomeSegmento = segSelecionadoTb.getNomeSegmento();
        altDescricaoSegmento = segSelecionadoTb.getDescricaoSegmento();
        
    }
}

public void adicionarSegLista(){
    Segmentos_bean segBean2 = new Segmentos_bean();
    segBean2.setNomeSegmento(nomeSegmento);
    segBean2.setDescricaoSegmento(descricaoSegmento);
    
    listaSegs.add(segBean2);
    
}


public void addAltSegsEve(){
    Segmentos_bean segBean2 = new Segmentos_bean();
    
    segBean2.setNomeSegmento(altNomeSegmento);
    segBean2.setDescricaoSegmento(altDescricaoSegmento);
    altListaSegs.add(segBean2);
    segsAdicionados.add(segBean2);
//     if(alteracao == false){
//         altListaSegs.clear();
//         
//         segBean2.setNomeSegmento(altNomeSegmento);
//         segBean2.setDescricaoSegmento(altDescricaoSegmento);
//         altListaSegs.add(segBean2); 
//         alteracao = true;
//     }else{
//         
//         segBean2.setNomeSegmento(altNomeSegmento);
//         segBean2.setDescricaoSegmento(altDescricaoSegmento);
//         altListaSegs.add(segBean2);
//         
//     }
    
}

public void segsParaAlt(){
    Segmentos_bean segBean2 = new Segmentos_bean();
    if(segSelecionadoTb != null){       
        
        segBean2.setCodSegmento(segSelecionadoTb.getCodSegmento());
        segBean2.setNomeSegmento(altNomeSegmento);
        segBean2.setDescricaoSegmento(altDescricaoSegmento);
        
         for(int i = 0; i < altListaSegs.size();i++){

                      if(segSelecionadoTb.getCodSegmento() == altListaSegs.get(i).getCodSegmento()){  
                
                          altListaSegs.remove(i);
                    
                }
                }
        
         altListaSegs.add(segBean2);
         segsAlterados.add(segBean2);
    }
    
}

public void removerSegLista(){
    
    String index = "";
        for(Segmentos_bean p: listaSegs){
            
            if(p.isSelected()){
               
                for(int i = 0; i < listaSegs.size();i++){

                    if(p.getNomeSegmento().equals(listaSegs.get(i).getNomeSegmento())){
                
                int aux = i;        
                index = String.valueOf(aux);
                    
                }
                }
            }
            }
       
        int x = Integer.valueOf(index);
        listaSegs.remove(x);
    
}


public void removerAltSegLista(){
    
    String index = "";
    
         if(segSelecionadoTb != null){       
                for(int i = 0; i < altListaSegs.size();i++){

                      if(segSelecionadoTb.getCodSegmento() == altListaSegs.get(i).getCodSegmento()){  
                int aux = i;        
                index = String.valueOf(aux);
                    
                }
                }
         }
     
       
        int x = Integer.valueOf(index);
        segsRemove.add(altListaSegs.get(x).getCodSegmento());
        altListaSegs.remove(x);
        altNomeSegmento = ""; 
        altDescricaoSegmento = "";
    }




public void restauraListaAltSegs()throws SQLException{
    segsRemove.clear();
    altListaSegs.clear();
    segsAlterados.clear();
    segsAdicionados.clear();
    itensDeATL();
}

public void adicionaCatLista(){
    
    Categorias_bean catBean2 = new Categorias_bean();
    
    catBean2.setGraduacaoMinCategoria(Integer.valueOf(graduDeSelecionada.substring(0,graduDeSelecionada.indexOf("-")).trim()));
    catBean2.setGraduacaoMaxCategoria(Integer.valueOf(graduAteSelecionada.substring(0,graduAteSelecionada.indexOf("-")).trim()));
    catBean2.setPesoMinCategoria(pesoDe);
    catBean2.setPesoMaxCategoria(pesoAte);
    catBean2.setIdadeMinCategoria(idadeDe);
    catBean2.setIdadeMaxCategoria(idadeate);
    catBean2.setSexo(sexo);
    catBean2.setNomeCategoria(nomeCat);
    
    listaCat.add(catBean2);
}

public void removerCatLista(){
    
    String index = "";
        for(Categorias_bean p: listaCat){
            
            if(p.isSelected()){
               
                for(int i = 0; i < listaCat.size();i++){

                    if(p.getNomeCategoria().equals(listaCat.get(i).getNomeCategoria()) && p.getSexo().equals(listaCat.get(i).getSexo())){
                
                int aux = i;        
                index = String.valueOf(aux);
                    
                }
                }
            }
            }
       
        int x = Integer.valueOf(index);
        listaCat.remove(x);
    
}

public void adicionarPontuacaoLista(){
    
    MetodosPontua_bean metPontoBean2 = new MetodosPontua_bean();
    
    metPontoBean2.setNomePontua(nomePontua);
    metPontoBean2.setValorPontua(valorPontua);
    metPontoBean2.setTipoPontua(tipoPontua);
    metPontoBean2.setParteDoCorpo(ptCorpoPontua);
    metPontoBean2.setDescricaoPontua(descricaoPontua);
    
    listaPontos.add(metPontoBean2);
    
}

public void removerPontuacaoLista(){
    
    String index = "";
        for(MetodosPontua_bean p: listaPontos){
            
            if(p.isSelected()){
               
                for(int i = 0; i < listaPontos.size();i++){

                    if(p.getNomePontua().equals(listaPontos.get(i).getNomePontua()) && p.getDescricaoPontua().equals(listaPontos.get(i).getDescricaoPontua())){
                
                int aux = i;        
                index = String.valueOf(aux);
                    
                }
                }
            }
            }
       
        int x = Integer.valueOf(index);
        listaPontos.remove(x);
    
}

public void adicionarPontuacaoListaARB(){
    
    Arbitros_bean arbtBean2 = new Arbitros_bean();
  
    // Caso um arbitro já cadastrado seja seleiconado no combo, ele entra nessa condição
     if(arbitroSelecionado != ""){
         
         String codGradu = ""; // Codigo de graduação do arbitro selecionado
         
         /* Este for percorre a lista de arbitros que carregaram o combo de arbitros cadastrados, e compara seus o códigos com o arbitro selecionado.
          Assim é possível pega o código da graducação do arbitro selecionado. */
         for(int i  = 0; i < arbitro.size();i++){
            if(arbitro.get(i).getCodArbitro() == Integer.valueOf(arbitroSelecionado.substring(0, arbitroSelecionado.indexOf("-")).trim())){
                codGradu = arbitro.get(i).getGraduacaoArbitro();
            }
         }
         
         arbtBean2.setNomeArbitro(arbitroSelecionado.substring(arbitroSelecionado.indexOf("-")).replaceAll("-", "").trim());   
         arbtBean2.setGraduacaoArbitro(codGradu);
         arbtBean2.setCodPontua(Integer.valueOf(pontuaSelecionado.substring(0,pontuaSelecionado.indexOf("-")).trim()));
         arbtBean2.setNomePontua(pontuaSelecionado.substring(pontuaSelecionado.indexOf("-")).replaceAll("-", "").trim());
         
     }else{
     
    arbtBean2.setNomeArbitro(nomeArbitro);
    arbtBean2.setGraduacaoArbitro(graduArbSelecionada.substring(0, graduArbSelecionada.indexOf("-")).trim());
    arbtBean2.setCodPontua(Integer.valueOf(pontuaSelecionado.substring(0,pontuaSelecionado.indexOf("-")).trim()));
    arbtBean2.setNomePontua(pontuaSelecionado.substring(pontuaSelecionado.indexOf("-")).replaceAll("-", "").trim());
    
     }
     
    ptPorARB.add(arbtBean2);
    
}

public void removerPontuacaoListaARB(){
    
   String index = "";
        for(Arbitros_bean p: ptPorARB){
            
            if(p.isSelected()){
               
                for(int i = 0; i < ptPorARB.size();i++){

                    if(p.getCodPontua() == ptPorARB.get(i).getCodPontua()){
                
                int aux = i;        
                index = String.valueOf(aux);
                    
                }
                }
            }
            }
       
        int x = Integer.valueOf(index);
        ptPorARB.remove(x); 
    
}

public void removerCatVis(){
    
    String index = "";
    
         if(catSelecionadaVis != null){       
                for(int i = 0; i < visListaCat.size();i++){

                      if(catSelecionadaVis.getCodCategoria()== visListaCat.get(i).getCodCategoria()){  
                int aux = i;        
                index = String.valueOf(aux);
                    
                }
                }
         }
     
       
        int x = Integer.valueOf(index);
        visListaCatRemove.add(visListaCat.get(x).getCodCategoria());
        visListaCat.remove(x);
    }

public void removerMTPontuaVis(){
    
    String index = "";
    String index2 = "";
    Arbitros_bean arbtBean2 = new Arbitros_bean(); 
         if(metSelecionadoVis != null){       
            int i =0;
          
            do{
                if(metSelecionadoVis.getCodPontua()== visPtPorARB.get(i).getCodPontua()){
                   arbtBean2.setCodPontua(visPtPorARB.get(i).getCodPontua());
                   arbtBean2.setCodArbitro(visPtPorARB.get(i).getCodArbitro());
                   visPtPorARBRemove.add(arbtBean2);
                   visPtPorARB.remove(i);
                 i = 0;
                }else{
                    i++;
                }
            }          
          while(i < visPtPorARB.size());   
             
             
             
             for( i = 0; i < visListaPontos.size();i++){

                      if(metSelecionadoVis.getCodPontua()== visListaPontos.get(i).getCodPontua()){  
               
               visListaPontosRemove.add(visListaPontos.get(i).getCodPontua());
               visListaPontos.remove(i);
                }
                }
                
//                for(int i = 0; i < visPtPorARB.size();i++){
//
//                      if(metSelecionadoVis.getCodPontua()== visPtPorARB.get(i).getCodPontua()){  
//                int aux = i;        
//                index2 = String.valueOf(aux);
//                    
//                }
//                }
         }
     
       
        
        
        
        
        
    }

public void removerARBVis(){
    
    Arbitros_bean arbtBean2 = new Arbitros_bean();    
    
         if(ARBSelecionadoVis != null){       
          int i =0;
          
            do{
                if(ARBSelecionadoVis.getCodArbitro()== visPtPorARB.get(i).getCodArbitro()){
                   arbtBean2.setCodPontua(visPtPorARB.get(i).getCodPontua());
                   arbtBean2.setCodArbitro(visPtPorARB.get(i).getCodArbitro());
                   visPtPorARBRemove.add(arbtBean2);       
                 visPtPorARB.remove(i);
                 i = 0;
                }else{
                    i++;
                }
            }          
          while(i < visPtPorARB.size());

             for( i = 0; i < visARB.size();i++){

                      if(ARBSelecionadoVis.getCodArbitro()== visARB.get(i).getCodArbitro()){  

                     visARBRemove.add(visARB.get(i).getCodArbitro());
                     visARB.remove(i);     
                          
                }
                }
         
         }

     

    }

public void removerPTporARBVis(){
    
    String index = "";
    Arbitros_bean arbtBean2 = new Arbitros_bean();    
        
        
         if(ptPorARBSelecionadoVis != null){       
                for(int i = 0; i < visPtPorARB.size();i++){

                      if(ptPorARBSelecionadoVis.getCodPontua()== visPtPorARB.get(i).getCodPontua() 
                         && ptPorARBSelecionadoVis.getCodArbitro()== visPtPorARB.get(i).getCodArbitro()){  
                int aux = i;        
                index = String.valueOf(aux);
                    
                }
                }
         }
     
       
        int x = Integer.valueOf(index);
        arbtBean.setCodPontua(visPtPorARB.get(x).getCodPontua());
        arbtBean.setCodArbitro(visPtPorARB.get(x).getCodArbitro());
        visPtPorARBRemove.add(arbtBean2);
        visPtPorARB.remove(x);
    }

public void limparCamposAltEve(){
    
    altDescricaoSegmento = "";
    altNomeSegmento = "";
    //altListaSegs.clear();
    
}

public void limparCamposInseEve(){
    nomeEvento = "";
    nomeSegmento = "";
    descricaoSegmento = "";
    listaSegs.clear();
}

public void linkEventos()throws IOException{
    FacesContext.getCurrentInstance().getExternalContext().redirect("./EventosTorneio.xhtml");
}
        

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getStatusEvento() {
        return statusEvento;
    }

    public void setStatusEvento(String statusEvento) {
        this.statusEvento = statusEvento;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
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

    public List<Eventoss_bean> getEve() {
        return eve;
    }

    public void setEve(List<Eventoss_bean> eve) {
        this.eve = eve;
    }

    public Eventoss_bean getBean() {
        return bean;
    }

    public void setBean(Eventoss_bean bean) {
        this.bean = bean;
    }

    public Eventos_dao getDao() {
        return dao;
    }

    public void setDao(Eventos_dao dao) {
        this.dao = dao;
    }




    
    

    public String getCodEveSelecionado() {
        return codEveSelecionado;
    }

    public void setCodEveSelecionado(String codEveSelecionado) {
        this.codEveSelecionado = codEveSelecionado;
    }


    public String getEventoDaLista() {
        return eventoDaLista;
    }

    public void setEventoDaLista(String eventoDaLista) {
        this.eventoDaLista = eventoDaLista;
    }

    public List<String> getComboSegs() {
        return ComboSegs;
    }

    public void setComboSegs(List<String> ComboSegs) {
        this.ComboSegs = ComboSegs;
    }

    public List<Modalidades_bean> getModalidades() {
        return modalidades;
    }

    public void setModalidades(List<Modalidades_bean> modalidades) {
        this.modalidades = modalidades;
    }

    public String getCodModSelecionado() {
        return codModSelecionado;
    }

    public void setCodModSelecionado(String codModSelecionado) {
        this.codModSelecionado = codModSelecionado;
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

    public List<Segmentos_bean> getListaSegs() {
        return listaSegs;
    }

    public void setListaSegs(List<Segmentos_bean> listaSegs) {
        this.listaSegs = listaSegs;
    }

    public Segmentos_bean getSegBean() {
        return segBean;
    }

    public void setSegBean(Segmentos_bean segBean) {
        this.segBean = segBean;
    }

    public String getDataEventoCons() {
        return dataEventoCons;
    }

    public void setDataEventoCons(String dataEventoCons) {
        this.dataEventoCons = dataEventoCons;
    }

    public String getAltNomeEvento() {
        return altNomeEvento;
    }

    public void setAltNomeEvento(String altNomeEvento) {
        this.altNomeEvento = altNomeEvento;
    }

    public int getAltCodSegmento() {
        return altCodSegmento;
    }

    public void setAltCodSegmento(int altCodSegmento) {
        this.altCodSegmento = altCodSegmento;
    }

    public String getAltNomeSegmento() {
        return altNomeSegmento;
    }

    public void setAltNomeSegmento(String altNomeSegmento) {
        this.altNomeSegmento = altNomeSegmento;
    }

    public String getAltDescricaoSegmento() {
        return altDescricaoSegmento;
    }

    public void setAltDescricaoSegmento(String altDescricaoSegmento) {
        this.altDescricaoSegmento = altDescricaoSegmento;
    }

    public Date getAltDataEventoCons() {
        return altDataEventoCons;
    }

    public void setAltDataEventoCons(Date altDataEventoCons) {
        this.altDataEventoCons = altDataEventoCons;
    }

    public List<Segmentos_bean> getAltListaSegs() {
        return altListaSegs;
    }

    public void setAltListaSegs(List<Segmentos_bean> altListaSegs) {
        this.altListaSegs = altListaSegs;
    }


    public String getCodSegSelecionado() {
        return codSegSelecionado;
    }

    public void setCodSegSelecionado(String codSegSelecionado) {
        this.codSegSelecionado = codSegSelecionado;
    }

    public boolean isAlteracao() {
        return alteracao;
    }

    public void setAlteracao(boolean alteracao) {
        this.alteracao = alteracao;
    }

    public String getGraduDeSelecionada() {
        return graduDeSelecionada;
    }

    public void setGraduDeSelecionada(String graduDeSelecionada) {
        this.graduDeSelecionada = graduDeSelecionada;
    }

    public String getGraduAteSelecionada() {
        return graduAteSelecionada;
    }

    public void setGraduAteSelecionada(String graduAteSelecionada) {
        this.graduAteSelecionada = graduAteSelecionada;
    }

    public int getIdadeDe() {
        return idadeDe;
    }

    public void setIdadeDe(int idadeDe) {
        this.idadeDe = idadeDe;
    }

    public int getIdadeate() {
        return idadeate;
    }

    public void setIdadeate(int idadeate) {
        this.idadeate = idadeate;
    }

    public float getPesoDe() {
        return pesoDe;
    }

    public void setPesoDe(float pesoDe) {
        this.pesoDe = pesoDe;
    }

    public float getPesoAte() {
        return pesoAte;
    }

    public void setPesoAte(float pesoAte) {
        this.pesoAte = pesoAte;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public List<GraduacoesModali_bean> getGraduacoesModaliARB() {
        return graduacoesModaliARB;
    }

    public void setGraduacoesModaliARB(List<GraduacoesModali_bean> graduacoesModaliARB) {
        this.graduacoesModaliARB = graduacoesModaliARB;
    }

    public List<GraduacoesModali_bean> getGraduacoesModaliATL() {
        return graduacoesModaliATL;
    }

    public void setGraduacoesModaliATL(List<GraduacoesModali_bean> graduacoesModaliATL) {
        this.graduacoesModaliATL = graduacoesModaliATL;
    }

    public List<String> getComboGraduacoesATL() {
        return comboGraduacoesATL;
    }

    public void setComboGraduacoesATL(List<String> comboGraduacoesATL) {
        this.comboGraduacoesATL = comboGraduacoesATL;
    }

    public List<Categorias_bean> getListaCat() {
        return listaCat;
    }

    public void setListaCat(List<Categorias_bean> listaCat) {
        this.listaCat = listaCat;
    }

    public String getNomeCat() {
        return nomeCat;
    }

    public void setNomeCat(String nomeCat) {
        this.nomeCat = nomeCat;
    }

    public List<Categorias_bean> getListaCatSegs() {
        return listaCatSegs;
    }

    public void setListaCatSegs(List<Categorias_bean> listaCatSegs) {
        this.listaCatSegs = listaCatSegs;
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

    public String getPtCorpoPontua() {
        return ptCorpoPontua;
    }

    public void setPtCorpoPontua(String ptCorpoPontua) {
        this.ptCorpoPontua = ptCorpoPontua;
    }

    public String getDescricaoPontua() {
        return descricaoPontua;
    }

    public void setDescricaoPontua(String descricaoPontua) {
        this.descricaoPontua = descricaoPontua;
    }

    public List<MetodosPontua_bean> getListaPontos() {
        return listaPontos;
    }

    public void setListaPontos(List<MetodosPontua_bean> listaPontos) {
        this.listaPontos = listaPontos;
    }

    public MetodosPontua_bean getMetPontoBean() {
        return metPontoBean;
    }

    public void setMetPontoBean(MetodosPontua_bean metPontoBean) {
        this.metPontoBean = metPontoBean;
    }

    public Categorias_bean getCatBean() {
        return catBean;
    }

    public void setCatBean(Categorias_bean catBean) {
        this.catBean = catBean;
    }

    public List<Integer> getSegsRemove() {
        return segsRemove;
    }

    public void setSegsRemove(List<Integer> segsRemove) {
        this.segsRemove = segsRemove;
    }

    public int getCodArbitro() {
        return codArbitro;
    }

    public void setCodArbitro(int codArbitro) {
        this.codArbitro = codArbitro;
    }

    public String getNomeArbitro() {
        return nomeArbitro;
    }

    public void setNomeArbitro(String nomeArbitro) {
        this.nomeArbitro = nomeArbitro;
    }

    public String getGraduArbSelecionada() {
        return graduArbSelecionada;
    }

    public void setGraduArbSelecionada(String graduArbSelecionada) {
        this.graduArbSelecionada = graduArbSelecionada;
    }

    public String getGraduacaoArbitro() {
        return graduacaoArbitro;
    }

    public void setGraduacaoArbitro(String graduacaoArbitro) {
        this.graduacaoArbitro = graduacaoArbitro;
    }

    public String getPontuaSelecionado() {
        return pontuaSelecionado;
    }

    public void setPontuaSelecionado(String pontuaSelecionado) {
        this.pontuaSelecionado = pontuaSelecionado;
    }

    public int getCodPontua() {
        return codPontua;
    }

    public void setCodPontua(int codPontua) {
        this.codPontua = codPontua;
    }

    public List<Arbitros_bean> getPtPorARB() {
        return ptPorARB;
    }

    public void setPtPorARB(List<Arbitros_bean> ptPorARB) {
        this.ptPorARB = ptPorARB;
    }

    public Arbitros_bean getArbtBean() {
        return arbtBean;
    }

    public void setArbtBean(Arbitros_bean arbtBean) {
        this.arbtBean = arbtBean;
    }

    public void setComboGraduacoesARB(List<String> comboGraduacoesARB) {
        this.comboGraduacoesARB = comboGraduacoesARB;
    }

    public List<String> getComboGraduacoesARB() {
        return comboGraduacoesARB;
    }

    public List<Arbitros_bean> getArbitro() {
        return arbitro;
    }

    public void setArbitro(List<Arbitros_bean> arbitro) {
        this.arbitro = arbitro;
    }

    public List<MetodosPontua_bean> getListaPontosParaARB() {
        return listaPontosParaARB;
    }

    public void setListaPontosParaARB(List<MetodosPontua_bean> listaPontosParaARB) {
        this.listaPontosParaARB = listaPontosParaARB;
    }

    public List<String> getComboPontosParaARB() {
        return comboPontosParaARB;
    }

    public void setComboPontosParaARB(List<String> comboPontosParaARB) {
        this.comboPontosParaARB = comboPontosParaARB;
    }

    public Segmentos_bean getSegSelecionadoTb() {
        return segSelecionadoTb;
    }

    public void setSegSelecionadoTb(Segmentos_bean segSelecionadoTb) {
        this.segSelecionadoTb = segSelecionadoTb;
    }

    public List<Segmentos_bean> getSegsAlterados() {
        return segsAlterados;
    }

    public void setSegsAlterados(List<Segmentos_bean> segsAlterados) {
        this.segsAlterados = segsAlterados;
    }

    public List<Segmentos_bean> getSegsAdicionados() {
        return segsAdicionados;
    }

    public void setSegsAdicionados(List<Segmentos_bean> segsAdicionados) {
        this.segsAdicionados = segsAdicionados;
    }

    public List<MetodosPontua_bean> getVisListaPontos() {
        return visListaPontos;
    }

    public void setVisListaPontos(List<MetodosPontua_bean> visListaPontos) {
        this.visListaPontos = visListaPontos;
    }

    public List<Arbitros_bean> getVisPtPorARB() {
        return visPtPorARB;
    }

    public void setVisPtPorARB(List<Arbitros_bean> visPtPorARB) {
        this.visPtPorARB = visPtPorARB;
    }

    public List<Arbitros_bean> getVisARB() {
        return visARB;
    }

    public void setVisARB(List<Arbitros_bean> visARB) {
        this.visARB = visARB;
    }

    public List<Categorias_bean> getVisListaCat() {
        return visListaCat;
    }

    public void setVisListaCat(List<Categorias_bean> visListaCat) {
        this.visListaCat = visListaCat;
    }

    public List<Integer> getVisListaCatRemove() {
        return visListaCatRemove;
    }

    public void setVisListaCatRemove(List<Integer> visListaCatRemove) {
        this.visListaCatRemove = visListaCatRemove;
    }

    public List<Integer> getVisARBRemove() {
        return visARBRemove;
    }

    public void setVisARBRemove(List<Integer> visARBRemove) {
        this.visARBRemove = visARBRemove;
    }

    public List<Arbitros_bean> getVisPtPorARBRemove() {
        return visPtPorARBRemove;
    }

    public void setVisPtPorARBRemove(List<Arbitros_bean> visPtPorARBRemove) {
        this.visPtPorARBRemove = visPtPorARBRemove;
    }

    public List<Integer> getVisListaPontosRemove() {
        return visListaPontosRemove;
    }

    public void setVisListaPontosRemove(List<Integer> visListaPontosRemove) {
        this.visListaPontosRemove = visListaPontosRemove;
    }

    public MetodosPontua_bean getMetSelecionadoVis() {
        return metSelecionadoVis;
    }

    public void setMetSelecionadoVis(MetodosPontua_bean metSelecionadoVis) {
        this.metSelecionadoVis = metSelecionadoVis;
    }

    public Arbitros_bean getPtPorARBSelecionadoVis() {
        return ptPorARBSelecionadoVis;
    }

    public void setPtPorARBSelecionadoVis(Arbitros_bean ptPorARBSelecionadoVis) {
        this.ptPorARBSelecionadoVis = ptPorARBSelecionadoVis;
    }

    public Arbitros_bean getARBSelecionadoVis() {
        return ARBSelecionadoVis;
    }

    public void setARBSelecionadoVis(Arbitros_bean ARBSelecionadoVis) {
        this.ARBSelecionadoVis = ARBSelecionadoVis;
    }

    public Categorias_bean getCatSelecionadaVis() {
        return catSelecionadaVis;
    }

    public void setCatSelecionadaVis(Categorias_bean catSelecionadaVis) {
        this.catSelecionadaVis = catSelecionadaVis;
    }

    public String getArbitroSelecionado() {
        return arbitroSelecionado;
    }

    public void setArbitroSelecionado(String arbitroSelecionado) {
        this.arbitroSelecionado = arbitroSelecionado;
    }

    public List<String> getComboArbitros() {
        return comboArbitros;
    }

    public void setComboArbitros(List<String> comboArbitros) {
        this.comboArbitros = comboArbitros;
    }

   
    
    
    
}
