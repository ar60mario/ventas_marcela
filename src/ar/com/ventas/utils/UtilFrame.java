/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.utils;

import ar.com.ventas.entities.Factura;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario
 */
public class UtilFrame {

    private static final SimpleDateFormat sdf_qr = new SimpleDateFormat("yyyy-MM-dd");
    private static final DecimalFormat df = new DecimalFormat("#0.00");

    public static JTable limpiarTabla(JTable tabla) {
        int rows = tabla.getRowCount();
        if (rows > 0) {
            DefaultTableModel tbl = (DefaultTableModel) tabla.getModel();
            for (int i = 0; i < rows; i++) {
                tbl.removeRow(0);
            }
            tabla.setModel(tbl);
        }
        return tabla;
    }

    public static Date getFechaMesSiguiente(Date fecha){
        Date fechaSiguiente = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        if(dia > 28){
            dia = 28;
            cal.set(cal.DAY_OF_MONTH, dia);
        }
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1);
        fechaSiguiente = cal.getTime();
        return fechaSiguiente;
    }
    
    public static Date getFecha1Abono(Date fecha){
        Date fecha1;
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
//        int dia1 = cal.get(Calendar.DAY_OF_MONTH);
//        if(dia > 28){
//            dia = 28;
            cal.set(cal.DAY_OF_MONTH, 1);
//        }
//        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1);
        fecha1 = cal.getTime();
        return fecha1;
    }
    
    public static Date getFecha2Abono(Date fecha){
        Date fecha2;
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
//        int dia1 = cal.get(Calendar.DAY_OF_MONTH);
//        if(dia > 28){
//            dia = 28; cal.getActualMaximum(Calendar.DAY_OF_MONTH)
            cal.set(cal.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
//        }
//        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1);
        fecha2 = cal.getTime();
        return fecha2;
    }
    
    public static String generaQr(Factura fc) {
        String cuitTitular = fc.getCliente().getTitular().getCuit();
        String priT = cuitTitular.substring(0, 2);
        String medT = cuitTitular.substring(3, 11);
        String finT = cuitTitular.substring(12, 13);
        String data;
        String ver_qr = "1";
        Date fecha = fc.getFecha();
        String cuit_qr = priT + medT + finT;
        String cui = fc.getCliente().getCuit();
        String modeloFcPapel = fc.getTipoDoc().toString();
        String pri = "";
        String med = "";
        String fin = "";
        int lgo = cui.length();
        if (lgo != 13) {
            cui = "0000000000000" + cui;
            int lgo1 = cui.length();
            fin = cui.substring(lgo1 - 11, lgo1);
        }
        if (lgo > 11) {
            pri = cui.substring(0, 2);
            med = cui.substring(3, 11);
            fin = cui.substring(12, 13);
        }
        String fecha_qr = sdf_qr.format(fecha);
        String numeroDoc_qr = pri + med + fin;
        String tipoComprobante_qr = modeloFcPapel;
        String numeroComprobante_qr = fc.getNumero().toString();
        String importe_qr = df.format(fc.getImporte()).replace(",", ".");
        String tipoDoc_qr = fc.getCliente().getTipoDoc().toString();
        String nroCae_qr = fc.getCae().toString();
        String puntoVenta_qr = fc.getSucursal().toString();
        String moneda_qr = "PES";
        String cotiz_qr = "1";
        String tipoCodigoAutoriz_qr = "E";
        data = "{\"ver\":" + ver_qr
                + ",\"fecha\":\"" + fecha_qr + "\""
                + ",\"cuit\":" + cuit_qr
                + ",\"ptoVta\":" + puntoVenta_qr
                + ",\"tipoCmp\":" + tipoComprobante_qr
                + ",\"nroCmp\":" + numeroComprobante_qr
                + ",\"importe\":" + importe_qr
                + ",\"moneda\":\"" + moneda_qr + "\""
                + ",\"ctz\":" + cotiz_qr
                + ",\"tipoDocRec\":" + tipoDoc_qr
                + ",\"nroDocRec\":" + numeroDoc_qr
                + ",\"tipoCodAut\":\"" + tipoCodigoAutoriz_qr + "\""
                + ",\"codAut\":" + nroCae_qr + "}";
        System.out.println(data);
//        System.exit(0);
        return data;
    }

    public static String decoder(File file) throws Exception {

        FileInputStream inputStream = new FileInputStream(file);

        BufferedImage image = ImageIO.read(inputStream);

        int width = image.getWidth();
        int height = image.getHeight();
        int[] pixels = new int[width * height];

        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        // decode the barcode
        QRCodeReader reader = new QRCodeReader();
        Result result = reader.decode(bitmap);
        return new String(result.getText());
    }

    public static JTable agregarLinea(JTable tabla, int cols) {
        DefaultTableModel tbl = (DefaultTableModel) tabla.getModel();
        Object o[] = new Object[cols];
        for (int i = 0; i < cols; i++) {
            o[i] = "";
        }
        tbl.addRow(o);
        tabla.setModel(tbl);
        return tabla;
    }

    public static JTable borrarLinea(JTable tabla, int row) {
        DefaultTableModel tbl = (DefaultTableModel) tabla.getModel();
        tbl.removeRow(row);
        tabla.setModel(tbl);
        return tabla;
    }
}
