/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @grupo4
 */
public class RentCar {


    private int idRentCar;
    private String Nombre;
    private String Ubicacion;
    private String Estilo;
    private String Correo;
    private String Telefono;

    public RentCar() {
    }

    public RentCar(int idRentCar, String Nombre, String Ubicacion, String Estilo, String Correo, String Telefono) {
        this.idRentCar = idRentCar;
        this.Nombre = Nombre;
        this.Ubicacion = Ubicacion;
        this.Estilo = Estilo;
        this.Correo = Correo;
        this.Telefono = Telefono;
    }

    public int getIdRentCar() {
        return idRentCar;
    }

    public void setIdRentCar(int idRentCar) {
        this.idRentCar = idRentCar;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String Ubicacion) {
        this.Ubicacion = Ubicacion;
    }

    public String getEstilo() {
        return Estilo;
    }

    public void setEstilo(String Estilo) {
        this.Estilo = Estilo;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }



}