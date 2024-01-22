package com.example.day_08.service;

import com.example.day_08.entity.Movie;
import com.example.day_08.model.enums.MovieType;
import com.example.day_08.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getNewMoviesByType(MovieType movieType) {
        return movieRepository.getNewMoviesByType(movieType);
    }

    public Movie findMovieById(Integer id) {
        return movieRepository.findMovieById(id).orElse(null);
    }

    public List<Movie> getHotMovies() {
        return movieRepository.findMoviesOrderByRatingDesc();
    }

    public List<Movie> findMovieRelated(MovieType movieType) {
        return movieRepository.findMoviesByTypeOrderByRatingDesc(movieType);
    }
}
