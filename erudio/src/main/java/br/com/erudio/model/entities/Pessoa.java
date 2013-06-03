package br.com.erudio.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.ForeignKey;


@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TipoPessoa", discriminatorType = DiscriminatorType.STRING)
public class Pessoa implements Serializable {
    
    private static final long serialVersionUID =  1L;
    
    @Id
    @GeneratedValue
    @Column(name="IdPessoa", nullable=false)
    private Integer idPessoa;
    @Column (name="NomeRazaoSocial", nullable = false, length = 80 )
    private String nomeRazaoSocial;
    @Column (name="Email", nullable = false, length = 80 )
    private String email;
    @Column (name="Telefone", nullable = false, length = 15 )
    private String telefone;
    @Column(name = "CPFCNPJ", length = 14)
    private String cpfcnpj;
    @Column(name = "RG", length = 16)
    private String rg;
    @Column (name="DataDeNascimentoFundacao", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataDeNascimentoFundacao;
    @Column (name="DataDeCadastro", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataDeCadastro;
    
    @Column(name = "Login", unique=true, length = 25)
    private String login;
    @Column(name = "Senha", length = 40)
    private String senha;
    @Column(name = "Permissao", length = 36)
    private String permissao;
    
    @OneToOne(mappedBy = "pessoa", fetch = FetchType.LAZY)
    @ForeignKey(name="EnderecoPessoa")
    private Endereco endereco;
    
    @ManyToOne(optional=false)
    @ForeignKey(name = "PessoaSexo") 
    @JoinColumn(name="IdSexo", referencedColumnName = "IdSexo")
    private Sexo sexo;

    public Pessoa() {
        this.sexo = new Sexo();
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNomeRazaoSocial() {
        return nomeRazaoSocial;
    }

    public void setNomeRazaoSocial(String nomeRazaoSocial) {
        this.nomeRazaoSocial = nomeRazaoSocial;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpfcnpj() {
        return cpfcnpj;
    }

    public void setCpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getDataDeNascimentoFundacao() {
        return dataDeNascimentoFundacao;
    }

    public void setDataDeNascimentoFundacao(Date dataDeNascimentoFundacao) {
        this.dataDeNascimentoFundacao = dataDeNascimentoFundacao;
    }

    public Date getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(Date dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
             
}
