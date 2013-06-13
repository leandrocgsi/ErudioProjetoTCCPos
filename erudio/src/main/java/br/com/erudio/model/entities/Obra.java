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
@Table(name="obra")
public class Obra implements Serializable {
    
    private static final long serialVersionUID =  1L;       
    
    @Id
    @GeneratedValue
    @Column(name="IdObra")
    private Integer idObra;
    @Column(name="Assunto")
    private String assunto;
    @Column(name="Autor")
    private String autor;
    @Column(name="Titulo")
    private String titulo;
    @Column(name="Subtitulo")
    private String subtitulo;
    @Column(name="Editora")
    private String editora;
    @Column(name="AnoDePublicacao")
    private String anoDePublicacao;
    @Column(name="NotacaoDeAutor")
    private String notacaoDeAutor;
    @Column(name="NotacaoDeArea")
    private String notacaoDeArea;
    @Column(name="ValorDeReposicao")
    private Double valorDeReposicao;
        
    @OneToMany(mappedBy = "obra", fetch = FetchType.LAZY)
    @ForeignKey(name="ReservaObra")       
    private List<Reserva> reservas;
        
    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @ForeignKey(name = "EdicaoObra")
    @JoinColumn(name = "IdEdicao", referencedColumnName="IdEdicao")         
    private Edicao edicao;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @ForeignKey(name="ObraTipoObra")
    @JoinColumn(name = "IdTipoObra", referencedColumnName="IdTipoObra")         
    private TipoObra tipoobra;

	//IdTipoObra;
    
    public Obra() {}

    public Integer getIdObra() {
        return idObra;
    }

    public void setIdObra(Integer idObra) {
        this.idObra = idObra;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getAnoDePublicacao() {
        return anoDePublicacao;
    }

    public void setAnoDePublicacao(String anoDePublicacao) {
        this.anoDePublicacao = anoDePublicacao;
    }

    public String getNotacaoDeAutor() {
        return notacaoDeAutor;
    }

    public void setNotacaoDeAutor(String notacaoDeAutor) {
        this.notacaoDeAutor = notacaoDeAutor;
    }

    public String getNotacaoDeArea() {
        return notacaoDeArea;
    }

    public void setNotacaoDeArea(String notacaoDeArea) {
        this.notacaoDeArea = notacaoDeArea;
    }

    public Double getValorDeReposicao() {
        return valorDeReposicao;
    }

    public void setValorDeReposicao(Double valorDeReposicao) {
        this.valorDeReposicao = valorDeReposicao;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Edicao getEdicao() {
        return edicao;
    }

    public void setEdicao(Edicao edicao) {
        this.edicao = edicao;
    }

    public TipoObra getTipoobra() {
        return tipoobra;
    }

    public void setTipoobra(TipoObra tipoobra) {
        this.tipoobra = tipoobra;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.idObra != null ? this.idObra.hashCode() : 0);
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
        final Obra other = (Obra) obj;
        if (this.idObra != other.idObra && (this.idObra == null || !this.idObra.equals(other.idObra))) {
            return false;
        }
        return true;
    }

}