package br.com.erudio.suport;

import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.TipoReserva;
import br.com.erudio.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Session;

@ManagedBean(name="bbTipoReserva")
@RequestScoped
public class BbTipoReserva  implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public List<TipoReserva> getTipoReservas() {
        Session session = FacesContextUtil.getRequestSession();
        InterfaceDAO<TipoReserva> tipoReservaDAO = new HibernateDAO<TipoReserva>(TipoReserva.class, session);
        return tipoReservaDAO.getEntities();
    }
}