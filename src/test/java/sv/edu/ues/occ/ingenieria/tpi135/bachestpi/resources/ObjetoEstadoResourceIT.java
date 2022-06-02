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
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import sv.edu.ues.occ.ingenieria.tpi135.bachestpi.control.ObjetoEstadoBean;
import sv.edu.ues.occ.ingenieria.tpi135.bachestpi.control.abstractDataAccess;
import sv.edu.ues.occ.ingenieria.tpi135.bachestpi.entity.ObjetoEstado;

/**
 *
 * @author armandop444
 */
@ExtendWith(ArquillianExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ObjetoEstadoResourceIT {

    @Deployment
    public static WebArchive crearDespliegue() {
        WebArchive salida = ShrinkWrap.create(WebArchive.class)
                .addPackage("com.mycompany.crudbaches.entity")
                .addAsResource("persistence-arquillian.xml")
                .addClass(abstractDataAccess.class)
                .addClass(ObjetoEstadoBean.class)
                .addClass(JakartaRestConfiguration.class)
                .addClass(ObjetoEstadoResource.class)
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsResource("META-INF/sql/datos.sql", "META-INF/sql/datos.sql")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(salida.toString(true));
        return salida;
    }
    @ArquillianResource
    URL url;

//    @Test
//    @RunAsClient
//    @Order(4)
//    public void testFindAll() {
//        System.out.println("findAllObjetoEstado");
//        
//        int resultadoEsperado = 200;
//        Client cliente = ClientBuilder.newClient();
//        WebTarget target = cliente.target(url.toString() + "resources/");
//        Response respuesta = target.path("tipoobjeto").request("application/json").get();
//        assertEquals(resultadoEsperado, respuesta.getStatus());
//        String totalTexto = respuesta.getHeaderString("Total-Registro");
//        assertNotEquals(Integer.valueOf(0), Integer.valueOf(totalTexto));
//        String cuerpoString = respuesta.readEntity(String.class);
//        JsonReader lector = Json.createReader(new StringReader(cuerpoString));
//        JsonArray listaJson = lector.readArray();
//        int totalRegistros = listaJson.size();
//        assertTrue(totalRegistros > 0);
//        System.out.println("\n\n");
//        System.out.println("\n\n");
//        for (int i = 0; i < listaJson.size(); i++) {
//            JsonObject objeto = listaJson.getJsonObject(i);
//            System.out.println("ID: " + objeto.getInt("idObjetoEstado") + " Activo:" + objeto.getBoolean("activo"));
//        }
//    }
    @Test
    @RunAsClient
    @Order(1)
    public void testCrear() {
        System.out.println("\n\n");
        System.out.println("\n\n");
        System.out.println("--------------------------------------------------------------");
        System.out.println("Crear ObjetoEstado");
        ObjetoEstado nuevo = new ObjetoEstado();
        nuevo.setActual(Boolean.TRUE);

        int resultadoEsperado = 200;
        Client cliente = ClientBuilder.newClient();
        WebTarget target = cliente.target(url.toString() + "resources/");
        Response respuesta = target.path("objetoestado").request("application/json").post(Entity.entity(nuevo, MediaType.APPLICATION_JSON));
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
        System.out.println("\n\n");
        System.out.println("\n\n");
        System.out.println("--------------------------------------------------------------");
        System.out.println("Modificar ObjetoEstado");
        ObjetoEstado nuevo = new ObjetoEstado();
        nuevo.setIdObjetoEstado(1L);
        nuevo.setActual(Boolean.FALSE);

        int resultadoEsperado = 200;
        Client cliente = ClientBuilder.newClient();
        WebTarget target = cliente.target(url.toString() + "resources/");
        Response respuesta = target.path("objetoestado").request("application/json").put(Entity.entity(nuevo, MediaType.APPLICATION_JSON));
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
        System.out.println("\n\n");
        System.out.println("\n\n");
        System.out.println("--------------------------------------------------------------");
        System.out.println("Eliminar ObjetoEstado");
        ObjetoEstado nuevo = new ObjetoEstado();

        int resultadoEsperado = 200;
        Client cliente = ClientBuilder.newClient();
        WebTarget target = cliente.target(url.toString() + "resources/");
        Response respuesta = target.path("objetoestado/3").request("application/json").delete();
        assertEquals(resultadoEsperado, respuesta.getStatus());
        String registro = respuesta.getHeaderString("ID-eliminado");
        assertNotEquals(null, registro);
        String cuerpoString = respuesta.readEntity(String.class);
        JsonReader lector = Json.createReader(new StringReader(cuerpoString));
        JsonObject objeto = lector.readObject();

        System.out.println("\n\n");
        System.out.println("\n\n");
        System.out.println("ID:" + objeto.getInt("idObjetoEstado") + " eliminado con exito");
        System.out.println("\n\n");
        System.out.println("\n\n");
    }

    @Test
    @RunAsClient
    @Order(4)
    public void testFindId() {
        System.out.println("findId Estado");

        int resultadoEsperado = 200;
        Client cliente = ClientBuilder.newClient();
        WebTarget target = cliente.target(url.toString() + "resources/");
        //Response respuesta = target.path("estado?findNombre=prueba4").request("application/json").get();
        Response respuesta = target.path("objetoestado/findId").queryParam("id", "2").request().get();
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
