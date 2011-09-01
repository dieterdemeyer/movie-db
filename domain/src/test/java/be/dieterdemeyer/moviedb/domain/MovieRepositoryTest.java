package be.dieterdemeyer.moviedb.domain;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import be.dieterdemeyer.moviedb.test.IntegrationTestCase;

public class MovieRepositoryTest extends IntegrationTestCase {

    @Inject
    private MovieRepository movieRepository;

    @Test
    public void canStoreMovie() {
        Movie movie = new MovieTestBuilder().build();
        movieRepository.store(movie);

        flushAndClear();

        assertTrue(new ReflectionEquals(movie).matches(entityManager.find(Movie.class, movie.getId())));
    }

    @Test
    public void canFindAll() {
        Movie movie1 = new MovieTestBuilder().withImdb("123456").withTitle("The Matrix").build();
        Movie movie2 = new MovieTestBuilder().withImdb("654321").withTitle("The Matrix Reloaded").build();
        persistFlushAndClear(movie1, movie2);

        List<Movie> result = movieRepository.findAll();

        assertEquals(2, result.size());
        assertTrue(new ReflectionEquals(movie1).matches(result.get(0)));
        assertTrue(new ReflectionEquals(movie2).matches(result.get(1)));
    }
}
