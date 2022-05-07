/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi135.bachestpi.resources;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import sv.edu.ues.occ.ingenieria.tpi135.bachestpi.control.ObjetoEstadoBean;
import sv.edu.ues.occ.ingenieria.tpi135.bachestpi.entity.ObjetoEstado;

/**
 *
 * @author armandop444
 */
@Path("objetoestado")
@RequestScoped
public class ObjetoEstadoResource implements Serializable {

    @Inject
    ObjetoEstadoBean toBean;

    public Response findAll() {
        List<ObjetoEstado> registros = toBean.findAll();
        Long total = toBean.contar();

        return Response.ok(registros)
                .header("Total-Registro", total)
                .build();

    }

    @GET
    @Path("contar")
    public CompletableFuture<Long> contar() {
        return CompletableFuture.supplyAsync(toBean::contar);
    }

    @POST
    public Response crea(ObjetoEstado nuevo) {
        toBean.crear(nuevo);
        return Response.ok(nuevo)
                .header("Registro-Creado", nuevo)
                .build();
    }

    @PUT
    public Response modificar(ObjetoEstado edit) {
        toBean.Modificar(edit);
        return Response.ok(edit)
                .header("Modificado", edit)
                .build();

    }

    @DELETE
    @Path("{userId}")
    public Response eliminar(@PathParam("userId") Long id) {
        ObjetoEstado eliminar = new ObjetoEstado();
        eliminar.setIdObjetoEstado(id);
        toBean.eliminar(eliminar);
        return Response.ok(eliminar)
                .header("ID-eliminado", id)
                .build();
    }

    @GET
    @Produces({"application/json; charset=UTF-8"})
    public Response findRange(
            @QueryParam(value = "first")
            @DefaultValue(value = "0") int firts,
            @QueryParam(value = "pagesize")
            @DefaultValue(value = "50") int pagueSize) {
        List<ObjetoEstado> registros = toBean.findRange(firts, pagueSize);
        Long total = toBean.contar();
        return Response.ok(registros)
                .header("Total-Registro", total)
                //                .header("Access-Control-Allow-Origin", "*")
                //                .header("Access-Control-Allow-Credentials", "true")
                //                .header("Access-Control-Allow-Headers", "origins,content-type,accept,authorization")
                //                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .build();
    }

}
