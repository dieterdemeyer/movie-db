package be.dieterdemeyer.moviedb.presentation.scenario.movie;

import static be.dieterdemeyer.moviedb.domain.MovieTestBuilder.IMDB;
import static be.dieterdemeyer.moviedb.domain.MovieTestBuilder.TITLE;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import be.dieterdemeyer.moviedb.test.ScenarioTestCase;

public class AddMovieScenarioTest extends ScenarioTestCase {

    @Test
    public void canAddMovie() {
        open("/movie/overview.xhtml");
        clickAndWait("link=add");
        type("form:imdb", IMDB);
        type("form:title", TITLE);
        clickAndWait("form:addButton");
        assertTrue(isTextPresent(IMDB));
        assertTrue(isTextPresent(TITLE));
    }
}
