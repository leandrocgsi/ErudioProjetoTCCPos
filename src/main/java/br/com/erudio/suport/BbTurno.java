package br.com.erudio.suport;

import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.Turno;
import br.com.erudio.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="bbTurno")
@RequestScoped
public class BbTurno  implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public List<Turno> getTurnos() {
        InterfaceDAO<Turno> turnoDAO = new HibernateDAO<Turno>(Turno.class, FacesContextUtil.getRequestSession());
        return turnoDAO.getEntities();
    }	
}