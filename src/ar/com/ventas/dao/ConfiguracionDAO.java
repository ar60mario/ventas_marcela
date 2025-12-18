/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.dao;

import ar.com.ventas.entities.Configuracion;
import ar.com.ventas.entities.TitularCuit;
import ar.com.ventas.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Mario
 */
public class ConfiguracionDAO  extends GenericDAO {
    
    public Configuracion getConfiguracionByTitular(TitularCuit titular) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Configuracion.class);
        criteria.add(Restrictions.eq("titular", titular));
        Configuracion cnfig = (Configuracion) criteria.uniqueResult();
        return cnfig;
    }
    
}
