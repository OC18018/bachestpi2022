/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi135.bachestpi.control;

import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

import sv.edu.ues.occ.ingenieria.tpi135.bachestpi.resources.entity.Estado;
/**
 *
 * @author armandop444
 */
public class EstadoBeanTest {
    
    public EstadoBeanTest() {
    }
    @Test
    public void testCrear() throws Exception {
        System.out.println("crear");
        Estado nuevo = new Estado();
        EstadoBean cut=new EstadoBean();
        assertThrows(IllegalArgumentException.class, ()->{
        cut.crear(null);
        });
        assertThrows(IllegalStateException.class, ()->{
        cut.crear(nuevo);
        });
        EntityManager mockEM=Mockito.mock(EntityManager.class);
        cut.em=mockEM;
        cut.crear(nuevo);
    }
    
}
