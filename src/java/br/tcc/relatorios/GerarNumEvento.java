/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tcc.relatorios;

import br.tcc.relatorios.bean.NumerosDoEvento_relat_bean;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author joãomarcos
 */
public class GerarNumEvento {
    
    private HttpServletResponse response;
    private FacesContext context;
    private ByteArrayOutputStream baos;
    private InputStream stream;
    public GerarNumEvento(){
        this.context = FacesContext.getCurrentInstance();
        this.response = (HttpServletResponse)context.getExternalContext().getResponse();
    }
    
    public void GerarRelatorio(List<NumerosDoEvento_relat_bean> lista) throws IOException, JRException{
        stream = this.getClass().getResourceAsStream("/br/tcc/relatorios/NumerosDoEvento.jasper");
        Map<String,Object> params = new HashMap<>();
        baos = new ByteArrayOutputStream();
        
       JasperReport report = (JasperReport) JRLoader.loadObject(stream);
        JasperPrint print = JasperFillManager.fillReport(report, params, new JRBeanCollectionDataSource(lista));
        JasperExportManager.exportReportToPdfStream(print,baos);
        
        response.reset();
        response.setContentType("Application/pdf");
        response.setContentLength(baos.size());
        response.setHeader("Content-disposition", "inline; filename = numerosDoEvento.pdf");
        response.getOutputStream().write(baos.toByteArray());
        response.getOutputStream().flush();
        response.getOutputStream().close();
        
        context.responseComplete();
        
        
//                for(Relatorio_bean i: lista){
//            System.out.println(i.getCodigo()+"\n"+i.getNome()+"\n"+i.getIdade()+"\n\n");
//        }
    }
}
