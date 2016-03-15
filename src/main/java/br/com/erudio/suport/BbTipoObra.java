package br.com.erudio.suport;

import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.TipoObra;
import br.com.erudio.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Session;

@ManagedBean(name="bbTipoObra")
@RequestScoped
public class BbTipoObra  implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public List<TipoObra> getTipoobras() {        
        Session session = FacesContextUtil.getRequestSession();
        InterfaceDAO<TipoObra> tipoObraDAO = new HibernateDAO<TipoObra>(TipoObra.class, session);
        return tipoObraDAO.getEntities();
    }
}