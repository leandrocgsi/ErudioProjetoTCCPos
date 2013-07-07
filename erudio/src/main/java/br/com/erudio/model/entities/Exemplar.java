package br.com.erudio.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="exemplar")
public class Exemplar implements Serializable {
    
    private static final long serialVersionUID =  1L;       
    
    @Id
    @GeneratedValue
    @Column(name="IdExemplar")
    private Integer idExemplar;
    @Column (name="Volume")
    private String volume;
    @Column (name="Tomo")
    private String tomo;
    @Column (name="Disponivel")
    private Boolean disponivel;
    @Column (name="NumeroExemplar")
    private Integer numeroExemplar;
    
    @OneToMany(mappedBy = "exemplar", fetch = FetchType.LAZY)
    @ForeignKey(name = "EmprestimoExemplar")
    private List<Emprestimo> emprestimos;
    
    @ManyToOne(optional=true)
    @ForeignKey(name = "ExemplarObra")  
    @JoinColumn(name="IdObra", referencedColumnName = "IdObra")
    private Obra obra;
    
    public Exemplar(){}

    public Integer getIdExemplar() {
        return idExemplar;
    }

    public void setIdExemplar(Integer idExemplar) {
        this.idExemplar = idExemplar;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getTomo() {
        return tomo;
    }

    public void setTomo(String tomo) {
        this.tomo = tomo;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Integer getNumeroExemplar() {
        return numeroExemplar;
    }

    public void setNumeroExemplar(Integer numeroExemplar) {
        this.numeroExemplar = numeroExemplar;
    }
           
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.idExemplar != null ? this.idExemplar.hashCode() : 0);
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
        final Exemplar other = (Exemplar) obj;
        if (this.idExemplar != other.idExemplar && (this.idExemplar == null || !this.idExemplar.equals(other.idExemplar))) {
            return false;
        }
        return true;
    }
        
}
