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
@Table(name="edicao")
public class Edicao implements Serializable {
    
    private static final long serialVersionUID =  1L;   
    
    @Id
    @GeneratedValue
    @Column(name="IdEdicao")
    private Integer idEdicao;
    @Column(name="Descricao", unique=true, nullable=false, length=9)
    private String descricao;

    @OneToMany(mappedBy = "edicao", fetch = FetchType.LAZY)
    @ForeignKey(name = "EdicaoObra")        
    private List<Obra> obras;
    
    public Edicao() {
    }

    public Integer getIdEdicao() {
        return idEdicao;
    }

    public void setIdEdicao(Integer idEdicao) {
        this.idEdicao = idEdicao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Obra> getObras() {
        return obras;
    }

    public void setObras(List<Obra> obras) {
        this.obras = obras;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + (this.idEdicao != null ? this.idEdicao.hashCode() : 0);
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
        final Edicao other = (Edicao) obj;
        if (this.idEdicao != other.idEdicao && (this.idEdicao == null || !this.idEdicao.equals(other.idEdicao))) {
            return false;
        }
        return true;
    }
    
}