/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi135.bachestpi.resources;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import sv.edu.ues.occ.ingenieria.tpi135.bachestpi.control.TipoObjetoBean;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import sv.edu.ues.occ.ingenieria.tpi135.bachestpi.resources.entity.TipoObjeto;

/**
 *
 * @author armandop444
 */
@Path("tipoobjeto")
@RequestScoped
public class TipoObjetoResource {

    @Inject
    TipoObjetoBean toBean;

    @GET
    @Produces({"application/json; charset=UTF-8"})
    public Response findAll() {
        List<TipoObjeto> registros = toBean.findAll();
        Long total = toBean.contar();

        return Response.ok(registros)
                .header("Total-Registro", total)
                .build();

    }

}
