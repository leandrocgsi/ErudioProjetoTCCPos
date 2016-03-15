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
@Table(name="tipoaluno")
public class TipoAluno implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "IdTipoAluno", nullable = false)
    private Integer idTipoAluno;
    @Column(name = "DescricaoTipoAluno", nullable = false, length = 35)
    private String descricaoTipoAluno;
    
    @OneToMany(mappedBy = "tipoaluno", fetch = FetchType.LAZY)
    @ForeignKey(name="AlunoTipoAluno")
    private List<Aluno> alunos;

    public TipoAluno() {
    }

    public String getDescricaoTipoAluno() {
        return descricaoTipoAluno;
    }

    public void setDescricaoTipoAluno(String descricaoTipoAluno) {
        this.descricaoTipoAluno = descricaoTipoAluno;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Integer getIdTipoAluno() {
        return idTipoAluno;
    }

    public void setIdTipoAluno(Integer idTipoAluno) {
        this.idTipoAluno = idTipoAluno;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoAluno other = (TipoAluno) obj;
        if (this.idTipoAluno != other.idTipoAluno && (this.idTipoAluno == null || !this.idTipoAluno.equals(other.idTipoAluno))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.idTipoAluno != null ? this.idTipoAluno.hashCode() : 0);
        return hash;
    }
}
