/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi135.bachestpi.control;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        
        EstadoBean espia = Mockito.spy(EstadoBean.class);
        espia.em=mockEM;
        
        Mockito.when(espia.getEntityManager()).thenThrow(NullPointerException.class);
        try {
            espia.crear(nuevo);
        } catch (Exception e) {
        }
        Mockito.verify(espia,Mockito.times(1)).getEntityManager();
    }
    
    @Test
    public void testFindById(){
        System.out.println("findById");
        Integer id = 1;
        EntityManager mockEM=Mockito.mock(EntityManager.class);
        EstadoBean cut = new EstadoBean();
        Estado esperado = new Estado();
        Mockito.when(mockEM.find(Estado.class, id)).thenReturn(esperado);
        assertThrows(IllegalArgumentException.class, () -> {
            cut.findById(null);
        });
        assertThrows(IllegalStateException.class, ()->{
            cut.findById(id);
        });
        
        cut.em=mockEM;
        Estado encontrado = cut.findById(id);
        assertNotNull(encontrado);
        assertEquals(esperado, encontrado );
        
        EstadoBean espia = Mockito.spy(EstadoBean.class);
        espia.em=mockEM;
        
        Mockito.when(espia.getEntityManager()).thenThrow(NullPointerException.class);
        try {
            espia.findById(id);
        } catch (Exception e) {
        }
        Mockito.verify(espia,Mockito.times(1)).getEntityManager();
        
    }

    /**
     * Test of findAll method, of class EstadoBean.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
               
    }

    /**
     * Test of findRange method, of class EstadoBean.
     */
    @Test
    public void testFindRange() throws Exception {
        System.out.println("findRange");
        
        
    }

    /**
     * Test of contar method, of class EstadoBean.
     */
    @Test
    public void testContar() throws Exception {
        System.out.println("contar");
        Long esperado = Long.valueOf(1);
        EntityManager mockEM = Mockito.mock(EntityManager.class);
        CriteriaBuilder mockCB = Mockito.mock(CriteriaBuilder.class);
        CriteriaQuery mockCQ = Mockito.mock(CriteriaQuery.class);
        TypedQuery mockTQ = Mockito.mock(TypedQuery.class);
        
        Mockito.when(mockEM.getCriteriaBuilder()).thenReturn(mockCB);
        Mockito.when(mockCB.createQuery(Long.class)).thenReturn(mockCQ);
        Mockito.when(mockEM.createQuery(mockCQ)).thenReturn(mockTQ);
        Mockito.when(mockTQ.getSingleResult()).thenReturn(esperado);
        EstadoBean cut = new EstadoBean();
        
        assertThrows(IllegalArgumentException.class, ()->{
            cut.contar();
        });
        
        cut.em = mockEM;
        Long resultado = cut.contar();
        assertNotNull(resultado);
        assertEquals(esperado, resultado);
        
        
        EstadoBean espia = Mockito.spy(EstadoBean.class);
        espia.em=mockEM;
        
        Mockito.when(espia.getEntityManager()).thenThrow(NullPointerException.class);
        try {
            espia.contar();
        } catch (Exception e) {
        }
        Mockito.verify(espia,Mockito.times(1)).getEntityManager();
        
    }

    /**
     * Test of Modificar method, of class EstadoBean.
     */
    @Test
    public void testModificar() throws Exception {
        System.out.println("Modificar");
        int id=1;
        EntityManager mockEM = Mockito.mock(EntityManager.class);
        
        Estado nuevo=new Estado(2);
        EstadoBean cut=new EstadoBean();

        
        assertThrows(IllegalArgumentException.class, ()->{
        cut.Modificar(null, id);
        });

        assertThrows(IllegalStateException.class, ()->{
        cut.Modificar(nuevo, id);
        });

        cut.em=mockEM;
        cut.Modificar(nuevo, id);

        EstadoBean espia = Mockito.spy(EstadoBean.class);
        espia.em=mockEM;
        Mockito.when(espia.getEntityManager()).thenThrow(NullPointerException.class);
        try {
            espia.Modificar(nuevo, id);
        } catch (Exception e) {
        }
        Mockito.verify(espia,Mockito.times(1)).getEntityManager();
    }

    /**
     * Test of Eliminar method, of class EstadoBean.
     */
    @Test
    public void testEliminar() throws Exception {
        System.out.println("Eliminar");
        int id=1;
        EntityManager mockEM = Mockito.mock(EntityManager.class);
        
        EstadoBean cut=new EstadoBean();
        assertThrows(IllegalArgumentException.class, ()->{
        cut.Eliminar(0);
        });
        
        cut.em=mockEM;
        cut.Eliminar(id);
        
        EstadoBean espia = Mockito.spy(EstadoBean.class);
        espia.em=mockEM;
        
        Mockito.when(espia.getEntityManager()).thenThrow(NullPointerException.class);
        try {
            espia.Eliminar(id);
        } catch (Exception e) {
        }
        Mockito.verify(espia,Mockito.times(1)).getEntityManager();
        
    }
    
}
