package br.com.erudio.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="modulo")
public class Modulo implements Serializable {
    
    private static final long serialVersionUID =  1L;   
    
    @Id
    @GeneratedValue
    @Column(name="IdModulo")
    private Integer idModulo;
    @Column(name="Descricao")
    private String descricao;
                
//    @ManyToOne(optional=true)
//    @ForeignKey(name = "CursoModulo") 
//    @JoinColumn(name="IdCurso", referencedColumnName = "IdCurso")
//    private Curso curso;
    
    public Modulo() {}

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.idModulo != null ? this.idModulo.hashCode() : 0);
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
        final Modulo other = (Modulo) obj;
        if (this.idModulo != other.idModulo && (this.idModulo == null || !this.idModulo.equals(other.idModulo))) {
            return false;
        }
        return true;
    }
        
}
