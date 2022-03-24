/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi135.bachestpi.control;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author armandop444
 */
public abstract class abstractDataAccess<T> implements Serializable {

    protected final Class clase;

    public abstractDataAccess(Class clase) {
        this.clase = clase;
    }

    public abstract EntityManager getEntityManager();

    public void crear(T nuevo) throws IllegalArgumentException, IllegalStateException {
        if (nuevo != null) {
            EntityManager em = null;
            try {
                em = getEntityManager();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "entity nulo");
            }
            if (em != null) {
                em.persist(nuevo);
                return;
            } else {
                throw new IllegalStateException();
            }

        }
        throw new IllegalArgumentException();

    }

    public T findById(final Object id) throws IllegalArgumentException, IllegalStateException {
        if (id != null) {
            EntityManager em = null;
            try {
                em = this.getEntityManager();
            } catch (Exception e) {
            }
            if (em != null) {
                return (T) em.find(clase, id);
            }
            throw new IllegalStateException("no se puede obtener un ambito de persistencia");
        }
        throw new IllegalArgumentException();
    }

    public List<T> findAll() {
        EntityManager em = null;
        try {
            em = this.getEntityManager();
        } catch (Exception e) {
        }
        if (em != null) {
            TypedQuery q = this.generarConsultaBase(em);
            List salida = q.getResultList();
            if (salida != null) {
                return salida;
            }
            return Collections.EMPTY_LIST;
        }
        throw new IllegalStateException("no se puede obtener un ambito de persistencia");
    }

    public List<T> findRange(int first, int pageSize) {
        EntityManager em = null;
        try {
            em = this.getEntityManager();
        } catch (Exception e) {
        }
        if (em != null) {
            TypedQuery q = this.generarConsultaBase(em);
            q.setFirstResult(first);
            q.setMaxResults(pageSize);
            List salida = q.getResultList();
            if (salida != null) {
                return salida;
            }
            return Collections.EMPTY_LIST;
        }
        throw new IllegalStateException("no se puede obtener un ambito de persistencia");
    }

    protected TypedQuery generarConsultaBase(EntityManager em) {
        if (em != null) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery(clase);
            Root<T> raiz = cq.from(clase);
            cq.select(raiz);
            return em.createQuery(cq);
        }
        throw new IllegalArgumentException();
    }

    public Long contar() throws IllegalStateException {
        EntityManager em = null;
        try {
            em = this.getEntityManager();
        } catch (Exception e) {
        }
        if (em != null) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            cq.select(cb.count(cq.from(clase)));
            return em.createQuery(cq).getSingleResult();
        }
        throw new IllegalArgumentException();
    }

    public void Modificar(T nuevo) throws IllegalArgumentException, IllegalStateException {
        if (nuevo != null) {
            EntityManager em = null;
            try {
                em = this.getEntityManager();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "entity nulo");
            }
            if (em != null) {
                //codigo para modificar
                em.merge(nuevo);
                return;
            } else {
                throw new IllegalStateException();
            }

        }
        throw new IllegalArgumentException();

    }

public void eliminar(T registro) throws IllegalArgumentException, IllegalStateException {
        if (registro != null) {
            EntityManager em = null;
            try {
                em = getEntityManager();
            } catch (Exception e) {
            }
            if (em != null) {
                if (!em.contains(registro)) {
                    registro = em.merge(registro);
                }
                em.remove(registro);
                return;
            }
            throw new IllegalStateException("entity null");

        }
        throw new IllegalArgumentException("objeto null");
    }
}
