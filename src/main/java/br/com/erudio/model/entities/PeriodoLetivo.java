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
@Table(name="periodoletivo")
public class PeriodoLetivo implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "IdPeriodoLetivo", nullable = false)
    private Integer idPeriodoLetivo;
    @Column(name = "Descricao", nullable = false, length = 35)
    private String descricao;
    
    @OneToMany(mappedBy = "periodoletivo", fetch = FetchType.LAZY)
    @ForeignKey(name="MatriculaPeriodoLetivo")
    private List<Matricula> matriculas;

    public PeriodoLetivo() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdPeriodoLetivo() {
        return idPeriodoLetivo;
    }

    public void setIdPeriodoLetivo(Integer idPeriodoLetivo) {
        this.idPeriodoLetivo = idPeriodoLetivo;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }   
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PeriodoLetivo other = (PeriodoLetivo) obj;
        if (this.idPeriodoLetivo != other.idPeriodoLetivo && (this.idPeriodoLetivo == null || !this.idPeriodoLetivo.equals(other.idPeriodoLetivo))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.idPeriodoLetivo != null ? this.idPeriodoLetivo.hashCode() : 0);
        return hash;
    }
}
