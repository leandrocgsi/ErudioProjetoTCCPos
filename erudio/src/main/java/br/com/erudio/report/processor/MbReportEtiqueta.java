package br.com.erudio.report.processor;

import br.com.erudio.report.templates.HelperResponseStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="mbReportEtiqueta")
@RequestScoped
public class MbReportEtiqueta implements Serializable {

    private static final long serialVersionUID = 1L;
    String atualDir = FacesContext.getCurrentInstance().getExternalContext().getRealPath("../../")
            + "/src/main/java/br/com/erudio/report/templates/";
    String mimeType = "application/pdf";
    String nameOfFile = "capa.pdf";
    HelperResponseStream helperResponseStream = new HelperResponseStream();
       
    public MbReportEtiqueta() {        
    } 

    public void makeReportVEtiqueta() throws IOException{   
        HelperResponseStream.readerFile("capa.pdf", atualDir, new File(atualDir+nameOfFile));
    }
    
}