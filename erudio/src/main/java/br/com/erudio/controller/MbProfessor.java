package br.com.erudio.controller;

import br.com.erudio.conversores.ConverterSHA1;
import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.Endereco;
import br.com.erudio.model.entities.Professor;
import br.com.erudio.model.entities.Matricula;
import br.com.erudio.util.FacesContextUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "mbProfessor")
@SessionScoped
public class MbProfessor implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String confereSenha;
    
    private Professor professor = new Professor();
    private Endereco endereco = new Endereco();

    private List<Professor> professors;
    private List<Endereco> enderecos;

    
    public MbProfessor() {}
    
    private InterfaceDAO<Professor> pessoaDAO(){
        InterfaceDAO<Professor> pessoaDAO = new HibernateDAO<Professor>(Professor.class, FacesContextUtil.getRequestSession());
        return pessoaDAO;
    }

    private InterfaceDAO<Endereco> enderecoDAO(){
        InterfaceDAO<Endereco> enderecoDAO = new HibernateDAO<Endereco>(Endereco.class, FacesContextUtil.getRequestSession());
        return enderecoDAO;
    }
    
    private InterfaceDAO<Matricula> matriculaDAO(){
        InterfaceDAO<Matricula> matriculaDAO = new HibernateDAO<Matricula>(Matricula.class, FacesContextUtil.getRequestSession());
        return matriculaDAO;
    }
    
    public String limpProfessor() {
        professor = new Professor();
        endereco = new Endereco();
        return editProfessor();
    }

    public String editProfessor() {
        return "/restrict/cadastrarprofessor.faces";
    }

    public String addProfessor() {
        Date date = new Date();
        if (professor.getIdPessoa() == null || professor.getIdPessoa() == 0) {
            professor.setDataDeCadastro(date);
            insertProfessor();
        } else {
            updateProfessor();
        }
        return "/restrict/consultarprofessors.faces";
    }

    private void insertProfessor() {
        professor.setSenha(ConverterSHA1.cipher(professor.getSenha()));
        if (professor.getSenha() == null ? confereSenha == null : professor.getSenha().equals(ConverterSHA1.cipher(confereSenha))) {
            professor.setPermissao("ROLE_ALUNO");
            pessoaDAO().save(professor);
            endereco.setPessoa(professor);            
            enderecoDAO().save(endereco);                       
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "As senhas não conferem.", ""));
        }
    }

    private void updateProfessor() {
        pessoaDAO().update(professor);
        enderecoDAO().update(endereco);        
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }

    public String deleteProfessor() {
        pessoaDAO().remove(professor);
        enderecoDAO().remove(endereco);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso", ""));
        return null;
    }

    public List<Professor> getProfessors() {
        professors = pessoaDAO().getEntities();
        return professors;
    }

    public void setProfessors(List<Professor> pessoas) {
        this.professors = pessoas;
    }

    public List<Endereco> getEnderecos() {
        enderecos = enderecoDAO().getEntities();
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor pessoa) {
        this.professor = pessoa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getConfereSenha() {
        return confereSenha;
    }

    public void setConfereSenha(String confereSenha) {
        this.confereSenha = confereSenha;
    }
}