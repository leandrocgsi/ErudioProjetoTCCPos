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
@Table(name="tipoEmprestimo")
public class TipoEmprestimo implements Serializable {
    
    private static final long serialVersionUID =  1L;   
    
    @Id
    @GeneratedValue
    @Column(name="IdTipoEmprestimo")
    private Integer idTipoEmprestimo;
    @Column(name="Descricao")
    private String descricao;
    @Column(name="NumeroDeDias")
    private Integer numeroDeDias;

    @OneToMany(mappedBy = "tipoEmprestimo", fetch = FetchType.LAZY)
    @ForeignKey(name = "EmprestimoTipoEmprestimo")        
    private List<Emprestimo> emprestimos;

    public TipoEmprestimo() {
    }

    public Integer getIdTipoEmprestimo() {
        return idTipoEmprestimo;
    }

    public void setIdTipoEmprestimo(Integer idTipoEmprestimo) {
        this.idTipoEmprestimo = idTipoEmprestimo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public Integer getNumeroDeDias() {
        return numeroDeDias;
    }

    public void setNumeroDeDias(Integer numeroDeDias) {
        this.numeroDeDias = numeroDeDias;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.idTipoEmprestimo != null ? this.idTipoEmprestimo.hashCode() : 0);
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
        final TipoEmprestimo other = (TipoEmprestimo) obj;
        if (this.idTipoEmprestimo != other.idTipoEmprestimo && (this.idTipoEmprestimo == null || !this.idTipoEmprestimo.equals(other.idTipoEmprestimo))) {
            return false;
        }
        return true;
    }
    
}
