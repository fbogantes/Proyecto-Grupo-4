/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Cliente;

/**
 *
 * @author ProyectoGrupo4
 */
public class ClienteGestion {

    private static final String SQL_GETCLIENTES = "SELECT * FROM cliente";
    private static final String SQL_GETCLIENTE = "SELECT * FROM cliente where id=? and idCliente=?";
    private static final String SQL_INSERTCLIENTE = "insert into cliente(idCliente,nombre,apellido,correo,celular) values (?,?,?,?,?)";
    private static final String SQL_UPDATECLIENTE= "update  cliente set nombre=?,apellido=?,correo=?,celular=? where id=? and idCliente=?";
    private static final String SQL_DELETECLIENTE = "Delete FROM cliente where id=? and idCliente=?";

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
                        rs.getString(6)
                ));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list;

    }

    public static Cliente getCliente(int id, String idCliente) {
        Cliente cliente = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETCLIENTE);
            sentencia.setInt(1, id);
            sentencia.setString(2, idCliente);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                cliente = new Cliente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
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
            sentencia.setString(1, cliente.getIdCliente());
            sentencia.setString(2, cliente.getNombre());
            sentencia.setString(3, cliente.getApellido());
            sentencia.setString(4, cliente.getCorreo());
            sentencia.setString(5, cliente.getCelular());
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean updateCliente(Cliente cliente) {
        try {

            PreparedStatement sentencia = Conexion.getConexion().prepareCall(SQL_UPDATECLIENTE);
            sentencia.setString(1, cliente.getNombre());
            sentencia.setString(2, cliente.getApellido());
            sentencia.setString(3, cliente.getCorreo());
            sentencia.setString(4, cliente.getCelular());
            sentencia.setInt(5, cliente.getId());
            sentencia.setString(6, cliente.getIdCliente());
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean deleteCliente(Cliente cliente) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_DELETECLIENTE);
            sentencia.setInt(1, cliente.getId());
            sentencia.setString(2, cliente.getIdCliente());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }
}
