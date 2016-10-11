
package br.tcc.dao;

import Conexao.ConectaBanco;
import br.tcc.bean.Login_bean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Login_dao {

private Connection conexao;
    
    public Login_dao() throws SQLException{
        this.conexao = ConectaBanco.getConexao();    
   }   
    
    public void adicionaUsuario(Login_bean c1) throws SQLException{
    
      
        String sql = "insert into TB_PerfisUsuarios(tipoPRF,loginPRF,senhaPRF,emailPRF) values (?,?,?,?)";

        
        
        //seta os valores nos campos declarados acima
        PreparedStatement stmt = conexao.prepareStatement(sql);
        
        
        
        stmt.setLong(1, c1.getTipoPerfil());
        stmt.setString(2, c1.getLogin());
        stmt.setString(3, c1.getSenha());
        stmt.setString(4, c1.getEmail());
        //executa o códido sql de insert que declaramos 
        stmt.execute();
        stmt.close();

    }
    
    
    
    
//    public int login(String usuario, String senha) throws SQLException{  
//     
//     int existe = 0 ;
//     boolean existeAdm = false;  
//     PreparedStatement stmAdm = conexao.prepareStatement("select * from TB_PerfisUsuarios where loginPRF = ? and senhaPRF = ? and tipoPRF = 1");  
//     stmAdm.setString(1, usuario);  
//     stmAdm.setString(2, senha);  
//     ResultSet rsAdm = stmAdm.executeQuery();  
//   
//     boolean existeCom = false;  
//     PreparedStatement stmcom = conexao.prepareStatement("select * from TB_PerfisUsuarios where loginPRF = ? and senhaPRF = ? and tipoPRF = 2");  
//     stmcom.setString(1, usuario);  
//     stmcom.setString(2, senha);  
//     ResultSet rscom = stmcom.executeQuery();  
//     
//     if(rsAdm.next()){  
//         existeAdm = true;  
//         existe = 1; 
//      
//     } else if(rscom.next()){
//         existeCom = true;  
//         existe = 2; 
//         
//         
//     } 
//     return existe;
//        
//}  
   
    public int login(String usuario, String senha) throws SQLException{  
     
        int existe = 0 ;
     boolean existeAdm = false;  
     PreparedStatement stmAdm = conexao.prepareStatement("select * from TB_PerfisUsuarios where loginPRF = ? and senhaPRF = ? and tipoPRF = 1");  
     stmAdm.setString(1, usuario);  
     stmAdm.setString(2, senha);  
     ResultSet rsAdm = stmAdm.executeQuery();  
   
     boolean existeCom = false;  
     PreparedStatement stmcom = conexao.prepareStatement("select * from TB_PerfisUsuarios where loginPRF = ? and senhaPRF = ? and tipoPRF = 2");  
     stmcom.setString(1, usuario);  
     stmcom.setString(2, senha);  
     ResultSet rscom = stmcom.executeQuery();  
     
     if(rsAdm.next()){  
         existeAdm = true;  
         existe = 1; 
         
      PreparedStatement stmAtivaCNXAdm = conexao.prepareStatement("exec usp_abreCNX\n" +
"@login = ?,\n" +
"@senha = ?");  
      stmAtivaCNXAdm.setString(1, usuario);  
      stmAtivaCNXAdm.setString(2, senha); 
      
      
      stmAtivaCNXAdm.execute();
      stmAtivaCNXAdm.close();

      
     } else if(rscom.next()){
         existeCom = true;  
         existe = 2; 
         
         PreparedStatement stmAtivaCNXAdm = conexao.prepareStatement("exec usp_abreCNX\n" +
"@login = ?,\n" +
"@senha = ?");  
      stmAtivaCNXAdm.setString(1, usuario);  
      stmAtivaCNXAdm.setString(2, senha); 
      
      
      stmAtivaCNXAdm.execute();
      stmAtivaCNXAdm.close();
     } 
     return existe;
        
}  
    
    
    public void fechaConexao(String codigo) throws SQLException{
        
        String sql = "exec usp_fecharCNX\n" +
"\n" +
"@codCNX = ?\n";

        
        
        //seta os valores nos campos declarados acima
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1,codigo);
        
        stmt.execute();
        stmt.close();
        
        
    }
    
   
    public boolean comparaEmail(String user, String email) throws SQLException{
        
        boolean existe = false;  
     PreparedStatement stm = conexao.prepareStatement("select * from TB_PerfisUsuarios where loginPRF = ? and emailPRF = ?");  
     stm.setString(1, user);  
     stm.setString(2, email);  
     
     
     ResultSet rs = stm.executeQuery();  
   
        if(rs.next()){
            existe = true;
        }
        return existe;
    }  
    
    public List<Login_bean>getSenhaDoEmailValido(String user, String email) throws SQLException{
        
    String sql = "select senhaPRF from TB_PerfisUsuarios where loginPRF = ?  and emailPRF = ?";
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
        //stmt.setString(1,nome_produto);
        stmt.setString(1, user);
        stmt.setString(2, email);
        
        ResultSet rs = stmt.executeQuery(); 
        
        ArrayList<Login_bean> minhaLista = new ArrayList<Login_bean>();
        
        
        while(rs.next()){
            Login_bean c1 = new Login_bean();
            c1.setSenha(rs.getString("senhaPRF"));
            
            minhaLista.add(c1);
        }
        rs.close();
        stmt.close();
        
        return minhaLista;
    
    }   
        
    public List<Login_bean>informacoesAcesso(String user, String senha) throws SQLException{
        
        String sql= "select p.loginPRF, c.codCNX from TB_usersCNX c inner join TB_PerfisUsuarios p on  c.nomeUserCNX = ? and p.loginPRF = ? and p.senhaPRF = ?";
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        
        stmt.setString(1, user);
        stmt.setString(2, user);
        stmt.setString(3, senha);
        //stmt.setString(2, senha);
        
        ResultSet rs = stmt.executeQuery();
        
        ArrayList<Login_bean> minhaLista = new ArrayList<Login_bean>();
        
         while(rs.next()){
            Login_bean c1 = new Login_bean();
            
            c1.setLogin(rs.getString("loginPRF"));
            c1.setCodCNX(rs.getString("codCNX"));
            
            minhaLista.add(c1);
        }
        rs.close();
        stmt.close();
        
        return minhaLista;
   
    }
    
    public List<Login_bean>acessosAtivos() throws SQLException{
        
        String sql= "select * from TB_usersCNX";
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        
        
        ResultSet rs = stmt.executeQuery();
        
        ArrayList<Login_bean> minhaLista = new ArrayList<Login_bean>();
        
         while(rs.next()){
            Login_bean c1 = new Login_bean();
            
            c1.setCodCNX(rs.getString("codCNX"));
            c1.setCodPRF(rs.getString("codUserCNX"));
            c1.setLogin(rs.getString("nomeUserCNX"));
            c1.setDtLoginCNX(rs.getString("dtLoginCNX"));
            
            minhaLista.add(c1);
        }
        rs.close();
        stmt.close();
        
        return minhaLista;
   
    }
    
    public boolean verificaUsuarioConectado(String codCNX) throws SQLException{  
     
        //int existe = 0 ;
     boolean existeCNX = false;  
     PreparedStatement stmUser = conexao.prepareStatement("select * from TB_usersCNX where codCNX =?");  
     stmUser.setString(1, codCNX);  
       
     ResultSet rsAdm = stmUser.executeQuery();  
   
   
     
     if(rsAdm.next()){  
         existeCNX = true;  
         
       
     } 
     return existeCNX;
        
}  
    
}
