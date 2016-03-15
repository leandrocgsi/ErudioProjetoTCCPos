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
@Table(name="titulacao")
public class Titulacao implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "IdTitulacao", nullable = false)
    private Integer idTitulacao;
    @Column(name = "DescricaoTitulacao", nullable = false, length = 35)
    private String descricaoTitulacao;
    
    @OneToMany(mappedBy = "titulacao", fetch = FetchType.LAZY)
    @ForeignKey(name="ProfessorTitulacao")
    private List<Professor> professors;

    public Titulacao() {
    }

    public String getDescricaoTitulacao() {
        return descricaoTitulacao;
    }

    public void setDescricaoTitulacao(String descricaoTitulacao) {
        this.descricaoTitulacao = descricaoTitulacao;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
    }

    public Integer getIdTitulacao() {
        return idTitulacao;
    }

    public void setIdTitulacao(Integer idTitulacao) {
        this.idTitulacao = idTitulacao;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Titulacao other = (Titulacao) obj;
        if (this.idTitulacao != other.idTitulacao && (this.idTitulacao == null || !this.idTitulacao.equals(other.idTitulacao))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.idTitulacao != null ? this.idTitulacao.hashCode() : 0);
        return hash;
    }
}
