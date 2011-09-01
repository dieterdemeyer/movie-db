package be.dieterdemeyer.moviedb.presentation.controller.movie;

import java.util.List;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import be.dieterdemeyer.moviedb.application.MovieService;
import be.dieterdemeyer.moviedb.domain.Movie;

@Named
@RequestScoped
public class OverviewController {

    private final MovieService movieService;

    @Inject
    public OverviewController(MovieService movieService) {
        this.movieService = movieService;
    }

    public List<Movie> getMovies() {
        return movieService.getOverview();
    }

}