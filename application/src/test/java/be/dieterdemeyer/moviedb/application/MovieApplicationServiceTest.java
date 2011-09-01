package be.dieterdemeyer.moviedb.application;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import be.dieterdemeyer.moviedb.domain.Movie;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import be.dieterdemeyer.moviedb.domain.MovieTestBuilder;
import be.dieterdemeyer.moviedb.domain.MovieRepository;
import be.dieterdemeyer.moviedb.test.TestCase;


public class MovieApplicationServiceTest extends TestCase {

    private MovieApplicationService movieApplicationService;

    @Mock
    private MovieRepository movieRepositoryMock;

    @Before
    public void setUpSubject() {
        movieApplicationService = new MovieApplicationService(movieRepositoryMock);
    }

    @Test
    public void shouldFindAllTheMoviesFromRepository() {
        when(movieRepositoryMock.findAll()).thenReturn(singletonList(new MovieTestBuilder().build()));

        List<Movie> result = movieApplicationService.getOverview();

        assertEquals(1, result.size());
        assertTrue(new ReflectionEquals(new MovieTestBuilder().build()).matches(result.get(0)));
    }

    @Test
    public void shouldStoreNewMovieInRepository() {
        Movie movie = new MovieTestBuilder().build();

        movieApplicationService.add(movie);

        verify(movieRepositoryMock).store(movie);
    }

}