package br.com.erudio.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="professorvinculo")
public class ProfessorVinculo implements Serializable {
    
    private static final long serialVersionUID =  1L;       
    
    @Id
    @GeneratedValue
    @Column(name="IdProfessorVinculo")
    private Integer idProfessorVinculo;
    @Column(name = "DataInicio")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataInicio;
    @Column(name = "DataFim")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataFim;

    @ManyToOne(optional=true)
    @ForeignKey(name = "ProfessorVinculoCurso")
    @JoinColumn(name="IdCurso", referencedColumnName = "IdCurso")
    private Curso curso;
    
    @ManyToOne(optional=true)
    @ForeignKey(name = "ProfessorVinculoProfessor")  
    @JoinColumn(name="IdPessoa", referencedColumnName = "IdPessoa")
    private Professor professor;

    public ProfessorVinculo() {}

    public Integer getIdProfessorVinculo() {
        return idProfessorVinculo;
    }

    public void setIdProfessorVinculo(Integer idProfessorVinculo) {
        this.idProfessorVinculo = idProfessorVinculo;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (this.idProfessorVinculo != null ? this.idProfessorVinculo.hashCode() : 0);
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
        final ProfessorVinculo other = (ProfessorVinculo) obj;
        if (this.idProfessorVinculo != other.idProfessorVinculo && (this.idProfessorVinculo == null || !this.idProfessorVinculo.equals(other.idProfessorVinculo))) {
            return false;
        }
        return true;
    }   
}
