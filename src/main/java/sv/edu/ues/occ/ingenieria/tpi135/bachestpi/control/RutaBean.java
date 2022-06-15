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
import sv.edu.ues.occ.ingenieria.tpi135.bachestpi.entity.Ruta;

/**
 *
 * @author armandop444
 */
@Stateless
@LocalBean
public class RutaBean extends abstractDataAccess<Ruta> implements Serializable {

    @PersistenceContext(unitName = "BACHES-PU")
    EntityManager em;

    public RutaBean() {
        super(Ruta.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

        public List<Ruta> findByIdRuta(final Long idRuta) {
        if (this.em != null && idRuta != null) {
            Query q = em.createNamedQuery("Ruta.findByIdRutaLike");
            q.setParameter("idRuta", "%"+idRuta+"%");
            return q.getResultList();

        }
        return Collections.EMPTY_LIST;
    }

    public List<Ruta> findNombre(String nombre) {

        Query q = em.createNamedQuery("Ruta.findByNombre");
        q.setParameter("nombre", nombre);

        return q.getResultList();
    }
}
