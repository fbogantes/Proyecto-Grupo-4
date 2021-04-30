/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.RentCarGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.RentCar;

/**
 *
 * @Grupo4
 */
@Named(value = "rentCarController")
@SessionScoped
public class RentCarController extends RentCar implements Serializable {

    /**
     * Creates a new instance of RentCarController
     */
    public RentCarController() {
    }
    
    public List<RentCar> getRentCars() {
        return RentCarGestion.getRentCars();
    }

    public String editRentCar(int IdRentCar,String Nombre) {
        RentCar e = RentCarGestion.getRentCar(IdRentCar,Nombre);
        if (e != null) {
            this.setIdRentCar(e.getIdRentCar());
            this.setNombre(e.getNombre());
            this.setUbicacion(e.getUbicacion());
            this.setEstilo(e.getEstilo());
            this.setCorreo(e.getCorreo());
            this.setTelefono(e.getTelefono());
            return "edit.xhtml"; //por editar con el front end
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro no exista");
            FacesContext.getCurrentInstance().addMessage("rentCarForm:identificacion", msg);
            return "list.xhtml"; //por editar con el front end
        }
    }

    public String insertRentCar() {
        if (RentCarGestion.insertRentCar(this)) {
            return "list.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al insertar el cliente");
            FacesContext.getCurrentInstance().addMessage("rentCarForm:identificacion", msg);
            return "registroRent.xhtml";//por editar con el front end
        }
    }

    public String updateRentCar() {
        if (RentCarGestion.updateRentCar(this)) {
            return "list.xhtml";//por editar con el front end
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al actualizar el cliente");
            FacesContext.getCurrentInstance().addMessage("UpdaterentCarForm:identificacion", msg);
            return "edit.xhtml";//por editar con el front end
        }
    }

    public String deleteRentCar() {
        if (RentCarGestion.deleteRentCar(this)) {
            return "list.xhtml";//por editar con el front end
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al eliminar el cliente");
            FacesContext.getCurrentInstance().addMessage("rentCarForm:identificacion", msg);
            return "edit.xhtml";//por editar con el front end
        }
    }
    
}