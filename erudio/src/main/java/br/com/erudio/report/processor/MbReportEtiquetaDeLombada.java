package br.com.erudio.report.processor;

import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.Lombada;
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
    
    private String stringQuery = "from Lombada";
    
    private InterfaceDAO<Lombada> etiquetaDAO() {
        InterfaceDAO<Lombada> reportDAO = new HibernateDAO<Lombada>(Lombada.class, FacesContextUtil.getRequestSession());
        return reportDAO;
    }
    
    public MbReportEtiquetaDeLombada() {        
    } 

    public void makeReportVLombada(){    
        helperReport.makeReport(stringQuery, "lombada.jrxml", null, "lombada", null, "pdf", etiquetaDAO());
    }
}