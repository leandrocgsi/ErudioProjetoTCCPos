package br.com.erudio.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "pendencia")
public class Pendencia implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    @Column(name = "IdPendencia")
    private Integer idPendencia;
    @Column(name = "Observacao", length = 150)
    private String observacao;
    @Column(name = "DataPendencia")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataPendencia;
    @Column(name = "DataDeSolucao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataDeSolucao;
    
    @OneToMany(mappedBy = "pendencia", fetch = FetchType.LAZY)
    @ForeignKey(name = "PessoaPendencia")
    private List<Pessoa> pessoas;
    
    @ManyToOne(optional=true)
    @ForeignKey(name = "PendenciaTipoPendencia") 
    @JoinColumn(name="IdTipoPendencia", referencedColumnName = "IdTipoPendencia")
    private TipoPendencia tipopendencia;

    public Pendencia() {
    }

    public Date getDataPendencia() {
        return dataPendencia;
    }

    public void setDataPendencia(Date dataPendencia) {
        this.dataPendencia = dataPendencia;
    }

    public Integer getIdPendencia() {
        return idPendencia;
    }

    public void setIdPendencia(Integer idPendencia) {
        this.idPendencia = idPendencia;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getDataDeSolucao() {
        return dataDeSolucao;
    }

    public void setDataDeSolucao(Date dataDeSolucao) {
        this.dataDeSolucao = dataDeSolucao;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public TipoPendencia getTipopendencia() {
        return tipopendencia;
    }

    public void setTipopendencia(TipoPendencia tipopendencia) {
        this.tipopendencia = tipopendencia;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (this.idPendencia != null ? this.idPendencia.hashCode() : 0);
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
        final Pendencia other = (Pendencia) obj;
        if (this.idPendencia != other.idPendencia && (this.idPendencia == null || !this.idPendencia.equals(other.idPendencia))) {
            return false;
        }
        return true;
    }
        
}
