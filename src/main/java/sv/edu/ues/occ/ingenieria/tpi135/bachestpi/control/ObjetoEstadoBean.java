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
import sv.edu.ues.occ.ingenieria.tpi135.bachestpi.entity.ObjetoEstado;

/**
 *
 * @author armandop444
 */
@Stateless
@LocalBean
public class ObjetoEstadoBean extends abstractDataAccess<ObjetoEstado> implements Serializable {

    @PersistenceContext(unitName = "BACHES-PU")
    EntityManager em;

    public ObjetoEstadoBean() {
        super(ObjetoEstado.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

}
