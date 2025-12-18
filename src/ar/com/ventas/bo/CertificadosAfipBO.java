/*
 * Aqui va toda la lógica de validaciones respecto a los Administradores.
 */
package ar.com.ventas.bo;

import ar.com.ventas.dao.CertificadosAfipDAO;
import ar.com.ventas.entities.CertificadosAfip;
import ar.com.ventas.entities.TitularCuit;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Mar y Mar Informatica
 */
public class CertificadosAfipBO {

    private final CertificadosAfipDAO dao = new CertificadosAfipDAO();

    private static final Logger logger = Logger.getLogger("AdministradorBO");

    public List<CertificadosAfip> getAllAdministradoresActivos() throws Exception {
        List<CertificadosAfip> administradores = null;
        try {
            administradores = dao.getAllAdministradoresActivos();
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return administradores;
    }
    
    public List<CertificadosAfip> getAllAdministradoresInactivos() throws Exception {
        List<CertificadosAfip> administradores = null;
        try {
            administradores = dao.getAllAdministradoresInactivos();
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return administradores;
    }
    
    
    public CertificadosAfip saveCertificado(CertificadosAfip certificado) throws Exception {
        CertificadosAfip ca=null;
        try {
            ca = (CertificadosAfip) dao.save(certificado);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return ca;
    }
    
    public CertificadosAfip updateCertificado(CertificadosAfip certificado) throws Exception {
        CertificadosAfip ca=null;
        try {
            ca = (CertificadosAfip) dao.update(certificado);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return ca;
    }
    
    public CertificadosAfip getCertificadoByTitular(TitularCuit titular) throws Exception {
        CertificadosAfip certif = null;
        try {
            certif = dao.getCertificadoByTitular(titular);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return certif;
    }
    
//    public CertificadosAfip updateAdministrador(CertificadosAfip administrador) throws Exception {
//        DomicilioBO domicilioBO = new DomicilioBO();
//        Domicilio domicilio = administrador.getDomicilio();
//        domicilio = domicilioBO.updateDomicilio(domicilio);
//        administrador.setDomicilio(domicilio);
//        try {
//            dao.update(administrador);
//        } catch (HibernateException ex) {
//            throw new Exception(ex);
//        }
//        return administrador;
//    }

}
