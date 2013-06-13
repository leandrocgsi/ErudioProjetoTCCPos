package br.com.erudio.model.entities;

import javax.persistence.*;
import org.hibernate.annotations.ForeignKey;

@Entity
@DiscriminatorValue(value = "P")
public class Professor extends Pessoa {

    private static final long serialVersionUID = 1L;
    
    @ManyToOne(optional=false)
    @ForeignKey(name = "ProfessorTitulacao") 
    @JoinColumn(name="IdTitulacao", referencedColumnName = "IdTitulacao")
    private Titulacao titulacao;

    public Professor() {
      //  this.setCartao(new Cartao());
    }

    public Titulacao getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(Titulacao titulacao) {
        this.titulacao = titulacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + (this.titulacao != null ? this.titulacao.hashCode() : 0);
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
        final Professor other = (Professor) obj;
        if (this.titulacao != other.titulacao && (this.titulacao == null || !this.titulacao.equals(other.titulacao))) {
            return false;
        }
        return true;
    }
        
}