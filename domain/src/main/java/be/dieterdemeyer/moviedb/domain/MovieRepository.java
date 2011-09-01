package be.dieterdemeyer.moviedb.domain;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository extends AbstractRepository<Movie> {

    public Movie find(long id) {
        return find(Movie.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Movie> findAll() {
        return entityManager.createQuery("from Movie").getResultList();
    }

}