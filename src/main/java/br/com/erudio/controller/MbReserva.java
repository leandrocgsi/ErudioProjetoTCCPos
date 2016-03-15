package br.com.erudio.controller;


import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.Exemplar;
import br.com.erudio.model.entities.Obra;
import br.com.erudio.model.entities.Pessoa;
import br.com.erudio.model.entities.Reserva;
import br.com.erudio.model.entities.TipoReserva;
import br.com.erudio.suport.BbUsuarioLogado;
import br.com.erudio.util.FacesContextUtil;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

@ManagedBean(name = "mbReserva")
@SessionScoped
public class MbReserva {

    private List<Reserva> reservas;
    private Reserva reserva = new Reserva();

    public MbReserva() {
    }
    
    /* Verifica se a obra esta disponivel para reserva
     * True: quando disponivel
     * False: quando indisponivel
     */
    public boolean obraDisponivel(Obra obra){
        // quantidade de reservas da obra
        InterfaceDAO<TipoReserva> situacaoDAO = new HibernateDAO<TipoReserva>(TipoReserva.class, FacesContextUtil.getRequestSession());
        TipoReserva situacao = situacaoDAO.getEntity(TipoReserva.PENDENTE);
        DetachedCriteria criteria = DetachedCriteria.forClass(Reserva.class);
        criteria.add(Restrictions.eq("obra", obra))
                .add(Restrictions.eq("tiporeserva", situacao));
        InterfaceDAO<Reserva> reservaDAO = new HibernateDAO<Reserva>(Reserva.class, FacesContextUtil.getRequestSession());
        List<Reserva> reservasObra = reservaDAO.getListByDetachedCriteria(criteria);
        
        // quantidade de exemplares disponiveis
        DetachedCriteria criteria2 = DetachedCriteria.forClass(Exemplar.class);
        criteria2.add(Restrictions.eq("obra", obra))
                .add(Restrictions.eq("disponivel", Boolean.TRUE));
        InterfaceDAO<Exemplar> exemplarDAO = new HibernateDAO<Exemplar>(Exemplar.class, FacesContextUtil.getRequestSession());
        List<Exemplar> exemplaresDisp = exemplarDAO.getListByDetachedCriteria(criteria2);
        
        if((reservasObra == null || reservasObra.isEmpty()) && (exemplaresDisp == null || exemplaresDisp.isEmpty())){
            System.out.println("Reserva = null; exemplar = null");
            return true;
        } else if((reservasObra != null && !reservasObra.isEmpty()) && (exemplaresDisp == null || exemplaresDisp.isEmpty())){
            System.out.println(reservasObra.size()+" Reserva != null; exemplar = null");
            return true;
        } else if((reservasObra == null || reservasObra.isEmpty()) && (exemplaresDisp != null && !exemplaresDisp.isEmpty())){
            System.out.println("Reserva = null; exemplar != null "+exemplaresDisp.size());
            return false;
        }else if((reservasObra != null && !reservasObra.isEmpty()) && (exemplaresDisp != null && !exemplaresDisp.isEmpty())){
            System.out.println(reservasObra.size()+"Reserva != null; exemplar != null"+exemplaresDisp.size());
            if(reservasObra.size() >= exemplaresDisp.size())
                return true;
            else
                return false;
        }
        return true;
    }
    
    /*
     * Verifica se o usuario já possui reservas pendentes para determinada obra
     * True: possui reservas pendentes
     * False: não possui reservas pendentes
     */
    public boolean obraDuplicada(Obra obra, Pessoa usuario){
        InterfaceDAO<TipoReserva> situacaoDAO = new HibernateDAO<TipoReserva>(TipoReserva.class, FacesContextUtil.getRequestSession());
        TipoReserva situacao = situacaoDAO.getEntity(TipoReserva.PENDENTE);
        DetachedCriteria criteria = DetachedCriteria.forClass(Reserva.class);
        criteria.add(Restrictions.eq("obra", obra))
                .add(Restrictions.eq("usuario", usuario))
                .add(Restrictions.eq("tiporeserva", situacao));
        InterfaceDAO<Reserva> reservaDAO = new HibernateDAO<Reserva>(Reserva.class, FacesContextUtil.getRequestSession());
        List<Reserva> reservasObra = reservaDAO.getListByDetachedCriteria(criteria);
        if(reservasObra == null || reservasObra.isEmpty()){
            System.out.println("Não possui reservas pendentes!");
            return false; // não possui reservas pendentes
        }
        System.out.println("Possui reservas pendentes! "+ reservasObra.size());
        return true;
    }

    public String addReserva() {        
        InterfaceDAO<TipoReserva> situacaoDAO = new HibernateDAO<TipoReserva>(TipoReserva.class, FacesContextUtil.getRequestSession());
        TipoReserva situacao = situacaoDAO.getEntity(TipoReserva.PENDENTE);
        InterfaceDAO<Reserva> reservaDAO = new HibernateDAO<Reserva>(Reserva.class, FacesContextUtil.getRequestSession());
        if (reserva.getIdReserva() == null || reserva.getIdReserva() == 0) {
            if(obraDisponivel(reserva.getObra())){
                if(obraDuplicada(reserva.getObra(), reserva.getUsuario())){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário já possui reserva desse material!", ""));
                    reserva = new Reserva();
                    return null;
                }
                reserva.setTiporeserva(situacao);
                reserva.setDataReserva(new Date());
                reserva.setOperador(BbUsuarioLogado.procuraPessoa());
                reservaDAO.save(reserva);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
            } else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Material disponivel para emprestimo", ""));
            }
        }
        reserva = new Reserva();
        return null;
    }

    public String obterReserva() {
        return "cadastrarreserva.xhtml";
    }

    public String delReserva() {
        Session session = FacesContextUtil.getRequestSession();
        InterfaceDAO<Reserva> reservaDAO = new HibernateDAO<Reserva>(Reserva.class, session);
        reservaDAO.remove(reserva);
        reserva = new Reserva();
        return null;
    }

    public void limpReserva() {
        reserva = new Reserva();
    }

    public List<Reserva> getReservas() {
        InterfaceDAO<Reserva> reservaDAO = new HibernateDAO<Reserva>(Reserva.class, FacesContextUtil.getRequestSession());
        reservas = reservaDAO.getEntities();
        return reservas;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}