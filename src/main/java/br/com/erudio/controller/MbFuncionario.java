package br.com.erudio.controller;

import br.com.erudio.conversores.ConverterSHA1;
import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.Endereco;
import br.com.erudio.model.entities.Funcionario;
import br.com.erudio.util.FacesContextUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "mbFuncionario")
@SessionScoped
public class MbFuncionario implements Serializable {

    private static final long serialVersionUID = 1L;
    private String confereSenha;
    private Funcionario funcionario = new Funcionario();
    private Endereco endereco = new Endereco();
    private List<Funcionario> pessoas;
    private List<Endereco> enderecos;
    
    public MbFuncionario() {}
    
    private InterfaceDAO<Funcionario> pessoaDAO(){
        InterfaceDAO<Funcionario> pessoaDAO = new HibernateDAO<Funcionario>(Funcionario.class, FacesContextUtil.getRequestSession());
        return pessoaDAO;
    }

    private InterfaceDAO<Endereco> enderecoDAO(){
        InterfaceDAO<Endereco> enderecoDAO = new HibernateDAO<Endereco>(Endereco.class, FacesContextUtil.getRequestSession());
        return enderecoDAO;
    }
    
    public String limpFuncionario() {
        funcionario = new Funcionario();
        endereco = new Endereco();
        return editFuncionario();
    }

    public String editFuncionario() {
        return "/restrict/cadastrarfuncionario.faces";
    }

    public String addFuncionario() {
        Date date = new Date();
        if (funcionario.getIdPessoa() == null || funcionario.getIdPessoa() == 0) {
            funcionario.setDataDeCadastro(date);
            insertFuncionario();
        } else {
            updateFuncionario();
        }
        return null;
    }

    private void insertFuncionario() {
        funcionario.setSenha(ConverterSHA1.cipher(funcionario.getSenha()));
        if (funcionario.getSenha() == null ? confereSenha == null : funcionario.getSenha().equals(ConverterSHA1.cipher(confereSenha))) {
            funcionario.setPermissao("ROLE_FUNCIONARIO");
            pessoaDAO().save(funcionario);
            endereco.setPessoa(funcionario);
            enderecoDAO().save(endereco);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "As senhas não conferem.", ""));
        }
    }

    private void updateFuncionario() {
        pessoaDAO().update(funcionario);
        enderecoDAO().update(endereco);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }

    public String deleteFuncionario() {
        pessoaDAO().remove(funcionario);
        enderecoDAO().remove(endereco);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso", ""));
        return null;
    }

    public List<Funcionario> getFuncionarios() {
        pessoas = pessoaDAO().getEntities();
        return pessoas;
    }

    public void setFuncionarios(List<Funcionario> pessoas) {
        this.pessoas = pessoas;
    }

    public List<Endereco> getEnderecos() {
        enderecos = enderecoDAO().getEntities();
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario pessoa) {
        this.funcionario = pessoa;
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
