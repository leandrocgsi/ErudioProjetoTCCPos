package br.com.erudio.model.entities;

import java.util.Date;
import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "F")
public class Funcionario extends Pessoa {

    private static final long serialVersionUID = 1L;
    
    @Lob
    @Column(name = "Foto")
    private byte[] foto;
    @Column(name = "CTPS", length = 2)
    private String ctps;
    @Column(name = "SerieCTPS", length = 2)
    private String serieCTPS;
    @Column(name = "PIS", length = 2)
    private String pis;
    @Column(name = "Pai", length = 130)
    private String pai;
    @Column(name = "Mae", length = 130)
    private String mae;
    @Column(name = "Naturalidade", length = 130)
    private String naturalidade;
    @Temporal(TemporalType.DATE)
    @Column(name = "DataNascimento")
    private Date dataNascimento;
    @Column(name = "DiaAcerto")
    private String diaAcerto;
    @Temporal(TemporalType.TIME)
    @Column(name = "HorarioInicio")
    private Date horarioInicio;
    @Temporal(TemporalType.TIME)
    @Column(name = "HorarioFim")
    private Date horarioFim;
    @Column(name = "Especialidades", unique = true, length = 25)
    private String especialidades;
    @Column(name = "Profissao", length = 100)
    private String profissao;

    public Funcionario() {
      //  this.setCartao(new Cartao());
    }

    public String getCtps() {
        return ctps;
    }

    public void setCtps(String ctps) {
        this.ctps = ctps;
    }

    public String getSerieCTPS() {
        return serieCTPS;
    }

    public void setSerieCTPS(String serieCTPS) {
        this.serieCTPS = serieCTPS;
    }

    public String getPis() {
        return pis;
    }

    public void setPis(String pis) {
        this.pis = pis;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(String especialidades) {
        this.especialidades = especialidades;
    }
    
    public String getDiaAcerto() {
        return diaAcerto;
    }

    public void setDiaAcerto(String diaAcerto) {
        this.diaAcerto = diaAcerto;
    }

    public Date getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(Date horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public Date getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(Date horarioFim) {
        this.horarioFim = horarioFim;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }
}