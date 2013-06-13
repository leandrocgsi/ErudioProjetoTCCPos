package br.com.erudio.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="curso")
public class Curso implements Serializable{
    
    private static final long serialVersionUID =  1L; 
    
    @Id
    @GeneratedValue
    @Column(name="IdCurso", nullable=false)
    private Integer idCurso;
    @Column(name="Nome", length=80, nullable=false)
    private String nome;

    @OneToOne(optional=true, fetch= FetchType.LAZY)
    @ForeignKey(name="MatriculaCurso")
    @JoinColumn(name = "IdMatricula", referencedColumnName = "IdMatricula")
    private Matricula matricula;
    
    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    @ForeignKey(name = "CursoModulo") 
    private List<Modulo> modulos;
        
    public Curso() {
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public List<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }       

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.idCurso != null ? this.idCurso.hashCode() : 0);
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
        final Curso other = (Curso) obj;
        if (this.idCurso != other.idCurso && (this.idCurso == null || !this.idCurso.equals(other.idCurso))) {
            return false;
        }
        return true;
    }
    
}
