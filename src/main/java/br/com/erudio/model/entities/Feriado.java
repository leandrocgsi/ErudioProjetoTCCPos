package br.com.erudio.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="feriado")
public class Feriado implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    @Column(name = "IdFeriado", nullable = false)
    private Integer idFeriado;
    @Column(name = "NomeFeriado", nullable = false, length = 40)
    private String nomeFeriado;
    
    @Column (name="DataFeriado", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataFeriado;

    public Feriado() {
    }

    public Integer getIdFeriado() {
        return idFeriado;
    }

    public void setIdFeriado(Integer idFeriado) {
        this.idFeriado = idFeriado;
    }

    public String getNomeFeriado() {
        return nomeFeriado;
    }

    public void setNomeFeriado(String nomeFeriado) {
        this.nomeFeriado = nomeFeriado;
    }

    public Date getDataFeriado() {
        return dataFeriado;
    }

    public void setDataFeriado(Date dataFeriado) {
        this.dataFeriado = dataFeriado;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Feriado other = (Feriado) obj;
        if (this.idFeriado != other.idFeriado && (this.idFeriado == null || !this.idFeriado.equals(other.idFeriado))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.idFeriado != null ? this.idFeriado.hashCode() : 0);
        return hash;
    }
}
