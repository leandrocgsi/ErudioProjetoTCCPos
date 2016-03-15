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
@Table(name="departamento")
public class Departamento implements Serializable {
    
    private static final long serialVersionUID =  1L;   
    
    @Id
    @GeneratedValue
    @Column(name="IdDepartamento")
    private Integer idDepartamento;
    @Column(name="Descricao", unique=true, nullable=false, length=10)
    private String descricao;

    @OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY)
    @ForeignKey(name = "PessoaDepartamento")        
    private List<Pessoa> pessoas;

    public Departamento() {
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.idDepartamento != null ? this.idDepartamento.hashCode() : 0);
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
        final Departamento other = (Departamento) obj;
        if (this.idDepartamento != other.idDepartamento && (this.idDepartamento == null || !this.idDepartamento.equals(other.idDepartamento))) {
            return false;
        }
        return true;
    }

}
