//
//package br.tcc.controller;
//
//import br.tcc.bean.CadastroAluno_bean;
//import br.tcc.dao.CadastroAluno_dao;
//import br.tcc.webCep.WebServiceCep;
//import java.io.Serializable;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
//import javax.faces.context.FacesContext;
//import org.primefaces.event.SelectEvent;
//import org.primefaces.event.UnselectEvent;
//
///**
// *
// * @author joãomarcos
// */
//@ManagedBean(name = "AlunoManagedBean", eager = true)
//@RequestScoped
//
//
//public class AlunoManagedBean implements Serializable{
//static int teste = 0;
//List<CadastroAluno_bean> cadastros =  new ArrayList<>();
//List<CadastroAluno_bean> selecionados = new ArrayList<>();
//List <CadastroAluno_bean> historico = new ArrayList<>();    
//    
//private String user ;
//private String statusMatricula;    
//private String matriculaPesq;
//private String nomePesq;
//private String matricula;
//private String nome;
//private int idade;
//private float peso;
//private String cep;
//private  String cpf;
//private String telefone;
//private String email;
//private String endereco;
//private String num_residencia;
//private String complemento;
//private String bairro;
//private String cidade;
//private String graduacao;
//private float valor_mensalidade;
//private String sexo;
//private String atestado;
//private String codAlteracaoALU;
//private String altRealizada;
//private String dtAlteracao;
//private boolean selected;
//
//public void cadastro (){
//    try {
//        CadastroAluno_bean cadastro = new CadastroAluno_bean();
//        
//        
//        cadastro.setNome(this.nome);
//        
//        cadastro.setIdade(this.idade);
//        
//        cadastro.setPeso(this.peso);
//        
//        cadastro.setCep(this.cep);
//        
//        cadastro.setCpf(this.cpf);
//        
//        
//        cadastro.setTelefone(this.telefone);
//        
//        cadastro.setEmail(this.email);
//        
//        cadastro.setEndereco(this.endereco);
//        
//        cadastro.setNum_residencia(this.num_residencia);
//        
//        cadastro.setComplemento(this.complemento);
//        
//        cadastro.setBairro(this.bairro);
//        
//        cadastro.setCidade(this.cidade);
//        
//        cadastro.setGraduacao(this.graduacao);
//        
//        cadastro.setValor_mensalidade(this.valor_mensalidade);
//        
//        cadastro.setSexo(this.sexo);
//        
//        cadastro.setAtestado(atestado);
//        
//        CadastroAluno_dao dao = new CadastroAluno_dao();            
//        dao.adiciona(cadastro);
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage("Sucesso","Aluno "+ nome+" cadastrado"));
//    } catch (SQLException ex) {
//      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "\n"+ ex));
//    }
//}
//
//public void trancarMatricula(){
//           
//        try {
//            CadastroAluno_bean aluno = new CadastroAluno_bean();
//           
//            aluno.setMatricula(matriculaPesq);
//            aluno.setUser(user);
//            
//            CadastroAluno_dao dao = new CadastroAluno_dao();
//            dao.trancar(aluno);
//            FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage("Sucesso","Matricula do aluno "+ nome +" trancada"));
//        montaPesquisa();
//        } catch (SQLException ex) {
//     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "\n"+ ex));
//        }
//}
//            
//      public void destrancarMatricula(){
//           
//        try {
//            CadastroAluno_bean aluno = new CadastroAluno_bean();
//           
//            aluno.setMatricula(matriculaPesq);
//            aluno.setUser(user);
//            
//            CadastroAluno_dao dao = new CadastroAluno_dao();
//            dao.destrancar(aluno);
//           FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage("Sucesso","Matricula do aluno "+ aluno.getNome()+" trancada"));
//        montaPesquisa();
//        } catch (SQLException ex) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "\n"+ ex));
//        }
//}
//
//public void montaPesquisa() throws SQLException{
//
//        
//         
//          CadastroAluno_dao dao = new CadastroAluno_dao();
//          String sql = "select * from TB_cadAluno ";
//          if(!nomePesq.equals("") && matriculaPesq.equals("") ){
//              
//               sql +=  "where nomeALU like '%" + this.nomePesq+"%'";
//              
//          }else if(nomePesq.equals("") && !matriculaPesq.equals("")){
//              sql +=  "where matriculaALU = " + matriculaPesq;
//          } else if(!nomePesq.equals("") && !matriculaPesq.equals("")){
//              sql +=  "where nomeALU like '"+nomePesq+"' " +" and matriculaALU = " + matriculaPesq;
//          }          
//    System.out.println(sql);        
//          cadastros = dao.getListaAlunosNome(sql); 
//     
//      }
//
//public void consultaHistorico(String matricula) throws SQLException{
//    
//    CadastroAluno_dao dao = new CadastroAluno_dao();
//    
//   historico = dao.ListarHistAlteracoes(matricula);
//        
//    if(historico.size() == 0){
//        
//             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nenhum alteração realizada neste cadastro", "\n"));
//    }else{
//        String[] linha = new String[] {null, null, null, null};
//        for( int i=0; i <historico.size(); i++){
//           
//            
//            codAlteracaoALU =(historico.get(i).getCodAlteracaoALU());
//            altRealizada = (historico.get(i).getAltRealizada());
//            dtAlteracao =(historico.get(i).getDtAlteracao());
//            user = (historico.get(i).getUser());
//            
//            System.out.println("aluno = "+ getAltRealizada());
//    }
//    } 
//    
//}
//
//public void limparLinhas(){
//        
//        setMatriculaPesq("");
//        setNomePesq("");
//        setAtestado("");
//        setBairro("");
//        setAtestado("");
//        setCep("");
//        setCidade("");
//        setComplemento("");
//        setCpf("");
//        setEmail("");
//        setEndereco("");
//        setGraduacao("");
//        setIdade(0);
//        setMatricula("");
//        setNome("");
//        setNum_residencia("");
//        setPeso(0);
//        setSexo("");
//        setTelefone("");
//        setValor_mensalidade(0);
//
//}
//
//public void consultaCep(){
//          
//          WebServiceCep webServiceCep = WebServiceCep.searchCep(getCep());
//		//A ferramenta de busca ignora qualquer caracter que não seja número.
//		
//		//caso a busca ocorra bem, imprime os resultados.
//		if (webServiceCep.wasSuccessful()) {
//		
//		setEndereco(webServiceCep.getLogradouroFull());
//                
//		setBairro(webServiceCep.getBairro());
//                
//		setCidade(webServiceCep.getCidade()+"/"+ webServiceCep.getUf());
//                
//		
//			
//		//caso haja problemas imprime as exceções.
//		} else {
//			
//                //    JOptionPane.showMessageDialog(null,"Erro número: " + webServiceCep.getResulCode());
//			//JOptionPane.showMessageDialog(null,"Erro: " + webServiceCep.getResultText());
//                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", ""+ webServiceCep.getResultText()));
//                        setCep("");
//		}
//          
//      }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    public String getUser() {
//        return user;
//    }
//
//    public void setUser(String user) {
//        this.user = user;
//    }
//
//
//
//    public String getStatusMatricula() {
//        return statusMatricula;
//    }
//
//    public void setStatusMatricula(String statusMatricula) {
//        this.statusMatricula = statusMatricula;
//    }
//
//   
//
//    public String getMatricula() {
//        return matricula;
//    }
//
//    public void setMatricula(String matricula) {
//        this.matricula = matricula;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public void setNome(String nome) {
//        this.nome = nome;
//    }
//
//    public int getIdade() {
//        return idade;
//    }
//
//    public void setIdade(int idade) {
//        this.idade = idade;
//    }
//
//    public float getPeso() {
//        return peso;
//    }
//
//    public void setPeso(float peso) {
//        this.peso = peso;
//    }
//
//    public String getCep() {
//        return cep;
//    }
//
//    public void setCep(String cep) {
//        this.cep = cep;
//    }
//
//    
//
//    public String getCpf() {
//        return cpf;
//    }
//
//    public void setCpf(String cpf) {
//        this.cpf = cpf;
//    }
//
//    public String getTelefone() {
//        return telefone;
//    }
//
//    public void setTelefone(String telefone) {
//        this.telefone = telefone;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getEndereco() {
//        return endereco;
//    }
//
//    public void setEndereco(String enderec) {
//        this.endereco = enderec;
//    }
//
//    public String getNum_residencia() {
//        return num_residencia;
//    }
//
//    public void setNum_residencia(String num_residencia) {
//        this.num_residencia = num_residencia;
//    }
//
//    public String getComplemento() {
//        return complemento;
//    }
//
//    public void setComplemento(String complemento) {
//        this.complemento = complemento;
//    }
//
//    public String getBairro() {
//        return bairro;
//    }
//
//    public void setBairro(String bairro) {
//        this.bairro = bairro;
//    }
//
//    public String getCidade() {
//        return cidade;
//    }
//
//    public void setCidade(String cidade) {
//        this.cidade = cidade;
//    }
//
//    public String getGraduacao() {
//        return graduacao;
//    }
//
//    public void setGraduacao(String graduacao) {
//        this.graduacao = graduacao;
//    }
//
//    public float getValor_mensalidade() {
//        return valor_mensalidade;
//    }
//
//    public void setValor_mensalidade(float valor_mensalidade) {
//        this.valor_mensalidade = valor_mensalidade;
//    }
//
//    public String getSexo() {
//        return sexo;
//    }
//
//    public void setSexo(String sexo) {
//        this.sexo = sexo;
//    }
//
//    public String getAtestado() {
//        return atestado;
//    }
//
//    public void setAtestado(String atestado) {
//        this.atestado = atestado;
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
//        
//        this.dtAlteracao = dtAlteracao;
//        
//    }
//
//    public static int getTeste() {
//        return teste;
//    }
//
//    public static void setTeste(int teste) {
//        AlunoManagedBean.teste = teste;
//    }
//
//    public String getMatriculaPesq() {
//        return matriculaPesq;
//    }
//
//    public void setMatriculaPesq(String matriculaPesq) {
//        this.matriculaPesq = matriculaPesq;
//    }
//
//    public String getNomePesq() {
//        return nomePesq;
//    }
//
//    public void setNomePesq(String nomePesq) {
//        this.nomePesq = nomePesq;
//    }
//
//      public boolean isSelected() {
//        return selected;
//    }
//
//    public void setSelected(boolean selected) {
//        this.selected = selected;
//    }
//  
//    
// public List<CadastroAluno_bean> getCadastros() {
//        return cadastros;
//    }
//
//    public void setCadastros(List<CadastroAluno_bean> cadastros) {
//        this.cadastros = cadastros;
//    }
//
//    public List<CadastroAluno_bean> getHistorico() {
//        return historico;
//    }
//
//    public void setHistorico(List<CadastroAluno_bean> historico) {
//        this.historico = historico;
//    }
// 
//    
//}
