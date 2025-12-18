/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.entities;

import com.mysql.jdbc.Blob;

/**
 *
 * @author Mario
 */
public class ClaseNueva {

    private Long id;
    private Blob bl;

    public ClaseNueva() {
    }

    public ClaseNueva(Long id, Blob bl) {
        this.id = id;
        this.bl = bl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Blob getBl() {
        return bl;
    }

    public void setBl(Blob bl) {
        this.bl = bl;
    }

}
