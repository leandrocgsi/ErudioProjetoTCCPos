package br.com.erudio.controller;

import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.Curso;
import br.com.erudio.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "mbCurso")
@RequestScoped
public class MbCurso implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Curso curso = new Curso();
    private List<Curso> cursos;

    public MbCurso() {
    }

    private InterfaceDAO<Curso> cursoDAO() {
        InterfaceDAO<Curso> cursoDAO = new HibernateDAO<Curso>(Curso.class, FacesContextUtil.getRequestSession());
        return cursoDAO;
    }

    public String limpCurso() {
        curso = new Curso();
        return editCurso();
    }

    public String editCurso() {
        return "/restrict/cadastrarcurso.faces";
    }

    public String addCurso() {
        if (curso.getIdCurso() == null || curso.getIdCurso() == 0) {
            insertCurso();
        } else {
            updateCurso();
        }
        limpCurso();
        return null;
    }

    private void insertCurso() {
        cursoDAO().save(curso);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
    }

    private void updateCurso() {
        cursoDAO().update(curso);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }
    
    public void deleteCurso(){
        cursoDAO().remove(curso);        
    }
    
    public List<Curso> getCursos() {       
        cursos = cursoDAO().getEntities();
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
        
}
