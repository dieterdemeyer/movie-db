package be.dieterdemeyer.moviedb.domain;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractRepository<E> {

    @PersistenceContext
    protected EntityManager entityManager;

    public E find(Class<E> clazz, long id) {
        return entityManager.find(clazz, id);
    }

    public void store(E entity) {
        entityManager.persist(entity);
    }

    public void remove(E entity) {
        entityManager.remove(entity);
    }

}
