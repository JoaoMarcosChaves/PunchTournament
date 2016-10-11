/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tcc.dao;

import Conexao.ConectaBanco;
import br.tcc.bean.Arbitros_bean;
import br.tcc.bean.Confrontos_bean;
import br.tcc.bean.InscricaoPRT_bean;
import br.tcc.bean.MetricasAT_bean;
import br.tcc.bean.MetricasCBT_bean;
import br.tcc.bean.Relatorios_bean;
import java.io.Serializable;
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

public class Confrontos_dao implements Serializable{
private Connection conexao; 

public Confrontos_dao()throws SQLException{
    this.conexao = new ConectaBanco().getConexao();
}

public List<InscricaoPRT_bean> consultaATL(int codCat)throws SQLException{
           
           String sql = "select * from TB_inscricaoAtleta i inner join TB_atleta a\n" +
                        "on i.codAtleta = a.codAtleta \n" +
                        "and i.codCategoria = ?";
           
           PreparedStatement stmt = this.conexao.prepareStatement(sql);
           stmt.setInt(1, codCat);
           ResultSet rs = stmt.executeQuery(); 
        
        List<InscricaoPRT_bean> lista = new ArrayList<>();
        while(rs.next()){
        InscricaoPRT_bean c1 = new InscricaoPRT_bean();
        
            c1.setCodInscricao(rs.getInt("codInscricao"));
            c1.setNomeAtleta(rs.getString("nomeAtleta"));
            lista.add(c1);
        }
        rs.close();
        stmt.close();
        return lista;
           
       }


//public List<Confrontos_bean> inserirConfrontos(Confrontos_bean bean)throws SQLException{
    public int inserirConfrontos(Confrontos_bean bean)throws SQLException{
    String sql= "INSERT INTO [dbo].[TB_confrontos]\n" +
"           ([codSegmento]\n" +
"           ,[codCategoria]\n" +
"           ,[nomeChvConf]\n" +
"           ,[statusChvConf]\n" +
"           ,[qtdRodadasChvConf])\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,'Aberto'\n" +
"           ,1)";
    
    String sql2 = "select * from TB_confrontos where codChvConf = (select max(codChvConf) from TB_confrontos)";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    PreparedStatement stmt2 = this.conexao.prepareStatement(sql2);
    stmt.setInt(1, bean.getCodSegmento());
    stmt.setInt(2, bean.getCodCategoria());
    stmt.setString(3, bean.getNomeChvConf());
    
    stmt.execute();
        
    ResultSet rs = stmt2.executeQuery(); 
    
   // ArrayList<Confrontos_bean> lista = new ArrayList<>();
    int codCHV = 0;
    while(rs.next()){
        //Confrontos_bean c1 = new Confrontos_bean();
        
        //c1.setCodChvConf(rs.getInt("codChvConf"));
        codCHV = rs.getInt("codChvConf");
        
    }
    rs.close();
    stmt2.close();
    return codCHV;
}

public void inserirMetATL(MetricasAT_bean bean)throws SQLException{
    String sql = "INSERT INTO [dbo].[TB_metricasAT]\n" +
"           ([codChvConf]\n" +
"           ,[codInscricao]\n" +
"           ,[totPontosPositivos]\n" +
"           ,[totPontosNegativos])\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?\n" +
"           ,0\n" +
"           ,0)";
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, bean.getCodChvConf());
    stmt.setInt(2, bean.getCodInscricao());
    
    stmt.execute();
    stmt.close();
}

public void inserirMetCBTVencedor(MetricasCBT_bean bean)throws SQLException{
    String sql = "INSERT INTO [dbo].[TB_metricasCBT]\n" +
"           ([codChvConf]\n" +
"           ,[codInscricao]\n" +
"           ,[numRodada]\n" +
"           ,[parteDoCorpoNGT]\n" +
"           ,[parteDoCorpoPST]\n" +
"           ,[descricaoCBT]"
            + ",[finalCBT]"
            + ",totPontosPositivos"
            + ",totPontosNegativos)\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?\n" +
"           ,(select numRodada from rodadasConfrontos where codChvConf = ? and statusRodada = 'Aberta')\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?"
         + ",?"
         + ",?"
         + ",?)\n" ;
    
    
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, bean.getCodChvConf());
    stmt.setInt(2, bean.getCodInscricao());
    stmt.setInt(3, bean.getCodChvConf());
    stmt.setString(4, bean.getParteDoCorpoNGT());
    stmt.setString(5, bean.getParteDoCorpoPST());
    stmt.setString(6, bean.getDescricaoCBT());
    stmt.setString(7, bean.getFinalCBT());
    stmt.setInt(8, bean.getTotPontosPositivos());
    stmt.setInt(9, bean.getTotPontosNegativos());
    stmt.execute();
    stmt.close();
}

public void inserirMetCBTPerdedor(MetricasCBT_bean bean)throws SQLException{
    String sql = "INSERT INTO [dbo].[TB_metricasCBT]\n" +
"           ([codChvConf]\n" +
"           ,[codInscricao]\n" +
"           ,[numRodada]\n" +
"           ,[parteDoCorpoNGT]\n" +
"           ,[parteDoCorpoPST]\n" +
"           ,[descricaoCBT]"
            + ",[finalCBT]"
            + ",totPontosPositivos"
            + ",totPontosNegativos)\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?\n" +
"           ,(select numRodada from rodadasConfrontos where codChvConf = ? and statusRodada = 'Aberta')\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?"
         + ",?"
         + ",?"
         + ",?)\n" ;
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, bean.getCodChvConf());
    stmt.setInt(2, bean.getCodInscricao());
    stmt.setInt(3, bean.getCodChvConf());
    stmt.setString(4, bean.getParteDoCorpoNGT());
    stmt.setString(5, bean.getParteDoCorpoPST());
    stmt.setString(6, bean.getDescricaoCBT());
    stmt.setString(7, bean.getFinalCBT());
    stmt.setInt(8, bean.getTotPontosPositivos());
    stmt.setInt(9, bean.getTotPontosNegativos());
    stmt.execute();
    stmt.close();
}

public void armazenarTBchave1(MetricasCBT_bean bean)throws SQLException{
    String sql = "INSERT INTO [dbo].[ordemChave1]\n" +
"           ([codChvConf]\n" +
"           ,[numRodada]\n" +
"           ,[codInscricao]\n" +
          " ,[statusCBT])"+
      "     VALUES\n" +
"           (?\n" +
"           ,(select numRodada from rodadasConfrontos where codChvConf = ? and statusRodada = 'Aberta')\n" +
"           ,?"+
            ",'Competindo')";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, bean.getCodChvConf());
    stmt.setInt(2, bean.getCodChvConf());
    stmt.setInt(3, bean.getCodInscricao());
    
    stmt.execute();
    stmt.close();
}

public void armazenarTBchave2(MetricasCBT_bean bean)throws SQLException{
    String sql = "INSERT INTO [dbo].[ordemChave2]\n" +
"           ([codChvConf]\n" +
"           ,[numRodada]\n" +
"           ,[codInscricao]\n" +
          " ,[statusCBT])"+
      "     VALUES\n" +
"           (?\n" +
"           ,(select numRodada from rodadasConfrontos where codChvConf = ? and statusRodada = 'Aberta')\n" +
"           ,?"+
            ",'Competindo')";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, bean.getCodChvConf());
    stmt.setInt(2, bean.getCodChvConf());
    stmt.setInt(3, bean.getCodInscricao());
    
    stmt.execute();
    stmt.close();
}

public void inserirRodadaCHV(int codCHV)throws SQLException{
    
    String sql = "INSERT INTO [dbo].[rodadasConfrontos]\n" +
"           ([codChvConf]\n" +
"           ,[numRodada]\n" +
"           ,[statusRodada])\n" +
"     VALUES\n" +
"           (?\n" +
"           ,1\n" +
"           ,'Aberta')";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codCHV);
    stmt.execute();
    stmt.close();
}

public void inserirNovaRodadaCHV(int codCHV)throws SQLException{
    
    String sql = "INSERT INTO [dbo].[rodadasConfrontos]\n" +
"           ([codChvConf]\n" +
"           ,[numRodada]\n" +
"           ,[statusRodada])\n" +
"     VALUES\n" +
"           (?\n" +
"           ,(select max(numRodada) from rodadasConfrontos where codChvConf = ?)+1\n" +
"           ,'Aberta')";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codCHV);
    stmt.setInt(2, codCHV);
    stmt.execute();
    stmt.close();
}

public void alteraStatusCBTchave1(int inscCod,String status)throws SQLException{
    String sql = "update ordemChave1 set statusCBT = ? where codInscricao = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setString(1, status);
    stmt.setInt(2, inscCod);
    
    
    stmt.execute();
    stmt.close();
}

public void alteraStatusCBTchave2(int inscCod,String status)throws SQLException{
    String sql = "update ordemChave2 set statusCBT = ? where codInscricao = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setString(1, status);
    stmt.setInt(2, inscCod);
    
    
    stmt.execute();
    stmt.close();
}

public void alteraMetricasAT(int ptPST,int ptNGT, int codInsc, int codCHV)throws SQLException{
    String sql = "update TB_metricasAT set totPontosPositivos += ?, totPontosNegativos += ? where codInscricao = ? and codChvConf = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, ptPST);
    stmt.setInt(2, ptNGT);
    stmt.setInt(3, codInsc);
    stmt.setInt(4, codCHV);
    
    stmt.execute();
    stmt.close();
}

public void finalizaRodadaCHV(int codCHV)throws SQLException{
    String sql = "update rodadasConfrontos set statusRodada = 'Finalizado' where codChvConf = ? and statusRodada = 'Aberta'";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    
    stmt.setInt(1, codCHV);
    
    stmt.execute();
    stmt.close();
}

public void finalizaCHV(int codCHV)throws SQLException{
    String sql = "update TB_confrontos set statusChvConf = 'Finalizado',"
            + " qtdRodadasChvConf = (select MAX(numRodada) from rodadasConfrontos where codChvConf = ?) where codChvConf = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    
    stmt.setInt(1, codCHV);
    stmt.setInt(2, codCHV);
    
    stmt.execute();
    stmt.close();
}

public List<Confrontos_bean> consultaChaveCONF(int cod)throws SQLException{
    
    String sql = "select * from TB_confrontos where codSegmento = ? and statusChvConf = 'Aberto'";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, cod);
    ResultSet rs = stmt.executeQuery(); 
    
    ArrayList<Confrontos_bean> lista = new ArrayList<>();
    while(rs.next()){
       Confrontos_bean c1 = new Confrontos_bean();
        
     c1.setCodChvConf(rs.getInt("codChvConf"));
     c1.setNomeChvConf(rs.getString("nomeChvConf"));
     c1.setQtdRodadasChvConf(rs.getInt("qtdRodadasChvConf"));
     lista.add(c1);   
    }
    rs.close();
    stmt.close();
    
    return lista;
}

public List<InscricaoPRT_bean> consultaPRTdeCHV(int codCHV)throws SQLException{
    
   String sql = "select i.codInscricao, a.nomeAtleta from TB_inscricaoAtleta i inner join TB_atleta a\n" +
"on i.codAtleta = a.codAtleta \n" +
"inner join TB_metricasAT m on m.codInscricao = i.codInscricao\n" +
"and m.codChvConf = ?";
           
           PreparedStatement stmt = this.conexao.prepareStatement(sql);
           stmt.setInt(1, codCHV);
           ResultSet rs = stmt.executeQuery(); 
        
        List<InscricaoPRT_bean> lista = new ArrayList<>();
        while(rs.next()){
        InscricaoPRT_bean c1 = new InscricaoPRT_bean();
        
            c1.setCodInscricao(rs.getInt("codInscricao"));
           
            c1.setNomeAtleta(rs.getString("nomeAtleta"));
           
            lista.add(c1);
        }
        rs.close();
        stmt.close();
        return lista;
}

public List<InscricaoPRT_bean> consultaEstruturaCHV1(int codCHV)throws SQLException{
    
    String sql = "select o.codInscricao, a.nomeAtleta, o.statusCBT, o.numRodada from TB_inscricaoAtleta i inner join TB_atleta a\n" +
"on i.codAtleta = a.codAtleta\n" +
"inner join ordemChave1 o on o.codInscricao = i.codInscricao\n" +
"and o.codChvConf = ?\n" +
"and o.statusCBT = 'Competindo'"+ 
"and o.numRodada = (select numRodada from rodadasConfrontos where codChvConf = ? and statusRodada = 'Aberta')";
    
           
           PreparedStatement stmt = this.conexao.prepareStatement(sql);
           
           stmt.setInt(1, codCHV);
           stmt.setInt(2, codCHV);
           
           ResultSet rs = stmt.executeQuery(); 
           
        List<InscricaoPRT_bean> lista = new ArrayList<>();
        while(rs.next()){
        InscricaoPRT_bean c1 = new InscricaoPRT_bean();
        List<Arbitros_bean> statusConf = new ArrayList<>();
        statusConf = consultandoStatusConfronto(codCHV); 
        String status = "";
        
        for(int i = 0; i < statusConf.size(); i++){
            if(rs.getInt("codInscricao") == statusConf.get(i).getCodInsc1()){
                   
                 status = "Iniciado";        
               }
        }
        
            c1.setCodInscricao(rs.getInt("codInscricao"));
           
            c1.setNomeAtleta(rs.getString("nomeAtleta"));
           
            c1.setNumRodada(rs.getInt("numRodada"));
        
            c1.setStatusConf(status);
       
            
            lista.add(c1);
        }
        rs.close();
        stmt.close();
        return lista;
}

public int consultaRodadaAtual(int codCHV)throws SQLException{
    
    String sql = "select numRodada from rodadasConfrontos where codChvConf = ? and statusRodada = 'Aberta'";
    
           
           PreparedStatement stmt = this.conexao.prepareStatement(sql);
           
           stmt.setInt(1, codCHV);
           
           ResultSet rs = stmt.executeQuery(); 
           
        int rodada = 0;
        while(rs.next()){
        InscricaoPRT_bean c1 = new InscricaoPRT_bean();
        
       
            rodada = rs.getInt("numRodada");
            
            
        }
        rs.close();
        stmt.close();
        return rodada;
}

public List<InscricaoPRT_bean> consultaEstruturaCHV2(int codCHV)throws SQLException{

    String sql = "select o.codInscricao, a.nomeAtleta, o.statusCBT, o.numRodada from TB_inscricaoAtleta i inner join TB_atleta a\n" +
"on i.codAtleta = a.codAtleta\n" +
"inner join ordemChave2 o on o.codInscricao = i.codInscricao\n" +
"and o.codChvConf = ?\n" +
"and o.statusCBT = 'Competindo'"+
"and o.numRodada = (select numRodada from rodadasConfrontos where codChvConf = ? and statusRodada = 'Aberta')";
           
           PreparedStatement stmt = this.conexao.prepareStatement(sql);
           
           stmt.setInt(1, codCHV);
           stmt.setInt(2, codCHV);
           
           ResultSet rs = stmt.executeQuery(); 
           
        List<InscricaoPRT_bean> lista = new ArrayList<>();
        while(rs.next()){
        InscricaoPRT_bean c1 = new InscricaoPRT_bean();
        
        List<Arbitros_bean> statusConf = new ArrayList<>();
        statusConf = consultandoStatusConfronto(codCHV); 
        String status = "";
        
        for(int i = 0; i < statusConf.size(); i++){
            if(rs.getInt("codInscricao") == statusConf.get(i).getCodInsc2()){
                   
                 status = "Iniciado";        
               }
        }
        
            c1.setCodInscricao(rs.getInt("codInscricao"));
           
            c1.setNomeAtleta(rs.getString("nomeAtleta"));
           
            c1.setNumRodada(rs.getInt("numRodada"));
            
            c1.setStatusConf(status);
             
            lista.add(c1);
        }
        rs.close();
        stmt.close();
        return lista;
}

public List<InscricaoPRT_bean> consultaVencedoresCHV1(int codCHV, int numRodada)throws SQLException{

    String sql = "select  * from ordemChave1 o inner join TB_inscricaoAtleta i\n" +
"on o.codInscricao = i.codInscricao\n" +
"inner join TB_atleta a on i.codAtleta = a.codAtleta\n" +
"and o.statusCBT = 'Vencedor' and o.codChvConf =? and o.numRodada  = ?";
           
           PreparedStatement stmt = this.conexao.prepareStatement(sql);
           
           stmt.setInt(1, codCHV);
           stmt.setInt(2, numRodada);
           
           ResultSet rs = stmt.executeQuery(); 
           
        List<InscricaoPRT_bean> lista = new ArrayList<>();
        while(rs.next()){
        InscricaoPRT_bean c1 = new InscricaoPRT_bean();
        
            c1.setCodInscricao(rs.getInt("codInscricao"));
           
            c1.setNomeAtleta(rs.getString("nomeAtleta"));
           
            
            lista.add(c1);
        }
        rs.close();
        stmt.close();
        return lista;
}

public List<InscricaoPRT_bean> consultaVencedoresCHV2(int codCHV, int numRodada)throws SQLException{

    String sql = "select  * from ordemChave2 o inner join TB_inscricaoAtleta i\n" +
"on o.codInscricao = i.codInscricao\n" +
"inner join TB_atleta a on i.codAtleta = a.codAtleta\n" +
"and o.statusCBT = 'Vencedor' and o.codChvConf =? and o.numRodada  = ?";
           
           PreparedStatement stmt = this.conexao.prepareStatement(sql);
           
           stmt.setInt(1, codCHV);
           stmt.setInt(2, numRodada);
           
           ResultSet rs = stmt.executeQuery(); 
           
        List<InscricaoPRT_bean> lista = new ArrayList<>();
        while(rs.next()){
        InscricaoPRT_bean c1 = new InscricaoPRT_bean();
        
            c1.setCodInscricao(rs.getInt("codInscricao"));
           
            c1.setNomeAtleta(rs.getString("nomeAtleta"));
           
            
            lista.add(c1);
        }
        rs.close();
        stmt.close();
        return lista;
}

public List<Relatorios_bean> relatorioVencedoresCHV1(int codEve,int codSeg, int codCat)throws SQLException{

     List<Relatorios_bean> lista = new ArrayList<>();
   
     
     // Caso não sejam selecionados os combos de segmento ou categoria, é realizada essa consulta
    if(codSeg == 0 && codCat ==0){
    String sql = "select * from TB_confrontos c inner join ordemChave1 o\n" +
"on c.codChvConf = o.codChvConf\n" +
"and c.qtdRodadasChvConf = o.numRodada\n" +
"and o.statusCBT = 'Vencedor'\n" +
"inner join TB_categoria x\n" +
"on c.codCategoria = x.codCategoria\n" +
"inner join TB_segmentos s\n" +
"on c.codSegmento = s.codSegmento\n" +
"inner join TB_inscricaoAtleta i\n" +
"on o.codInscricao = i.codInscricao\n" +
"inner join TB_atleta a\n" +
"on i.codAtleta = a.codAtleta\n" +
"inner join TB_evento e\n" +
"on e.codEvento = s.codEvento\n" +
"and s.codEvento = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
    
    ResultSet rs = stmt.executeQuery(); 
           
       
        while(rs.next()){
        Relatorios_bean c1 = new Relatorios_bean();
        
            c1.setNomeSegmento(rs.getString("nomeSegmento"));
            c1.setNomeCategoria(rs.getString("nomeCategoria"));
            c1.setNomeChvConf(rs.getString("nomeChvConf"));
            c1.setCodInscricao(rs.getInt("codInscricao"));
            c1.setCodAtleta(rs.getInt("codAtleta"));
            c1.setNomeAtleta(rs.getString("nomeAtleta"));
            
           
            
            lista.add(c1);
        }
        rs.close();
        stmt.close();
    
    }
    
    // Caso não seja selecionado o combos de categoria, é realizada essa consulta
    else if(codSeg != 0 && codCat ==0){
      
        String sql = "select * from TB_confrontos c inner join ordemChave1 o\n" +
"on c.codChvConf = o.codChvConf\n" +
"and c.qtdRodadasChvConf = o.numRodada\n" +
"and o.statusCBT = 'Vencedor'\n" +
"inner join TB_categoria x\n" +
"on c.codCategoria = x.codCategoria\n" +
"inner join TB_segmentos s\n" +
"on c.codSegmento = s.codSegmento\n" +
"inner join TB_inscricaoAtleta i\n" +
"on o.codInscricao = i.codInscricao\n" +
"inner join TB_atleta a\n" +
"on i.codAtleta = a.codAtleta\n" +
"inner join TB_evento e\n" +
"on e.codEvento = s.codEvento\n" +
"and s.codEvento = ?\n" +
"and s.codSegmento = ?";
        
           PreparedStatement stmt = this.conexao.prepareStatement(sql);
           stmt.setInt(1, codEve);
           stmt.setInt(2, codSeg);
          
           
           ResultSet rs = stmt.executeQuery(); 
           
       
        while(rs.next()){
        Relatorios_bean c1 = new Relatorios_bean();
        
           c1.setNomeSegmento(rs.getString("nomeSegmento"));
            c1.setNomeCategoria(rs.getString("nomeCategoria"));
            c1.setNomeChvConf(rs.getString("nomeChvConf"));
            c1.setCodInscricao(rs.getInt("codInscricao"));
            c1.setCodAtleta(rs.getInt("codAtleta"));
            c1.setNomeAtleta(rs.getString("nomeAtleta"));
           
            
            lista.add(c1);
        }
        rs.close();
        stmt.close();
    }
    // Caso não seja selecionado o combos de Segmento, é realizada essa consulta    
    else if(codSeg != 0 && codCat !=0){
        
        String sql = "select * from TB_confrontos c inner join ordemChave1 o\n" +
"on c.codChvConf = o.codChvConf\n" +
"and c.qtdRodadasChvConf = o.numRodada\n" +
"and o.statusCBT = 'Vencedor'\n" +
"inner join TB_categoria x\n" +
"on c.codCategoria = x.codCategoria\n" +
"inner join TB_segmentos s\n" +
"on c.codSegmento = s.codSegmento\n" +
"inner join TB_inscricaoAtleta i\n" +
"on o.codInscricao = i.codInscricao\n" +
"inner join TB_atleta a\n" +
"on i.codAtleta = a.codAtleta\n" +
"inner join TB_evento e\n" +
"on e.codEvento = s.codEvento\n" +
"and s.codEvento = ?\n" +
"and s.codSegmento = ?\n" +
"and x.codCategoria = ?";
        
           PreparedStatement stmt = this.conexao.prepareStatement(sql);
           stmt.setInt(1, codEve);
           stmt.setInt(2, codSeg);
           stmt.setInt(3, codCat);
           
           ResultSet rs = stmt.executeQuery(); 
           
       
        while(rs.next()){
        Relatorios_bean c1 = new Relatorios_bean();
        
           c1.setNomeSegmento(rs.getString("nomeSegmento"));
            c1.setNomeCategoria(rs.getString("nomeCategoria"));
            c1.setNomeChvConf(rs.getString("nomeChvConf"));
            c1.setCodInscricao(rs.getInt("codInscricao"));
            c1.setCodAtleta(rs.getInt("codAtleta"));
            c1.setNomeAtleta(rs.getString("nomeAtleta"));
           
            
            lista.add(c1);
        }
        rs.close();
        stmt.close();
    }
    
        return lista;
}

public List<Relatorios_bean> relatorioVencedoresCHV2(int codEve,int codSeg, int codCat)throws SQLException{

     List<Relatorios_bean> lista = new ArrayList<>();
   
     
     // Caso não sejam selecionados os combos de segmento ou categoria, é realizada essa consulta
    if(codSeg == 0 && codCat ==0){
        
    String sql = "select * from TB_confrontos c inner join ordemChave2 o\n" +
"on c.codChvConf = o.codChvConf\n" +
"and c.qtdRodadasChvConf = o.numRodada\n" +
"and o.statusCBT = 'Vencedor'\n" +
"inner join TB_categoria x\n" +
"on c.codCategoria = x.codCategoria\n" +
"inner join TB_segmentos s\n" +
"on c.codSegmento = s.codSegmento\n" +
"inner join TB_inscricaoAtleta i\n" +
"on o.codInscricao = i.codInscricao\n" +
"inner join TB_atleta a\n" +
"on i.codAtleta = a.codAtleta\n" +
"inner join TB_evento e\n" +
"on e.codEvento = s.codEvento\n" +
"and s.codEvento = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
    ResultSet rs = stmt.executeQuery(); 
           
       
        while(rs.next()){
        Relatorios_bean c1 = new Relatorios_bean();
        
            c1.setNomeSegmento(rs.getString("nomeSegmento"));
            c1.setNomeCategoria(rs.getString("nomeCategoria"));
            c1.setNomeChvConf(rs.getString("nomeChvConf"));
            c1.setCodInscricao(rs.getInt("codInscricao"));
            c1.setCodAtleta(rs.getInt("codAtleta"));
            c1.setNomeAtleta(rs.getString("nomeAtleta"));
            
           
            
            lista.add(c1);
        }
        rs.close();
        stmt.close();
    
    }
    
    // Caso não seja selecionado o combos de categoria, é realizada essa consulta
    else if(codSeg != 0 && codCat ==0){
        
        String sql = "select * from TB_confrontos c inner join ordemChave2 o\n" +
"on c.codChvConf = o.codChvConf\n" +
"and c.qtdRodadasChvConf = o.numRodada\n" +
"and o.statusCBT = 'Vencedor'\n" +
"inner join TB_categoria x\n" +
"on c.codCategoria = x.codCategoria\n" +
"inner join TB_segmentos s\n" +
"on c.codSegmento = s.codSegmento\n" +
"inner join TB_inscricaoAtleta i\n" +
"on o.codInscricao = i.codInscricao\n" +
"inner join TB_atleta a\n" +
"on i.codAtleta = a.codAtleta\n" +
"inner join TB_evento e\n" +
"on e.codEvento = s.codEvento\n" +
"and s.codEvento = ?\n" +
"and s.codSegmento = ?";
        
           PreparedStatement stmt = this.conexao.prepareStatement(sql);
           stmt.setInt(1, codEve);
           stmt.setInt(2, codSeg);
          
           
           ResultSet rs = stmt.executeQuery(); 
           
       
        while(rs.next()){
        Relatorios_bean c1 = new Relatorios_bean();
        
           c1.setNomeSegmento(rs.getString("nomeSegmento"));
            c1.setNomeCategoria(rs.getString("nomeCategoria"));
            c1.setNomeChvConf(rs.getString("nomeChvConf"));
            c1.setCodInscricao(rs.getInt("codInscricao"));
            c1.setCodAtleta(rs.getInt("codAtleta"));
            c1.setNomeAtleta(rs.getString("nomeAtleta"));
           
            
            lista.add(c1);
        }
        rs.close();
        stmt.close();
    }
    // Caso não seja selecionado o combos de Segmento, é realizada essa consulta    
    else if(codSeg != 0 && codCat !=0){
        System.out.println("consulta com cat"); 
        String sql = "select * from TB_confrontos c inner join ordemChave2 o\n" +
"on c.codChvConf = o.codChvConf\n" +
"and c.qtdRodadasChvConf = o.numRodada\n" +
"and o.statusCBT = 'Vencedor'\n" +
"inner join TB_categoria x\n" +
"on c.codCategoria = x.codCategoria\n" +
"inner join TB_segmentos s\n" +
"on c.codSegmento = s.codSegmento\n" +
"inner join TB_inscricaoAtleta i\n" +
"on o.codInscricao = i.codInscricao\n" +
"inner join TB_atleta a\n" +
"on i.codAtleta = a.codAtleta\n" +
"inner join TB_evento e\n" +
"on e.codEvento = s.codEvento\n" +
"and s.codEvento = ?\n" +
"and s.codSegmento = ?\n" +
"and x.codCategoria = ?";
        
           PreparedStatement stmt = this.conexao.prepareStatement(sql);
           stmt.setInt(1, codEve);
           stmt.setInt(2, codSeg);
           stmt.setInt(3, codCat);
           
           ResultSet rs = stmt.executeQuery(); 
           
       
        while(rs.next()){
        Relatorios_bean c1 = new Relatorios_bean();
        
           c1.setNomeSegmento(rs.getString("nomeSegmento"));
            c1.setNomeCategoria(rs.getString("nomeCategoria"));
            c1.setNomeChvConf(rs.getString("nomeChvConf"));
            c1.setCodInscricao(rs.getInt("codInscricao"));
            c1.setCodAtleta(rs.getInt("codAtleta"));
            c1.setNomeAtleta(rs.getString("nomeAtleta"));
           
            
            lista.add(c1);
        }
        rs.close();
        stmt.close();
    }
    
        return lista;
}

public int estruCBT(Arbitros_bean bean)throws SQLException{
    int codConf = 0;
    
    
    
    String sql = "INSERT INTO [dbo].[TB_estruCBT]\n" +
"           ([codChvConf]\n" +
"           ,[numRodada]\n" +
"           ,[codInsc1]\n" +
"           ,[codInsc2]\n" +
"           ,[statusConf]"
            + ",confrmsFnlz)\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?"
         + ",0)";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, bean.getCodChvConf());
    stmt.setInt(2, bean.getNumRodada());
    stmt.setInt(3, bean.getCodInsc1());
    stmt.setInt(4, bean.getCodInsc2());
    stmt.setString(5, bean.getStatusConf());
    
    stmt.execute();
    
    String sql2 = "select max(codConf)codConf from TB_estruCBT";
    
    PreparedStatement stmt2 = this.conexao.prepareStatement(sql2);
    ResultSet rs = stmt2.executeQuery(); 
           
        while(rs.next()){
           
            codConf = rs.getInt("codConf");
            
        }
    rs.close();
    stmt.close();
    
    return codConf;
}

public List<Arbitros_bean> arbCBT(int codArb, int codConf)throws SQLException{
    
    String sql = "INSERT INTO [dbo].[TB_arbCBT]\n" +
"           ([codConf]\n" +
"           ,[codArbitro])\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?)";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
           stmt.setInt(1, codConf);
           stmt.setInt(2, codArb);
           
           stmt.execute();
           
           String sql2 = "select * from TB_arbCBT where codConf = ?";
           PreparedStatement stmt2 = this.conexao.prepareStatement(sql2);
           stmt2.setInt(1, codConf);
           ResultSet rs = stmt2.executeQuery(); 
           ArrayList<Arbitros_bean> lista = new ArrayList<>();
           
            while(rs.next()){
            Arbitros_bean c1 = new Arbitros_bean();
            c1.setCodConfArb(rs.getInt("codConfArb"));
            c1.setCodArbitro(rs.getInt("codArbitro"));
            lista.add(c1);
        }
           
           rs.close();
           stmt.close();
           return lista;
}

public void pontArbCBT(int codConfArb,int codPontua)throws SQLException{
    String sql = "INSERT INTO [dbo].[TB_pontArbCBT]\n" +
"           ([codConfArb]\n" +
"           ,[codPontua])\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?)";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
           stmt.setInt(1, codConfArb);
           stmt.setInt(2, codPontua);
           stmt.execute();
           stmt.close();
}


public List<Arbitros_bean> consultandoStatusConfronto(int codChv)throws SQLException{
    
    String sql = "select * from TB_estruCBT where statusConf = 'Iniciado' and codChvConf = ?";
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codChv);
    ResultSet rs = stmt.executeQuery(); 
               
    ArrayList<Arbitros_bean> lista = new ArrayList<>();
    while(rs.next()){
        Arbitros_bean c1 = new Arbitros_bean();
        
        c1.setCodInsc1(rs.getInt("codInsc1"));
        c1.setCodInsc2(rs.getInt("codInsc2"));
        
        lista.add(c1);
    }
    rs.close();
    stmt.close();
    
    return lista;
}
/////////////////////////////////// Arbitros /////////////////////////////////////
public List<Arbitros_bean> consultaEstruConfArb(int codArb)throws SQLException{
    
    String sql = "select a.codChvConf, a.codConf, a.codInsc1,nomeAtleta1 = x.nomeAtleta,a.codInsc2,nomeAtleta2 = e.nomeAtleta, a.statusConf from tb_estruCBT a inner join tb_arbCBT b\n" +
"on a.codConf = b.codConf\n" +
"inner join TB_inscricaoAtleta i\n" +
"on i.codInscricao = a.codInsc1 \n" +
"inner join TB_inscricaoAtleta f\n" +
"on f.codInscricao = a.codInsc2 \n" +
"inner join TB_atleta x\n" +
"on x.codAtleta = i.codAtleta\n" +
"inner join TB_atleta e\n" +
"on f.codAtleta = e.codAtleta\n" +
"and b.codArbitro = ?\n" +
"and a.statusConf = 'Iniciado'";
    
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

public void inserirMetricasAtRestantes(int codChv, int codInsc1, int codInsc2, int codConf)throws SQLException{
    String sql = "update TB_metricasCBT set parteDoCorpoNGT = (select parteDoCorpoPST from TB_metricasCBT where codChvConf = ? and codInscricao = ?\n" +
"and numRodada = (select max(numRodada)numRodada from TB_metricasCBT where codChvConf = ? and codInscricao = ?)) \n" +
",totPontosNegativos = (select totPontosPositivos from TB_metricasCBT where codChvConf = ? and codInscricao = ?\n" +
"and numRodada = (select max(numRodada)numRodada from TB_metricasCBT where codChvConf = ? and codInscricao = ?)) \n" +
"where codChvConf = ? and codInscricao = ? and numRodada = (select max(numRodada)numRodada from TB_metricasCBT where codChvConf = ? and codInscricao = ?)";
   
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
           stmt.setInt(1, codChv);
           stmt.setInt(2, codInsc1);
           stmt.setInt(3, codChv);
           stmt.setInt(4, codInsc1);
           stmt.setInt(5, codChv);
           stmt.setInt(6, codInsc1);
           stmt.setInt(7, codChv);
           stmt.setInt(8, codInsc1);
           stmt.setInt(9, codChv);
           stmt.setInt(10, codInsc2);
           stmt.setInt(11, codChv);
           stmt.setInt(12, codInsc2);
           
           stmt.execute();
           stmt.close();
           
      String sql2 = "update TB_metricasAT set totPontosNegativos  += (select totPontosPositivos from TB_metricasAT where codChvConf = ? and codInscricao = ?) \n" +
"where codChvConf = ? and codInscricao = ?";
    
    PreparedStatement stmt2 = this.conexao.prepareStatement(sql2);
           stmt2.setInt(1, codChv);
           stmt2.setInt(2, codInsc1);
           stmt2.setInt(3, codChv);
           stmt2.setInt(4, codInsc2);     
           
           stmt2.execute();
           stmt2.close();
           
           String sql3 = "update TB_metricasCBT set parteDoCorpoNGT = (select parteDoCorpoPST from TB_metricasCBT where codChvConf = ? and codInscricao = ?\n" +
"and numRodada = (select max(numRodada)numRodada from TB_metricasCBT where codChvConf = ? and codInscricao = ?)) \n" +
",totPontosNegativos = (select totPontosPositivos from TB_metricasCBT where codChvConf = ? and codInscricao = ?\n" +
"and numRodada = (select max(numRodada)numRodada from TB_metricasCBT where codChvConf = ? and codInscricao = ?)) \n" +
"where codChvConf = ? and codInscricao = ? and numRodada = (select max(numRodada)numRodada from TB_metricasCBT where codChvConf = ? and codInscricao = ?)";
    
    PreparedStatement stmt3 = this.conexao.prepareStatement(sql3);
           stmt3.setInt(1, codChv);
           stmt3.setInt(2, codInsc2);
           stmt3.setInt(3, codChv);
           stmt3.setInt(4, codInsc2);
           stmt3.setInt(5, codChv);
           stmt3.setInt(6, codInsc2);
           stmt3.setInt(7, codChv);
           stmt3.setInt(8, codInsc2);
           stmt3.setInt(9, codChv);
           stmt3.setInt(10, codInsc1);
           stmt3.setInt(11, codChv);
           stmt3.setInt(12, codInsc1);
           
           stmt3.execute();
           stmt3.close();
           
      String sql4 = "update TB_metricasAT set totPontosNegativos  += (select totPontosPositivos from TB_metricasAT where codChvConf = ? and codInscricao = ?) \n" +
"where codChvConf = ? and codInscricao = ?";
    
    PreparedStatement stmt4 = this.conexao.prepareStatement(sql4);
           stmt4.setInt(1, codChv);
           stmt4.setInt(2, codInsc2);
           stmt4.setInt(3, codChv);
           stmt4.setInt(4, codInsc1);     
           
           stmt4.execute();
           stmt4.close();
           
      String sql5 = "update TB_acertosCBT set\n" +
"acertosCabecaNgt += (select acertosCabecaPst from TB_acertosCBT where codConf = ? and codInsc = ?)\n" +
",acertosBracosngt += (select acertosBracosPst from TB_acertosCBT where codConf = ? and codInsc = ?)\n" +
",acertosTroncoNgt += (select acertosTroncoPst from TB_acertosCBT where codConf = ? and codInsc = ?)\n" +
",acertosPernasNgt += (select acertosPernasPst from TB_acertosCBT where codConf = ? and codInsc = ?)\n" +
",acertosQuedagemNgt += (select acertosQuedagemPst from TB_acertosCBT where codConf = ? and codInsc = ?)\n" +
"where codConf = ? and codInsc = ?";
           
      
      PreparedStatement stmt5 = this.conexao.prepareStatement(sql5);
           stmt5.setInt(1, codConf);
           stmt5.setInt(2, codInsc1);
           stmt5.setInt(3, codConf);
           stmt5.setInt(4, codInsc1);
           stmt5.setInt(5, codConf);
           stmt5.setInt(6, codInsc1);
           stmt5.setInt(7, codConf);
           stmt5.setInt(8, codInsc1);
           stmt5.setInt(9, codConf);
           stmt5.setInt(10, codInsc1);
           stmt5.setInt(11, codConf);
           stmt5.setInt(12, codInsc2);
           
           stmt5.execute();
           stmt5.close();
           
           String sql6 = "update TB_acertosCBT set\n" +
"acertosCabecaNgt += (select acertosCabecaPst from TB_acertosCBT where codConf = ? and codInsc = ?)\n" +
",acertosBracosngt += (select acertosBracosPst from TB_acertosCBT where codConf = ? and codInsc = ?)\n" +
",acertosTroncoNgt += (select acertosTroncoPst from TB_acertosCBT where codConf = ? and codInsc = ?)\n" +
",acertosPernasNgt += (select acertosPernasPst from TB_acertosCBT where codConf = ? and codInsc = ?)\n" +
",acertosQuedagemNgt += (select acertosQuedagemPst from TB_acertosCBT where codConf = ? and codInsc = ?)\n" +
"where codConf = ? and codInsc = ?";
           
      
      PreparedStatement stmt6 = this.conexao.prepareStatement(sql6);
           stmt6.setInt(1, codConf);
           stmt6.setInt(2, codInsc2);
           stmt6.setInt(3, codConf);
           stmt6.setInt(4, codInsc2);
           stmt6.setInt(5, codConf);
           stmt6.setInt(6, codInsc2);
           stmt6.setInt(7, codConf);
           stmt6.setInt(8, codInsc2);
           stmt6.setInt(9, codConf);
           stmt6.setInt(10, codInsc2);
           stmt6.setInt(11, codConf);
           stmt6.setInt(12, codInsc1);
           
           stmt6.execute();
           stmt6.close();
}

public void adicionaInicioConf(int codConf, int codInsc1, int codInsc2)throws SQLException{
    String sql = "INSERT INTO [dbo].[TB_iniciarCBT]\n" +
"           ([codConf]\n" +
"           ,[codInsc1]\n" +
"           ,[statusInicio1]\n" +
"           ,[codInsc2]\n" +
"           ,[statusInicio2]\n" +
"           ,[confrmsInicio])\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?\n" +
"           ,null\n" +
"           ,?\n" +
"           ,null\n" +
"           ,0)";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
           stmt.setInt(1, codConf);
           stmt.setInt(2, codInsc1);
           stmt.setInt(3, codInsc2);
           
           stmt.execute();
           stmt.close();
}

public void adicionaValoresPontCBT(
     int codConf
    ,int codInsc
    ,int cabecaTotPst
    ,int troncoTotPst
    ,int quedagemTotPst
    ,int pernasTotPst
    ,int bracosTotPst
    
    ,int cabecaTotNgt 
    ,int troncoTotNgt
    ,int quedagemTotNgt
    ,int pernasTotNgt
    ,int bracosTotNgt
    )throws SQLException{
    String sql = "INSERT INTO [dbo].[TB_acertosCBT]\n" +
"           ([codConf]\n" +
"           ,[codInsc]\n" +
"           ,[acertosCabecaPst]\n" +
"           ,[acertosBracosPst]\n" +
"           ,[acertosTroncoPst]\n" +
"           ,[acertosPernasPst]\n" +
"           ,[acertosQuedagemPst]\n" +
"           ,[acertosCabecaNgt]\n" +
"           ,[acertosBracosNgt]\n" +
"           ,[acertosTroncoNgt]\n" +
"           ,[acertosPernasNgt]\n" +
"           ,[acertosQuedagemNgt])\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?)";
    
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
           stmt.setInt(1, codConf);
           stmt.setInt(2, codInsc);
           stmt.setInt(3, cabecaTotPst);
           stmt.setInt(4, bracosTotPst);
           stmt.setInt(5, troncoTotPst);
           stmt.setInt(6, pernasTotPst);
           stmt.setInt(7, quedagemTotPst);
           stmt.setInt(8, cabecaTotNgt);
           stmt.setInt(9, bracosTotNgt);
           stmt.setInt(10, troncoTotNgt);
           stmt.setInt(11, pernasTotNgt);
           stmt.setInt(12, quedagemTotNgt);
           
           stmt.execute();
           stmt.close();
}

}
