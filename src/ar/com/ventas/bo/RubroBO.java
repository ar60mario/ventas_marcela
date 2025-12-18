/*
 * Aqui va toda la lógica de validaciones respecto a los Administradores.
 */
package ar.com.ventas.bo;

import ar.com.ventas.dao.RubroDAO;
import ar.com.ventas.entities.Rubro;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Mar y Mar Informatica
 */
public class RubroBO {

    private final RubroDAO dao = new RubroDAO();

    private static final Logger logger = Logger.getLogger("RubroBO");

    public List<Rubro> getAllRubrosActivos() throws Exception {
        List<Rubro> rubros = null;
        try {
            rubros = dao.getAllRubrosActivos();
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return rubros;
    }
    
    public List<Rubro> getAllRubrosInactivos() throws Exception {
        List<Rubro> rubros = null;
        try {
            rubros = dao.getAllRubrosInactivos();
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return rubros;
    }
    
    public Integer getCodigoSiguiente() throws Exception {
        Integer codigo;
        try {
            codigo = dao.getCodigoSiguiente();
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return codigo;
    }
    
    public Rubro saveRubro(Rubro rubro) throws Exception {
        
        try {
            dao.save(rubro);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return rubro;
    }
    
    public Rubro updateRubro(Rubro rubro) throws Exception {
        
        try {
            dao.update(rubro);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return rubro;
    }

}
