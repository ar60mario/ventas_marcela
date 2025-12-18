/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.bo;

import ar.com.ventas.dao.AbonoDAO;
import ar.com.ventas.entities.Abono;
import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.Rubro;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Mario
 */
public class AbonoBO {

    AbonoDAO dao = new AbonoDAO();

    public List<Abono> getAllAbonosActivosOrdenado() throws Exception {
        try {
            return dao.getAllAbonosActivosOrdenado();
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
    }

    public List<Abono> getAbonosActivosByRubroAndClienteHabilitado(Rubro rubro) throws Exception {
        try {
            return dao.getAbonosActivosByRubroAndClienteHabilitado(rubro);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
    }

    public List<Abono> getAbonosActivosByRubro(Rubro rubro) throws Exception {
        try {
            return dao.getAbonosActivosByRubro(rubro);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
    }

    public List<Abono> getAllAbonosActivos() throws Exception {
        try {
            return dao.getAllAbonosActivos();
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
    }

    public void saveAbono(Abono abono) throws Exception {
        try {
            dao.save(abono);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
    }

    public void updateAbono(Abono abono) throws Exception {
        try {
            dao.update(abono);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
    }
    
    public void delete(Abono abono) throws Exception {
        try {
            dao.delete(abono);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
    }

    public void updateListaAbono(List<Abono> abonos) throws Exception {
        for (Abono ab : abonos) {
            
            try {
                dao.update(ab);
            } catch (HibernateException ex) {
                throw new Exception(ex);
            }
        }
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

    public List<Abono> getAbonosActivosPorCliente(Cliente cliente) throws Exception {
        try {
            return dao.getAbonosActivosPorCliente(cliente);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
    }
}
