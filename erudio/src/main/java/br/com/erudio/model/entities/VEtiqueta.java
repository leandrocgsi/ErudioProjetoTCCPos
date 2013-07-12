package br.com.erudio.model.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "etiqueta")
public class VEtiqueta implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "obraTitulo")
    private String obraTitulo;
    @Column(name = "exemplarIdExemplar")
    private Integer exemplaridexemplar;

    public String getobraTitulo() {
        return obraTitulo;
    }

    public Integer getExemplaridexemplar() {
        return exemplaridexemplar;
    }

    public void setExemplaridexemplar(Integer exemplaridexemplar) {
        this.exemplaridexemplar = exemplaridexemplar;
    }

    public String getObratitulo() {
        return obraTitulo;
    }

    public void setObratitulo(String obratitulo) {
        this.obraTitulo = obratitulo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VEtiqueta other = (VEtiqueta) obj;
        if ((this.obraTitulo == null) ? (other.obraTitulo != null) : !this.obraTitulo.equals(other.obraTitulo)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.obraTitulo != null ? this.obraTitulo.hashCode() : 0);
        return hash;
    }
}