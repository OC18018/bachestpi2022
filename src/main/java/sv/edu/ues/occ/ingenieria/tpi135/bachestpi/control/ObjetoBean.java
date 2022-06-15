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
import sv.edu.ues.occ.ingenieria.tpi135.bachestpi.entity.Objeto;

/**
 *
 * @author armandop444
 */
@Stateless
@LocalBean
public class ObjetoBean extends abstractDataAccess<Objeto> implements Serializable {
    @PersistenceContext(unitName = "BACHES-PU")
    EntityManager em;

    public ObjetoBean() {
        super(Objeto.class);
    }
    
    @Override
    public EntityManager getEntityManager() {
    return em;
    }
    
        public List<Objeto> findByIdTipoObjeto(final Integer idTipoObjeto, int firts,int pageSize){
        if (this.em!=null && idTipoObjeto!=null) {
            Query q=em.createNamedQuery("Objeto.findByTipoObjeto");
            q.setParameter("idTipoObjeto", idTipoObjeto);
            q.setFirstResult(firts);
            q.setMaxResults(pageSize);
            return q.getResultList();
            
        }
        return Collections.EMPTY_LIST;
    }
    public int count(final Integer idTipoObjeto){
            if (this.em!=null && idTipoObjeto!=null) {
            Query q=em.createNamedQuery("Objeto.countByTipoObjeto");
            q.setParameter("idTipoObjeto", idTipoObjeto);
            
            return ((Long) q.getSingleResult()).intValue();
            
        }
        return 0;
    }
    
    public List<Objeto> findByIdObjeto(final Integer idObjeto) {
        if (this.em != null && idObjeto != null) {
            Query q = em.createNamedQuery("Objeto.findByIdObjetoLike");
            q.setParameter("idObjeto", "%"+idObjeto+"%");
            return q.getResultList();

        }
        return Collections.EMPTY_LIST;
    }
    
    public List<Objeto> findNombre(String nombre){
        
        Query q=em.createNamedQuery("Objeto.findByNombre");
        q.setParameter("nombre", nombre);
        
        return q.getResultList();
    }
    
    
}
