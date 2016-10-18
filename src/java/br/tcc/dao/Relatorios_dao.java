
package br.tcc.dao;

import Conexao.ConectaBanco;
import br.tcc.bean.Relatorios_bean;
import java.sql.CallableStatement;
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
public class Relatorios_dao {
private Connection conexao; 

    public Relatorios_dao()throws SQLException{
        this.conexao = new ConectaBanco().getConexao();
    }
    
        public List<Relatorios_bean>chavesNaoFinalizadas(int codEve) throws SQLException{
        
    String sql = "select quantidadeCHVs = count(c.codChvConf) from TB_evento e inner join TB_segmentos s\n" +
"on e.codEvento = s.codEvento\n" +
"inner join TB_confrontos c\n" +
"on s.codSegmento = c.codSegmento\n" +
"and c.statusChvConf = 'Aberto'\n" +
"and e.codEvento = ?";
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.setInt(1, codEve);
        
       
        ResultSet rs = stmt.executeQuery(); 
        
        ArrayList<Relatorios_bean> minhaLista = new ArrayList<Relatorios_bean>();
        
        
        while(rs.next()){
            Relatorios_bean c1 = new Relatorios_bean();
            c1.setQtdChvs(rs.getInt("quantidadeCHVs"));
            minhaLista.add(c1);
        }
        rs.close();
        stmt.close();
        return minhaLista;
    }
    
    public List<Relatorios_bean>totalChaves(int codEve) throws SQLException{
        
    String sql = "select numChvs = COUNT(c.codChvConf) from TB_evento e inner join TB_segmentos s\n" +
"on e.codEvento = s.codEvento\n" +
"inner join TB_confrontos c\n" +
"on s.codSegmento = c.codSegmento\n" +
"and e.codEvento = ?";
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
        
        
       
        ResultSet rs = stmt.executeQuery(); 
        
        ArrayList<Relatorios_bean> minhaLista = new ArrayList<Relatorios_bean>();
        
        
        while(rs.next()){
            Relatorios_bean c1 = new Relatorios_bean();
            c1.setQtdChvs(rs.getInt("numChvs"));
            minhaLista.add(c1);
        }
        rs.close();
        stmt.close();
        return minhaLista;
    }    
        
    public List<Relatorios_bean>topsTorneio(int codEve, int codSeg, String finalCBT) throws SQLException{
         ArrayList<Relatorios_bean> minhaLista = new ArrayList<Relatorios_bean>();
         
    if(codSeg == 0 && finalCBT.equals("")){    
    String sql = "select e.codEvento, e.nomeEvento, a.nomeAtleta, i.codInscricao, i.codAtleta, m.totPontosPositivos, m.totPontosNegativos,c.finalCBT, total = count(c.finalCBT)\n" +
"from TB_metricasAT m inner join TB_inscricaoAtleta i\n" +
"on i.codInscricao = m.codInscricao\n" +
"inner join TB_atleta a\n" +
"on a.codAtleta = i.codAtleta\n" +
"inner join TB_segmentos s\n" +
"on s.codSegmento = i.codSegmento\n" +
"inner join TB_evento e\n" +
"on e.codEvento = s.codEvento\n" +
"inner join TB_metricasCBT c\n" +
"on i.codInscricao = c.codInscricao\n" +
"and e.codEvento = ? \n" +
"group by e.codEvento, e.nomeEvento, a.nomeAtleta, i.codInscricao, i.codAtleta, m.totPontosPositivos, m.totPontosNegativos, c.finalCBT\n" +
"order by finalCBT,totPontosPositivos desc";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
        
       
        ResultSet rs = stmt.executeQuery(); 
        
       while(rs.next()){
            Relatorios_bean c1 = new Relatorios_bean();
            c1.setCodEvento(rs.getInt("codEvento"));
            c1.setNomeEvento(rs.getString("nomeEvento"));
            c1.setNomeAtleta(rs.getString("nomeAtleta"));
            c1.setCodInscricao(rs.getInt("codInscricao"));
            c1.setCodAtleta(rs.getInt("codAtleta"));
            c1.setTotPontosPositivos(rs.getInt("totPontosPositivos"));
            c1.setTotPontosNegativos(rs.getInt("totPontosNegativos"));
            c1.setFinalCBT(rs.getString("finalCBT"));
            c1.setTotais(rs.getInt("total"));
            minhaLista.add(c1);
        }
        rs.close();
        stmt.close();
    }
    
    
    else if(codSeg != 0 && finalCBT.equals("")){    
    String sql = "select e.codEvento, e.nomeEvento, a.nomeAtleta, i.codInscricao, i.codAtleta, m.totPontosPositivos, m.totPontosNegativos,c.finalCBT, total = count(c.finalCBT)\n" +
"from TB_metricasAT m inner join TB_inscricaoAtleta i\n" +
"on i.codInscricao = m.codInscricao\n" +
"inner join TB_atleta a\n" +
"on a.codAtleta = i.codAtleta\n" +
"inner join TB_segmentos s\n" +
"on s.codSegmento = i.codSegmento\n" +
"inner join TB_evento e\n" +
"on e.codEvento = s.codEvento\n" +
"inner join TB_metricasCBT c\n" +
"on i.codInscricao = c.codInscricao\n" +
"and e.codEvento = ? \n" +
"and s.codSegmento = ?\n" +
"group by e.codEvento, e.nomeEvento, a.nomeAtleta, i.codInscricao, i.codAtleta, m.totPontosPositivos, m.totPontosNegativos, c.finalCBT\n" +
"order by finalCBT,totPontosPositivos desc";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
    stmt.setInt(2, codSeg);    
       
        ResultSet rs = stmt.executeQuery(); 
        
       while(rs.next()){
            Relatorios_bean c1 = new Relatorios_bean();
            c1.setCodEvento(rs.getInt("codEvento"));
            c1.setNomeEvento(rs.getString("nomeEvento"));
            c1.setNomeAtleta(rs.getString("nomeAtleta"));
            c1.setCodInscricao(rs.getInt("codInscricao"));
            c1.setCodAtleta(rs.getInt("codAtleta"));
            c1.setTotPontosPositivos(rs.getInt("totPontosPositivos"));
            c1.setTotPontosNegativos(rs.getInt("totPontosNegativos"));
            c1.setFinalCBT(rs.getString("finalCBT"));
            c1.setTotais(rs.getInt("total"));
            minhaLista.add(c1);
        }
        rs.close();
        stmt.close();
    }
    
    
    else if(codSeg == 0 && !finalCBT.equals("")){    
    String sql = "select e.codEvento, e.nomeEvento, a.nomeAtleta, i.codInscricao, i.codAtleta, m.totPontosPositivos, m.totPontosNegativos,c.finalCBT, total = count(c.finalCBT)\n" +
"from TB_metricasAT m inner join TB_inscricaoAtleta i\n" +
"on i.codInscricao = m.codInscricao\n" +
"inner join TB_atleta a\n" +
"on a.codAtleta = i.codAtleta\n" +
"inner join TB_segmentos s\n" +
"on s.codSegmento = i.codSegmento\n" +
"inner join TB_evento e\n" +
"on e.codEvento = s.codEvento\n" +
"inner join TB_metricasCBT c\n" +
"on i.codInscricao = c.codInscricao\n" +
"and e.codEvento = ? \n" +
"and c.finalCBT = ?\n" +
"group by e.codEvento, e.nomeEvento, a.nomeAtleta, i.codInscricao, i.codAtleta, m.totPontosPositivos, m.totPontosNegativos, c.finalCBT\n" +
"order by finalCBT,totPontosPositivos desc";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
    stmt.setString(2, finalCBT);    
       
        ResultSet rs = stmt.executeQuery(); 
        
       while(rs.next()){
            Relatorios_bean c1 = new Relatorios_bean();
            c1.setCodEvento(rs.getInt("codEvento"));
            c1.setNomeEvento(rs.getString("nomeEvento"));
            c1.setNomeAtleta(rs.getString("nomeAtleta"));
            c1.setCodInscricao(rs.getInt("codInscricao"));
            c1.setCodAtleta(rs.getInt("codAtleta"));
            c1.setTotPontosPositivos(rs.getInt("totPontosPositivos"));
            c1.setTotPontosNegativos(rs.getInt("totPontosNegativos"));
            c1.setFinalCBT(rs.getString("finalCBT"));
            c1.setTotais(rs.getInt("total"));
            minhaLista.add(c1);
        }
        rs.close();
        stmt.close();
    }
    
    else{
    String sql = "select e.codEvento, e.nomeEvento, a.nomeAtleta, i.codInscricao, i.codAtleta, m.totPontosPositivos, m.totPontosNegativos,c.finalCBT, total = count(c.finalCBT)\n" +
"from TB_metricasAT m inner join TB_inscricaoAtleta i\n" +
"on i.codInscricao = m.codInscricao\n" +
"inner join TB_atleta a\n" +
"on a.codAtleta = i.codAtleta\n" +
"inner join TB_segmentos s\n" +
"on s.codSegmento = i.codSegmento\n" +
"inner join TB_evento e\n" +
"on e.codEvento = s.codEvento\n" +
"inner join TB_metricasCBT c\n" +
"on i.codInscricao = c.codInscricao\n" +
"and e.codEvento = ? \n" +
"and s.codSegmento = ?\n" +
"and c.finalCBT = ?\n" +
"group by e.codEvento, e.nomeEvento, a.nomeAtleta, i.codInscricao, i.codAtleta, m.totPontosPositivos, m.totPontosNegativos, c.finalCBT\n" +
"order by finalCBT,totPontosPositivos desc";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
    stmt.setInt(2, codSeg);
    stmt.setString(3, finalCBT);    
       
        ResultSet rs = stmt.executeQuery(); 
        
       while(rs.next()){
            Relatorios_bean c1 = new Relatorios_bean();
            c1.setCodEvento(rs.getInt("codEvento"));
            c1.setNomeEvento(rs.getString("nomeEvento"));
            c1.setNomeAtleta(rs.getString("nomeAtleta"));
            c1.setCodInscricao(rs.getInt("codInscricao"));
            c1.setCodAtleta(rs.getInt("codAtleta"));
            c1.setTotPontosPositivos(rs.getInt("totPontosPositivos"));
            c1.setTotPontosNegativos(rs.getInt("totPontosNegativos"));
            c1.setFinalCBT(rs.getString("finalCBT"));
            c1.setTotais(rs.getInt("total"));
            minhaLista.add(c1);
        }
        rs.close();
        stmt.close();    
    }
    
        return minhaLista;
    }    
    
    public int metricasFinalizaCBTFinalizacoes(int codEve) throws SQLException{
        
         int totFinaliza = 0;
   
    String sql = "select numFinaliza = COUNT(finalCBT) from TB_metricasCBT m inner join TB_confrontos c\n" +
"on m.codChvConf = c.codChvConf\n" +
"inner join TB_segmentos s\n" +
"on c.codSegmento = s.codSegmento\n" +
"and s.codEvento = ?\n" +
"and finalCBT = 'Finalização'";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
    
       
        ResultSet rs = stmt.executeQuery(); 
        
         
       while(rs.next()){
         
           
            totFinaliza = rs.getInt("numFinaliza");
            
        }
        rs.close();
        stmt.close();    
    
        return totFinaliza;
    }
    
    public int metricasFinalizaCBTPontos(int codEve) throws SQLException{
        
         int totPontos = 0;
   
    String sql = "select numPontos = COUNT(finalCBT) from TB_metricasCBT m inner join TB_confrontos c\n" +
"on m.codChvConf = c.codChvConf\n" +
"inner join TB_segmentos s\n" +
"on c.codSegmento = s.codSegmento\n" +
"and s.codEvento = ?\n" +
"and finalCBT = 'Pontos'";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
    
       
        ResultSet rs = stmt.executeQuery(); 
        
         
       while(rs.next()){
         
           
            totPontos = rs.getInt("numPontos");
            
        }
        rs.close();
        stmt.close();    
    
        return totPontos;
    }
    
    public int metricasFinalizaCBTNocaute(int codEve) throws SQLException{
        
         int totNocaute = 0;
   
    String sql = "select numNocaute = COUNT(finalCBT) from TB_metricasCBT m inner join TB_confrontos c\n" +
"on m.codChvConf = c.codChvConf\n" +
"inner join TB_segmentos s\n" +
"on c.codSegmento = s.codSegmento\n" +
"and s.codEvento = ?\n" +
"and finalCBT = 'Nocaute'";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
    
       
        ResultSet rs = stmt.executeQuery(); 
        
         
       while(rs.next()){
         
           
            totNocaute = rs.getInt("numNocaute");
            
        }
        rs.close();
        stmt.close();    
    
        return totNocaute;
    }
    
    public List<Relatorios_bean> consultaMetricasPST(int codAtleta, int codEve)throws SQLException{
        
        String sql = "exec p_consultaMetricasPstEve ?,?";
        CallableStatement  stmt = this.conexao.prepareCall(sql);
        stmt.setInt(1, codAtleta);
        stmt.setInt(2, codEve);
       

        ArrayList<Relatorios_bean> lista = new ArrayList<>();
        

           ResultSet rs = stmt.executeQuery();
        while(rs.next()){
        
         Relatorios_bean c1 = new Relatorios_bean();
         
         //c1.setCodEvento(rs.getInt("codEvento"));
         c1.setNomeEvento(rs.getString("nomeEvento"));
         c1.setNomeSegmento(rs.getString("nomeSegmento"));
         c1.setNomeChvConf(rs.getString("nomeChvConf"));
         c1.setCodInscricao(rs.getInt("codInscricao"));
         c1.setCodAtleta(rs.getInt("codAtleta"));
         c1.setTotPontosPositivos(rs.getInt("totPontosPositivos"));
         c1.setParteDoCorpoPST(rs.getString("parteDoCorpoPST"));
         c1.setQtdCbtGeral(rs.getInt("qtdCbtGeral"));
         c1.setTotalVits(rs.getInt("vits"));
         
         lista.add(c1);
        }
        rs.close();
        
         stmt.close();    
        return lista;
    }
    
    
    public List<Relatorios_bean> consultaMetricasNGT(int codAtleta, int codEve)throws SQLException{
        
        String sql = "exec p_consultaMetricasNgtEve ?,?";
        CallableStatement  stmt = this.conexao.prepareCall(sql);
        stmt.setInt(1, codAtleta);
        stmt.setInt(2, codEve);
       

        ArrayList<Relatorios_bean> lista = new ArrayList<>();
        

           ResultSet rs = stmt.executeQuery();
        while(rs.next()){
        
         Relatorios_bean c1 = new Relatorios_bean();
         
         //c1.setCodEvento(rs.getInt("codEvento"));
         c1.setNomeEvento(rs.getString("nomeEvento"));
         c1.setNomeSegmento(rs.getString("nomeSegmento"));
         c1.setNomeChvConf(rs.getString("nomeChvConf"));
         c1.setCodInscricao(rs.getInt("codInscricao"));
         c1.setCodAtleta(rs.getInt("codAtleta"));
         c1.setTotPontosNegativos(rs.getInt("totPontosNegativos"));
         c1.setParteDoCorpoNGT(rs.getString("parteDoCorpoNGT"));
         c1.setQtdCbtGeral(rs.getInt("qtdCbtGeral"));
         c1.setTotalDerr(rs.getInt("qtdDerr"));
         
         lista.add(c1);
        }
        rs.close();
        
         stmt.close();    
        return lista;
    }
    
    public List<Relatorios_bean> consultaComentsArbPst(int codAtleta, int codEve)throws SQLException{
        
        String sql = "exec p_consultaComentsArbPst ?,?";
        CallableStatement  stmt = this.conexao.prepareCall(sql);
        stmt.setInt(1, codAtleta);
        stmt.setInt(2, codEve);
       

        ArrayList<Relatorios_bean> lista = new ArrayList<>();
        

           ResultSet rs = stmt.executeQuery();
        while(rs.next()){
        
         Relatorios_bean c1 = new Relatorios_bean();
         
         c1.setNomeSegmento(rs.getString("nomeSegmento"));
         c1.setNomeChvConf(rs.getString("nomeChvConf"));
         c1.setNumRodada(rs.getInt("numRodada"));
         c1.setDescricaoCBT(rs.getString("descricaoCBT"));
         
         lista.add(c1);
        }
        rs.close();
        
         stmt.close();    
        return lista;
    }
    
    public List<Relatorios_bean> consultaComentsArbNgt(int codAtleta, int codEve)throws SQLException{
        
        String sql = "exec p_consultaComentsArbNgt ?,?";
        CallableStatement  stmt = this.conexao.prepareCall(sql);
        stmt.setInt(1, codAtleta);
        stmt.setInt(2, codEve);
       

        ArrayList<Relatorios_bean> lista = new ArrayList<>();
        

           ResultSet rs = stmt.executeQuery();
        while(rs.next()){
        
         Relatorios_bean c1 = new Relatorios_bean();
         
         c1.setNomeSegmento(rs.getString("nomeSegmento"));
         c1.setNomeChvConf(rs.getString("nomeChvConf"));
         c1.setNumRodada(rs.getInt("numRodada"));
         c1.setDescricaoCBT(rs.getString("descricaoCBT"));
         
         lista.add(c1);
        }
        rs.close();
        
         stmt.close();    
        return lista;
    }
    
    public int graficoTotalNgtCabeca(int codEve, int codAtleta) throws SQLException{
        
         int tot = 0;
   
    String sql = "select Sum(acertosCabecaNgt)parteDoCorpoNGT from TB_acertosCBT a inner join TB_estruCBT e\n" +
"on e.codConf = a.codConf\n" +
"inner join TB_confrontos c\n" +
"on e.codChvConf = c.codChvConf\n" +
"inner join TB_segmentos s\n" +
"on s.codSegmento = c.codSegmento\n" +
"inner join TB_inscricaoAtleta i\n" +
"on i.codInscricao = a.codInsc\n" +
"and s.codEvento = ?\n" +
"and i.codAtleta = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
    stmt.setInt(2, codAtleta);
    
       
        ResultSet rs = stmt.executeQuery(); 
        
         
       while(rs.next()){
         
           
            tot = rs.getInt("parteDoCorpoNGT");
            
        }
        rs.close();
        stmt.close();    
    
        return tot;
    }
    
    public int graficoTotalNgtTronco(int codEve, int codAtleta) throws SQLException{
        
         int tot = 0;
   
    String sql = "select SUM(acertosTroncoNgt)parteDoCorpoNGT from TB_acertosCBT a inner join TB_estruCBT e\n" +
"on e.codConf = a.codConf\n" +
"inner join TB_confrontos c\n" +
"on e.codChvConf = c.codChvConf\n" +
"inner join TB_segmentos s\n" +
"on s.codSegmento = c.codSegmento\n" +
"inner join TB_inscricaoAtleta i\n" +
"on i.codInscricao = a.codInsc\n" +
"and s.codEvento = ?\n" +
"and i.codAtleta = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
    stmt.setInt(2, codAtleta);
    
       
        ResultSet rs = stmt.executeQuery(); 
        
         
       while(rs.next()){
         
           
            tot = rs.getInt("parteDoCorpoNGT");
            
        }
        rs.close();
        stmt.close();    
    
        return tot;
    }
    
    public int graficoTotalNgtBracos(int codEve, int codAtleta) throws SQLException{
        
         int tot = 0;
   
    String sql = "select SUM(acertosBracosNgt)parteDoCorpoNGT from TB_acertosCBT a inner join TB_estruCBT e\n" +
"on e.codConf = a.codConf\n" +
"inner join TB_confrontos c\n" +
"on e.codChvConf = c.codChvConf\n" +
"inner join TB_segmentos s\n" +
"on s.codSegmento = c.codSegmento\n" +
"inner join TB_inscricaoAtleta i\n" +
"on i.codInscricao = a.codInsc\n" +
"and s.codEvento = ?\n" +
"and i.codAtleta = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
    stmt.setInt(2, codAtleta);
    
       
        ResultSet rs = stmt.executeQuery(); 
        
         
       while(rs.next()){
         
           
            tot = rs.getInt("parteDoCorpoNGT");
            
        }
        rs.close();
        stmt.close();    
    
        return tot;
    }
    
    public int graficoTotalNgtPernas(int codEve, int codAtleta) throws SQLException{
        
         int tot = 0;
   
    String sql = "select SUM(acertosPernasNgt)parteDoCorpoNGT from TB_acertosCBT a inner join TB_estruCBT e\n" +
"on e.codConf = a.codConf\n" +
"inner join TB_confrontos c\n" +
"on e.codChvConf = c.codChvConf\n" +
"inner join TB_segmentos s\n" +
"on s.codSegmento = c.codSegmento\n" +
"inner join TB_inscricaoAtleta i\n" +
"on i.codInscricao = a.codInsc\n" +
"and s.codEvento = ?\n" +
"and i.codAtleta = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
    stmt.setInt(2, codAtleta);
    
       
        ResultSet rs = stmt.executeQuery(); 
        
         
       while(rs.next()){
         
           
            tot = rs.getInt("parteDoCorpoNGT");
            
        }
        rs.close();
        stmt.close();    
    
        return tot;
    }
    
    public int graficoTotalNgtQuedagem(int codEve, int codAtleta) throws SQLException{
        
         int tot = 0;
   
    String sql = "select SUM(acertosQuedagemNgt)parteDoCorpoNGT from TB_acertosCBT a inner join TB_estruCBT e\n" +
"on e.codConf = a.codConf\n" +
"inner join TB_confrontos c\n" +
"on e.codChvConf = c.codChvConf\n" +
"inner join TB_segmentos s\n" +
"on s.codSegmento = c.codSegmento\n" +
"inner join TB_inscricaoAtleta i\n" +
"on i.codInscricao = a.codInsc\n" +
"and s.codEvento = ?\n" +
"and i.codAtleta = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
    stmt.setInt(2, codAtleta);
    
       
        ResultSet rs = stmt.executeQuery(); 
        
           
       while(rs.next()){
         
           
            tot = rs.getInt("parteDoCorpoNGT");
            
        }
        rs.close();
        stmt.close();    
    
        return tot;
    }
    
    
    public int graficoTotalPstCabeca(int codEve, int codAtleta) throws SQLException{
        
         int tot = 0;
   
    String sql = "select Sum(acertosCabecaPst)parteDoCorpoPST from TB_acertosCBT a inner join TB_estruCBT e\n" +
"on e.codConf = a.codConf\n" +
"inner join TB_confrontos c\n" +
"on e.codChvConf = c.codChvConf\n" +
"inner join TB_segmentos s\n" +
"on s.codSegmento = c.codSegmento\n" +
"inner join TB_inscricaoAtleta i\n" +
"on i.codInscricao = a.codInsc\n" +
"and s.codEvento = ?\n" +
"and i.codAtleta = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
    stmt.setInt(2, codAtleta);
    
       
        ResultSet rs = stmt.executeQuery(); 
        
         
       while(rs.next()){
         
           
            tot = rs.getInt("parteDoCorpoPST");
            
        }
        rs.close();
        stmt.close();    
    
        return tot;
    }
    
    public int graficoTotalPstTronco(int codEve, int codAtleta) throws SQLException{
        
         int tot = 0;
   
    String sql = "select SUM(acertosTroncoPst)parteDoCorpoPST from TB_acertosCBT a inner join TB_estruCBT e\n" +
"on e.codConf = a.codConf\n" +
"inner join TB_confrontos c\n" +
"on e.codChvConf = c.codChvConf\n" +
"inner join TB_segmentos s\n" +
"on s.codSegmento = c.codSegmento\n" +
"inner join TB_inscricaoAtleta i\n" +
"on i.codInscricao = a.codInsc\n" +
"and s.codEvento = ?\n" +
"and i.codAtleta = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
    stmt.setInt(2, codAtleta);
    
       
        ResultSet rs = stmt.executeQuery(); 
        
         
       while(rs.next()){
         
           
            tot = rs.getInt("parteDoCorpoPST");
            
        }
        rs.close();
        stmt.close();    
    
        return tot;
    }
    
    public int graficoTotalPstBracos(int codEve, int codAtleta) throws SQLException{
        
         int tot = 0;
   
    String sql = "select SUM(acertosBracosPst)parteDoCorpoPST from TB_acertosCBT a inner join TB_estruCBT e\n" +
"on e.codConf = a.codConf\n" +
"inner join TB_confrontos c\n" +
"on e.codChvConf = c.codChvConf\n" +
"inner join TB_segmentos s\n" +
"on s.codSegmento = c.codSegmento\n" +
"inner join TB_inscricaoAtleta i\n" +
"on i.codInscricao = a.codInsc\n" +
"and s.codEvento = ?\n" +
"and i.codAtleta = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
    stmt.setInt(2, codAtleta);
    
       
        ResultSet rs = stmt.executeQuery(); 
        
         
       while(rs.next()){
         
           
            tot = rs.getInt("parteDoCorpoPST");
            
        }
        rs.close();
        stmt.close();    
    
        return tot;
    }
    
    public int graficoTotalPstPernas(int codEve, int codAtleta) throws SQLException{
        
         int tot = 0;
   
    String sql = "select SUM(acertosPernasPst)parteDoCorpoPST from TB_acertosCBT a inner join TB_estruCBT e\n" +
"on e.codConf = a.codConf\n" +
"inner join TB_confrontos c\n" +
"on e.codChvConf = c.codChvConf\n" +
"inner join TB_segmentos s\n" +
"on s.codSegmento = c.codSegmento\n" +
"inner join TB_inscricaoAtleta i\n" +
"on i.codInscricao = a.codInsc\n" +
"and s.codEvento = ?\n" +
"and i.codAtleta = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
    stmt.setInt(2, codAtleta);
    
       
        ResultSet rs = stmt.executeQuery(); 
        
         
       while(rs.next()){
         
           
            tot = rs.getInt("parteDoCorpoPST");
            
        }
        rs.close();
        stmt.close();    
    
        return tot;
    }
    
    public int graficoTotalPstQuedagem(int codEve, int codAtleta) throws SQLException{
        
         int tot = 0;
   
    String sql = "select SUM(acertosQuedagemPst)parteDoCorpoPST from TB_acertosCBT a inner join TB_estruCBT e\n" +
"on e.codConf = a.codConf\n" +
"inner join TB_confrontos c\n" +
"on e.codChvConf = c.codChvConf\n" +
"inner join TB_segmentos s\n" +
"on s.codSegmento = c.codSegmento\n" +
"inner join TB_inscricaoAtleta i\n" +
"on i.codInscricao = a.codInsc\n" +
"and s.codEvento = ?\n" +
"and i.codAtleta = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
    stmt.setInt(2, codAtleta);
    
       
        ResultSet rs = stmt.executeQuery(); 
        
         
       while(rs.next()){
         
           
            tot = rs.getInt("parteDoCorpoPST");
            
        }
        rs.close();
        stmt.close();    
    
        return tot;
    }
    
    
    public List<Relatorios_bean> graficoProgressAcertosNgt(int codEve, int codAtleta) throws SQLException{
        
         
    String sql = "select e.codConf ,i.codAtleta,c.nomeChvConf, a.acertosCabecaNgt,a.acertosBracosNgt, a.acertosTroncoNgt,a.acertosPernasNgt, a.acertosQuedagemNgt \n" +
"from TB_acertosCBT a inner join TB_estruCBT e\n" +
"on a.codConf = e.codConf\n" +
"inner join TB_confrontos c\n" +
"on c.codChvConf = e.codChvConf\n" +
"inner join TB_segmentos s\n" +
"on s.codSegmento = c.codSegmento\n" +
"inner join TB_inscricaoAtleta i\n" +
"on i.codInscricao = a.codInsc\n" +
"and s.codEvento = ?\n" +
"and i.codAtleta = ?\n" +
"group by e.codConf, i.codAtleta,c.nomeChvConf, a.acertosCabecaNgt,a.acertosBracosNgt, a.acertosTroncoNgt,a.acertosPernasNgt, a.acertosQuedagemNgt \n" +
"order by e.codConf asc";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
    stmt.setInt(2, codAtleta);
    
       
        ResultSet rs = stmt.executeQuery(); 
        List<Relatorios_bean> lista = new ArrayList<>();
         
       while(rs.next()){
         Relatorios_bean bean = new Relatorios_bean();
           
         bean.setCabeca(rs.getInt("acertosCabecaNgt"));
         bean.setTronco(rs.getInt("acertosTroncoNgt"));
         bean.setBracos(rs.getInt("acertosBracosNgt"));
         bean.setPernas(rs.getInt("acertosPernasNgt"));
         bean.setQuedagem(rs.getInt("acertosQuedagemNgt"));
            
            lista.add(bean);
        }
        rs.close();
        stmt.close();    
    
        return lista;
    }
    
    public List<Relatorios_bean> graficoProgressAcertosPst(int codEve, int codAtleta) throws SQLException{
        
         
    String sql = "select e.codConf ,i.codAtleta,c.nomeChvConf, a.acertosCabecaPst,a.acertosBracosPst, a.acertosTroncoPst,a.acertosPernasPst, a.acertosQuedagemPst \n" +
"from TB_acertosCBT a inner join TB_estruCBT e\n" +
"on a.codConf = e.codConf\n" +
"inner join TB_confrontos c\n" +
"on c.codChvConf = e.codChvConf\n" +
"inner join TB_segmentos s\n" +
"on s.codSegmento = c.codSegmento\n" +
"inner join TB_inscricaoAtleta i\n" +
"on i.codInscricao = a.codInsc\n" +
"and s.codEvento = ?\n" +
"and i.codAtleta = ?\n" +
"group by e.codConf, i.codAtleta,c.nomeChvConf, a.acertosCabecaPst,a.acertosBracosPst, a.acertosTroncoPst,a.acertosPernasPst, a.acertosQuedagemPst \n" +
"order by e.codConf asc";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
    stmt.setInt(2, codAtleta);
    
       
        ResultSet rs = stmt.executeQuery(); 
        List<Relatorios_bean> lista = new ArrayList<>();
         
       while(rs.next()){
         Relatorios_bean bean = new Relatorios_bean();
           
         bean.setCabeca(rs.getInt("acertosCabecaPst"));
         bean.setTronco(rs.getInt("acertosTroncoPst"));
         bean.setBracos(rs.getInt("acertosBracosPst"));
         bean.setPernas(rs.getInt("acertosPernasPst"));
         bean.setQuedagem(rs.getInt("acertosQuedagemPst"));
            
            lista.add(bean);
        }
        rs.close();
        stmt.close();    
    
        return lista;
    }
    
    public List<Relatorios_bean> graficoResultsConfPst(int codAtleta,int codEve) throws SQLException{
        
         
    String sql = "exec p_consultaResultadosConfPst ?,?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codAtleta);
    stmt.setInt(2, codEve);
    
    
       
        ResultSet rs = stmt.executeQuery(); 
        List<Relatorios_bean> lista = new ArrayList<>();
         
       while(rs.next()){
         Relatorios_bean bean = new Relatorios_bean();
           
         bean.setFinalCBT(rs.getString("finalCBT"));
         
            lista.add(bean);
        }
        rs.close();
        stmt.close();    
    
        return lista;
    }
    
    public List<Relatorios_bean> graficoResultsConfNgt(int codEve, int codAtleta) throws SQLException{
        
         
    String sql = "exec p_consultaResultadosConfNgt ?,?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    
    stmt.setInt(1, codAtleta);
    stmt.setInt(2, codEve);
       
        ResultSet rs = stmt.executeQuery(); 
        List<Relatorios_bean> lista = new ArrayList<>();
         
       while(rs.next()){
         Relatorios_bean bean = new Relatorios_bean();
           
         bean.setFinalCBT(rs.getString("finalCBT"));
         
            lista.add(bean);
        }
        rs.close();
        stmt.close();    
    
        return lista;
    }
    
    public List<Integer> consultaCodEventos(int codEve) throws SQLException{
        
         
    String sql = "select codEvento from TB_evento where codModali = (select codModali FROM TB_evento where codEvento = ?) order by dataEvento desc";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    
    stmt.setInt(1, codEve);
       
        ResultSet rs = stmt.executeQuery(); 
        List<Integer> lista = new ArrayList<>();
         
       while(rs.next()){
    
            lista.add(rs.getInt("codEvento"));
        }
        rs.close();
        stmt.close();    
    
        return lista;
    }
    
    public List<Relatorios_bean> consultaTotImpactoPst(int codEve, int codAtleta) throws SQLException{
        
         
    String sql = "select  SUM(ac.acertosCabecaPst)CABECA, SUM(ac.acertosBracosPst)BRACO, SUM(ac.acertosPernasPst)PERNAS, SUM(ac.acertosQuedagemPst)QUEDA, SUM(ac.acertosTroncoPst)TRONCO\n" +
"from TB_acertosCBT ac inner join TB_estruCBT es\n" +
"on ac.codConf = es.codConf\n" +
"inner join TB_inscricaoAtleta i\n" +
"on ac.codInsc = i.codInscricao\n" +
"inner join TB_segmentos s\n" +
"on s.codSegmento = i.codSegmento\n" +
"and i.codAtleta = ?\n" +
"and s.codEvento = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    
    stmt.setInt(1, codAtleta);
    stmt.setInt(2, codEve);
       
        ResultSet rs = stmt.executeQuery(); 
        List<Relatorios_bean> lista = new ArrayList<>();
         
       while(rs.next()){
         Relatorios_bean bean = new Relatorios_bean();
           
         bean.setProgCabecaPst(rs.getInt("CABECA"));
         bean.setProgTroncoPst(rs.getInt("TRONCO"));
         bean.setProgBracoPst(rs.getInt("BRACO"));
         bean.setProgPernaPst(rs.getInt("PERNAS"));
         bean.setProgQuedagemPst(rs.getInt("QUEDA"));
         
            lista.add(bean);
        }
        rs.close();
        stmt.close();    
    
        return lista;
    }
    
    public List<Relatorios_bean> consultaTotImpactoNgt(int codEve, int codAtleta) throws SQLException{
        
         
    String sql = "select  SUM(ac.acertosCabecaNgt)CABECA, SUM(ac.acertosBracosNgt)BRACO, SUM(ac.acertosPernasNgt)PERNAS, SUM(ac.acertosQuedagemNgt)QUEDA, SUM(ac.acertosTroncoNgt)TRONCO\n" +
"from TB_acertosCBT ac inner join TB_estruCBT es\n" +
"on ac.codConf = es.codConf\n" +
"inner join TB_inscricaoAtleta i\n" +
"on ac.codInsc = i.codInscricao\n" +
"inner join TB_segmentos s\n" +
"on s.codSegmento = i.codSegmento\n" +
"and i.codAtleta = ?\n" +
"and s.codEvento = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    
    stmt.setInt(1, codAtleta);
    stmt.setInt(2, codEve);
        
        ResultSet rs = stmt.executeQuery(); 
        List<Relatorios_bean> lista = new ArrayList<>();
         
       while(rs.next()){
         Relatorios_bean bean = new Relatorios_bean();
           
         bean.setProgCabecaNgt(rs.getInt("CABECA"));
         bean.setProgTroncoNgt(rs.getInt("TRONCO"));
         bean.setProgBracoNgt(rs.getInt("BRACO"));
         bean.setProgPernaNgt(rs.getInt("PERNAS"));
         bean.setProgQuedagemNgt(rs.getInt("QUEDA"));
         
            lista.add(bean);
        }
        rs.close();
        stmt.close();    
    
        return lista;
    }
    
    
    //////////////////////////////////////////////////// Números do evento /////////////////////////////////////////
    
    public List<Relatorios_bean> consultaQtdIncs(int codEve) throws SQLException{
     
        String sql = "select i.codSegmento, i.codInscricao from TB_inscricaoAtleta i inner join TB_segmentos s\n" +
                 "on i.codSegmento = s.codSegmento\n" +
                 "and s.codEvento = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
        
        ResultSet rs = stmt.executeQuery(); 
        List<Relatorios_bean> lista = new ArrayList<>();
         
       while(rs.next()){
         Relatorios_bean bean = new Relatorios_bean();
           
         bean.setCodSegmento(rs.getInt("codSegmento"));
         bean.setCodInscricao(rs.getInt("codInscricao"));
         
            lista.add(bean);
        }
        rs.close();
        stmt.close();    
    
        return lista;
    }
    
    public List<Relatorios_bean> consultaQtdSegs(int codEve) throws SQLException{
     
        String sql = "select * from TB_segmentos where codEvento = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
        
        ResultSet rs = stmt.executeQuery(); 
        List<Relatorios_bean> lista = new ArrayList<>();
         
       while(rs.next()){
         Relatorios_bean bean = new Relatorios_bean();
           
         bean.setCodSegmento(rs.getInt("codSegmento"));
         
            lista.add(bean);
        }
        rs.close();
        stmt.close();    
    
        return lista;
    }
    
    public List<Relatorios_bean> consultaQtdCat(int codEve) throws SQLException{
     
        String sql = "select c.codCategoria, c.codSegmento from TB_categoria c inner join TB_segmentos s\n" +
                     "on c.codSegmento = s.codSegmento\n" +
                     "and s.codEvento = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
        
        ResultSet rs = stmt.executeQuery(); 
        List<Relatorios_bean> lista = new ArrayList<>();
         
       while(rs.next()){
         Relatorios_bean bean = new Relatorios_bean();
           
         bean.setCodSegmento(rs.getInt("codSegmento"));
         bean.setCodCategoria(rs.getInt("codCategoria"));
         
            lista.add(bean);
        }
        rs.close();
        stmt.close();    
    
        return lista;
    }
    
    public List<Relatorios_bean> consultaQtdArb(int codEve) throws SQLException{
     
        String sql = "select a.codArbitro,a.codSegmento from TB_arbitro a inner join TB_segmentos s\n" +
                     "on a.codSegmento = s.codSegmento\n" +
                     "and s.codEvento = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
        
        ResultSet rs = stmt.executeQuery(); 
        List<Relatorios_bean> lista = new ArrayList<>();
         
       while(rs.next()){
         Relatorios_bean bean = new Relatorios_bean();
           
         bean.setCodSegmento(rs.getInt("codSegmento"));
         bean.setCodArbitro(rs.getInt("codArbitro"));
         
         
            lista.add(bean);
        }
        rs.close();
        stmt.close();    
    
        return lista;
    }
    
     public List<Relatorios_bean> consultaQtdChv(int codEve) throws SQLException{
     
        String sql = "select c.codChvConf,c.codSegmento from TB_confrontos c inner join TB_segmentos s\n" +
                     "on c.codSegmento = s.codSegmento\n" +
                     "and s.codEvento = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
        
        ResultSet rs = stmt.executeQuery(); 
        List<Relatorios_bean> lista = new ArrayList<>();
         
       while(rs.next()){
         Relatorios_bean bean = new Relatorios_bean();
           
         bean.setCodSegmento(rs.getInt("codSegmento"));
         bean.setCodChvConf(rs.getInt("codChvConf"));
         
         
            lista.add(bean);
        }
        rs.close();
        stmt.close();    
    
        return lista;
    }
     
     public int consultaTotSexoFem(int codEve) throws SQLException{
     
        String sql = "select count(a.codAtleta)codAtletaF from TB_inscricaoAtleta i inner join TB_atleta a\n" +
"on a.codAtleta = i.codAtleta\n" +
"inner join TB_segmentos s \n" +
"on s.codSegmento = i.codSegmento\n" +
"and a.sexo = 'Feminino'\n" +
"and s.codEvento = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
        
        ResultSet rs = stmt.executeQuery(); 
        int f = 0;
         
       while(rs.next()){
         Relatorios_bean bean = new Relatorios_bean();
           
         f = rs.getInt("codAtletaF");
         
        }
        rs.close();
        stmt.close();    
    
        return f;
    }
     
     public int consultaTotSexoMasc(int codEve) throws SQLException{
     
        String sql = "select count(a.codAtleta)codAtletaM from TB_inscricaoAtleta i inner join TB_atleta a\n" +
"on a.codAtleta = i.codAtleta\n" +
"inner join TB_segmentos s \n" +
"on s.codSegmento = i.codSegmento\n" +
"and a.sexo = 'Masculino'\n" +
"and s.codEvento = ?";
    
    PreparedStatement stmt = this.conexao.prepareStatement(sql);
    stmt.setInt(1, codEve);
        
        ResultSet rs = stmt.executeQuery(); 
        int m = 0;
         
       while(rs.next()){
         Relatorios_bean bean = new Relatorios_bean();
           
         m = rs.getInt("codAtletaM");
         
        }
        rs.close();
        stmt.close();    
    
        return m;
    }
}
