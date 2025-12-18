/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.dao;

import ar.com.ventas.entities.TicketTime;
import ar.com.ventas.entities.TitularCuit;
import ar.com.ventas.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Mario
 */
public class TicketTimeDAO extends GenericDAO {

    public TicketTime getTicketByTitularCuit(TitularCuit titular) throws HibernateException {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(TicketTime.class);
        criteria.add(Restrictions.eq("titular", titular));
        TicketTime tkt = (TicketTime) criteria.uniqueResult();
        return tkt;        
    }
}
