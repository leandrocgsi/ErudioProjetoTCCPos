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
}
