package br.com.erudio.suport;

import br.com.erudio.model.entities.Pessoa;
import br.com.erudio.util.FacesContextUtil;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@ManagedBean(name = "bbUsuarioLogado")
@SessionScoped
public class BbUsuarioLogado implements Serializable {

    private static final long serialVersionUID = 1L;
    private static String login;
    static SecurityContext context = SecurityContextHolder.getContext();

    public BbUsuarioLogado() {
    }

    public static Pessoa procuraPessoa() {
        if (context instanceof SecurityContext) {
            Authentication authentication = context.getAuthentication();
            if (authentication instanceof Authentication) {
                login = (((User) authentication.getPrincipal()).getUsername());
            }
        }
        Session session = FacesContextUtil.getRequestSession();
        Query query = session.createQuery("from Pessoa user where user.login like ?");
        query.setString(0, login);
        return (Pessoa) query.uniqueResult();
    }    
}