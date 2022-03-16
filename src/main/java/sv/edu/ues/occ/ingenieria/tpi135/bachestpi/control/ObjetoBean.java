/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi135.bachestpi.control;


import java.io.Serializable;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.tpi135.bachestpi.resources.entity.Objeto;

/**
 *
 * @author armandop444
 */
@Stateless
@LocalBean
public class ObjetoBean extends abstractDataAccess<Objeto> implements Serializable {
    @PersistenceContext(unitName = "baches-PU")
    EntityManager em;

    public ObjetoBean() {
        super(Objeto.class);
    }
    
    @Override
    public EntityManager getEntityManager() {
    return em;
    }
    
}
