/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi135.bachestpi.resources;

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
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.test.api.ArquillianResource;
import static org.junit.jupiter.api.Assertions.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import sv.edu.ues.occ.ingenieria.tpi135.bachestpi.entity.Estado;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import sv.edu.ues.occ.ingenieria.tpi135.bachestpi.control.EstadoBean;
import sv.edu.ues.occ.ingenieria.tpi135.bachestpi.control.abstractDataAccess;
/**
 *
 * @author armandop444
 */
@ExtendWith(ArquillianExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class EstadoResourcesIT {

    @Deployment
    public static WebArchive crearDespliegue() {
        WebArchive salida = ShrinkWrap.create(WebArchive.class)
                .addPackage("sv.edu.ues.occ.ingenieria.tpi135.bachestpi.entity")
                .addAsResource("persistence-arquillian.xml")
                .addClass(abstractDataAccess.class)
                .addClass(EstadoBean.class)
                .addClass(JakartaRestConfiguration.class)
                .addClass(EstadoResource.class)
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
    @Order(4)
    public void testFindAll() {
        System.out.println("findAllEstado");

        int resultadoEsperado = 200;
        Client cliente = ClientBuilder.newClient();
        WebTarget target = cliente.target(url.toString() + "resources/");
        Response respuesta = target.path("estado").request("application/json").get();
        assertEquals(resultadoEsperado, respuesta.getStatus());
        String totalTexto = respuesta.getHeaderString("Total-Registro");
        assertNotEquals(Integer.valueOf(0), Integer.valueOf(totalTexto));
        String cuerpoString = respuesta.readEntity(String.class);
        JsonReader lector = Json.createReader(new StringReader(cuerpoString));
        JsonArray listaJson = lector.readArray();
        int totalRegistros = listaJson.size();
        assertTrue(totalRegistros > 0);
        System.out.println("\n\n");
        System.out.println("\n\n");
        for (int i = 0; i < listaJson.size(); i++) {
            JsonObject objeto = listaJson.getJsonObject(i);
            System.out.println("ID: " + objeto.getInt("idEstado"));
        }
    }

    @Test
    @RunAsClient
    @Order(1)
    public void testCrear() {
        System.out.println("Crear Estado");
        Estado nuevo = new Estado();
        nuevo.setNombre("creado desde prueba");

        int resultadoEsperado = 200;
        Client cliente = ClientBuilder.newClient();
        WebTarget target = cliente.target(url.toString() + "resources/");
        Response respuesta = target.path("estado").request("application/json").post(Entity.entity(nuevo, MediaType.APPLICATION_JSON));
        assertEquals(resultadoEsperado, respuesta.getStatus());
        String registro = respuesta.getHeaderString("Registro-Creado");
        assertNotEquals(null, registro);
        String cuerpoString = respuesta.readEntity(String.class);
        JsonReader lector = Json.createReader(new StringReader(cuerpoString));
        JsonObject objeto = lector.readObject();
        System.out.println("\n\n");
        System.out.println("\n\n");
        System.out.println("Creado " + objeto);
        System.out.println("\n\n");
        System.out.println("\n\n");
    }

    @Test
    @RunAsClient
    @Order(2)
    public void testModificar() {
        System.out.println("Modificar estado");
        Estado nuevo = new Estado();
        nuevo.setIdEstado(3);
        nuevo.setNombre("modificado desde prueba");

        int resultadoEsperado = 200;
        Client cliente = ClientBuilder.newClient();
        WebTarget target = cliente.target(url.toString() + "resources/");
        Response respuesta = target.path("estado").request("application/json").put(Entity.entity(nuevo, MediaType.APPLICATION_JSON));
        assertEquals(resultadoEsperado, respuesta.getStatus());
        String registro = respuesta.getHeaderString("Modificado");
        assertNotEquals(null, registro);
        String cuerpoString = respuesta.readEntity(String.class);
        JsonReader lector = Json.createReader(new StringReader(cuerpoString));
        JsonObject objeto = lector.readObject();
        System.out.println("\n\n");
        System.out.println("\n\n");
        System.out.println("Modificado " + objeto);
        System.out.println("\n\n");
        System.out.println("\n\n");

    }

    @Test
    @RunAsClient
    @Order(3)
    public void testEliminar() {
        System.out.println("Eliminar estado");
        Estado nuevo = new Estado();

        int resultadoEsperado = 200;
        Client cliente = ClientBuilder.newClient();
        WebTarget target = cliente.target(url.toString() + "resources/");
        Response respuesta = target.path("estado/2").request("application/json").delete();
        assertEquals(resultadoEsperado, respuesta.getStatus());
        String registro = respuesta.getHeaderString("ID-eliminado");
        assertNotEquals(null, registro);
        String cuerpoString = respuesta.readEntity(String.class);
        JsonReader lector = Json.createReader(new StringReader(cuerpoString));
        JsonObject objeto = lector.readObject();

        System.out.println("\n\n");
        System.out.println("\n\n");
        System.out.println("ID:" + objeto.getInt("idEstado") + " eliminado con exito");
        System.out.println("\n\n");
        System.out.println("\n\n");
    }

    @Test
    @RunAsClient
    @Order(5)
    public void testFindNombre() {
        System.out.println("findNombre");
        
        int resultadoEsperado = 200;
        Client cliente = ClientBuilder.newClient();
        WebTarget target = cliente.target(url.toString() + "resources/");
        //Response respuesta = target.path("estado?findNombre=prueba4").request("application/json").get();
        Response respuesta = target.path("estado/find").queryParam("nombre", "prueba4").request().get();
        assertEquals(resultadoEsperado, respuesta.getStatus());
        String totalTexto = respuesta.getHeaderString("Total-Registro");
        System.out.println("\n\n");
        System.out.println("\n\n");
        System.out.println("Total Texto " + totalTexto);
        System.out.println("\n\n");
        System.out.println("\n\n");
        assertNotEquals(Integer.valueOf(0), Integer.valueOf(totalTexto));
        String cuerpoString = respuesta.readEntity(String.class);
        JsonReader lector = Json.createReader(new StringReader(cuerpoString));
        JsonArray listaJson = lector.readArray();
        int totalRegistros = listaJson.size();
        assertTrue(totalRegistros > 0);
        System.out.println("\n\n");
        System.out.println("\n\n");
        for (int i = 0; i < listaJson.size(); i++) {
            JsonObject objeto = listaJson.getJsonObject(i);
            System.out.println("aqui estoy loco ID: " + objeto);
                    System.out.println("\n\n");
        }
    }
    
    @Test
    @RunAsClient
    @Order(6)
    public void testFindId() {
        System.out.println("findId Estado");
        
        int resultadoEsperado = 200;
        Client cliente = ClientBuilder.newClient();
        WebTarget target = cliente.target(url.toString() + "resources/");
        //Response respuesta = target.path("estado?findNombre=prueba4").request("application/json").get();
        Response respuesta = target.path("estado/findId").queryParam("id", "1").request().get();
        assertEquals(resultadoEsperado, respuesta.getStatus());
        String totalTexto = respuesta.getHeaderString("Total-Registro");
        System.out.println("\n\n");
        System.out.println("\n\n");
        System.out.println("Total Texto " + totalTexto);
        System.out.println("\n\n");
        System.out.println("\n\n");
        assertNotEquals(Integer.valueOf(0), Integer.valueOf(totalTexto));
        String cuerpoString = respuesta.readEntity(String.class);
        JsonReader lector = Json.createReader(new StringReader(cuerpoString));
        JsonArray listaJson = lector.readArray();
        int totalRegistros = listaJson.size();
        assertTrue(totalRegistros > 0);
        System.out.println("\n\n");
        System.out.println("\n\n");
        for (int i = 0; i < listaJson.size(); i++) {
            JsonObject objeto = listaJson.getJsonObject(i);
            System.out.println("aqui esta el ID: " + objeto);
            System.out.println("\n\n");
        }
    }
}
