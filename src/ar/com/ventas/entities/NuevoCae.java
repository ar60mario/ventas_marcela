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
public class NuevoCae {
    private Long cae;
    private Date fechaCae;
    private String estado;
    private String excepcion;
    private String errMsj;
    private String Observaciones;
    private Integer sucursal;
    private Integer numero;
    private String motivo;

    public NuevoCae() {
    }

    public NuevoCae(Long cae, Date fechaCae, String estado, String excepcion, String errMsj, String Observaciones, Integer sucursal, Integer numero, String motivo) {
        this.cae = cae;
        this.fechaCae = fechaCae;
        this.estado = estado;
        this.excepcion = excepcion;
        this.errMsj = errMsj;
        this.Observaciones = Observaciones;
        this.sucursal = sucursal;
        this.numero = numero;
        this.motivo = motivo;
    }

    public Long getCae() {
        return cae;
    }

    public void setCae(Long cae) {
        this.cae = cae;
    }

    public Date getFechaCae() {
        return fechaCae;
    }

    public void setFechaCae(Date fechaCae) {
        this.fechaCae = fechaCae;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getExcepcion() {
        return excepcion;
    }

    public void setExcepcion(String excepcion) {
        this.excepcion = excepcion;
    }

    public String getErrMsj() {
        return errMsj;
    }

    public void setErrMsj(String errMsj) {
        this.errMsj = errMsj;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String Observaciones) {
        this.Observaciones = Observaciones;
    }

    public Integer getSucursal() {
        return sucursal;
    }

    public void setSucursal(Integer sucursal) {
        this.sucursal = sucursal;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    
}