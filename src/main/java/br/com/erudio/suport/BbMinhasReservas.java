package br.com.erudio.suport;

import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.Reserva;
import br.com.erudio.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.hibernate.Session;

@ManagedBean(name = "bbReserva")
@ViewScoped
public class BbMinhasReservas implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer idPessoa = BbUsuarioLogado.procuraPessoa().getIdPessoa();
    
    private String stringQuery = "from Reserva as f where f.usuario = " + idPessoa;
    
    public List<Reserva> getReservas() {
        Session session = FacesContextUtil.getRequestSession();
        InterfaceDAO<Reserva> reservaDAO = new HibernateDAO<Reserva>(Reserva.class, session);
        List<Reserva> reservas = reservaDAO.getListByHQLQuery(stringQuery);
        return reservas;
    }
}