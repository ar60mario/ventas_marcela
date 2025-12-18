/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.dao;

import ar.com.ventas.entities.TextoPredefinido;
import ar.com.ventas.entities.TextoPredefinidoLinea;
import ar.com.ventas.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Order;

/**
 *
 * @author Mar y Mar Informatica
 */
public class TextoPredefinidoLineaDAO extends GenericDAO {

    public List<TextoPredefinidoLinea> getAllTextoPredefinidoActivos() {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(TextoPredefinidoLinea.class);
        Criteria criteria1 = criteria.createCriteria("domicilio");
        criteria.add(Restrictions.eq("activo", true));
        criteria1.addOrder(Order.asc("calle"));
        criteria1.addOrder(Order.asc("numero"));
        return (List<TextoPredefinidoLinea>) criteria.list();
    }

    public TextoPredefinidoLinea getTextoPredefinidoById(Long idC) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(TextoPredefinidoLinea.class);
        criteria.add(Restrictions.eq("id", idC));
        return (TextoPredefinidoLinea) criteria.uniqueResult();
    }

    public List<TextoPredefinidoLinea> getAllTextoPredefinidoActivosByFiltro(String filtro) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(TextoPredefinidoLinea.class);
        criteria.add(Restrictions.eq("activo", true));
        Criteria criteria1 = criteria.createCriteria("domicilio");
        criteria1.add(Restrictions.like("calle", "%" + filtro + "%"));
        return (List<TextoPredefinidoLinea>) criteria.list();
    }
    
    public List<TextoPredefinidoLinea> getAllTextoPredefinidoActivosByTextoPredefinido(TextoPredefinido tp) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(TextoPredefinidoLinea.class);
        criteria.add(Restrictions.eq("textoPredefinido", tp));
//        Criteria criteria1 = criteria.createCriteria("domicilio");
//        criteria1.add(Restrictions.like("calle", "%" + filtro + "%"));
        return (List<TextoPredefinidoLinea>) criteria.list();
    }

    public List<TextoPredefinidoLinea> getAllTextoPredefinidoActivosByNumero(String numero) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(TextoPredefinidoLinea.class);
        criteria.add(Restrictions.eq("activo", true));
        Criteria criteria1 = criteria.createCriteria("domicilio");
        criteria1.add(Restrictions.like("numero", "%" + numero + "%"));
        return (List<TextoPredefinidoLinea>) criteria.list();
    }

    public List<TextoPredefinidoLinea> getAllTextoPredefinidoInactivos() {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(TextoPredefinidoLinea.class);
        criteria.add(Restrictions.eq("activo", false));
        //criteria.addOrder(Order.asc("razonSocial"));
        return (List<TextoPredefinidoLinea>) criteria.list();
    }

    public List<TextoPredefinidoLinea> getAllTextoPredefinidoInactivosByFiltro(String filtro) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(TextoPredefinidoLinea.class);
        criteria.add(Restrictions.eq("activo", false));
        Criteria criteria1 = criteria.createCriteria("domicilio");
        criteria1.add(Restrictions.like("calle", "%" + filtro + "%"));
        return (List<TextoPredefinidoLinea>) criteria.list();
    }

//    public List<Cliente> getAllClientesActivos() {
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Criteria criteria = session.createCriteria(Cliente.class);
//        criteria.add(Restrictions.eq("activo", true));
//        return (List<Cliente>) criteria.list();
//    }
//
//    public List<Cliente> getClientesInactivosByFiltroOrdenado(String f) {
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Criteria criteria = session.createCriteria(Cliente.class);
//        criteria.add(Restrictions.like("razonSocial", "%" + f + "%"));
//        criteria.addOrder(Order.asc("razonSocial"));
//        criteria.add(Restrictions.eq("inactivo", true));
//        return (List<Cliente>) criteria.list();
//    }
//
//    public List<Cliente> getClientesByFiltro(String filtro) {
//        List<Cliente> clientes = null;
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        StringBuffer sb = new StringBuffer();
//        sb.append("from Cliente clie ");
//        sb.append("where clie.razonSocial like :filtro ");
//        sb.append("and clie.activo = true ");
//        sb.append("order by clie.razonSocial asc");
//        Query query = session.createQuery(sb.toString());
//        query.setParameter("filtro", "%" + filtro + "%");
//        clientes = (List<Cliente>) query.list();
//        return clientes;
//    }
//    
//    public List<Cliente> getClientesByFiltroNumerico(String filtro) {
//        List<Cliente> clientes = null;
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        StringBuffer sb = new StringBuffer();
//        sb.append("from Cliente clie ");
//        sb.append("where clie.razonSocial like :filtro ");
//        //sb.append("and clie.activo = false ");
//        sb.append("order by clie.codigo asc");
//        Query query = session.createQuery(sb.toString());
//        query.setParameter("filtro", "%" + filtro + "%");
//        clientes = (List<Cliente>) query.list();
//        return clientes;
//    }
//
//    public List<Cliente> getAllClientesOrdenadoPorNumero() {
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Criteria criteria = session.createCriteria(Cliente.class);
//        criteria.addOrder(Order.asc("codigo"));
//        return (List<Cliente>) criteria.list();
//    }
//
//    public List<Cliente> getClientesDeudores() {
//        List<Cliente> clientes = null;
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        StringBuffer sb = new StringBuffer();
//        sb.append("from Cliente clie ");
//        sb.append("where clie.saldo > 0.0");
//        Query query = session.createQuery(sb.toString());
//        clientes = (List<Cliente>) query.list();
//        return clientes;
//    }
//
//    public List<Cliente> getAllClientesConSaldo() {
//        List<Cliente> clientes = null;
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        StringBuffer sb = new StringBuffer();
//        sb.append("from Cliente clie ");
//        sb.append("where clie.saldo != 0.00");
//        Query query = session.createQuery(sb.toString());
//        clientes = (List<Cliente>) query.list();
//        return clientes;
//    }
//
//    public List<Cliente> getByVendedor(Vendedor vendedor) {
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Criteria criteria = session.createCriteria(Cliente.class);
//        criteria.add(Restrictions.eq("vendedor", vendedor));
//        criteria.addOrder(Order.asc("codigo"));
//        List<Cliente> clientes = (List<Cliente>) criteria.list();
//        return clientes;
//    }
}
