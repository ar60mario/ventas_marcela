/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.services;

import ar.com.ventas.bo.TitularCuitBO;
import ar.com.ventas.entities.TitularCuit;
import ar.com.ventas.utils.HibernateUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Mario
 */
public class TitularCuitService {
    public List<TitularCuit> getAllTitularDeCuitActivos() throws Exception{
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<TitularCuit> tcs = null;
        try {
            tcs = new TitularCuitBO().getAllTitularesDeCuitActivos();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return tcs;
        
    }
    
    public TitularCuit saveTitular(TitularCuit tc) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            tc = new TitularCuitBO().saveTitularCuit(tc);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return tc;
    }
    
    public TitularCuit updateTitular(TitularCuit tc) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            tc = new TitularCuitBO().updateTitularCuit(tc);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return tc;
    }
    
//    public TitularCuit getTitularByCliente(TitularCuit tc) throws Exception {
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//        try {
//            tc = new TitularCuitBO().updateTitularCuit(tc);
//            tx.commit();
//        } catch (Exception ex) {
//            tx.rollback();
//            throw new Exception(ex);
//        }
//        return tc;
//    }
        /*
        
        TicketTime ticket = null;
        try {
//            ticket = new TicketTimeBO().getTicketById(id);
//            tx.commit();
        } catch (Exception ex) {
//            tx.rollback();
//            throw new Exception(ex);
        }
        return ticket;
        */
//        List<TitularCuit> tcs = new ArrayList<>();
//        TitularCuit tc = new TitularCuit();
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.DAY_OF_MONTH, 1);
//        cal.set(Calendar.MONTH, 6);
//        cal.set(Calendar.YEAR, 2019);
//        Date fe = cal.getTime();
//        tc.setCalleNumero("YATAY 12");
//        tc.setCategoria(6); // MONOTRIBUTO
//        tc.setCodigoPostalLocalidad("1437 - C.A.B.A.");
//        tc.setCuit("27-14618662-4");
//        tc.setFechaInicioActividades(fe);
//        tc.setIibb("27146186624");
//        tc.setRazonSocial("LOUSTAU MARCELA ESTELA");
//        tcs.add(tc);
//        return tcs;
//    }
    
    
}
