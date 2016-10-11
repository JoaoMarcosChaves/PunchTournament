/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tcc.dao;

import Conexao.ConectaBanco;
import br.tcc.bean.GraduacoesModali_bean;
import br.tcc.bean.Modalidades_bean;
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
public class Modalidades_dao {
    private Connection conexao;
    
    public Modalidades_dao()throws SQLException{
    this.conexao = new ConectaBanco().getConexao();
    }
    
    public List<Modalidades_bean> PesqMod()throws SQLException{
        
        String sql2 = "select * from TB_modalidades";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql2);
        
        
        stmt.execute();
        
        ResultSet rs = stmt.executeQuery(); 
    
        ArrayList<Modalidades_bean> lista = new ArrayList<>();
        
        while(rs.next()){
            Modalidades_bean bean = new Modalidades_bean();
            bean.setCodModali(rs.getInt("codModali"));
            bean.setNomeModali(rs.getString("nomeModali"));
            bean.setDescricao(rs.getString("descricao"));
            lista.add(bean);
        }
        rs.close();
        stmt.close();
        return lista;
    }
    
    public ArrayList<GraduacoesModali_bean> PesqGraduARB(int codMod)throws SQLException{
        
        String sql2 = "select * from TB_graduaModali where codModali = ? and tipoGradu = 'Arbitro'";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql2);
        stmt.setInt(1, codMod);
        
        stmt.execute();
        
        ResultSet rs = stmt.executeQuery(); 
    
        ArrayList<GraduacoesModali_bean> lista = new ArrayList<>();
        
        while(rs.next()){
            GraduacoesModali_bean bean = new GraduacoesModali_bean();
            bean.setCodGraduMod(rs.getInt("codGraduMod"));
            bean.setIdentificacaoGradu(rs.getString("IdentificacaoGradu"));
            //bean.setDescricao(rs.getString("descricao"));
            lista.add(bean);
        }
        rs.close();
        stmt.close();
        return lista;
    }
    
    public ArrayList<GraduacoesModali_bean> PesqGraduATL(int codMod)throws SQLException{
        
        String sql2 = "select * from TB_graduaModali where codModali = ? and tipoGradu = 'Atleta'";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql2);
        stmt.setInt(1, codMod);
        
        stmt.execute();
        
        ResultSet rs = stmt.executeQuery(); 
    
        ArrayList<GraduacoesModali_bean> lista = new ArrayList<>();
        
        while(rs.next()){
            GraduacoesModali_bean bean = new GraduacoesModali_bean();
            //bean.setCodModali(rs.getInt("codModali"));
            bean.setCodGraduMod(rs.getInt("codGraduMod"));
            bean.setIdentificacaoGradu(rs.getString("IdentificacaoGradu"));
            
            //bean.setDescricao(rs.getString("descricao"));
            lista.add(bean);
        }
        rs.close();
        stmt.close();
        return lista;
    }
    
     public List<Modalidades_bean>inserirMod(Modalidades_bean bean)throws SQLException{
        
        String sql = "INSERT INTO TB_modalidades\n" +
"           (nomeModali\n" +
"           ,descricao) \n" +
"            VALUES\n" +
"           (?\n" +
"           ,?)\n" +
"\n" ;

            String sql2 = "select * from TB_modalidades where codModali = (select max(codModali) from TB_modalidades)";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        PreparedStatement stmt2 = this.conexao.prepareStatement(sql2);
        
        stmt.setString(1,bean.getNomeModali());
        stmt.setString(2,bean.getDescricao());
        
        stmt.execute();
       
        
        
        ResultSet rs = stmt2.executeQuery(); 
    
        ArrayList<Modalidades_bean> lista = new ArrayList<>();
        
        while(rs.next()){
            bean.setCodModali(rs.getInt("codModali"));
            bean.setNomeModali(rs.getString("nomeModali"));
            lista.add(bean);
        }
        rs.close();
        stmt.close();
       
        return lista;
    }
    
    
    public void insereGraduATL(GraduacoesModali_bean bean) throws SQLException{
        
        String sql = "INSERT INTO [dbo].[TB_graduaModali]\n" +
"           ([codModali]\n" +
"           ,[identificacaoGradu]\n" +
"           ,[tipoGradu])\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?\n" +
"           ,'Atleta')";
        
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        
        stmt.setInt(1, bean.getCodModali());
        stmt.setString(2, bean.getIdentificacaoGradu());
       
        
        stmt.execute();
        stmt.close(); 
    }
    
    public void insereGraduARB(GraduacoesModali_bean bean) throws SQLException{
        
        String sql = "INSERT INTO [dbo].[TB_graduaModali]\n" +
"           ([codModali]\n" +
"           ,[identificacaoGradu]\n" +
"           ,[tipoGradu])\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?\n" +
"           ,'Arbitro')";
        
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        
        stmt.setInt(1, bean.getCodModali());
        stmt.setString(2, bean.getIdentificacaoGradu());
       
        
        stmt.execute();
        stmt.close(); 
    }
    
    public void alterarMod(Modalidades_bean bean)throws SQLException{
        
        String sql = "update TB_modalidades set nomeModali = ?, descricao = ? where codModali = ?";
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        
        stmt.setString(1,bean.getNomeModali());
        stmt.setString(2,bean.getDescricao());
        stmt.setInt(3,bean.getCodModali());
        
        stmt.execute();
        stmt.close();
        
        
    }
    
    public void RemoveGradu(GraduacoesModali_bean bean) throws SQLException{
        
        String sql = "delete from TB_graduaModali where codGraduMod = ?";
        
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        
        stmt.setInt(1, bean.getCodGraduMod());
        
        stmt.execute();
        stmt.close(); 
    }
        
}
