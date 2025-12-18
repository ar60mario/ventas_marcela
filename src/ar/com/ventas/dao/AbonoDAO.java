/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.dao;

import ar.com.ventas.entities.Abono;
import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.Rubro;
import ar.com.ventas.utils.HibernateUtils;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Mario
 */
public class AbonoDAO extends GenericDAO {

    public List<Abono> getAllAbonosActivosOrdenado() {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Abono.class);
        Criteria criteria1 = criteria.createCriteria("cliente");
        Criteria criteria3 = criteria1.createCriteria("direccion");
        Criteria criteria2 = criteria.createCriteria("rubro");
        criteria.add(Restrictions.eq("activo", true));
        criteria3.addOrder(Order.asc("calle"));
        criteria3.addOrder(Order.asc("numero"));
        criteria2.addOrder(Order.asc("detalle"));
        List<Abono> abono = (List<Abono>) criteria.list();
        return abono;
    }
    
    public List<Abono> getAbonosActivosByRubroAndClienteHabilitado(Rubro rubro) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Abono.class);
        Criteria criteria1 = criteria.createCriteria("cliente");
        Criteria criteria3 = criteria1.createCriteria("direccion");
//        Criteria criteria2 = criteria.createCriteria("rubro");
        criteria1.add(Restrictions.eq("activo", true));
        criteria.add(Restrictions.eq("generado",false));
        criteria.add(Restrictions.eq("rubro",rubro));
        criteria3.addOrder(Order.asc("calle"));
        criteria3.addOrder(Order.asc("numero"));
        
        List<Abono> abono = (List<Abono>) criteria.list();
        return abono;
    }
    
    public List<Abono> getAbonosActivosByRubro(Rubro rubro) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Abono.class);
        Criteria criteria1 = criteria.createCriteria("cliente");
        Criteria criteria3 = criteria1.createCriteria("direccion");
//        Criteria criteria2 = criteria.createCriteria("rubro");
        criteria.add(Restrictions.eq("activo", true));
        criteria.add(Restrictions.eq("rubro",rubro));
        criteria3.addOrder(Order.asc("calle"));
        criteria3.addOrder(Order.asc("numero"));
        
        List<Abono> abono = (List<Abono>) criteria.list();
        return abono;
    }

    public List<Abono> getAllAbonosActivos() {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Abono.class);
        criteria.add(Restrictions.eq("activo", true));
        criteria.add(Restrictions.isNotNull("abono"));
        criteria.addOrder(Order.asc("detalle"));
        List<Abono> abono = (List<Abono>) criteria.list();
        return abono;
    }
    
    public List<Abono> getAbonosActivosPorCliente(Cliente cliente) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Abono.class);
        criteria.add(Restrictions.eq("activo", true));
        criteria.add(Restrictions.eq("cliente", cliente));
        criteria.addOrder(Order.asc("codigo"));
        List<Abono> abono = (List<Abono>) criteria.list();
        return abono;
    }

    public Integer getCodigoSiguiente() {
        Integer codigo = 0;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Abono.class);
        criteria.addOrder(Order.desc("codigo"));
        List<Abono> abonos = (List<Abono>) criteria.list();
        if (abonos != null && !abonos.isEmpty()) {
            Abono a = abonos.get(0);
            codigo = a.getCodigo();
        }
        return codigo;
    }

}
