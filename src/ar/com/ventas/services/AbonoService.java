/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.services;

import ar.com.ventas.bo.AbonoBO;
import ar.com.ventas.bo.RenglonAbonoBO;
import ar.com.ventas.entities.Abono;
import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.RenglonAbono;
import ar.com.ventas.entities.Rubro;
import ar.com.ventas.utils.HibernateUtils;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Mario
 */
public class AbonoService {

    public List<Abono> getAllAbonosActivosOrdenado() throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<Abono> abonos = null;
        try {
            abonos = new AbonoBO().getAllAbonosActivosOrdenado();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return abonos;
    }

    public List<Abono> getAllAbonosActivos() throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<Abono> abonos = null;
        try {
            abonos = new AbonoBO().getAllAbonosActivos();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return abonos;
    }

    public void saveAbono(Abono abono) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            new AbonoBO().saveAbono(abono);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
    }

    public void saveAbonoCompleto(Abono abono, List<RenglonAbono> renglones) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            new AbonoBO().saveAbono(abono);
            for (RenglonAbono re : renglones) {
                re.setAbono(abono);
                System.out.println(re.getAbono());
                System.out.println(re.getImporte());
                System.out.println(re.getOrden());
                System.out.println(re.getTexto());
                new RenglonAbonoBO().saveRenglonAbono(re);
            }
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
    }

    public void updateAbono(Abono abono) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            new AbonoBO().updateAbono(abono);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
    }
    
    public void updateListaAbono(List<Abono> abonos) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            new AbonoBO().updateListaAbono(abonos);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
    }

    public void updateAbonoCompleto(Abono abono, List<RenglonAbono> renglones, List<RenglonAbono> renglones2) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            if (renglones2 != null && !renglones2.isEmpty()) {
                for (RenglonAbono ra1 : renglones2) {
                    new RenglonAbonoBO().deleteRenglonAbono(ra1);
                }
            }
            new AbonoBO().updateAbono(abono);
            if (renglones != null && !renglones.isEmpty()) {
                for (RenglonAbono ra2 : renglones) {
                    ra2.setAbono(abono);
                    new RenglonAbonoBO().saveRenglonAbono(ra2);
                }
            }
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
    }

    public void deleteAbonoCompleto(Abono abono, List<RenglonAbono> renglones) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            if (renglones != null && !renglones.isEmpty()) {
                for (RenglonAbono ra1 : renglones) {
                    new RenglonAbonoBO().deleteRenglonAbono(ra1);
                }
            }
            new AbonoBO().delete(abono);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
    }
    
    public Integer getCodigoSiguiente() throws Exception {
        Integer codigo;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            codigo = new AbonoBO().getCodigoSiguiente();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return codigo;
    }

    public List<Abono> getAbonosActivosPorCliente(Cliente cliente) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<Abono> abonos = null;
        try {
            abonos = new AbonoBO().getAbonosActivosPorCliente(cliente);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return abonos;
    }
    
    public List<Abono> getAbonosActivosByRubroAndClienteHabilitado(Rubro rubro) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<Abono> abonos = null;
        try {
            abonos = new AbonoBO().getAbonosActivosByRubroAndClienteHabilitado(rubro);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return abonos;
    }
    
    public List<Abono> getAbonosActivosByRubro(Rubro rubro) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<Abono> abonos = null;
        try {
            abonos = new AbonoBO().getAbonosActivosByRubro(rubro);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return abonos;
    }
}
