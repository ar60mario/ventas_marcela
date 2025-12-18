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
public class Abono {

    private Long id;
    private Integer codigo;
    private Boolean activo;
    private Boolean generado;
    private Double importe;
    private Cliente cliente;
    private Date fechaModif;
    private Rubro rubro;

    public Abono() {
    }

    public Abono(Long id, Integer codigo, Boolean activo, Boolean generado, Double importe, Cliente cliente, Date fechaModif, Rubro rubro) {
        this.id = id;
        this.codigo = codigo;
        this.activo = activo;
        this.generado = generado;
        this.importe = importe;
        this.cliente = cliente;
        this.fechaModif = fechaModif;
        this.rubro = rubro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getGenerado() {
        return generado;
    }

    public void setGenerado(Boolean generado) {
        this.generado = generado;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFechaModif() {
        return fechaModif;
    }

    public void setFechaModif(Date fechaModif) {
        this.fechaModif = fechaModif;
    }

    public Rubro getRubro() {
        return rubro;
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }

}
