/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.dao;

import ar.com.ventas.entities.CertificadosAfip;
import ar.com.ventas.entities.TitularCuit;
import ar.com.ventas.utils.HibernateUtils;
import com.mchange.v1.xml.DomParseUtils;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Mar y Mar Informatica
 */
public class CertificadosAfipDAO extends GenericDAO {

    public List<CertificadosAfip> getAllAdministradoresActivos() {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(CertificadosAfip.class);
        criteria.add(Restrictions.eq("activo", true));
        criteria.addOrder(Order.asc("razonSocial"));
        return (List<CertificadosAfip>) criteria.list();
    }
    
    public List<CertificadosAfip> getAllAdministradoresInactivos() {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(CertificadosAfip.class);
        criteria.add(Restrictions.eq("activo", false));
        criteria.addOrder(Order.asc("razonSocial"));
        return (List<CertificadosAfip>) criteria.list();
    }

    public CertificadosAfip getCertificadoByTitular(TitularCuit titular) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(CertificadosAfip.class);
        criteria.add(Restrictions.eq("titularCuit", titular));
        return (CertificadosAfip) criteria.uniqueResult();
    }
}
