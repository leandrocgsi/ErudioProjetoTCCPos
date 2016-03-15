package br.com.erudio.report.processor;

import br.com.erudio.model.dao.InterfaceDAO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@SuppressWarnings("rawtypes")
public class HelperReport implements Serializable {

    private static final long serialVersionUID = 1L;
    String atualDir = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "/WEB-INF/classes/br/com/erudio/report/templates/";

    public HelperReport() {}

    public void makeReport(String stringQuery, String nomeJRXML, String nomeLogo, String nomeDeDestino, HashMap hash, String formato, InterfaceDAO reportDAO) {

		List listAll = reportDAO.getListByHQLQuery(stringQuery);
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            ServletOutputStream responseStream = response.getOutputStream();
            System.out.println("Caminho do .jrxml.:" + atualDir);
            String caminhojrxml = atualDir + nomeJRXML;                  
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + nomeDeDestino + "." + formato + "\"");
            JasperReport jasper = JasperCompileManager.compileReport(caminhojrxml);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listAll);
            JasperPrint print = JasperFillManager.fillReport(jasper, hash, dataSource);
            JasperExportManager.exportReportToPdfStream(print, responseStream);
            responseStream.flush();
            responseStream.close();
            context.renderResponse();
            context.responseComplete();
        } catch (Exception e) {
        	e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um erro ao gerar o relatório :-(", ""));
        }
    }
}