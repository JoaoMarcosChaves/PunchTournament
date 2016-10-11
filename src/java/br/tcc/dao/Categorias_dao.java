//
//package br.tcc.dao;
//
//import Conexao.ConectaBanco;
//import br.tcc.bean.Categorias_bean;
//import java.beans.Beans;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author joãomarcos
// */
//public class Categorias_dao {
//    private Connection conexao;
//
//public Categorias_dao()throws SQLException{
//    this.conexao = new ConectaBanco().getConexao();
//}
//// Inicio das instruções da categoria de peso
//public void inserirCatPeso(Categorias_bean c1)throws SQLException{
//    
//    String sql = "insert into CPT_catPESO(\n" +
//"dePESO\n" +
//",atePESO\n" +
//",dtCadCatPESO\n" +
//",usuarioCNX\n" +
//") values (\n" +
//"\n" +
//"?\n" +
//",?\n" +
//", GETDATE()\n" +
//",?\n" +
//")\n" ;
//    PreparedStatement stmt = conexao.prepareStatement(sql);
//    
//    stmt.setFloat(1, c1.getDePESO());
//    stmt.setFloat(2, c1.getAtePESO());
//    stmt.setString(3, c1.getUsuarioCNX());
//    
//    stmt.execute();
//    stmt.close();
//}
//
//public void alteraCatPeso(Categorias_bean c1)throws SQLException{
//    String sql = "update CPT_catPeso set dePESO = ?, atePESO = ? where codCatPeso = ?";
//    PreparedStatement stmt = conexao.prepareStatement(sql);
//    
//    stmt.setFloat(1, c1.getDePESO() );
//    stmt.setFloat(2, c1.getAtePESO() );
//    stmt.setInt(3, c1.getCodCatPESO());
//    
//    stmt.execute();
//    stmt.close();
//}
//
//
//public List<Categorias_bean> consultaCatPeso()throws SQLException{
//    String sql = "select * from CPT_catPESO";
//    PreparedStatement stmt = conexao.prepareStatement(sql);
//    
//        ResultSet rs = stmt.executeQuery();
//    List<Categorias_bean> minhaLista = new ArrayList<Categorias_bean>();
//   
//    while(rs.next()){
//    Categorias_bean c1 =  new Categorias_bean();
//     
//    c1.setCodCatPESO(rs.getInt("codCatPESO"));
//        
//    c1.setDePESO(rs.getFloat("dePESO"));
//        
//    c1.setAtePESO(rs.getFloat("atePESO"));
//    
//    minhaLista.add(c1);
//    }
//    rs.close();
//    stmt.close();
//    return minhaLista;
//}
//
//// Final das instruções da categoria de peso
//
//// Inicio das instruções da categoria de faixa
//
//public void inserirCatFaixa(Categorias_bean c1)throws SQLException{
//    
//    String sql = "insert into CPT_catFAIXA(\n" +
//"deFAIXA\n" +
//",ateFAIXA\n" +
//",dtCadCatFAIXA\n" +
//",usuarioCNX\n" +
//") values (\n" +
//"\n" +
//"?\n" +
//",?\n" +
//", GETDATE()\n" +
//",?\n" +
//")\n" ;
//    PreparedStatement stmt = conexao.prepareStatement(sql);
//    
//    stmt.setInt(1, c1.getDeFAIXA());
//    stmt.setInt(2, c1.getAteFAIXA());
//    stmt.setString(3, c1.getUsuarioCNX());
//    
//    stmt.execute();
//    stmt.close();
//}
//
//public void alteraCatFaixa(Categorias_bean c1)throws SQLException{
//    String sql = "update CPT_catFAIXA set deFAIXA = ?, ateFAIXA = ? where codCatFAIXA = ?";
//    PreparedStatement stmt = conexao.prepareStatement(sql);
//    
//    stmt.setInt(1, c1.getDeFAIXA());
//    stmt.setInt(2, c1.getAteFAIXA());
//    stmt.setInt(3, c1.getCodCatFAIXA());
//    
//    stmt.execute();
//    stmt.close();
//}
//
//
//public List<Categorias_bean> consultaCatFaixa()throws SQLException{
//    String sql = "select * from CPT_catFAIXA";
//    PreparedStatement stmt = conexao.prepareStatement(sql);
//    
//    ResultSet rs = stmt.executeQuery();
//    List<Categorias_bean> minhaLista = new ArrayList<Categorias_bean>();
//   
//    while(rs.next()){
//    Categorias_bean c1 =  new Categorias_bean();
//     
//    c1.setCodCatFAIXA(rs.getInt("codCatFAIXA"));
//        
//    c1.setDeFAIXA(rs.getInt("deFAIXA"));
//        
//    c1.setAteFAIXA(rs.getInt("ateFAIXA"));
//    
//    minhaLista.add(c1);
//    }
//    rs.close();
//    stmt.close();
//    return minhaLista;
//}
//
//
//// Final das instruções da categoria de faixa
//
//// Inicio das instruções da categoria de faixa etária
//
//public void inserirCatIdade(Categorias_bean c1)throws SQLException{
//    
//    String sql = "insert into CPT_cadFaixaEtaria (\n" +
//"nomeCatFE\n" +
//",deFE\n" +
//",ateFE\n" +
//",dtCadFE\n" +
//",usuarioCNX)\n" +
//"values(\n" +
//"?\n" +
//",?\n" +
//",?\n" +
//",GETDATE()\n" +
//",?)" ;
//    PreparedStatement stmt = conexao.prepareStatement(sql);
//    
//    stmt.setString(1, c1.getNomeFE());
//    stmt.setInt(2, c1.getDeFE());
//    stmt.setInt(3, c1.getAteFE());
//    stmt.setString(4, c1.getUsuarioCNX());
//    
//    stmt.execute();
//    stmt.close();
//}
//
//public void alteraCatIdade(Categorias_bean c1)throws SQLException{
//    String sql = "update CPT_cadFaixaEtaria set nomeCatFE = ?, deFE = ?, ateFE = ? where codCatFE = ?";
//    PreparedStatement stmt = conexao.prepareStatement(sql);
//    
//    stmt.setString(1, c1.getNomeFE());
//    stmt.setInt(2, c1.getDeFE());
//    stmt.setInt(3, c1.getAteFE());
//    stmt.setInt(4, c1.getCodCatFE());
//    
//    stmt.execute();
//    stmt.close();
//}
//
//
//public List<Categorias_bean> consultaCatIdade()throws SQLException{
//    String sql = "select * from CPT_cadFaixaEtaria";
//    PreparedStatement stmt = conexao.prepareStatement(sql);
//    
//    ResultSet rs = stmt.executeQuery();
//    List<Categorias_bean> minhaLista = new ArrayList<Categorias_bean>();
//   
//    while(rs.next()){
//    Categorias_bean c1 =  new Categorias_bean();
//     
//    c1.setNomeFE(rs.getString("nomeCatFE"));
//    
//    c1.setCodCatFE(rs.getInt("codCatFE"));
//        
//    c1.setDeFE(rs.getInt("deFE"));
//        
//    c1.setAteFE(rs.getInt("ateFE"));
//    
//    minhaLista.add(c1);
//    }
//    rs.close();
//    stmt.close();
//    return minhaLista;
//}
//
//
//// Final das instruções da categoria de faixa etária
//}
