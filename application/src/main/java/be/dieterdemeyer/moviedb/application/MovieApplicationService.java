package be.dieterdemeyer.moviedb.application;

import java.util.List;

import javax.inject.Inject;

import be.dieterdemeyer.moviedb.domain.Movie;
import org.springframework.stereotype.Service;

import be.dieterdemeyer.moviedb.domain.MovieRepository;

@Service
public class MovieApplicationService implements MovieService {

    private final MovieRepository repository;

    @Inject
    MovieApplicationService(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Movie> getOverview() {
        return repository.findAll();
    }

    @Override
    public void add(Movie movie) {
        repository.store(movie);
    }

}