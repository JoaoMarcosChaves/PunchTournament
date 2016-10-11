//
//package br.tcc.controller;
//
//import br.tcc.bean.CadastroProd_bean;
//import br.tcc.bean.Despesas_bean;
//import br.tcc.dao.CadastroProd_dao;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
//import javax.faces.context.FacesContext;
//
///**
// *
// * @author joãomarcos
// */
//@ManagedBean(name = "ProdutoManagedBean", eager = true)
//@RequestScoped
//public class ProdutoManagedBean {
//private List<CadastroProd_bean> listaProd = new ArrayList<>();
//private List<Despesas_bean> listaPendencias = new ArrayList<>();    
// List <CadastroProd_bean> historico =  new ArrayList<>();
//
//int saldo_definitivo;
//private String user = "teste";    
//private long codigo_produto;
//private String nome_produto;
//private String estoque_minimo;
//private String estoque_maximo;
//private float preco ;
//private String ultimaultima_entrada;
//private String saldo_estoque;
//private String reserva_prod;
//private String status_produto;
//private String DT_AlteracaoCad;
//private String codAlteracaoALU;
//private String altRealizada;
//private String dtAlteracao;
//private String dtMovEstoque;
//private int saldoAnt;
//private int saldoPost;
//private String tipoMov;
//private boolean selectedProdCancelado;
//
//
//public void cadastro (){
//
//        
//    try {
//        CadastroProd_bean cadastro = new CadastroProd_bean();
//        
//        
//        
//        cadastro.setNome_produto(nome_produto);
//        
//        cadastro.setEstoque_minimo(estoque_minimo);
//        cadastro.setEstoque_maximo(estoque_maximo);
//        
//        cadastro.setPreco(preco);
//        
//        
//        
//        cadastro.setSaldo_estoque(String.valueOf(saldo_definitivo));
//        
//        
//        
//        CadastroProd_dao dao = new CadastroProd_dao();
//        dao.adiciona(cadastro);
//            FacesContext context = FacesContext.getCurrentInstance();
//            context.addMessage(null, new FacesMessage("Sucesso","Produto "+ nome_produto+" cadastrado"));
//    } catch (SQLException ex) {
//         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "\n"+ ex));
//    }
//               
//        }
//public void alterar(){
//    try {
//             
//             
//             CadastroProd_bean cadastro = new CadastroProd_bean();
//             
//             cadastro.setCodigo_produto(codigo_produto);
//             
//             cadastro.setNome_produto(nome_produto);
//             
//             cadastro.setEstoque_minimo(estoque_minimo);
//             
//             cadastro.setEstoque_maximo(estoque_maximo);
//             
//             cadastro.setPreco(preco);
//             
//             
//             cadastro.setUser(user);
//         
//           //Essa sequencia de código é feita para acrescentar um valor no campo entrada, e soma-lo ao estoque atual
//             
//          
//             cadastro.setSaldo_estoque(String.valueOf(saldo_definitivo));
//             
//             
//            //fim da sequencia add estoque
//             
//             CadastroProd_dao dao = new CadastroProd_dao();
//             dao.altera(cadastro);
//           //  LimparCampos();
//             listarCadastros();
//             FacesContext context = FacesContext.getCurrentInstance();
//             context.addMessage(null, new FacesMessage("Sucesso","Produto "+ nome_produto+" alterado"));
//         } catch (SQLException ex) {
//             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "\n"+ ex));
//                  
//         }
//}
//
//
//public void desativarProduto(){
//          
//            
//        
//        try {
//            CadastroProd_bean cadastro = new CadastroProd_bean();
//            
//            cadastro.setCodigo_produto(codigo_produto);
//            cadastro.setUser(user);
//            
//            CadastroProd_dao dao = new CadastroProd_dao();
//             dao.desativaProduto(cadastro);
//             FacesContext context = FacesContext.getCurrentInstance();
//             context.addMessage(null, new FacesMessage("Sucesso","Produto "+ nome_produto+" desativado"));
//             listarCadastros();
//            
//        } catch (SQLException ex) {
//         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "\n"+ ex));
//        }
//      }
//      
//public void ativarProduto(){
//          
//        
//        try {
//            CadastroProd_bean cadastro = new CadastroProd_bean();
//            
//            cadastro.setCodigo_produto(codigo_produto);
//            cadastro.setUser(user);
//            
//            CadastroProd_dao dao = new CadastroProd_dao();
//             dao.ativaProduto(cadastro);
//             FacesContext context = FacesContext.getCurrentInstance();
//             context.addMessage(null, new FacesMessage("Sucesso","Produto "+ nome_produto+" desativado"));
//             listarCadastros();
//             
//        } catch (SQLException ex) {
//         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "\n"+ ex));
//        }
//      }
//
//public void consultaHistorico(String cod) throws SQLException{
//    
//    CadastroProd_dao dao = new CadastroProd_dao();
//    
//   historico = dao.ListarHistAlteracoes(cod);
//    
//       
//    if(historico.size() == 0){
//        
//         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nenhum alteração realizada neste cadastro", "\n"));
//        //    JOptionPane.showMessageDialog(null,"Nenhum alteração realizada neste cadastro");
//    }
//    
//}
//
//public void montaPesquisaAtivo(String nome, String cod) throws SQLException{
//     CadastroProd_dao dao = new CadastroProd_dao();
//        
//     
//     String sql = "select * from TB_cadProd where statusPROD = 'Ativo' ";
//          if(!nome.equals("") && cod.equals("0") ){
//              
//               sql +=  " and nomePROD like '%" + nome+"%'";
//              
//          }else if(nome.equals("") && !cod.equals("0")){
//              sql +=  "and codigoPROD = " + cod;
//          } else if(!nome.equals("") && !cod.equals("0")){
//              sql +=  " and nomePROD like '%"+nome+"'% " +" and codigoProd = " + cod;
//          }          
//     
//          listaProd = dao.getLista(sql);
//        
//          
// }
//    
// public void montaPesquisaDesat(String nome, String cod) throws SQLException{
//      CadastroProd_dao dao = new CadastroProd_dao();
//     
//     String sql = "select * from TB_cadProd ";
//          if(!nome.equals("") && cod.equals("0") ){
//              
//               sql +=  " where nomePROD like '" + nome+"'";
//              
//          }else if(nome.equals("") && !cod.equals("0")){
//              sql +=  "where codigoPROD = " + cod;
//          } else if(!nome.equals("") && !cod.equals("0")){
//              sql +=  " where nomePROD like '%"+nome+"%' " +" and codigoProd = " + cod;
//          }          
//     
//     
//          listaProd = dao.getLista2(sql);
//          
// }
//
// public void listarCadastros (){
//        
//       
//           if(isSelectedProdCancelado()==true){
//           try {
//                 
//               montaPesquisaDesat(nome_produto , String.valueOf(codigo_produto));
//           } catch (SQLException ex) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "\n"+ ex));
//           }
//       
//        }else{
//           try {
//               montaPesquisaAtivo(nome_produto, String.valueOf(codigo_produto));
//           } catch (SQLException ex) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "\n"+ ex));
//           }
//}
//     
//    }
// 
// public void pesquisaPendencia(String cod){
//    try {
//        CadastroProd_dao dao = new CadastroProd_dao();
//        
//        listaPendencias = dao.getLista3(String.valueOf(cod));
//        
//        if(listaPendencias.size() == 0){
//            
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nenhum débito para este produto", "\n"));
//        }
//        
//     
//    } catch (SQLException ex) {
//        Logger.getLogger(ProdutoManagedBean.class.getName()).log(Level.SEVERE, null, ex);
//    }
//    }
// 
// public void limparCampos(){
//        
//        setCodigo_produto(Integer.valueOf(""));
//        setDT_AlteracaoCad("");
//        setEstoque_maximo("");
//        setEstoque_minimo("");
//        setNome_produto("");
//        setPreco(Integer.valueOf(""));
//        setStatus_produto("");
//        setUltimaultima_entrada("");
//        setSaldo_estoque("");
//        
//        
// 
//    }
// 
//public boolean isSelectedProdCancelado() {
//        return selectedProdCancelado;
//    }
//
//    public void setSelectedProdCancelado(boolean selectedProdCancelado) {
//        this.selectedProdCancelado = selectedProdCancelado;
//    }
//
//
//    public String getDT_AlteracaoCad() {
//        return DT_AlteracaoCad;
//    }
//
//    public void setDT_AlteracaoCad(String DT_AlteracaoCad) {
//        this.DT_AlteracaoCad = DT_AlteracaoCad;
//    }
//
//    public String getReserva_prod() {
//        return reserva_prod;
//    }
//
//    public void setReserva_prod(String reserva_prod) {
//        this.reserva_prod = reserva_prod;
//    }
//
//    public String getStatus_produto() {
//        return status_produto;
//    }
//
//    public void setStatus_produto(String status_produto) {
//        this.status_produto = status_produto;
//    }
//
//   
//    public long getCodigo_produto() {
//        return codigo_produto;
//    }
//
//    public void setCodigo_produto(long codigo_produto) {
//        this.codigo_produto = codigo_produto;
//    }
//
//    public String getNome_produto() {
//        return nome_produto;
//    }
//
//    public void setNome_produto(String nome_produto) {
//        this.nome_produto = nome_produto;
//    }
//
//    public String getEstoque_minimo() {
//        return estoque_minimo;
//    }
//
//    public void setEstoque_minimo(String estoque_minimo) {
//        this.estoque_minimo = estoque_minimo;
//    }
//
//    public String getEstoque_maximo() {
//        return estoque_maximo;
//    }
//
//    public void setEstoque_maximo(String estoque_maximo) {
//        this.estoque_maximo = estoque_maximo;
//    }
//
//    public float getPreco() {
//        return preco;
//    }
//
//    public void setPreco(float preco) {
//        this.preco = preco;
//    }
//
//    public String getUltimaultima_entrada() {
//        return ultimaultima_entrada;
//    }
//
//    public void setUltimaultima_entrada(String ultimaultima_entrada) {
//        this.ultimaultima_entrada = ultimaultima_entrada;
//    }
//
//    public String getSaldo_estoque() {
//        return saldo_estoque;
//    }
//
//    public void setSaldo_estoque(String saldo_estoque) {
//        this.saldo_estoque = saldo_estoque;
//    }
//
//    public String getUser() {
//        return user;
//    }
//
//    public void setUser(String user) {
//        this.user = user;
//    }
//
//    public String getCodAlteracaoALU() {
//        return codAlteracaoALU;
//    }
//
//    public void setCodAlteracaoALU(String codAlteracaoALU) {
//        this.codAlteracaoALU = codAlteracaoALU;
//    }
//
//    public String getAltRealizada() {
//        return altRealizada;
//    }
//
//    public void setAltRealizada(String altRealizada) {
//        this.altRealizada = altRealizada;
//    }
//
//    public String getDtAlteracao() {
//        return dtAlteracao;
//    }
//
//    public void setDtAlteracao(String dtAlteracao) {
//        this.dtAlteracao = dtAlteracao;
//    }
//
//    public String getDtMovEstoque() {
//        return dtMovEstoque;
//    }
//
//    public void setDtMovEstoque(String dtMovEstoque) {
//        this.dtMovEstoque = dtMovEstoque;
//    }
//
//    public int getSaldoAnt() {
//        return saldoAnt;
//    }
//
//    public void setSaldoAnt(int saldoAnt) {
//        this.saldoAnt = saldoAnt;
//    }
//
//    public int getSaldoPost() {
//        return saldoPost;
//    }
//
//    public void setSaldoPost(int saldoPost) {
//        this.saldoPost = saldoPost;
//    }
//
//    public String getTipoMov() {
//        return tipoMov;
//    }
//
//    public void setTipoMov(String tipoMov) {
//        this.tipoMov = tipoMov;
//    }
//
//    public int getSaldo_definitivo() {
//        return saldo_definitivo;
//    }
//
//    public void setSaldo_definitivo(int saldo_definitivo) {
//        this.saldo_definitivo = saldo_definitivo;
//    }
//
//    
//    public List<CadastroProd_bean> getListaProd() {
//        return listaProd;
//    }
//
//    public void setListaProd(List<CadastroProd_bean> listaProd) {
//        this.listaProd = listaProd;
//    }
//
//    public List<Despesas_bean> getListaPendencias() {
//        return listaPendencias;
//    }
//
//    public void setListaPendencias(List<Despesas_bean> listaPendencias) {
//        this.listaPendencias = listaPendencias;
//    }
//
//    public List<CadastroProd_bean> getHistorico() {
//        return historico;
//    }
//
//    public void setHistorico(List<CadastroProd_bean> historico) {
//        this.historico = historico;
//    }
//
//    
//
//    
//
//
//}
