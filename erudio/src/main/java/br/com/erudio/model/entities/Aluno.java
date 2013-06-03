package br.com.erudio.model.entities;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "A")
public class Aluno extends Pessoa {

    private static final long serialVersionUID = 1L;
    
    @Lob
    @Column(name = "Foto")
    private byte[] foto;
    @Column(name = "Responsavel", length = 130)
    private String responsavel;
    @Column(name = "NumeroMatricula")
    private String numeroMatricula;

    public Aluno() {
      //  this.setCartao(new Cartao());
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Aluno other = (Aluno) obj;
        return true;
    }  
}