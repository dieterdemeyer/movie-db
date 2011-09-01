package be.dieterdemeyer.moviedb.presentation.controller.movie;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import be.dieterdemeyer.moviedb.application.MovieService;
import be.dieterdemeyer.moviedb.domain.Movie;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import be.dieterdemeyer.moviedb.domain.MovieTestBuilder;
import be.dieterdemeyer.moviedb.test.TestCase;

public class OverviewControllerTest extends TestCase {

    private OverviewController overviewController;

    @Mock
    private MovieService movieServiceMock;

    @Before
    public void setUpSubject() {
        overviewController = new OverviewController(movieServiceMock);
    }

    @Test
    public void getMoviesCallsService() {
        List<Movie> movies = Arrays.asList(new MovieTestBuilder().build());
        when(movieServiceMock.getOverview()).thenReturn(movies);

        List<Movie> actual = overviewController.getMovies();

        assertEquals(movies, actual);
    }

}