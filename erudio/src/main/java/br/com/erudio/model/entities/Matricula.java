package br.com.erudio.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="matricula")
public class Matricula implements Serializable {
    
    private static final long serialVersionUID =  1L;   
    
    @Id
    @GeneratedValue
    @Column(name="IdMatricula")
    private Integer idMatricula;
   
    @Column(name="DataMatricula")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataMatricula;        
            
    @OneToOne(optional=true, fetch= FetchType.LAZY)
    @ForeignKey(name="MatriculaCurso")
    @JoinColumn(name = "IdCurso", referencedColumnName = "IdCurso")
    private Curso curso;

    @OneToOne(optional=true, fetch= FetchType.LAZY)
    @JoinColumn(name = "IdPessoa", referencedColumnName = "IdPessoa")
    @ForeignKey(name="MatriculaAluno")
    private Aluno aluno;
            
    @ManyToOne
    @ForeignKey(name="MatriculaPeriodoLetivo")
    @JoinColumn(name="IdPeriodoLetivo", referencedColumnName = "IdPeriodoLetivo")
    private PeriodoLetivo periodoletivo;
    
    public Matricula() {
    }

    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Date getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(Date dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public PeriodoLetivo getPeriodoletivo() {
        return periodoletivo;
    }

    public void setPeriodoletivo(PeriodoLetivo periodoletivo) {
        this.periodoletivo = periodoletivo;
    }
//
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }        

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.idMatricula != null ? this.idMatricula.hashCode() : 0);
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
        final Matricula other = (Matricula) obj;
        if (this.idMatricula != other.idMatricula && (this.idMatricula == null || !this.idMatricula.equals(other.idMatricula))) {
            return false;
        }
        return true;
    }
        
}
