package br.com.erudio.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="multa")
public class Multa implements Serializable {
    
    private static final long serialVersionUID =  1L;   
    
    @Id
    @GeneratedValue
    @Column(name="IdMulta")
    private Integer idMulta;
    @Column(name="Valor")
    private Double valor;
    @Column(name="NovoValor")
    private Double novoValor;
    
    @Column(name="DataAcerto")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataAcerto;
        
    @OneToOne(optional=true, fetch= FetchType.LAZY)
    @ForeignKey(name="EmprestimoMulta")
    @JoinColumn(name = "IdEmprestimo", referencedColumnName = "IdEmprestimo")
    private Emprestimo emprestimo;
    
    public Multa() {
    }

    public Integer getIdMulta() {
        return idMulta;
    }

    public void setIdMulta(Integer idMulta) {
        this.idMulta = idMulta;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getNovoValor() {
        return novoValor;
    }

    public void setNovoValor(Double novoValor) {
        this.novoValor = novoValor;
    }

    public Date getDataAcerto() {
        return dataAcerto;
    }

    public void setDataAcerto(Date dataAcerto) {
        this.dataAcerto = dataAcerto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.idMulta != null ? this.idMulta.hashCode() : 0);
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
        final Multa other = (Multa) obj;
        if (this.idMulta != other.idMulta && (this.idMulta == null || !this.idMulta.equals(other.idMulta))) {
            return false;
        }
        return true;
    }
        
}
