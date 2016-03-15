package br.com.erudio.suport;

import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.TipoReserva;
import br.com.erudio.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="bbTipoPendencia")
@RequestScoped
public class BbTipoPendencia  implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public List<TipoReserva> getTipoReservas() {        
        InterfaceDAO<TipoReserva> tipoReservaDAO = new HibernateDAO<TipoReserva>(TipoReserva.class, FacesContextUtil.getRequestSession());
        return tipoReservaDAO.getEntities();
    }
}