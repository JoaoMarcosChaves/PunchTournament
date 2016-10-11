
package br.tcc.bean;

/**
 *
 * @author joãomarcos
 */
public class Login_bean {
    
   private long tipoPerfil;
   private String login;
   private String senha;
   private String email;
   private String codCNX;
   private String codPRF;
   private String dtLoginCNX;
   
    public long getTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(long tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }

   
   
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void getTipoPerfil(Long valueOf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getCodCNX() {
        return codCNX;
    }

    public void setCodCNX(String codCNX) {
        this.codCNX = codCNX;
    }

    public String getCodPRF() {
        return codPRF;
    }

    public void setCodPRF(String codPRF) {
        this.codPRF = codPRF;
    }

    public String getDtLoginCNX() {
        return dtLoginCNX;
    }

    public void setDtLoginCNX(String dtLoginCNX) {
        this.dtLoginCNX = dtLoginCNX;
    }

  
    
    
}
