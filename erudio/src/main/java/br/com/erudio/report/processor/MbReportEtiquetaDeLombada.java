package br.com.erudio.report.processor;

import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.Lombada;
import br.com.erudio.report.templates.HelperResponseStream;
import br.com.erudio.util.FacesContextUtil;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="mbReportEtiquetaDeLombada")
@RequestScoped
public class MbReportEtiquetaDeLombada implements Serializable {
    
    private static final long serialVersionUID = 1L;

    String atualDir = FacesContext.getCurrentInstance().getExternalContext().getRealPath("../../")
            + "/src/main/java/br/com/erudio/report/templates/";
    String mimeType = "application/pdf";
    String nameOfFile = "lombada.pdf";
    HelperResponseStream helperResponseStream = new HelperResponseStream();
       
    public MbReportEtiquetaDeLombada() {        
    } 

    public void makeReportVLombada() throws IOException{   
        HelperResponseStream.readerFile("lombada.pdf", atualDir, new File(atualDir+nameOfFile));
    }
}