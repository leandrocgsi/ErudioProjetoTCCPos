package br.com.erudio.suport;

import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.TipoAluno;
import br.com.erudio.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Session;

@ManagedBean(name="bbTipoAluno")
@RequestScoped
public class BbTipoAluno  implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public List<TipoAluno> getTipoAlunos() {
        Session session = FacesContextUtil.getRequestSession();
        InterfaceDAO<TipoAluno> tipoAlunoDAO = new HibernateDAO<TipoAluno>(TipoAluno.class, session);
        return tipoAlunoDAO.getEntities();
    }
}