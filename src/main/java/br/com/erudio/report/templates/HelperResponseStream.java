package br.com.erudio.report.templates;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class HelperResponseStream {

    public static void readerFile(String nameOfFile, String contentType, File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytesOfFile = new byte[(int) file.length()];
        fileInputStream.read(bytesOfFile);
        fileInputStream.close();
        setResponseStream(contentType, nameOfFile, bytesOfFile);
    }

    private static void setResponseStream(String contentType, String nameOfFile, byte[] bytesOfFile) throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        ServletOutputStream responseStream = response.getOutputStream();
        response.setContentType("\"" + contentType + "\"");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + nameOfFile + "\"");
        responseStream.write(bytesOfFile);
        responseStream.flush();
        responseStream.close();
        context.renderResponse();   
    }    
}