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
@Table(name="tipoobra")
public class TipoObra implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "IdTipoObra", nullable = false)
    private Integer idTipoObra;
    @Column(name = "DescricaoTipoObra", nullable = false, length = 35)
    private String descricaoTipoObra;
    
    @OneToMany(mappedBy = "tipoobra", fetch = FetchType.LAZY)
    @ForeignKey(name="ObraTipoObra")
    private List<Obra> obras;

    public TipoObra() {
    }

    public String getDescricaoTipoObra() {
        return descricaoTipoObra;
    }

    public void setDescricaoTipoObra(String descricaoTipoObra) {
        this.descricaoTipoObra = descricaoTipoObra;
    }

    public List<Obra> getObras() {
        return obras;
    }

    public void setObras(List<Obra> obras) {
        this.obras = obras;
    }

    public Integer getIdTipoObra() {
        return idTipoObra;
    }

    public void setIdTipoObra(Integer idTipoObra) {
        this.idTipoObra = idTipoObra;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoObra other = (TipoObra) obj;
        if (this.idTipoObra != other.idTipoObra && (this.idTipoObra == null || !this.idTipoObra.equals(other.idTipoObra))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.idTipoObra != null ? this.idTipoObra.hashCode() : 0);
        return hash;
    }
}
