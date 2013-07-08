package br.com.erudio.report.processor;

import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.VLombada;
import br.com.erudio.util.FacesContextUtil;
import java.io.Serializable;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="mbReportEtiquetaDeLombada")
@RequestScoped
public class MbReportEtiquetaDeLombada implements Serializable {
    
    private static final long serialVersionUID = 1L;
        
    HelperReport helperReport = new HelperReport();        
    HashMap hash = new HashMap();
    
    private String stringQuery = "from VLombada";
    
    private InterfaceDAO<VLombada> etiquetaDAO() {
        InterfaceDAO<VLombada> reportDAO = new HibernateDAO<VLombada>(VLombada.class, FacesContextUtil.getRequestSession());
        return reportDAO;
    }
    
    public MbReportEtiquetaDeLombada() {        
    } 

    public void makeReportVLombada(){    
        helperReport.makeReport(stringQuery, "lombada.jrxml", null, "etiquetas_de_lombada", hash, "pdf", etiquetaDAO());
    }
}