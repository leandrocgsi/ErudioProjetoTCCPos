package br.com.erudio.suport;

import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.PeriodoLetivo;
import br.com.erudio.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="bbPeriodoLetivo")
@RequestScoped
public class BbPeriodoLetivo  implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public List<PeriodoLetivo> getPeriodoLetivos(){
        InterfaceDAO<PeriodoLetivo> periodoLetivoDAO = new HibernateDAO<PeriodoLetivo>(PeriodoLetivo.class, FacesContextUtil.getRequestSession());
        return periodoLetivoDAO.getEntities();
    }   
}