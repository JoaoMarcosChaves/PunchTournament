
package Conexao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class TesteConexao  {
    public static void main(String[] args)throws SQLException {
        
    
    Connection conexao = ConectaBanco.getConexao();
        conexao.close();
        //JOptionPane.showMessageDialog(null,"desconectado do banco de dados");    
}
}