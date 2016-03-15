package br.com.erudio.suport;

import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.Edicao;
import br.com.erudio.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="bbEdicao")
@RequestScoped
public class BbEdicao  implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public List<Edicao> getEdicaos() {
        InterfaceDAO<Edicao> edicaoDAO = new HibernateDAO<Edicao>(Edicao.class, FacesContextUtil.getRequestSession());
        return edicaoDAO.getEntities();
    }	
}