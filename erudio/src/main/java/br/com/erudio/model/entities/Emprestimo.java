package br.com.erudio.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "emprestimo")
public class Emprestimo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IdEmprestimo")
    private Integer idEmprestimo;
    //IdPessoaOperador
    //IdPessoaAluno
    @Column(name = "DataEmprestimo")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataEmprestimo;    
    @Column(name = "DataDevolucao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataDevolucao;
    @Column(name = "DataRealDevolucao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataRealDevolucao;
   
    @OneToOne(mappedBy = "emprestimo", fetch = FetchType.LAZY)
    @ForeignKey(name = "EmprestimoMulta")
    private Multa multa;
    
    @ManyToOne(optional = false)
    @ForeignKey(name = "EmprestimoTipoEmprestimo")
    @JoinColumn(name = "IdTipoEmprestimo", referencedColumnName = "IdTipoEmprestimo")
    private TipoEmprestimo tipoEmprestimo;
    
    @ManyToOne(optional = false)
    @ForeignKey(name = "EmprestimoExemplar")
    @JoinColumn(name = "IdExemplar", referencedColumnName = "IdExemplar")
    private Exemplar exemplar;
   
    @ManyToOne(optional = true)
    @ForeignKey(name = "EmprestimoReserva")
    @JoinColumn(name = "IdReserva", referencedColumnName = "IdReserva")
    private Reserva reserva;
    
    @ManyToOne(optional=true)
    @ForeignKey(name="operador")
    @JoinColumn(name="IdPessoaOperador", referencedColumnName="IdPessoa")
    private Pessoa operador;

    @ManyToOne(optional=true)
    @ForeignKey(name="usuario")
    @JoinColumn(name="IdPessoaUsuario", referencedColumnName="IdPessoa")
    private Pessoa usuario;

    public Emprestimo() {
        this.operador = new Pessoa();
        this.usuario = new Pessoa();
    }

    public Integer getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(Integer idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Date getDataRealDevolucao() {
        return dataRealDevolucao;
    }

    public void setDataRealDevolucao(Date dataRealDevolucao) {
        this.dataRealDevolucao = dataRealDevolucao;
    }

    public Multa getMulta() {
        return multa;
    }

    public void setMulta(Multa multa) {
        this.multa = multa;
    }

    public TipoEmprestimo getTipoEmprestimo() {
        return tipoEmprestimo;
    }

    public void setTipoEmprestimo(TipoEmprestimo tipoEmprestimo) {
        this.tipoEmprestimo = tipoEmprestimo;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Pessoa getOperador() {
        return operador;
    }

    public void setOperador(Pessoa operador) {
        this.operador = operador;
    }

    public Pessoa getUsuario() {
        return usuario;
    }

    public void setUsuario(Pessoa usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.idEmprestimo != null ? this.idEmprestimo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Emprestimo other = (Emprestimo) obj;
        if (this.idEmprestimo != other.idEmprestimo && (this.idEmprestimo == null || !this.idEmprestimo.equals(other.idEmprestimo))) {
            return false;
        }
        return true;
    }

}
