//
//package br.tcc.dao;
//
//import Conexao.ConectaBanco;
//import br.tcc.bean.Eventos_bean;
//import java.beans.Beans;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
///**
// *
// * @author joãomarcos
// */
//public class Cadastros_dao {
//private Connection conexao;
//
//public Cadastros_dao()throws SQLException{
//    this.conexao = new ConectaBanco().getConexao();
//}
//
//public void inserirEventos(Eventos_bean c1)throws SQLException{
//    
//    String sql = "insert into CPT_cadTRN (\n" +
//"nomeTRN \n" +
//",dtCadTRN \n" +
//",dtInicioTRN\n" +
//",statusTRN"+            
//",usuarioCNX\n" +
//") values (\n" +
//"?\n" +
//",GETDATE()\n" +
//",?\n" +
//",'Aberto'\n" +            
//",?)";
//    
//    PreparedStatement stmt = conexao.prepareStatement(sql);
//    
//    stmt.setString(1, c1.getNomeTRN());
//    stmt.setString(2, c1.getDtInicioTRN());
//    stmt.setString(3, c1.getUsuarioCNX());
//    
//    stmt.execute();
//    stmt.close();
//}
//
//public void inserirSeg(Eventos_bean c1)throws SQLException{
//    
//    String sql = "insert into CPT_segTRN(\n" +
//"codTRN\n" +
//",nomeSEG\n"+            
//")values(\n" +
//"\n" +
//"(select max(codTRN) from CPT_cadTRN)\n" +
//"\n" +
//",?)";
//    
//    PreparedStatement stmt = conexao.prepareStatement(sql);
//    
//    stmt.setString(1, c1.getNomeSeg());
//    
//    stmt.execute();
//    stmt.close();
//}
//
//
//public void alteraSeg(Eventos_bean c1)throws SQLException{
//    
//    String sql = "insert into CPT_segTRN(\n" +
//"codTRN\n" +
//",nomeSEG\n"+            
//")values(\n" +
//"\n" +
//"?\n" +
//"\n" +
//",?)";
//    
//    PreparedStatement stmt = conexao.prepareStatement(sql);
//    
//    stmt.setInt(1, c1.getCodTRN());
//    stmt.setString(2, c1.getNomeSeg());
//    
//    stmt.execute();
//    stmt.close();
//}
//
//public void ExcluirSeg(Eventos_bean c1)throws SQLException{
//    
//    String sql= "delete from CPT_segTRN where codSEG = ?";
//    PreparedStatement stmt = this.conexao.prepareStatement(sql);
//    stmt.setInt(1, c1.getCodSeg());
//    
//    stmt.execute();
//    stmt.close();
//    
//}
//
//public void alterarEve(Eventos_bean c1)throws SQLException{
//    
//    String sql= "update CPT_cadTRN set nomeTRN = ?, dtInicioTRN = ? where codTRN = ?";
//    PreparedStatement stmt = this.conexao.prepareStatement(sql);
//    stmt.setString(1, c1.getNomeTRN());
//    stmt.setString(2, c1.getDtInicioTRN());
//    stmt.setInt(3, c1.getCodTRN());
//    
//    stmt.execute();
//    stmt.close();
//
//}
//
//public void finalizaEve(int cod)throws SQLException{
//    
//    String sql= "update CPT_cadTRN set statusTRN = 'Finalizado', dtFinalTRN = getdate() where codTRN = ?";
//    PreparedStatement stmt = this.conexao.prepareStatement(sql);
//    
//    stmt.setInt(1, cod);
//    
//    stmt.execute();
//    stmt.close();
//
//}
//
//public void cancelaEve(int cod)throws SQLException{
//    
//    String sql= "update CPT_cadTRN set statusTRN = 'Cancelado' where codTRN = ?";
//    PreparedStatement stmt = this.conexao.prepareStatement(sql);
//    
//    stmt.setInt(1, cod);
//    
//    stmt.execute();
//    stmt.close();
//
//}
//
//}
