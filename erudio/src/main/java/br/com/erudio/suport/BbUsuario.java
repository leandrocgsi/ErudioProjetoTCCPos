package br.com.erudio.suport;

import java.util.List;



import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.Pessoa;
import br.com.erudio.util.FacesContextUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="bbUsuario")
@RequestScoped
public class BbUsuario {
    public List<Pessoa> getLista() {
        InterfaceDAO<Pessoa> usuarioDAO = new HibernateDAO<Pessoa>(Pessoa.class, FacesContextUtil.getRequestSession());
        return usuarioDAO.getEntities();
    }
}