
package br.tcc.controller;

import br.tcc.bean.GraduacoesModali_bean;
import br.tcc.bean.Modalidades_bean;
import br.tcc.dao.Modalidades_dao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 *
 * @author joãomarcos
 */
@ManagedBean(name="ModalidadeManagedBean", eager = true)
@SessionScoped
//@ViewScoped
//@RequestScoped
public class ModalidadeManagedBean {
    
    public ModalidadeManagedBean()throws SQLException{
        Modalidades_dao dao = new Modalidades_dao();
        
        modalidades = dao.PesqMod();
        
    }
    boolean alteracao = false;
    boolean alteraABR = false;
    
    private int codModali;   
    private int altCodModali;
    private int codGraduMod;
    private String identificacaoGraduARB;
    private String altIdentificacaoGraduARB;
    private String identificacaoGraduATL;
    private String altIdentificacaoGraduATL;
    private String tipoGradu;
    private String descricaoDaModalidade;
    private String nomeDaModalidade;
    private String AltDescricaoDaModalidade;
    private String AltNomeDaModalidade;
    private List<Modalidades_bean> modalidades = new ArrayList<>();
    private List<GraduacoesModali_bean> graduacoesModaliARB = new ArrayList<>();
    private List<GraduacoesModali_bean> graduacoesModaliATL = new ArrayList<>() ;
    private Modalidades_bean modSelecionada;
    
    Modalidades_bean modBean = new Modalidades_bean();
    GraduacoesModali_bean graduMod = new GraduacoesModali_bean();
    
    private List<GraduacoesModali_bean> AltGraduacoesModaliARB = new ArrayList<>();
    private List<GraduacoesModali_bean> AltGraduacoesModaliATL = new ArrayList<>() ;
    List<Integer> graduRemovATL = new ArrayList<>();
    List<Integer> graduRemovARB = new ArrayList<>();
///////////////////////////////////////////// Metodos inserção /////////////////////////////////////////////////////
    
    
    public void inserirModali(){
        try {
            
            Modalidades_dao dao = new Modalidades_dao();
            
            modBean.setNomeModali(nomeDaModalidade);
            
            modBean.setDescricao(descricaoDaModalidade);
            
            modalidades = dao.inserirMod(modBean);
            //  dao.inserirMod(modBean);
            codModali = modalidades.get(0).getCodModali();
            
            inserirGraduATL();
            inserirGraduARB();
            
           FacesContext context = FacesContext.getCurrentInstance();
           context.addMessage(null, new FacesMessage("Sucesso","Modalidade "+ nomeDaModalidade +" cadastrada"));
            modalidades = dao.PesqMod();
            limparDadosIns();
    } catch (SQLException ex) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "\n"+ ex)); 
        }
        
    }
    
   
    public void inserirGraduATL(){
        try {
            Modalidades_dao dao = new Modalidades_dao();
            for(int i = 0; i< graduacoesModaliATL.size(); i++){
                
                graduMod.setCodModali(codModali);
                graduMod.setIdentificacaoGradu(graduacoesModaliATL.get(i).getIdentificacaoGradu());
                dao.insereGraduATL(graduMod);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModalidadeManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void inserirGraduARB(){
        try {
            Modalidades_dao dao = new Modalidades_dao();
            for(int i = 0; i< graduacoesModaliARB.size(); i++){
                
                graduMod.setCodModali(codModali);
                graduMod.setIdentificacaoGradu(graduacoesModaliARB.get(i).getIdentificacaoGradu());
                dao.insereGraduARB(graduMod);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModalidadeManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     ////////////////////////////////////////////// Metodos Alteração /////////////////////////////////////////////////////
    
    public void alteraMod(){
        
        try {
            Modalidades_dao dao = new Modalidades_dao();
            
            modBean.setNomeModali(AltNomeDaModalidade);
            
            modBean.setDescricao(AltDescricaoDaModalidade);
            
            modBean.setCodModali(altCodModali);
            
            dao.alterarMod(modBean);
           
            if(alteracao == true){
                
                for(int i = 0; i< AltGraduacoesModaliATL.size(); i++){
                
                graduMod.setCodModali(altCodModali);
                graduMod.setIdentificacaoGradu(AltGraduacoesModaliATL.get(i).getIdentificacaoGradu());                   
                dao.insereGraduATL(graduMod);
                
               
            }
                 alteracao = false;
            }else{
                
                for(int i = 0; i < graduRemovATL.size(); i++){
                    graduMod.setCodGraduMod(graduRemovATL.get(i));
                    dao.RemoveGradu(graduMod);
                }
                
            }

            if(alteraABR == true){
                
                for(int i = 0; i< AltGraduacoesModaliARB.size(); i++){
                
                graduMod.setCodModali(altCodModali);
                graduMod.setIdentificacaoGradu(AltGraduacoesModaliARB.get(i).getIdentificacaoGradu());                   
                dao.insereGraduARB(graduMod);
                
            }
                 alteraABR = false;
            }else{
                
                for(int i = 0; i < graduRemovARB.size(); i++){
                    graduMod.setCodGraduMod(graduRemovARB.get(i));
                    dao.RemoveGradu(graduMod);
                }
                
            } 
            
            FacesContext context = FacesContext.getCurrentInstance();
           context.addMessage(null, new FacesMessage("Sucesso","Modalidade "+ AltNomeDaModalidade +" alterada"));
            modalidades = dao.PesqMod();
        } catch (SQLException ex) {
            Logger.getLogger(ModalidadeManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    ////////////////////////////////////////////// Metodos Consulta /////////////////////////////////////////////////////
    
    
    public void consultaGradu() throws SQLException{
     Modalidades_dao dao = new Modalidades_dao();
        
     AltGraduacoesModaliARB = dao.PesqGraduARB(altCodModali);
     AltGraduacoesModaliATL = dao.PesqGraduATL(altCodModali);  
    }
    
    ////////////////////////////////////////////// Metodos adicionais /////////////////////////////////////////////////////
    public void adicionarGraduARB(){
        GraduacoesModali_bean graduMod2 = new GraduacoesModali_bean();
        graduMod2.setIdentificacaoGradu(identificacaoGraduARB);
        graduacoesModaliARB.add(graduMod2);
        
    }
    
    public void adicionarGraduATL(){
        GraduacoesModali_bean graduMod2 = new GraduacoesModali_bean();
        graduMod2.setIdentificacaoGradu(identificacaoGraduATL);
        graduacoesModaliATL.add(graduMod2);
        
    }
    
    public void alteraGraduATL(){
        GraduacoesModali_bean graduMod2 = new GraduacoesModali_bean();
        if(alteracao == false){
       
            AltGraduacoesModaliATL.clear();
            
            graduMod2.setIdentificacaoGradu(altIdentificacaoGraduATL);
            AltGraduacoesModaliATL.add(graduMod2);
           alteracao = true;
        }else{
            
            graduMod2.setIdentificacaoGradu(altIdentificacaoGraduATL);
            AltGraduacoesModaliATL.add(graduMod2);
            
        }
        
    }
    
    public void alteraGraduABR(){
        GraduacoesModali_bean graduMod2 = new GraduacoesModali_bean();
        if(alteraABR == false){
       
            AltGraduacoesModaliARB.clear();
            
            graduMod2.setIdentificacaoGradu(altIdentificacaoGraduARB);
            AltGraduacoesModaliARB.add(graduMod2);
           alteraABR = true;
        }else{
            
            graduMod2.setIdentificacaoGradu(altIdentificacaoGraduARB);
            AltGraduacoesModaliARB.add(graduMod2);
            
        }
        
    }
    
    public void linhaSelecionada()throws SQLException{
   
        if(modSelecionada != null){
        AltNomeDaModalidade = modSelecionada.getNomeModali();
        AltDescricaoDaModalidade = modSelecionada.getDescricao();
        altCodModali = modSelecionada.getCodModali();
        
         consultaGradu();
        }
//        for(Modalidades_bean p: modalidades){
//            
//            if(p.isSelected()){
//                                   
//          altCodModali = p.getCodModali();
//              
//          AltNomeDaModalidade = p.getNomeModali();
//          AltDescricaoDaModalidade = p.getDescricao();
//         
//            }
//            consultaGradu();
//        }
    }
    
    public void linhaSelecionadaInsATL()throws SQLException{
   String index = "";
        for(GraduacoesModali_bean p: graduacoesModaliATL){
            
            if(p.isSelected()){
               
                for(int i = 0; i < graduacoesModaliATL.size();i++){

                    if(p.getIdentificacaoGradu().equals(graduacoesModaliATL.get(i).getIdentificacaoGradu())){
                
                int aux = i;        
                index = String.valueOf(aux);
                    
                }
                }
            }
        }
       
        int x = Integer.valueOf(index);
        graduacoesModaliATL.remove(x);
        
    }
    
    public void linhaSelecionadaInsARB()throws SQLException{
  String index = "";
        for(GraduacoesModali_bean p: graduacoesModaliARB){
            
            if(p.isSelected()){
               
                for(int i = 0; i < graduacoesModaliARB.size();i++){

                    if(p.getIdentificacaoGradu().equals(graduacoesModaliARB.get(i).getIdentificacaoGradu())){
                        
                int aux = i;        
                index = String.valueOf(aux);
                    
                }
                }
            }
        }
        int x = Integer.valueOf(index);
        graduacoesModaliARB.remove(x);
    }
    
    public void linhaSelecionadaAltATL()throws SQLException{
  String index = "";
        for(GraduacoesModali_bean p: AltGraduacoesModaliATL){
            
            if(p.isSelected()){
               
                for(int i = 0; i < AltGraduacoesModaliATL.size();i++){

                    if(p.getCodGraduMod() == AltGraduacoesModaliATL.get(i).getCodGraduMod()){
                        
                int aux = i;        
                index = String.valueOf(aux);
                        System.out.println("O valor "+p.getCodGraduMod()+" é igual a "+AltGraduacoesModaliATL.get(i).getCodGraduMod()+" no indice"+
                                i);
                }
                }
            }
        }
         int x = Integer.valueOf(index);
         graduRemovATL.add(AltGraduacoesModaliATL.get(x).getCodGraduMod());
        AltGraduacoesModaliATL.remove(x);
       
     //  
    }
    
    public void linhaSelecionadaAltARB()throws SQLException{
    String index = "";
        for(GraduacoesModali_bean p: AltGraduacoesModaliARB){
            
            if(p.isSelected()){
          
                
                for(int i = 0; i < AltGraduacoesModaliARB.size();i++){
                
                    if(p.getCodGraduMod() == AltGraduacoesModaliARB.get(i).getCodGraduMod()){
                
               int aux = i;        
                index = String.valueOf(aux); 
                    
                }
                }
            }
        }
         int x = Integer.valueOf(index);
         graduRemovARB.add(AltGraduacoesModaliARB.get(x).getCodGraduMod());
        AltGraduacoesModaliARB.remove(x);
        
    }
    
    
    
    public void limparTbATL(){
        graduacoesModaliATL.clear();
    }
    
    public void limparTbARB(){
        graduacoesModaliARB.clear();
    }
    
    public void limparDadosIns(){
        nomeDaModalidade ="";
        descricaoDaModalidade = "";
        graduacoesModaliATL.clear();
        graduacoesModaliARB.clear();
        identificacaoGraduARB = "";
        identificacaoGraduATL ="";
    }
    
    
    public void linkModalidade() throws IOException{
    
  
    FacesContext.getCurrentInstance().getExternalContext().redirect("./Modalidade.xhtml");
    
}
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int getAltCodModali() {
        return altCodModali;
    }

    public void setAltCodModali(int altCodModali) {
        this.altCodModali = altCodModali;
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

    public String getIdentificacaoGraduARB() {
        return identificacaoGraduARB;
    }

    public void setIdentificacaoGraduARB(String identificacaoGraduARB) {
        this.identificacaoGraduARB = identificacaoGraduARB;
    }

    public String getIdentificacaoGraduATL() {
        return identificacaoGraduATL;
    }

    public void setIdentificacaoGraduATL(String identificacaoGraduATL) {
        this.identificacaoGraduATL = identificacaoGraduATL;
    }


    public String getTipoGradu() {
        return tipoGradu;
    }

    public void setTipoGradu(String tipoGradu) {
        this.tipoGradu = tipoGradu;
    }
    
    public List<Modalidades_bean> getModalidades() {
        return modalidades;
    }

    public void setModalidades(List<Modalidades_bean> modalidades) {
        this.modalidades = modalidades;
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

    
    public Modalidades_bean getModBean() {
        return modBean;
    }

    public void setModBean(Modalidades_bean modBean) {
        this.modBean = modBean;
    }

    public GraduacoesModali_bean getGraduMod() {
        return graduMod;
    }

    public void setGraduMod(GraduacoesModali_bean graduMod) {
        this.graduMod = graduMod;
    }

    public List<GraduacoesModali_bean> getAltGraduacoesModaliARB() {
        return AltGraduacoesModaliARB;
    }

    public void setAltGraduacoesModaliARB(List<GraduacoesModali_bean> AltGraduacoesModaliARB) {
        this.AltGraduacoesModaliARB = AltGraduacoesModaliARB;
    }

    public List<GraduacoesModali_bean> getAltGraduacoesModaliATL() {
        return AltGraduacoesModaliATL;
    }

    public void setAltGraduacoesModaliATL(List<GraduacoesModali_bean> AltGraduacoesModaliATL) {
        this.AltGraduacoesModaliATL = AltGraduacoesModaliATL;
    }
    

    public String getNomeDaModalidade() {
        return nomeDaModalidade;
    }

    public void setNomeDaModalidade(String nomeDaModalidade) {
        this.nomeDaModalidade = nomeDaModalidade;
    }

    public String getDescricaoDaModalidade() {
        return descricaoDaModalidade;
    }

    public void setDescricaoDaModalidade(String descricaoDaModalidade) {
        this.descricaoDaModalidade = descricaoDaModalidade;
    }

    public String getAltDescricaoDaModalidade() {
        return AltDescricaoDaModalidade;
    }

    public void setAltDescricaoDaModalidade(String AltDescricaoDaModalidade) {
        this.AltDescricaoDaModalidade = AltDescricaoDaModalidade;
    }

    public String getAltNomeDaModalidade() {
        return AltNomeDaModalidade;
    }

    public void setAltNomeDaModalidade(String AltNomeDaModalidade) {
        this.AltNomeDaModalidade = AltNomeDaModalidade;
    }

    public boolean isAlteracao() {
        return alteracao;
    }

    public void setAlteracao(boolean alteracao) {
        this.alteracao = alteracao;
    }


    public String getAltIdentificacaoGraduARB() {
        return altIdentificacaoGraduARB;
    }

    public void setAltIdentificacaoGraduARB(String altIdentificacaoGraduARB) {
        this.altIdentificacaoGraduARB = altIdentificacaoGraduARB;
    }

    public String getAltIdentificacaoGraduATL() {
        return altIdentificacaoGraduATL;
    }

    public void setAltIdentificacaoGraduATL(String altIdentificacaoGraduATL) {
        this.altIdentificacaoGraduATL = altIdentificacaoGraduATL;
    }

    public Modalidades_bean getModSelecionada() {
        return modSelecionada;
    }

    public void setModSelecionada(Modalidades_bean modSelecionada) {
        this.modSelecionada = modSelecionada;
    }

    

   
    
}
