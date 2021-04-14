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
import model.Usuario;

/**
 *
 * @author ProyectoGrupo4
 */
public class UsuarioGestion {

    private static final String SQL_GETUSUARIOReporte = "SELECT * FROM usuario where  idUsuario=?";
    private static final String SQL_GETUSUARIO = "Select * from usuario where idUsuario=? and pwUsuario=MD5(?)";
    private static final String SQL_GETUSUARIOS = "SELECT * FROM usuario";

    public static ArrayList<Usuario> getUsuarios() {
        ArrayList<Usuario> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETUSUARIOS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new Usuario(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                ));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list;

    }
    public static String generarJson() {
        Usuario usuario = null;
        String tiraJson = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETUSUARIOS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                usuario = new Usuario(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
                
                
                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson
                        .add("idUsuario", usuario.getIdUsuario())
                        .add("pwusuario", usuario.getPwUsuario())
                        .add("nombreUsuario", usuario.getNombreUsuario())
                        .add("idRol", usuario.getIdRol())
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
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return tiraJson;
    }
    
    
    

    public static Usuario getUsuario(String idUsuario, String pwUsuario) {
        Usuario usuario = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETUSUARIO);
            sentencia.setString(1, idUsuario);
            sentencia.setString(2, pwUsuario);
            ResultSet rs = sentencia.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(idUsuario);
                usuario.setPwUsuario(pwUsuario);
                usuario.setNombreUsuario(rs.getString(4));
                usuario.setIdRol(rs.getString(5));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);

        }

        return usuario;

    }
    
    public static Usuario buscarUsuario(String idUsuario) {
        Usuario usuario = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETUSUARIOReporte);
            sentencia.setString(1, idUsuario);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                usuario = new Usuario(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return usuario;
    }
}
