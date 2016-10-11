//
//package br.tcc.controller;
//import br.tcc.bean.Eventos_bean;
//import br.tcc.dao.Consultas_dao;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.annotation.PostConstruct;
//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
//import javax.faces.context.FacesContext;
//import org.primefaces.event.SelectEvent;
// 
//import org.primefaces.event.TransferEvent;
//import org.primefaces.event.UnselectEvent;
//import org.primefaces.model.DualListModel;
//
///**
// *
// * @author joãomarcos
// */
//@ManagedBean
//public class Teste {
//    
//     private DualListModel<String> particip;
//     private List <Eventos_bean> listaEventos = new ArrayList<>();
//     private boolean selecionados;
//     private List<Teste> lista = new ArrayList<>();
//     
//     
//        public void editar(){
//        
//      for(Teste p: lista){
//            
//            if(p.isSelecionados()){
//                
//                
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage("Sucesso","Linha "+ p +" Selecionada"));        
//                
//                
//                
//            }
//      
//      }
//        }
//        
//        
//     public Teste(){
//         List<String> ParticipOrigem = new ArrayList<String>();
//        List<String> ParticipDestino = new ArrayList<String>();
//     
//        
//        
//        try{
//        listaEventos = new Consultas_dao().Relatorio();
//      } catch (SQLException ex) {
//          Logger.getLogger(TestePkList.class.getName()).log(Level.SEVERE, null, ex);
//      }
//        
//        for(int i = 0; i < listaEventos.size(); i++){
//        
//        ParticipOrigem.add(listaEventos.get(i).getNomeTRN());
//            
//        }
//        
//         
//        particip = new DualListModel<String>(ParticipOrigem, ParticipDestino);
//     }
//    public DualListModel<String> getCities() {
//        return particip;
//    }
// 
//    public void setCities(DualListModel<String> cities) {
//        this.particip = cities;
//    }
//
//    public boolean isSelecionados() {
//        return selecionados;
//    }
//
//    public void setSelecionados(boolean selecionados) {
//        this.selecionados = selecionados;
//    }
//
//
//    
//}
