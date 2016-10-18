
package br.tcc.controller;

import br.tcc.bean.Relatorios_bean;
import br.tcc.bean.Segmentos_bean;
import br.tcc.dao.Atletas_dao;
import br.tcc.dao.Eventos_dao;
import br.tcc.dao.Relatorios_dao;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author joãomarcos
 */ 
@ManagedBean(name="RelatoriosManagedBean", eager = true)
@SessionScoped
public class RelatoriosManagedBean {
    
    // quase todos tem
private String sexo; 
private int codSegmento;   

 // Atletas 

private int codAtleta;
private String nomeAtleta;
private int idadeAtleta;
private float pesoAtleta;
private String graduacaoAtleta;
private String cpfAtleta;
private String emailAtleta;

// Categorias
private int codCategoria;
private float pesoMinCategoria;
private float pesoMaxCategoria;
private int graduacaoMinCategoria;
private int graduacaoMaxCategoria;
private int idadeMinCategoria;
private int idadeMaxCategoria;
private String nomeCategoria;    
    
// Confrontos

private int codChvConf;
private String nomeChvConf;
private String statusChvConf;
private int qtdRodadasChvConf;    
private int qtdChvs;

// metricas atleta

private int codInscricao;
private int totPontosPositivos;
private int totPontosNegativos;

// metricas combate

private int numRodada;
private String parteDoCorpoNGT;
private String parteDoCorpoPST;
private String descricaoCBT;
private String finalCBT;
private int totais;

// Segmentos 

private int codEvento;    
private String nomeSegmento;
private String descricaoSegmento;


// Eventos
 
private  int codModali;
private String dataEvento;
private String statusEvento;
private String nomeEvento;

// atributos manipulaveis

private String codEveSelecionado;
private String codSegSelecionado;
private int codAtletaSelecionado;

// metricas atleta
private String nomeAtletaSelecionado;
private PieChartModel grafPizzaTops;
private PieChartModel grafPizzaMetNgts;
private PieChartModel grafPizzaMetPst;
private Relatorios_bean atletaSelecionado;
private LineChartModel grafLinhaMetAcertNgt;
private LineChartModel grafLinhaMetAcertPst;
private BarChartModel grafBarResultsConfPst;
private BarChartModel grafBarResultsConfNgt;
 
private String progCabecaPst = "0,00";
private String progTroncoPst= "0,00";
private String progBracoPst= "0,00";
private String progPernaPst= "0,00";
private String progQuedagemPst= "0,00";
private String rendimentoPst= "0,00";

private String progCabecaNegPst= "0,00";
private String progTroncoNegPst= "0,00";
private String progBracoNegPst= "0,00";
private String progPernaNegPst= "0,00";
private String progQuedagemNegPst= "0,00";

private String progCabecaNgt= "0,00";
private String progTroncoNgt= "0,00";
private String progBracoNgt= "0,00";
private String progPernaNgt= "0,00";
private String progQuedagemNgt= "0,00";
private String rendimentoNgt= "0,00";

private String progCabecaPstNgt= "0,00";
private String progTroncoPstNgt= "0,00";
private String progBracoPstNgt= "0,00";
private String progPernaPstNgt= "0,00";
private String progQuedagemPstNgt= "0,00";


// Numeros do evento 
private int qtdPrtInscGeral;
private int qtdSegmentos;
private int mediaPrtInscSeg;
private int qtdCat;
private int mediaArbSeg;
private int qtdChv;

private int qtdPrtInscGeralAnt;
private int qtdSegmentosAnt;
private int mediaPrtInscSegAnt;
private int qtdCatAnt;
private int mediaArbSegAnt;
private int qtdChvAnt;

private int qtdPrtInscGeralFut;
private int qtdSegmentosFut;
private int mediaPrtInscSegFut;
private int qtdCatFut;
private int mediaArbSegFut;
private int qtdChvFut;
private int qtdFemEvFut;
private int qtdMascEvFut;
private float lucroFut;
private String valInsc;
private String percEstProg;


private float crescEve;
private BarChartModel grafBarComparaNumEve;
private PieChartModel grafPizzaSexoParts;



List<Relatorios_bean> listaQtdPrtInscs = new ArrayList<>();
List<Relatorios_bean> listaQtdSegmentos = new ArrayList<>();
List<Relatorios_bean> listaQtdCat = new ArrayList<>();
List<Relatorios_bean> listaQtdArb = new ArrayList<>();
List<Relatorios_bean> listaQtdChv = new ArrayList<>();

List<Relatorios_bean> chavesAberto = new ArrayList<>();
List<Relatorios_bean> chaves = new ArrayList<>();
List<Relatorios_bean> metricasPST = new ArrayList<>();
List<Relatorios_bean> metricasNGT = new ArrayList<>();
private List<String> ComboSegs = new ArrayList<>();
private List<String> comboEve = new ArrayList<>();
private List<Segmentos_bean> listaSegs = new ArrayList<>();
private List<Relatorios_bean> listaTops = new ArrayList<>();
private List<Relatorios_bean> listaAtletas = new ArrayList<>();
private List<Relatorios_bean> listaComentsArbPst = new ArrayList<>();
private List<Relatorios_bean> listaComentsArbNgt = new ArrayList<>();
private List<Relatorios_bean> listaProgresAcertNgt = new ArrayList<>();
private List<Relatorios_bean> listaProgresAcertPst = new ArrayList<>();
private List<Relatorios_bean> listaResultsConfPst = new ArrayList<>();
private List<Relatorios_bean> listaResultsConfNgt = new ArrayList<>();
///////////////////////////////////////////////////////// Métodos Tops do torneio //////////////////////////////////////////////


/////////////// Metodos consulta ////////////////////

public void consulta(){
    try {
        listaTops.clear();
        if(codEveSelecionado.equals("")){
FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "Selecione o evento que gostaria de consultar os vencedores."));                            
        }else{
        if(!codSegSelecionado.equals("")){
            codSegmento = Integer.valueOf(codSegSelecionado.substring(0,codSegSelecionado.indexOf(" ")));
        }
        codEvento = Integer.valueOf(codEveSelecionado.substring(0,codEveSelecionado.indexOf(" ")));
        Relatorios_dao dao = new Relatorios_dao();
        
        listaTops = dao.topsTorneio(codEvento, codSegmento, finalCBT);
        
    }    
    } catch (SQLException ex) {
        Logger.getLogger(RelatoriosManagedBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}



/////////////// Metodos combos ////////////////////

public void carregaCombos()throws SQLException{
      Eventos_dao dao = new Eventos_dao();    
        if(codEveSelecionado.equals("")){
        
       ComboSegs.clear();
       codCategoria = 0;
       codEvento = 0;
       codSegmento = 0;
    }else{
   
   ComboSegs.clear();
   codEvento = Integer.valueOf(codEveSelecionado.substring(0,codEveSelecionado.indexOf(" ")));   
   listaSegs = dao.consultaSegs(codEvento);
   
  if(listaSegs.isEmpty()){
        
        ComboSegs.clear();
   
    }else
      for(int i = 0; i< listaSegs.size(); i++){
      
          ComboSegs.add(listaSegs.get(i).getCodSegmento()+" - "+ listaSegs.get(i).getNomeSegmento());
          
      }
    }
        consultaTotaisEncerramentosCBT();
    }

/////////////// Metodos graficos /////////////////
public void consultaTotaisEncerramentosCBT()throws SQLException{
    Relatorios_dao dao = new Relatorios_dao();
    int finaliza = dao.metricasFinalizaCBTFinalizacoes(codEvento);
    int pontos = dao.metricasFinalizaCBTPontos(codEvento);
    int nocautes = dao.metricasFinalizaCBTNocaute(codEvento);
    graficoPizzaTops(finaliza, pontos, nocautes);
}

private void graficoPizzaTops(int finaliza, int pontos, int nocautes)throws SQLException{
        grafPizzaTops = new PieChartModel();
       
        
        grafPizzaTops.set("Total de finalizações", finaliza);    
    
        grafPizzaTops.set("Total por pontos", pontos);
        
        grafPizzaTops.set("Total por nocautes", nocautes);
       
        grafPizzaTops.setLegendPosition("e");
        grafPizzaTops.setFill(false);
        grafPizzaTops.setShowDataLabels(true);
        grafPizzaTops.setDiameter(150);
    }

public void consultaTotaisGraficosMetricas()throws SQLException{
    Relatorios_dao dao = new Relatorios_dao();
    
    // Pizza
    int cabeca = dao.graficoTotalNgtCabeca(codEvento, codAtleta);
    int bracos = dao.graficoTotalNgtBracos(codEvento, codAtleta);
    int pernas = dao.graficoTotalNgtPernas(codEvento, codAtleta);
    int quedagem = dao.graficoTotalNgtQuedagem(codEvento, codAtleta);
    int tronco = dao.graficoTotalNgtTronco(codEvento, codAtleta);
    
    
    
    int cabecaPst = dao.graficoTotalPstCabeca(codEvento, codAtleta);
    int bracosPst = dao.graficoTotalPstBracos(codEvento, codAtleta);
    int pernasPst = dao.graficoTotalPstPernas(codEvento, codAtleta);
    int quedagemPst = dao.graficoTotalPstQuedagem(codEvento, codAtleta);
    int troncoPst = dao.graficoTotalPstTronco(codEvento, codAtleta);
    graficosPizzaMetricas(cabeca, bracos, pernas, tronco, quedagem, cabecaPst, bracosPst, pernasPst, troncoPst, quedagemPst);
   
    // Linhas 
    
    listaProgresAcertNgt = dao.graficoProgressAcertosNgt(codEvento, codAtleta);
    listaProgresAcertPst = dao.graficoProgressAcertosPst(codEvento, codAtleta);
    criarModeloDeLinhas();
    
    // Barras 
    
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
        
        
        grafBarResultsConfNgt = graficoBarrasFinaisConfNgt();
        grafBarResultsConfNgt.setTitle("Confrontos perdidos finalizados em");
        grafBarResultsConfNgt.setLegendPosition("ne");
        Axis xAxis2 = grafBarResultsConfNgt.getAxis(AxisType.X);
        xAxis2.setLabel("Evento");
        Axis yAxis2 = grafBarResultsConfNgt.getAxis(AxisType.Y);
        yAxis2.setLabel("Numero de derrotas");
        yAxis2.setMin(0);
        yAxis2.setMax(10);
        
        
        int max = 0;
        
        if((qtdPrtInscGeral+qtdSegmentos+mediaPrtInscSeg+qtdCat+mediaArbSeg+qtdChv) > (qtdPrtInscGeralAnt+qtdSegmentosAnt+mediaPrtInscSegAnt+qtdCatAnt+mediaArbSegAnt+qtdChvAnt)){
            max = qtdPrtInscGeral+qtdSegmentos+mediaPrtInscSeg+qtdCat+mediaArbSeg+qtdChv+10;
        }else{
            max = qtdPrtInscGeralAnt+qtdSegmentosAnt+mediaPrtInscSegAnt+qtdCatAnt+mediaArbSegAnt+qtdChvAnt +10;
        }
        grafBarComparaNumEve = graficoBarrasComparaEves(); 
        grafBarComparaNumEve.setTitle("Comparação qualitativa de crescimento");
        grafBarComparaNumEve.setLegendPosition("ne");
        Axis xAxis3 = grafBarComparaNumEve.getAxis(AxisType.X);
        xAxis3.setLabel("Eventos");
        Axis yAxis3 = grafBarComparaNumEve.getAxis(AxisType.Y);
        yAxis3.setLabel("Progresso geral dos números");
        yAxis3.setMin(0);
        yAxis3.setMax(max); 
}

public BarChartModel graficoBarrasFinaisConfPst(){
    BarChartModel graf = new BarChartModel();
    
    ChartSeries nocaute = new ChartSeries();
    ChartSeries finalizacao = new ChartSeries(); 
    ChartSeries pontos = new ChartSeries();
    
    nocaute.setLabel("Nocaute");
    finalizacao.setLabel("Finalização");
    pontos.setLabel("Pontos");
    
    if(!listaResultsConfPst.isEmpty()){
        int numNocaute = 0;
        int numFinaliza = 0;
        int numPontos = 0;
    for(int i = 0; i < listaResultsConfPst.size(); i++){
        
        if(listaResultsConfPst.get(i).getFinalCBT().equals("Nocaute")){
            nocaute.set("Circuito de chutes", numNocaute+=1);
        }
        else if(listaResultsConfPst.get(i).getFinalCBT().equals("Finalização")){
            finalizacao.set("Circuito de chutes", numFinaliza += 1);
        }else if(listaResultsConfPst.get(i).getFinalCBT().equals("Pontos")){
            pontos.set("Circuito de chutes", numPontos += 1);
            
        }
        
    }
    }else{
        nocaute.set("",0);
        pontos.set("",0);
        finalizacao.set("",0);
    }
    graf.addSeries(nocaute);
    graf.addSeries(finalizacao);
    graf.addSeries(pontos);
    
    return graf;
}

public BarChartModel graficoBarrasFinaisConfNgt(){
    BarChartModel graf = new BarChartModel();
    
    ChartSeries nocaute = new ChartSeries();
    ChartSeries finalizacao = new ChartSeries();
    ChartSeries pontos = new ChartSeries();
    
    nocaute.setLabel("Nocaute");
    finalizacao.setLabel("Finalização");
    pontos.setLabel("Pontos");
    
    if(!listaResultsConfNgt.isEmpty()){
        int numNocaute = 0;
        int numFinaliza = 0;
        int numPontos = 0;
    for(int i = 0; i < listaResultsConfNgt.size(); i++){
        
        if(listaResultsConfNgt.get(i).getFinalCBT().equals("Nocaute")){
            nocaute.set(codEveSelecionado, numNocaute+=1);
        }else if(listaResultsConfNgt.get(i).getFinalCBT().equals("Finalização")){
            finalizacao.set(codEveSelecionado, numFinaliza += 1);
        }else if(listaResultsConfNgt.get(i).getFinalCBT().equals("Pontos") ){
            finalizacao.set(codEveSelecionado, numPontos += 1);
        }
    }
    }else{
        nocaute.set("",0);
        pontos.set("",0);
        finalizacao.set("",0);
    }
    graf.addSeries(nocaute);
    graf.addSeries(finalizacao);
    graf.addSeries(pontos);
    return graf;
}

public void criarModeloDeLinhas()throws SQLException{
      int max = 0;
      int max2 = 0;
    
      for(int i = 0; i < listaProgresAcertNgt.size(); i++){
         if(listaProgresAcertNgt.get(i).getCabeca() > listaProgresAcertNgt.get(i).getBracos()
            && listaProgresAcertNgt.get(i).getCabeca() > listaProgresAcertNgt.get(i).getTronco()
                 && listaProgresAcertNgt.get(i).getCabeca() > listaProgresAcertNgt.get(i).getPernas()
                 && listaProgresAcertNgt.get(i).getCabeca() > listaProgresAcertNgt.get(i).getQuedagem()){
             
             max = listaProgresAcertNgt.get(i).getCabeca();
             
         } else if(listaProgresAcertNgt.get(i).getBracos() > listaProgresAcertNgt.get(i).getCabeca()
            && listaProgresAcertNgt.get(i).getBracos()> listaProgresAcertNgt.get(i).getTronco()
                 && listaProgresAcertNgt.get(i).getBracos()> listaProgresAcertNgt.get(i).getPernas()
                 && listaProgresAcertNgt.get(i).getBracos()> listaProgresAcertNgt.get(i).getQuedagem()){
             
             max = listaProgresAcertNgt.get(i).getBracos();
             
         }else if(listaProgresAcertNgt.get(i).getTronco() > listaProgresAcertNgt.get(i).getBracos()
            && listaProgresAcertNgt.get(i).getTronco() > listaProgresAcertNgt.get(i).getCabeca()
                 && listaProgresAcertNgt.get(i).getTronco()> listaProgresAcertNgt.get(i).getPernas()
                 && listaProgresAcertNgt.get(i).getTronco()> listaProgresAcertNgt.get(i).getQuedagem()){
             
             max = listaProgresAcertNgt.get(i).getTronco();
             
         } else if(listaProgresAcertNgt.get(i).getPernas()> listaProgresAcertNgt.get(i).getBracos()
            && listaProgresAcertNgt.get(i).getPernas()> listaProgresAcertNgt.get(i).getTronco()
                 && listaProgresAcertNgt.get(i).getPernas()> listaProgresAcertNgt.get(i).getCabeca()
                 && listaProgresAcertNgt.get(i).getPernas()> listaProgresAcertNgt.get(i).getQuedagem()){
             
             max = listaProgresAcertNgt.get(i).getPernas();
             
         } else if(listaProgresAcertNgt.get(i).getQuedagem()> listaProgresAcertNgt.get(i).getBracos()
            && listaProgresAcertNgt.get(i).getQuedagem()> listaProgresAcertNgt.get(i).getTronco()
                 && listaProgresAcertNgt.get(i).getQuedagem()> listaProgresAcertNgt.get(i).getPernas()
                 && listaProgresAcertNgt.get(i).getQuedagem()> listaProgresAcertNgt.get(i).getCabeca()){
             
             max = listaProgresAcertNgt.get(i).getQuedagem();
         }  
      }
      
      for(int i = 0; i < listaProgresAcertPst.size(); i++){
         if(listaProgresAcertPst.get(i).getCabeca() > listaProgresAcertPst.get(i).getBracos()
            && listaProgresAcertPst.get(i).getCabeca() > listaProgresAcertPst.get(i).getTronco()
                 && listaProgresAcertPst.get(i).getCabeca() > listaProgresAcertPst.get(i).getPernas()
                 && listaProgresAcertPst.get(i).getCabeca() > listaProgresAcertPst.get(i).getQuedagem()){
             
             max2 = listaProgresAcertPst.get(i).getCabeca();
             
         } else if(listaProgresAcertPst.get(i).getBracos() > listaProgresAcertPst.get(i).getCabeca()
            && listaProgresAcertPst.get(i).getBracos()> listaProgresAcertPst.get(i).getTronco()
                 && listaProgresAcertPst.get(i).getBracos()> listaProgresAcertPst.get(i).getPernas()
                 && listaProgresAcertPst.get(i).getBracos()> listaProgresAcertPst.get(i).getQuedagem()){
             
             max2 = listaProgresAcertPst.get(i).getBracos();
             
         }else if(listaProgresAcertPst.get(i).getTronco() > listaProgresAcertPst.get(i).getBracos()
            && listaProgresAcertPst.get(i).getTronco() > listaProgresAcertPst.get(i).getCabeca()
                 && listaProgresAcertPst.get(i).getTronco()> listaProgresAcertPst.get(i).getPernas()
                 && listaProgresAcertPst.get(i).getTronco()> listaProgresAcertPst.get(i).getQuedagem()){
              
             max2 = listaProgresAcertPst.get(i).getTronco();
             
         } else if(listaProgresAcertPst.get(i).getPernas()> listaProgresAcertPst.get(i).getBracos()
            && listaProgresAcertPst.get(i).getPernas()> listaProgresAcertPst.get(i).getTronco()
                 && listaProgresAcertPst.get(i).getPernas()> listaProgresAcertPst.get(i).getCabeca() 
                 && listaProgresAcertPst.get(i).getPernas()> listaProgresAcertPst.get(i).getQuedagem()){
             
             max2 = listaProgresAcertPst.get(i).getPernas();
             
         } else if(listaProgresAcertPst.get(i).getQuedagem()> listaProgresAcertPst.get(i).getBracos()
            && listaProgresAcertPst.get(i).getQuedagem()> listaProgresAcertPst.get(i).getTronco()
                 && listaProgresAcertPst.get(i).getQuedagem()> listaProgresAcertPst.get(i).getPernas()
                 && listaProgresAcertPst.get(i).getQuedagem()> listaProgresAcertPst.get(i).getCabeca()){
             
             max2 = listaProgresAcertPst.get(i).getQuedagem();
         }  
      }
      
      
        grafLinhaMetAcertNgt = graficoLinhaMetricasAcertosNgt();
        grafLinhaMetAcertNgt.setTitle("Produtividade de acertos negativos de confrontos");
        grafLinhaMetAcertNgt.setLegendPosition("e");
        grafLinhaMetAcertNgt.setShowPointLabels(true);
        grafLinhaMetAcertNgt.getAxes().put(AxisType.X, new CategoryAxis("Confrontos na ordem que aconteceram"));
        Axis yAxis = grafLinhaMetAcertNgt.getAxis(AxisType.Y);
        yAxis.setLabel("Pontuação");
        yAxis.setMin(0);
        yAxis.setMax(max+5);
        
        grafLinhaMetAcertPst = graficoLinhaMetricasAcertosPst();
        grafLinhaMetAcertPst.setTitle("Produtividade de acertos positivos de confrontos");
        grafLinhaMetAcertPst.setLegendPosition("e");
        grafLinhaMetAcertPst.setShowPointLabels(true);
        grafLinhaMetAcertPst.getAxes().put(AxisType.X, new CategoryAxis("Confrontos na ordem que aconteceram"));
        Axis yAxis2 = grafLinhaMetAcertPst.getAxis(AxisType.Y);
        yAxis2.setLabel("Pontuação");
        yAxis2.setMin(0);
        yAxis2.setMax(max2+5);
}

private LineChartModel graficoLinhaMetricasAcertosNgt(){
        LineChartModel graf = new LineChartModel();
        
        
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
        
         if(!listaProgresAcertNgt.isEmpty()){
        for(int i = 0; i < listaProgresAcertNgt.size(); i++) {
            
            cabeca.set(i+1,listaProgresAcertNgt.get(i).getCabeca());
            tronco.set(i+1,listaProgresAcertNgt.get(i).getTronco());
            bracos.set(i+1,listaProgresAcertNgt.get(i).getBracos());
            pernas.set(i+1,listaProgresAcertNgt.get(i).getPernas());
            quedagem.set(i+1,listaProgresAcertNgt.get(i).getQuedagem());
        
        }
         }else{
             cabeca.set("",0);
             bracos.set("",0);
             tronco.set("",0);
             pernas.set("",0);
             quedagem.set("",0);
         }
       
        graf.addSeries(cabeca);
        graf.addSeries(pernas);
        graf.addSeries(tronco);
        graf.addSeries(bracos);
        graf.addSeries(quedagem);
        return graf;
    }


private LineChartModel graficoLinhaMetricasAcertosPst(){
        LineChartModel graf = new LineChartModel();
        
        
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
        
         if(!listaProgresAcertPst.isEmpty()){
        for(int i = 0; i < listaProgresAcertPst.size(); i++) {
            
            cabeca.set(i+1,listaProgresAcertPst.get(i).getCabeca());
            tronco.set(i+1,listaProgresAcertPst.get(i).getTronco());
            bracos.set(i+1,listaProgresAcertPst.get(i).getBracos());
            pernas.set(i+1,listaProgresAcertPst.get(i).getPernas());
            quedagem.set(i+1,listaProgresAcertPst.get(i).getQuedagem());
        
        }
         }else{
             cabeca.set("",0);
             bracos.set("",0);
             tronco.set("",0);
             pernas.set("",0);
             quedagem.set("",0);
         }
       
        
        graf.addSeries(cabeca);
        graf.addSeries(pernas);
        graf.addSeries(tronco);
        graf.addSeries(bracos);
        graf.addSeries(quedagem);
        return graf;
    }

public void graficosPizzaMetricas(int cabeca, int bracos, int pernas, int tronco, int quedagem,
                                  int cabecaPst, int bracosPst, int pernasPst, int troncoPst, int quedagemPst){
    
    grafPizzaMetNgts = new PieChartModel();
    grafPizzaMetPst = new PieChartModel();
    
    grafPizzaMetNgts.set("Cabeça",cabeca);
    grafPizzaMetNgts.set("Braços",bracos);
    grafPizzaMetNgts.set("Pernas",pernas);
    grafPizzaMetNgts.set("Tronco",tronco);
    grafPizzaMetNgts.set("Quedagem",quedagem);
    
    grafPizzaMetNgts.setTitle("Maiores danos recebidos nos confrontos");
    grafPizzaMetNgts.setLegendPosition("e");
    grafPizzaMetNgts.setFill(false);
    grafPizzaMetNgts.setShowDataLabels(true);
    grafPizzaMetNgts.setDiameter(150);
    
    
    grafPizzaMetPst.set("Cabeça",cabecaPst);
    grafPizzaMetPst.set("Braços",bracosPst);
    grafPizzaMetPst.set("Pernas",pernasPst);
    grafPizzaMetPst.set("Tronco",troncoPst);
    grafPizzaMetPst.set("Quedagem",quedagemPst);
    
    grafPizzaMetPst.setTitle("Menores danos aplicados nos confrontos");
    grafPizzaMetPst.setLegendPosition("e");
    grafPizzaMetPst.setFill(false);
    grafPizzaMetPst.setShowDataLabels(true);
    grafPizzaMetPst.setDiameter(150);
}

/////////////// Metodos links ////////////////////

public void linkTops() throws IOException{
    
  
    FacesContext.getCurrentInstance().getExternalContext().redirect("./RelatorioTopsTRN.xhtml");
    
} 

public void linkMetricas() throws IOException{
    
  
    FacesContext.getCurrentInstance().getExternalContext().redirect("./MetricasAtletas.xhtml");
    
} 


public void linkNumeros() throws IOException{
    
  
    FacesContext.getCurrentInstance().getExternalContext().redirect("./NumerosDoEvento.xhtml");
    
} 
///////////////////////////////////////////////////////// Métodos métricas atletas //////////////////////////////////////////////

/////////////// Métodos Consultas ////////////////
public void consultaAtletas()throws SQLException{
    
    if(codEvento == 0){
FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "Selecione um evento antes de realizar a consulta dos atlétas."));        
    }else{
    
         if(codAtletaSelecionado == 0 && nomeAtletaSelecionado == ""){
        
             
         listaAtletas = new Atletas_dao().consultaGeralAtleta(codAtletaSelecionado, nomeAtletaSelecionado,codEvento,0);    
            if(listaAtletas.isEmpty()){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "A consulta não retornou nenhum valor"));
            }
         }else{
        
         listaAtletas = new Atletas_dao().consultaGeralAtleta(codAtletaSelecionado, nomeAtletaSelecionado,codEvento,1);
         
           if(listaAtletas.isEmpty()){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "A consulta não retornou nenhum valor"));
            }  
    
              }
         
         
         }
}

public void consultaMetricas()throws SQLException{
    
    Relatorios_dao dao= new Relatorios_dao();
    
    metricasPST = dao.consultaMetricasPST(codAtleta,codEvento);
    metricasNGT = dao.consultaMetricasNGT(codAtleta,codEvento); 
    listaComentsArbPst = dao.consultaComentsArbPst(codAtleta, codEvento);
    listaComentsArbNgt = dao.consultaComentsArbNgt(codAtleta, codEvento);
    listaResultsConfPst = dao.graficoResultsConfPst(codAtleta, codEvento);
    listaResultsConfNgt = dao.graficoResultsConfNgt(codEvento, codAtleta);
    
    
    consultaTotaisGraficosMetricas();
    calculaProgressAtl();
}

public void calculaProgressAtl()throws SQLException{
    Relatorios_dao dao= new Relatorios_dao();
    List<Integer> eventos = new ArrayList<>();
    List<Relatorios_bean> impacPst = new ArrayList<>();
    List<Relatorios_bean> impacNgt = new ArrayList<>();
    impacPst = dao.consultaTotImpactoPst(codEvento, codAtleta);
    impacNgt = dao.consultaTotImpactoNgt(codEvento, codAtleta);
    eventos = dao.consultaCodEventos(codEvento);
    int eventAnte = eventos.get(1);
    
    int cabeca = dao.graficoTotalNgtCabeca(eventAnte, codAtleta);
    int bracos = dao.graficoTotalNgtBracos(eventAnte, codAtleta);
    int pernas = dao.graficoTotalNgtPernas(eventAnte, codAtleta);
    int quedagem = dao.graficoTotalNgtQuedagem(eventAnte, codAtleta);
    int tronco = dao.graficoTotalNgtTronco(eventAnte, codAtleta); 
    
    int cabecaPst = dao.graficoTotalPstCabeca(eventAnte, codAtleta);
    int bracosPst = dao.graficoTotalPstBracos(eventAnte, codAtleta);
    int pernasPst = dao.graficoTotalPstPernas(eventAnte, codAtleta);
    int quedagemPst = dao.graficoTotalPstQuedagem(eventAnte, codAtleta);
    int troncoPst = dao.graficoTotalPstTronco(eventAnte, codAtleta);
    
    DecimalFormat df = new DecimalFormat("0.00");
    
    for(int i = 0; i < impacPst.size(); i++){
    //Positivos caso as variaveis declaradas não forem maiores que as carregadas no array, que representam o rendimento atual, forem maiores que a do ultimo evento
    
    if(cabecaPst > impacPst.get(i).getProgCabecaPst()){
        progCabecaNgt = df.format((cabecaPst/impacPst.get(i).getProgCabecaPst()) * 100);
       
    }else{
        if((((impacPst.get(i).getProgCabecaPst()-cabecaPst)/impacPst.get(i).getProgCabecaPst()) * 100)>=100){
        progCabecaPst = df.format(0);
    }else{
        progCabecaPst = df.format(((impacPst.get(i).getProgCabecaPst()-cabecaPst)/impacPst.get(i).getProgCabecaPst()) * 100);    
        }
        
       
    }
    if(troncoPst > impacPst.get(i).getProgTroncoPst()){
        progTroncoNgt = df.format(((troncoPst - impacPst.get(i).getProgTroncoPst())/impacPst.get(i).getProgTroncoPst())*100);
    }else{
        if((((impacPst.get(i).getProgTroncoPst()-troncoPst)/impacPst.get(i).getProgTroncoPst())*100)>=100){
            progTroncoPst = df.format(0);
        }else{
        progTroncoPst = df.format(((impacPst.get(i).getProgTroncoPst()-troncoPst)/impacPst.get(i).getProgTroncoPst())*100);    
        }
        
    }
    if(bracosPst > impacPst.get(i).getProgBracoPst()){
        
        progBracoNgt = df.format(((bracosPst - impacPst.get(i).getProgBracoPst())/impacPst.get(i).getProgBracoPst())*100);
        
    }else{
        if((((impacPst.get(i).getProgBracoPst()-bracosPst)/impacPst.get(i).getProgBracoPst())*100)>=100){
            progBracoPst = df.format(0);
        }else{
        progBracoPst = df.format(((impacPst.get(i).getProgBracoPst()-bracosPst)/impacPst.get(i).getProgBracoPst())*100);
        }
    }
    if(pernasPst > impacPst.get(i).getProgPernaPst()){
        progPernaNgt = df.format(((pernasPst - impacPst.get(i).getProgPernaPst())/impacPst.get(i).getProgPernaPst())*100);
    }else{
        if((((impacPst.get(i).getProgPernaPst()-pernasPst)/impacPst.get(i).getProgPernaPst())*100)>=100){
            progPernaPst = df.format(0);
        }else{
        progPernaPst = df.format(((impacPst.get(i).getProgPernaPst()-pernasPst)/impacPst.get(i).getProgPernaPst())*100);
        }
    }
    if(quedagemPst > impacPst.get(i).getProgQuedagemPst()){
        progQuedagemNgt = df.format(((quedagemPst - impacPst.get(i).getProgQuedagemPst())/impacPst.get(i).getProgQuedagemPst())*100);
    }else{
        if((((impacPst.get(i).getProgQuedagemPst() - quedagemPst)/impacPst.get(i).getProgQuedagemPst())*100)>=100){
            progQuedagemPst = df.format(0);
        }else{
        progQuedagemPst = df.format(((impacPst.get(i).getProgQuedagemPst() - quedagemPst)/impacPst.get(i).getProgQuedagemPst())*100);
        }
         }
    
    }
    
    
    for(int i = 0; i < impacNgt.size();i++){
            
    //Negativos
        
    if(cabeca > impacNgt.get(i).getProgCabecaNgt()){
        progCabecaNegPst = df.format(((cabeca - impacNgt.get(i).getProgCabecaNgt())/impacNgt.get(i).getProgCabecaNgt())*100);
    }else{
        if((((impacNgt.get(i).getProgCabecaNgt()-cabeca)/impacNgt.get(i).getProgCabecaNgt())*100)>=100){
            progCabecaPstNgt = df.format(0);
        }else{
        progCabecaPstNgt = df.format(((impacNgt.get(i).getProgCabecaNgt()-cabeca)/impacNgt.get(i).getProgCabecaNgt())*100);
        }
    }
    if(tronco > impacNgt.get(i).getProgTroncoNgt()){
        progTroncoPstNgt = df.format(((tronco - impacNgt.get(i).getProgTroncoNgt())/impacNgt.get(i).getProgTroncoNgt())*100);
    }else{
        if((((impacNgt.get(i).getProgTroncoNgt() - tronco)/impacNgt.get(i).getProgTroncoNgt())*100)>=100){
            progTroncoPstNgt = df.format(0);
        }else{
        progTroncoPstNgt = df.format(((impacNgt.get(i).getProgTroncoNgt() - tronco)/impacNgt.get(i).getProgTroncoNgt())*100);
        }
    }
    if(bracos > impacNgt.get(i).getProgBracoNgt()){
        progBracoNgt = df.format(((bracos - impacNgt.get(i).getProgBracoNgt())/impacNgt.get(i).getProgBracoNgt())*100);
    }else{
        if((((impacNgt.get(i).getProgBracoNgt() - bracos)/impacNgt.get(i).getProgBracoNgt())*100)>=100){
            progBracoPstNgt = df.format(0);
        }else{
        progBracoPstNgt = df.format(((impacNgt.get(i).getProgBracoNgt() - bracos)/impacNgt.get(i).getProgBracoNgt())*100);
        }
    }
    if(pernas > impacNgt.get(i).getProgPernaNgt()){
        progPernaNegPst = df.format(((pernas - impacNgt.get(i).getProgPernaNgt())/impacNgt.get(i).getProgPernaNgt())*100);
    }else{
        if((((impacNgt.get(i).getProgPernaNgt() - pernas)/impacNgt.get(i).getProgPernaNgt())*100)>=100){
            progPernaPstNgt = df.format(0);
        }else{
        progPernaPstNgt = df.format(((impacNgt.get(i).getProgPernaNgt() - pernas)/impacNgt.get(i).getProgPernaNgt())*100);
        }
    }
    if(quedagem > impacNgt.get(i).getProgQuedagemNgt()){
        progQuedagemNegPst = df.format(((quedagem - impacNgt.get(i).getProgQuedagemNgt())/impacNgt.get(i).getProgQuedagemNgt())*100);
    }else{
        if((((impacNgt.get(i).getProgQuedagemNgt() - quedagem)/impacNgt.get(i).getProgQuedagemNgt())*100)>=100){
            progQuedagemPstNgt = df.format(0);
        }else{
        progQuedagemPstNgt = df.format(((impacNgt.get(i).getProgQuedagemNgt() - quedagem)/impacNgt.get(i).getProgQuedagemNgt())*100);
        }
    }
    
   } 
}


/////////////// Métodos adicionais ////////////////

public void linhaSelecionadaAtleta(){
    
    codAtletaSelecionado = atletaSelecionado.getCodAtleta();
    nomeAtletaSelecionado = atletaSelecionado.getNomeAtleta();
    
    codAtleta = codAtletaSelecionado;
    nomeAtleta = nomeAtletaSelecionado;
    
}

///////////////////////////////////////////////////////////////// MÉTODOS NÚMEROS DO EVENTO ///////////////////////////////////////////////////////

/////// Métodos consulta ///////

public void consultaInfIniciaisNumEve()throws SQLException{
    Relatorios_dao dao = new Relatorios_dao();
    List<Integer> eventos = new ArrayList<>();
    if(codEveSelecionado.equals("")){
        codEvento = 0;
        qtdPrtInscGeral = 0;
        qtdSegmentos = 0;
        mediaPrtInscSeg = 0;
        qtdCat = 0;
        mediaArbSeg = 0;
        qtdChv = 0;
        
        qtdPrtInscGeralAnt = 0;
        qtdSegmentosAnt = 0;
        mediaPrtInscSegAnt = 0;
        qtdCatAnt = 0;
        mediaArbSegAnt = 0;
        qtdChvAnt = 0;
    }else{
    // Evento atual
        
    codEvento = Integer.valueOf(codEveSelecionado.substring(0,codEveSelecionado.indexOf(" ")));   
    listaQtdPrtInscs = dao.consultaQtdIncs(codEvento);
    listaQtdSegmentos = dao.consultaQtdSegs(codEvento);
    listaQtdCat = dao.consultaQtdCat(codEvento);
    listaQtdArb = dao.consultaQtdArb(codEvento);
    listaQtdChv = dao.consultaQtdChv(codEvento);
    
    int qtdSeg = 0;
    int dif = 0;
    for(int i = 0; i < listaQtdPrtInscs.size();i++){
        if(listaQtdPrtInscs.get(i).getCodSegmento() != dif){
            qtdSeg +=1;
            dif = listaQtdPrtInscs.get(i).getCodSegmento();
        }
    }
    
    int qtdSegArb = 0;
    dif = 0;
    for(int i = 0; i < listaQtdArb.size();i++){
        if(listaQtdArb.get(i).getCodSegmento() != dif){
            qtdSegArb +=1;
            dif = listaQtdArb.get(i).getCodSegmento();
        }
    }
    
    
    qtdPrtInscGeral = listaQtdPrtInscs.size();
    qtdSegmentos = listaQtdSegmentos.size();
    if(listaQtdPrtInscs.size() != 0){
    mediaPrtInscSeg = listaQtdPrtInscs.size()/qtdSeg;    
    }else{
    mediaPrtInscSeg = 0;        
    }
    qtdCat = listaQtdCat.size();
    if(listaQtdArb.size() != 0){
    mediaArbSeg = listaQtdArb.size()/qtdSegArb;
    }else{
    mediaArbSeg = 0;    
    }
    qtdChv = listaQtdChv.size();
     
    // evento anterior
    
    eventos = dao.consultaCodEventos(codEvento);
    listaQtdPrtInscs = dao.consultaQtdIncs(eventos.get(1));
    listaQtdSegmentos = dao.consultaQtdSegs(eventos.get(1));
    listaQtdCat = dao.consultaQtdCat(eventos.get(1));
    listaQtdArb = dao.consultaQtdArb(eventos.get(1));
    listaQtdChv = dao.consultaQtdChv(eventos.get(1));
        
    qtdSeg = 0;
    dif = 0;
    for(int i = 0; i < listaQtdPrtInscs.size();i++){
        if(listaQtdPrtInscs.get(i).getCodSegmento() != dif){
            qtdSeg +=1;
            dif = listaQtdPrtInscs.get(i).getCodSegmento();
        }
    }
    
    qtdSegArb = 0;
    dif = 0;
    for(int i = 0; i < listaQtdArb.size();i++){
        if(listaQtdArb.get(i).getCodSegmento() != dif){
            qtdSegArb +=1;
            dif = listaQtdArb.get(i).getCodSegmento();
        }
    }
    
    
    qtdPrtInscGeralAnt = listaQtdPrtInscs.size();
    qtdSegmentosAnt = listaQtdSegmentos.size();
    if(listaQtdPrtInscs.size() != 0){
    mediaPrtInscSegAnt = listaQtdPrtInscs.size()/qtdSeg;    
    }else{
    mediaPrtInscSegAnt = 0;        
    }
    qtdCatAnt = listaQtdCat.size();
    if(listaQtdArb.size() != 0){
    mediaArbSegAnt = listaQtdArb.size()/qtdSegArb;
    }else{
    mediaArbSegAnt = 0;    
    }
    qtdChvAnt = listaQtdChv.size();
    

    }
    
    // Grafico de barras 
    criarModeloDeBarras();
    // grafico de pizza
    graficosPizzaSexosParts();
    // Progresso de um evento para o outro
     int eveAtu = qtdPrtInscGeral+qtdSegmentos+mediaPrtInscSeg+qtdCat+mediaArbSeg+qtdChv;
     int eveAnt = qtdPrtInscGeralAnt+qtdSegmentosAnt+mediaPrtInscSegAnt+qtdCatAnt+mediaArbSegAnt+qtdChvAnt;
     if(eveAtu > eveAnt){
         crescEve = ((eveAtu - eveAnt)/(float)eveAtu)*100;
     }else if(eveAtu < eveAnt){
         crescEve = -((eveAnt - eveAtu)/(float)eveAnt)*100;
     }
} 

public void graficosPizzaSexosParts()throws SQLException{
    
    
    grafPizzaSexoParts = new PieChartModel();
    
    grafPizzaSexoParts.set("Masculino",new Relatorios_dao().consultaTotSexoMasc(codEvento));
    grafPizzaSexoParts.set("Feminino",new Relatorios_dao().consultaTotSexoFem(codEvento));
    
    grafPizzaSexoParts.setTitle("Divisão de sexo dos participantes");
    grafPizzaSexoParts.setLegendPosition("e");
    grafPizzaSexoParts.setFill(false);
    grafPizzaSexoParts.setShowDataLabels(true);
    grafPizzaSexoParts.setDiameter(150);
    
}  

public BarChartModel graficoBarrasComparaEves(){
    BarChartModel graf = new BarChartModel();
    
    ChartSeries eventAnt = new ChartSeries();
    ChartSeries eventAtu = new ChartSeries();
    
    
    eventAnt.setLabel("Evento anterior");
    eventAtu.setLabel("Evento atual");
    
    
        eventAtu.set(" ",qtdPrtInscGeral+qtdSegmentos+mediaPrtInscSeg+qtdCat+mediaArbSeg+qtdChv);
        eventAnt.set(" ",qtdPrtInscGeralAnt+qtdSegmentosAnt+mediaPrtInscSegAnt+qtdCatAnt+mediaArbSegAnt+qtdChvAnt);
    
        graf.addSeries(eventAtu);
        graf.addSeries(eventAnt);
    
    
    return graf;
}

public void calculaProgFutEve()throws SQLException{
    
    if(!percEstProg.equals("")){
    qtdPrtInscGeralFut =qtdPrtInscGeral + ((int)(qtdPrtInscGeral*Float.valueOf(percEstProg.replace(",", ".").replace("%", "").replace(" ", "")))/100);
    mediaPrtInscSegFut =mediaPrtInscSeg+ ((int)(mediaPrtInscSeg*Float.valueOf(percEstProg.replace(",", ".").replace("%", "").replace(" ", "")))/100);
    qtdChvFut =qtdChv+ ((int)(qtdChv*Float.valueOf(percEstProg.replace(",", ".").replace("%", "").replace(" ", "")))/100);
    qtdFemEvFut = new Relatorios_dao().consultaTotSexoFem(codEvento) + 
                 ((int)(new Relatorios_dao().consultaTotSexoFem(codEvento)*Float.valueOf(percEstProg.replace(",", ".").replace("%", "").replace(" ", "")))/100);
    qtdMascEvFut = new Relatorios_dao().consultaTotSexoMasc(codEvento) + 
                 ((int)(new Relatorios_dao().consultaTotSexoMasc(codEvento)*Float.valueOf(percEstProg.replace(",", ".").replace("%", "").replace(" ", "")))/100);
   
    if(!valInsc.equals("")){
    lucroFut = Float.valueOf(valInsc.replace("R", "").replace("$", "").replace(",", "."))*qtdPrtInscGeralFut;
    }
    }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public int getTotais() {
        return totais;
    }

    public void setTotais(int totais) {
        this.totais = totais;
    }
    
    public int getQtdChvs() {
        return qtdChvs;
    }

    public void setQtdChvs(int qtdChvs) {
        this.qtdChvs = qtdChvs;
    }

    public int getCodModali() {
        return codModali;
    }

    public void setCodModali(int codModali) {
        this.codModali = codModali;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getStatusEvento() {
        return statusEvento;
    }

    public void setStatusEvento(String statusEvento) {
        this.statusEvento = statusEvento;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getCodSegmento() {
        return codSegmento;
    }

    public void setCodSegmento(int codSegmento) {
        this.codSegmento = codSegmento;
    }

    public int getCodAtleta() {
        return codAtleta;
    }

    public void setCodAtleta(int codAtleta) {
        this.codAtleta = codAtleta;
    }

    public String getNomeAtleta() {
        return nomeAtleta;
    }

    public void setNomeAtleta(String nomeAtleta) {
        this.nomeAtleta = nomeAtleta;
    }

    public int getIdadeAtleta() {
        return idadeAtleta;
    }

    public void setIdadeAtleta(int idadeAtleta) {
        this.idadeAtleta = idadeAtleta;
    }

    public float getPesoAtleta() {
        return pesoAtleta;
    }

    public void setPesoAtleta(float pesoAtleta) {
        this.pesoAtleta = pesoAtleta;
    }

    public String getGraduacaoAtleta() {
        return graduacaoAtleta;
    }

    public void setGraduacaoAtleta(String graduacaoAtleta) {
        this.graduacaoAtleta = graduacaoAtleta;
    }

    public String getCpfAtleta() {
        return cpfAtleta;
    }

    public void setCpfAtleta(String cpfAtleta) {
        this.cpfAtleta = cpfAtleta;
    }

    public String getEmailAtleta() {
        return emailAtleta;
    }

    public void setEmailAtleta(String emailAtleta) {
        this.emailAtleta = emailAtleta;
    }

    public int getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(int codCategoria) {
        this.codCategoria = codCategoria;
    }

    public float getPesoMinCategoria() {
        return pesoMinCategoria;
    }

    public void setPesoMinCategoria(float pesoMinCategoria) {
        this.pesoMinCategoria = pesoMinCategoria;
    }

    public float getPesoMaxCategoria() {
        return pesoMaxCategoria;
    }

    public void setPesoMaxCategoria(float pesoMaxCategoria) {
        this.pesoMaxCategoria = pesoMaxCategoria;
    }

    public int getGraduacaoMinCategoria() {
        return graduacaoMinCategoria;
    }

    public void setGraduacaoMinCategoria(int graduacaoMinCategoria) {
        this.graduacaoMinCategoria = graduacaoMinCategoria;
    }

    public int getGraduacaoMaxCategoria() {
        return graduacaoMaxCategoria;
    }

    public void setGraduacaoMaxCategoria(int graduacaoMaxCategoria) {
        this.graduacaoMaxCategoria = graduacaoMaxCategoria;
    }

    public int getIdadeMinCategoria() {
        return idadeMinCategoria;
    }

    public void setIdadeMinCategoria(int idadeMinCategoria) {
        this.idadeMinCategoria = idadeMinCategoria;
    }

    public int getIdadeMaxCategoria() {
        return idadeMaxCategoria;
    }

    public void setIdadeMaxCategoria(int idadeMaxCategoria) {
        this.idadeMaxCategoria = idadeMaxCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public int getCodChvConf() {
        return codChvConf;
    }

    public void setCodChvConf(int codChvConf) {
        this.codChvConf = codChvConf;
    }

    public String getNomeChvConf() {
        return nomeChvConf;
    }

    public void setNomeChvConf(String nomeChvConf) {
        this.nomeChvConf = nomeChvConf;
    }

    public String getStatusChvConf() {
        return statusChvConf;
    }

    public void setStatusChvConf(String statusChvConf) {
        this.statusChvConf = statusChvConf;
    }

    public int getQtdRodadasChvConf() {
        return qtdRodadasChvConf;
    }

    public void setQtdRodadasChvConf(int qtdRodadasChvConf) {
        this.qtdRodadasChvConf = qtdRodadasChvConf;
    }

    public int getCodInscricao() {
        return codInscricao;
    }

    public void setCodInscricao(int codInscricao) {
        this.codInscricao = codInscricao;
    }

    public int getTotPontosPositivos() {
        return totPontosPositivos;
    }

    public void setTotPontosPositivos(int totPontosPositivos) {
        this.totPontosPositivos = totPontosPositivos;
    }

    public int getTotPontosNegativos() {
        return totPontosNegativos;
    }

    public void setTotPontosNegativos(int totPontosNegativos) {
        this.totPontosNegativos = totPontosNegativos;
    }

    public int getNumRodada() {
        return numRodada;
    }

    public void setNumRodada(int numRodada) {
        this.numRodada = numRodada;
    }

    public String getParteDoCorpoNGT() {
        return parteDoCorpoNGT;
    }

    public void setParteDoCorpoNGT(String parteDoCorpoNGT) {
        this.parteDoCorpoNGT = parteDoCorpoNGT;
    }

    public String getParteDoCorpoPST() {
        return parteDoCorpoPST;
    }

    public void setParteDoCorpoPST(String parteDoCorpoPST) {
        this.parteDoCorpoPST = parteDoCorpoPST;
    }

    public String getDescricaoCBT() {
        return descricaoCBT;
    }

    public void setDescricaoCBT(String descricaoCBT) {
        this.descricaoCBT = descricaoCBT;
    }

    public String getFinalCBT() {
        return finalCBT;
    }

    public void setFinalCBT(String finalCBT) {
        this.finalCBT = finalCBT;
    }

    public int getCodEvento() {
        return codEvento;
    }

    public void setCodEvento(int codEvento) {
        this.codEvento = codEvento;
    }

    public String getNomeSegmento() {
        return nomeSegmento;
    }

    public void setNomeSegmento(String nomeSegmento) {
        this.nomeSegmento = nomeSegmento;
    }

    public String getDescricaoSegmento() {
        return descricaoSegmento;
    }

    public void setDescricaoSegmento(String descricaoSegmento) {
        this.descricaoSegmento = descricaoSegmento;
    }

    public String getCodEveSelecionado() {
        return codEveSelecionado;
    }

    public void setCodEveSelecionado(String codEveSelecionado) {
        this.codEveSelecionado = codEveSelecionado;
    }

    public String getCodSegSelecionado() {
        return codSegSelecionado;
    }

    public void setCodSegSelecionado(String codSegSelecionado) {
        this.codSegSelecionado = codSegSelecionado;
    }

    public List<String> getComboSegs() {
        return ComboSegs;
    }

    public void setComboSegs(List<String> ComboSegs) {
        this.ComboSegs = ComboSegs;
    }

    public List<String> getComboEve() {
        return comboEve;
    }

    public void setComboEve(List<String> comboEve) {
        this.comboEve = comboEve;
    }

    public List<Relatorios_bean> getListaTops() {
        return listaTops;
    }

    public void setListaTops(List<Relatorios_bean> listaTops) {
        this.listaTops = listaTops;
    }

    public PieChartModel getGrafPizzaTops() {
        return grafPizzaTops;
    }

    public void setGrafPizzaTops(PieChartModel grafPizzaTops) {
        this.grafPizzaTops = grafPizzaTops;
    }

    public List<Relatorios_bean> getChavesAberto() {
        return chavesAberto;
    }

    public void setChavesAberto(List<Relatorios_bean> chavesAberto) {
        this.chavesAberto = chavesAberto;
    }

    public List<Relatorios_bean> getChaves() {
        return chaves;
    }

    public void setChaves(List<Relatorios_bean> chaves) {
        this.chaves = chaves;
    }

    public List<Segmentos_bean> getListaSegs() {
        return listaSegs;
    }

    public void setListaSegs(List<Segmentos_bean> listaSegs) {
        this.listaSegs = listaSegs;
    }

    public Relatorios_bean getAtletaSelecionado() {  
        return atletaSelecionado;
    }

    public void setAtletaSelecionado(Relatorios_bean atletaSelecionado) {
        this.atletaSelecionado = atletaSelecionado;
    }

    public List<Relatorios_bean> getListaAtletas() {
        return listaAtletas;
    }

    public void setListaAtletas(List<Relatorios_bean> listaAtletas) {
        this.listaAtletas = listaAtletas;
    }

    public int getCodAtletaSelecionado() {
        return codAtletaSelecionado;
    }

    public void setCodAtletaSelecionado(int codAtletaSelecionado) {
        this.codAtletaSelecionado = codAtletaSelecionado;
    }

    public String getNomeAtletaSelecionado() {
        return nomeAtletaSelecionado;
    }

    public void setNomeAtletaSelecionado(String nomeAtletaSelecionado) {
        this.nomeAtletaSelecionado = nomeAtletaSelecionado;
    }

    public List<Relatorios_bean> getMetricasPST() {
        return metricasPST;
    }

    public void setMetricasPST(List<Relatorios_bean> metricasPST) {
        this.metricasPST = metricasPST;
    }

    public List<Relatorios_bean> getMetricasNGT() {
        return metricasNGT;
    }

    public void setMetricasNGT(List<Relatorios_bean> metricasNGT) {
        this.metricasNGT = metricasNGT;
    }

    public List<Relatorios_bean> getListaComentsArbPst() {
        return listaComentsArbPst;
    }

    public void setListaComentsArbPst(List<Relatorios_bean> listaComentsArbPst) {
        this.listaComentsArbPst = listaComentsArbPst;
    }

    public List<Relatorios_bean> getListaComentsArbNgt() {
        return listaComentsArbNgt;
    }

    public void setListaComentsArbNgt(List<Relatorios_bean> listaComentsArbNgt) {
        this.listaComentsArbNgt = listaComentsArbNgt;
    }

    public PieChartModel getGrafPizzaMetNgts() {
        return grafPizzaMetNgts;
    }

    public PieChartModel getGrafPizzaMetPst() {
        return grafPizzaMetPst;
    }

    public LineChartModel getGrafLinhaMetAcertNgt() {
        return grafLinhaMetAcertNgt;
    }

    public LineChartModel getGrafLinhaMetAcertPst() {
        return grafLinhaMetAcertPst;
    }

    public BarChartModel getGrafBarResultsConfPst() {
        return grafBarResultsConfPst;
    }

    public BarChartModel getGrafBarResultsConfNgt() {
        return grafBarResultsConfNgt;
    }

    public List<Relatorios_bean> getListaProgresAcertNgt() {
        return listaProgresAcertNgt;
    }

    public void setListaProgresAcertNgt(List<Relatorios_bean> listaProgresAcertNgt) {
        this.listaProgresAcertNgt = listaProgresAcertNgt;
    }

    public List<Relatorios_bean> getListaProgresAcertPst() { 
        return listaProgresAcertPst;
    }

    public void setListaProgresAcertPst(List<Relatorios_bean> listaProgresAcertPst) {
        this.listaProgresAcertPst = listaProgresAcertPst;
    }

    public List<Relatorios_bean> getListaResultsConfPst() {
        return listaResultsConfPst;
    }

    public void setListaResultsConfPst(List<Relatorios_bean> listaResultsConfPst) {
        this.listaResultsConfPst = listaResultsConfPst;
    }

    public List<Relatorios_bean> getListaResultsConfNgt() {
        return listaResultsConfNgt;
    }

    public void setListaResultsConfNgt(List<Relatorios_bean> listaResultsConfNgt) {
        this.listaResultsConfNgt = listaResultsConfNgt;
    }

    public String getProgCabecaPst() {
        return progCabecaPst;
    }

    public String getProgTroncoPst() {
        return progTroncoPst;
    }

    public String getProgBracoPst() {
        return progBracoPst;
    }

    public String getProgPernaPst() {
        return progPernaPst;
    }

    public String getProgQuedagemPst() {
        return progQuedagemPst;
    }

    public String getRendimentoPst() {
        return rendimentoPst;
    }

    public String getProgCabecaNegPst() {
        return progCabecaNegPst;
    }

    public String getProgTroncoNegPst() {
        return progTroncoNegPst;
    }

    public String getProgBracoNegPst() {
        return progBracoNegPst;
    }

    public String getProgPernaNegPst() {
        return progPernaNegPst;
    }

    public String getProgQuedagemNegPst() {
        return progQuedagemNegPst;
    }

    public String getProgCabecaNgt() {
        return progCabecaNgt;
    }

    public String getProgTroncoNgt() {
        return progTroncoNgt;
    }

    public String getProgBracoNgt() {
        return progBracoNgt;
    }

    public String getProgPernaNgt() {
        return progPernaNgt;
    }

    public String getProgQuedagemNgt() {
        return progQuedagemNgt;
    }

    public String getRendimentoNgt() {
        return rendimentoNgt;
    }

    public String getProgCabecaPstNgt() {
        return progCabecaPstNgt;
    }

    public String getProgTroncoPstNgt() {
        return progTroncoPstNgt;
    }

    public String getProgBracoPstNgt() {
        return progBracoPstNgt;
    }

    public String getProgPernaPstNgt() {
        return progPernaPstNgt;
    }

    public String getProgQuedagemPstNgt() {
        return progQuedagemPstNgt;
    }

    public int getQtdPrtInscGeral() {
        return qtdPrtInscGeral;
    }

    public int getQtdSegmentos() {
        return qtdSegmentos;
    }

    public int getMediaPrtInscSeg() {
        return mediaPrtInscSeg;
    }

    public int getQtdCat() {
        return qtdCat;
    }

    public int getMediaArbSeg() {
        return mediaArbSeg;
    }

    public int getQtdChv() {
        return qtdChv;
    }

    public int getQtdPrtInscGeralAnt() {
        return qtdPrtInscGeralAnt;
    }

    public int getQtdSegmentosAnt() {
        return qtdSegmentosAnt;
    }

    public int getMediaPrtInscSegAnt() {
        return mediaPrtInscSegAnt;
    }

    public int getQtdCatAnt() {
        return qtdCatAnt;
    }

    public int getMediaArbSegAnt() {
        return mediaArbSegAnt;
    }

    public int getQtdChvAnt() {
        return qtdChvAnt;
    }

    public Float getCrescEve() {
        return crescEve;
    }

    public BarChartModel getGrafBarComparaNumEve() {
        return grafBarComparaNumEve;
    }

    public PieChartModel getGrafPizzaSexoParts() {
        return grafPizzaSexoParts;
    }

    public int getQtdPrtInscGeralFut() {
        return qtdPrtInscGeralFut;
    }

    public int getQtdSegmentosFut() {
        return qtdSegmentosFut;
    }

    public int getMediaPrtInscSegFut() {
        return mediaPrtInscSegFut;
    }

    public int getQtdCatFut() {
        return qtdCatFut;
    }

    public int getMediaArbSegFut() {
        return mediaArbSegFut;
    }

    public int getQtdChvFut() {
        return qtdChvFut;
    }

    public int getQtdFemEvFut() {
        return qtdFemEvFut;
    }

    public int getQtdMascEvFut() {
        return qtdMascEvFut;
    }

    public float getLucroFut() {
        return lucroFut;
    }

    public String getValInsc() {
        return valInsc;
    }

    public String getPercEstProg() {
        return percEstProg;
    }

    public void setValInsc(String valInsc) {
        this.valInsc = valInsc;
    }

    public void setPercEstProg(String percEstProg) {
        this.percEstProg = percEstProg;
    }
    
    
}
