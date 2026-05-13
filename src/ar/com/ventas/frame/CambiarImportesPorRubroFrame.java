package ar.com.ventas.frame;

import ar.com.ventas.entities.Abono;
import ar.com.ventas.entities.Direccion;
import ar.com.ventas.entities.RenglonAbono;
import ar.com.ventas.entities.Rubro;
import ar.com.ventas.estructuras.Constantes;
import ar.com.ventas.services.AbonoService;
import ar.com.ventas.services.RenglonAbonoService;
import ar.com.ventas.services.RubroService;
import ar.com.ventas.utils.UtilFrame;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CambiarImportesPorRubroFrame extends javax.swing.JFrame {

    private List<Rubro> rubros;
    private List<Abono> abonos;
    private Rubro rubro;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private DecimalFormat df = new DecimalFormat("#0.00");

    public CambiarImportesPorRubroFrame() {
        initComponents();
        limpiarCampos();
        llenarCombo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        combo = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        porcentualRb = new javax.swing.JRadioButton();
        importeRb = new javax.swing.JRadioButton();
        aplicarBtn = new javax.swing.JButton();
        grabarBtn = new javax.swing.JButton();
        volverBtn = new javax.swing.JButton();
        importeTxt = new javax.swing.JTextField();
        sacarBtn = new javax.swing.JButton();
        importe2Txt = new javax.swing.JTextField();
        aplicar2Btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("MODIFICAR IMPORTE ABONO");

        jLabel1.setText("Rubro:");

        combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboActionPerformed(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CONSORCIO", "FCH CAMBIO", "IMPORTE"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        porcentualRb.setText("Porcentual");
        porcentualRb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porcentualRbActionPerformed(evt);
            }
        });

        importeRb.setText("Importe");
        importeRb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importeRbActionPerformed(evt);
            }
        });

        aplicarBtn.setText("Aplicar");
        aplicarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aplicarBtnActionPerformed(evt);
            }
        });

        grabarBtn.setText("Grabar");
        grabarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grabarBtnActionPerformed(evt);
            }
        });

        volverBtn.setText("Volver");
        volverBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBtnActionPerformed(evt);
            }
        });

        importeTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        importeTxt.setText("IMPORTE");

        sacarBtn.setText("Sacar");
        sacarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sacarBtnActionPerformed(evt);
            }
        });

        importe2Txt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        importe2Txt.setText("IMPORTE2");

        aplicar2Btn.setText("APLICAR");
        aplicar2Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aplicar2BtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(porcentualRb)
                        .addGap(18, 18, 18)
                        .addComponent(importeRb)
                        .addGap(18, 18, 18)
                        .addComponent(importeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(aplicarBtn)
                        .addGap(18, 18, 18)
                        .addComponent(sacarBtn)
                        .addGap(18, 18, 18)
                        .addComponent(grabarBtn)
                        .addGap(18, 18, 18)
                        .addComponent(importe2Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(aplicar2Btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 184, Short.MAX_VALUE)
                        .addComponent(volverBtn)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(volverBtn)
                    .addComponent(porcentualRb)
                    .addComponent(importeRb)
                    .addComponent(importeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aplicarBtn)
                    .addComponent(sacarBtn)
                    .addComponent(grabarBtn)
                    .addComponent(importe2Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aplicar2Btn))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboActionPerformed
        int row = combo.getSelectedIndex();
        if (row > 0) {
            buscar(row);
        }
    }//GEN-LAST:event_comboActionPerformed

    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBtnActionPerformed
        volver();
    }//GEN-LAST:event_volverBtnActionPerformed

    private void grabarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grabarBtnActionPerformed
        grabar();
    }//GEN-LAST:event_grabarBtnActionPerformed

    private void aplicarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aplicarBtnActionPerformed
        aplicar();
    }//GEN-LAST:event_aplicarBtnActionPerformed

    private void importeRbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importeRbActionPerformed
        importeRb.setSelected(true);
        porcentualRb.setSelected(false);
    }//GEN-LAST:event_importeRbActionPerformed

    private void porcentualRbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porcentualRbActionPerformed
        importeRb.setSelected(false);
        porcentualRb.setSelected(true);
    }//GEN-LAST:event_porcentualRbActionPerformed

    private void sacarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sacarBtnActionPerformed
        int row = tabla.getSelectedRow();
        int rows = tabla.getSelectedRowCount();

        if (row < 0) {
            JOptionPane.showMessageDialog(this, "DEBE SELECCIONAR UN ABONO");
            return;
        }
        if (rows > 1) {
            DefaultTableModel tbl = (DefaultTableModel) tabla.getModel();
//            int contadorRows = tabla.getRowCount();
            int a[] = tabla.getSelectedRows();
            for (int i = rows - 1; i > -1; i--) {
                tbl.removeRow(a[i]);
                abonos.remove(a[i]);
            }
            tabla.setModel(tbl);
        } else {
            sacar(row);
        }
    }//GEN-LAST:event_sacarBtnActionPerformed

    private void aplicar2BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aplicar2BtnActionPerformed
        int row = tabla.getSelectedRow();
        if (row < 0) {
            return;
        }
        Double impNue = Double.valueOf(importe2Txt.getText().replace(",", "."));
        Abono abo = abonos.get(row);
        abo.setImporte(impNue);
        llenarTabla(abonos);
    }//GEN-LAST:event_aplicar2BtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CambiarImportesPorRubroFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CambiarImportesPorRubroFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CambiarImportesPorRubroFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CambiarImportesPorRubroFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CambiarImportesPorRubroFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aplicar2Btn;
    private javax.swing.JButton aplicarBtn;
    private javax.swing.JComboBox<String> combo;
    private javax.swing.JButton grabarBtn;
    private javax.swing.JTextField importe2Txt;
    private javax.swing.JRadioButton importeRb;
    private javax.swing.JTextField importeTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton porcentualRb;
    private javax.swing.JButton sacarBtn;
    private javax.swing.JTable tabla;
    private javax.swing.JButton volverBtn;
    // End of variables declaration//GEN-END:variables

    private void llenarCombo() {
        rubros = null;
        try {
            rubros = new RubroService().getAllRubrosActivos();
        } catch (Exception ex) {
            Logger.getLogger(CambiarImportesPorRubroFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (rubros != null && !rubros.isEmpty()) {
            for (Rubro r : rubros) {
                combo.addItem(r.getDetalle());
            }
        }
    }

    private void limpiarCampos() {
        getContentPane().setBackground(new java.awt.Color(Constantes.getR(), Constantes.getG(), Constantes.getB()));
        this.setLocationRelativeTo(null);
        combo.removeAllItems();
        combo.addItem("");
        porcentualRb.setSelected(true);
        importeRb.setSelected(false);
        importeTxt.setText("");
        importe2Txt.setText("");
    }

    private void buscar(int row) {
        rubro = rubros.get(row - 1);
        abonos = null;
        try {
            abonos = new AbonoService().getAbonosActivosByRubro(rubro);
        } catch (Exception ex) {
            Logger.getLogger(CambiarImportesPorRubroFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        llenarTabla(abonos);
    }

    private void sacar(int row) {
        abonos.remove(row);
        llenarTabla(abonos);
    }

    private void llenarTabla(List<Abono> abonos) {
        UtilFrame.limpiarTabla(tabla);
        if (abonos != null && !abonos.isEmpty()) {
            DefaultTableModel tbl = (DefaultTableModel) tabla.getModel();
            for (Abono a : abonos) {
                Direccion dm = a.getCliente().getDireccion();
                String di = dm.getCalle() + " " + dm.getNumero();
                String fe = "";
                if (a.getFechaModif() != null) {
                    fe = sdf.format(a.getFechaModif());
                }
                Object o[] = new Object[3];
                o[0] = di;
                o[1] = fe;
                o[2] = df.format(a.getImporte());
                tbl.addRow(o);
            }
            tabla.setModel(tbl);
        }
    }

    private void volver() {
        AbmAbonoFrame aaf = new AbmAbonoFrame();
        aaf.setVisible(true);
        this.dispose();
    }

    private void grabar() {
        if (!abonos.isEmpty()) {
            for (Abono a : abonos) {
                Double importe = a.getImporte();
                List<RenglonAbono> renglones = null;
                try {
                    renglones = new RenglonAbonoService().getRenglonAbonosByAbono(a);
                } catch (Exception ex) {
                    Logger.getLogger(CambiarImportesPorRubroFrame.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "ERROR DE GRABACION");
                    return;
                }
                if (renglones != null && !renglones.isEmpty()) {
                    for (RenglonAbono ra : renglones) {
                        if (ra.getImporte() != null) {
                            if (ra.getImporte() > 0.0) {
                                ra.setImporte(importe);
                                a.setFechaModif(new Date());
                                try {
                                    new RenglonAbonoService().updateRenglonAbono(ra);
                                    new AbonoService().updateAbono(a);
                                } catch (Exception ex) {
                                    Logger.getLogger(CambiarImportesPorRubroFrame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }

                    }
                }
            }
            try {
                new AbonoService().updateListaAbono(abonos);
            } catch (Exception ex) {
                Logger.getLogger(CambiarImportesPorRubroFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "ERROR GRABANDO ABONOS");
                return;
            }
            JOptionPane.showMessageDialog(this, "GRABADO OK");

        }
    }

    private void aplicar() {
        if (!abonos.isEmpty()) {
            Double importe = 0.0;
            Float porce = 0F;
            if (importeRb.isSelected()) {
                if (!importeTxt.getText().isEmpty()) {
                    importe = Double.valueOf(importeTxt.getText().replace(",", "."));
                }
            }
            if (porcentualRb.isSelected()) {
                if (!importeTxt.getText().isEmpty()) {
                    porce = Float.valueOf(importeTxt.getText().replace(",", "."));
                }
            }
            for (Abono a : abonos) {
                if (importeRb.isSelected()) {

                    a.setImporte(importe);
                    a.setFechaModif(new Date());
                }
                if (porcentualRb.isSelected()) {
                    importe = a.getImporte();
                    Double calculo = importe * porce / 100;
                    String str0 = df.format(importe + calculo);
                    a.setImporte(Double.valueOf(str0.replace(",", ".")));
                    a.setFechaModif(new Date());
                }
            }
            llenarTabla(abonos);
        }
    }
}
