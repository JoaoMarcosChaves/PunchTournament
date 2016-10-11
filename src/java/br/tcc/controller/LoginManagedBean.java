
package br.tcc.controller;


import br.tcc.bean.Arbitros_bean;
import br.tcc.bean.Login_bean;
import br.tcc.criptografia.Conteudo;
import br.tcc.criptografia.Criptografar;
import br.tcc.criptografia.InterfaceCrip;
import br.tcc.dao.Eventos_dao;
import br.tcc.dao.Login_dao;
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
@ManagedBean(name="LoginManagedBean", eager = true)
@SessionScoped
public class LoginManagedBean {
   
    private int tipo;
    private int codigoCNX;
    private String usuario;
    private String senha;
    
    private String cadUsusario;
    private int cadTipo;
    private String cadSenha;
    private String cadConfSenha;
    private String email;
    private String arbSelec;
    
    List<Login_bean> usuarios = new ArrayList<>();
    List<String> arbitros = new ArrayList<>();            
    
    //////////////////////////////////////////////////////////// Métodos de inserção ///////////////////////////////////////////////////////////
    
    public void verificaSenha() throws SQLException{
    
    if(cadSenha.equals(cadConfSenha) ){
        
        
        if(cadTipo == 2){
        boolean ehNumero = false;
        try {
	 
            if(Integer.valueOf(cadUsusario)/1 == Integer.valueOf(cadUsusario)){
            ehNumero = true;
              }
            
	} catch (NumberFormatException e) {	  
          
            ehNumero = false;
	}
       if (!ehNumero) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "O login de um usuário arbitro, deve ser seu código de cadastro. Verifique o código no botão Arbitros ao lado."));        
       }else{
           Cadastrar();
       }
        
    }else{
            Cadastrar();
        }
        
         
//    
//        //return true;
    }else{
        
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "Confirmação de senha é diferente da digitada anteriormente."));        
        
//       // return false;
    }
    
    
    
}
    
    public void Cadastrar() throws SQLException{
    
   
    Login_bean bean = new Login_bean();
    Conteudo cont = new Conteudo();
    InterfaceCrip crip = new Criptografar();
    
    cont.setInformacao(cadUsusario) ;
    bean.setLogin(cadUsusario);
    
    bean.setSenha(cadSenha);
    
    bean.setEmail(email);
    
    bean.setTipoPerfil(cadTipo);
    
    
    
    Login_dao dao = new Login_dao();
    dao.adicionaUsuario(bean);
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Usuário cadastrado com sucesso"));        
    
}
    
    
    /////////////////////////////////////////////////////////// métodos logar e deslogar ////////////////////////////////////////////
    
    
     public void linkInicial() throws IOException{
    
        try {
            Login_dao dao = new Login_dao();
            
            int retorno = dao.login(usuario, senha);
            
            if(retorno == 1){
                
                constroiCNX();
                FacesContext.getCurrentInstance().getExternalContext().redirect("./Inicio.xhtml");
                tipo = 1;
                
            }else if(retorno == 2) {
                
                constroiCNX();
                FacesContext.getCurrentInstance().getExternalContext().redirect("./Arbitro.xhtml");
                
            }else {
                // FacesContext.getCurrentInstance().getExternalContext().redirect("./index.xhtml");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "Usuário/senha incorretos, ou inexistente"));
            }   } catch (SQLException ex) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção","\n"+ ex));
        }
    
}
    
    public void constroiCNX() throws SQLException{
    
        Login_dao cnx = new Login_dao();
            List <Login_bean> infCNX;
            
            infCNX = cnx.informacoesAcesso(usuario, senha);
            
            
           
            for( int i=0; i <infCNX.size(); i++){
                
                
                usuario  = infCNX.get(i).getLogin();
                
                codigoCNX = Integer.valueOf(infCNX.get(i).getCodCNX());
                
            }
    }
    
    public void deslogar()throws IOException, SQLException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("./index.xhtml");
        
        Login_dao dao = new Login_dao();
        dao.fechaConexao(String.valueOf(codigoCNX));
        
        tipo = 0;
        usuario = "";
        senha = "";
    }
    
    
   
    
    ///////////////////////////////////////////////////////// métodos consultas ////////////////////////////////////////////
    
    
    public void consultaUsuariosConectados()throws SQLException{
    usuarios = new Login_dao().acessosAtivos();
    }
    
    ///////////////////////////////////////////////////////// métodos link ////////////////////////////////////////////
    
     public void usuarios()throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("./Usuarios.xhtml");
     
    }
    
    
    
    
    public int renderizaIndex() throws IOException{
        
        if(tipo == 0){
        
            return 1;
            
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "Usuário/senha incorretos, ou inexistente"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("./Inicio.xhtml");
            return 0;
        }
        
    }
    
    
     public void pesquisaArbitros()throws SQLException{
    
         arbitros = new Eventos_dao().pesquisaGeralArbitro();
    
         
    
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int getCodigoCNX() {
        return codigoCNX;
    }

    public void setCodigoCNX(int codigoCNX) {
        this.codigoCNX = codigoCNX;
    }

    public List<Login_bean> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Login_bean> usuarios) {
        this.usuarios = usuarios;
    }

    public List<String> getArbitros() {
        return arbitros;
    }

    public void setArbitros(List<String> arbitros) {
        this.arbitros = arbitros;
    }

     
     
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCadUsusario() {
        return cadUsusario;
    }

    public void setCadUsusario(String cadUsusario) {
        this.cadUsusario = cadUsusario;
    }

    public int getCadTipo() {
        return cadTipo;
    }

    public void setCadTipo(int cadTipo) {
        this.cadTipo = cadTipo;
    }

    public String getCadSenha() {
        return cadSenha;
    }

    public void setCadSenha(String cadSenha) {
        this.cadSenha = cadSenha;
    }

    public String getCadConfSenha() {
        return cadConfSenha;
    }

    public void setCadConfSenha(String cadConfSenha) {
        this.cadConfSenha = cadConfSenha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArbSelec() {
        return arbSelec;
    }

    public void setArbSelec(String arbSelec) {
        this.arbSelec = arbSelec;
    }
    
    
    
    
}
