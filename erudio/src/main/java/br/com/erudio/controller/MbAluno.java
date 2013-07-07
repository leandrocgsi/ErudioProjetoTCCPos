package br.com.erudio.controller;

import br.com.erudio.conversores.ConverterSHA1;
import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.Endereco;
import br.com.erudio.model.entities.Aluno;
import br.com.erudio.model.entities.Matricula;
import br.com.erudio.util.FacesContextUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "mbAluno")
@SessionScoped
public class MbAluno implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String confereSenha;
    
    private Aluno aluno = new Aluno();
    private Endereco endereco = new Endereco();
    private Matricula matricula = new Matricula();
    private List<Aluno> alunos;
    private List<Endereco> enderecos;
    private List<Matricula> matriculas;
    
    public MbAluno() {}
    
    private InterfaceDAO<Aluno> pessoaDAO(){
        InterfaceDAO<Aluno> pessoaDAO = new HibernateDAO<Aluno>(Aluno.class, FacesContextUtil.getRequestSession());
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
    
    public String limpAluno() {
        aluno = new Aluno();
        endereco = new Endereco();
        matricula = new Matricula();
        return editAluno();
    }

    public String editAluno() {
        return "/restrict/cadastraraluno.faces";
    }

    public String addAluno() {
        Date date = new Date();
        if (aluno.getIdPessoa() == null || aluno.getIdPessoa() == 0) {
            aluno.setDataDeCadastro(date);
            insertAluno();
        } else {
            updateAluno();
        }
        return "/restrict/consultaralunos.faces";
    }

    private void insertAluno() {
        aluno.setSenha(ConverterSHA1.cipher(aluno.getSenha()));
        if (aluno.getSenha() == null ? confereSenha == null : aluno.getSenha().equals(ConverterSHA1.cipher(confereSenha))) {
            aluno.setPermissao("ROLE_ALUNO");
            pessoaDAO().save(aluno);
            endereco.setPessoa(aluno);            
            enderecoDAO().save(endereco);            
            matricula.setAluno(aluno);
            matricula.setDataMatricula(new Date());
            matriculaDAO().save(matricula);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "As senhas não conferem.", ""));
        }
    }

    private void updateAluno() {
        pessoaDAO().update(aluno);
        enderecoDAO().update(endereco);
        matriculaDAO().update(matricula);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }

    public String deleteAluno() {
        pessoaDAO().remove(aluno);
        enderecoDAO().remove(endereco);
        matriculaDAO().remove(matricula);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso", ""));
        return null;
    }

    public List<Aluno> getAlunos() {
        alunos = pessoaDAO().getEntities();
        return alunos;
    }

    public void setAlunos(List<Aluno> pessoas) {
        this.alunos = pessoas;
    }

    public List<Endereco> getEnderecos() {
        enderecos = enderecoDAO().getEntities();
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Matricula> getMatriculas() {
        matriculas = matriculaDAO().getEntities();
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno pessoa) {
        this.aluno = pessoa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public String getConfereSenha() {
        return confereSenha;
    }

    public void setConfereSenha(String confereSenha) {
        this.confereSenha = confereSenha;
    }
}
