/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi135.bachestpi.control;

import java.io.StringReader;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import sv.edu.ues.occ.ingenieria.tpi135.bachestpi.entity.TipoObjeto;
import sv.edu.ues.occ.ingenieria.tpi135.bachestpi.resources.JakartaRestConfiguration;
import sv.edu.ues.occ.ingenieria.tpi135.bachestpi.resources.TipoObjetoResource;

/**
 *
 * @author armandop444
 */
@ExtendWith(ArquillianExtension.class)
public class TipoObjetoResourceIT {
    
    @Deployment
    public static WebArchive crearDespliegue() {
        WebArchive salida = ShrinkWrap.create(WebArchive.class)
                .addPackage("sv.edu.ues.occ.ingenieria.tpi135.bachestpi.entity")
                .addAsResource("persistence-arquillian.xml")
                .addClass(abstractDataAccess.class)
                .addClass(TipoObjetoBean.class)
                .addClass(JakartaRestConfiguration.class)
                .addClass(TipoObjetoResource.class)
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsResource("META-INF/sql/datos.sql", "META-INF/sql/datos.sql")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(salida.toString(true));
        return salida;
    }
    
    @ArquillianResource
    URL url;
    
    @Test
    @RunAsClient
    public void testFindAll(){
        System.out.println("\n\n");
        System.out.println("\n\n");
        System.out.println("--------------------------------------------------------------");
        System.out.println("findAllTipoObjeto");
        int resultadoEsperado=200;
        Client cliente=ClientBuilder.newClient();
        WebTarget target= cliente.target(url.toString()+"resources/");
        Response respuesta = target.path("tipoobjeto").request("application/json").get(); 
        assertEquals(resultadoEsperado, respuesta.getStatus());
        String totalTexto = respuesta.getHeaderString("Total-Registros");
        assertNotEquals(Integer.valueOf(0), Integer.valueOf(totalTexto));
        String cuerpoString = respuesta.readEntity(String.class);
        JsonReader lector = Json.createReader(new StringReader(cuerpoString));
        JsonArray listaJson = lector.readArray();
        int totalRegistros = listaJson.size();
        assertTrue(totalRegistros>0);
        System.out.println("\n\n");
        System.out.println("\n\n");
        for(int i=0; i< listaJson.size(); i++){
            JsonObject objeto = listaJson.getJsonObject(i);
            System.out.println("ID: " + objeto.getInt("idTipoObjeto"));
        }
        System.out.println("\n\n");
        System.out.println("\n\n");
    }
    
    @Test
    @RunAsClient
    public void crear() {
        System.out.println("\n\n");
        System.out.println("\n\n");
        System.out.println("--------------------------------------------------------------");        
        System.out.println("Crear TipoObjeto");
        TipoObjeto nuevo = new TipoObjeto();
        nuevo.setActivo(Boolean.TRUE);

        int resultadoEsperado=200;
        Client cliente=ClientBuilder.newClient();
        WebTarget target= cliente.target(url.toString()+"resources/");
        Response respuesta = target.path("tipoobjeto").request("application/json").post(Entity.entity(nuevo, MediaType.APPLICATION_JSON)); 
        assertEquals(resultadoEsperado, respuesta.getStatus());
        String registro = respuesta.getHeaderString("Registro-Creado");
        assertNotEquals(null, registro);
        String cuerpoString = respuesta.readEntity(String.class);
        JsonReader lector = Json.createReader(new StringReader(cuerpoString));
        JsonObject objeto = lector.readObject();
        System.out.println("\n\n");
        System.out.println("\n\n");
        System.out.println("Creado "+ objeto);
        System.out.println("\n\n");
        System.out.println("\n\n");       
    }
}
