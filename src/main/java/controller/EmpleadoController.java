/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.EmpleadoGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Empleado;

/**
 *
 * @author ProyectoGrupo4
 */
@Named(value = "empleadoController")
@SessionScoped
public class EmpleadoController extends Empleado implements Serializable {

    public EmpleadoController() {
    }

    public List<Empleado> getEmpleados() {
        return EmpleadoGestion.getEmpleados();
    }

    public String editEmpleado(int id, String idEmpleado) {
        Empleado e = EmpleadoGestion.getEmpleado(id, idEmpleado);
        if (e != null) {
            this.setId(e.getId());
            this.setIdEmpleado(e.getIdEmpleado());
            this.setNombre(e.getNombre());
            this.setApellido1(e.getApellido1());
            this.setApellido2(e.getApellido2());
            this.setFechaNacimiento(e.getFechaNacimiento());
            this.setFechaIngreso(e.getFechaIngreso());
            this.setCorreo(e.getCorreo());
            this.setCelular(e.getCelular());
            return "editempleado.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro no exista");
            FacesContext.getCurrentInstance().addMessage("editaEmpleadoForm:identificacion", msg);
            return "listempleado.xhtml";
        }
    }
    
    private boolean noImprimir = true;

    public boolean isNoImprimir() {
        return noImprimir;
    }

    public void setNoImprimir(boolean noImprimir) {
        this.noImprimir = noImprimir;
    }

    public void buscarEmpleado(String idEmpleado) {
        Empleado e = EmpleadoGestion.buscarEmpleado(idEmpleado);
        if (e != null) {
            this.setId(e.getId());
            this.setIdEmpleado(e.getIdEmpleado());
            this.setNombre(e.getNombre());
            this.setApellido1(e.getApellido1());
            this.setApellido2(e.getApellido2());
            this.setFechaNacimiento(e.getFechaNacimiento());
            this.setFechaIngreso(e.getFechaIngreso());
            this.setCorreo(e.getCorreo());
            this.setCelular(e.getCelular());
            noImprimir = false;
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro no exista");
            FacesContext.getCurrentInstance().addMessage("reporteEmpleadoForm:identificacion", msg);
            noImprimir = true;
        }
    }

    public String insertEmpleado() {
        if (EmpleadoGestion.insertEmpleado(this)) {
            return "listempleado.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al insertar el empleado");
            FacesContext.getCurrentInstance().addMessage("editaEmpleadoForm:identificacion", msg);
            return "registro.xhtml";
        }
    }

    public String updateEmpleado() {
        if (EmpleadoGestion.updateEmpleado(this)) {
            return "listempleado.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al actualizar el empleado");
            FacesContext.getCurrentInstance().addMessage("editaEmpleadoForm:identificacion", msg);
            return "editempleado.xhtml";
        }
    }

    public String deleteEmpleado() {
        if (EmpleadoGestion.deleteEmpleado(this)) {
            return "listempleado.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al eliminar el empleado");
            FacesContext.getCurrentInstance().addMessage("editaEmpleadoForm:identificacion", msg);
            return "editempleado.xhtml";
        }
    }

}
