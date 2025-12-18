/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.utils;

import ar.com.ventas.entities.Abono;
import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.Factura;
import ar.com.ventas.entities.NuevoCae;
import ar.com.ventas.entities.RenglonAbono;
import ar.com.ventas.entities.RenglonFactura;
import ar.com.ventas.entities.Rubro;
import ar.com.ventas.entities.TicketTime;
import ar.com.ventas.entities.TitularCuit;
import ar.com.ventas.estructuras.Categoria;
import ar.com.ventas.services.AbonoService;
import ar.com.ventas.services.ClienteService;
import ar.com.ventas.services.FacturaService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mario
 */
public class UtilFactura {

    public static String saveFactura(Cliente cliente, Abono abono, List<RenglonAbono> renglones, Date da1, Date da2, Date da3, Date da4, Integer ps) {
        TitularCuit tc = cliente.getTitular();
        String codigoCategoria = tc.getCategoria();
        Rubro rubro = abono.getRubro();
//        System.out.println(codigoCategoria);
        String letra = Categoria.getLetraByCode(codigoCategoria);
//        System.out.println(letra);
//        System.exit(0);
        String resultado = "N";
//        System.out.println(letra);
//        System.exit(0);
        if (letra.equals("C")) {
            NuevoCae nuevoCae = UtilAfip.getNuevoCaeFcC(cliente, abono, da1, da2, da3, da4, ps);
//        TicketTime tt = UtilAfip.solicitarNuevoTicket(titu, certif, llave);
//        int ufc = UtilAfip.getUltimaFcC(ptoVta, cui1, tt.getToken(), tt.getSign());
//        int unc = UtilAfip.getUltimaNcC(ptoVta, cui1, tt.getToken(), tt.getSign());
            if (nuevoCae != null) {
                String aceptado = nuevoCae.getEstado();
                if (aceptado.equals("A")) {
                    Factura fc = new Factura();
                    fc.setCliente(cliente);
                    fc.setFecha(da1);
                    fc.setCae(nuevoCae.getCae());
                    fc.setFechaVencimCae(nuevoCae.getFechaCae());
                    fc.setImporte(abono.getImporte());
                    fc.setSucursal(cliente.getTitular().getSucursal());
                    fc.setAbonoDesde(da2);
                    fc.setAbonoHasta(da3);
                    fc.setVencimientoFactura(da4);
                    fc.setNumero(nuevoCae.getNumero());
                    fc.setProductoServicio(ps);
                    fc.setTipoDoc(11);
                    fc.setRubro(rubro);
                    List<RenglonFactura> renglonesFc = new ArrayList<>();
                    for(RenglonAbono ra:renglones){
                        RenglonFactura rf = new RenglonFactura();
                        rf.setFactura(fc);
                        rf.setImporte(ra.getImporte());
                        rf.setOrden(ra.getOrden());
                        rf.setTexto(ra.getTexto());
                        renglonesFc.add(rf);
                    }
                    try {
                        new FacturaService().saveFacturaCompleta(fc, renglonesFc);
                        new AbonoService().updateAbono(abono);
                        resultado = "A";
                    } catch (Exception ex) {
                        Logger.getLogger(UtilFactura.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return resultado;
    }
}
