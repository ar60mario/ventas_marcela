package ar.com.ventas.entities;

public class Configuracion {
    private Long id;
    private Integer sucursal;
    private Integer numeroFc;
    private Integer numeroNc;
    private Integer numeroFcA;
    private Integer numeroFcB;
    private Integer numeroNcA;
    private Integer numeroNcB;
    private TitularCuit titular;

    public Configuracion() {
    }

    public Configuracion(Long id, Integer sucursal, Integer numeroFc, Integer numeroNc, Integer numeroFcA, Integer numeroFcB, Integer numeroNcA, Integer numeroNcB, TitularCuit titular) {
        this.id = id;
        this.sucursal = sucursal;
        this.numeroFc = numeroFc;
        this.numeroNc = numeroNc;
        this.numeroFcA = numeroFcA;
        this.numeroFcB = numeroFcB;
        this.numeroNcA = numeroNcA;
        this.numeroNcB = numeroNcB;
        this.titular = titular;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSucursal() {
        return sucursal;
    }

    public void setSucursal(Integer sucursal) {
        this.sucursal = sucursal;
    }

    public Integer getNumeroFc() {
        return numeroFc;
    }

    public void setNumeroFc(Integer numeroFc) {
        this.numeroFc = numeroFc;
    }

    public Integer getNumeroNc() {
        return numeroNc;
    }

    public void setNumeroNc(Integer numeroNc) {
        this.numeroNc = numeroNc;
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

    public TitularCuit getTitular() {
        return titular;
    }

    public void setTitular(TitularCuit titular) {
        this.titular = titular;
    }

}