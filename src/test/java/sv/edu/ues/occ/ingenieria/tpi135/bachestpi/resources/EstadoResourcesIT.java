/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi135.bachestpi.resources;

import java.util.List;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.jupiter.api.Assertions.*;
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
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsResource("META-INF/sql/datos.sql", "META-INF/sql/datos.sql")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(salida.toString(true));
        return salida;
    }

    @Inject
    EstadoBean cut;

    @Test
    @Order(1)
    public void testContar() {
        System.out.println("\n\n");
        System.out.println("\n\n");
        System.out.println("--------------------------------------------------------------");
        System.out.println("Contar");
        System.out.println("\n\n");
        assertNotNull(cut);
        Long resultado = cut.contar();
        assertNotNull(resultado);
        System.out.println("Se encontraron " + resultado + " Resultados");
        System.out.println("\n\n");
    }

    @Test
    @Order(2)
    public void testCrear() {
        System.out.println("--------------------------------------------------------------");
        System.out.println("Crear");
        System.out.println("\n\n");
        assertNotNull(cut);
        Estado nuevo = new Estado();
        nuevo.setNombre("creado IT");
        cut.crear(nuevo);
        Estado esperado = cut.findById(5);
        System.out.println("Creado id 5 obteniendo nombre " + esperado.getNombre());
        System.out.println("\n\n");
        Long contar = cut.contar();
        System.out.println("Total de Datos actuales: " + contar);
        System.out.println("\n\n");
    }

    @Test
    @Order(3)
    public void testFindByid() {
        System.out.println("--------------------------------------------------------------");
        System.out.println("FindId");
        System.out.println("\n\n");
        assertNotNull(cut);
        Estado nuevo = new Estado();
        Integer id = 4;
        Estado Result = cut.findById(id);
        System.out.println("Se encontro el resultado " + Result.getNombre());
        System.out.println("\n\n");
    }

    @Test
    @Order(4)
    public void testModificar() {
        System.out.println("--------------------------------------------------------------");
        System.out.println("Modificar");
        System.out.println("\n\n");
        assertNotNull(cut);
        int id = 4;
        Estado nuevo = new Estado();
        nuevo.setIdEstado(id);
        nuevo.setNombre("Modificado en IT");
        Estado sinModificar = cut.findById(id);
        System.out.println("Antes de Modificar " + sinModificar.getNombre());
        System.out.println("\n\n");
        cut.Modificar(nuevo);
        Estado modificado = cut.findById(id);
        System.out.println("Paso Modificar " + modificado.getNombre());
        System.out.println("\n\n");
    }

    @Test
    @Order(5)
    public void testFindAll() {
        System.out.println("--------------------------------------------------------------");
        System.out.println("findAll");
        System.out.println("\n\n");
        assertNotNull(cut);
        List<Estado> resultado = cut.findAll();
        assertNotNull(resultado);
        assertTrue(!resultado.isEmpty());
        System.out.println("La lista posee " + resultado.size());
        System.out.println("\n\n");
    }

    @Test
    @Order(6)
    public void testFindRange() {
        System.out.println("--------------------------------------------------------------");
        System.out.println("FindRange");
        System.out.println("\n\n");
        assertNotNull(cut);
        int first = 1;
        int pageSize = 3;
        List<Estado> resultado = cut.findRange(first, pageSize);
        assertNotNull(resultado);
        assertTrue(!resultado.isEmpty());
        System.out.println("Se encontraron " + resultado.size());
        System.out.println("\n\n");

    }

    @Test
    @Order(7)
    public void testEliminar() {
        System.out.println("--------------------------------------------------------------");
        System.out.println("Eliminar");
        assertNotNull(cut);
        Estado eliminado = new Estado();
        eliminado.setIdEstado(2);
        System.out.println("\n\n");
        System.out.println("Registros actuales:" + cut.contar());
        System.out.println("\n\n");
        cut.eliminar(eliminado);        
        System.out.println("Eliminado con exito registro actuales:" + cut.contar());
        System.out.println("\n\n");

    }

}
