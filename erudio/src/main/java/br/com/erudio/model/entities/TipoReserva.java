package br.com.erudio.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="tiporeserva")
public class TipoReserva implements Serializable {
    
    private static final long serialVersionUID =  1L;  
    
    public static final int ATENDIDA = 1;
    public static final int CANCELADA = 2;
    public static final int PENDENTE = 3;
    public static final int VENCIDA = 4;
    
    @Id
    @GeneratedValue
    @Column(name="IdTipoReserva")
    private Integer idTipoReserva;
    @Column(name="Descricao", unique=true, nullable=false, length=10)
    private String descricao;

    @OneToMany(mappedBy = "tiporeserva", fetch = FetchType.LAZY)
    @ForeignKey(name = "ReservaTipoReserva")        
    private List<Reserva> reservas;

    public TipoReserva() {
    }

    public Integer getIdTipoReserva() {
        return idTipoReserva;
    }

    public void setIdTipoReserva(Integer idTipoReserva) {
        this.idTipoReserva = idTipoReserva;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.idTipoReserva != null ? this.idTipoReserva.hashCode() : 0);
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
        final TipoReserva other = (TipoReserva) obj;
        if (this.idTipoReserva != other.idTipoReserva && (this.idTipoReserva == null || !this.idTipoReserva.equals(other.idTipoReserva))) {
            return false;
        }
        return true;
    }
    
}
