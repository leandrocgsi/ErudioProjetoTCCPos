package br.com.erudio.suport;

import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.TipoEmprestimo;
import br.com.erudio.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Session;

@ManagedBean(name="bbTipoEmprestimo")
@RequestScoped
public class BbTipoEmprestimo  implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public List<TipoEmprestimo> getTipoEmprestimos() {
        Session session = FacesContextUtil.getRequestSession();
        InterfaceDAO<TipoEmprestimo> tipoEmprestimoDAO = new HibernateDAO<TipoEmprestimo>(TipoEmprestimo.class, session);
        return tipoEmprestimoDAO.getEntities();
    }
}