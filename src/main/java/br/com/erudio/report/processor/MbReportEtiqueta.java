package br.com.erudio.report.processor;

import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.Etiqueta;
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
    
    @SuppressWarnings("rawtypes")
	HashMap hash = new HashMap();
    
    private String stringQuery = "from Etiqueta";
    
    private InterfaceDAO<Etiqueta> etiquetaDAO() {
        InterfaceDAO<Etiqueta> reportDAO = new HibernateDAO<Etiqueta>(Etiqueta.class, FacesContextUtil.getRequestSession());
        return reportDAO;
    }
    
    public MbReportEtiqueta() {        
    } 

    public void makeReportVEtiqueta(){    
        helperReport.makeReport(stringQuery, "etiquetas.jrxml", null, "barras", null, "pdf", etiquetaDAO());
    }
}