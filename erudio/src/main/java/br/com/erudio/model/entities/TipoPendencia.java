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
@Table(name="tipopendencia")
public class TipoPendencia implements Serializable {
    
    private static final long serialVersionUID =  1L;   
    
    @Id
    @GeneratedValue
    @Column(name="IdTipoPendencia")
    private Integer idTipoPendencia;
    @Column(name="Descricao", unique=true, nullable=false, length=10)
    private String descricao;

    @OneToMany(mappedBy = "tipopendencia", fetch = FetchType.LAZY)
    @ForeignKey(name = "PendenciaTipoPendencia")        
    private List<Pendencia> pendencia;

    public TipoPendencia() {
    }

    public Integer getIdTipoPendencia() {
        return idTipoPendencia;
    }

    public void setIdTipoPendencia(Integer idTipoPendencia) {
        this.idTipoPendencia = idTipoPendencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Pendencia> getPendencia() {
        return pendencia;
    }

    public void setPendencia(List<Pendencia> pendencia) {
        this.pendencia = pendencia;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.idTipoPendencia != null ? this.idTipoPendencia.hashCode() : 0);
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
        final TipoPendencia other = (TipoPendencia) obj;
        if (this.idTipoPendencia != other.idTipoPendencia && (this.idTipoPendencia == null || !this.idTipoPendencia.equals(other.idTipoPendencia))) {
            return false;
        }
        return true;
    }
    
}
