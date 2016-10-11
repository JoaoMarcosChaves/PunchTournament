
package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author joãomarcos
 */
public class ConectaBanco {
 
public static Connection getConexao()throws SQLException{
    try{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    //  JOptionPane.showMessageDialog(null,"conectando ao banco");
        return DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=PUNCH", "sa", "123456789");
    }catch (ClassNotFoundException e){
        throw  new SQLException(e.getMessage());
    }
    
}    
}
    

