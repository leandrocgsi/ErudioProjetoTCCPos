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

public class HelperReport implements Serializable {

    private static final long serialVersionUID = 1L;
    String atualDir = FacesContext.getCurrentInstance().getExternalContext().getRealPath("../../")
            + "/src/main/java/br/com/erudio/report/templates/";

    public HelperReport() {}

    /**
     * Método responsável por gerar o relatório
     *
     * @param stringQuery Esse parâmetro deve receber uma string com a query HQL
     * do Hibernate.
     * @param nomeJRXML Esse parâmetro deve receber o nome do arquivo jrxml do
     * iReport.
     * @param nomeLogo Esse parâmetro deve receber o nome do arquivo de logotipo
     * que aparecerá no cabeçaho do relatório.
     * @param nomeDeDestino Esse parâmetro deve receber o nome que o arquivo PDF
     * gerado terá.
     * @param hash Esse parâmetro deve receber um HashMap com os parâmetros
     * utilizados pelo JasperPrint.
     * @param formato Esse parâmetro deve receber uma String com o formato de
     * destino do relatório.
     * @param reportDAO Esse parâmetro deve receber o DAO referente a entidade
     * sobre a qual será feita a consulta.
     */
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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um erro ao gerar o relatório :-(", ""));
        }
    }
}