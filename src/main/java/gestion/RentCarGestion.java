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
import model.RentCar;

/**
 *
 * @author valer
 */
public class RentCarGestion {

    private static final String SQL_GETRENTS = "SELECT * FROM rentcar";
    private static final String SQL_GETRENT = "SELECT * FROM rentcar where idRentCar=? and Nombre=?";
    private static final String SQL_INSERTRENT = "insert into rentcar (idRentCar,Nombre,Ubicacion,Estilo,Correo,Telefono) values (?,?,?,?,?,?)";
    private static final String SQL_UPDATERENT = "update rentcar set Nombre=?,Ubicacion=?,Estilo=?,Correo=?,Telefono=? where idRentCar=?";
    private static final String SQL_DELETERENT = "Delete FROM rentcar where idRentCar=? and Nombre=?";

    public static ArrayList<RentCar> getRentCars() {
        ArrayList<RentCar> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETRENTS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new RentCar(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                ));

            }

        } catch (SQLException ex) {
            Logger.getLogger(RentCarGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list;

    }

    public static RentCar getRentCar(int idRentCar, String Nombre) {
        RentCar rent = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETRENT);
            sentencia.setInt(1, idRentCar);
            sentencia.setString(2, Nombre);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                rent = new RentCar(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(RentCarGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return rent;
    }

    public static boolean insertRentCar(RentCar rent) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERTRENT);
            sentencia.setInt(1, rent.getIdRentCar());
            sentencia.setString(2, rent.getNombre());
            sentencia.setString(3, rent.getUbicacion());
            sentencia.setString(4, rent.getEstilo());
            sentencia.setString(5, rent.getCorreo());
            sentencia.setString(6, rent.getTelefono());
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(RentCarGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean updateRentCar(RentCar rent) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareCall(SQL_UPDATERENT);
            sentencia.setString(1, rent.getNombre());
            sentencia.setString(2, rent.getUbicacion());
            sentencia.setString(3, rent.getEstilo());
            sentencia.setString(4, rent.getCorreo());
            sentencia.setString(5, rent.getTelefono());
            sentencia.setInt(6, rent.getIdRentCar());

            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(RentCarGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean deleteRentCar(RentCar rent) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_DELETERENT);
            sentencia.setInt(1, rent.getIdRentCar());
            sentencia.setString(2, rent.getNombre());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(RentCarGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static String generarJson() {
        RentCar rent = null;
        String tiraJson = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETRENTS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                rent = new RentCar(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                );

                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("idRentCar", rent.getIdRentCar())
                        .add("Nombre", rent.getNombre())
                        .add("Ubicacion", rent.getUbicacion())
                        .add("Correo", rent.getCorreo())
                        .add("Estilo", rent.getEstilo())
                        .add("Correo", rent.getCorreo())
                        .add("Telefono", rent.getTelefono())
                        
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
            Logger.getLogger(RentCarGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return tiraJson;
    }

}