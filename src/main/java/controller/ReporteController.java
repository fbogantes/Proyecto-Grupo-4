/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.ClienteGestion;
import gestion.EmpleadoGestion;
import gestion.VehiculoGestion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Conexion;
import model.Usuario;
import model.Vehiculo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import ws.EmpleadoWS;
import ws.UsuarioWS;

/**
 *
 * @author User
 */
@Named(value = "reporteController")
@SessionScoped
public class ReporteController implements Serializable {

    /**
     * Creates a new instance of ReporteController
     */
    public ReporteController() {
    }

    public void verPDF() {
        try {
            File jasper = new File(FacesContext
                    .getCurrentInstance().
                    getExternalContext()
                    .getRealPath("/prospecto/ProspectoK.jasper"));

            JasperPrint reporteJasper = JasperFillManager.
                    fillReport(jasper.getPath(), null, Conexion.getConexion());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();

            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type", "application/pdf");

            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void descargarPDF() {
        try {
            File jasper = new File(FacesContext
                    .getCurrentInstance().
                    getExternalContext()
                    .getRealPath("/prospecto/ProspectoK.jasper"));

            JasperPrint reporteJasper = JasperFillManager.
                    fillReport(jasper.getPath(), null, Conexion.getConexion());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();
          
            respuesta.addHeader("Content-disposition", "attachment; filename=reporteProspectoK.pdf");

            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void verClientePDF() {
        try {
            File jasper = new File(FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getRealPath("/reportes/ClientesK.jasper"));
            
            JasperPrint reporteJasper = JasperFillManager.
                    fillReport(jasper.getPath(), null, Conexion.getConexion());
            
            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();
            
            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type", "application/pdf");
            
            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();
                        
        } catch (JRException | IOException e) {
            
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    public void descargaClientePDF() {
        try {
            File jasper = new File(FacesContext
                    .getCurrentInstance().
                    getExternalContext()
                    .getRealPath("/reportes/ClientesK.jasper"));

            JasperPrint reporteJasper = JasperFillManager.
                    fillReport(jasper.getPath(), null, Conexion.getConexion());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();
          
            respuesta.addHeader("Content-disposition", "attachment; filename=reporteClienteK.pdf");

            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void verEmpleadosPDF() {
        try {
            File jasper = new File(FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getRealPath("/reportes/EmployeeK.jasper"));
            
            JasperPrint reporteJasper = JasperFillManager.
                    fillReport(jasper.getPath(), null, Conexion.getConexion());
            
            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();
            
            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type", "application/pdf");
            
            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();
                        
        } catch (JRException | IOException e) {
            
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    public void descargaEmpleadosPDF() {
        try {
            File jasper = new File(FacesContext
                    .getCurrentInstance().
                    getExternalContext()
                    .getRealPath("/reportes/EmployeeK.jasper"));

            JasperPrint reporteJasper = JasperFillManager.
                    fillReport(jasper.getPath(), null, Conexion.getConexion());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();
          
            respuesta.addHeader("Content-disposition", "attachment; filename=reporteEmpleadosK.pdf");

            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void verListoVehiculoPDF() {
        try {
            File jasper = new File(FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getRealPath("/reportes/ListaVehiculosK.jasper"));
            
            JasperPrint reporteJasper = JasperFillManager.
                    fillReport(jasper.getPath(), null, Conexion.getConexion());
            
            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();
            
            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type", "application/pdf");
            
            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();
                        
        } catch (JRException | IOException e) {
            
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void descargaListoVehiculosPDF() {
        try {
            File jasper = new File(FacesContext
                    .getCurrentInstance().
                    getExternalContext()
                    .getRealPath("/reportes/ListaVehiculosK.jasper"));

            JasperPrint reporteJasper = JasperFillManager.
                    fillReport(jasper.getPath(), null, Conexion.getConexion());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();
          
            respuesta.addHeader("Content-disposition", "attachment; filename=reporteListaVehiculosK.pdf");

            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void verRentCar() {
        try {
            File jasper = new File(FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getRealPath("/reportes/RentCar.jasper"));
            
            JasperPrint reporteJasper = JasperFillManager.
                    fillReport(jasper.getPath(), null, Conexion.getConexion());
            
            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();
            
            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type", "application/pdf");
            
            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();
                        
        } catch (JRException | IOException e) {
            
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void descargaRentCar() {
        try {
            File jasper = new File(FacesContext
                    .getCurrentInstance().
                    getExternalContext()
                    .getRealPath("/reportes/RentCar.jasper"));

            JasperPrint reporteJasper = JasperFillManager.
                    fillReport(jasper.getPath(), null, Conexion.getConexion());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();
          
            respuesta.addHeader("Content-disposition", "attachment; filename=RentCar.pdf");

            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void verVehiculoPDF() {
        try {
            File jasper = new File(FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getRealPath("/reportes/VehiculoK.jasper"));
            
            JasperPrint reporteJasper = JasperFillManager.
                    fillReport(jasper.getPath(), null, Conexion.getConexion());
            
            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();
            
            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type", "application/pdf");
            
            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();
                        
        } catch (JRException | IOException e) {
            
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void verVehiculosPDF() {
        try {
            File jasper = new File(FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getRealPath("/reportes/vehiculo_clientK.jasper"));
            
            JasperPrint reporteJasper = JasperFillManager.
                    fillReport(jasper.getPath(), null, Conexion.getConexion());
            
            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();
            
            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type", "application/pdf");
            
            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();
                        
        } catch (JRException | IOException e) {
            
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    public void verUsuario(Usuario usuario) {
         Map<String, Object> parametrosReporte = new HashMap<>();
        parametrosReporte.put("idUsuario", usuario.getIdUsuario());
        try {
            File jasper = new File(FacesContext
                    .getCurrentInstance().
                    getExternalContext()
                    .getRealPath("/reportes/UsuarioK.jasper"));

            JasperPrint reporteJasper = JasperFillManager.
                    fillReport(jasper.getPath(), parametrosReporte, Conexion.getConexion());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();

            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type", "application/pdf");

            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    public void respaldoClienteVehiculo() {
        ZipOutputStream out = null;
        try {
            
            String json = UsuarioWS.generarJson();

            File f = new File(FacesContext
                    .getCurrentInstance().
                    getExternalContext()
                    .getRealPath("/respaldo") + "clientes.zip");
            out = new ZipOutputStream(new FileOutputStream(f));
            ZipEntry e = new ZipEntry("clientes.json");
            out.putNextEntry(e);
            byte[] data = json.getBytes();
            out.write(data, 0, data.length);
            out.closeEntry();
            out.close();

            File zipPath = new File(FacesContext
                    .getCurrentInstance().
                    getExternalContext()
                    .getRealPath("/respaldo") + "clientes.zip");

            byte[] zip = Files.readAllBytes(zipPath.toPath());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();
            ServletOutputStream flujo = respuesta.getOutputStream();

            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-disposition", "attachment; filename=clientes.zip");

            flujo.write(zip);
            flujo.flush();
            FacesContext.getCurrentInstance().responseComplete();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    
    
    
    
    
//     public void respaldoClienteVehiculo() {
//        ZipOutputStream out = null;
//        try {
//            
//            String json = EmpleadoGestion.generarJson() + ClienteGestion.generarJson() + VehiculoGestion.generarJson();
//
//            File f = new File(FacesContext
//                    .getCurrentInstance().
//                    getExternalContext()
//                    .getRealPath("/respaldo") + "respaldo.zip");
//            out = new ZipOutputStream(new FileOutputStream(f));
//            
//            ZipEntry e = new ZipEntry("respaldo.json");
//            out.putNextEntry(e);
//            byte[] data = json.getBytes();
//            out.write(data, 0, data.length);
//            out.closeEntry();
//            out.close();
//            
//            File zipPath = new File(FacesContext
//                    .getCurrentInstance().
//                    getExternalContext()
//                    .getRealPath("/respaldo") + "respaldo.zip");
//
//            byte[] zip = Files.readAllBytes(zipPath.toPath());
//
//            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
//                    .getExternalContext().getResponse();
//            ServletOutputStream flujo = respuesta.getOutputStream();
//
//            respuesta.setContentType("application/pdf");
//            respuesta.addHeader("Content-disposition", "attachment; filename=respaldo.zip");
//
//            flujo.write(zip);
//            flujo.flush();
//            FacesContext.getCurrentInstance().responseComplete();
//            
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                out.close();
//            } catch (IOException ex) {
//                Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//    }
//     public void respaldoEmpleado() {
//        ZipOutputStream out = null;
//        try {
//            
//            String json = EmpleadoGestion.generarJson();
//
//            File f = new File(FacesContext
//                    .getCurrentInstance().
//                    getExternalContext()
//                    .getRealPath("/respaldo") + "respaldoEmpleado.zip");
//            out = new ZipOutputStream(new FileOutputStream(f));
//            
//            ZipEntry e = new ZipEntry("respaldoEmpleado.json");
//            out.putNextEntry(e);
//            byte[] data = json.getBytes();
//            out.write(data, 0, data.length);
//            out.closeEntry();
//            out.close();
//            
//            File zipPath = new File(FacesContext
//                    .getCurrentInstance().
//                    getExternalContext()
//                    .getRealPath("/respaldo") + "respaldoEmpleado.zip");
//
//            byte[] zip = Files.readAllBytes(zipPath.toPath());
//
//            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
//                    .getExternalContext().getResponse();
//            ServletOutputStream flujo = respuesta.getOutputStream();
//
//            respuesta.setContentType("application/pdf");
//            respuesta.addHeader("Content-disposition", "attachment; filename=respaldoEmpleado.zip");
//
//            flujo.write(zip);
//            flujo.flush();
//            FacesContext.getCurrentInstance().responseComplete();
//            
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                out.close();
//            } catch (IOException ex) {
//                Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//    }

     
     
     
     public void respaldoEmpleado() {
        ZipOutputStream out = null;
        try {
            
            String json = EmpleadoWS.generarJson();

            File f = new File(FacesContext
                    .getCurrentInstance().
                    getExternalContext()
                    .getRealPath("/respaldo") + "empleados.zip");
            out = new ZipOutputStream(new FileOutputStream(f));
            ZipEntry e = new ZipEntry("empleados.json");
            out.putNextEntry(e);
            byte[] data = json.getBytes();
            out.write(data, 0, data.length);
            out.closeEntry();
            out.close();

            File zipPath = new File(FacesContext
                    .getCurrentInstance().
                    getExternalContext()
                    .getRealPath("/respaldo") + "empleados.zip");

            byte[] zip = Files.readAllBytes(zipPath.toPath());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();
            ServletOutputStream flujo = respuesta.getOutputStream();

            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-disposition", "attachment; filename=empleados.zip");

            flujo.write(zip);
            flujo.flush();
            FacesContext.getCurrentInstance().responseComplete();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
