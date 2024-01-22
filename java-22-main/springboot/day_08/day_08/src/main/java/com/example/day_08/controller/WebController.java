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

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

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
            model.addAttribute("moviesRelated", movieService.findMovieRelated(MovieType.PHIM_CHIEU_RAP));
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
            model.addAttribute("moviesRelated", movieService.findMovieRelated(MovieType.PHIM_LE));
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
            model.addAttribute("moviesRelated", movieService.findMovieRelated(MovieType.PHIM_BO));
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
        if (movie == null || !slug.equals(WebUtils.createSlug(movie.getTitle()))) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
        }
        model.addAttribute("movie", movie);
        model.addAttribute("moviesRelated", movieService.findMovieRelated(MovieType.valueOf(String.valueOf(movie.getType()))));
        return ResponseEntity.ok("web/chi-tiet-phim");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }

    public static class WebUtils {
        private static final Pattern NON_LATIN = Pattern.compile("[^\\w-]");
        private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

        public static String createSlug(String input) {
            String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
            String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
            return NON_LATIN.matcher(normalized).replaceAll("").toLowerCase(Locale.ENGLISH);
        }
    }

}
