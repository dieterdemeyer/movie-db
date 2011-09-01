package be.dieterdemeyer.moviedb.presentation.scenario.movie;

import static be.dieterdemeyer.moviedb.domain.MovieTestBuilder.IMDB;
import static be.dieterdemeyer.moviedb.domain.MovieTestBuilder.TITLE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import be.dieterdemeyer.moviedb.domain.Movie;
import be.dieterdemeyer.moviedb.test.ScenarioTestCase;

public class MovieOverviewScenarioTest extends ScenarioTestCase {

    @Override
    protected void setUp() {
        persist(new Movie(IMDB, TITLE));
    }

    @Test
    public void canOpenOverview() {
        open("/movie/overview.xhtml");
        assertEquals("MovieDB - Movies overview", getTitle());
        assertTrue(isTextPresent(IMDB));
        assertTrue(isTextPresent(TITLE));
    }

}