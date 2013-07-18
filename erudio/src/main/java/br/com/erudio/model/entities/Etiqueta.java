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
    @Column(name = "Titulo")
    private String titulo;
    @Column(name = "IdExemplar")
    private Integer idExemplar;

    public Integer getIdExemplar() {
        return idExemplar;
    }

    public void setIdExemplar(Integer idExemplar) {
        this.idExemplar = idExemplar;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.idExemplar != null ? this.idExemplar.hashCode() : 0);
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
        if (this.idExemplar != other.idExemplar && (this.idExemplar == null || !this.idExemplar.equals(other.idExemplar))) {
            return false;
        }
        return true;
    }
   
}