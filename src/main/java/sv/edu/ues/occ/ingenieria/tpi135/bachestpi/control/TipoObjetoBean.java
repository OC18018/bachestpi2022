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
import sv.edu.ues.occ.ingenieria.tpi135.bachestpi.entity.TipoObjeto;

/**
 *
 * @author armandop444
 */
@Stateless
@LocalBean
public class TipoObjetoBean extends abstractDataAccess<TipoObjeto> implements Serializable {

    @PersistenceContext(unitName = "BACHES-PU")
    EntityManager em;

    public TipoObjetoBean() {
        super(TipoObjeto.class);
    }
    
    @Override
    public EntityManager getEntityManager() {
    return em;
    }
    
}
