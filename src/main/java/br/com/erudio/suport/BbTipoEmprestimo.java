package br.com.erudio.suport;

import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.TipoEmprestimo;
import br.com.erudio.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="bbTipoEmprestimo")
@RequestScoped
public class BbTipoEmprestimo  implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public List<TipoEmprestimo> getTipoEmprestimos() {        
        InterfaceDAO<TipoEmprestimo> tipoEmprestimoDAO = new HibernateDAO<TipoEmprestimo>(TipoEmprestimo.class, FacesContextUtil.getRequestSession());
        return tipoEmprestimoDAO.getEntities();
    }
}