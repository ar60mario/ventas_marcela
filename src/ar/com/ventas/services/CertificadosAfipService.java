package ar.com.ventas.services;

import ar.com.ventas.bo.CertificadosAfipBO;
import ar.com.ventas.entities.CertificadosAfip;
import ar.com.ventas.entities.TitularCuit;
import ar.com.ventas.utils.HibernateUtils;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CertificadosAfipService {

    public CertificadosAfip saveCertificado(CertificadosAfip certificado) throws Exception {
        CertificadosAfip ca = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            ca = new CertificadosAfipBO().saveCertificado(certificado);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return ca;
    }
    
    public CertificadosAfip updateCertificado(CertificadosAfip certificado) throws Exception {
        CertificadosAfip ca = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            ca = new CertificadosAfipBO().updateCertificado(certificado);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return ca;
    }

//    public CertificadosAfip updateAdministrador(CertificadosAfip administrador) throws Exception {
//        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//        try {
//            administrador = new CertificadosAfipBO().updateAdministrador(administrador);
//            tx.commit();
//        } catch (Exception ex) {
//            tx.rollback();
//            throw new Exception(ex);
//        }
//        return administrador;
//    }

    public CertificadosAfip getCertificadoByTitular(TitularCuit titular) throws Exception {
        CertificadosAfip certif = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            certif = new CertificadosAfipBO().getCertificadoByTitular(titular);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return certif;
    }

    public List<CertificadosAfip> getAllAdministradoresActivos() throws Exception {
        List<CertificadosAfip> administradores = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            administradores = new CertificadosAfipBO().getAllAdministradoresActivos();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return administradores;
    }

    public List<CertificadosAfip> getAllAdministradoresInactivos() throws Exception {
        List<CertificadosAfip> administradores = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            administradores = new CertificadosAfipBO().getAllAdministradoresInactivos();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new Exception(ex);
        }
        return administradores;
    }

}
