package be.dieterdemeyer.moviedb.presentation.controller.movie;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import be.dieterdemeyer.moviedb.application.MovieService;
import be.dieterdemeyer.moviedb.domain.Movie;

@Named
@RequestScoped
public class AddController {

    private final MovieService movieService;

    private Movie movie = new Movie();

    @Inject
    public AddController(MovieService movieService) {
        this.movieService = movieService;
    }

    public String save() {
        movieService.add(movie);
        
        return "success";
    }

    public Movie getMovie() {
        return movie;
    }
}