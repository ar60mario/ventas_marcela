/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.dao;

import ar.com.ventas.entities.TitularCuit;
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
public class TitularCuitDAO extends GenericDAO {

    public List<TitularCuit> getAllTitularesDeCuitActivos() {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(TitularCuit.class);
        criteria.add(Restrictions.eq("activo", true));
        criteria.addOrder(Order.asc("razonSocial"));
        return (List<TitularCuit>) criteria.list();
    }
    
    public List<TitularCuit> getAllTitularesDeCuitInactivos() {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(TitularCuit.class);
        criteria.add(Restrictions.eq("activo", false));
        criteria.addOrder(Order.asc("nombreFantasia"));
        return (List<TitularCuit>) criteria.list();
    }
    
//    public TitularCuit getTitularDeCuitActivoByPersona(Persona persona) {
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Criteria criteria = session.createCriteria(TitularCuit.class);
//        criteria.add(Restrictions.eq("activo", true));
//        criteria.add(Restrictions.eq("persona", persona));
//        return (TitularCuit) criteria.uniqueResult();
//    }
    
    public List<TitularCuit> getTitularDeCuitActivoByFiltro(String filtro) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(TitularCuit.class);
        criteria.add(Restrictions.eq("activo", true));
        Criteria criteria1 = criteria.createCriteria("persona");
        criteria1.add(Restrictions.like("apellidoNombre", "%"+filtro+"%"));
        return (List<TitularCuit>) criteria.list();
    }
}
