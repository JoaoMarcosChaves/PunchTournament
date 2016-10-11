//
//package br.tcc.dao;
//
//import Conexao.ConectaBanco;
//import br.tcc.bean.Eventos_bean;
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
//public class Consultas_dao {
//
//    private Connection conexao;
//    
//    public Consultas_dao()throws SQLException{
//    this.conexao = new ConectaBanco().getConexao();
//    }
//    
//    
//    public List<Eventos_bean>Relatorio() throws SQLException{
//        
//    String sql = "select t.nomeTRN , segmentos = count(s.nomeSEG)"
//            + " from CPT_cadTRN t inner join CPT_segTRN s on t.codTRN = s.codTRN\n" +
//                 "group by t.nomeTRN ";
//    PreparedStatement stmt = this.conexao.prepareStatement(sql);
//        
//        
//       
//        ResultSet rs = stmt.executeQuery(); 
//        
//        ArrayList<Eventos_bean> minhaLista = new ArrayList<Eventos_bean>();
//        
//        
//        while(rs.next()){
//            Eventos_bean c1 = new Eventos_bean();
//            c1.setNomeTRN(rs.getString("nomeTRN"));
//            c1.setQtdSegs(rs.getInt("segmentos"));
//            minhaLista.add(c1);
//        }
//        rs.close();
//        stmt.close();
//        return minhaLista;
//    }
//    
//    
//    public List<Eventos_bean>comboEventos() throws SQLException{
//        
//    String sql = "select * from CPT_cadTRN where statusTRN = 'Aberto' ";
//    PreparedStatement stmt = this.conexao.prepareStatement(sql);
//        
//        
//       
//        ResultSet rs = stmt.executeQuery(); 
//        
//        ArrayList<Eventos_bean> minhaLista = new ArrayList<Eventos_bean>();
//        
//        
//        while(rs.next()){
//            Eventos_bean c1 = new Eventos_bean();
//            c1.setNomeTRN(rs.getString("nomeTRN"));
//            c1.setCodTRN(rs.getInt("codTRN"));
//            minhaLista.add(c1);
//        }
//        rs.close();
//        stmt.close();
//        return minhaLista;
//    }
//    
//    
//    
//    
//    public List<Eventos_bean>consultaDtTRN(int cod) throws SQLException{
//        
//    String sql = "select * from CPT_cadTRN where codTRN = ? and statusTRN = 'Aberto' ";
//    PreparedStatement stmt = this.conexao.prepareStatement(sql);
//      stmt.setInt(1,cod);  
//        
//       
//        ResultSet rs = stmt.executeQuery(); 
//        
//        ArrayList<Eventos_bean> minhaLista = new ArrayList<Eventos_bean>();
//        
//        
//        while(rs.next()){
//            Eventos_bean c1 = new Eventos_bean();
//            
//            //c1.setCodTRN(rs.getInt("codTRN"));
//            c1.setDtInicioTRN(rs.getString("dtInicioTRN"));
//            
//            minhaLista.add(c1);
//        }
//        rs.close();
//        stmt.close();
//        return minhaLista;
//    }
//    
//    public List<Eventos_bean>consultaSegsTRN(int cod) throws SQLException{
//        
//    String sql = "select * from CPT_segTRN where codTRN = ?";
//    PreparedStatement stmt = this.conexao.prepareStatement(sql);
//      stmt.setInt(1,cod);  
//        
//       
//        ResultSet rs = stmt.executeQuery(); 
//        
//        ArrayList<Eventos_bean> minhaLista = new ArrayList<Eventos_bean>();
//        
//        
//        while(rs.next()){
//            Eventos_bean c1 = new Eventos_bean();
//            
//            c1.setCodSeg(rs.getInt("codSEG"));
//            c1.setNomeSeg(rs.getString("nomeSEG"));
//            minhaLista.add(c1);
//        }
//        rs.close();
//        stmt.close();
//        return minhaLista;
//    }
//    
//    public List<Eventos_bean>consultaCodSegTRN(String nomeSeg, int codTRN) throws SQLException{
//        
//    String sql = "select * from CPT_segTRN where nomeSEG = ? and codTRN = ? ";
//    PreparedStatement stmt = this.conexao.prepareStatement(sql);
//      
//      stmt.setString(1, nomeSeg);  
//      stmt.setInt(2, codTRN);    
//       
//        ResultSet rs = stmt.executeQuery(); 
//        
//        ArrayList<Eventos_bean> minhaLista = new ArrayList<Eventos_bean>();
//        
//        
//        while(rs.next()){
//            Eventos_bean c1 = new Eventos_bean();
//            
//            c1.setCodSeg(rs.getInt("codSEG"));
//            
//            minhaLista.add(c1);
//        }
//        rs.close();
//        stmt.close();
//        return minhaLista;
//    }
//    
//}
