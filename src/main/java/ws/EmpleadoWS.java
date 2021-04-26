/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import gestion.EmpleadoGestion;
import java.text.ParseException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;
import model.Empleado;

/**
 * REST Web Service
 *
 * @author giank
 */
@Path("empleado")
@RequestScoped
public class EmpleadoWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EmpleadoWS
     */
    public EmpleadoWS() {
    }

    /**
     * Retrieves representation of an instance of ws.EmpleadoWS
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
  public List<Empleado> getEmpleados() throws ParseException{
        return EmpleadoGestion.getEmpleados();
    }


}
