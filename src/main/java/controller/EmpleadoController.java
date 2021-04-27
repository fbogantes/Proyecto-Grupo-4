/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.URI;
import gestion.EmpleadoGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.json.JsonArray;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import model.Empleado;

/**
 *
 * @author ProyectoGrupo4
 */
@Named(value = "empleadoController")
@SessionScoped
public class EmpleadoController extends Empleado implements Serializable {

    
    
    private int id;
    private String idEmpleado;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Date fechaNacimiento;
    private Date fechaIngreso;
    private String correo;
    private String celular;
    
    private final String URI = "http://localhost:80/TAREA_Grupo4-1.0-SNAPSHOT/resources/empleado";
    
    public EmpleadoController() {
    }

    public List<Empleado> getEmpleados() {
        return EmpleadoGestion.getEmpleados();
    }
    
    public List<Empleado> getEmpleadosLista() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        List<Empleado> lista= null;
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URI);
        JsonArray response = target.request(MediaType.APPLICATION_JSON)
        .get(JsonArray.class);
            id = response.asJsonObject().getInt("id");
            idEmpleado = response.asJsonObject().getString("idEmpleado");
            nombre = response.asJsonObject().getString("nombre");
            apellido1 = response.asJsonObject().getString("apellido1");
            apellido2 = response.asJsonObject().getString("apellido2");
            fechaNacimiento = format.parse(response.asJsonObject().toString());
            fechaIngreso = format.parse(response.asJsonObject().toString());
            correo = response.asJsonObject().getString("correo");
            celular = response.asJsonObject().getString("celular");
            lista= target.request(MediaType.APPLICATION_JSON)
        .get(new GenericType<List<Empleado>>(){});
        return lista;

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
                    "Ocurrio un error al actualizar el Empleado");
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

}
