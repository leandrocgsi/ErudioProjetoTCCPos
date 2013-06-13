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
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "reserva")
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "idReserva")
    private Integer idReserva;
    //idPessoaOperador;
    //idObra;
    //idPessoaAluno;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataReserva;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataOperacao;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCancelamento;
    
    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @ForeignKey(name="ReservaTipoReserva")
    @JoinColumn(name = "IdTipoReserva", referencedColumnName="IdTipoReserva")
    private TipoReserva tiporeserva;
    
    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @ForeignKey(name="ReservaObra")
    @JoinColumn(name = "IdObra", referencedColumnName="IdObra")
    private Obra obra;

    public Reserva() {
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public Date getDataOperacao() {
        return dataOperacao;
    }

    public void setDataOperacao(Date dataOperacao) {
        this.dataOperacao = dataOperacao;
    }

    public Date getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(Date dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public TipoReserva getTiporeserva() {
        return tiporeserva;
    }

    public void setTiporeserva(TipoReserva tiporeserva) {
        this.tiporeserva = tiporeserva;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.idReserva != null ? this.idReserva.hashCode() : 0);
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
        final Reserva other = (Reserva) obj;
        if (this.idReserva != other.idReserva && (this.idReserva == null || !this.idReserva.equals(other.idReserva))) {
            return false;
        }
        return true;
    }
        
}
