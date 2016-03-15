package br.com.erudio.model.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lombada")
public class Lombada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idexemplar")
    private String idexemplar;
    @Column(name = "notacaodearea")
    private String notacaodearea;
    @Column(name = "notacaodeautor")
    private String notacaoDeAutor;

    public Lombada() {
    }

    public String getIdexemplar() {
        return idexemplar;
    }

    public void setIdexemplar(String idexemplar) {
        this.idexemplar = idexemplar;
    }

    public String getNotacaodearea() {
        return notacaodearea;
    }

    public void setNotacaodearea(String notacaodearea) {
        this.notacaodearea = notacaodearea;
    }

    public String getNotacaoDeAutor() {
        return notacaoDeAutor;
    }

    public void setNotacaoDeAutor(String notacaoDeAutor) {
        this.notacaoDeAutor = notacaoDeAutor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.idexemplar != null ? this.idexemplar.hashCode() : 0);
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
        final Lombada other = (Lombada) obj;
        if (this.idexemplar != other.idexemplar && (this.idexemplar == null || !this.idexemplar.equals(other.idexemplar))) {
            return false;
        }
        return true;
    }

    
}