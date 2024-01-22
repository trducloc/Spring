package com.example.day_08.controller;

import com.example.day_08.entity.Movie;
import com.example.day_08.model.enums.MovieType;
import com.example.day_08.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController {
    private final MovieService movieService;
    @Autowired
    public WebController(MovieService movieService) {
        if (movieService == null) {
            throw new IllegalArgumentException("movieService must not be null");
        }
        this.movieService = movieService;
    }
    @GetMapping("/")
    public String getHome(Model model) {
        try {
            model.addAttribute("hotMovies", movieService.getHotMovies());
            model.addAttribute("newMoviesLe", movieService.getNewMoviesByType(MovieType.PHIM_LE));
            model.addAttribute("newMoviesBo", movieService.getNewMoviesByType(MovieType.PHIM_BO));
            model.addAttribute("newMoviesPCR", movieService.getNewMoviesByType(MovieType.PHIM_CHIEU_RAP));
        } catch (Exception e){
            return "error";
        }
        return "web/index";
    }
    @GetMapping("/phim-chieu-rap")
    public String getPhimChieuRap(Model model){
        try{
            model.addAttribute("newMoviesPCR", movieService.getNewMoviesByType(MovieType.PHIM_CHIEU_RAP));
        }
        catch (Exception e){
            return "error";
        }
        return "web/phim-chieu-rap";
    }
    @GetMapping("/phim-le")
    public String getPhimLe(Model model){
        try{
            model.addAttribute("newMoviesLe", movieService.getNewMoviesByType(MovieType.PHIM_LE));
        }
        catch (Exception e){
            return "error";
        }
        return "web/phim-le";
    }
    @GetMapping("/phim-bo")
    public String getPhimBo(Model model){
        try{
            model.addAttribute("newMoviesBo", movieService.getNewMoviesByType(MovieType.PHIM_BO));
        }
        catch (Exception e){
            return "error";
        }
        return "web/phim-bo";
    }
    @GetMapping("/error")
    public String getErrorPage() {
        return "error";
    }
    @GetMapping("/chi-tiet-phim")
    public String getChiTietPhim(){
        return "web/chi-tiet-phim";
    }

    @GetMapping("/phim/{id}/{slug}")
    public ResponseEntity<String> getChiTietPhim(@PathVariable Integer id, @PathVariable String slug, Model model) {
        Movie movie = movieService.findMovieById(id);
        model.addAttribute("movie", movie);
        model.addAttribute("moviesRelated", movieService.findMovieRelated(MovieType.valueOf(String.valueOf(movie.getType()))));
        return ResponseEntity.ok("web/chi-tiet-phim");
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }



}
