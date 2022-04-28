/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi135.bachestpi.resources;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author armandop444
 */
@Provider
public class CorsFilter implements ContainerResponseFilter{

    @Override
    public void filter(ContainerRequestContext requestContext,
            ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().add("Access-COntrol-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-COntrol-Allow-Credentials","true");
        responseContext.getHeaders().add("Access-COntrol-Allow-Headers", "origin,content-type,accept,authorization");
        responseContext.getHeaders().add("Access-COntrol-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
    }
    
}