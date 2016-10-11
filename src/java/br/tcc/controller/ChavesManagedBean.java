
package br.tcc.controller;

import br.tcc.bean.Arbitros_bean;
import br.tcc.bean.Categorias_bean;
import br.tcc.bean.Confrontos_bean;
import br.tcc.bean.InscricaoPRT_bean;
import br.tcc.bean.MetodosPontua_bean;
import br.tcc.bean.MetricasAT_bean;
import br.tcc.bean.MetricasCBT_bean;
import br.tcc.dao.Confrontos_dao;
import br.tcc.dao.Eventos_dao;
import com.sun.xml.internal.ws.client.ContentNegotiation;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author joãomarcos
 */
@ManagedBean(name="ChavesManagedBean", eager = true)
@SessionScoped
public class ChavesManagedBean {

   
    
private int codSegmento ;
private String codSegSelecionado;
private String codCatSelecionado;
private String codChvSelecionado;
private int codCategoria;
private int codChvConf;
private String nomeChvConf;
private String statusChvConf;
private int qtdRodadasChvConf;
private int codArbitro;
private String arbitroSelecionado;
private String nomeArbitro;
private String graduacaoArbitro;
private int codPontua;
private String codPontuaSelecionado;
private String nomePontua;
private int valorPontua;
private String tipoPontua;
private String descricaoPontua;
private String parteDoCorpo;
private Arbitros_bean pontoSelecionado;
private int codConf;
int codInscPerdedor;
int codInscVencedor;
int indiceConfrontoFinalizado;
int chave;
int numRodada;
private String vencedor;
private String ptCorpoNGT;
private String ptCorpoPST;
private String ptCorpoNGTPerde;
private String ptCorpoPSTPerde;
private String descMetricasVencedor;
private String descMetricasPerdedor;
private String finalCBTVencedor;
private String finalCBTPerdedor;
private int qtdPontosPSTVencedor;
private int qtdPontosNGTVencedor;
private int qtdPontosPSTPerdedor;
private int qtdPontosNGTPerdedor;
private InscricaoPRT_bean inscSelec;
int iniciado; // Esta variavel informa se o confronto selecionado já foi ou não iniciado. 0 = NÃO INICIADO e 1 = INICIADO.

private List<Categorias_bean> listaCatSegs = new ArrayList<>() ;
private List<InscricaoPRT_bean> inscritos = new ArrayList<>();    
private List<Confrontos_bean> chaves = new ArrayList<>() ;    
private List<InscricaoPRT_bean> tabCHV1 = new ArrayList<>();
private List<InscricaoPRT_bean> tabCHV2 = new ArrayList<>();
private List<InscricaoPRT_bean> vencedores = new ArrayList<>();
private List<Arbitros_bean> Arbitros = new ArrayList<>() ;  
private List<Arbitros_bean> PontoArb = new ArrayList<>() ;  
private List<Arbitros_bean> listaArbitros = new ArrayList<>() ;  
private List<Arbitros_bean> listaFinalPont = new ArrayList<>() ; 
private List<Arbitros_bean> listaArbCBT = new ArrayList<>() ; 
private List<Arbitros_bean> listaPont = new ArrayList<>() ; 
private List<String> comboCHVs = new ArrayList<>() ;    
private List<String> comboCats = new ArrayList<>() ;  
private List<String> comboArb = new ArrayList<>() ;  
private List<String> comboPtArb = new ArrayList<>() ;  

Confrontos_bean confBean = new Confrontos_bean();
MetricasAT_bean metATLbean = new MetricasAT_bean();
MetricasCBT_bean metCBTbean = new MetricasCBT_bean();



////////////////////////////////////////////////////////////// Métodos inserção //////////////////////////////////////////////////////

public void inserirConfrontos()throws SQLException{
   Confrontos_dao dao = new Confrontos_dao();
   
   
   // insere a s informações da chave de confronto, retornando seu código
   
   confBean.setCodSegmento(Integer.valueOf(codSegSelecionado.substring(0,codSegSelecionado.indexOf("-")).trim()));
   confBean.setCodCategoria(Integer.valueOf(codCatSelecionado.substring(0,codCatSelecionado.indexOf("-")).trim()));
   confBean.setNomeChvConf(nomeChvConf);

   codChvConf = dao.inserirConfrontos(confBean);
   
   // final da inserção de chave
   
   // Inicia a inserção de métricas de torneio dos participantes que estavam inscritos
   for(int i = 0; i < inscritos.size();i++){
       
       metATLbean.setCodChvConf(codChvConf);
       metATLbean.setCodInscricao(inscritos.get(i).getCodInscricao());
       
       dao.inserirMetATL(metATLbean);
   }
   // final da inserção de métricas de torneio
   
   // inicia a inserção da rodada inicial
   
   dao.inserirRodadaCHV(codChvConf); 
   
   // finaliza inserção rodada
   
   // define a ordem da chave
   
           int i = 0;
           while(!inscritos.isEmpty()){
           
           int x = (int) (Math.random() * inscritos.size());
           
           if(i%2 == 0 || i == 0){
           MetricasCBT_bean mtBean1 = new MetricasCBT_bean();
  
           mtBean1.setCodChvConf(codChvConf);
           mtBean1.setCodInscricao(inscritos.get(x).getCodInscricao());
           
               dao.armazenarTBchave1(mtBean1);
             

           inscritos.remove(x);
          i++;
           }else{
           MetricasCBT_bean mtBean2 = new MetricasCBT_bean();    

           mtBean2.setCodChvConf(codChvConf);
           mtBean2.setCodInscricao(inscritos.get(x).getCodInscricao());
           
           dao.armazenarTBchave2(mtBean2);
           
           inscritos.remove(x);
       
           i++;
           }
           }
  

   // finaliza a definição dessa ordem
   
   
   
   FacesContext context = FacesContext.getCurrentInstance();
   context.addMessage(null, new FacesMessage("Sucesso","Chave de confronto \n'"+ nomeChvConf +"'\n foi gerada e esta disponível para iniciar os combates."));
   
   }


// OS DOIS METODOS COMENTADOS ABAIXO ESTÃO EM TESTES NO CONTROLLER ARBITRO.

//public void inserirMetricasCBTVencedor(){
//    try {
//       Confrontos_dao dao = new Confrontos_dao();
//        
//       
//       // realiza a inserção da metrica de combate do vencedor
//       if(!tabCHV1.isEmpty() || !tabCHV2.isEmpty()){
//        metCBTbean.setCodChvConf(codChvConf);
//        metCBTbean.setCodInscricao(codInscVencedor);
//        metCBTbean.setParteDoCorpoNGT(ptCorpoNGT);
//        metCBTbean.setParteDoCorpoPST(ptCorpoPST);
//        metCBTbean.setDescricaoCBT(descMetricasVencedor);
//        metCBTbean.setFinalCBT(finalCBTVencedor);
//        dao.inserirMetCBTVencedor(metCBTbean);
//       }
//        // Finaliza o processo de inserção da métrica de combate
//    
//    // Realiza a alteração dos status dos vencedores das tabelas de chaves. Caso o participante esteja na tabela 1 ou 2
//    if(chave == 1){
//       dao.alteraStatusCBTchave1(codInscVencedor, "Vencedor");
//   }else{
//       dao.alteraStatusCBTchave2(codInscVencedor, "Vencedor");
//   }
//   // Finaliza a atualização de status
//    
//   // realiza a atualização das métricas do atleta
//   dao.alteraMetricasAT(qtdPontosPSTVencedor, qtdPontosNGTVencedor, codInscVencedor, codChvConf);
//   // finaliza a atualização das métricas do atleta      
//   FacesContext context = FacesContext.getCurrentInstance();
//   context.addMessage(null, new FacesMessage("Sucesso","Métricas do vencedor inscrição código "+ codInscVencedor +" foi adicionada."));
//   
//   tabCHV1.remove(indiceConfrontoFinalizado);
//   
//  
//   
//   ptCorpoNGT = "";
//   ptCorpoPST = "";
//   descMetricasVencedor = "";
//    } catch (SQLException ex) {
//        Logger.getLogger(ChavesManagedBean.class.getName()).log(Level.SEVERE, null, ex);
//    }
// 
// 
//}
//
//public void inserirMetricasCBTPerdedor(){
//    try {
//       Confrontos_dao dao = new Confrontos_dao();
//        
//       // realiza a inserção da metrica de combate do vencedor
//       if(!tabCHV1.isEmpty() || !tabCHV2.isEmpty()){
//        metCBTbean.setCodChvConf(codChvConf);
//        metCBTbean.setCodInscricao(codInscPerdedor);
//        metCBTbean.setParteDoCorpoNGT(ptCorpoNGTPerde);
//        metCBTbean.setParteDoCorpoPST(ptCorpoPSTPerde);
//        metCBTbean.setDescricaoCBT(descMetricasPerdedor);
//        metCBTbean.setFinalCBT(finalCBTPerdedor);
//        dao.inserirMetCBTPerdedor(metCBTbean);
//       
//         // Finaliza o processo de inserção da métrica de combate
//        
//        // Realiza a alteração dos status dos perdedores das tabelas de chaves. Caso o participante esteja na tabela 1 ou 2. No caso, o oposto do vencedor
//        if(chave == 1){
//       dao.alteraStatusCBTchave2(codInscPerdedor, "Perdedor");
//   }else{
//       dao.alteraStatusCBTchave1(codInscPerdedor, "Perdedor");
//   }
//       // Finaliza a atualização de status 
//        
//   // realiza a atualização das métricas do atleta
//   dao.alteraMetricasAT(qtdPontosPSTPerdedor, qtdPontosNGTPerdedor, codInscPerdedor, codChvConf);
//   // finaliza a atualização das métricas do atleta     
//        
//   FacesContext context = FacesContext.getCurrentInstance();
//   context.addMessage(null, new FacesMessage("Sucesso","Métricas do perdedor inscrição código "+ codInscPerdedor +" foi adicionada."));
//   tabCHV2.remove(indiceConfrontoFinalizado);   
//   }else{
//           FacesContext context = FacesContext.getCurrentInstance();
//           context.addMessage(null, new FacesMessage("Sucesso","Métricas do perdedor não adicionada, pois não havia mais participantes"));
//       }
//   
//   
//   ptCorpoNGTPerde = "";
//   ptCorpoPSTPerde = "";
//   descMetricasPerdedor = "";
//    consultaVencedoresRodada();
//    } catch (SQLException ex) {
//        Logger.getLogger(ChavesManagedBean.class.getName()).log(Level.SEVERE, null, ex);
//    }
// 
// 
//}




public void fializaRodadaCHV(){
    try {
        Confrontos_dao dao = new Confrontos_dao();
        
        
        
        
        if(vencedores.size() == 1){
     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Informação", "Há apenas 1 vencedor marcado nesta rodada \n"
             + "Caso ele seja o campeão definitivo da chave, clique no botão para finalizar a chave"));       
        }else{
        
            // Realiza a atualização da rodada para finalizada
        
            dao.finalizaRodadaCHV(codChvConf);
        
            // Encerra o processo de atualização da rodada
            
        
        // Insere uma nova rodada
        
        dao.inserirNovaRodadaCHV(codChvConf);
        
        // encerra inserção de uma nova rodada
        
        // Inicia o processo de redistribuição da chave
        
        int i = 0;
           while(!vencedores.isEmpty()){
           
           int x = (int) (Math.random() * vencedores.size());
        
           if(i%2 == 0 || i == 0){
           MetricasCBT_bean mtBean1 = new MetricasCBT_bean();
  
           mtBean1.setCodChvConf(codChvConf);
           mtBean1.setCodInscricao(vencedores.get(x).getCodInscricao());
           
               dao.armazenarTBchave1(mtBean1);
        
 
           vencedores.remove(x);
          i++;
           }else{
           MetricasCBT_bean mtBean2 = new MetricasCBT_bean();    

           mtBean2.setCodChvConf(codChvConf);
           mtBean2.setCodInscricao(vencedores.get(x).getCodInscricao());
           
           dao.armazenarTBchave2(mtBean2);
       
           vencedores.remove(x);
       
           i++;
           }
           }
        
        // encerra a redistribuição da chave
        
           consultaEstruturaCHV();
   FacesContext context = FacesContext.getCurrentInstance();
   context.addMessage(null, new FacesMessage("Sucesso","Nova rodada da chave "+ codChvSelecionado+ " foi ainiciada."));
        }
        
   
           
    } catch (SQLException ex) {
        Logger.getLogger(ChavesManagedBean.class.getName()).log(Level.SEVERE, null, ex);
    }
     
     
}

public void finalizaCHV(){
    try {
        Confrontos_dao dao = new Confrontos_dao();
        
        if(vencedores.size() > 1 || vencedores.isEmpty()){
        
   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "Uma chave de confronto deve ter apenas 1 vencedor \n"
           + "listado no momento de finalizar a chave. Caso haja mais vencedores, deve ser feito mais confrontos.\n"
           + "Caso não haja vecedores listados, realize os confrontos para defini-los"));                
            
        }else{
        // Primeiramente a rodada atual é finalizada para que não fique registro incorreto na base
        dao.finalizaRodadaCHV(codChvConf);
        // Encerra a finalização da rodada
        
        // É finalizada a chave
        
        dao.finalizaCHV(codChvConf);
        
        // Final da finalização da chave
      
        
        // Informa o vencedor
        vencedor = vencedores.get(0).getNomeAtleta();
        // Final informar vencedor
        
        
   FacesContext context = FacesContext.getCurrentInstance();
   context.addMessage(null, new FacesMessage("Sucesso","Chave "+ codChvSelecionado+ " foi finalizada."));
   carregaComboCHVs();
        }
    } catch (SQLException ex) {
        Logger.getLogger(ChavesManagedBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}

////////////////////////////////////////////////////////////// Métodos combo //////////////////////////////////////////////////////
public void carregaComboCat()throws SQLException{
   Eventos_dao dao = new Eventos_dao();
   comboCats.clear();
   if(codSegSelecionado.equals("")){
    comboCats.clear();
   }else{
   listaCatSegs.clear();
   listaCatSegs = dao.consultaCategoriasSegmento(Integer.valueOf(codSegSelecionado.substring(0,codSegSelecionado.indexOf("-")).trim()));
   
   if(listaCatSegs.isEmpty()){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", "Não há categorias para este segmento"));
   }else{
   for(int i = 0; i < listaCatSegs.size();i++){
       comboCats.add(listaCatSegs.get(i).getCodCategoria()+" - "+ listaCatSegs.get(i).getNomeCategoria());
   }
   }
  
   }
    }

 public void carregaComboCHVs()throws SQLException{
       comboCHVs.clear();
       tabCHV1.clear();
       tabCHV2.clear();
       comboArb.clear();
       comboPtArb.clear();
       if(codSegSelecionado.equals("")){
       comboCHVs.clear();  
       tabCHV1.clear();
       tabCHV2.clear();
       vencedores.clear();
       comboArb.clear();
       comboPtArb.clear();
       }else{
       codSegmento = Integer.valueOf(codSegSelecionado.substring(0,codSegSelecionado.indexOf("-")).trim());
       chaves = new Confrontos_dao().consultaChaveCONF(codSegmento);
       carregaComboArb();
       if(chaves.isEmpty()){
           comboCHVs.clear();
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", "Não foram geradas chaves para este segmento."));
       }else{
       for(int i = 0; i< chaves.size(); i++){
           comboCHVs.add(chaves.get(i).getCodChvConf()+" - "+ chaves.get(i).getNomeChvConf());
       }
       }
       }
   }

     public void carregaComboArb()throws SQLException{
    if(codSegSelecionado.equals("")){
       comboArb.clear();
       comboPtArb.clear();
       }else{
        Arbitros = new Eventos_dao().pesquisaArbitro(codSegmento);
        for(int i = 0; i < Arbitros.size(); i++){
            comboArb.add(Arbitros.get(i).getCodArbitro()+ " - "+ Arbitros.get(i).getNomeArbitro());
        }
    }
    
     }
     
     public void carregaComboPontua()throws SQLException{
         listaPont.clear();
       //  comboPtArb.clear();
         if(arbitroSelecionado.equals("")){
             //comboArb.clear();
             listaPont.clear();
              comboPtArb.clear();
         }else{
             comboPtArb.clear();
             codArbitro = Integer.valueOf(arbitroSelecionado.substring(0,arbitroSelecionado.indexOf("-")).trim());
             PontoArb = new Eventos_dao().pesquisaPTdoARB(codArbitro);
             for(int i = 0 ; i < PontoArb.size(); i ++){
                 comboPtArb.add(PontoArb.get(i).getCodPontua()+ " - "+ PontoArb.get(i).getNomePontua());
             }
         }
     }
 
////////////////////////////////////////////////////////////// Métodos consulta //////////////////////////////////////////////////////

public void listarAtletasInsc()throws SQLException{
   
       inscritos = new Confrontos_dao().consultaATL(Integer.valueOf(codCatSelecionado.substring(0,codCatSelecionado.indexOf("-")).trim()));
       if(inscritos.isEmpty()){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", "Não há participantes inscritos nesta categoria"));
       }      
   }

public void consultaEstruturaCHV()throws SQLException{
       Confrontos_dao dao = new Confrontos_dao();
      
       tabCHV1.clear();
       tabCHV2.clear();
       if(codChvSelecionado.equals("") ){
       vencedores.clear();
       tabCHV1.clear(); 
       tabCHV2.clear();
           
       }else{
       
       codChvConf = Integer.valueOf(codChvSelecionado.substring(0,codChvSelecionado.indexOf("-")).trim());    
       tabCHV1 = dao.consultaEstruturaCHV1(codChvConf);
       tabCHV2 = dao.consultaEstruturaCHV2(codChvConf);
       int rodada = dao.consultaRodadaAtual(codChvConf);
       
      
       
       if(rodada != 0){
       numRodada = rodada;
       
       consultaVencedoresRodada();
       
       }else{
       numRodada = rodada;    
       }
       }
       
   }

public void consultaVencedoresRodada()throws SQLException{
    Confrontos_dao dao = new Confrontos_dao();
    
    vencedores.clear();
    
    List<InscricaoPRT_bean> tb1 = new ArrayList<>();
    List<InscricaoPRT_bean> tb2 = new ArrayList<>();
    
    tb1 = dao.consultaVencedoresCHV1(codChvConf, numRodada);
    tb2 = dao.consultaVencedoresCHV2(codChvConf, numRodada);
    
    for(int i = 0; i < tb1.size();i++){
        vencedores.add(tb1.get(i));
    }
    for(int i = 0; i < tb2.size();i++){
        vencedores.add(tb2.get(i));
    }
}


////////////////////////////////////////////////////////////// Métodos adicionais //////////////////////////////////////////////////////
   

public void removerPrtGerarChave(){
    for(int i = 0; i < inscritos.size(); i++){
    if( inscritos.get(i).getCodInscricao() == inscSelec.getCodInscricao())
        inscritos.remove(i);
    }
    
}

public void adicionaPontoLista(){
    Arbitros_bean bean = new Arbitros_bean();
    boolean verifica = false;
    codPontua = Integer.valueOf(codPontuaSelecionado.substring(0,codPontuaSelecionado.indexOf("-")).trim());
    nomePontua = codPontuaSelecionado.substring(codPontuaSelecionado.indexOf("-")).replaceAll("-", "").trim();
    
    for(int i = 0; i < listaPont.size(); i ++){
        if(codPontua == listaPont.get(i).getCodPontua()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "\nEsta forma de pontuação já foi adicionada na lista"));
            verifica = true;
        }   
    }
    if(verifica == false){
    bean.setCodPontua(codPontua);
    bean.setNomePontua(nomePontua);
    
    listaPont.add(bean);
    }
}


public void limparListaPont(){
listaPont.clear();
}

public void removePontLista(){
    for(int i = 0 ; i < listaPont.size(); i++){
        if(pontoSelecionado.getCodPontua() == listaPont.get(i).getCodPontua()){
            listaPont.remove(i);
        }
    }
}

public void adicionarArbitroLista(){
    Arbitros_bean bean = new Arbitros_bean();
     boolean verifica = false;
    
     if(iniciado == 1){
     
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "\nEsta partida já foi iniciada."));              
     }else{
     for(int i = 0; i < listaArbitros.size(); i++){
        if(codArbitro == listaArbitros.get(i).getCodArbitro()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "\nÁrbitro já adicionado para este confronto"));
            verifica = true;
        }
    }

    if(verifica ==  false && listaPont.size() > 0){
    bean.setCodArbitro(codArbitro);
    listaArbitros.add(bean);
    
    for(int i = 0; i < listaPont.size(); i++){
       Arbitros_bean beanPont = new Arbitros_bean();    
       beanPont.setCodArbitro(codArbitro);
       beanPont.setCodPontua(listaPont.get(i).getCodPontua());
       listaFinalPont.add(beanPont);
    }
     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção", "\nÁrbitro adicionado com sucesso"));
    }else{
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "\nAdicione pelo menos uma pontuação a ser julgada pelo árbitro"));
    }
     }
}


public void preparaInicioConf(){
    
    iniciado = 0;
    for(InscricaoPRT_bean p: tabCHV1){
       
           if(p.isSelected()){
               if(!p.getStatusConf().equals("")){
                iniciado = 1;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "\nEsta partida já foi iniciada."));           
               }
           }
    }
    
    for(InscricaoPRT_bean p: tabCHV2){
       
           if(p.isSelected()){
               if(!p.getStatusConf().equals("")){
                iniciado = 1;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "\nEsta partida já foi iniciada."));           
               }
           }
    }
    
listaArbitros.clear();
listaFinalPont.clear();
listaPont.clear();
}

public void iniciarCBT()throws SQLException{
    int confrontoSelecionado = -1;
    int prt1;
    int prt2;
    
    for(InscricaoPRT_bean p: tabCHV1){
       
           if(p.isSelected()){
               codInscVencedor = p.getCodInscricao();
               for(int i =0; i < tabCHV1.size();i++){
                   
                   if(p.getCodInscricao()== tabCHV1.get(i).getCodInscricao()){
                    confrontoSelecionado = i;
                   }   
               }
           }
    }
    
    for(InscricaoPRT_bean p: tabCHV2){
       
           if(p.isSelected()){
               codInscVencedor = p.getCodInscricao();
               for(int i =0; i < tabCHV2.size();i++){
                   
                   if(p.getCodInscricao()== tabCHV2.get(i).getCodInscricao()){
                    confrontoSelecionado = i;
                   }   
               }
           }
    }
    
    if(confrontoSelecionado == -1){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "\nNenhum confronto foi selecionado, então não ouve inicialização de partida."));
    }else{
    
    prt1 = tabCHV1.get(confrontoSelecionado).getCodInscricao();
    prt2 = tabCHV2.get(confrontoSelecionado).getCodInscricao();
    
    if(!listaArbitros.isEmpty() && !listaFinalPont.isEmpty()){

        Arbitros_bean bean = new Arbitros_bean();
        Confrontos_dao dao = new Confrontos_dao();
        
        // Adicionando a estrutura
        bean.setCodChvConf(codChvConf);
        bean.setNumRodada(numRodada);
        bean.setCodInsc1(prt1);
        bean.setCodInsc2(prt2);
        bean.setStatusConf("Iniciado");
        
        codConf = dao.estruCBT(bean);
        
        // Adicionando os arbitros
        
        for(int i = 0; i < listaArbitros.size(); i++){
          listaArbCBT =  dao.arbCBT(listaArbitros.get(i).getCodArbitro(), codConf);
        }
        
        // Adicionando os pontos
        for(int i = 0; i < listaArbCBT.size(); i++){
        
            for(int j = 0; j < listaFinalPont.size(); j++){
                if(listaFinalPont.get(j).getCodArbitro() == listaArbCBT.get(i).getCodArbitro()){
                    dao.pontArbCBT(listaArbCBT.get(i).getCodConfArb(), listaFinalPont.get(j).getCodPontua());
                }
            }
        }
        
        // Adicionando inicio conf
        
        dao.adicionaInicioConf(codConf, prt1, prt2);
        
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "\nConfronto iniciado"));    
    }else{
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "\nNão há arbitros e pontuações atreladas a esta partida. Realize as devidas configurações."));
    }
    }
}


   public void adicionaVencedor(){
    
       
       for(InscricaoPRT_bean p: tabCHV1){
       
           if(p.isSelected()){
              
              // vencedores.add(p);
               codInscVencedor = p.getCodInscricao();
               for(int i =0; i < tabCHV1.size();i++){
                   
                   if(p.getCodInscricao()== tabCHV1.get(i).getCodInscricao()){
                    codInscPerdedor = tabCHV2.get(i).getCodInscricao();
                    indiceConfrontoFinalizado = i;
                    chave = 1;
                   }   
               } 
FacesContext context = FacesContext.getCurrentInstance();
context.addMessage(null, new FacesMessage("Sucesso","Atleta \n'"+ p.getNomeAtleta() +"'\nfoi adicionado aos vencedores da rodada"));
           }
            
       }
        
       for(InscricaoPRT_bean p: tabCHV2){
           
           if(p.isSelected()){
            
              //vencedores.add(p);
              codInscVencedor = p.getCodInscricao();
              for(int i =0; i < tabCHV2.size();i++){
                   
                   if(p.getCodInscricao()== tabCHV2.get(i).getCodInscricao()){
                    codInscPerdedor = tabCHV1.get(i).getCodInscricao();   
                    indiceConfrontoFinalizado = i;
                    chave = 2;
                   }
                    
               }
FacesContext context = FacesContext.getCurrentInstance();
context.addMessage(null, new FacesMessage("Sucesso","Atleta \n'"+ p.getNomeAtleta() +"'\nfoi adicionado aos vencedores da rodada"));
           }
            
       }
         
   }
   
   public void limparTBvence(){
       vencedores.clear();
   }
   
   
  public void linkGerarChaves() throws IOException{
    
  
    FacesContext.getCurrentInstance().getExternalContext().redirect("./GerarChaves.xhtml");
    
} 
   
  public void linkChaves() throws IOException{
    
  
    FacesContext.getCurrentInstance().getExternalContext().redirect("./Chaves.xhtml");
    
} 
   
   
   
     
   
   
   //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int getCodSegmento() {
        return codSegmento;
    }

    public void setCodSegmento(int codSegmento) {
        this.codSegmento = codSegmento;
    }

    public String getCodSegSelecionado() {
        return codSegSelecionado;
    }

    public void setCodSegSelecionado(String codSegSelecionado) {
        this.codSegSelecionado = codSegSelecionado;
    }

    public int getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(int codCategoria) {
        this.codCategoria = codCategoria;
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

    public List<Categorias_bean> getListaCatSegs() {
        return listaCatSegs;
    }

    public void setListaCatSegs(List<Categorias_bean> listaCatSegs) {
        this.listaCatSegs = listaCatSegs;
    }

    public List<String> getComboCats() {
        return comboCats;
    }

    public void setComboCats(List<String> comboCats) {
        this.comboCats = comboCats;
    }

    public String getCodCatSelecionado() {
        return codCatSelecionado;
    }

    public void setCodCatSelecionado(String codCatSelecionado) {
        this.codCatSelecionado = codCatSelecionado;
    }

    public List<InscricaoPRT_bean> getIncritos() {
        return inscritos;
    }

    public void setIncritos(List<InscricaoPRT_bean> incritos) {
        this.inscritos = incritos;
    }

    public List<InscricaoPRT_bean> getInscritos() {
        return inscritos;
    }

    public void setInscritos(List<InscricaoPRT_bean> inscritos) {
        this.inscritos = inscritos;
    }

    public List<String> getComboCHVs() {
        return comboCHVs;
    }

    public void setComboCHVs(List<String> comboCHVs) {
        this.comboCHVs = comboCHVs;
    }

    public List<Confrontos_bean> getChaves() {
        return chaves;
    }

    public void setChaves(List<Confrontos_bean> chaves) {
        this.chaves = chaves;
    }

    public Confrontos_bean getConfBean() {
        return confBean;
    }

    public void setConfBean(Confrontos_bean confBean) {
        this.confBean = confBean;
    }

    public MetricasAT_bean getMetATLbean() {
        return metATLbean;
    }

    public void setMetATLbean(MetricasAT_bean metATLbean) {
        this.metATLbean = metATLbean;
    }

    public MetricasCBT_bean getMetCBTbean() {
        return metCBTbean;
    }

    public void setMetCBTbean(MetricasCBT_bean metCBTbean) {
        this.metCBTbean = metCBTbean;
    }

    public List<InscricaoPRT_bean> getTabCHV1() {
        return tabCHV1;
    }

    public void setTabCHV1(List<InscricaoPRT_bean> tabCHV1) {
        this.tabCHV1 = tabCHV1;
    }

    public List<InscricaoPRT_bean> getTabCHV2() {
        return tabCHV2;
    }

    public void setTabCHV2(List<InscricaoPRT_bean> tabCHV2) {
        this.tabCHV2 = tabCHV2;
    }

    public String getCodChvSelecionado() {
        return codChvSelecionado;
    }

    public void setCodChvSelecionado(String codChvSelecionado) {
        this.codChvSelecionado = codChvSelecionado;
    }

    public List<InscricaoPRT_bean> getVencedores() {
        return vencedores;
    }

    public void setVencedores(List<InscricaoPRT_bean> vencedores) {
        this.vencedores = vencedores;
    }

    public int getCodInscPerdedor() {
        return codInscPerdedor;
    }

    public void setCodInscPerdedor(int codInscPerdedor) {
        this.codInscPerdedor = codInscPerdedor;
    }

    public int getCodInscVencedor() {
        return codInscVencedor;
    }

    public void setCodInscVencedor(int codInscVencedor) {
        this.codInscVencedor = codInscVencedor;
    }

    public int getIndiceConfrontoFinalizado() {
        return indiceConfrontoFinalizado;
    }

    public void setIndiceConfrontoFinalizado(int indiceConfrontoFinalizado) {
        this.indiceConfrontoFinalizado = indiceConfrontoFinalizado;
    }

    public String getPtCorpoNGT() {
        return ptCorpoNGT;
    }

    public void setPtCorpoNGT(String ptCorpoNGT) {
        this.ptCorpoNGT = ptCorpoNGT;
    }

    public String getPtCorpoPST() {
        return ptCorpoPST;
    }

    public void setPtCorpoPST(String ptCorpoPST) {
        this.ptCorpoPST = ptCorpoPST;
    }

    public String getDescMetricasVencedor() {
        return descMetricasVencedor;
    }

    public void setDescMetricasVencedor(String descMetricasVencedor) {
        this.descMetricasVencedor = descMetricasVencedor;
    }

    public String getDescMetricasPerdedor() {
        return descMetricasPerdedor;
    }

    public void setDescMetricasPerdedor(String descMetricasPerdedor) {
        this.descMetricasPerdedor = descMetricasPerdedor;
    }

    public String getPtCorpoNGTPerde() {
        return ptCorpoNGTPerde;
    }

    public void setPtCorpoNGTPerde(String ptCorpoNGTPerde) {
        this.ptCorpoNGTPerde = ptCorpoNGTPerde;
    }

    public String getPtCorpoPSTPerde() {
        return ptCorpoPSTPerde;
    }

    public void setPtCorpoPSTPerde(String ptCorpoPSTPerde) {
        this.ptCorpoPSTPerde = ptCorpoPSTPerde;
    }

    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public int getQtdPontosPSTVencedor() {
        return qtdPontosPSTVencedor;
    }

    public void setQtdPontosPSTVencedor(int qtdPontosPSTVencedor) {
        this.qtdPontosPSTVencedor = qtdPontosPSTVencedor;
    }

    public int getQtdPontosNGTVencedor() {
        return qtdPontosNGTVencedor;
    }

    public void setQtdPontosNGTVencedor(int qtdPontosNGTVencedor) {
        this.qtdPontosNGTVencedor = qtdPontosNGTVencedor;
    }

    public int getQtdPontosPSTPerdedor() {
        return qtdPontosPSTPerdedor;
    }

    public void setQtdPontosPSTPerdedor(int qtdPontosPSTPerdedor) {
        this.qtdPontosPSTPerdedor = qtdPontosPSTPerdedor;
    }

    public int getQtdPontosNGTPerdedor() {
        return qtdPontosNGTPerdedor;
    }

    public void setQtdPontosNGTPerdedor(int qtdPontosNGTPerdedor) {
        this.qtdPontosNGTPerdedor = qtdPontosNGTPerdedor;
    }

    public String getVencedor() {
        return vencedor;
    }

    public void setVencedor(String vencedor) {
        this.vencedor = vencedor;
    }

    public String getFinalCBTVencedor() {
        return finalCBTVencedor;
    }

    public void setFinalCBTVencedor(String finalCBTVencedor) {
        this.finalCBTVencedor = finalCBTVencedor;
    }

    public String getFinalCBTPerdedor() {
        return finalCBTPerdedor;
    }

    public void setFinalCBTPerdedor(String finalCBTPerdedor) {
        this.finalCBTPerdedor = finalCBTPerdedor;
    }

    public int getCodArbitro() {
        return codArbitro;
    }

    public void setCodArbitro(int codArbitro) {
        this.codArbitro = codArbitro;
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

    public int getValorPontua() {
        return valorPontua;
    }

    public void setValorPontua(int valorPontua) {
        this.valorPontua = valorPontua;
    }

    public String getTipoPontua() {
        return tipoPontua;
    }

    public void setTipoPontua(String tipoPontua) {
        this.tipoPontua = tipoPontua;
    }

    public String getDescricaoPontua() {
        return descricaoPontua;
    }

    public void setDescricaoPontua(String descricaoPontua) {
        this.descricaoPontua = descricaoPontua;
    }

    public String getParteDoCorpo() {
        return parteDoCorpo;
    }

    public void setParteDoCorpo(String parteDoCorpo) {
        this.parteDoCorpo = parteDoCorpo;
    }

    public int getNumRodada() {
        return numRodada;
    }

    public void setNumRodada(int numRodada) {
        this.numRodada = numRodada;
    }

    public List<Arbitros_bean> getArbitros() {
        return Arbitros;
    }

    public void setArbitros(List<Arbitros_bean> Arbitros) {
        this.Arbitros = Arbitros;
    }

    public List<Arbitros_bean> getPontoArb() {
        return PontoArb;
    }

    public void setPontoArb(List<Arbitros_bean> PontoArb) {
        this.PontoArb = PontoArb;
    }

    public List<String> getComboArb() {
        return comboArb;
    }

    public void setComboArb(List<String> comboArb) {
        this.comboArb = comboArb;
    }

    public List<String> getComboPtArb() {
        return comboPtArb;
    }

    public void setComboPtArb(List<String> comboPtArb) {
        this.comboPtArb = comboPtArb;
    }

    public String getArbitroSelecionado() {
        return arbitroSelecionado;
    }

    public void setArbitroSelecionado(String arbitroSelecionado) {
        this.arbitroSelecionado = arbitroSelecionado;
    }

    public String getCodPontuaSelecionado() {
        return codPontuaSelecionado;
    }

    public void setCodPontuaSelecionado(String codPontuaSelecionado) {
        this.codPontuaSelecionado = codPontuaSelecionado;
    }

    public List<Arbitros_bean> getListaPont() {
        return listaPont;
    }

    public void setListaPont(List<Arbitros_bean> listaPont) {
        this.listaPont = listaPont;
    }

    public Arbitros_bean getPontoSelecionado() {
        return pontoSelecionado;
    }

    public void setPontoSelecionado(Arbitros_bean pontoSelecionado) {
        this.pontoSelecionado = pontoSelecionado;
    }

    public InscricaoPRT_bean getInscSelec() {
        return inscSelec;
    }

    public void setInscSelec(InscricaoPRT_bean inscSelec) {
        this.inscSelec = inscSelec;
    }

   
    
}
