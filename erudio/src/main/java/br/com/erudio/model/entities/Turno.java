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
@Table(name="turno")
public class Turno implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "IdTurno", nullable = false)
    private Integer idTurno;
    @Column(name = "DescricaoTurno", nullable = false, length = 35)
    private String descricaoTurno;
    
    @OneToMany(mappedBy = "turno", fetch = FetchType.LAZY)
    @ForeignKey(name="AlunoTurno")
    private List<Aluno> alunos;

    public Turno() {
    }

    public String getDescricaoTurno() {
        return descricaoTurno;
    }

    public void setDescricaoTurno(String descricaoTurno) {
        this.descricaoTurno = descricaoTurno;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Integer getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Integer idTurno) {
        this.idTurno = idTurno;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Turno other = (Turno) obj;
        if (this.idTurno != other.idTurno && (this.idTurno == null || !this.idTurno.equals(other.idTurno))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.idTurno != null ? this.idTurno.hashCode() : 0);
        return hash;
    }
}
