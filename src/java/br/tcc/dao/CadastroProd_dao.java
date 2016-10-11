//
//package br.tcc.dao;
//
//import br.tcc.bean.CadastroProd_bean;
//import br.tcc.bean.Despesas_bean;
//import br.tcc.bean.Vendas_bean;
//
//import Conexao.ConectaBanco;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author joãomarcos
// */
//public class CadastroProd_dao {
//
//    
// private Connection conexao;
//    
//    public CadastroProd_dao() throws SQLException{
//        this.conexao = ConectaBanco.getConexao();
//    }
//    public void adiciona(CadastroProd_bean c1) throws SQLException{
//    
//        
//        String sql = "EXEC uspCadProd @NomeProd = ?, @EstoqueMin = ?,@EstoqueMax = ?, @PrecoProd = ?, @Saldo_estoque = ? ";
//
//        
//        
//        //seta os valores nos campos declarados acima
//        PreparedStatement stmt = conexao.prepareStatement(sql);
//       // stmt.setInt(1, c1.getMatricula());
//        stmt.setString(1, c1.getNome_produto());
//        
//        stmt.setString(2, c1.getEstoque_minimo());
//        
//        stmt.setString(3, c1.getEstoque_maximo());
//        
//        stmt.setFloat(4, c1.getPreco());
//        
//        stmt.setString(5, c1.getSaldo_estoque());
//        
//      //  stmt.setString(6, c1.getUltimaultima_entrada());
//        
//        //executa o códido sql de insert que declaramos 
//        stmt.execute();
//        stmt.close();
//
//    }  
//      
//    
//    //consulta por código
//    public List<CadastroProd_bean>getLista(String query) throws SQLException{
//        
//    String sql = query;
//    PreparedStatement stmt = this.conexao.prepareStatement(sql);
//        //stmt.setString(1,nome_produto);
//        //stmt.setString(1, codigo_produto);
//       
//        ResultSet rs = stmt.executeQuery(); 
//        
//        ArrayList<CadastroProd_bean> minhaLista = new ArrayList<CadastroProd_bean>();
//        
//        
//        while(rs.next()){
//            CadastroProd_bean c1 = new CadastroProd_bean();
//            c1.setCodigo_produto(rs.getLong("codigoPROD"));
//            c1.setNome_produto(rs.getString("nomePROD"));
//            c1.setEstoque_minimo(rs.getString("estoqueMinimoPROD"));
//            c1.setEstoque_maximo(rs.getString("estoqueMaximoPROD"));
//            c1.setPreco(rs.getFloat("precoPROD"));
//            c1.setSaldo_estoque(rs.getString("saldoEstoquePROD"));
//            c1.setReserva_prod(rs.getString("reservaPROD"));
//            c1.setStatus_produto(rs.getString("statusPROD"));
//            c1.setDT_AlteracaoCad(rs.getString("DTalteracaoCadPROD"));
//            minhaLista.add(c1);
//        }
//        rs.close();
//        stmt.close();
//        return minhaLista;
//   
//    }
//    
//    //consulta por nome
//    public List<CadastroProd_bean>getLista2(String query) throws SQLException{
//        
//    String sql = query;
//    PreparedStatement stmt = this.conexao.prepareStatement(sql);
//        
//        
//       
//        ResultSet rs = stmt.executeQuery(); 
//        
//        ArrayList<CadastroProd_bean> minhaLista = new ArrayList<CadastroProd_bean>();
//        
//        
//        while(rs.next()){
//            CadastroProd_bean c1 = new CadastroProd_bean();
//            c1.setCodigo_produto(rs.getLong("codigoPROD"));
//            c1.setNome_produto(rs.getString("nomePROD"));
//            c1.setEstoque_minimo(rs.getString("estoqueMinimoPROD"));
//            c1.setEstoque_maximo(rs.getString("estoqueMaximoPROD"));
//            c1.setPreco(rs.getFloat("precoPROD"));
//            
//            c1.setSaldo_estoque(rs.getString("saldoEstoquePROD"));
//            c1.setReserva_prod(rs.getString("reservaPROD"));
//            c1.setStatus_produto(rs.getString("statusPROD"));
//            c1.setDT_AlteracaoCad(rs.getString("DTalteracaoCadPROD"));
//            minhaLista.add(c1);
//        }
//        rs.close();
//        stmt.close();
//        return minhaLista;
//    }
//    
//    public List<CadastroProd_bean>pegaProdFocusCodigoDesp(String cod) throws SQLException{
//        
//    String sql = "select * from TB_cadProd where codigoPROD = ? and statusPROD = 'Ativo'";
//    PreparedStatement stmt = this.conexao.prepareStatement(sql);
//        stmt.setString(1,cod);
//        
//       
//        ResultSet rs = stmt.executeQuery(); 
//        
//        ArrayList<CadastroProd_bean> minhaLista = new ArrayList<CadastroProd_bean>();
//        
//        
//        while(rs.next()){
//            CadastroProd_bean c1 = new CadastroProd_bean();
//            c1.setCodigo_produto(rs.getLong("codigoPROD"));
//            c1.setNome_produto(rs.getString("nomePROD"));
//            c1.setEstoque_minimo(rs.getString("estoqueMinimoPROD"));
//            c1.setEstoque_maximo(rs.getString("estoqueMaximoPROD"));
//            c1.setPreco(rs.getFloat("precoPROD"));
//            
//            c1.setSaldo_estoque(rs.getString("saldoEstoquePROD"));
//            c1.setReserva_prod(rs.getString("reservaPROD"));
//            c1.setStatus_produto(rs.getString("statusPROD"));
//            c1.setDT_AlteracaoCad(rs.getString("DTalteracaoCadPROD"));
//           
//            minhaLista.add(c1);
//        }
//        rs.close();
//        stmt.close();
//        return minhaLista;
//    }
//    
//    public List<Despesas_bean>getLista3(String codigo_ProdDevido) throws SQLException{
//        
//    String sql = "select d.codigoPendencia, d.matriculaALU,d.precoPROD,d.quantidadePendencia,d.valorTotalPendencia\n" +
//"from TB_cadProd i, TB_Pendencia d where i.codigoPROD = ? and d.statusPendencia = 'Venda não concluída'";
//    PreparedStatement stmt = this.conexao.prepareStatement(sql);
//        stmt.setString(1,codigo_ProdDevido);
//        
//       
//        ResultSet rs = stmt.executeQuery(); 
//        
//        ArrayList<Despesas_bean> minhaLista = new ArrayList<Despesas_bean>();
//        
//        
//        while(rs.next()){
//            Despesas_bean c3 = new Despesas_bean();
//            c3.setCodigo_DividaProd(rs.getString("codigoPendencia"));            
//            c3.setCodigo_AlunoDevedor(rs.getString("matriculaALU"));
//            c3.setPreco(rs.getFloat("precoPROD"));
//            c3.setQuantidade_devida(rs.getString("quantidadePendencia"));
//            c3.setValorTotalDivida(rs.getFloat("valorTotalPendencia"));
//            
//            minhaLista.add(c3);
//        }
//        rs.close();
//        stmt.close();
//        return minhaLista;
//    }
//    
//    
//    
//public void altera (CadastroProd_bean c1) throws SQLException{
////    String sql = "update TB_cadProd set  nomeProduto = ?, estoqueMinimo= ?, estoqueMaximo= ?, preco= ?, saldoEstoque= ?"
////            
////            + "      where codigoProduto = ? "
////            + "update TB_cadProd set DTalteracaoCad = GETDATE() where codigoProduto = ? ";
//    String sql = "exec usp_histAltCadPROD\n" +
//                 "@usuarioCNX = ?\n" +
//                 ",@codigoPROD = ?\n" +
//                 ",@NomeProd =?\n" +
//                 ",@EstoqueMin = ?\n" +
//                 ",@EstoqueMax = ?\n" +
//                 ",@PrecoProd = ?";
//    
//    PreparedStatement stmt = this.conexao.prepareStatement(sql);
//    
//        stmt.setString(1,c1.getUser());
//        stmt.setLong(2, c1.getCodigo_produto()); 
//        stmt.setString(3, c1.getNome_produto());
//        stmt.setString(4, c1.getEstoque_minimo());
//        stmt.setString(5, c1.getEstoque_maximo());
//        stmt.setFloat(6, c1.getPreco());
//        
//       //System.out.println("codigo " +c1.getCodigo_produto());
//        
//        stmt.execute();
//        stmt.close();
//
//    
//    
//    }
//    
//public void desativaProduto (CadastroProd_bean c1) throws SQLException{
//    String sql = "update TB_cadProd set statusPROD = 'Desativado' where codigoPROD = ?\n" +
//"\n" +
//"insert into TB_histAltCadPROD (altRealizadaPROD, dtAlteracaoPROD, usuarioCNX, codigoPROD) values(\n" +
//"              'Produto desativado'\n" +
//"              ,getdate()\n" +
//"              ,?\n" +
//"              ,?\n" +
//"              )";
//            
//    PreparedStatement stmt = this.conexao.prepareStatement(sql);
//    
//        stmt.setLong(1, c1.getCodigo_produto());    
//        stmt.setString(2, c1.getUser());
//        stmt.setLong(3, c1.getCodigo_produto());    
//        
//        stmt.execute();
//        stmt.close();
//    }
//
//public void ativaProduto (CadastroProd_bean c1) throws SQLException{
//    String sql = "update TB_cadProd set statusPROD = 'Ativo' where codigoPROD = ?"
//            +"\n"
//            + "insert into TB_histAltCadPROD (altRealizadaPROD, dtAlteracaoPROD, usuarioCNX, codigoPROD) values(\n" +
//              "'Produto reativado'\n" +
//              ",getdate()\n" +
//              ",?" +
//              ",?\n" +
//              ")";
//            
//    PreparedStatement stmt = this.conexao.prepareStatement(sql);
//    
//        stmt.setLong(1, c1.getCodigo_produto());    
//        stmt.setString(2, c1.getUser());
//        stmt.setLong(3, c1.getCodigo_produto());    
//        
//        stmt.execute();
//        stmt.close();
//    }
//
//public void entradaEstoque (CadastroProd_bean c1) throws SQLException{
//    String sql = "exec usp_EntradaEstoque\n" +
//"@codigoProd = ?,\n" +
//"@QtdEntrada = ?"+
//",@Usuario = ?" ;
//            
//    PreparedStatement stmt = this.conexao.prepareStatement(sql);
//    
//        stmt.setLong(1, c1.getCodigo_produto());    
//        stmt.setString(2, c1.getSaldo_estoque());    
//        stmt.setString(3, c1.getUser());    
//        stmt.execute();
//        stmt.close();
//    }
//
//public void saidaEstoque (CadastroProd_bean c1) throws SQLException{
//    String sql = "exec usp_SaidaEstoque\n" +
//"@codigoProd = ?,\n" +
//"@QtdSaida = ?"+
//",@Usuario = ?"            ;
//            
//    PreparedStatement stmt = this.conexao.prepareStatement(sql);
//    
//        stmt.setLong(1, c1.getCodigo_produto());    
//        stmt.setString(2, c1.getSaldo_estoque());    
//        stmt.setString(3, c1.getUser());    
//        stmt.execute();
//        stmt.close();
//    }
//
//public List<CadastroProd_bean>ListarHistAlteracoes(String cod) throws SQLException{
//        
//    String sql = "select * from TB_histAltCadPROD where codigoPROD = ? ";
//   
//        
//    PreparedStatement stmt = this.conexao.prepareStatement(sql);
//        stmt.setString(1,cod);
//       
//        
//        ResultSet rs = stmt.executeQuery(); 
//        
//        ArrayList<CadastroProd_bean> minhaLista = new ArrayList<CadastroProd_bean>();
//        
//        
//        while(rs.next()){
//            CadastroProd_bean c1 = new CadastroProd_bean();
//            
//            
//            c1.setCodAlteracaoALU(rs.getString("codAlteracaoPROD"));
//            c1.setAltRealizada(rs.getString("altRealizadaPROD"));
//            c1.setDtAlteracao(rs.getString("dtAlteracaoPROD"));
//            c1.setUser(rs.getString("usuarioCNX"));
//            
//            minhaLista.add(c1);
//        }
//        rs.close();
//        stmt.close();
//        return minhaLista;
//   
//    }
//
//
//public List<CadastroProd_bean>PegaNomeProduto(String cod) throws SQLException{
//    
// String sql = "select nomeProd from TB_cadProd where codigoPROD = ? ";
//   
//        
//    PreparedStatement stmt = this.conexao.prepareStatement(sql);
//    stmt.setString(1,cod);
//    ResultSet rs = stmt.executeQuery(); 
//    ArrayList<CadastroProd_bean> minhaLista = new ArrayList<CadastroProd_bean>();
//    
//    while(rs.next()){
//            CadastroProd_bean c1 = new CadastroProd_bean();
//            
//            
//            c1.setNome_produto(rs.getString("nomeProd"));
//            
//            minhaLista.add(c1);
//        }
//        rs.close();
//        stmt.close();
//        return minhaLista;
//    
//}
//
//
//public List<CadastroProd_bean>consultaMovProd(String query) throws SQLException{
//    
// String sql = query;
//   
//        
//    PreparedStatement stmt = this.conexao.prepareStatement(sql);
//    
//    ResultSet rs = stmt.executeQuery(); 
//    ArrayList<CadastroProd_bean> minhaLista = new ArrayList<CadastroProd_bean>();
//    
//    while(rs.next()){
//            CadastroProd_bean c1 = new CadastroProd_bean();
////{"Código produto","Nome produto","Data movimento", "Tipo movimento", "Saldo anterior", "Saldo posterior", "Saldo atual", "Usuário que a realizou"});
//           
//            c1.setCodigo_produto(rs.getLong("codigoPROD"));
//            c1.setNome_produto(rs.getString("nomePROD"));
//            c1.setDtMovEstoque(rs.getString("dtMOV"));
//            c1.setTipoMov(rs.getString("tipoMOV"));
//            c1.setSaldoAnt(rs.getInt("saldoAntMOV"));
//            c1.setSaldoPost(rs.getInt("saldoPostMOV"));
//            c1.setSaldo_estoque(rs.getString("saldoEstoquePROD"));
//            c1.setUser(rs.getString("usuarioCNX"));
//            
//            
//            minhaLista.add(c1);
//        }
//        rs.close();
//        stmt.close();
//        return minhaLista;
//    
//}
//
//
//}
