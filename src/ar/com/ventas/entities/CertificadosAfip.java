/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.entities;

import java.util.Date;

/**
 *
 * @author Mario
 */
public class CertificadosAfip {
    private Long id;
    private String certificado;
    private String llave;
    private Date fecha;
    private TitularCuit titularCuit;

    public CertificadosAfip() {
    }

    public CertificadosAfip(Long id, Date fecha, String certificado, String llave, TitularCuit titularCuit) {
        this.id = id;
        this.certificado = certificado;
        this.llave = llave;
        this.titularCuit = titularCuit;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCertificado() {
        return certificado;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public TitularCuit getTitularCuit() {
        return titularCuit;
    }

    public void setTitularCuit(TitularCuit titularCuit) {
        this.titularCuit = titularCuit;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
