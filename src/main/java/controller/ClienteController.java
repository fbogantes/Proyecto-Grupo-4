/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.ClienteGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Cliente;

/**
 *
 * @author ProyectoGrupo4
 */
@Named(value = "clienteController")
@SessionScoped
public class ClienteController extends Cliente implements Serializable {


    public ClienteController() {
    }

    public List<Cliente> getClientes() {
        return ClienteGestion.getClientes();
    }

    public String editCliente(int id, String idCliente) {
        Cliente e = ClienteGestion.getCliente(id, idCliente);
        if (e != null) {
            this.setId(e.getId());
            this.setIdCliente(e.getIdCliente());
            this.setNombre(e.getNombre());
            this.setApellido(e.getApellido());
            this.setCorreo(e.getCorreo());
            this.setCelular(e.getCelular());
            return "edit.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro no exista");
            FacesContext.getCurrentInstance().addMessage("editaClienteForm:identificacion", msg);
            return "list.xhtml";
        }
    }

    public String insertCliente() {
        if (ClienteGestion.insertCliente(this)) {
            return "list.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al insertar el cliente");
            FacesContext.getCurrentInstance().addMessage("editaClienteForm:identificacion", msg);
            return "edit.xhtml";
        }
    }

    public String updateCliente() {
        if (ClienteGestion.updateCliente(this)) {
            return "list.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al actualizar el cliente");
            FacesContext.getCurrentInstance().addMessage("editaClienteForm:identificacion", msg);
            return "edit.xhtml";
        }
    }

    public String deleteCliente() {
        if (ClienteGestion.deleteCliente(this)) {
            return "list.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al eliminar el cliente");
            FacesContext.getCurrentInstance().addMessage("editaClienteForm:identificacion", msg);
            return "edit.xhtml";
        }
    }

}
