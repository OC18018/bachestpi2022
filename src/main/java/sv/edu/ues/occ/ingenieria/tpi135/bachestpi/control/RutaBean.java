/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.occ.ingenieria.tpi135.bachestpi.control;


import java.io.Serializable;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.tpi135.bachestpi.resources.entity.Ruta;

/**
 *
 * @author armandop444
 */
@Stateless
@LocalBean
public class RutaBean extends abstractDataAccess<Ruta> implements Serializable {

    @PersistenceContext(unitName = "baches-PU")
    EntityManager em;

    public RutaBean() {
        super(Ruta.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

}