/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 *
 * @author Frey
 */
public class Vehiculo {
    private int ID_VEHICULO;
    private String MARCA; 
    private String MODELO;
    private String PLACA; 
    private String ESTILO;
    private int YEAR;
    private int PUERTAS;
    private String DESC_CONDICION;
    private String CILINDRAJE;
    private String TRACCION;
    private String COMBUSTIBLE;
    private int KILOMETRAJE;
    private String COLOR_EXTERIOR;
    private String COLOR_INTERIOR;
    private String DESC_EXTRAS;
    private int PRECIO;
    private String NEGOCIABLE;
    private String TRASPASO;
    private int ID_CLIENTE;
    
    private String IMAGE;
    
    
    public Vehiculo() {
    }

    public Vehiculo(int ID_VEHICULO, String MARCA, String MODELO, String PLACA, String ESTILO, int YEAR, int PUERTAS, String DESC_CONDICION, String CILINDRAJE, String TRACCION, String COMBUSTIBLE, int KILOMETRAJE, String COLOR_EXTERIOR, String COLOR_INTERIOR, String DESC_EXTRAS, int PRECIO, String NEGOCIABLE, String TRASPASO, int ID_CLIENTE, String IMAGE) {
        this.ID_VEHICULO = ID_VEHICULO;
        this.MARCA = MARCA;
        this.MODELO = MODELO;
        this.PLACA = PLACA;
        this.ESTILO = ESTILO;
        this.YEAR = YEAR;
        this.PUERTAS = PUERTAS;
        this.DESC_CONDICION = DESC_CONDICION;
        this.CILINDRAJE = CILINDRAJE;
        this.TRACCION = TRACCION;
        this.COMBUSTIBLE = COMBUSTIBLE;
        this.KILOMETRAJE = KILOMETRAJE;
        this.COLOR_EXTERIOR = COLOR_EXTERIOR;
        this.COLOR_INTERIOR = COLOR_INTERIOR;
        this.DESC_EXTRAS = DESC_EXTRAS;
        this.PRECIO = PRECIO;
        this.NEGOCIABLE = NEGOCIABLE;
        this.TRASPASO = TRASPASO;
        this.ID_CLIENTE = ID_CLIENTE;
        this.IMAGE = IMAGE;
    }


    

    public int getID_VEHICULO() {
        return ID_VEHICULO;
    }

    public void setID_VEHICULO(int ID_VEHICULO) {
        this.ID_VEHICULO = ID_VEHICULO;
    }

    public String getMARCA() {
        return MARCA;
    }

    public void setMARCA(String MARCA) {
        this.MARCA = MARCA;
    }

    public String getMODELO() {
        return MODELO;
    }

    public void setMODELO(String MODELO) {
        this.MODELO = MODELO;
    }

    public String getPLACA() {
        return PLACA;
    }

    public void setPLACA(String PLACA) {
        this.PLACA = PLACA;
    }

    public String getESTILO() {
        return ESTILO;
    }

    public void setESTILO(String ESTILO) {
        this.ESTILO = ESTILO;
    }

    public int getYEAR() {
        return YEAR;
    }

    public void setYEAR(int YEAR) {
        this.YEAR = YEAR;
    }

    public int getPUERTAS() {
        return PUERTAS;
    }

    public void setPUERTAS(int PUERTAS) {
        this.PUERTAS = PUERTAS;
    }

    public String getDESC_CONDICION() {
        return DESC_CONDICION;
    }

    public void setDESC_CONDICION(String DESC_CONDICION) {
        this.DESC_CONDICION = DESC_CONDICION;
    }

    public String getCILINDRAJE() {
        return CILINDRAJE;
    }

    public void setCILINDRAJE(String CILINDRAJE) {
        this.CILINDRAJE = CILINDRAJE;
    }

    public String getTRACCION() {
        return TRACCION;
    }

    public void setTRACCION(String TRACCION) {
        this.TRACCION = TRACCION;
    }

    public String getCOMBUSTIBLE() {
        return COMBUSTIBLE;
    }

    public void setCOMBUSTIBLE(String COMBUSTIBLE) {
        this.COMBUSTIBLE = COMBUSTIBLE;
    }

    public int getKILOMETRAJE() {
        return KILOMETRAJE;
    }

    public void setKILOMETRAJE(int KILOMETRAJE) {
        this.KILOMETRAJE = KILOMETRAJE;
    }

    public String getCOLOR_EXTERIOR() {
        return COLOR_EXTERIOR;
    }

    public void setCOLOR_EXTERIOR(String COLOR_EXTERIOR) {
        this.COLOR_EXTERIOR = COLOR_EXTERIOR;
    }

    public String getCOLOR_INTERIOR() {
        return COLOR_INTERIOR;
    }

    public void setCOLOR_INTERIOR(String COLOR_INTERIOR) {
        this.COLOR_INTERIOR = COLOR_INTERIOR;
    }

    public String getDESC_EXTRAS() {
        return DESC_EXTRAS;
    }

    public void setDESC_EXTRAS(String DESC_EXTRAS) {
        this.DESC_EXTRAS = DESC_EXTRAS;
    }

    public int getPRECIO() {
        return PRECIO;
    }

    public void setPRECIO(int PRECIO) {
        this.PRECIO = PRECIO;
    }

    public String getNEGOCIABLE() {
        return NEGOCIABLE;
    }

    public void setNEGOCIABLE(String NEGOCIABLE) {
        this.NEGOCIABLE = NEGOCIABLE;
    }

    public String getTRASPASO() {
        return TRASPASO;
    }

    public void setTRASPASO(String TRASPASO) {
        this.TRASPASO = TRASPASO;
    }

    public int getID_CLIENTE() {
        return ID_CLIENTE;
    }

    public void setID_CLIENTE(int ID_CLIENTE) {
        this.ID_CLIENTE = ID_CLIENTE;
    }

    public String getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(String IMAGE) {
        this.IMAGE = IMAGE;
    }

    

    

    public String getNombreVehiculo(){
        String texto="";
        texto += this.MARCA != null ? this.MARCA + " ":"";
        texto += this.MODELO != null ? this.MODELO + " ":"";
        return texto;
    }


}