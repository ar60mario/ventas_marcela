package ar.com.ventas.bo;

import ar.com.ventas.dao.FacturaDAO;
import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.Factura;
import ar.com.ventas.entities.Rubro;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;

public class FacturaBO {

    FacturaDAO dao = new FacturaDAO();

    public List<Factura> getAllFacturas() throws Exception {
        try {
            return dao.getAllFacturas();
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
    }

    public Factura saveFactura(Factura factura) throws Exception {
        try {
            factura = (Factura) dao.save(factura);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return factura;
    }

    public Factura getFacturasByNro(Integer numero, Integer tipoDoc) throws Exception {
        Factura factura = null;
        try {
            factura = (Factura) dao.getFacturasByNro(numero, tipoDoc);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return factura;
    }

    public List<Factura> getFacturasEntreFechas(Date fd, Date fa) throws Exception {
        try {
            return dao.getFacturasEntreFechas(fd, fa);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
    }

    public List<Factura> getFacturasEntreFechasAndRubro(Date fd, Date fa, Rubro ru) throws Exception {
        try {
            return dao.getFacturasEntreFechasAndRubro(fd, fa, ru);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
    }

    public List<Factura> getFacturasPorCliente(Cliente cliente) throws Exception {
        try {
            return dao.getFacturasPorCliente(cliente);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
    }
    
    public List<Factura> getFcAndNcPorCliente(Cliente cliente) throws Exception {
        try {
            return dao.getFcAndNcPorCliente(cliente);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
    }
}
