/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.RepuestoGestion;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Repuesto;

/**
 *
 * @author Frey
 */
@Named(value = "repuestoController")
@SessionScoped
public class RepuestoController extends Repuesto implements Serializable {

    public RepuestoController() {
    }

    public List<Repuesto> getRepuestos() {
        return RepuestoGestion.getRepuestos();
    }

    public String editRepuesto(int ID) {
        Repuesto e = RepuestoGestion.getRepuesto(ID);
        if (e != null) {
            this.setId(e.getId());
            this.setDescripcion(e.getDescripcion());
            this.setPrecio(e.getPrecio());

            return "editRepuesto.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro no exista");
            FacesContext.getCurrentInstance().addMessage("editaRepuestoForm:identificacion", msg);
            return "listRepuesto.xhtml";
        }
    }

    public String infoRepuesto(int ID) {
        Repuesto e = RepuestoGestion.getRepuesto(ID);
        if (e != null) {
            this.setId(e.getId());
            this.setDescripcion(e.getDescripcion());
            this.setPrecio(e.getPrecio());
            return "detallesRepuesto.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro no exista");
            FacesContext.getCurrentInstance().addMessage("detallesRepuestoForm:identificacion", msg);
            return "principal.xhtml";
        }
    }

    private boolean noImprimir = true;

    public boolean isNoImprimir() {
        return noImprimir;
    }

    public void setNoImprimir(boolean noImprimir) {
        this.noImprimir = noImprimir;
    }

    public void buscarRepuesto(int ID) {
        Repuesto e = RepuestoGestion.getRepuesto(ID);
        if (e != null) {
            this.setId(e.getId());
            this.setDescripcion(e.getDescripcion());
            this.setPrecio(e.getPrecio());

            noImprimir = false;
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro no exista");
            FacesContext.getCurrentInstance().addMessage("reporteRepuestoForm:PLACA", msg);
            noImprimir = true;
        }
    }

    public String insertRepuesto() {
        if (RepuestoGestion.insertar(this)) {
            return "listRepuesto.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al insertar el repuesto");
            FacesContext.getCurrentInstance().addMessage("editaRepuestoForm:PLACA", msg);
            return "ingresoRepuesto.xhtml";
        }
    }

    public String updateRepuesto() {
        if (RepuestoGestion.modificar(this)) {
            return "listRepuesto.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al actualizar el repuesto");
            FacesContext.getCurrentInstance().addMessage("editaRepuestoForm:identificacion", msg);
            return "editRepuesto.xhtml";
        }
    }

    public String deleteRepuesto() {
        if (RepuestoGestion.eliminar(this.getId())) {
            return "listRepuesto.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al eliminar el repuesto");
            FacesContext.getCurrentInstance().addMessage("editaRepuestoForm:identificacion", msg);
            return "editRepuesto.xhtml";
        }
    }
}
