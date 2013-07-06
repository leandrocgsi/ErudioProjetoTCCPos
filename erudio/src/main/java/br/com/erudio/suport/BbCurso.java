package br.com.erudio.suport;

import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.Curso;
import br.com.erudio.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="bbCurso")
@RequestScoped
public class BbCurso  implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public List<Curso> getCursos() {
        InterfaceDAO<Curso> cursoDAO = new HibernateDAO<Curso>(Curso.class, FacesContextUtil.getRequestSession());
        return cursoDAO.getEntities();
    }
}