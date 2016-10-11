//package br.tcc.dao;
//
//import br.tcc.bean.CadastroAluno_bean;
//
//
//import Conexao.ConectaBanco;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CadastroAluno_dao {
//
//        private Connection conexao;
//    
//    public CadastroAluno_dao() throws SQLException{
//        this.conexao = ConectaBanco.getConexao();
//    }
//    public void adiciona(CadastroAluno_bean c1) throws SQLException{
//    
//        //prepara conexão
//        String sql = "exec usp_inserirAluno \n" +
//"@nome = ?,\n" +
//"@idade = ? ,\n" +
//"@peso = ?, \n" +
//"@cep = ?, \n" +
//"@cpf = ?, \n" +
//"@telefone = ?, \n" +
//"@email = ?, \n" +
//"@endereco = ?, \n" +
//"@num_residencia = ?, \n" +
//"@complemento = ?, \n" +
//"@bairro = ?, \n" +
//"@cidade = ?, \n" +
//"@graduacao = ?, \n" +
//"@valor_mensalidade = ?, \n" +
//"@sexo = ?, \n" +
//"@atestado = ?";
//
//        //seta os valores nos campos declarados acima
//        PreparedStatement stmt = conexao.prepareStatement(sql);
//       // stmt.setInt(1, c1.getMatricula());
//        stmt.setString(1, c1.getNome());
//        stmt.setInt(2, c1.getIdade());
//        stmt.setDouble(3, c1.getPeso());
//        stmt.setString(4, c1.getCep());
//        stmt.setString(5, c1.getCpf());
//        stmt.setString(6, c1.getTelefone());
//        stmt.setString(7, c1.getEmail());
//        stmt.setString(8, c1.getEndereco());
//        stmt.setString(9, c1.getNum_residencia());
//        stmt.setString(10, c1.getComplemento());
//        stmt.setString(11, c1.getBairro());
//        stmt.setString(12, c1.getCidade());
//        stmt.setString(13, c1.getGraduacao());
//        stmt.setDouble(14, c1.getValor_mensalidade());
//       
//        stmt.setString(15, c1.getSexo());
//        stmt.setString(16, c1.getAtestado());
//        //executa o códido sql de insert que declaramos 
//        stmt.execute();
//        stmt.close();
//
//    }  
//    //consulta por nome
//    public List<CadastroAluno_bean>getListaAlunosNome(String query) throws SQLException{
//        
//    String sql = query;
//   
//    PreparedStatement stmt = this.conexao.prepareStatement(sql);
//      
//        
//        
//        ResultSet rs = stmt.executeQuery(); 
//        
//        ArrayList<CadastroAluno_bean> minhaLista = new ArrayList<CadastroAluno_bean>();
//        
//        
//        while(rs.next()){
//            CadastroAluno_bean c1 = new CadastroAluno_bean();
//            
//            c1.setStatusMatricula(rs.getString("statusMatriculaALU"));
//            c1.setMatricula(rs.getString("MatriculaALU"));
//            c1.setNome(rs.getString("nomeALU"));
//            c1.setIdade(rs.getInt("idadeALU"));
//            c1.setPeso(rs.getFloat("pesoALU"));
//            c1.setCep(rs.getString("cepALU"));
//            c1.setCpf(rs.getString("cpfALU"));
//            c1.setTelefone(rs.getString("telefoneALU"));
//            c1.setEmail(rs.getString("emailALU"));
//            c1.setEndereco(rs.getString("enderecoALU"));
//            c1.setNum_residencia(rs.getString("numResidenciaALU"));
//            c1.setComplemento(rs.getString("complementoALU"));
//            c1.setBairro(rs.getString("bairroALU"));
//            c1.setCidade(rs.getString("cidadeALU"));
//            c1.setGraduacao(rs.getString("graduacaoALU"));
//            c1.setValor_mensalidade(rs.getFloat("valorMensalidadeALU"));
//            c1.setSexo(rs.getString("sexoALU"));
//           
//            c1.setAtestado(rs.getString("atestadoALU"));
//            minhaLista.add(c1);
//        }
//        rs.close();
//        stmt.close();
//        return minhaLista;
//   
//    }
//    
//    public List<CadastroAluno_bean>getListaAlunosMatricula(String matricula) throws SQLException{
//        
//    String sql = "select * from TB_CadAluno where matriculaALU = ? ";
//   
//        
//    PreparedStatement stmt = this.conexao.prepareStatement(sql);
//        stmt.setString(1,matricula);
//       
//        
//        ResultSet rs = stmt.executeQuery(); 
//        
//        ArrayList<CadastroAluno_bean> minhaLista = new ArrayList<CadastroAluno_bean>();
//        
//        
//        while(rs.next()){
//            CadastroAluno_bean c1 = new CadastroAluno_bean();
//            
//            c1.setStatusMatricula(rs.getString("statusMatriculaALU"));
//            c1.setMatricula(rs.getString("MatriculaALU"));
//            c1.setNome(rs.getString("nomeALU"));
//            c1.setIdade(rs.getInt("idadeALU"));
//            c1.setPeso(rs.getFloat("pesoALU"));
//            c1.setCep(rs.getString("cepALU"));
//            c1.setCpf(rs.getString("cpfALU"));
//            c1.setTelefone(rs.getString("telefoneALU"));
//            c1.setEmail(rs.getString("emailALU"));
//            c1.setEndereco(rs.getString("enderecoALU"));
//            c1.setNum_residencia(rs.getString("numResidenciaALU"));
//            c1.setComplemento(rs.getString("complementoALU"));
//            c1.setBairro(rs.getString("bairroALU"));
//            c1.setCidade(rs.getString("cidadeALU"));
//            c1.setGraduacao(rs.getString("graduacaoALU"));
//            c1.setValor_mensalidade(rs.getFloat("valorMensalidadeALU"));
//            c1.setSexo(rs.getString("sexoALU"));
//           
//            c1.setAtestado(rs.getString("atestadoALU"));
//            minhaLista.add(c1);
//        }
//        rs.close();
//        stmt.close();
//        return minhaLista;
//   
//    }
//    
//    
//    public void altera (CadastroAluno_bean c1) throws SQLException{
//    
//    String sql = "exec usp_hisAltALU\n" +
// "@matriculaALU = ?\n" +
//",@nomeALU = ?\n" +
//",@idadeALU = ?\n" +
//",@pesoALU = ?\n" +
//",@cepALU = ?\n" +
//",@cpfALU = ?\n" +
//",@telefoneALU = ?\n" +
//",@emailALU = ?\n" +
//",@enderecoALU = ?\n" +
//",@numResidenciaALU = ?\n" +
//",@complementoALU = ?\n" +
//",@bairroALU = ?\n" +
//",@cidadeALU = ?\n" +
//",@graduacaoALU = ?\n" +
//",@valorMensalidadeALU = ?\n" +
//",@sexoALU = ?\n" +
//",@atestadoALU = ?\n" +
//",@usuarioCNX = ?";
//    
//    PreparedStatement stmt = this.conexao.prepareStatement(sql);
//    
//        stmt.setString(1, c1.getMatricula());
//        stmt.setString(2, c1.getNome());
//        stmt.setInt(3, c1.getIdade());
//        stmt.setDouble(4, c1.getPeso());
//        stmt.setString(5, c1.getCep());
//        stmt.setString(6, c1.getCpf());
//        stmt.setString(7, c1.getTelefone());
//        stmt.setString(8, c1.getEmail());
//        stmt.setString(9, c1.getEndereco());
//        stmt.setString(10, c1.getNum_residencia());
//        stmt.setString(11, c1.getComplemento());
//        stmt.setString(12, c1.getBairro());
//        stmt.setString(13, c1.getCidade());
//        stmt.setString(14, c1.getGraduacao());
//        stmt.setDouble(15, c1.getValor_mensalidade());
//        stmt.setString(16, c1.getSexo());
//        stmt.setString(17, c1.getAtestado());
//        stmt.setString(18, c1.getUser());
//        
//        stmt.execute();
//        stmt.close();
//
//    
//    
//    }
//    
//     public void trancar (CadastroAluno_bean c1) throws SQLException{
//    String sql = "update TB_CadAluno set statusMatriculaALU = 'Trancado'  where matriculaALU = ? "
//            + " insert into TB_histAltCadALU (altRealizadaALU, dtAlteracaoALU, usuarioCNX, matriculaALU) values(\n" +
//               "'A matricula do aluno foi trancada'\n" +
//               ",getdate()\n" +
//               ",?\n" +
//               ",?\n" +
//               ")    ";
//    
//    PreparedStatement stmt = this.conexao.prepareStatement(sql);
//    
//    
//        stmt.setString(1, c1.getMatricula());
//        stmt.setString(2, c1.getUser());
//        stmt.setString(3, c1.getMatricula());
//        
//        stmt.execute();
//        stmt.close();
//
//    }
//    
//      public void destrancar (CadastroAluno_bean c1) throws SQLException{
//    String sql = "update TB_CadAluno set statusMatriculaALU = 'Aberto'  where matriculaALU = ? "
//            + " insert into TB_histAltCadALU (altRealizadaALU, dtAlteracaoALU, usuarioCNX, matriculaALU) values(\n" +
//               "'A matricula do aluno foi destrancada'\n" +
//               ",getdate()\n" +
//               ",?\n" +
//               ",?\n" +
//               ")    ";
//    
//    PreparedStatement stmt = this.conexao.prepareStatement(sql);
//    
//    
//        stmt.setString(1, c1.getMatricula());
//        stmt.setString(2, c1.getUser());
//        stmt.setString(3, c1.getMatricula());
//        
//        stmt.execute();
//        stmt.close();
//
//    }
//    
//     public List<CadastroAluno_bean>ListarHistAlteracoes(String matricula) throws SQLException{
//        
//    String sql = "select * from TB_histAltCadALU where matriculaALU = ? ";
//   
//        
//    PreparedStatement stmt = this.conexao.prepareStatement(sql);
//        stmt.setString(1,matricula);
//       
//        
//        ResultSet rs = stmt.executeQuery(); 
//        
//        ArrayList<CadastroAluno_bean> minhaLista = new ArrayList<CadastroAluno_bean>();
//        
//        
//        while(rs.next()){
//            CadastroAluno_bean c1 = new CadastroAluno_bean();
//            
//            
//            c1.setCodAlteracaoALU(rs.getString("codAlteracaoALU"));
//            c1.setAltRealizada(rs.getString("altRealizadaALU"));
//            c1.setDtAlteracao(rs.getString("dtAlteracaoALU"));
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
//     
//     
//    
//}
