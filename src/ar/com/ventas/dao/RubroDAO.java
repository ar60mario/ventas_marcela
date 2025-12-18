/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.dao;

import ar.com.ventas.entities.Rubro;
import ar.com.ventas.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Mar y Mar Informatica
 */
public class RubroDAO extends GenericDAO {

    public List<Rubro> getAllRubrosActivos() {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Rubro.class);
        criteria.add(Restrictions.eq("activo", true));
        criteria.addOrder(Order.asc("detalle"));
        return (List<Rubro>) criteria.list();
    }
    
    public List<Rubro> getAllRubrosInactivos() {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Rubro.class);
        criteria.add(Restrictions.eq("activo", false));
        criteria.addOrder(Order.asc("detalle"));
        return (List<Rubro>) criteria.list();
    }

    public Integer getCodigoSiguiente() {
        Integer codigo = 0;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Rubro.class);
        criteria.addOrder(Order.desc("codigo"));
        List<Rubro> rubros = (List<Rubro>) criteria.list();
        if (rubros != null && !rubros.isEmpty()) {
            Rubro admin = rubros.get(0);
            codigo = admin.getCodigo();
        }
        return codigo;
    }

}
