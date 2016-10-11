package br.tcc.controller;

import br.tcc.bean.Login_bean;
import br.tcc.bean.Relatorios_bean;
import br.tcc.dao.Login_dao;
import br.tcc.dao.Relatorios_dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author joãomarcos
 */
@ManagedBean(name="InicioManagedBean", eager = true)
@SessionScoped
public class InicioManagedBean {
    
    private PieChartModel pieModel2;
    private int codEve;
    List<Login_bean> usuarios = new ArrayList<>();
    List<Relatorios_bean> chavesAberto = new ArrayList<>();
    List<Relatorios_bean> chaves = new ArrayList<>();
    
    
    public InicioManagedBean()throws SQLException{
         usuarios = new Login_dao().acessosAtivos();
         atualziaDados();
    }

    /////////////////////////////////////////////////////////////// Métodos graficos //////////////////////////////////////////////////
    
    
    
    
    /////////////////////////////////////////////////////////////// Métodos adicionais //////////////////////////////////////////////////
    public void atualziaDados()throws SQLException{
    
        usuarios.clear();
        usuarios = new Login_dao().acessosAtivos();
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Login_bean> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Login_bean> usuarios) {
        this.usuarios = usuarios;
    }

    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    public void setPieModel2(PieChartModel pieModel2) {
        this.pieModel2 = pieModel2;
    }

    public List<Relatorios_bean> getChaves() {
        return chaves;
    }

    public void setChaves(List<Relatorios_bean> chaves) {
        this.chaves = chaves;
    }

    public List<Relatorios_bean> getChavesAberto() {
        return chavesAberto;
    }

    public void setChavesAberto(List<Relatorios_bean> chavesAberto) {
        this.chavesAberto = chavesAberto;
    }
    
    
}
