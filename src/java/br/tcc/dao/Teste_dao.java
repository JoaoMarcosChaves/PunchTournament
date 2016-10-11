/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tcc.dao;

import Conexao.ConectaBanco;
import br.tcc.bean.Arbitros_bean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joãomarcos
 */
public class Teste_dao {
private Connection conexao; 
    public Teste_dao()throws SQLException{
    this.conexao = new ConectaBanco().getConexao();
}
    
    public List<Arbitros_bean> consultaEstruConfArb(int codArb)throws SQLException{
    
    String sql = "select a.codChvConf, a.codConf, a.codInsc1,nomeAtleta1 = x.nomeAtleta,a.codInsc2,nomeAtleta2 = e.nomeAtleta, a.statusConf"
            + " from tb_estruCBT a inner join tb_arbCBT b\n" +
" on a.codConf = b.codConf\n" +
" inner join TB_inscricaoAtleta i\n" +
" on i.codInscricao = a.codInsc1 \n" +
" inner join TB_inscricaoAtleta f\n" +
" on f.codInscricao = a.codInsc2 \n" +
" inner join TB_atleta x\n" +
" on x.codAtleta = i.codAtleta\n" +
" inner join TB_atleta e\n" +
" on f.codAtleta = e.codAtleta\n" +
" and b.codArbitro = ?\n" +
" and a.statusConf = 'Iniciado'";
     System.out.println("sql = "+ sql);
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codArb);
    ResultSet rs = stmt.executeQuery(); 
               
    ArrayList<Arbitros_bean> lista = new ArrayList<>();
    while(rs.next()){
        Arbitros_bean c1 = new Arbitros_bean();
        
        c1.setCodChvConf(rs.getInt("codChvConf"));
        c1.setCodConf(rs.getInt("codConf"));
        c1.setCodInsc1(rs.getInt("codInsc1"));
        c1.setCodInsc2(rs.getInt("codInsc2"));
        c1.setNomeAtl1(rs.getString("nomeAtleta1"));
        c1.setNomeAtl2(rs.getString("nomeAtleta2"));
        c1.setStatusConf(rs.getString("statusConf"));
        
        lista.add(c1);
    }
    rs.close();
    stmt.close();
    
    return lista;
}
    
    public void confirmaAtl1(int codInsc, int codConf)throws SQLException{
    String sql = "update TB_iniciarCBT set statusInicio1 = 'Ok' where codConf = ? and codInsc1 =?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
           stmt.setInt(1, codConf);
           stmt.setInt(2, codInsc);
           stmt.execute();
           stmt.close();
}

public void confirmaAtl2(int codInsc, int codConf)throws SQLException{
    String sql = "update TB_iniciarCBT set statusInicio2 = 'Ok' where codConf = ? and codInsc2 =?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
           stmt.setInt(1, codConf);
           stmt.setInt(2, codInsc);
           stmt.execute();
           stmt.close();
}

public void confirmaInicio(int codConf)throws SQLException{
    String sql = "update TB_iniciarCBT set confrmsInicio += 1 where codConf = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
           stmt.setInt(1, codConf);
           
           stmt.execute();
           stmt.close();
}

public int verificaConfrmsInicioCbt(int codConf)throws SQLException{
    int confirms = 0;
    String sql = "select confrmsInicio from TB_iniciarCBT where codConf = ?";
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codConf);
    ResultSet rs = stmt.executeQuery(); 
               
    
    while(rs.next()){
    
      confirms =  rs.getInt("confrmsInicio");
        
    }
    rs.close();
    stmt.close();
    return confirms;
}

public int verificaQtdArbtCBT(int codConf)throws SQLException{
    int confirms = 0;
    String sql = "select COUNT(codArbitro)codArbitro from TB_arbCBT where codConf = ?";
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codConf);
    ResultSet rs = stmt.executeQuery(); 
               
    
    while(rs.next()){
    
      confirms =  rs.getInt("codArbitro");
        
    }
    rs.close();
    stmt.close();
    return confirms;
}

public boolean verificaOkInicCBT(int codConf)throws SQLException{
    boolean confirm = false;
    String sql = "select statusInicio1,statusInicio2 from TB_iniciarCBT where codConf = ?";
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codConf);
    ResultSet rs = stmt.executeQuery(); 
               
    
    while(rs.next()){
        
        if(rs.getString("statusInicio1").equals("Ok") && rs.getString("statusInicio2").equals("Ok")){
            confirm = true;
        }
        
    }
    rs.close();
    stmt.close();
    return confirm;
}


public List<Arbitros_bean> consultaPontsArbCBT(int codConf,int codArb)throws SQLException{
    
    String sql = "select p.codPontua,m.nomePontua, a.codArbitro,m.tipoPontua,m.valorPontua, m.descricaoPontua,m.parteDoCorpo from TB_arbCBT a inner join TB_pontArbCbt p\n" +
"on a.codConfArb = p.codConfArb\n" +
"inner join TB_metodoPontua m\n" +
"on m.codPontua = p.codPontua\n" +
"and a.codArbitro = ?\n" +
"and a.codConf = ?";
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codArb);
    stmt.setInt(2, codConf);
    ResultSet rs = stmt.executeQuery(); 
               
    List<Arbitros_bean> lista = new ArrayList<>();
    while(rs.next()){
        Arbitros_bean c1 = new Arbitros_bean();
        
        
    c1.setCodPontua(rs.getInt("codPontua"));
    c1.setNomePontua(rs.getString("nomePontua"));
//    c1.setCodArbitro(rs.getInt("codArbitro"));
    c1.setTipoPontua(rs.getString("tipoPontua"));
    c1.setValorPontua(rs.getInt("valorPontua"));
    c1.setDescricaoPontua(rs.getString("descricaoPontua"));
    c1.setParteDoCorpo(rs.getString("parteDoCorpo"));
    
    lista.add(c1);
    }
    rs.close();
    stmt.close();
    return lista;
}

public void alteraEstruCBT(int codConf)throws SQLException{
    String sql = "update TB_estruCBT set statusConf = 'Finalizado' where codConf = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
           stmt.setInt(1, codConf);
           
           stmt.execute();
           stmt.close();
}

public void confirmaOkFinaliza(int codConf)throws SQLException{
    String sql = "update TB_estruCBT set confrmsFnlz += 1 where codConf = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
           stmt.setInt(1, codConf);
           
           stmt.execute();
           stmt.close();
}

public int verificaOkFnlzCBT(int codConf)throws SQLException{
    int confirm = 0;
    String sql = "select confrmsFnlz from TB_estruCBT where codConf = ?";
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codConf);
    ResultSet rs = stmt.executeQuery(); 
               
    
    while(rs.next()){
        
        confirm = rs.getInt("confrmsFnlz");
            
    }
    rs.close();
    stmt.close();
    return confirm;
}


public void inserirMetricasCbtRestantesVenc(int codChv, int codInsc1, int codInsc2)throws SQLException{
    String sql = "update TB_metricasCBT set parteDoCorpoNGT = (select parteDoCorpoPST from TB_metricasCBT where codChvConf = ? and codInscricao = ?) \n" +
                  "where codChvConf = ? and codInscricao = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
           stmt.setInt(1, codChv);
           stmt.setInt(2, codInsc1);
           stmt.setInt(3, codChv);
           stmt.setInt(4, codInsc2);
           
           stmt.execute();
           stmt.close();
           
//      String sql2 = "update TB_metricasAT set totPontosNegativos  = (select totPontosPositivos from TB_metricasAT where codChvConf = ? and codInscricao = ?) \n" +
//"where codChvConf = ? and codInscricao = ?";
//    
//    PreparedStatement stmt2 = this.conexao.prepareStatement(sql2);
//           stmt.setInt(1, codChv);
//           stmt.setInt(2, codInsc1);
//           stmt.setInt(3, codChv);
//           stmt.setInt(4, codInsc2);     
//           
//           stmt2.execute();
//           stmt2.close();
//           
         
}



public void inserirMetricasAtRestantes(int codChv, int codInsc1, int codInsc2)throws SQLException{
    String sql = "update TB_metricasCBT set parteDoCorpoNGT = (select parteDoCorpoPST from TB_metricasCBT where codChvConf = ? and codInscricao = ?) \n" +
                  "where codChvConf = ? and codInscricao = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
           stmt.setInt(1, codChv);
           stmt.setInt(2, codInsc1);
           stmt.setInt(3, codChv);
           stmt.setInt(4, codInsc2);
           
           stmt.execute();
           stmt.close();
           
      String sql2 = "update TB_metricasAT set totPontosNegativos  = (select totPontosPositivos from TB_metricasAT where codChvConf = ? and codInscricao = ?) \n" +
"where codChvConf = ? and codInscricao = ?";
    
    PreparedStatement stmt2 = this.conexao.prepareStatement(sql2);
           stmt2.setInt(1, codChv);
           stmt2.setInt(2, codInsc1);
           stmt2.setInt(3, codChv);
           stmt2.setInt(4, codInsc2);     
           
           stmt2.execute();
           stmt2.close();
           
           String sql3 = "update TB_metricasCBT set parteDoCorpoNGT = (select parteDoCorpoPST from TB_metricasCBT where codChvConf = ? and codInscricao = ?) \n" +
                  "where codChvConf = ? and codInscricao = ?";
    
    PreparedStatement stmt3 = this.conexao.prepareStatement(sql3);
           stmt3.setInt(1, codChv);
           stmt3.setInt(2, codInsc2);
           stmt3.setInt(3, codChv);
           stmt3.setInt(4, codInsc1);
           
           stmt3.execute();
           stmt3.close();
           
      String sql4 = "update TB_metricasAT set totPontosNegativos  = (select totPontosPositivos from TB_metricasAT where codChvConf = ? and codInscricao = ?) \n" +
"where codChvConf = ? and codInscricao = ?";
    
    PreparedStatement stmt4 = this.conexao.prepareStatement(sql4);
           stmt4.setInt(1, codChv);
           stmt4.setInt(2, codInsc2);
           stmt4.setInt(3, codChv);
           stmt4.setInt(4, codInsc1);     
           
           stmt4.execute();
           stmt4.close();
}
    
}
