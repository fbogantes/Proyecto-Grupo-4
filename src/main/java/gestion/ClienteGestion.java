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
import model.Cliente;

/**
 *
 * @author ProyectoGrupo4
 */
public class ClienteGestion {

    private static final String SQL_GETCLIENTES = "SELECT * FROM cliente";
    private static final String SQL_GETCLIENTE = "SELECT * FROM cliente where ID_CLIENTE=? and CEDULA=?";
    private static final String SQL_INSERTCLIENTE = "insert into cliente(CEDULA,NOMBRE,APELLIDO_1,APELLIDO_2,CORREO,TELEFONO,UBICACION) values (?,?,?,?,?,?,?)";
    private static final String SQL_UPDATECLIENTE= "update  cliente set NOMBRE=?,APELLIDO_1=?,APELLIDO_2=?,CORREO=?,TELEFONO=?,UBICACION=? where ID_CLIENTE=? and CEDULA=?";
    private static final String SQL_DELETECLIENTE = "Delete FROM cliente where ID_CLIENTE=? and CEDULA=?";

    public static ArrayList<Cliente> getClientes() {
        ArrayList<Cliente> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETCLIENTES);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new Cliente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                ));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list;

    }

    public static Cliente getCliente(int ID_CLIENTE, String CEDULA) {
        Cliente cliente = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETCLIENTE);
            sentencia.setInt(1, ID_CLIENTE);
            sentencia.setString(2, CEDULA);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                cliente = new Cliente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return cliente;
    }

    public static boolean insertCliente(Cliente cliente) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERTCLIENTE);
            sentencia.setString(1, cliente.getCEDULA());
            sentencia.setString(2, cliente.getNOMBRE());
            sentencia.setString(3, cliente.getAPELLIDO_1());
            sentencia.setString(4, cliente.getAPELLIDO_2());
            sentencia.setString(5, cliente.getCORREO());
            sentencia.setString(6, cliente.getTELEFONO());
            sentencia.setString(7, cliente.getUBICACION());
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean updateCliente(Cliente cliente) {
        try {

            PreparedStatement sentencia = Conexion.getConexion().prepareCall(SQL_UPDATECLIENTE);
            sentencia.setString(1, cliente.getNOMBRE());
            sentencia.setString(2, cliente.getAPELLIDO_1());
            sentencia.setString(3, cliente.getAPELLIDO_2());
            sentencia.setString(4, cliente.getCORREO());
            sentencia.setString(5, cliente.getTELEFONO());
            sentencia.setString(6, cliente.getUBICACION());
            sentencia.setInt(7, cliente.getID_CLIENTE());
            sentencia.setString(8, cliente.getCEDULA());
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean deleteCliente(Cliente cliente) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_DELETECLIENTE);
            sentencia.setInt(1, cliente.getID_CLIENTE());
            sentencia.setString(2, cliente.getCEDULA());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }
    
    public static String generarJson() {
        Cliente cliente = null;
        String tiraJson = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETCLIENTES);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                cliente = new Cliente(
                         rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                );

                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("ID_CLIENTE", cliente.getID_CLIENTE())
                        .add("CEDULA", cliente.getCEDULA())
                        .add("NOMBRE", cliente.getNOMBRE())
                        .add("APELLIDO_1", cliente.getAPELLIDO_1())
                        .add("APELLIDO_2", cliente.getAPELLIDO_2())
                        .add("CORREO", cliente.getCORREO())
                        .add("TELEFONO", cliente.getTELEFONO())
                        .add("UBICACION", cliente.getUBICACION())
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
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return tiraJson;
    }
}
