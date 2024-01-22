package com.example.day_08.service;

import com.example.day_08.entity.Movie;
import com.example.day_08.model.enums.MovieType;
import com.example.day_08.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getNewMoviesBo() {
        return getNewMovies(MovieType.PHIM_BO);
    }

    public List<Movie> getNewMoviesLe() {
        return getNewMovies(MovieType.PHIM_LE);
    }

    public List<Movie> getNewMoviesPCR() {
        return getNewMovies(MovieType.PHIM_CHIEU_RAP);
    }


    public Movie findMovieById(Integer id) {
        return movieRepository.findAll().stream().filter(movie -> Objects.equals(movie.getId(), id) && movie.getStatus()).findFirst().orElse(null);
    }

    public List<Movie> getNewMovies(MovieType movieType) {
        Pageable pageable = PageRequest.of(0, 6, Sort.by("publishedAt").descending());
        return movieRepository.findByTypeAndStatus(movieType, true, pageable).getContent();
    }

    public List<Movie> getHotMovies() {
        return movieRepository.findMoviesOrderByRatingDesc();
    }

    public List<Movie> findMovieRelated(MovieType movieType) {
        return movieRepository.findMoviesByTypeOrderByRatingDesc(movieType);
    }
}
