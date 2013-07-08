package br.com.erudio.model.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lombada", catalog = "erudio")
public class VLombada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "IdExemplar")
    private Integer idExemplar;
    @Column(name = "NotacaoDeArea")
    private String notacaoDeArea;
    @Column(name = "NotacaoDeAutor")
    private String notacaoDeAutor;

    public VLombada() {
    }

    public Integer getIdExemplar() {
        return idExemplar;
    }

    public void setIdExemplar(Integer idExemplar) {
        this.idExemplar = idExemplar;
    }

    public String getNotacaoDeArea() {
        return notacaoDeArea;
    }

    public void setNotacaoDeArea(String notacaoDeArea) {
        this.notacaoDeArea = notacaoDeArea;
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
        hash = 31 * hash + (this.idExemplar != null ? this.idExemplar.hashCode() : 0);
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
        final VLombada other = (VLombada) obj;
        if (this.idExemplar != other.idExemplar && (this.idExemplar == null || !this.idExemplar.equals(other.idExemplar))) {
            return false;
        }
        return true;
    }
}