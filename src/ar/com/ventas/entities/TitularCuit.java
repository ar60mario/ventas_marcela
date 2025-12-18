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
public class TitularCuit {

    private Long id;
    private String razonSocial;
    private String calleNumero;
    private String codigoPostalLocalidad;
    private String provincia;
    private String telefono;
    private String mail;
    private String cuit;
    private Integer sucursal;
    private String categoria;
    private String iibb;
    private Date fechaInicioActividades;
    private Boolean activo;
    private String observaciones;

    /*
    CATEGORIA:
    1 INSCRIPTO
    2 EXENTO
    3 CONSUMIDOR FINAL
    A MONOTRIBUTO A
    B ... H
     */
    public TitularCuit() {
    }

    public TitularCuit(Long id, String razonSocial, String calleNumero, String codigoPostalLocalidad, String provincia, String telefono, String mail, String cuit, Integer sucursal, String categoria, String iibb, Date fechaInicioActividades, Boolean activo, String observaciones) {
        this.id = id;
        this.razonSocial = razonSocial;
        this.calleNumero = calleNumero;
        this.codigoPostalLocalidad = codigoPostalLocalidad;
        this.provincia = provincia;
        this.telefono = telefono;
        this.mail = mail;
        this.cuit = cuit;
        this.sucursal = sucursal;
        this.categoria = categoria;
        this.iibb = iibb;
        this.fechaInicioActividades = fechaInicioActividades;
        this.activo = activo;
        this.observaciones = observaciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCalleNumero() {
        return calleNumero;
    }

    public void setCalleNumero(String calleNumero) {
        this.calleNumero = calleNumero;
    }

    public String getCodigoPostalLocalidad() {
        return codigoPostalLocalidad;
    }

    public void setCodigoPostalLocalidad(String codigoPostalLocalidad) {
        this.codigoPostalLocalidad = codigoPostalLocalidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public Integer getSucursal() {
        return sucursal;
    }

    public void setSucursal(Integer sucursal) {
        this.sucursal = sucursal;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getIibb() {
        return iibb;
    }

    public void setIibb(String iibb) {
        this.iibb = iibb;
    }

    public Date getFechaInicioActividades() {
        return fechaInicioActividades;
    }

    public void setFechaInicioActividades(Date fechaInicioActividades) {
        this.fechaInicioActividades = fechaInicioActividades;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
}