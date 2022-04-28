/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi135.bachestpi.resources;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import sv.edu.ues.occ.ingenieria.tpi135.bachestpi.control.ObjetoBean;
import sv.edu.ues.occ.ingenieria.tpi135.bachestpi.entity.Objeto;

/**
 *
 * @author armandop444
 */
@Path("objeto")
@RequestScoped
public class ObjetoResource implements Serializable{
    
    @Inject
    ObjetoBean toBean;
    
    
    @GET
    @Path("nombre")
    public Response findNombre( 
            @QueryParam(value = "first") 
            @DefaultValue(value = "0")
            String nombre){
        List<Objeto> lista;
        
        lista=toBean.findNombre(nombre);
        
        return Response.ok(lista).build();
    }
}
