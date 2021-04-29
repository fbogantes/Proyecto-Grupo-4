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
import model.Taller;

/**
 *
 * @author Grupo4
 */
public class TallerGestion {
    
    private static final String SQL_GETTALLERES = "SELECT * FROM taller";
    private static final String SQL_GETTALLER = "SELECT * FROM taller where id=? and nombre=?";
    private static final String SQL_INSERTTALLER = "insert into taller(id, nombre, ubicacion,telefono) values (?,?,?,?)";
    private static final String SQL_UPDATETALLER= "update  taller set nombre=?,ubicacion=?,telefono=? where id=?";
    private static final String SQL_DELETETALLER = "Delete FROM taller where id=? and nombre=?";

    
    public static ArrayList<Taller> getTalleres() {
        ArrayList<Taller> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETTALLERES);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new Taller(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                ));

            }

        } catch (SQLException ex) {
            Logger.getLogger(TallerGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list;

    }

    public static Taller getTaller(int id, String nombre) {
        Taller taller = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETTALLER);
            sentencia.setInt(1, id);
            sentencia.setString(2, nombre);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                taller = new Taller(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(TallerGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return taller;
    }

    public static boolean insertTaller(Taller taller) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERTTALLER);
            sentencia.setInt(1, taller.getId());
            sentencia.setString(2, taller.getNombre());
            sentencia.setString(3, taller.getUbicacion());
            sentencia.setString(4, taller.getTelefono());
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(TallerGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean updateTaller(Taller taller) {
        try {

            PreparedStatement sentencia = Conexion.getConexion().prepareCall(SQL_UPDATETALLER);
            sentencia.setString(1, taller.getNombre());
            sentencia.setString(2, taller.getUbicacion());
            sentencia.setString(3, taller.getTelefono());
            sentencia.setInt(4, taller.getId());
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(TallerGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean deleteTaller(Taller taller) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_DELETETALLER);
            sentencia.setInt(1, taller.getId());
            sentencia.setString(2, taller.getNombre());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TallerGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }
    
    public static String generarJson() {
        Taller taller = null;
        String tiraJson = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETTALLER);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                taller = new Taller(
                         rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );

                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("id", taller.getId())
                        .add("nombre", taller.getNombre())
                        .add("ubicacion", taller.getUbicacion())
                        .add("telefono", taller.getTelefono())
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
            Logger.getLogger(TallerGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return tiraJson;
    }
    
}
