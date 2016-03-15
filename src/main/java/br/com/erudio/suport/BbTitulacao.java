package br.com.erudio.suport;

import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.Titulacao;
import br.com.erudio.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="bbTitulacao")
@RequestScoped
public class BbTitulacao  implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public List<Titulacao> getTitulacaos() {        
        InterfaceDAO<Titulacao> titulacaoDAO = new HibernateDAO<Titulacao>(Titulacao.class, FacesContextUtil.getRequestSession());
        return titulacaoDAO.getEntities();
    }
}