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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import model.Conexion;
import model.Vehiculo;

/**
 *
 * @author Grupo4
 */
public class VehiculoGestion {
    
    private static final String SQL_GETVEHICULOS = "SELECT * FROM VEHICULO";
    private static final String SQL_GETVEHICULO = "SELECT * FROM VEHICULO WHERE ID_VEHICULO=? AND PLACA=?";
    private static final String SQL_GETVEHICULOReporte = "SELECT * FROM VEHICULO WHERE PLACA=?";
    private static final String SQL_INSERTVEHICULO= "insert into VEHICULO"
            + "(MARCA, MODELO, PLACA, ESTILO, YEAR, PUERTAS, CONDICION, CILINDRAJE, TRACCION, COMBUSTIBLE, KILOMETRAJE, COLOR_EXT, COLOR_INT, EXTRAS, PRECIO, NEGOCIABLE, TRASPASO, ID_CLIENTE, IMAGE) "
            + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATEVEHICULO= "update  VEHICULO set "
            + "MARCA=?,MODELO=?, ESTILO=?,YEAR=?, PUERTAS=?, CONDICION=?, CILINDRAJE=?, TRACCION=?, COMBUSTIBLE=?, KILOMETRAJE=?, COLOR_EXT=?, COLOR_INT=?, EXTRAS=?,PRECIO=?, NEGOCIABLE=?, TRASPASO=?, ID_CLIENTE=?, IMAGE=?"
            + "WHERE ID_VEHICULO=? AND PLACA=?";
    private static final String SQL_DELETEVEHICULO = "Delete FROM VEHICULO WHERE ID_VEHICULO AND PLACA";

    public static ArrayList<Vehiculo> getVehiculos() {
        ArrayList<Vehiculo> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETVEHICULOS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new Vehiculo(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getInt(16),
                        rs.getString(17),
                        rs.getString(18),
                        rs.getInt(19),
                        rs.getString(20))
                );

            }

        } catch (SQLException ex) {
            Logger.getLogger(VehiculoGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list;

    }

        public static Vehiculo infoVehiculo(int ID_VEHICULO,String PLACA) {
        Vehiculo vehiculo = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETVEHICULO);
            sentencia.setInt(1, ID_VEHICULO);
            sentencia.setString(2, PLACA);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                vehiculo = new Vehiculo(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getInt(16),
                        rs.getString(17),
                        rs.getString(18),
                        rs.getInt(19),
                        rs.getString(20)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(VehiculoGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return vehiculo;
    }
    
    public static Vehiculo getVehiculo(int ID_VEHICULO, String PLACA) {
        Vehiculo vehiculo = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETVEHICULO);
            sentencia.setInt(1, ID_VEHICULO);
            sentencia.setString(2, PLACA);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                vehiculo = new Vehiculo(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getInt(16),
                        rs.getString(17),
                        rs.getString(18),
                        rs.getInt(19),
                        rs.getString(20)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(VehiculoGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return vehiculo;
    }
    
    public static Vehiculo buscarVehiculo(String PLACA) {
        Vehiculo vehiculo = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETVEHICULOReporte);
            sentencia.setString(4, PLACA);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                vehiculo = new Vehiculo(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getInt(16),
                        rs.getString(17),
                        rs.getString(18),
                        rs.getInt(19),
                        rs.getString(20)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(VehiculoGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return vehiculo;
    }

    public static boolean insertVehiculo(Vehiculo vehiculo) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERTVEHICULO);
            sentencia.setString(1, vehiculo.getMARCA());
            sentencia.setString(2, vehiculo.getMODELO());
            sentencia.setString(3, vehiculo.getPLACA());
            sentencia.setString(4, vehiculo.getESTILO());
            sentencia.setInt(5, vehiculo.getYEAR());
            sentencia.setInt(6, vehiculo.getPUERTAS());
            sentencia.setString(7, vehiculo.getDESC_CONDICION());
            sentencia.setString(8, vehiculo.getCILINDRAJE());
            sentencia.setString(9, vehiculo.getTRACCION());
            sentencia.setString(10, vehiculo.getCOMBUSTIBLE());
            sentencia.setInt(11, vehiculo.getKILOMETRAJE());
            sentencia.setString(12, vehiculo.getCOLOR_EXTERIOR());
            sentencia.setString(13, vehiculo.getCOLOR_INTERIOR());
            sentencia.setString(14, vehiculo.getDESC_EXTRAS());
            sentencia.setInt(15, vehiculo.getPRECIO());
            sentencia.setString(16, vehiculo.getNEGOCIABLE());
            sentencia.setString(17, vehiculo.getTRASPASO());
            sentencia.setInt(18, vehiculo.getID_CLIENTE());
            sentencia.setString(19, vehiculo.getIMAGE());
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(VehiculoGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean updateVehiculo(Vehiculo vehiculo) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareCall(SQL_UPDATEVEHICULO);
            sentencia.setString(1, vehiculo.getMARCA());
            sentencia.setString(2, vehiculo.getMODELO());
            sentencia.setString(3, vehiculo.getESTILO());
            sentencia.setInt(4, vehiculo.getYEAR());
            sentencia.setInt(5, vehiculo.getPUERTAS());
            sentencia.setString(6, vehiculo.getDESC_CONDICION());
            sentencia.setString(7, vehiculo.getCILINDRAJE());
            sentencia.setString(8, vehiculo.getTRACCION());
            sentencia.setString(9, vehiculo.getCOMBUSTIBLE());
            sentencia.setInt(10, vehiculo.getKILOMETRAJE());
            sentencia.setString(11, vehiculo.getCOLOR_EXTERIOR());
            sentencia.setString(12, vehiculo.getCOLOR_INTERIOR());
            sentencia.setString(13, vehiculo.getDESC_EXTRAS());
            sentencia.setInt(14, vehiculo.getPRECIO());
            sentencia.setString(15, vehiculo.getNEGOCIABLE());
            sentencia.setString(16, vehiculo.getTRASPASO());
            sentencia.setInt(17, vehiculo.getID_CLIENTE());
            sentencia.setString(18, vehiculo.getIMAGE());
            sentencia.setInt(19, vehiculo.getID_VEHICULO());
            sentencia.setString(20, vehiculo.getPLACA());
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(VehiculoGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean deleteVehiculo(Vehiculo vehiculo) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_DELETEVEHICULO);
            sentencia.setInt(1, vehiculo.getID_VEHICULO());
            sentencia.setString(2, vehiculo.getPLACA());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(VehiculoGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }
    
    public static String generarJson() {
        Vehiculo vehiculo = null;
        String tiraJson = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETVEHICULOS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                vehiculo = new Vehiculo(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getInt(16),
                        rs.getString(17),
                        rs.getString(18),
                        rs.getInt(19),
                        rs.getString(20)
                );

                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("id", vehiculo.getID_VEHICULO())
                        .add("Marca", vehiculo.getMARCA())
                        .add("Modelo", vehiculo.getMODELO())
                        .add("Placa", vehiculo.getPLACA())
                        .add("Estilo", vehiculo.getESTILO())
                        .add("Año", vehiculo.getYEAR())
                        .add("Puertas", vehiculo.getPUERTAS())
                        .add("Condicion", vehiculo.getDESC_CONDICION())
                        .add("Cilindraje", vehiculo.getCILINDRAJE())
                        .add("Traccion", vehiculo.getTRACCION())
                        .add("Combustible", vehiculo.getCOMBUSTIBLE())
                        .add("Kilometraje", vehiculo.getKILOMETRAJE())
                        .add("Color Externo", vehiculo.getCOLOR_EXTERIOR())
                        .add("Color Interno", vehiculo.getCOLOR_INTERIOR())
                        .add("Extras", vehiculo.getDESC_EXTRAS())
                        .add("Precio", vehiculo.getPRECIO())
                        .add("Negocio", vehiculo.getNEGOCIABLE())
                        .add("Traspaso", vehiculo.getTRASPASO())
                        .add("ID Cliente", vehiculo.getID_CLIENTE())
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
            Logger.getLogger(VehiculoGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return tiraJson;
    }
    
  
}
