
package br.tcc.controller;

import br.tcc.bean.Arbitros_bean;
import br.tcc.bean.MetricasCBT_bean;
import br.tcc.dao.Confrontos_dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author joãomarcos
 */
@ManagedBean(name="ArbitroManagedBean", eager = true)

@SessionScoped
public class ArbitroController {
    



private int codArbitro = 0;
private int codSegmento;
private String nomeArbitro;
private String graduacaoArbitro;
private int codPontua;
private String nomePontua;

private int codConf;
private int codConfArb;
private int codChvConf;
private int numRodada;
private int codInsc1;
private int codInsc2 ;
private String statusConf;
private String nomeAtl1;
private String nomeAtl2;


private String atletaSelec1;
private String atletaSelec2;
private String atletaSelecDef;
private String msgStatusConf= "Aguardando confirmação dos outros arbitros.";
int statusInicioConf;
private boolean skip;
private boolean boolAtlSelec1;
private boolean boolAtlSelec2;

private int totalPontPst;
private int totalPontNgt;
private String vencedor;
private String finalCBT;
private String descMetricas;


private List<Arbitros_bean> listaEstruConf = new ArrayList<>() ;  
private List<Arbitros_bean> chaveConf = new ArrayList<>() ;  
private List<Arbitros_bean> listaPontos = new ArrayList<>() ;  
private List<Arbitros_bean> listaPontosPstCbt = new ArrayList<>() ;   
private List<Arbitros_bean> listaPontosNgtCbt = new ArrayList<>() ;  
//private Confrontos_dao dao = new Confrontos_dao();
MetricasCBT_bean metCBTbean = new MetricasCBT_bean();

    public void criaEstrutura()throws SQLException{
    
        
        Confrontos_dao dao = new Confrontos_dao();
//    listaEstruConf = dao.consultaEstruConfArb(codArbitro);
        
        listaEstruConf = dao.consultaEstruConfArb(codArbitro);
        
}


public String proxSelecAtl(FlowEvent event) {

    
            atletaSelec1 = "";
            atletaSelec2 = "";
            codInsc1 = 0;
            codInsc2 = 0;
            codChvConf = 0;
        for(Arbitros_bean p: listaEstruConf){
        if(p.isSelected()){ 
            
    
            atletaSelec1 = p.getCodInsc1()+" - "+ p.getNomeAtl1();
            codInsc1 = p.getCodInsc1();
            
            atletaSelec2 = p.getCodInsc2()+" - "+p.getNomeAtl2();
            codInsc2 = p.getCodInsc2();
            codConf = p.getCodConf();
           
            codChvConf = p.getCodChvConf();
           // retorno = "confirm";
            
        }
 
    } 
    
    if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
            
        }
    }

public void confirmaInicioConf()throws SQLException{
    Confrontos_dao dao = new Confrontos_dao();
    
    if(boolAtlSelec1 == true){
        
       dao.confirmaAtl1(Integer.valueOf(atletaSelec1.substring(0,atletaSelec1.indexOf("-")).trim()), codConf);    
       dao.confirmaInicio(codConf);
    }else if(boolAtlSelec2 == true){
       
       dao.confirmaAtl2(Integer.valueOf(atletaSelec2.substring(0,atletaSelec2.indexOf("-")).trim()), codConf);    
       dao.confirmaInicio(codConf);
    }
}

public void verificaOkArb()throws SQLException{
    Confrontos_dao dao = new Confrontos_dao();
    int qtdTotArb = dao.verificaQtdArbtCBT(codConf);
    int qtdConfirms = dao.verificaConfrmsInicioCbt(codConf);
//    boolean confirm = dao.verificaOkInicCBT(codConf);
    
    if(qtdConfirms != 0 && qtdTotArb != 0){
     if(qtdTotArb == qtdConfirms ){
        msgStatusConf = "O confronto pode ser iniciado.";
    }   
    }else{
        msgStatusConf = "Aguardando confirmação dos outros arbitros.";
    }
     
}


public void okConf()throws SQLException{
    if(msgStatusConf.equals("O confronto pode ser iniciado.")){  
        Confrontos_dao dao = new Confrontos_dao();
        List<Arbitros_bean> arb = new ArrayList<>();
          
        arb = dao.consultaPontsArbCBT(codConf, codArbitro); 
        listaPontosNgtCbt.clear();
        listaPontosPstCbt.clear();
         
        
        for(int i = 0; i < arb.size(); i++){ 
            if(arb.get(i).getTipoPontua().equals("Positiva")){
                
                Arbitros_bean bean = new Arbitros_bean();
                
                bean.setCodPontua(arb.get(i).getCodPontua());
                bean.setNomePontua(arb.get(i).getNomePontua());
                bean.setTipoPontua(arb.get(i).getTipoPontua());
                bean.setValorPontua(arb.get(i).getValorPontua());
                bean.setDescricaoPontua(arb.get(i).getDescricaoPontua());
                bean.setParteDoCorpo(arb.get(i).getParteDoCorpo());
    
                listaPontosPstCbt.add(bean);
             
            }else{
                Arbitros_bean bean = new Arbitros_bean();
                
                bean.setCodPontua(arb.get(i).getCodPontua());
                bean.setNomePontua(arb.get(i).getNomePontua());
                bean.setTipoPontua(arb.get(i).getTipoPontua());
                bean.setValorPontua(arb.get(i).getValorPontua());
                bean.setDescricaoPontua(arb.get(i).getDescricaoPontua());
                bean.setParteDoCorpo(arb.get(i).getParteDoCorpo());
    
                listaPontosNgtCbt.add(bean);
                
                
            }
        }
        
    }else{
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "O confronto não pode se iniciar pois os arbitros designados não confirmarão sua participação. Aguarde por gentileza."));
    }
}

public void computarTotPontos(){
    int totPst = 0;
    int totNgt = 0;
    
    
    for(int i = 0; i< listaPontosPstCbt.size(); i++){
    
    totPst += listaPontosPstCbt.get(i).getTotPont();
        }
        
        for(int i = 0; i< listaPontosNgtCbt.size(); i++){
        
    totNgt += listaPontosNgtCbt.get(i).getTotPont();        
            
        }
        
        totalPontPst = totPst;  
             
}     

public void definicaoConf(){
//    System.out.println("\n\n---------------Método Definição conf---------------\n\n");
    
    int cabeca = 0;
    int tronco = 0;
    int quedagem = 0;
    int pernas = 0;
    int bracos = 0;
    String ptCorpoPST = "";
    
    int cabecaNgt = 0;
    int troncoNgt = 0;
    int quedagemNgt = 0;
    int pernasNgt = 0;
    int bracosNgt = 0;
    String ptCorpoNgt = "";
    
    // Essas variaveis representam a quantidade de vezes que o atleta foi atingido em uma determinada parte do corpo.
    int cabecaTotPst = 0;
    int troncoTotPst = 0;
    int quedagemTotPst = 0;
    int pernasTotPst = 0;
    int bracosTotPst = 0;
    
    int cabecaTotNgt = 0;
    int troncoTotNgt = 0;
    int quedagemTotNgt = 0;
    int pernasTotNgt = 0;
    int bracosTotNgt = 0;
    
    // pontos positivos
    for(int i = 0; i < listaPontosPstCbt.size(); i++){ 
        if(listaPontosPstCbt.get(i).getParteDoCorpo().equals("Cabeça")){
            cabeca += listaPontosPstCbt.get(i).getTotPont();
            
            cabecaTotPst += listaPontosPstCbt.get(i).getTotPont() / listaPontosPstCbt.get(i).getValorPontua(); 
          //  System.out.println("cabeça "+cabeca);
        }
        else if(listaPontosPstCbt.get(i).getParteDoCorpo().equals("Tronco")){
            tronco += listaPontosPstCbt.get(i).getTotPont();
            troncoTotPst += listaPontosPstCbt.get(i).getTotPont() / listaPontosPstCbt.get(i).getValorPontua();
        //    System.out.println("tronco "+tronco);
        }
        else if(listaPontosPstCbt.get(i).getParteDoCorpo().equals("Braços")){
            bracos += listaPontosPstCbt.get(i).getTotPont();
            bracosTotPst += listaPontosPstCbt.get(i).getTotPont() / listaPontosPstCbt.get(i).getValorPontua();
      //      System.out.println("braço "+bracos);
        }
        else if(listaPontosPstCbt.get(i).getParteDoCorpo().equals("Pernas")){
            pernas += listaPontosPstCbt.get(i).getTotPont();
            pernasTotPst += listaPontosPstCbt.get(i).getTotPont() / listaPontosPstCbt.get(i).getValorPontua();
    //        System.out.println("perna "+pernas);
        }
        else if(listaPontosPstCbt.get(i).getParteDoCorpo().equals("Quedagem")){
            quedagem += listaPontosPstCbt.get(i).getTotPont();
            quedagemTotPst += listaPontosPstCbt.get(i).getTotPont() / listaPontosPstCbt.get(i).getValorPontua();
  //          System.out.println("quedagem "+quedagem);
        }
       
        
    }
    
    //pontos negativos
    
    for(int i = 0; i < listaPontosNgtCbt.size(); i++){
        if(listaPontosNgtCbt.get(i).getParteDoCorpo().equals("Cabeça")){
            cabecaNgt += listaPontosNgtCbt.get(i).getTotPont();
            cabecaTotNgt += listaPontosNgtCbt.get(i).getTotPont() / listaPontosNgtCbt.get(i).getValorPontua();
//            System.out.println("cabeça "+cabecaNgt);
        }
        else if(listaPontosNgtCbt.get(i).getParteDoCorpo().equals("Tronco")){
            troncoNgt += listaPontosNgtCbt.get(i).getTotPont();
            troncoTotNgt += listaPontosNgtCbt.get(i).getTotPont() / listaPontosNgtCbt.get(i).getValorPontua();
            //System.out.println("tronco "+troncoNgt);
        }
        else if(listaPontosNgtCbt.get(i).getParteDoCorpo().equals("Braços")){
            bracosNgt += listaPontosNgtCbt.get(i).getTotPont();
            bracosTotNgt += listaPontosNgtCbt.get(i).getTotPont() / listaPontosNgtCbt.get(i).getValorPontua();
          //  System.out.println("braço "+bracosNgt);
        }
        else if(listaPontosNgtCbt.get(i).getParteDoCorpo().equals("Pernas")){
            pernasNgt += listaPontosNgtCbt.get(i).getTotPont();
            pernasTotNgt += listaPontosNgtCbt.get(i).getTotPont() / listaPontosNgtCbt.get(i).getValorPontua();
        //    System.out.println("perna "+pernasNgt);
        }
        else if(listaPontosNgtCbt.get(i).getParteDoCorpo().equals("Quedagem")){
            quedagemNgt += listaPontosNgtCbt.get(i).getTotPont();
            quedagemTotNgt += listaPontosNgtCbt.get(i).getTotPont() / listaPontosNgtCbt.get(i).getValorPontua();
      //      System.out.println("quedagem "+quedagemNgt);
        }
        
    }
    
    
    // definindo melhores pst
    
    if(cabeca > tronco && cabeca > bracos && cabeca > quedagem && cabeca > pernas){
        ptCorpoPST = "Cabeça";
    }
    else if(tronco > cabeca && tronco > bracos && tronco > quedagem && tronco > pernas){
        ptCorpoPST = "Tronco";
    }
    else if(bracos > tronco && bracos > cabeca && bracos > quedagem && bracos > pernas){
        ptCorpoPST = "Braços";
    }
    else if(pernas > tronco && pernas > bracos && pernas > quedagem && pernas > cabeca){
        ptCorpoPST = "Pernas";
    }
    else if(quedagem > tronco && quedagem > bracos && quedagem > cabeca && quedagem > pernas){
        ptCorpoPST = "Quedagem";
    }
    //System.out.println("Local mais atingiu: " +ptCorpoPST);
    
    // definindo melhores ngt
    
   if(cabecaNgt > troncoNgt && cabecaNgt > bracosNgt && cabecaNgt > quedagemNgt && cabecaNgt > pernasNgt){
        ptCorpoPST = "Cabeça";
    }
    else if(troncoNgt > cabecaNgt && troncoNgt > bracosNgt && troncoNgt > quedagemNgt && troncoNgt > pernasNgt){
        ptCorpoPST = "Tronco";
    }
    else if(bracosNgt > troncoNgt && bracosNgt > cabecaNgt && bracosNgt > quedagemNgt && bracosNgt > pernasNgt){
        ptCorpoPST = "Braços";
    } 
    else if(pernasNgt > troncoNgt && pernasNgt > bracosNgt && pernasNgt > quedagemNgt && pernasNgt > cabecaNgt){
        ptCorpoPST = "Pernas";
    }
    else if(quedagemNgt > troncoNgt && quedagemNgt > bracosNgt && quedagemNgt > cabecaNgt && quedagemNgt > pernasNgt){
        ptCorpoPST = "Quedagem";
    }
  //  System.out.println("Local mais atingido: " +ptCorpoNgt);
    
    if(vencedor.equals("vencedor")){
        if(boolAtlSelec1 == true){
            atletaSelecDef = atletaSelec1.substring(0,atletaSelec1.indexOf("-")).trim();
        }else{
            atletaSelecDef = atletaSelec2.substring(0,atletaSelec2.indexOf("-")).trim();
        }
    inserirMetricasCBTVencedor(ptCorpoPST, ptCorpoNgt, Integer.valueOf(atletaSelecDef)
    ,cabecaTotPst
    ,troncoTotPst
    ,quedagemTotPst
    ,pernasTotPst
    ,bracosTotPst
    
    ,cabecaTotNgt 
    ,troncoTotNgt
    ,quedagemTotNgt
    ,pernasTotNgt
    ,bracosTotNgt);
        
   // System.out.println("\nO atleta foi o vencedor "+atletaSelecDef);
    }else if(vencedor.equals("perdedor")){
        if(boolAtlSelec1 == true){
            atletaSelecDef = atletaSelec1.substring(0,atletaSelec1.indexOf("-")).trim();
        }else{
            atletaSelecDef = atletaSelec2.substring(0,atletaSelec2.indexOf("-")).trim();
        }
    inserirMetricasCBTPerdedor(ptCorpoPST, ptCorpoNgt, Integer.valueOf(atletaSelecDef)
    ,cabecaTotPst
    ,troncoTotPst
    ,quedagemTotPst
    ,pernasTotPst
    ,bracosTotPst
    
    ,cabecaTotNgt 
    ,troncoTotNgt
    ,quedagemTotNgt
    ,pernasTotNgt
    ,bracosTotNgt);    
    //System.out.println("\nO atleta foi o perdedor "+atletaSelecDef);
    }
    
}

public void inserirMetricasCBTVencedor(
     String parteCorpoPst
    ,String parteCorpoNgt
    ,int codInscVencedor
    
    ,int cabecaTotPst
    ,int troncoTotPst
    ,int quedagemTotPst
    ,int pernasTotPst
    ,int bracosTotPst
    
    ,int cabecaTotNgt 
    ,int troncoTotNgt
    ,int quedagemTotNgt
    ,int pernasTotNgt
    ,int bracosTotNgt ){
       
    try {
        Confrontos_dao dao = new Confrontos_dao();
        //System.out.println("\n\n---------------Método inserir metricas vencedores---------------\n\n");
        // realiza a inserção da metrica de combate do vencedor
        metCBTbean.setCodChvConf(codChvConf);
        metCBTbean.setCodInscricao(codInscVencedor);
        metCBTbean.setParteDoCorpoNGT(parteCorpoNgt);
        metCBTbean.setParteDoCorpoPST(parteCorpoPst);
        metCBTbean.setDescricaoCBT(descMetricas);
        metCBTbean.setFinalCBT(finalCBT);  
        metCBTbean.setTotPontosPositivos(totalPontPst);
        metCBTbean.setTotPontosNegativos(totalPontNgt);
        dao.inserirMetCBTVencedor(metCBTbean);
        
//        System.out.println("Métricas inseridas: \nCod chave: "+metCBTbean.getCodChvConf()+"\nInscri: "+metCBTbean.getCodInscricao()
//        +"\nNegativo: "+metCBTbean.getParteDoCorpoNGT()+"\nPositivo: "+metCBTbean.getParteDoCorpoPST()+"\nDesc: "+metCBTbean.getDescricaoCBT()
//        +"\nVenceu por: "+metCBTbean.getFinalCBT());
        // Finaliza o processo de inserção da métrica de combate
        
        // Realiza a alteração dos status dos vencedores das tabelas de chaves. Caso o participante esteja na tabela 1 ou 2
        
//        System.out.println("oq deveria entrar no laço de estrutura de tb é: \ncodInsc do parâmetro: "+codInscVencedor+"\n e codInsc do bool1: "+ codInsc1
//        +"\ncodInsc do boll2: "+codInsc2);
        if(codInscVencedor == codInsc1){
          dao.alteraStatusCBTchave1(codInscVencedor, "Vencedor");
         //   System.out.println("Vencedor era da chave 1 na estrutura");
        }else{
        dao.alteraStatusCBTchave2(codInscVencedor, "Vencedor");
       //System.out.println("Vencedor era da chave 2 na estrutura");
        }
        // Finaliza a atualização de status
        
        // realiza a atualização das métricas do atleta
        dao.alteraMetricasAT(totalPontPst, totalPontNgt, codInscVencedor, codChvConf);
        
        // Adiciona os valores dos impactos em cada local do corpo
        dao.adicionaValoresPontCBT(codConf, codInscVencedor, cabecaTotPst, troncoTotPst, quedagemTotPst, pernasTotPst, bracosTotPst,
                                   cabecaTotNgt, troncoTotNgt, quedagemTotNgt, pernasTotNgt, bracosTotNgt);
//        System.out.println("\nMétricas do atleta atualizadas\nTotal pontos positivos: "+totalPontPst+"\nTotal negativos: "+totalPontNgt
//        +"\nVencedor: "+codInscVencedor+"\nChave: "+codChvConf);
        
       // finaliza a atualização das métricas do atleta      
        
   FacesContext context = FacesContext.getCurrentInstance();
   context.addMessage(null, new FacesMessage("Sucesso","Métricas do vencedor inscrição código "+ codInscVencedor +" foi adicionada."));
   finalizaCbt();
    } catch (SQLException ex) {
    }
    
}

public void inserirMetricasCBTPerdedor(
    String parteCorpoPst
    ,String parteCorpoNgt
    ,int codInscPerdedor
    ,int cabecaTotPst
    ,int troncoTotPst
    ,int quedagemTotPst
    ,int pernasTotPst
    ,int bracosTotPst
    
    ,int cabecaTotNgt 
    ,int troncoTotNgt
    ,int quedagemTotNgt
    ,int pernasTotNgt
    ,int bracosTotNgt){
       
    try {
        Confrontos_dao dao = new Confrontos_dao();
        //System.out.println("\n\n---------------Método inserir metricas perdedores---------------\n\n");
        // realiza a inserção da metrica de combate do vencedor
        metCBTbean.setCodChvConf(codChvConf);
        metCBTbean.setCodInscricao(codInscPerdedor);
        metCBTbean.setParteDoCorpoNGT(parteCorpoNgt);
        metCBTbean.setParteDoCorpoPST(parteCorpoPst);
        metCBTbean.setDescricaoCBT(descMetricas);
        metCBTbean.setFinalCBT(finalCBT);
        metCBTbean.setTotPontosPositivos(totalPontPst);
        metCBTbean.setTotPontosNegativos(totalPontNgt);
        dao.inserirMetCBTPerdedor(metCBTbean);
        
//        System.out.println("Métricas inseridas: \nCod chave: "+metCBTbean.getCodChvConf()+"\nInscri: "+metCBTbean.getCodInscricao()
//        +"\nNegativo: "+metCBTbean.getParteDoCorpoNGT()+"\nPositivo: "+metCBTbean.getParteDoCorpoPST()+"\nDesc: "+metCBTbean.getDescricaoCBT()
//        +"\nVenceu por: "+metCBTbean.getFinalCBT());
        
        // Finaliza o processo de inserção da métrica de combate
        
        // Realiza a alteração dos status dos vencedores das tabelas de chaves. Caso o participante esteja na tabela 1 ou 2
        
      //  System.out.println("oq deveria entrar no laço de estrutura de tb é: \ncodInsc do parâmetro: "+codInscPerdedor+"\n e codInsc do bool1: "+ codInsc1
       // +"\ncodInsc do boll2: "+codInsc2);
        if(codInscPerdedor == codInsc1){
          dao.alteraStatusCBTchave1(codInscPerdedor, "Perdedor");
       //     System.out.println("Vencedor era da chave 1 na estrutura");
        }else{
       dao.alteraStatusCBTchave2(codInscPerdedor, "Perdedor");
     //  System.out.println("Vencedor era da chave 2 na estrutura");
        }
        // Finaliza a atualização de status
        
        // realiza a atualização das métricas do atleta
       dao.alteraMetricasAT(totalPontPst, totalPontNgt, codInscPerdedor, codChvConf);
       
       // Adiciona os valores dos impactos em cada local do corpo
       dao.adicionaValoresPontCBT(codConf, codInscPerdedor, cabecaTotPst, troncoTotPst, quedagemTotPst, pernasTotPst, bracosTotPst, cabecaTotNgt,
                                  troncoTotNgt, quedagemTotNgt, pernasTotNgt, bracosTotNgt);
   //System.out.println("\nMétricas do atleta atualizadas\nTotal pontos positivos: "+totalPontPst+"\nTotal negativos: "+totalPontNgt
     //   +"\nVencedor: "+codInscPerdedor+"\nChave: "+codChvConf);
       
       // finaliza a atualização das métricas do atleta      
        
   FacesContext context = FacesContext.getCurrentInstance();
   context.addMessage(null, new FacesMessage("Sucesso","Métricas do perdedor inscrição código "+ codInscPerdedor +" foi adicionada."));
   finalizaCbt();
    } catch (SQLException ex) {
    }
    
}

public void finalizaCbt()throws SQLException{
    
    Confrontos_dao dao = new Confrontos_dao();
    //System.out.println("\n\n---------------Método finalizar cbt---------------\n\n");
    int qtdOk = dao.verificaOkFnlzCBT(codConf);
    int qtdArb = dao.verificaQtdArbtCBT(codConf);
   // System.out.println("qtd ok = "+qtdOk+"\nQtd arb = "+qtdArb);
    if(qtdOk < qtdArb){
        if((qtdOk + 1)== qtdArb){
          //  System.out.println("Falta 1, entao hora de finalizar");
            dao.confirmaOkFinaliza(codConf);
            dao.alteraEstruCBT(codConf);
          //  System.out.println("informações finais:\n "+codChvConf+"\ninsc 1: "+codInsc1+"\ninsc 2: "+codInsc2);
            dao.inserirMetricasAtRestantes(codChvConf, codInsc1, codInsc2,codConf);
            
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Confronto finalizado com sucesso"));
        }else{
          //  System.out.println("só adiciona mais um ok");
             dao.confirmaOkFinaliza(codConf);
             listaEstruConf = dao.consultaEstruConfArb(codArbitro);
        } 
    }
    
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public int getCodArbitro() {
        return codArbitro;
    }

    public void setCodArbitro(int codArbitro) {
        this.codArbitro = codArbitro;
    }

    public int getCodSegmento() {
        return codSegmento;
    }

    public void setCodSegmento(int codSegmento) {
        this.codSegmento = codSegmento;
    }

    public String getNomeArbitro() {
        return nomeArbitro;
    }

    public void setNomeArbitro(String nomeArbitro) {
        this.nomeArbitro = nomeArbitro;
    }

    public String getGraduacaoArbitro() {
        return graduacaoArbitro;
    }

    public void setGraduacaoArbitro(String graduacaoArbitro) {
        this.graduacaoArbitro = graduacaoArbitro;
    }

    public int getCodPontua() {
        return codPontua;
    }

    public void setCodPontua(int codPontua) {
        this.codPontua = codPontua;
    }

    public String getNomePontua() {
        return nomePontua;
    }

    public void setNomePontua(String nomePontua) {
        this.nomePontua = nomePontua;
    }

    public int getCodConf() {
        return codConf;
    }

    public void setCodConf(int codConf) {
        this.codConf = codConf;
    }

    public int getCodConfArb() {
        return codConfArb;
    }

    public void setCodConfArb(int codConfArb) {
        this.codConfArb = codConfArb;
    }

    public int getCodChvConf() {
        return codChvConf;
    }

    public void setCodChvConf(int codChvConf) {
        this.codChvConf = codChvConf;
    }

    public int getNumRodada() {
        return numRodada;
    }

    public void setNumRodada(int numRodada) {
        this.numRodada = numRodada;
    }

    public int getCodInsc1() {
        return codInsc1;
    }

    public void setCodInsc1(int codInsc1) {
        this.codInsc1 = codInsc1;
    }

    public int getCodInsc2() {
        return codInsc2;
    }

    public void setCodInsc2(int codInsc2) {
        this.codInsc2 = codInsc2;
    }

    public String getStatusConf() {
        return statusConf;
    }

    public void setStatusConf(String statusConf) {
        this.statusConf = statusConf;
    }

    public List<Arbitros_bean> getListaEstruConf() {
        return listaEstruConf;
    }

    public void setListaEstruConf(List<Arbitros_bean> listaEstruConf) {
        this.listaEstruConf = listaEstruConf;
    }

    public List<Arbitros_bean> getChaveConf() {
        return chaveConf;
    }

    public void setChaveConf(List<Arbitros_bean> chaveConf) {
        this.chaveConf = chaveConf;
    }

    public List<Arbitros_bean> getListaPontos() {
        return listaPontos;
    }

    public void setListaPontos(List<Arbitros_bean> listaPontos) {
        this.listaPontos = listaPontos;
    }

    public String getNomeAtl1() {
        return nomeAtl1;
    }

    public void setNomeAtl1(String nomeAtl1) {
        this.nomeAtl1 = nomeAtl1;
    }

    public String getNomeAtl2() {
        return nomeAtl2;
    }

    public void setNomeAtl2(String nomeAtl2) {
        this.nomeAtl2 = nomeAtl2;
    }


    public String getAtletaSelec1() {
        return atletaSelec1;
    }

    public void setAtletaSelec1(String atletaSelec1) {
        this.atletaSelec1 = atletaSelec1;
    }

    public String getAtletaSelec2() {
        return atletaSelec2;
    }

    public void setAtletaSelec2(String atletaSelec2) {
        this.atletaSelec2 = atletaSelec2;
    }

    public String getAtletaSelecDef() {
        return atletaSelecDef;
    }

    public void setAtletaSelecDef(String atletaSelecDef) {
        this.atletaSelecDef = atletaSelecDef;
    }

public boolean isSkip() {
        return skip;
    }
 
    public void setSkip(boolean skip) {
        this.skip = skip;
    }    

    public String getMsgStatusConf() {
        return msgStatusConf;
    }

    public void setMsgStatusConf(String msgStatusConf) {
        this.msgStatusConf = msgStatusConf;
    }

    public boolean isBoolAtlSelec1() {
        return boolAtlSelec1;
    }

    public void setBoolAtlSelec1(boolean boolAtlSelec1) {
        this.boolAtlSelec1 = boolAtlSelec1;
    }

    public boolean isBoolAtlSelec2() {
        return boolAtlSelec2;
    }

    public void setBoolAtlSelec2(boolean boolAtlSelec2) {
        this.boolAtlSelec2 = boolAtlSelec2;
    }

    public List<Arbitros_bean> getListaPontosPstCbt() {
        return listaPontosPstCbt;
    }

    public void setListaPontosPstCbt(List<Arbitros_bean> listaPontosPstCbt) {
        this.listaPontosPstCbt = listaPontosPstCbt;
    }

    public List<Arbitros_bean> getListaPontosNgtCbt() {
        return listaPontosNgtCbt;
    }

    public void setListaPontosNgtCbt(List<Arbitros_bean> listaPontosNgtCbt) {
        this.listaPontosNgtCbt = listaPontosNgtCbt;
    }

  

    public int getStatusInicioConf() {
        return statusInicioConf;
    }

    public void setStatusInicioConf(int statusInicioConf) {
        this.statusInicioConf = statusInicioConf;
    }

    public String getVencedor() {
        return vencedor;
    }

    public void setVencedor(String vencedor) {
        this.vencedor = vencedor;
    }

    public int getTotalPontPst() {
        return totalPontPst; 
    }

    public void setTotalPontPst(int totalPontPst) {
        this.totalPontPst = totalPontPst;
    }
 
    public int getTotalPontNgt() {
        return totalPontNgt;
    }

    public void setTotalPontNgt(int totalPontNgt) {
        this.totalPontNgt = totalPontNgt;
    }

    public String getFinalCBT() {
        return finalCBT;
    }

    public void setFinalCBT(String finalCBT) {
        this.finalCBT = finalCBT;
    }

    public String getDescMetricas() {
        return descMetricas;
    }

    public void setDescMetricas(String descMetricas) {
        this.descMetricas = descMetricas;
    }

    
    public MetricasCBT_bean getMetCBTbean() {
        return metCBTbean;
    }

    public void setMetCBTbean(MetricasCBT_bean metCBTbean) {
        this.metCBTbean = metCBTbean;
    }

    
    
}
