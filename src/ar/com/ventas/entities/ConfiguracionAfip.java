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
public class ConfiguracionAfip {

    private Long id;
    private Integer numeroFcA;
    private Integer numeroFcB;
    private Integer numeroFcC;
    private Integer numeroNcA;
    private Integer numeroNcB;
    private Integer numeroNcC;

    public ConfiguracionAfip() {
    }

    public ConfiguracionAfip(Long id, Integer numeroFcA, Integer numeroFcB, Integer numeroFcC, Integer numeroNcA, Integer numeroNcB, Integer numeroNcC) {
        this.id = id;
        this.numeroFcA = numeroFcA;
        this.numeroFcB = numeroFcB;
        this.numeroFcC = numeroFcC;
        this.numeroNcA = numeroNcA;
        this.numeroNcB = numeroNcB;
        this.numeroNcC = numeroNcC;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroFcA() {
        return numeroFcA;
    }

    public void setNumeroFcA(Integer numeroFcA) {
        this.numeroFcA = numeroFcA;
    }

    public Integer getNumeroFcB() {
        return numeroFcB;
    }

    public void setNumeroFcB(Integer numeroFcB) {
        this.numeroFcB = numeroFcB;
    }

    public Integer getNumeroFcC() {
        return numeroFcC;
    }

    public void setNumeroFcC(Integer numeroFcC) {
        this.numeroFcC = numeroFcC;
    }

    public Integer getNumeroNcA() {
        return numeroNcA;
    }

    public void setNumeroNcA(Integer numeroNcA) {
        this.numeroNcA = numeroNcA;
    }

    public Integer getNumeroNcB() {
        return numeroNcB;
    }

    public void setNumeroNcB(Integer numeroNcB) {
        this.numeroNcB = numeroNcB;
    }

    public Integer getNumeroNcC() {
        return numeroNcC;
    }

    public void setNumeroNcC(Integer numeroNcC) {
        this.numeroNcC = numeroNcC;
    }

}