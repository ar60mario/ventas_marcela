/*
 * Aqui va toda la lógica de validaciones respecto a los Administradores.
 */
package ar.com.ventas.bo;

import ar.com.ventas.dao.TextoPredefinidoLineaDAO;
import ar.com.ventas.entities.TextoPredefinidoLinea;
import ar.com.ventas.entities.TextoPredefinido;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Mar y Mar Informatica
 */
public class TextoPredefinidoLineaBO {

    private final TextoPredefinidoLineaDAO dao = new TextoPredefinidoLineaDAO();

    private static final Logger logger = Logger.getLogger("TextoPredefinidoBO");

    public List<TextoPredefinidoLinea> getAllTextoPredefinidoActivos() throws Exception {
        List<TextoPredefinidoLinea> consorcios = null;
        try {
            consorcios = dao.getAllTextoPredefinidoActivos();
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return consorcios;
    }

    public TextoPredefinidoLinea getTextoPredefinidoById(Long idC) throws Exception {
        TextoPredefinidoLinea consorcio = null;
        try {
            consorcio = dao.getTextoPredefinidoById(idC);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return consorcio;
    }

    public List<TextoPredefinidoLinea> getAllTextoPredefinidoActivosByFiltro(String filtro) throws Exception {
        List<TextoPredefinidoLinea> consorcios = null;
        try {
            consorcios = dao.getAllTextoPredefinidoActivosByFiltro(filtro);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return consorcios;
    }

    public List<TextoPredefinidoLinea> getAllTextoPredefinidoActivosByTextoPredefinido(TextoPredefinido tp) throws Exception {
        List<TextoPredefinidoLinea> consorcios = null;
        try {
            consorcios = dao.getAllTextoPredefinidoActivosByTextoPredefinido(tp);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return consorcios;
    }
    
    public List<TextoPredefinidoLinea> getAllTextoPredefinidoActivosByNumero(String numero) throws Exception {
        List<TextoPredefinidoLinea> consorcios = null;
        try {
            consorcios = dao.getAllTextoPredefinidoActivosByNumero(numero);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return consorcios;
    }

    public List<TextoPredefinidoLinea> getAllTextoPredefinidoInactivos() throws Exception {
        List<TextoPredefinidoLinea> consorcios = null;
        try {
            consorcios = dao.getAllTextoPredefinidoInactivos();
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return consorcios;
    }

    public List<TextoPredefinidoLinea> getAllTextoPredefinidoInactivosByFiltro(String filtro) throws Exception {
        List<TextoPredefinidoLinea> consorcios = null;
        try {
            consorcios = dao.getAllTextoPredefinidoInactivosByFiltro(filtro);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return consorcios;
    }

//    public List<Cliente> getAllClientesActivos() throws Exception {
//        List<Cliente> listClientes = null;
//        try {
//            listClientes = dao.getAllClientesActivos();
//        } catch (HibernateException ex) {
//            throw new Exception(ex);
//        }
//        return listClientes;
//    }
//
    public TextoPredefinidoLinea saveTextoPredefinido(TextoPredefinidoLinea consorcio) throws Exception {
        try {
            dao.save(consorcio);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return consorcio;
    }

    public TextoPredefinidoLinea updateTextoPredefinido(TextoPredefinidoLinea txt) throws Exception {
        try {
            dao.update(txt);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return txt;
    }
    
    public void deleteTextoPredefinido(TextoPredefinidoLinea txt) throws Exception {
        try {
            dao.delete(txt);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
    }
    
//    public void deleteTextoPredefinido(TextoPredefinidoLinea consorcio) throws Exception {
//        try {
//            dao.delete(consorcio);
//        } catch (HibernateException ex) {
//            throw new Exception(ex);
//        }
//    }
//
//    public void updateCliente(Cliente cliente) throws Exception {
//
//        // Primero guardo la dirección del administrador.
////        DomicilioBO domicilioBO = new DomicilioBO();        
////        Domicilio domicilioCliente = cliente.getDomicilio();
////        domicilioCliente = domicilioBO.updateDomicilio(domicilioCliente);
////        cliente.setDomicilio(domicilioCliente);
//        try {
//            cliente = (Cliente) dao.update(cliente);
//        } catch (HibernateException ex) {
//            throw new Exception(ex);
//        }
//
//    }
//
//    public List<Cliente> getClienteByPagina(int paginaActual) throws Exception {
//
//        List<Cliente> listadoClientes = null;
//        int start = 0;
//        if (paginaActual > 1) {
//            start = ((paginaActual - 1) * Constantes.MAX_RESULTS) + 1;
//        }
//
//        try {
//            listadoClientes = dao.getAll(Cliente.class, start);
//        } catch (HibernateException ex) {
//            throw new Exception(ex);
//        }
//
//        return listadoClientes;
//
//    }
//
//    public int getClientesCount() throws Exception {
//
//        int cantidad = 0;
//
//        try {
//            cantidad = dao.getCount(Cliente.class);
//        } catch (HibernateException ex) {
//            throw new Exception(ex);
//        }
//
//        return cantidad;
//
//    }
//
//    public void deleteCliente(Cliente cliente) throws Exception {
//
//        try {
//            dao.delete(cliente);
//
//        } catch (HibernateException ex) {
//
//            throw new Exception(ex);
//        }
//    }
//
//    public Cliente getClienteByCodigo(String codigo) throws Exception {
//        Cliente cliente = null;
//        try {
//            cliente = dao.getByCodigo(codigo);
//        } catch (HibernateException ex) {
//            throw new Exception(ex);
//        }
//        return cliente;
//    }
//
//    public List<Cliente> getClientesOrdenado() throws Exception {
//
//        List<Cliente> listadoClientes = null;
//        try {
//            listadoClientes = dao.getAllClientesOrdenado();
//        } catch (HibernateException ex) {
//            throw new Exception(ex);
//        }
//        return listadoClientes;
//
//    }
//    
//    public List<Cliente> getClientesByFiltro(String filtro) throws Exception {
//        List<Cliente> clientes = null;
//        try {
//            clientes = dao.getClientesByFiltro(filtro);
//        } catch (HibernateException ex) {
//            throw new Exception(ex);
//        }
//        return clientes;
//    }
////getClientesInactivosByFiltroOrdenado
//    public List<Cliente> getClientesInactivosByFiltroOrdenado(String f) throws Exception {
//        List<Cliente> clientes = null;
//        try {
//            clientes = dao.getClientesInactivosByFiltroOrdenado(f);
//        } catch (HibernateException ex) {
//            throw new Exception(ex);
//        }
//        return clientes;
//    }
//    
//    public List<Cliente> getClientesByFiltroNumerico(String f) throws Exception {
//        List<Cliente> clientes = null;
//        try {
//            clientes = dao.getClientesByFiltroNumerico(f);
//        } catch (HibernateException ex) {
//            throw new Exception(ex);
//        }
//        return clientes;
//    }
//    
//    public void saveListaClientes(List<Cliente> listaClientes) throws Exception {
//        DomicilioBO db = new DomicilioBO();
//
//        if (listaClientes != null && !listaClientes.isEmpty()) {
//            for (Cliente cliente : listaClientes) {
//                Domicilio domicilio = cliente.getDomicilio();
//                try {
//                    domicilio = db.saveDomicilio(domicilio);
//                    cliente.setDomicilio(domicilio);
//                    dao.save(cliente);
//                } catch (HibernateException ex) {
//                    throw new Exception("Ha ocurrido un problema intentando guardar el Cliente.\nPor favor intente nuevamente mas tarde.");
//                }
//            }
//        }
//    }
//
//    public List<Cliente> getClientesOrdenadoPorNumero() throws Exception {
//
//        List<Cliente> listadoClientes = null;
//        try {
//            listadoClientes = dao.getAllClientesOrdenadoPorNumero();
//        } catch (HibernateException ex) {
//            throw new Exception(ex);
//        }
//        return listadoClientes;
//
//    }
//
//    public List<Cliente> getClientesDeudores() throws Exception {
//        List<Cliente> clientes = null;
//        try {
//            clientes = dao.getClientesDeudores();
//        } catch (HibernateException ex) {
//            throw new Exception(ex);
//        }
//        return clientes;
//    }
//
//    public List<Cliente> getAllClientesConSaldo() throws Exception {
//        List<Cliente> clientes = null;
//        try {
//            clientes = dao.getAllClientesConSaldo();
//        } catch (HibernateException ex) {
//            throw new Exception(ex);
//        }
//        return clientes;
//    }
//
//    public List<Cliente> getByVendedor(Vendedor vendedor) throws Exception {
//        List<Cliente> listClientes = null;
//        try {
//            listClientes = dao.getByVendedor(vendedor);
//        } catch (HibernateException ex) {
//            throw new Exception(ex);
//        }
//        return listClientes;
//    }
}
