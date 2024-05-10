package edu.duke.ece651.project.team5.shared.dao;

import edu.duke.ece651.project.team5.shared.*;

import java.sql.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public abstract class BaseDao<T> implements Dao<T> {

    private static EntityManagerFactory emf = DB.getEmf();
    private Class<T> entityClass;
    private EntityManager em;

    public BaseDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected static EntityManager getEm() {
        return emf.createEntityManager();
    }

    public Optional<T> get(Object id) {
        em = getEm();
        try {
            T entity = em.find(entityClass, id);
            return Optional.ofNullable(entity);
        } finally {
            em.close();
        }
    }

    public List<T> getAll() {
        em = getEm();
        try {
            TypedQuery<T> query = em.createQuery("SELECT e FROM " + entityClass.getName() + " e", entityClass);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void add(T entity) {
        em = getEm();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(entity);
            tx.commit();

        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void update(T entity) {
        em = getEm();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            T mergedEntity = em.merge(entity);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(T entity) {
        em = getEm();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
