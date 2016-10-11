/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tcc.controller;

//import br.tcc.bean.Eventos_bean;
//import br.tcc.dao.Consultas_dao;
import br.tcc.bean.Relatorios_bean;
import br.tcc.dao.Relatorios_dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author joãomarcos
 */
@ManagedBean(name="TestePkList", eager = true)
@RequestScoped
public class TestePkList {
     private LineChartModel lineModel1;
     private LineChartModel grafLinhaMetAcertNgt;
     private PieChartModel grafPizzaMetNgts;
     private BarChartModel grafBarResultsConfPst;
     private List<Relatorios_bean> listaProgresAcertNgt = new ArrayList<>();
     private List<Relatorios_bean> listaProgresAcertPst = new ArrayList<>();
     private List<Relatorios_bean> listaResultsConfPst = new ArrayList<>(); 
    @PostConstruct
    public void init() {
        
        try{
            createLineModels();
        consultaTotaisGraficosMetricas();
        }catch(SQLException ex){
            
        }
    }
  
    private void createLineModels() throws SQLException {

        grafLinhaMetAcertNgt = graficoLinhaMetricasAcertos();
        grafLinhaMetAcertNgt.setTitle("Produtividade de confrontos");
        grafLinhaMetAcertNgt.setLegendPosition("e");
        grafLinhaMetAcertNgt.setShowPointLabels(true);
        grafLinhaMetAcertNgt.getAxes().put(AxisType.X, new CategoryAxis("Confronto numero"));
        Axis yAxis = grafLinhaMetAcertNgt.getAxis(AxisType.Y);
        yAxis.setLabel("Pontuação");
        yAxis.setMin(0);
        yAxis.setMax(20);
    }
     
    public void consultaTotaisGraficosMetricas()throws SQLException{
    Relatorios_dao dao = new Relatorios_dao();
    
//    int cabeca = dao.graficoTotalNgtCabeca(7, 44);
//    int bracos = dao.graficoTotalNgtBracos(7, 44);
//    int pernas = dao.graficoTotalNgtPernas(7, 44);
//    int quedagem = dao.graficoTotalNgtQuedagem(7, 44);
//    int tronco = dao.graficoTotalNgtTronco(7, 44);
//    graficosPizzaMetricas(cabeca, bracos, pernas, tronco, quedagem);
//    
    
    listaResultsConfPst = dao.graficoResultsConfPst(44, 7);    
    

    criarModeloDeBarras();
}

    
public void criarModeloDeBarras(){
        grafBarResultsConfPst = graficoBarrasFinaisConfPst(); 
        
        grafBarResultsConfPst.setTitle("Confrontos vencidos finalizados em");
        grafBarResultsConfPst.setLegendPosition("ne");
        Axis xAxis = grafBarResultsConfPst.getAxis(AxisType.X);
        xAxis.setLabel("Evento");
        Axis yAxis = grafBarResultsConfPst.getAxis(AxisType.Y);
        yAxis.setLabel("Numero de vitórias");
        yAxis.setMin(0);
        yAxis.setMax(10);
 
        
}
    
public BarChartModel graficoBarrasFinaisConfPst(){
    
   
    BarChartModel graf = new BarChartModel();
    
    ChartSeries nocaute = new ChartSeries();
    ChartSeries finalizacao = new ChartSeries();
    ChartSeries pontos = new ChartSeries();
    
    nocaute.setLabel("Nocaute");
    finalizacao.setLabel("Finalização");
    pontos.setLabel("Pontos");
    int numNocaute = 0;
        int numFinaliza = 0;
        int numPontos = 0;
    for(int i = 0; i < listaResultsConfPst.size(); i++){
        
        if(listaResultsConfPst.get(i).getFinalCBT().equals("Nocaute")){
            nocaute.set("Circuito de chutes", numNocaute+=1);
            System.out.println("nocaute");
        }
        else if(listaResultsConfPst.get(i).getFinalCBT().equals("Finalização")){
            finalizacao.set("Circuito de chutes", numFinaliza += 1);
            System.out.println("Finaliza");
        }else if(listaResultsConfPst.get(i).getFinalCBT().equals("Pontos")){
            pontos.set("Circuito de chutes", numPontos += 1);
            System.out.println("pontos ");
        }
        
    }
    graf.addSeries(nocaute);
    graf.addSeries(finalizacao);
    graf.addSeries(pontos);
    
    return graf;
}

public void graficosPizzaMetricas(int cabeca, int bracos, int pernas, int tronco, int quedagem){
    grafPizzaMetNgts = new PieChartModel();
    
    grafPizzaMetNgts.set("Cabeça",cabeca);
    grafPizzaMetNgts.set("Braços",bracos);
    grafPizzaMetNgts.set("Pernas",pernas);
    grafPizzaMetNgts.set("Tronco",tronco);
    grafPizzaMetNgts.set("Quedagem",quedagem);
    
    grafPizzaMetNgts.setTitle("Porcentagem geral de danos nos confrontos");
    grafPizzaMetNgts.setLegendPosition("e");
    grafPizzaMetNgts.setFill(false);
    grafPizzaMetNgts.setShowDataLabels(true);
    grafPizzaMetNgts.setDiameter(150);
}

private LineChartModel graficoLinhaMetricasAcertos()throws SQLException{
        LineChartModel graf = new LineChartModel();
        Relatorios_dao dao = new Relatorios_dao();
        listaProgresAcertNgt = dao.graficoProgressAcertosNgt(7, 49);
        
         ChartSeries cabeca = new ChartSeries();
         cabeca.setLabel("Cabeça");
         
         ChartSeries tronco = new ChartSeries();
         tronco.setLabel("Tronco");
         
         ChartSeries bracos = new ChartSeries();
         bracos.setLabel("Braços");
         
         ChartSeries pernas = new ChartSeries();
         pernas.setLabel("Pernas");
         
         ChartSeries quedagem = new ChartSeries();
         quedagem.setLabel("Quedagem");
        
        for(int i = 0; i < listaProgresAcertNgt.size(); i++) {
            
            cabeca.set(i+1,listaProgresAcertNgt.get(i).getCabeca());
            tronco.set(i+1,listaProgresAcertNgt.get(i).getTronco());
            bracos.set(i+1,listaProgresAcertNgt.get(i).getBracos());
            pernas.set(i+1,listaProgresAcertNgt.get(i).getPernas());
            quedagem.set(i+1,listaProgresAcertNgt.get(i).getQuedagem());
        
        }
        graf.addSeries(cabeca);
        graf.addSeries(pernas);
        graf.addSeries(tronco);
        graf.addSeries(bracos);
        graf.addSeries(quedagem);
        return graf;
    }
    
//    private LineChartModel graficoLinhaMetricasAcertosT() {
//        LineChartModel model = new LineChartModel();
// 
//        ChartSeries boys = new ChartSeries();
//        boys.setLabel("Cabeça");
//        boys.set("chave de confronto x cbt 1", 22);
//        boys.set("chave de confronto x cbt 2", 39);
//        boys.set("chave de confronto x cbt 3", 44);
//        boys.set("chave de confronto x cbt 4", 46);
//        boys.set("chave de confronto x cbt 5", 46);
// 
//        ChartSeries girls = new ChartSeries();
//        girls.setLabel("Pernas");
//        girls.set("chave de confronto x cbt 1", 10);
//        girls.set("chave de confronto x cbt 2", 8);
//        girls.set("chave de confronto x cbt 3", 23);
//        girls.set("chave de confronto x cbt 4", 27);
//        girls.set("chave de confronto x cbt 5", 20);
//        
//        ChartSeries tronco = new ChartSeries();
//        tronco.setLabel("Tronco");
//        tronco.set("chave de confronto x cbt 1", 15);
//        tronco.set("chave de confronto x cbt 2", 21);
//        tronco.set("chave de confronto x cbt 3", 19);
//        tronco.set("chave de confronto x cbt 4", 30);
//        tronco.set("chave de confronto x cbt 5", 27);
//        
//        ChartSeries bracos = new ChartSeries();
//        bracos.setLabel("Braços");
//        bracos.set("chave de confronto x cbt 1", 21);
//        bracos.set("chave de confronto x cbt 2", 14);
//        bracos.set("chave de confronto x cbt 3", 31);
//        bracos.set("chave de confronto x cbt 4", 19);
//        bracos.set("chave de confronto x cbt 5", 26);
// 
//        
//        model.addSeries(boys);
//        model.addSeries(girls);
//        model.addSeries(tronco);
//        model.addSeries(bracos);
//         
//        return model;
//    }
    
    
    //////////////////////////////////////////////////////////////
    
     public PieChartModel getGrafPizzaMetNgts() {
        return grafPizzaMetNgts;
    }
 
    public LineChartModel getGrafLinhaMetAcertNgt() {
        return grafLinhaMetAcertNgt;
    }

    public BarChartModel getGrafBarResultsConfPst() {
        return grafBarResultsConfPst;
    }
   
    
}
