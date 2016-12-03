package br.tcc.dao;

import Conexao.ConectaBanco;
import br.tcc.bean.Atletas_bean;
import br.tcc.bean.Categorias_bean;
import br.tcc.bean.InscricaoPRT_bean;
import br.tcc.bean.Relatorios_bean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joãomarcos
 */
public class Atletas_dao {

    private Connection conexao; 
    
    public Atletas_dao()throws SQLException{
    this.conexao = new ConectaBanco().getConexao();
    }
    
    public List<Atletas_bean> inserirAtleta(Atletas_bean bean)throws SQLException{
        
        String sql1 = "INSERT INTO [dbo].[TB_atleta]\n" +
"           ([nomeAtleta]\n" +
"           ,[idadeAtleta]\n" +
"           ,[sexo]\n" +
"           ,[pesoAtleta]\n" +
"           ,[graduacaoAtleta]\n" +
"           ,[cpfAtleta]\n" +
"           ,[emailAtleta])\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?)";
        
        String sql2 = "select * from TB_atleta where codAtleta = (select codAtleta = MAX(codAtleta) from TB_atleta)";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql1);
        PreparedStatement stmt2 = this.conexao.prepareStatement(sql2);
        
        
        stmt.setString(1, bean.getNomeAtleta());
        stmt.setInt(2, bean.getIdadeAtleta());
        stmt.setString(3, bean.getSexo());
        stmt.setFloat(4, bean.getPesoAtleta());
        stmt.setString(5, bean.getGraduacaoAtleta());
        stmt.setString(6, bean.getCpfAtleta());
        stmt.setString(7, bean.getEmailAtleta());
        
        
        stmt.execute();
        
        ResultSet rs = stmt2.executeQuery(); 
        ArrayList<Atletas_bean> lista = new ArrayList<>();
        
        while(rs.next()){
        Atletas_bean c1 = new Atletas_bean();
            
        c1.setCodAtleta(rs.getInt("codAtleta"));
        lista.add(c1);
        }
        rs.close();
        stmt2.close();
        return lista;
    }
    
    public List<InscricaoPRT_bean> inserirInscAtleta(InscricaoPRT_bean bean)throws SQLException{
        
        String sql1 = "INSERT INTO [dbo].[TB_inscricaoAtleta]\n" +
"           ([codSegmento]\n" +
"           ,[codAtleta]\n" +
"           ,[codCategoria]\n" +
"           ,[statusInscricao])\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,'Pendente')";
        
        String sql2 = "select * from TB_inscricaoAtleta where codInscricao = (select codInscricao = MAX(codInscricao) from TB_inscricaoAtleta)";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql1);
        PreparedStatement stmt2 = this.conexao.prepareStatement(sql2);
        
        
        stmt.setInt(1, bean.getCodSegmento());
        stmt.setInt(2, bean.getCodAtleta());
        stmt.setInt(3, bean.getCodCategoria());
        
        stmt.execute();
        
        ResultSet rs = stmt2.executeQuery(); 
        ArrayList<InscricaoPRT_bean> lista = new ArrayList<>();
        
        while(rs.next()){
        InscricaoPRT_bean c1 = new InscricaoPRT_bean();
            
        c1.setCodInscricao(rs.getInt("codInscricao"));
        lista.add(c1);
        }
        rs.close();
        stmt2.close();
        return lista;
    }
    
    public void confirmaInscricao(int cod)throws SQLException{
        String sql = "update TB_inscricaoAtleta set statusInscricao = 'Confirmado' where codInscricao  = ?";
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.setInt(1, cod);
        stmt.execute();
        stmt.close();
    }
    
       public List<Atletas_bean> pesquisaAtletas(Atletas_bean bean)throws SQLException{
           
           String sql = "select * from TB_atleta where graduacaoAtleta in (select codGraduMod from TB_graduaModali where\n" +
                         "codModali = ? \n" +
                         "and tipoGradu = 'Atleta')";
           
           if(bean.getCodAtleta() != 0 && bean.getNomeAtleta().equals("")){
               sql+="\n and codAtleta =  "+bean.getCodAtleta();
           }else if(bean.getCodAtleta() == 0 && !bean.getNomeAtleta().equals("")){
               sql+="\n and nomeAtleta like '"+bean.getNomeAtleta()+"%'";
           }else if(bean.getCodAtleta() != 0 && !bean.getNomeAtleta().equals("")){
               sql+="\n and codAtleta =  "+bean.getCodAtleta();
           }
           
           PreparedStatement stmt = this.conexao.prepareStatement(sql);
           stmt.setInt(1, bean.getCodModalidade());
           ResultSet rs = stmt.executeQuery(); 
        ArrayList<Atletas_bean> lista = new ArrayList<>();
        
        while(rs.next()){
        Atletas_bean c1 = new Atletas_bean();
            
        c1.setCodAtleta(rs.getInt("codAtleta"));
         
        c1.setNomeAtleta(rs.getString("nomeAtleta"));
        
        lista.add(c1);
        }
        rs.close();
        stmt.close();
        return lista;
           
       }
    
       
       public List<Relatorios_bean> consultaGeralAtleta(int codATL, String nomeATL, int codEve, int tpCons)throws SQLException{
           
           String sql = "select a.nomeAtleta, a.codAtleta from TB_inscricaoAtleta i inner join TB_atleta a\n" +
"on i.codAtleta = a.codAtleta\n" +
"inner join TB_segmentos s\n" +
"on i.codSegmento = s.codSegmento\n" +
"and a.codAtleta = ? or a.nomeAtleta like ? \n" +
"and s.codEvento = ?\n" +
"group by a.codAtleta,a.nomeAtleta";
           
           String sql2 = "select a.nomeAtleta, a.codAtleta from TB_inscricaoAtleta i inner join TB_atleta a\n" +
"on i.codAtleta = a.codAtleta\n" +
"inner join TB_segmentos s\n" +
"on i.codSegmento = s.codSegmento\n" +
"and s.codEvento = ?\n" +
"group by a.codAtleta,a.nomeAtleta";
           
          PreparedStatement stmt = null;
          ResultSet rs = null;  
           if(tpCons == 1){
           stmt = this.conexao.prepareStatement(sql);
           stmt.setInt(1, codATL);
           stmt.setString(2, nomeATL);
           stmt.setInt(3, codEve);
           rs = stmt.executeQuery(); 
           }else{
           stmt = this.conexao.prepareStatement(sql2);
             stmt.setInt(1, codEve);
           rs = stmt.executeQuery(); 
           }
          
        ArrayList<Relatorios_bean> lista = new ArrayList<>();
        while(rs.next()){
        
         Relatorios_bean c1 = new Relatorios_bean();
            
        c1.setCodAtleta(rs.getInt("codAtleta"));
         
        c1.setNomeAtleta(rs.getString("nomeAtleta"));
        
        lista.add(c1);
        }
        rs.close();
        stmt.close();
        return lista;
           
       }
       
       public int consultaATLestaINSC(int codATL, int codSeg,int codCategoria)throws SQLException{
           
           String sql = "select codInscricao from TB_inscricaoAtleta where codAtleta =? and codSegmento = ? and codCategoria = ?";
           
           PreparedStatement stmt = this.conexao.prepareStatement(sql);
           stmt.setInt(1, codATL);
           stmt.setInt(2, codSeg);
           stmt.setInt(3, codCategoria);
           ResultSet rs = stmt.executeQuery(); 
        int codInsc = 0;
        
        while(rs.next()){
        
            codInsc = rs.getInt("codInscricao");
        }
        rs.close();
        stmt.close();
        return codInsc;
           
       }
       
       public List<Categorias_bean> pesquisaCatATL(int codAtleta, int codSegmento)throws SQLException{
           
           String sql = "select * from TB_categoria where \n" +
"pesoMinCategoria <= (select pesoAtleta from TB_atleta where codAtleta = ?)\n" +
"and pesoMaxCategoria >= (select pesoAtleta from TB_atleta where codAtleta =?)\n" +
"and idadeMinCategoria <= (select idadeAtleta from TB_atleta where codAtleta = ?)\n" +
"and idadeMaxCategoria >= (select idadeAtleta from TB_atleta where codAtleta = ?)\n" +
"and sexo = (select sexo from TB_atleta where codAtleta = ?)\n" +
"and codSegmento = ?";
          
           PreparedStatement stmt = this.conexao.prepareStatement(sql);
           stmt.setInt(1, codAtleta);
           stmt.setInt(2, codAtleta);
           stmt.setInt(3, codAtleta);
           stmt.setInt(4, codAtleta);
           stmt.setInt(5, codAtleta);
           stmt.setInt(6, codSegmento);
           ResultSet rs = stmt.executeQuery(); 
        ArrayList<Categorias_bean> lista = new ArrayList<>();
        
        while(rs.next()){
        Categorias_bean c1 = new Categorias_bean();
            
        c1.setCodCategoria(rs.getInt("codCategoria"));
         
        c1.setNomeCategoria(rs.getString("nomeCategoria"));
        
        lista.add(c1);
        }
        rs.close();
        stmt.close();
        return lista;
           
       }
       
       public List<InscricaoPRT_bean> consultaInscPend(InscricaoPRT_bean bean)throws SQLException{
           String sql = "select i.codInscricao,s.nomeSegmento,c.nomeCategoria from TB_inscricaoAtleta i inner join TB_segmentos s\n" +
"on i.codSegmento = s.codSegmento\n" +
"inner join TB_categoria c\n" +
"on c.codCategoria = i.codCategoria\n" +
"and i.statusInscricao = 'Pendente'\n" +
"and codAtleta = ?";
           PreparedStatement stmt = this.conexao.prepareStatement(sql);
           stmt.setInt(1, bean.getCodAtleta());
           ResultSet rs = stmt.executeQuery();
           List<InscricaoPRT_bean> lista = new ArrayList<>();
           while(rs.next()){
               InscricaoPRT_bean c1 = new InscricaoPRT_bean();
               c1.setCodInscricao(rs.getInt("codInscricao"));
               c1.setNomeSegmento(rs.getString("nomeSegmento"));
               c1.setNomeCategoria(rs.getString("nomeCategoria"));
               
               lista.add(c1);
           }
           rs.close();
           stmt.close();
           return lista;
       }
}
