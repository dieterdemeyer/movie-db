package be.dieterdemeyer.moviedb.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:overridingTestContext.xml"})
@Transactional
@TransactionConfiguration
public abstract class IntegrationTestCase {

    @PersistenceContext
    protected EntityManager entityManager;

    protected <E> void persistFlushAndClear(E... entities) {
        persist(entities);
        flushAndClear();
    }
    
    protected <E> void persist(E... entities) {
        for (E entity : entities) {
            entityManager.persist(entity);
        }
    }

    protected void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }
}
