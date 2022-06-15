/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import sv.edu.ues.occ.ingenieria.tpi135.bachestpi.entity.Estado;

/**
 *
 * @author armandop444
 */
@Stateless
@LocalBean
public class EstadoBean extends abstractDataAccess<Estado> implements Serializable {

    @PersistenceContext(unitName = "BACHES-PU")
    EntityManager em;

    public EstadoBean() {
        super(Estado.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public List<Estado> findByIdEstado(final Integer idEstado) {
        if (this.em != null && idEstado != null) {
            Query q = em.createNamedQuery("Estado.findByIdEstadoLike");
            q.setParameter("idEstado", "%"+idEstado+"%");
            return q.getResultList();

        }
        return Collections.EMPTY_LIST;
    }

    public List<Estado> findNombre(String nombre) {

        Query q = em.createNamedQuery("Estado.findByNombre");
        q.setParameter("nombre", nombre);

        return q.getResultList();
    }
}
