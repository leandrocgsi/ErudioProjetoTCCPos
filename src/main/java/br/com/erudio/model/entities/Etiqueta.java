package br.com.erudio.model.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "etiqueta")
public class Etiqueta implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "idexemplar")
    private String idexemplar;

    public String getIdexemplar() {
        return idexemplar;
    }

    public void setIdexemplar(String idexemplar) {
        this.idexemplar = idexemplar;
    }

    

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.idexemplar != null ? this.idexemplar.hashCode() : 0);
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
        final Etiqueta other = (Etiqueta) obj;
        if (this.idexemplar != other.idexemplar && (this.idexemplar == null || !this.idexemplar.equals(other.idexemplar))) {
            return false;
        }
        return true;
    }

    
   
}