package br.com.erudio.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
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
    @Column (name="Pai")
    private String pai;
    @Column (name="Mae")
    private String mae;
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
    
    @ManyToOne(optional=true)
    @ForeignKey(name = "PessoaPendencia") 
    @JoinColumn(name="IdPendencia", referencedColumnName = "IdPendencia")
    private Pendencia pendencia;
    
    @ManyToOne(optional=true)
    @ForeignKey(name = "PessoaDepartamento") 
    @JoinColumn(name="IdDepartamento", referencedColumnName = "IdDepartamento")
    private Departamento departamento;
    
    @OneToMany(mappedBy = "operador", fetch = FetchType.LAZY)
    @ForeignKey(name = "EmprestimoOperador")
    private List<Emprestimo> emprestimosoperador;
    
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)    
    @ForeignKey(name = "EmprestimoUsuario")
    private List<Emprestimo> emprestimosusuario;
    
    @OneToMany(mappedBy = "operador", fetch = FetchType.LAZY)
    @ForeignKey(name = "ReservaOperador")
    private List<Reserva> reservasoperador;
    
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)    
    @ForeignKey(name = "ReservaUsuario")
    private List<Reserva> reservasusuario;
    

    public Pessoa() {
        this.sexo = new Sexo();
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
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

    public Pendencia getPendencia() {
        return pendencia;
    }

    public void setPendencia(Pendencia pendencia) {
        this.pendencia = pendencia;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Emprestimo> getEmprestimosoperador() {
        return emprestimosoperador;
    }

    public void setEmprestimosoperador(List<Emprestimo> emprestimosoperador) {
        this.emprestimosoperador = emprestimosoperador;
    }

    public List<Emprestimo> getEmprestimosusuario() {
        return emprestimosusuario;
    }

    public void setEmprestimosusuario(List<Emprestimo> emprestimosusuario) {
        this.emprestimosusuario = emprestimosusuario;
    }

    public List<Reserva> getReservasoperador() {
        return reservasoperador;
    }

    public void setReservasoperador(List<Reserva> reservasoperador) {
        this.reservasoperador = reservasoperador;
    }

    public List<Reserva> getReservasusuario() {
        return reservasusuario;
    }

    public void setReservasusuario(List<Reserva> reservasusuario) {
        this.reservasusuario = reservasusuario;            
    }
}
