package br.com.erudio.suport;

import java.util.List;


import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.Obra;
import br.com.erudio.util.FacesContextUtil;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "bbObra")
@RequestScoped
public class BbObra {

    public List<Obra> getLista() {
        InterfaceDAO<Obra> obraDAO = new HibernateDAO<Obra>(Obra.class, FacesContextUtil.getRequestSession());
        return obraDAO.getEntities();
    }
}
