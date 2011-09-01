package be.dieterdemeyer.moviedb.application;

import java.util.List;

import be.dieterdemeyer.moviedb.domain.Movie;

public interface MovieService {

    List<Movie> getOverview();

    void add(Movie movie);
}