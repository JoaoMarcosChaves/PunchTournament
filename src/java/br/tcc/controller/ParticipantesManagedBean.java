package br.tcc.controller;

import br.tcc.bean.Atletas_bean;
import br.tcc.bean.GraduacoesModali_bean;
import br.tcc.dao.Atletas_dao;
import br.tcc.dao.Modalidades_dao;
import br.tcc.interfaces.Criptografar;
import br.tcc.interfaces.InterfaceCrip;
import br.tcc.interfaces.InterfaceValida;
import br.tcc.interfaces.Validacoes;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author joãomarcos
 */

@ManagedBean(name="ParticipantesManagedBean", eager = true)
@SessionScoped
public class ParticipantesManagedBean {

private  String codModaliSelecionada;    
private int codAtleta;
private String nomeAtleta;
private int idadeAtleta;
private String sexo;
private float pesoAtleta;
private String graduacaoAtleta;
private String cpfAtleta;
private String emailAtleta;
 
private List<Atletas_bean> codATL = new ArrayList<>();
private List<GraduacoesModali_bean> graduacoesModaliATL = new ArrayList<>() ;
//private List<Modalidades_bean> modalidades = new ArrayList<>();
private List <String> comboGradu = new ArrayList<>();

Atletas_bean atlBean = new Atletas_bean();


public void carregaComboGradu()throws SQLException{
    comboGradu.clear();
    
    if(codModaliSelecionada.equals("")){
    comboGradu.clear();    
    }else{
    
    Modalidades_dao modaliDao = new Modalidades_dao();
    graduacoesModaliATL = modaliDao.PesqGraduATL(Integer.valueOf(codModaliSelecionada.substring(0, codModaliSelecionada.indexOf("-")).trim()));   
    for(int i=0; i< graduacoesModaliATL.size();i++){
         comboGradu.add(graduacoesModaliATL.get(i).getCodGraduMod()+" - "+ graduacoesModaliATL.get(i).getIdentificacaoGradu());
     }
    }
}

public void inserirParticipante(){
    InterfaceValida v = new Validacoes();
    InterfaceCrip c = new Criptografar();
    
    try {
        Atletas_dao dao = new Atletas_dao();

        if(v.ValidaCpf(cpfAtleta.replace(".", "").replace("-", "").replace(" ", ""))){
            if(v.ValidaEmail(emailAtleta)){
            
        atlBean.setNomeAtleta(nomeAtleta);
        atlBean.setCpfAtleta(c.TipoCrip(cpfAtleta.replace(".", "").replace("-", "").replace(" ", "")));
        atlBean.setEmailAtleta(emailAtleta);
        atlBean.setGraduacaoAtleta(graduacaoAtleta.substring(0, graduacaoAtleta.indexOf("-")).trim());
        atlBean.setIdadeAtleta(idadeAtleta);
        atlBean.setPesoAtleta(pesoAtleta);
        atlBean.setSexo(sexo);
    
        codATL = dao.inserirAtleta(atlBean);
        
        codAtleta = codATL.get(0).getCodAtleta();
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso","Atleta "+ nomeAtleta +" cadastrado"));
                
            }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "Endereço de E-mail com formato inválido"));
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "CPF inválido. Entre com um CPF válido."));
        }
        
        
    } catch (SQLException ex) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "\n"+ ex));
    }
    
}



public void linkInscricao() throws IOException{
    
  
    FacesContext.getCurrentInstance().getExternalContext().redirect("./InscricaoPRT.xhtml");
    
}

public void linkParticipantes() throws IOException{
    
  
    FacesContext.getCurrentInstance().getExternalContext().redirect("./Participantes.xhtml");
    
}


















////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int getCodAtleta() {
        return codAtleta;
    }

    public void setCodAtleta(int codAtleta) {
        this.codAtleta = codAtleta;
    }

    public String getNomeAtleta() {
        return nomeAtleta;
    }

    public void setNomeAtleta(String nomeAtleta) {
        this.nomeAtleta = nomeAtleta;
    }

    public int getIdadeAtleta() {
        return idadeAtleta;
    }

    public void setIdadeAtleta(int idadeAtleta) {
        this.idadeAtleta = idadeAtleta;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public float getPesoAtleta() {
        return pesoAtleta;
    }

    public void setPesoAtleta(float pesoAtleta) {
        this.pesoAtleta = pesoAtleta;
    }

    public String getGraduacaoAtleta() {
        return graduacaoAtleta;
    }

    public void setGraduacaoAtleta(String graduacaoAtleta) {
        this.graduacaoAtleta = graduacaoAtleta;
    }

    public String getCpfAtleta() {
        return cpfAtleta;
    }

    public void setCpfAtleta(String cpfAtleta) {
        this.cpfAtleta = cpfAtleta;
    }

    public String getEmailAtleta() {
        return emailAtleta;
    }

    public void setEmailAtleta(String emailAtleta) {
        this.emailAtleta = emailAtleta;
    }

    public List<Atletas_bean> getCodATL() {
        return codATL;
    }

    public void setCodATL(List<Atletas_bean> codATL) {
        this.codATL = codATL;
    }

      public List<GraduacoesModali_bean> getGraduacoesModaliATL() {
        return graduacoesModaliATL;
    }

    public void setGraduacoesModaliATL(List<GraduacoesModali_bean> graduacoesModaliATL) {
        this.graduacoesModaliATL = graduacoesModaliATL;
    }

    public Atletas_bean getAtlBean() {
        return atlBean;
    }

    public void setAtlBean(Atletas_bean atlBean) {
        this.atlBean = atlBean;
    }

    public String getCodModaliSelecionada() {
        return codModaliSelecionada;
    }

    public void setCodModaliSelecionada(String codModaliSelecionada) {
        this.codModaliSelecionada = codModaliSelecionada;
    }

    public List<String> getComboGradu() {
        return comboGradu;
    }

    public void setComboGradu(List<String> comboGradu) {
        this.comboGradu = comboGradu;
    }




}
