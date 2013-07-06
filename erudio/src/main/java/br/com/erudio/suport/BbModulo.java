package br.com.erudio.suport;

import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.Modulo;
import br.com.erudio.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="bbModulo")
@RequestScoped
public class BbModulo  implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public List<Modulo> getModulos(){
        InterfaceDAO<Modulo> moduloDAO = new HibernateDAO<Modulo>(Modulo.class, FacesContextUtil.getRequestSession());
        return moduloDAO.getEntities();
    }   
}