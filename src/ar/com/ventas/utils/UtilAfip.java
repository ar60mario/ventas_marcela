/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.utils;

import ar.com.ventas.entities.Abono;
import ar.com.ventas.entities.CertificadosAfip;
import ar.com.ventas.entities.Cliente;
import ar.com.ventas.entities.NuevoCae;
import ar.com.ventas.entities.TicketTime;
import ar.com.ventas.entities.TitularCuit;
import ar.com.ventas.services.CertificadosAfipService;
import ar.com.ventas.services.TicketTimeService;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.LibraryLoader;
import com.jacob.com.Variant;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario
 */
public class UtilAfip {

    private static final SimpleDateFormat sdf_qr = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat fechaWs = new SimpleDateFormat("yyyyMMdd");
    private static final DecimalFormat df = new DecimalFormat("#0.00");
    public static String wsdl = "https://wsaa.afip.gov.ar/ws/services/LoginCms";
    public static final String wsd2 = "https://servicios1.afip.gov.ar/wsfev1/service.asmx?WSDL"; // produccion
    public static ActiveXComponent wsaa = new ActiveXComponent("WSAA");
    public static ActiveXComponent wsfev1 = new ActiveXComponent("WSFEv1");

    public static NuevoCae testNuevoCae(Cliente cliente, Abono abono, Date f1, Date f2, Date f3) {
        NuevoCae nc = new NuevoCae();
        nc.setCae(Long.MIN_VALUE);
        nc.setErrMsj(wsdl);
        nc.setEstado(wsdl);
        nc.setExcepcion(wsdl);
        nc.setFechaCae(f3);
        nc.setMotivo(wsdl);
        nc.setNumero(Integer.SIZE);
        nc.setObservaciones(wsdl);
        nc.setSucursal(Integer.SIZE);

        nc.setCae(12345678901234L);
        nc.setEstado("A");
        nc.setFechaCae(f3);
        nc.setErrMsj(wsdl);
        nc.setEstado(wsdl);
        nc.setMotivo("M");
        nc.setNumero(1);
        nc.setSucursal(cliente.getTitular().getSucursal());
        return nc;
    }

    public static NuevoCae getNuevoCaeFcC(Cliente cliente, Abono abono, Date da1, Date da2, Date da3, Date da4, Integer ps) {
        NuevoCae nc = null;
        TitularCuit titular = cliente.getTitular();
        String ptoVta = titular.getSucursal().toString();

        String concepto = ps.toString();// producto y servicio
        String cuiCli = cliente.getCuit();
        String cuiTit = titular.getCuit();
        String tipo_cbte = "11";
        if (cuiCli.length() != 13) {
            JOptionPane.showMessageDialog(null, "VERIFIQUE EL CUIT CLIENTE, DEBE TENER GUIONES");
            return nc;
        }
        if (cuiTit.length() == 0) {
            JOptionPane.showMessageDialog(null, "VERIFIQUE EL CUIT TITULAR, NO DEBE ESTAR VACIO");
            return nc;
        }
        String tipo_doc = String.valueOf(cliente.getTipoDoc());
        String nro_doc_cliente = cuiCli.substring(0, 2) + cuiCli.substring(3, 11) + cuiCli.substring(12, 13);
        String nro_doc_titular = cuiTit.substring(0, 2) + cuiTit.substring(3, 11) + cuiTit.substring(12, 13);
        CertificadosAfip certificado = null;
        try {
            certificado = new CertificadosAfipService().getCertificadoByTitular(titular);
        } catch (Exception ex) {
            Logger.getLogger(UtilAfip.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "NO SE PUEDE OBTENER CERTIFICADOS AFIP");
            return nc;
        }
        if (certificado != null) {
            String certif = certificado.getCertificado();
            String llave = certificado.getLlave();
            TicketTime tt = UtilAfip.solicitarNuevoTicket(titular, certif, llave);
            String tkn = tt.getToken();
            String sgn = tt.getSign();
            Integer cbte_nro = UtilAfip.getUltimaFcC(ptoVta, nro_doc_titular, tkn, sgn);
            cbte_nro += 1;
            int cbt_desde = cbte_nro, cbt_hasta = cbte_nro;
            Double importeAbono = abono.getImporte();
            String imp_total = df.format(importeAbono).replaceAll("\\,", "\\.");//"124.00";
            String imp_tot_conc = "0.00";
            String imp_neto = imp_total;
            String imp_iva = "0.00";
            String imp_trib = "0.00", imp_op_ex = "0";
            String fecha_cbte = fechaWs.format(da1);
            String fecha_venc_pago = fechaWs.format(da4);
            String fecha_serv_desde = fechaWs.format(da2), fecha_serv_hasta = fechaWs.format(da3);
            String moneda_id = "PES", moneda_ctz = "1.000";
//            System.out.println("numero:");
//            System.out.println(cbte_nro);
//            System.exit(0);
            int xxx = 1;
            if (xxx != 0) {
                Variant ok = Dispatch.call(wsfev1, "CrearFactura",
                        new Variant(concepto), new Variant(tipo_doc),
                        new Variant(nro_doc_cliente), new Variant(tipo_cbte),
                        new Variant(ptoVta),
                        new Variant(cbt_desde), new Variant(cbt_hasta),
                        new Variant(imp_total), new Variant(imp_tot_conc),
                        new Variant(imp_neto), new Variant(imp_iva),
                        new Variant(imp_trib), new Variant(imp_op_ex),
                        new Variant(fecha_cbte), new Variant(fecha_venc_pago),
                        new Variant(fecha_serv_desde), new Variant(fecha_serv_hasta),
                        new Variant(moneda_id), new Variant(moneda_ctz));
                System.out.println(concepto);
                System.out.println(tipo_doc);
                System.out.println(nro_doc_cliente);
                System.out.println(tipo_cbte);
                System.out.println(ptoVta);
                System.out.println(cbt_desde);
                System.out.println(cbt_hasta);
                System.out.println(imp_total);
                System.out.println(imp_tot_conc);
                System.out.println(imp_neto);
                System.out.println(imp_iva);
                System.out.println(imp_trib);
                System.out.println(imp_op_ex);
                System.out.println(fecha_cbte);
////                if (internos > 0) {
////                    Variant tributo_id = new Variant(99),
////                            tributo_desc = new Variant("Otros Impuestos"),
////                            tributo_base_imp = new Variant("0.00"),
////                            tributo_alic = new Variant("0.00"),
////                            tributo_importe = new Variant(imp_trib);
////                    Dispatch.call(wsfev1, "AgregarTributo",
////                            tributo_id, tributo_desc, tributo_base_imp,
////                            tributo_alic, tributo_importe);
////                }
////                Variant iva_id = new Variant(5),
////                        iva_base_imp = new Variant(imp_neto),
////                        iva_importe = new Variant(imp_iva);
////                Dispatch.call(wsfev1, "AgregarIva",
////                        iva_id, iva_base_imp, iva_importe);
                Dispatch.put(wsfev1, "Reprocesar", new Variant(true));
                Variant cae = Dispatch.call(wsfev1, "CAESolicitar");
                String requ = Dispatch.get(wsfev1, "XmlRequest").toString();
                String resp = Dispatch.get(wsfev1, "XmlResponse").toString();
                String excepcion = Dispatch.get(wsfev1, "Excepcion").toString();
                String errmsg = Dispatch.get(wsfev1, "ErrMsg").toString();
                String obs = Dispatch.get(wsfev1, "Obs").toString();
                String vto = Dispatch.get(wsfev1, "Vencimiento").toString();
                SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
                Date caeVencim = new Date();
                String resultado = Dispatch.get(wsfev1, "Resultado").toString();
                if (!resultado.equals("A")) {
                    JOptionPane.showMessageDialog(null, "Obs: " + obs + "\nError: " + errmsg);
                    nc = null;
                    return nc;
                }
                if (vto != "" && vto != null) {
                    try {
                        caeVencim = sd.parse(vto);
                    } catch (ParseException ex) {
                        Logger.getLogger(UtilAfip.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String vencCae = vto.substring(6, 8) + "/" + vto.substring(4, 6) + "/" + vto.substring(0, 4);
                }
                Long caeLong = Long.valueOf(cae.toString());

                nc = new NuevoCae();

                nc.setCae(caeLong);
                nc.setErrMsj(errmsg);
                nc.setEstado(resultado);
                nc.setExcepcion(excepcion);
                nc.setFechaCae(caeVencim);
                nc.setMotivo("");
                nc.setNumero(cbte_nro);
                nc.setObservaciones(obs);
                nc.setSucursal(cliente.getTitular().getSucursal());

                String ruta1 = "c:/ventasL/cmprbt/" + tipo_cbte
                        + "C" + ptoVta
                        + cbt_desde + ".xm1";
                String ruta2 = "c:/ventasL/cmprbt/" + tipo_cbte
                        + "C" + ptoVta
                        + cbt_desde + ".xm2";
                File archivo1 = new File(ruta1);
                File archivo2 = new File(ruta2);
                BufferedWriter bw1, bw2;
                try {
                    bw1 = new BufferedWriter(new FileWriter(archivo1));
                    bw2 = new BufferedWriter(new FileWriter(archivo2));
                    bw1.write(requ);
                    bw2.write(resp);
                    bw1.close();
                    bw2.close();
                } catch (IOException ex) {
                    Logger.getLogger(UtilAfip.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "ERROR GRABANDO ARCHIVOS XML");
                    return nc;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "ERRORR 94 - EN CERTIFICADOS");
            return nc;
        }
        return nc;
        /*
        int  = Integer.parseInt(ult.toString()) + 1,
                                
                        numeroFacturaPapel = String.valueOf(cbte_nro);
                        numf = cbte_nro;
                        comprobanteNumero = cbte_nro;
                        int largo = ("00000000" + numeroFacturaPapel).length();
                        numeroFacturaPapel = ("00000000" + numeroFacturaPapel).substring(largo - 8, largo);
                        
                        
                        String imp_tot_conc = "0.00";
                        String imp_neto = df.format(f.getGravado()).toString().replaceAll("\\,", "\\.");
                        String imp_iva = df.format(f.getIva()).toString().replaceAll("\\,", "\\.");
                        int internos = (int) rint(f.getImpuesto() * 100);
                        String imp_trib = "", imp_op_ex = "0";
                        if (internos > 0) {
                            imp_trib = df.format(f.getImpuesto()).toString().replaceAll("\\,", "\\.");
                        } else {
                            imp_trib = "0.00";
                        }
                        String fecha_cbte = fechaWs, fecha_venc_pago = "";
                        String fecha_serv_desde = "", fecha_serv_hasta = "";
                        String moneda_id = "PES", moneda_ctz = "1.000";

                        int xxx = 1;
                        if (xxx != 0) {
                            Variant ok = Dispatch.call(wsfev1, "CrearFactura",
                                    new Variant(concepto), new Variant(tipo_doc),
                                    new Variant(nro_doc), new Variant(tipo_cbte),
                                    new Variant(pto_vta),
                                    new Variant(cbt_desde), new Variant(cbt_hasta),
                                    new Variant(imp_total), new Variant(imp_tot_conc),
                                    new Variant(imp_neto), new Variant(imp_iva),
                                    new Variant(imp_trib), new Variant(imp_op_ex),
                                    new Variant(fecha_cbte), new Variant(fecha_venc_pago),
                                    new Variant(fecha_serv_desde), new Variant(fecha_serv_hasta),
                                    new Variant(moneda_id), new Variant(moneda_ctz));
                            if (internos > 0) {
                                Variant tributo_id = new Variant(99),
                                        tributo_desc = new Variant("Otros Impuestos"),
                                        tributo_base_imp = new Variant("0.00"),
                                        tributo_alic = new Variant("0.00"),
                                        tributo_importe = new Variant(imp_trib);
                                Dispatch.call(wsfev1, "AgregarTributo",
                                        tributo_id, tributo_desc, tributo_base_imp,
                                        tributo_alic, tributo_importe);
                            }
                            Variant iva_id = new Variant(5),
                                    iva_base_imp = new Variant(imp_neto),
                                    iva_importe = new Variant(imp_iva);
                            Dispatch.call(wsfev1, "AgregarIva",
                                    iva_id, iva_base_imp, iva_importe);
                            Dispatch.put(wsfev1, "Reprocesar", new Variant(false));
                            Variant cae = Dispatch.call(wsfev1, "CAESolicitar");
                            String requ = Dispatch.get(wsfev1, "XmlRequest").toString();
                            String resp = Dispatch.get(wsfev1, "XmlResponse").toString();
                            excepcion = Dispatch.get(wsfev1, "Excepcion").toString();
                            String errmsg = Dispatch.get(wsfev1, "ErrMsg").toString();
                            String obs = Dispatch.get(wsfev1, "Obs").toString();
                            String vto = Dispatch.get(wsfev1, "Vencimiento").toString();
                            SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
                            //caeVencim = sd.parse(vto);
                            String resultado = Dispatch.get(wsfev1, "Resultado").toString();
                            if (!resultado.equals("A")) {
                                JOptionPane.showMessageDialog(this, "Obs: " + obs + "\nError: " + errmsg);
                                return;
                            }
                            if (vto != "" && vto != null) {
                                caeVencim = sd.parse(vto);
                                vencCae = vto.substring(6, 8) + "/" + vto.substring(4, 6) + "/" + vto.substring(0, 4);
                            }
                            caeLong = Long.valueOf(cae.toString());
                            String ruta1 = "c:/ventas/compmartin/" + tipoComprob
                                    + letraFacturaPapel + sucursalFacturaPapel
                                    + numeroFacturaPapel + ".xm1";
                            String ruta2 = "c:/ventas/compmartin/" + tipoComprob
                                    + letraFacturaPapel + sucursalFacturaPapel
                                    + numeroFacturaPapel + ".xm2";
                            File archivo1 = new File(ruta1);
                            File archivo2 = new File(ruta2);
                            BufferedWriter bw1, bw2;
                            bw1 = new BufferedWriter(new FileWriter(archivo1));
                            bw2 = new BufferedWriter(new FileWriter(archivo2));
                            bw1.write(requ);
                            bw2.write(resp);
                            bw1.close();
                            bw2.close();
                        }
         */
//        return nc;
    }

    public static String getVersionPyAfipWS() {
        LibraryLoader.loadJacobLibrary();
        ActiveXComponent wsaa = new ActiveXComponent("WSAA");
        String vers = "Carpeta: >>> " + Dispatch.get(wsaa, "InstallDir").toString()
                + "  -  Versión: >>> "
                + Dispatch.get(wsaa, "Version").toString();
        return vers;
    }

//    public static TicketTime getTokenAfip(TitularCuit titu, String certif, String llave, String cuit, String tipoInscripcion) {
//        TicketTime ticketTime = null;
//        TicketTime newTicketTime = new TicketTime();
//        try {
//            ticketTime = new TicketTimeService().getTicketByTitularCuit(titu);
//            //ew TicketTime();
//        } catch (Exception ex) {
//            Logger.getLogger(UtilAfip.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        Calendar cal = Calendar.getInstance();
//        Date fecha = cal.getTime();
//        int hora = cal.get(Calendar.HOUR_OF_DAY);
//        int minutos = cal.get(Calendar.MINUTE);
//        if (ticketTime != null) {
//            if (fecha != ticketTime.getFecha()) {
//                newTicketTime = solicitarNuevoTicket(titu, certif, llave);
//            } else if (hora != ticketTime.getHora()) {
//                if (hora == ticketTime.getHora() + 1) {
//                    if (minutos > ticketTime.getMinuto()) {
//                        newTicketTime = solicitarNuevoTicket(titu, certif, llave);
//                    } else {
//                        int xMinuto = 60 - ticketTime.getMinuto();
//                        if ((xMinuto + minutos) > 30) {
//                            newTicketTime = solicitarNuevoTicket(titu, certif, llave);
//                        }
//                        newTicketTime = ticketTime;
//                    }
//                } else {
//                    newTicketTime = solicitarNuevoTicket(titu, certif, llave);
//                }
//            } else if (minutos - ticketTime.getMinuto() > 30) {
//                newTicketTime = solicitarNuevoTicket(titu, certif, llave);
//            }
//        } else {
//            newTicketTime = solicitarNuevoTicket2(titu, certif, llave);
//        }
//        return newTicketTime;
//        try {
//            LibraryLoader.loadJacobLibrary();
//            Dispatch.call(wsaa, "Autenticar",
//                    new Variant("wsfe"),
//                    new Variant(certif),
//                    new Variant(llave),
//                    new Variant(wsdl));
//            String excepcion = Dispatch.get(wsaa, "Excepcion").toString();
//            String token = Dispatch.get(wsaa, "Token").toString();
//            String sign = Dispatch.get(wsaa, "Sign").toString();
//            Dispatch.put(wsfev1, "Cuit", new Variant(cuit));
//            Dispatch.put(wsfev1, "Token", new Variant(token));
//            Dispatch.put(wsfev1, "Sign", new Variant(sign));
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//            e.printStackTrace();
//            return null;
//        }

    /*
        try {
            LibraryLoader.loadJacobLibrary();
            ActiveXComponent wsaa = new ActiveXComponent("WSAA");
            
            String userdir = "c:/certifmario";
            Dispatch.call(wsaa, "Autenticar",
                    new Variant("wsfe"),
                    new Variant(certif),
                    new Variant(llave),
                    new Variant(wsdl));
            String excepcion = Dispatch.get(wsaa, "Excepcion").toString();
            String token = Dispatch.get(wsaa, "Token").toString();
            String sign = Dispatch.get(wsaa, "Sign").toString();
            JOptionPane.showMessageDialog(this, "Ver");
            ActiveXComponent wsfev1 = new ActiveXComponent("WSFEv1");
            Dispatch.put(wsfev1, "Cuit", new Variant(cuit));
            Dispatch.put(wsfev1, "Token", new Variant(token));
            Dispatch.put(wsfev1, "Sign", new Variant(sign));
            String cache = "";
           // wsdl = 
            Dispatch.call(wsfev1, "Conectar",
                    new Variant(cache),
                    new Variant(wsdl)
            );
            String tipo_cbte = "1";
            tipo_cbte = "11"; //Factura C
            String pto_vta = "6"; // Sucursal declarada WS
            Variant ult = Dispatch.call(wsfev1, "CompUltimoAutorizado",
                    new Variant(tipo_cbte),
                    new Variant(pto_vta));
            excepcion = Dispatch.get(wsfev1, "Excepcion").toString();
            fcAfipTxt.setText(ult.toString());
            tipo_cbte = "13"; //Nota Credito C
            pto_vta = "6"; // Sucursal declarada WS
            ult = Dispatch.call(wsfev1, "CompUltimoAutorizado",
                    new Variant(tipo_cbte),
                    new Variant(pto_vta));
            excepcion = Dispatch.get(wsfev1, "Excepcion").toString();
            ncTxt.setText(ult.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
            e.printStackTrace();
            return;
        }
        Configuracion co = null;
        try {
            co = new ConfiguracionService().getConfiguracionById(1L);
        } catch (Exception ex) {
            Logger.getLogger(TestAfipFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(co!=null){
            facturaTxt.setText(df.format(co.getNumeroFc()));
        }
     */
//        return ticketTime;
//    }
    public static TicketTime solicitarNuevoTicket(TitularCuit titular, String certif, String llave) {
        TicketTime tkt = null;
        Date fecha = new Date();
        try {
            tkt = new TicketTimeService().getTicketByTitularCuit(titular);
        } catch (Exception ex) {
            Logger.getLogger(UtilAfip.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (tkt == null) {
            tkt = generarTicket(certif, llave);
            tkt.setTitular(titular);
            System.out.println(tkt.getToken());
            System.out.println(tkt.getSign());
            System.out.println(tkt.getException());
            try {
                new TicketTimeService().saveTicket(tkt);
                return tkt;
            } catch (Exception ex) {
                Logger.getLogger(UtilAfip.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "No se pudo guardar nuevo Ticket");
            }
        } else {
            if (fecha != tkt.getFecha()) {
                TicketTime tkt2 = generarTicket(certif, llave);
                tkt.setSign(tkt2.getSign());
                tkt.setException(tkt2.getException());
                tkt.setHora(tkt2.getHora());
                tkt.setMinuto(tkt2.getMinuto());
                tkt.setSegundo(tkt2.getSegundo());
                tkt.setToken(tkt2.getToken());
                System.out.println(tkt.getToken());
                System.out.println(tkt.getSign());
                System.out.println(tkt.getException());
                try {
                    new TicketTimeService().updateTicket(tkt);
                    return tkt;
                } catch (Exception ex) {
                    Logger.getLogger(UtilAfip.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "No se pudo guardar nuevo Ticket");
                }
            } else {
                Calendar ca1 = Calendar.getInstance();
                int hs1 = ca1.get(Calendar.HOUR_OF_DAY);
                int hs = hs1 * 100;
                int mi1 = ca1.get(Calendar.MINUTE);
                int ho_mi_1 = hs + mi1;
                int hs2 = tkt.getHora();
                int hs_1 = hs2 * 100;
                int mi2 = tkt.getMinuto();
                int ho_mi_2 = hs_1 + mi2;
                int tiempo = ho_mi_1 - ho_mi_2;
                if (tiempo > 30) {
                    TicketTime tkt2 = generarTicket(certif, llave);
                    tkt.setSign(tkt2.getSign());
                    tkt.setException(tkt2.getException());
                    tkt.setHora(tkt2.getHora());
                    tkt.setMinuto(tkt2.getMinuto());
                    tkt.setSegundo(tkt2.getSegundo());
                    tkt.setToken(tkt2.getToken());
                    System.out.println(tkt.getToken());
                    System.out.println(tkt.getSign());
                    System.out.println(tkt.getException());
                    try {
                        new TicketTimeService().updateTicket(tkt);
                        return tkt;
                    } catch (Exception ex) {
                        Logger.getLogger(UtilAfip.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, "No se pudo guardar nuevo Ticket");
                        return null;
                    }
                } else {
                    return tkt;
                }
            }
        }
        return null;
    }

    private static TicketTime generarTicket(String certif, String llave) {
        TicketTime tt1 = new TicketTime();
        Date fecha = new Date();
        int hora = 0;
        int minutos = 0;
        int segundos = 0;
        Dispatch.call(wsaa, "Autenticar",
                new Variant("wsfe"),
                new Variant(certif),
                new Variant(llave),
                new Variant(wsdl));
        String excepcion = Dispatch.get(wsaa, "Excepcion").toString();
        String token = Dispatch.get(wsaa, "Token").toString();
        String sign = Dispatch.get(wsaa, "Sign").toString();
        tt1.setFecha(fecha);
        tt1.setHora(hora);
        tt1.setMinuto(minutos);
        tt1.setSegundo(segundos);
        tt1.setToken(token);
        tt1.setSign(sign);
        tt1.setException(excepcion);
        return tt1;
    }

    public static Integer getUltimaFcA(String cui1, String token, String sign) {
        Integer fcA = 0;

        return fcA;
    }

    public static Integer getUltimaFcB(String cui1, String token, String sign) {
        int fcB = 0;
        return fcB;
    }

    public static Integer getUltimaFcC(String ptoVta, String cui1, String token, String sign) {
        int fc;
        Dispatch.put(wsfev1, "Cuit", new Variant(cui1));
        Dispatch.put(wsfev1, "Token", new Variant(token));
        Dispatch.put(wsfev1, "Sign", new Variant(sign));
        String cache = "";
        Dispatch.call(wsfev1, "Conectar",
                new Variant(cache),
                new Variant(wsd2)
        );
        String tipo_cbte = "11"; // FACTURAS
        Variant ult = Dispatch.call(wsfev1, "CompUltimoAutorizado",
                new Variant(tipo_cbte),
                new Variant(ptoVta));
//        String excepcion = Dispatch.get(wsfev1, "Excepcion").toString();
        fc = Integer.valueOf(ult.toString());
        return fc;
    }

    public static Integer getUltimaNcC(String ptoVta, String cui1, String token, String sign) {
        int nc = 0;
        Dispatch.put(wsfev1, "Cuit", new Variant(cui1));
        Dispatch.put(wsfev1, "Token", new Variant(token));
        Dispatch.put(wsfev1, "Sign", new Variant(sign));
        String cache = "";
        Dispatch.call(wsfev1, "Conectar",
                new Variant(cache),
                new Variant(wsd2)
        );
        String tipo_cbte = "13"; // NOTA DE CREDITO
        Variant ult = Dispatch.call(wsfev1, "CompUltimoAutorizado",
                new Variant(tipo_cbte),
                new Variant(ptoVta));
        nc = Integer.valueOf(ult.toString());
        return nc;
    }

    /*
    if (fecha != tkt.getFecha()) {
                            solicitarNuevoTicket();
                        } else if (hora != tkt.getHora()) {
                            if (hora == tkt.getHora() + 1) {
                                if (minutos > tkt.getMinuto()) {
                                    solicitarNuevoTicket();
                                } else {
                                    int xMinuto = 60 - tkt.getMinuto();
                                    if ((xMinuto + minutos) > 30) {
                                        solicitarNuevoTicket();
                                    }
                                }
                            } else {
                                solicitarNuevoTicket();
                            }
                        } else if (minutos - tkt.getMinuto() > 30) {
                            solicitarNuevoTicket();
                        }
                        ActiveXComponent wsfev1 = new ActiveXComponent("WSFEv1");
                        Dispatch.put(wsfev1, "Cuit", new Variant("20269118122"));
                        Dispatch.put(wsfev1, "Token", new Variant(token));
                        Dispatch.put(wsfev1, "Sign", new Variant(sign));
                        String cache = "";
                        wsdl = "https://servicios1.afip.gov.ar/wsfev1/service.asmx?WSDL";
                        Dispatch.call(wsfev1, "Conectar",
                                new Variant(cache),
                                new Variant(wsdl)
                        );
                        String tipo_cbte = "6";
                        tipoComprob = tipo_cbte;
                        String pto_vta = String.valueOf(suc); // Sucursal declarada WS
                        sucursalFacturaPapel = "000" + pto_vta;
                        Variant ult = Dispatch.call(wsfev1, "CompUltimoAutorizado",
                                new Variant(tipo_cbte),
                                new Variant(pto_vta));
                        excepcion = Dispatch.get(wsfev1, "Excepcion").toString();
//                        System.out.println(wsfev1);
//                        //JOptionPane.showMessageDialog(this, "Ult.Comprb." + ult.toString());
//                        System.out.println("Ult.Comprb." + ult.toString());
                        numf = Integer.valueOf(ult.toString());
                        comprobanteNumero = Integer.parseInt(ult.toString());
                        if (numf != comprobanteNumero) {
                            JOptionPane.showMessageDialog(this, "No coinciden los numeros");
                            return;
                        }
//                        System.out.println(numf);
//                        System.exit(0);
                        String fechaWs = new SimpleDateFormat("yyyyMMdd").format(fws);
                        String concepto = "1";// producto 
                        String cui = cli.getCuit();
                        String cuit1 = cui.substring(0, 2) + cui.substring(3, 11) + cui.substring(12, 13);
                        String tipoD = String.valueOf(cli.getTipo());
                        String tipo_doc = tipoD, nro_doc = cuit1; //tipo y numero
                        int cbte_nro = Integer.parseInt(ult.toString()) + 1,
                                cbt_desde = cbte_nro,
                                cbt_hasta = cbte_nro;
                        numeroFacturaPapel = String.valueOf(cbte_nro);
                        numf = cbte_nro;
                        comprobanteNumero = cbte_nro;
                        int largo = ("00000000" + numeroFacturaPapel).length();
                        numeroFacturaPapel = ("00000000" + numeroFacturaPapel).substring(largo - 8, largo);
                        Double importeAbono = f.getTotal(); //100.50
                        String imp_total = df.format(importeAbono).toString().replaceAll("\\,", "\\.");//"124.00";
                        String imp_tot_conc = "0.00";
                        String imp_neto = df.format(f.getGravado()).toString().replaceAll("\\,", "\\.");
                        String imp_iva = df.format(f.getIva()).toString().replaceAll("\\,", "\\.");
                        int internos = (int) rint(f.getImpuesto() * 100);
                        String imp_trib = "", imp_op_ex = "0";
                        if (internos > 0) {
                            imp_trib = df.format(f.getImpuesto()).toString().replaceAll("\\,", "\\.");
                        } else {
                            imp_trib = "0.00";
                        }
                        String fecha_cbte = fechaWs, fecha_venc_pago = "";
                        String fecha_serv_desde = "", fecha_serv_hasta = "";
                        String moneda_id = "PES", moneda_ctz = "1.000";

                        int xxx = 1;
                        if (xxx != 0) {
                            Variant ok = Dispatch.call(wsfev1, "CrearFactura",
                                    new Variant(concepto), new Variant(tipo_doc),
                                    new Variant(nro_doc), new Variant(tipo_cbte),
                                    new Variant(pto_vta),
                                    new Variant(cbt_desde), new Variant(cbt_hasta),
                                    new Variant(imp_total), new Variant(imp_tot_conc),
                                    new Variant(imp_neto), new Variant(imp_iva),
                                    new Variant(imp_trib), new Variant(imp_op_ex),
                                    new Variant(fecha_cbte), new Variant(fecha_venc_pago),
                                    new Variant(fecha_serv_desde), new Variant(fecha_serv_hasta),
                                    new Variant(moneda_id), new Variant(moneda_ctz));
                            if (internos > 0) {
                                Variant tributo_id = new Variant(99),
                                        tributo_desc = new Variant("Otros Impuestos"),
                                        tributo_base_imp = new Variant("0.00"),
                                        tributo_alic = new Variant("0.00"),
                                        tributo_importe = new Variant(imp_trib);
                                Dispatch.call(wsfev1, "AgregarTributo",
                                        tributo_id, tributo_desc, tributo_base_imp,
                                        tributo_alic, tributo_importe);
                            }
                            Variant iva_id = new Variant(5),
                                    iva_base_imp = new Variant(imp_neto),
                                    iva_importe = new Variant(imp_iva);
                            Dispatch.call(wsfev1, "AgregarIva",
                                    iva_id, iva_base_imp, iva_importe);
                            Dispatch.put(wsfev1, "Reprocesar", new Variant(false));
                            Variant cae = Dispatch.call(wsfev1, "CAESolicitar");
                            String requ = Dispatch.get(wsfev1, "XmlRequest").toString();
                            String resp = Dispatch.get(wsfev1, "XmlResponse").toString();
                            excepcion = Dispatch.get(wsfev1, "Excepcion").toString();
                            String errmsg = Dispatch.get(wsfev1, "ErrMsg").toString();
                            String obs = Dispatch.get(wsfev1, "Obs").toString();
                            String vto = Dispatch.get(wsfev1, "Vencimiento").toString();
                            SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
                            //caeVencim = sd.parse(vto);
                            String resultado = Dispatch.get(wsfev1, "Resultado").toString();
                            if (!resultado.equals("A")) {
                                JOptionPane.showMessageDialog(this, "Obs: " + obs + "\nError: " + errmsg);
                                return;
                            }
                            if (vto != "" && vto != null) {
                                caeVencim = sd.parse(vto);
                                vencCae = vto.substring(6, 8) + "/" + vto.substring(4, 6) + "/" + vto.substring(0, 4);
                            }
                            caeLong = Long.valueOf(cae.toString());
                            String ruta1 = "c:/ventas/compmartin/" + tipoComprob
                                    + letraFacturaPapel + sucursalFacturaPapel
                                    + numeroFacturaPapel + ".xm1";
                            String ruta2 = "c:/ventas/compmartin/" + tipoComprob
                                    + letraFacturaPapel + sucursalFacturaPapel
                                    + numeroFacturaPapel + ".xm2";
                            File archivo1 = new File(ruta1);
                            File archivo2 = new File(ruta2);
                            BufferedWriter bw1, bw2;
                            bw1 = new BufferedWriter(new FileWriter(archivo1));
                            bw2 = new BufferedWriter(new FileWriter(archivo2));
                            bw1.write(requ);
                            bw2.write(resp);
                            bw1.close();
                            bw2.close();
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
//                        e.printStackTrace();
                        return;
                    }
     */
    private static TicketTime solicitarNuevoTicket2(TitularCuit titu, String certif, String llave) {
        TicketTime tkt = null;
        String nameCrt = "";
        String nameKey = "";
        Date fecha = new Date();
        int hora = 0;
        int minutos = 0;
        int segundos = 0;
        try {
            tkt = new TicketTimeService().getTicketByTitularCuit(titu);
        } catch (Exception ex) {
            Logger.getLogger(UtilAfip.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (tkt == null) {
            tkt = new TicketTime();
            nameCrt = certif;
            nameKey = llave;
            Dispatch.call(wsaa, "Autenticar",
                    new Variant("wsfe"),
                    new Variant(nameCrt),
                    new Variant(nameKey),
                    new Variant(wsdl));
            String excepcion = Dispatch.get(wsaa, "Excepcion").toString();
//            System.out.println(excepcion);
//            System.out.println("LLEGO AQUI");
//            System.exit(0);

            String token = Dispatch.get(wsaa, "Token").toString();
            String sign = Dispatch.get(wsaa, "Sign").toString();
            tkt.setFecha(fecha);
            tkt.setHora(hora);
            tkt.setMinuto(minutos);
            tkt.setSegundo(segundos);
            tkt.setToken(token);
            tkt.setSign(sign);
            tkt.setTitular(titu);
            System.out.println(token);
            System.out.println(sign);
            try {
                tkt = new TicketTimeService().saveTicket(tkt);
                return tkt;
            } catch (Exception ex) {
                Logger.getLogger(UtilAfip.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "No se pudo guardar nuevo Ticket");
            }
        } else {
            if (fecha != tkt.getFecha()) {

            }
        }
        return tkt;
    }
}
