package br.com.erudio.controller;

import java.util.List;

import br.com.erudio.model.dao.HibernateDAO;
import br.com.erudio.model.dao.InterfaceDAO;
import br.com.erudio.model.entities.Emprestimo;
import br.com.erudio.model.entities.Exemplar;
import br.com.erudio.model.entities.Multa;
import br.com.erudio.model.entities.Obra;
import br.com.erudio.model.entities.Reserva;
import br.com.erudio.model.entities.TipoEmprestimo;
import br.com.erudio.model.entities.TipoReserva;
import br.com.erudio.suport.BbUsuarioLogado;
import br.com.erudio.util.FacesContextUtil;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

@ManagedBean(name="mbEmprestimo")
@ViewScoped
public class MbEmprestimo implements Serializable{ 
    
    private Emprestimo emprestimo = new Emprestimo();
    private Integer idExemplar;
    //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
    public MbEmprestimo() {
    }
    
    /* Método aplicado no ato da devolução. Caso a obra esteja reserva referenciar o exemplar
     * devolvido na Reserva.
     * True se obra reservada
     * False se obra disponível
     */
    protected void reservadoDevolucao(Exemplar exemplar) {   
        
        // buscar todas as reservas pendentes para determinada obra
        InterfaceDAO<TipoReserva> situacaoDAO = new HibernateDAO<TipoReserva>(TipoReserva.class, FacesContextUtil.getRequestSession());
        TipoReserva situacao = situacaoDAO.getEntity(TipoReserva.PENDENTE);
        DetachedCriteria criteria = DetachedCriteria.forClass(Reserva.class);
        criteria.add(Restrictions.eq("obra", exemplar.getObra())) // determina obra
                .add(Restrictions.eq("situacaoreserva", situacao)) // situação da reserva
                .add(Restrictions.isNull("dataLimite")) // data limite igual a null
                .addOrder(Order.asc("dataReserva")); // ordena pela data de reserva
        InterfaceDAO<Reserva> reservaDAO = new HibernateDAO<Reserva>(Reserva.class, FacesContextUtil.getRequestSession());
        List<Reserva> reservasPendentes = reservaDAO.getListByDetachedCriteria(criteria);
        if (reservasPendentes == null || reservasPendentes.isEmpty()) {
            return;
        }        
        Calendar data = GregorianCalendar.getInstance();
        data.add(GregorianCalendar.DAY_OF_MONTH, 2);
        Reserva reserva = reservasPendentes.get(0);
        reserva.setDataOperacao(data.getTime());
       // reserva.setExemplar(exemplar.getIdExemplar());
        reservaDAO.update(reserva);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Material reservado!", ""));            
    }
    
    /*
     * Método aplicado no ato do emprestimo. Caso o exemplar esteja associado a uma reserva
     * não será possivel emprestá-lo.
     * True se exemplar reservado
     * False se exemplar disponível
     */
    protected boolean reservadoEmprestimo(Exemplar exemplar){
        InterfaceDAO<Exemplar> exemplarDAO = new HibernateDAO<Exemplar>(Exemplar.class, FacesContextUtil.getRequestSession());
        Exemplar exAux = exemplarDAO.getEntity(exemplar.getIdExemplar());
        
        System.out.println("Obra: "+exAux.getObra().getTitulo());
        System.out.println("Exemplar"+exAux.getIdExemplar());
        
        InterfaceDAO<TipoReserva> situacaoDAO = new HibernateDAO<TipoReserva>(TipoReserva.class, FacesContextUtil.getRequestSession());
        TipoReserva situacao = situacaoDAO.getEntity(TipoReserva.PENDENTE);
        DetachedCriteria criteria = DetachedCriteria.forClass(Reserva.class);
        criteria.add(Restrictions.eq("obra", exAux.getObra())) // determina obra
                .add(Restrictions.eq("situacaoreserva", situacao)) // situação da reserva Pendente
                .add(Restrictions.eq("exemplar", exAux.getIdExemplar())) // exemplar
                .addOrder(Order.asc("dataReserva")); // ordena pela data de reserva
        InterfaceDAO<Reserva> reservaDAO = new HibernateDAO<Reserva>(Reserva.class, FacesContextUtil.getRequestSession());
        Reserva reAux = reservaDAO.getEntityByDetachedCriteria(criteria);
        if(reAux == null)
            return false;
        else if(reAux.getUsuario().equals(emprestimo.getUsuario()))
            return false;
        else
            return true;
    }
    
    public void calculaMulta(){
        // verifica se a data de devolucao real e menor que a data de devolucao
        if(!emprestimo.getDataRealDevolucao().before(emprestimo.getDataDevolucao())){
            InterfaceDAO<Multa> multaDAO = new HibernateDAO<Multa>(Multa.class, FacesContextUtil.getRequestSession());
            Multa multa = new Multa();
            multa.setEmprestimo(emprestimo);
            multa.setValor(0.50);          
            multaDAO.save(multa);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Multa gerada: "+multa.getValor(), ""));
        }
    }
    
    public Boolean disponivel(){
        InterfaceDAO<Emprestimo> emprestimoDAO = new HibernateDAO<Emprestimo>(Emprestimo.class, FacesContextUtil.getRequestSession());
        
        // verifica se o exemplar está emprestado
        DetachedCriteria criteria = DetachedCriteria.forClass(Emprestimo.class);
        criteria.add(Restrictions.eq("exemplar", emprestimo.getExemplar()))
                .add(Restrictions.isNull("dataRealDevolucao"));
                      
        if(!emprestimoDAO.getListByDetachedCriteria(criteria).isEmpty()){
            FacesContext.getCurrentInstance().addMessage
                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Este item já está emprestado!", ""));
            System.out.println("Exemplar já emprestado");
            return false;
        }else{
            return true;
        }        
    }

    public Boolean obraduplicada(){
        InterfaceDAO<Emprestimo> emprestimoDAO = new HibernateDAO<Emprestimo>(Emprestimo.class, FacesContextUtil.getRequestSession());
        InterfaceDAO<Exemplar> exemplarDAO = new HibernateDAO<Exemplar>(Exemplar.class, FacesContextUtil.getRequestSession());        
        // retorna todos os emprestimos correntes do usuario
        DetachedCriteria criteria = DetachedCriteria.forClass(Emprestimo.class);
        criteria.add(Restrictions.eq("usuario", emprestimo.getUsuario()))
                .add(Restrictions.isNull("dataRealDevolucao"));
        List<Emprestimo> emprestimoCorrentesAluno = emprestimoDAO.getListByDetachedCriteria(criteria);                       
        // exemplar que será emprestado
        Exemplar exemplar = exemplarDAO.getEntity(emprestimo.getExemplar().getIdExemplar());
        // obra do exemplar que será emprestado
        Obra obra = exemplar.getObra();        
        // verifica se o exemplar que sera emprestado pertence a uma mesma obra dos emprestimos correntes
        for(Emprestimo emp : emprestimoCorrentesAluno){
            if(emp.getExemplar().getObra().equals(obra)){
                FacesContext.getCurrentInstance().addMessage
                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, "O usuário já está com um exemplar dessa obra!", ""));
                System.out.println("O usuário já está com um exemplar dessa obra");
                return true;
            }
        }        
        return false;        
    }
        
    public String fazEmprestimo() {
        if(reservadoEmprestimo(emprestimo.getExemplar())){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Material reservado para outro usuário!", ""));            
                emprestimo = new Emprestimo();
                return null;
        }
        //`identifica o tipo de emprestimo
        InterfaceDAO<TipoEmprestimo> tipoEmprestimoDAO = new HibernateDAO<TipoEmprestimo>(TipoEmprestimo.class, FacesContextUtil.getRequestSession());      
        DetachedCriteria criteria = DetachedCriteria.forClass(TipoEmprestimo.class);
        criteria.add(Restrictions.eq("idTipoEmprestimo", emprestimo.getTipoEmprestimo().getIdTipoEmprestimo()));
        TipoEmprestimo tp = tipoEmprestimoDAO.getEntityByDetachedCriteria(criteria);        
        
        if(!obraduplicada() && disponivel()){            
            emprestimo.setOperador(BbUsuarioLogado.procuraPessoa());
            emprestimo.setDataEmprestimo(GregorianCalendar.getInstance().getTime());
            Calendar data = GregorianCalendar.getInstance();
            data.add(GregorianCalendar.DAY_OF_MONTH, tp.getNumeroDeDias());
            emprestimo.setDataDevolucao(data.getTime()); 
            InterfaceDAO<Emprestimo> emprestimoDAO = new HibernateDAO<Emprestimo>(Emprestimo.class, FacesContextUtil.getRequestSession());      
            emprestimoDAO.save(emprestimo);
            InterfaceDAO<Exemplar> exemplarDAO = new HibernateDAO<Exemplar>(Exemplar.class, FacesContextUtil.getRequestSession());
            Exemplar exAux = exemplarDAO.getEntity(emprestimo.getExemplar().getIdExemplar());
            exAux.setDisponivel(Boolean.FALSE);
            exemplarDAO.update(exAux);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
                (FacesMessage.SEVERITY_INFO, "Item emprestado com sucesso! Cod. Operação"+emprestimo.getIdEmprestimo()+".", ""));
            emprestimo = new Emprestimo();      
        }
        return "EfetuarEmprestimo";
    }

    public String renovaEmprestimo() {        
        InterfaceDAO<Emprestimo> emprestimoDAO = new HibernateDAO<Emprestimo>(Emprestimo.class, FacesContextUtil.getRequestSession());
        emprestimo.setOperador(BbUsuarioLogado.procuraPessoa());        
        Calendar data = GregorianCalendar.getInstance();
        data.add(GregorianCalendar.DAY_OF_MONTH, 7);
        emprestimo.setDataDevolucao(data.getTime());
        emprestimoDAO.update(emprestimo);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Renovação realizada com sucesso", ""));
        emprestimo = new Emprestimo();
        return null;
    }
    
    public String devolveEmprestimo() {
        // carrega o exemplar        
        InterfaceDAO<Exemplar> exemlarDAO = new HibernateDAO<Exemplar>(Exemplar.class, FacesContextUtil.getRequestSession());      
        DetachedCriteria criteria1 = DetachedCriteria.forClass(Exemplar.class);
        criteria1.add(Restrictions.eq("idExemplar", idExemplar));
        Exemplar exemplar = exemlarDAO.getEntity(criteria1);  
        if(exemplar == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Material desconhecido!", ""));            
            return null;
        }           
        
        // carrega o emprestimo
        InterfaceDAO<Emprestimo> emprestimoDAO = new HibernateDAO<Emprestimo>(Emprestimo.class, FacesContextUtil.getRequestSession());      
        DetachedCriteria criteria2 = DetachedCriteria.forClass(Emprestimo.class);
        criteria2.add(Restrictions.eq("exemplar", exemplar))
                .add(Restrictions.isNull("dataRealDevolucao"));
        emprestimo = emprestimoDAO.getEntityByDetachedCriteria(criteria2);        
        if(emprestimo == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Material encontra-se disponivel!", ""));
            return null;
        } 
        
        emprestimo.setOperador(BbUsuarioLogado.procuraPessoa());
        Calendar data = GregorianCalendar.getInstance();
        emprestimo.setDataRealDevolucao(data.getTime());
        emprestimoDAO.update(emprestimo);
        calculaMulta();
        InterfaceDAO<Exemplar> exemplarDAO = new HibernateDAO<Exemplar>(Exemplar.class, FacesContextUtil.getRequestSession());
        Exemplar exAux = exemplarDAO.getEntity(emprestimo.getExemplar().getIdExemplar());
        exAux.setDisponivel(Boolean.TRUE);
        exemplarDAO.update(exAux);
        reservadoDevolucao(exAux);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Devolução realizada com sucesso", ""));
        emprestimo = new Emprestimo();
        idExemplar = null;
        return null;
    }
    
    
    public void limpEmprestimo() {
        emprestimo = new Emprestimo();

    }
    
    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public Integer getIdExemplar() {
        return idExemplar;
    }

    public void setIdExemplar(Integer idExemplar) {
        this.idExemplar = idExemplar;
    }

    public List<Emprestimo> getEmprestimos() {       
        InterfaceDAO<Emprestimo> emprestimoDAO = new HibernateDAO<Emprestimo>(Emprestimo.class, FacesContextUtil.getRequestSession());
        return emprestimoDAO.getEntities();
    }
}
