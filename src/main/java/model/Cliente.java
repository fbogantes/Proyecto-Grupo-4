/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ProyectoGrupo4
 */
public class Cliente {

    private int ID_CLIENTE;
    private String CEDULA;
    private String NOMBRE;
    private String APELLIDO_1;
    private String APELLIDO_2;
    private String CORREO;
    private String TELEFONO;
    private String UBICACION;

    public Cliente() {
    }

    public Cliente(int ID_CLIENTE, String CEDULA, String NOMBRE, String APELLIDO_1, String APELLIDO_2, String CORREO, String TELEFONO, String UBICACION) {
        this.ID_CLIENTE = ID_CLIENTE;
        this.CEDULA = CEDULA;
        this.NOMBRE = NOMBRE;
        this.APELLIDO_1 = APELLIDO_1;
        this.APELLIDO_2 = APELLIDO_2;
        this.CORREO = CORREO;
        this.TELEFONO = TELEFONO;
        this.UBICACION = UBICACION;
    }

    public int getID_CLIENTE() {
        return ID_CLIENTE;
    }

    public void setID_CLIENTE(int ID_CLIENTE) {
        this.ID_CLIENTE = ID_CLIENTE;
    }

    public String getCEDULA() {
        return CEDULA;
    }

    public void setCEDULA(String CEDULA) {
        this.CEDULA = CEDULA;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getAPELLIDO_1() {
        return APELLIDO_1;
    }

    public void setAPELLIDO_1(String APELLIDO_1) {
        this.APELLIDO_1 = APELLIDO_1;
    }

    public String getAPELLIDO_2() {
        return APELLIDO_2;
    }

    public void setAPELLIDO_2(String APELLIDO_2) {
        this.APELLIDO_2 = APELLIDO_2;
    }

    public String getCORREO() {
        return CORREO;
    }

    public void setCORREO(String CORREO) {
        this.CORREO = CORREO;
    }

    public String getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    public String getUBICACION() {
        return UBICACION;
    }

    public void setUBICACION(String UBICACION) {
        this.UBICACION = UBICACION;
    }
    
    
    
}
