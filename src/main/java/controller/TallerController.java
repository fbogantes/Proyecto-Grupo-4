/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.TallerGestion;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Taller;

/**
 *
 * @author Grupo4
 */
@Named(value = "tallerController")
@SessionScoped
public class TallerController extends Taller implements Serializable {

    /**
     * Creates a new instance of TallerController
     */
    public TallerController() {
    }

    public List<Taller> getTalleres() {
        return TallerGestion.getTalleres();
    }

    public String editTaller(int id, String nombre) {
        Taller e = TallerGestion.getTaller(id, nombre);
        if (e != null) {
            this.setId(e.getId());
            this.setNombre(e.getNombre());
            this.setUbicacion(e.getUbicacion());
            this.setTelefono(e.getTelefono());
            return "editTaller.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro no exista");
            FacesContext.getCurrentInstance().addMessage("editaTallerForm:identificacion", msg);
            return "listTaller.xhtml";
        }
    }

    public String insertTaller() {
        if (TallerGestion.insertTaller(this)) {
            return "listTaller.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al insertar el taller");
            FacesContext.getCurrentInstance().addMessage("editaTallerForm:identificacion", msg);
            return "tallerForm.xhtml";
        }
    }

    public String updateTaller() {
        if (TallerGestion.updateTaller(this)) {
            return "listTaller.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al actualizar el taller");
            FacesContext.getCurrentInstance().addMessage("editaTallerForm:identificacion", msg);
            return "editTaller.xhtml";
        }
    }

    public String deleteTaller() {
        if (TallerGestion.deleteTaller(this)) {
            return "listTaller.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al eliminar el taller");
            FacesContext.getCurrentInstance().addMessage("editaTallerForm:identificacion", msg);
            return "editTaller.xhtml";
        }
    }
}
