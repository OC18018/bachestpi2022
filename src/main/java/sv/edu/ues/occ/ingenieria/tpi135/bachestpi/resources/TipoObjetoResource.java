/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi135.bachestpi.resources;

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
import sv.edu.ues.occ.ingenieria.tpi135.bachestpi.control.TipoObjetoBean;
import sv.edu.ues.occ.ingenieria.tpi135.bachestpi.entity.TipoObjeto;

/**
 *
 * @author armandop444
 */
@Path("tipoobjeto")
@RequestScoped
public class TipoObjetoResource {

    @Inject
    TipoObjetoBean toBean;

//    public Response findAll() {
//        List<TipoObjeto> registros = toBean.findAll();
//        Long total = toBean.contar();
//
//        return Response.ok(registros)
//                .header("Total-Registros", total)
//                .build();
//
//    }
    @POST
    public Response crear(TipoObjeto nuevo) {
        toBean.crear(nuevo);
        return Response.ok(nuevo)
                .header("Registro-Creado", nuevo)
                .build();
    }

    @PUT
    public Response modificar(TipoObjeto edit) {
        toBean.Modificar(edit);
        return Response.ok(edit)
                .header("Modificado", edit)
                .build();

    }

    @DELETE
    @Path("{userId}")
    public Response eliminar(@PathParam("userId") int id) {
        TipoObjeto eliminar = new TipoObjeto();
        eliminar.setIdTipoObjeto(id);
        toBean.eliminar(eliminar);
        return Response.ok(eliminar)
                .header("ID-eliminado", id)
                .build();
    }

    @GET
    @Produces({"application/json; charset=UTF-8"})
    public Response findRange(
            @QueryParam(value = "first")
            @DefaultValue(value = "0") int first,
            @QueryParam(value = "pagesize")
            @DefaultValue(value = "50") int pageSize) {
        List<TipoObjeto> registros = toBean.findRange(first, pageSize);
        Long total = toBean.contar();
        return Response.ok(registros)
                .header("Total-Registros", total)
                .build();
    }

    @GET
    @Path("contar")
    public CompletableFuture<Long> contar() {
        return CompletableFuture.supplyAsync(toBean::contar);
    }

    @GET
    @Path("findId")
    public Response findId(@QueryParam(value = "id") Integer idTipoObjeto) {
        List<TipoObjeto> lista;

        lista = toBean.findByIdTipoObjeto(idTipoObjeto);
        Long total = toBean.contar();
        return Response.ok(lista).header("Total-Registro", total).build();
    }
}
