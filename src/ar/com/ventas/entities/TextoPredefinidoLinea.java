/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.entities;

/**
 *
 * @author Mario
 */
public class TextoPredefinidoLinea {

    private Long id;
    private TextoPredefinido textoPredefinido;
    private Integer orden;
    private String linea;
    

    public TextoPredefinidoLinea() {
    }

    public TextoPredefinidoLinea(Long id, Integer orden, String linea, TextoPredefinido textoPredefinido) {
        this.id = id;
        this.orden = orden;
        this.linea = linea;
        this.textoPredefinido = textoPredefinido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public TextoPredefinido getTextoPredefinido() {
        return textoPredefinido;
    }

    public void setTextoPredefinido(TextoPredefinido textoPredefinido) {
        this.textoPredefinido = textoPredefinido;
    }

}
