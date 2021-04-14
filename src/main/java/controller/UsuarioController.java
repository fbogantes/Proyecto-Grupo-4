/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.UsuarioGestion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;

/**
 *
 * @author ProyectoGrupo4
 */
@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController extends Usuario implements Serializable {

//    private boolean rolAdmin = false;false
    /**
     * Creates a new instance of UsuarioController
     */
    public UsuarioController() {
    }

    public String getUsuario() {
        Usuario usuario = UsuarioGestion.getUsuario(this.getIdUsuario(), this.getPwUsuario());
        if (usuario != null) {
            this.setNombreUsuario(usuario.getNombreUsuario());
            this.setIdRol(usuario.getIdRol());
//            if (usuario.getIdRol() == "admin") {
//                rolAdmin = true; <!--esto en la plantilla
//rendered="#{usuarioController.rolAdmin} property to control access -->
//            }
            return "principal.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario o contraseña invalida");
            FacesContext.getCurrentInstance().addMessage("loginForm:clave", msg);
            return "index.xhtml";
        }
    }
    private boolean noImprimir = true;

    public boolean isNoImprimir() {
        return noImprimir;
    }

    public void setNoImprimir(boolean noImprimir) {
        this.noImprimir = noImprimir;
    }
    
    public void buscarUsuario(String idUsuario) {
        Usuario user = UsuarioGestion.buscarUsuario(idUsuario);
        if (user != null) {
            this.setIdRol(user.getIdUsuario());
            this.setPwUsuario(user.getPwUsuario());
            this.setNombreUsuario(user.getNombreUsuario());
            this.setIdRol(user.getIdRol());
            noImprimir = false;
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro no exista");
            FacesContext.getCurrentInstance().addMessage("reporteIdUsuarioForm:identificacion", msg);
            noImprimir = true;
        }
    }
    
    public void respaldo() {
        ZipOutputStream out = null;
        try {
            
            String json = UsuarioGestion.generarJson();

            File f = new File(FacesContext
                    .getCurrentInstance().
                    getExternalContext()
                    .getRealPath("/respaldo") + "usuarios.zip");
            out = new ZipOutputStream(new FileOutputStream(f));
            ZipEntry e = new ZipEntry("usuarios.json");
            out.putNextEntry(e);
            byte[] data = json.getBytes();
            out.write(data, 0, data.length);
            out.closeEntry();
            out.close();

            File zipPath = new File(FacesContext
                    .getCurrentInstance().
                    getExternalContext()
                    .getRealPath("/respaldo") + "usuarios.zip");

            byte[] zip = Files.readAllBytes(zipPath.toPath());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();
            ServletOutputStream flujo = respuesta.getOutputStream();

            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-disposition", "attachment; filename=usuarios.zip");

            flujo.write(zip);
            flujo.flush();
            FacesContext.getCurrentInstance().responseComplete();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
