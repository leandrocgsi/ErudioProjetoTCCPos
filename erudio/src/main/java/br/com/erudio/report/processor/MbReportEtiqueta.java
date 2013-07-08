package br.com.erudio.report.processor;

import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.VEtiqueta;
import br.com.erudio.util.FacesContextUtil;
import java.io.Serializable;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="mbReportEtiqueta")
@RequestScoped
public class MbReportEtiqueta implements Serializable {
    
    private static final long serialVersionUID = 1L;
        
    HelperReport helperReport = new HelperReport();        
    HashMap hash = new HashMap();
    
    private String stringQuery = "from VEtiqueta";
    
    private InterfaceDAO<VEtiqueta> etiquetaDAO() {
        InterfaceDAO<VEtiqueta> reportDAO = new HibernateDAO<VEtiqueta>(VEtiqueta.class, FacesContextUtil.getRequestSession());
        return reportDAO;
    }
    
    public MbReportEtiqueta() {        
    } 

    public void makeReportVEtiqueta(){    
        helperReport.makeReport(stringQuery, "etiquetas.jrxml", null, "teste", hash, "pdf", etiquetaDAO());
    }
}