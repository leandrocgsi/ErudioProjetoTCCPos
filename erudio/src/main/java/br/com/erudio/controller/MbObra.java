package br.com.erudio.controller;

import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.Exemplar;
import br.com.erudio.model.entities.Obra;
import br.com.erudio.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "mbObra")
@RequestScoped
public class MbObra implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer quantidade = 0;
    private Obra obra = new Obra();
    private Exemplar exemplar = new Exemplar();
    private List<Obra> obras;    
    private List<Exemplar> exemplars;

    public MbObra() {
    }

    private InterfaceDAO<Obra> obraDAO() {
        InterfaceDAO<Obra> obraDAO = new HibernateDAO<Obra>(Obra.class, FacesContextUtil.getRequestSession());
        return obraDAO;
    }

    private InterfaceDAO<Exemplar> exemplarDAO() {
        InterfaceDAO<Exemplar> exemplarDAO = new HibernateDAO<Exemplar>(Exemplar.class, FacesContextUtil.getRequestSession());
        return exemplarDAO;
    }

    public String limpObra() {
        obra = new Obra();
        exemplar = new Exemplar();
        return editObra();
    }

    public String editObra() {
        return "/restrict/cadastrarobra.faces";
    }

    public String addObra() {
        if (obra.getIdObra() == null || obra.getIdObra() == 0) {
            insertObra();
        } else {
            updateObra();
        }
        limpObra();
        return null;
    }

    private void insertObra() {
        obraDAO().save(obra);
        for (int i = 1; i <= quantidade; i++) {
            exemplar.setIdExemplar(i);
            exemplar.setObra(obra);
            exemplarDAO().save(exemplar);   
        }        
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
    }

    private void updateObra() {
        obraDAO().update(obra);
        exemplarDAO().update(exemplar);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }
    
    public void deleteObra(){
        obraDAO().remove(obra); 
        exemplarDAO().remove(exemplar);
    }
        
    public List<Exemplar> getExemplars() {
        obras = obraDAO().getEntities();
        return exemplars;
    }

    public void setExemplars(List<Exemplar> exemplars) {
        this.exemplars = exemplars;
    }
    
    public List<Obra> getObras() {       
        obras = obraDAO().getEntities();
        return obras;
    }

    public void setObras(List<Obra> obras) {
        this.obras = obras;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }               
}