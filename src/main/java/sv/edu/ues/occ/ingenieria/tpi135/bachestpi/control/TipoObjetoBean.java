/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.occ.ingenieria.tpi135.bachestpi.control;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    @Override
    public Long contar() throws IllegalStateException {
        try {
            Thread.sleep(2000);
        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return super.contar();
    }

    public List<TipoObjeto> findByIdTipoObjeto(final Integer idTipoObjeto) {
        if (this.em != null && idTipoObjeto != null) {
            Query q = em.createNamedQuery("TipoObjeto.findByIdTipoObjeto");
            q.setParameter("idTipoObjeto", idTipoObjeto);
            return q.getResultList();

        }
        return Collections.EMPTY_LIST;
    }
}
