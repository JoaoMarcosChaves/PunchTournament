/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tcc.dao;

import Conexao.ConectaBanco;
import br.tcc.bean.Arbitros_bean;
import br.tcc.bean.Categorias_bean;
import br.tcc.bean.Eventoss_bean;
import br.tcc.bean.MetodosPontua_bean;
import br.tcc.bean.Segmentos_bean;
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
public class Eventos_dao {
   private Connection conexao; 
   
   public Eventos_dao()throws SQLException{
       this.conexao = new ConectaBanco().getConexao();
   }
   
    
    
    public List<Eventoss_bean> inserirEventos(Eventoss_bean bean)throws SQLException{
        
        String sql = "INSERT INTO [dbo].[TB_evento]\n" +
"           ([codModali]\n" +
"           ,[dataEvento]\n" +
"           ,[statusEvento]\n" +
"           ,[nomeEvento])\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?\n" +
"           ,'Aberto'\n" +
"           ,?)";
        
        String sql2 = "select * from TB_evento where codEvento = (select max(codEvento) from TB_evento)";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        PreparedStatement stmt2 = this.conexao.prepareStatement(sql2);
        
        stmt.setInt(1, bean.getCodModali());
        stmt.setString(2, bean.getDataEvento());
        stmt.setString(3, bean.getNomeEvento());
        
        stmt.execute();
        
        ResultSet rs = stmt2.executeQuery(); 
        
         ArrayList<Eventoss_bean> minhaLista = new ArrayList<>();
        
        
        while(rs.next()){
            Eventoss_bean c1 = new Eventoss_bean();
            c1.setNomeEvento(rs.getString("nomeEvento"));
            c1.setCodEvento(rs.getInt("codEvento"));
            minhaLista.add(c1);
        }
        rs.close();
        stmt.close();
        return minhaLista;       
    }
    
    
    public void inserirSegs(Segmentos_bean bean)throws SQLException{
        
        String sql = "INSERT INTO [dbo].[TB_segmentos]\n" +
"           ([codEvento]\n" +
"           ,[nomeSegmento]\n" +
"           ,[descricaoSegmento])\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?\n" +
"           ,?)";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.setInt(1,bean.getCodEvento());
        stmt.setString(2,bean.getNomeSegmento());
        stmt.setString(3,bean.getDescricaoSegmento());
        
         stmt.execute();
         stmt.close();
    }
    
    
    
    public void inserirCategoria(Categorias_bean bean)throws SQLException{
        
        String sql = "INSERT INTO [dbo].[TB_categoria]\n" +
"           ([codSegmento]\n" +
"           ,[pesoMinCategoria]\n" +
"           ,[pesoMaxCategoria]\n" +
"           ,[graduacaoMinCategoria]\n" +
"           ,[graduacaoMaxCategoria]\n" +
"           ,[idadeMinCategoria]\n" +
"           ,[idadeMaxCategoria]\n" +
"           ,[sexo]\n" +
"           ,[nomeCategoria])\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?)";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.setInt(1,bean.getCodSegmento());
        stmt.setFloat(2,bean.getPesoMinCategoria());
        stmt.setFloat(3,bean.getPesoMaxCategoria());
        stmt.setInt(4,bean.getGraduacaoMinCategoria());
        stmt.setInt(5,bean.getGraduacaoMaxCategoria());
        stmt.setInt(6,bean.getIdadeMinCategoria());
        stmt.setInt(7,bean.getIdadeMaxCategoria());
        stmt.setString(8,bean.getSexo());
        stmt.setString(9,bean.getNomeCategoria());
        
         stmt.execute();
         stmt.close();
    }
    
   
    
    
    
    public void inserirModPonto(MetodosPontua_bean bean)throws SQLException{
        
        String sql = "INSERT INTO [dbo].[TB_metodoPontua]\n" +
"           ([codSegmento]\n" +
"           ,[nomePontua]\n" +
"           ,[valorPontua]\n" +
"           ,[tipoPontua]\n" +
"           ,[descricaoPontua]\n" +
"           ,[parteDoCorpo])\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?)";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.setInt(1,bean.getCodSegmento());
        stmt.setString(2,bean.getNomePontua());
        stmt.setInt(3,bean.getValorPontua());
        stmt.setString(4,bean.getTipoPontua());
        stmt.setString(5,bean.getDescricaoPontua());
        stmt.setString(6,bean.getParteDoCorpo());
        
         stmt.execute();
         stmt.close();
    }
    
    public List<Arbitros_bean> inserirARB(Arbitros_bean bean)throws SQLException{
        
        String sql1 = "INSERT INTO [dbo].[TB_arbitro]\n" +
"           ([codSegmento]\n" +
"           ,[nomeArbitro]\n" +
"           ,[graduacaoArbitro])\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?\n" +
"           ,?)";
        
        
        String sql2 = "select codArbitro = MAX(codArbitro) from TB_arbitro where codSegmento = ?";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql1);
        PreparedStatement stmt2 = this.conexao.prepareStatement(sql2);
        
        stmt.setInt(1, bean.getCodSegmento());
        stmt.setString(2, bean.getNomeArbitro());
        stmt.setString(3, bean.getGraduacaoArbitro());
        
        stmt2.setInt(1, bean.getCodSegmento());
        
        stmt.execute();
        
        ResultSet rs = stmt2.executeQuery(); 
        
         ArrayList<Arbitros_bean> minhaLista = new ArrayList<>();
        
        
        while(rs.next()){
            Arbitros_bean c1 = new Arbitros_bean();
            c1.setCodArbitro(rs.getInt("codArbitro"));
            minhaLista.add(c1);
        }
        rs.close();
        stmt.close();
        return minhaLista;       
        
    }
 
    public void inserirPTporARB(Arbitros_bean bean)throws SQLException{
        
        String sql = "INSERT INTO [dbo].[TB_pontua]\n" +
"           ([codArbitro]\n" +
"           ,[codPontua])\n" +
       "     VALUES\n" +
"           (?\n" +
"           ,?)";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.setInt(1, bean.getCodArbitro());
        stmt.setInt(2, bean.getCodPontua());
        stmt.execute();
        stmt.close();
    }
    
    public List<Eventoss_bean>pesquisaEventos(int cod) throws SQLException{
        
    String sql = "select * from TB_Evento where statusEvento = 'Aberto' and codModali = ?";
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    
    
    
    stmt.setInt(1,cod);     
        
        ResultSet rs = stmt.executeQuery(); 
        
        ArrayList<Eventoss_bean> minhaLista = new ArrayList<Eventoss_bean>();
        
        
        while(rs.next()){
            Eventoss_bean c1 = new Eventoss_bean();
            c1.setNomeEvento(rs.getString("nomeEvento"));
            c1.setCodEvento(rs.getInt("codEvento"));
            minhaLista.add(c1);
        }
        rs.close();
        stmt.close();
        return minhaLista;
    }
   
     public List<Segmentos_bean> consultaSegs(int cod)throws SQLException{
        
         String sql = "select * from TB_segmentos where codEvento = ?";
         PreparedStatement stmt = this.conexao.prepareStatement(sql);
          stmt.setInt(1,cod);  
         ResultSet rs = stmt.executeQuery(); 
         
         ArrayList<Segmentos_bean> minhaLista = new ArrayList<>();
         while(rs.next()){
            Segmentos_bean c1 = new Segmentos_bean();
            c1.setNomeSegmento(rs.getString("nomeSegmento"));
            c1.setCodSegmento(rs.getInt("codSegmento"));
            c1.setDescricaoSegmento(rs.getString("descricaoSegmento"));
            minhaLista.add(c1);
        }
         rs.close();
         stmt.close();
         return minhaLista;
    }
     
     public List<Eventoss_bean> consultaDataEve(int cod)throws SQLException{
        
         String sql = "select * from TB_Evento where codEvento = ?";
         PreparedStatement stmt = this.conexao.prepareStatement(sql);
          stmt.setInt(1,cod);  
         ResultSet rs = stmt.executeQuery(); 
         
         ArrayList<Eventoss_bean> minhaLista = new ArrayList<>();
         while(rs.next()){
            Eventoss_bean c1 = new Eventoss_bean();
            c1.setDataEvento(rs.getString("dataEvento"));
            minhaLista.add(c1);
        }
         rs.close();
         stmt.close();
         return minhaLista;
    }
    
     public List<Categorias_bean> consultaCategoriasSegmento(int cod)throws SQLException{
        
         String sql = "select * from TB_categoria where codSegmento = ?";
         PreparedStatement stmt = this.conexao.prepareStatement(sql);
          stmt.setInt(1,cod);  
         ResultSet rs = stmt.executeQuery(); 
         
         ArrayList<Categorias_bean> minhaLista = new ArrayList<>();
         while(rs.next()){
            Categorias_bean c1 = new Categorias_bean();
            c1.setCodCategoria(rs.getInt("codCategoria"));
            c1.setPesoMinCategoria(rs.getFloat("pesoMinCategoria"));
            c1.setPesoMaxCategoria(rs.getFloat("pesoMaxCategoria"));
            c1.setGraduacaoMinCategoria(rs.getInt("graduacaoMinCategoria"));
            c1.setGraduacaoMaxCategoria(rs.getInt("graduacaoMaxCategoria"));
            c1.setIdadeMinCategoria(rs.getInt("idadeMinCategoria"));
            c1.setIdadeMaxCategoria(rs.getInt("idadeMaxCategoria"));
            c1.setSexo(rs.getString("sexo"));
            c1.setNomeCategoria(rs.getString("nomeCategoria"));
            
            minhaLista.add(c1);
        }
         rs.close();
         stmt.close();
         return minhaLista;
    }
    
    public List<MetodosPontua_bean> pesquisaMTPontua(int cod)throws SQLException{
        
        String sql = "select * from TB_metodoPontua where codSegmento = ?";
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.setInt(1,cod);  
        ResultSet rs = stmt.executeQuery(); 
        
         ArrayList<MetodosPontua_bean> lista = new ArrayList<>();
        
         while(rs.next()){
            MetodosPontua_bean c1 = new MetodosPontua_bean();
            c1.setCodPontua(rs.getInt("codPontua"));
            c1.setNomePontua(rs.getString("nomePontua"));
            c1.setTipoPontua(rs.getString("tipoPontua"));
            c1.setDescricaoPontua(rs.getString("descricaoPontua"));
            lista.add(c1);
        }
        rs.close();
        stmt.close();
        
        return lista;
    }
    
    public List<Arbitros_bean> pesquisaArbitro(int cod)throws SQLException{
        
        String sql = "select * from TB_arbitro where codSegmento = ?";
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.setInt(1,cod);  
        ResultSet rs = stmt.executeQuery(); 
        
         ArrayList<Arbitros_bean> lista = new ArrayList<>();
        
         while(rs.next()){
            Arbitros_bean c1 = new Arbitros_bean();
            c1.setCodArbitro(rs.getInt("codArbitro"));
            c1.setNomeArbitro(rs.getString("nomeArbitro"));
            c1.setGraduacaoArbitro(rs.getString("graduacaoArbitro"));
            lista.add(c1);
        }
        rs.close();
        stmt.close();
        
        return lista;
    }
    
    
    public List<String> pesquisaGeralArbitro()throws SQLException{
        
        String sql = "select * from TB_arbitro";
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
       
        ResultSet rs = stmt.executeQuery(); 
        
         ArrayList<String> lista = new ArrayList<>();
        
         while(rs.next()){

            String arb = rs.getInt("codArbitro") +" - "+ rs.getString("nomeArbitro");
            
            lista.add(arb);
        }
        rs.close();
        stmt.close();
        
        return lista;
    }
    
    public List<Arbitros_bean> pesquisaPTporARB(int cod)throws SQLException{
        
        String sql = "select * from TB_pontua p inner join TB_metodoPontua m\n" +
"on p.codPontua = m.codPontua\n" +
"and p.codArbitro in (select codArbitro from TB_arbitro where codSegmento = ?)"
                + "order by p.codArbitro asc";
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.setInt(1,cod);  
        ResultSet rs = stmt.executeQuery(); 
        
         ArrayList<Arbitros_bean> lista = new ArrayList<>();
        
         while(rs.next()){
            Arbitros_bean c1 = new Arbitros_bean();
            c1.setCodArbitro(rs.getInt("codArbitro"));
            c1.setCodPontua(rs.getInt("codPontua"));
            c1.setNomePontua(rs.getString("nomePontua"));
            lista.add(c1);
        }
        rs.close();
        stmt.close();
        
        return lista;
    }
    
    public List<Arbitros_bean> pesquisaPTdoARB(int cod)throws SQLException{
        
        String sql = "select m.codPontua, m.nomePontua from TB_pontua p inner join TB_metodoPontua m\n" +
"on p.codPontua = m.codPontua\n" +
"and p.codArbitro = ?";
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.setInt(1,cod);  
        ResultSet rs = stmt.executeQuery(); 
        
         ArrayList<Arbitros_bean> lista = new ArrayList<>();
        
         while(rs.next()){
            Arbitros_bean c1 = new Arbitros_bean();
            c1.setCodPontua(rs.getInt("codPontua"));
            c1.setNomePontua(rs.getString("nomePontua"));
            lista.add(c1);
        }
        rs.close();
        stmt.close();
        
        return lista;
    }
    
    public void finalizaEvento(int cod)throws SQLException{
        
        String sql ="update TB_evento set statusEvento = 'Finalizado' where codEvento = ?";
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.setInt(1, cod);
        
        stmt.execute();
        stmt.close();
        
    }
    
    public void cancelaEvento(int cod)throws SQLException{
        
        String sql ="update TB_evento set statusEvento = 'Cancelado' where codEvento = ?";
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.setInt(1, cod);
        
        stmt.execute();
        stmt.close();
        
    }
    
    public void alteraSegsEve(Segmentos_bean bean)throws SQLException{
        
        String sql = "update TB_segmentos set nomeSegmento = ?, descricaoSegmento = ? where codSegmento = ?";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        
        
        stmt.setString(1,bean.getNomeSegmento());
        stmt.setString(2,bean.getDescricaoSegmento());
        stmt.setInt(3,bean.getCodSegmento());
        
         stmt.execute();
         stmt.close();
    }
    
    public void alteraEve(Eventoss_bean bean)throws SQLException{
        
        String sql = "update TB_Evento set dataEvento = ?, nomeEvento = ? where codEvento = ?";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        
        
        stmt.setString(1, bean.getDataEvento());
        stmt.setString(2, bean.getNomeEvento());
        stmt.setInt(3, bean.getCodEvento());
        
         stmt.execute();
         stmt.close();
    }
   
    
    
    public void removeSeg(Segmentos_bean bean)throws SQLException{
        
        String sql = "delete from TB_segmentos where codSegmento = ?";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        
        
        stmt.setInt(1,bean.getCodSegmento());
        
         stmt.execute();
         stmt.close();
    }
    
    public void removeCat(int codCat)throws SQLException{
        
        String sql = "delete from TB_categoria where codCategoria = ?";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        
        
        stmt.setInt(1,codCat);
        
         stmt.execute();
         stmt.close();
    }
    
    public void removePTporARB(int codARB, int codPontua)throws SQLException{
        
        String sql = "delete from TB_pontua where codPontua = ? and codArbitro = ?";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        
        
        stmt.setInt(1,codPontua);
        stmt.setInt(2,codARB);
        
         stmt.execute();
         stmt.close();
    }
    
     public void removeMTPontua(int codPontua)throws SQLException{
        
        String sql = "delete from TB_metodoPontua where codPontua = ?";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        
        
        stmt.setInt(1,codPontua);
        
        
         stmt.execute();
         stmt.close();
    }
    
    public void removeARB(int codARB)throws SQLException{
        
        String sql = "delete from TB_arbitro where codArbitro = ?";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        
        
        stmt.setInt(1,codARB);
        
         stmt.execute();
         stmt.close();
    }
    
    
    
}
