package br.com.erudio.suport;

import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.Departamento;
import br.com.erudio.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="bbDepartamento")
@RequestScoped
public class BbDepartamento  implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public List<Departamento> getDepartamentos() {
        InterfaceDAO<Departamento> departamentoDAO = new HibernateDAO<Departamento>(Departamento.class, FacesContextUtil.getRequestSession());
        return departamentoDAO.getEntities();
    }
}