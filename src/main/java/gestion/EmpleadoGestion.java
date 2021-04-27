/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import model.Conexion;
import model.Empleado;
import model.YearGender;

/**
 *
 * @author ProyectoGrupo4
 */
public class EmpleadoGestion {

    private static final String SQL_GETEMPLEADOS = "SELECT * FROM empleado";
    private static final String SQL_GETEMPLEADO = "SELECT * FROM empleado where id=? and idEmpleado=?";
    private static final String SQL_GETEMPLEADOREPORTE = "SELECT * FROM empleado where idEmpleado=?";
    private static final String SQL_INSERTEMPLEADO = "insert into empleado(idEmpleado,nombre,apellido1,apellido2,fechaNacimiento,fechaIngreso,correo,celular) values (?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATEEMPLEADO = "update  empleado set nombre=?,apellido1=?,apellido2=?,fechaNacimiento=?,fechaIngreso=?,correo=?,celular=? where id=? and idEmpleado=?";
    private static final String SQL_DELETEEMPLEADO = "Delete FROM empleado where id=? and idEmpleado=?";
    private static final String SQL_INGRESO_YEAR_GENDER = "SELECT YEAR(fechaIngreso) as Fecha,genero,Count(*) total FROM empleado group by  YEAR(fechaIngreso),genero order by YEAR(fechaIngreso)";

    public static ArrayList<Empleado> getEmpleados() {
        ArrayList<Empleado> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETEMPLEADOS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new Empleado(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDate(7),
                        rs.getString(8),
                        rs.getString(9)
                ));

            }

        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list;

    }

    public static Empleado getEmpleado(int id, String idEmpleado) {
        Empleado empleado = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETEMPLEADO);
            sentencia.setInt(1, id);
            sentencia.setString(2, idEmpleado);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                empleado = new Empleado(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDate(7),
                         rs.getString(8),
                        rs.getString(9)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return empleado;
    }

    public static Empleado buscarEmpleado(String idEmpleado) {
        Empleado empleado = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETEMPLEADOREPORTE);
            sentencia.setString(2, idEmpleado);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                empleado = new Empleado(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDate(7),
                         rs.getString(8),
                        rs.getString(9)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return empleado;
    }
    
    public static ArrayList<YearGender> getIngresoYearGender() {
        ArrayList<YearGender> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INGRESO_YEAR_GENDER);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new YearGender(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list;

    }

    public static boolean insertEmpleado(Empleado empleado) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERTEMPLEADO);
            sentencia.setString(1, empleado.getIdEmpleado());
            sentencia.setString(2, empleado.getNombre());
            sentencia.setString(3, empleado.getApellido1());
            sentencia.setString(4, empleado.getApellido2());
            sentencia.setObject(5, empleado.getFechaNacimiento());
            sentencia.setObject(6, empleado.getFechaIngreso());
            sentencia.setString(7, empleado.getCorreo());
            sentencia.setString(8, empleado.getCelular());
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean updateEmpleado(Empleado empleado) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareCall(SQL_UPDATEEMPLEADO);
            sentencia.setString(1, empleado.getNombre());
            sentencia.setString(2, empleado.getApellido1());
            sentencia.setString(3, empleado.getApellido2());
            sentencia.setObject(4, empleado.getFechaNacimiento());
            sentencia.setObject(5, empleado.getFechaIngreso());
            sentencia.setString(6, empleado.getCorreo());
            sentencia.setString(7, empleado.getCelular());
            sentencia.setInt(8, empleado.getId());
            sentencia.setString(9, empleado.getIdEmpleado());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean deleteEmpleado(Empleado empleado) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_DELETEEMPLEADO);
            sentencia.setInt(1, empleado.getId());
            sentencia.setString(2, empleado.getIdEmpleado());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }
    
    public static String generarJson() {
        Empleado empleado = null;
        String tiraJson = "";
        String fecha1 = "";
        String fecha2 = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETEMPLEADOS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                empleado = new Empleado(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDate(7),
                        rs.getString(8),
                        rs.getString(9)
                );

                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("id", empleado.getId())
                        .add("idCliente", empleado.getIdEmpleado())
                        .add("nombre", empleado.getNombre())
                        .add("apellido1", empleado.getApellido1())
                        .add("apellido2", empleado.getApellido2())
                        .add("fechaNacimiento", fecha1)
                        .add("fechaIngreso", fecha2)
                        .add("correo", empleado.getCorreo())
                        .add("celular", empleado.getCelular())
                        .build();
                StringWriter tira = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(tira);
                jsonWriter.writeObject(objectJson);
                if (tiraJson == null) {
                    tiraJson = tira.toString() + "\n";
                } else {
                    tiraJson = tiraJson + tira.toString() + "\n";
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return tiraJson;
    }
}
