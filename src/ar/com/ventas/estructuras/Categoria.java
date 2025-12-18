/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.estructuras;

import java.util.Arrays;

/**
 *
 * @author Mario
 */
public enum Categoria {

    CAT01("01", "INSCRIPTO", "A") {
        @Override
        public String getLetra(String codigo) {
            return codigo;
        }
    },
    CAT02("02", "EXENTO", "N") {
        @Override
        public String getLetra(String codigo) {
            return codigo;
        }
    },
    CAT03("03", "CONSUMIDOR_FINAL", "N") {
        @Override
        public String getLetra(String codigo) {
            return codigo;
        }
    },
    CAT04("04", "MONOTRIBUTO_A", "C") {
        @Override
        public String getLetra(String codigo) {
            return codigo;
        }
    },
    CAT05("05", "MONOTRIBUTO_B", "C") {
        @Override
        public String getLetra(String codigo) {
            return codigo;
        }
    },
    CAT06("06", "MONOTRIBUTO_C", "C") {
        @Override
        public String getLetra(String codigo) {
            return codigo;
        }
    },
    CAT07("07", "MONOTRIBUTO_D", "C") {
        @Override
        public String getLetra(String codigo) {
            return codigo;
        }
    },
    CAT08("08", "MONOTRIBUTO_E", "C") {
        @Override
        public String getLetra(String codigo) {
            return codigo;
        }
    },
    CAT09("09", "MONOTRIBUTO_F", "C") {
        @Override
        public String getLetra(String codigo) {
            return codigo;
        }
    },
    CAT10("10", "MONOTRIBUTO_G", "C") {
        @Override
        public String getLetra(String codigo) {
            return codigo;
        }
    },
    CAT11("11", "MONOTRIBUTO_H", "C") {
        @Override
        public String getLetra(String codigo) {
            return codigo;
        }
    };

    private final String codigo;
    private final String detalle;
    private final String letra;

    Categoria(String codigo, String detalle, String letra) {
        this.codigo = codigo;
        this.detalle = detalle;
        this.letra = letra;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDetalle() {
        return detalle;
    }

    public String getLetra() {
        return letra;
    }

    public abstract String getLetra(String codigo);
    
    public static String getLetraByCode(String codigo) {
        return Arrays.asList(values()).stream()
                .filter(campo -> campo.getCodigo().equals(codigo))
                .findFirst()
                .map(campo -> campo.getLetra())
                .orElseThrow(() -> new RuntimeException());
    }

}
