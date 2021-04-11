/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.VehiculoGestion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Vehiculo;
import net.sf.jasperreports.engine.JasperExportManager;

/**
 *
 * @author Frey
 */
@Named(value = "vehiculoController")
@SessionScoped
public class VehiculoController extends Vehiculo implements Serializable{
    
    public VehiculoController() {
    }

    public List<Vehiculo> getVehiculos() {
        return VehiculoGestion.getVehiculos();
    }

    public String editVehiculo(int ID_VEHICULO, String PLACA) {
        Vehiculo e = VehiculoGestion.getVehiculo(ID_VEHICULO, PLACA);
        if (e != null) {
            this.setID_VEHICULO(e.getID_VEHICULO());
            this.setPLACA(e.getPLACA());
            this.setMARCA(e.getMARCA());
            this.setMODELO(e.getMODELO());
            this.setESTILO(e.getESTILO());
            this.setYEAR(e.getYEAR());
            this.setPUERTAS(e.getPUERTAS());
            this.setDESC_CONDICION(e.getDESC_CONDICION());
            this.setCILINDRAJE(e.getCILINDRAJE());
            this.setKILOMETRAJE(e.getKILOMETRAJE());
            this.setCOLOR_EXTERIOR(e.getCOLOR_EXTERIOR());
            this.setCOLOR_INTERIOR(e.getCOLOR_INTERIOR());
            this.setDESC_EXTRAS(e.getDESC_EXTRAS());
            this.setPRECIO(e.getPRECIO());
            this.setNEGOCIABLE(e.getNEGOCIABLE());
            this.setTRASPASO(e.getTRASPASO());
            this.setID_CLIENTE(e.getID_CLIENTE());
            return "ingresoVehiculo.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro no exista");
            FacesContext.getCurrentInstance().addMessage("editaVehiculoForm:identificacion", msg);
            return "listVehiculo.xhtml";
        }
    }
    
    private boolean noImprimir = true;

    public boolean isNoImprimir() {
        return noImprimir;
    }

    public void setNoImprimir(boolean noImprimir) {
        this.noImprimir = noImprimir;
    }

    public void buscarVehiculo(String PLACA) {
        Vehiculo e = VehiculoGestion.buscarVehiculo(PLACA);
        if (e != null) {
            this.setID_VEHICULO(e.getID_VEHICULO());
            this.setPLACA(e.getPLACA());
            this.setMARCA(e.getMARCA());
            this.setMODELO(e.getMODELO());
            this.setESTILO(e.getESTILO());
            this.setYEAR(e.getYEAR());
            this.setPUERTAS(e.getPUERTAS());
            this.setDESC_CONDICION(e.getDESC_CONDICION());
            this.setCILINDRAJE(e.getCILINDRAJE());
            this.setKILOMETRAJE(e.getKILOMETRAJE());
            this.setCOLOR_EXTERIOR(e.getCOLOR_EXTERIOR());
            this.setCOLOR_INTERIOR(e.getCOLOR_INTERIOR());
            this.setDESC_EXTRAS(e.getDESC_EXTRAS());
            this.setPRECIO(e.getPRECIO());
            this.setNEGOCIABLE(e.getNEGOCIABLE());
            this.setTRASPASO(e.getTRASPASO());
            this.setID_CLIENTE(e.getID_CLIENTE());
            
            noImprimir = false;
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro no exista");
            FacesContext.getCurrentInstance().addMessage("reporteVehiculoForm:PLACA", msg);
            noImprimir = true;
        }
    }

    public String insertVehiculo() {
        if (VehiculoGestion.insertVehiculo(this)) {
            return "listVehiculo.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al insertar el vehiculo");
            FacesContext.getCurrentInstance().addMessage("editaVehiculoForm:identificacion", msg);
            return "ingresoVehiculo.xhtml";
        }
    }

    public String updateVehiculo() {
        if (VehiculoGestion.updateVehiculo(this)) {
            return "listVehiculo.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al actualizar el vehiculo");
            FacesContext.getCurrentInstance().addMessage("editaVehiculoForm:identificacion", msg);
            return "ingresoVehiculo.xhtml";
        }
    }

    public String deleteVehiculo() {
        if (VehiculoGestion.deleteVehiculo(this)) {
            return "listVehiculo.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al eliminar el vehiculo");
            FacesContext.getCurrentInstance().addMessage("editaVehiculoForm:identificacion", msg);
            return "ingresoVehiculo.xhtml";
        }
    }
    
}