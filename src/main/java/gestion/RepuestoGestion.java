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
import model.Repuesto;

/**
 *
 * @author Grupo4
 */
public class RepuestoGestion {
    
    private static final String SQL_SELECT_REPUESTOS = "SELECT * FROM REPUESTO";
    private static final String SQL_SELECT_REPUESTO = "SELECT * FROM REPUESTO WHERE ID=?";
    private static final String SQL_INSERT_REPUESTO = "INSERT INTO REPUESTO(descripcion,precio)VALUES(?,?)";
    private static final String SQL_UPDATE_REPUESTO = "UPDATE REPUESTO SET DESCRIPCION=?,PRECIO=? WHERE ID=?";
    private static final String SQL_DELETE_REPUESTO = "Delete from REPUESTO where ID=?";

    public static ArrayList<Repuesto> getRepuestos() {
        ArrayList<Repuesto> lista = new ArrayList<>();
        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_SELECT_REPUESTOS);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                lista.add(new Repuesto(
                        datos.getInt(1),
                        datos.getString(2),
                        datos.getDouble(3)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(RepuestoGestion.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return lista;
    }

    public static boolean insertar(Repuesto repuesto) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERT_REPUESTO);
            sentencia.setString(1, repuesto.getDescripcion());
            sentencia.setDouble(2, repuesto.getPrecio());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(RepuestoGestion.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean modificar(Repuesto repuesto) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_UPDATE_REPUESTO);
            sentencia.setString(1, repuesto.getDescripcion());
            sentencia.setDouble(2, repuesto.getPrecio());
            sentencia.setInt(3, repuesto.getId());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(RepuestoGestion.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean eliminar(int id) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_DELETE_REPUESTO);
            sentencia.setInt(1, id);
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(RepuestoGestion.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return false;
    }

    public static Repuesto getRepuesto(int id) {
        Repuesto repuesto = null;
        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_SELECT_REPUESTO);
            consulta.setInt(1, id);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                repuesto = new Repuesto(
                        datos.getInt(1),
                        datos.getString(2),
                        datos.getDouble(3));

            }
        } catch (SQLException ex) {
            Logger.getLogger(RepuestoGestion.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return repuesto;
    }

    public static String generarJson() {
        Repuesto repuesto = null;
        String tiraJson = "";
        String fechaNaci;
        String fechaIngr;

        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_SELECT_REPUESTOS);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                repuesto = new Repuesto(
                        datos.getInt(1),
                        datos.getString(2),
                        datos.getDouble(3));

                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objetoJson = creadorJson.add("id", repuesto.getId())
                        .add("descripcion", repuesto.getDescripcion())
                        .add("precio", repuesto.getPrecio()).build();

                StringWriter tira = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(tira);
                jsonWriter.writeObject(objetoJson);
                if (tiraJson == null) {
                    tiraJson = tira.toString() + "\n";
                } else {
                    tiraJson = tiraJson + tira.toString() + "\n";
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(RepuestoGestion.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return tiraJson;
    }
}
