package com.example.day_08.controller;

import com.example.day_08.entity.Blog;
import com.example.day_08.entity.Movie;
import com.example.day_08.model.enums.MovieType;
import com.example.day_08.service.BlogService;
import com.example.day_08.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final MovieService movieService;
    private final BlogService blogService;

    // Trang chủ
    @GetMapping("/")
    public String getHomePage(Model model) {
        Page<Movie> pageDataPhimHot = movieService.getHotMovies(true, 1, 8);
        Page<Movie> pageDataPhimLe = movieService.getMoviesByType(MovieType.PHIM_LE, true, 1, 6);
        Page<Movie> pageDataPhimBo = movieService.getMoviesByType(MovieType.PHIM_BO, true, 1, 6);
        Page<Movie> pageDataPhimChieuRap = movieService.getMoviesByType(MovieType.PHIM_CHIEU_RAP, true, 1, 6);
        Page<Blog> pageDataHotBlog = blogService.getHotBlogs(true, 1, 8);

        model.addAttribute("phimHotList", pageDataPhimHot.getContent());
        model.addAttribute("phimLeList", pageDataPhimLe.getContent());
        model.addAttribute("phimBoList", pageDataPhimBo.getContent());
        model.addAttribute("phimChieuRapList", pageDataPhimChieuRap.getContent());
        model.addAttribute("hotBlogList", pageDataHotBlog.getContent());
        return "web/index";
    }
    @GetMapping("/phim-chieu-rap")
    public String getPhimChieuRap(Model model,
                                  @RequestParam(required = false, defaultValue = "1") Integer page,
                                  @RequestParam(required = false, defaultValue = "12") Integer size) {
        Page<Movie> pageData = movieService.getMoviesByType(MovieType.PHIM_CHIEU_RAP, true, page, size);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "web/phim-chieu-rap";
    }
    @GetMapping("/phim-le")
    public String getPhimLe(Model model,
                            @RequestParam(required = false, defaultValue = "1") Integer page,
                            @RequestParam(required = false, defaultValue = "12") Integer size) {
        Page<Movie> pageData = movieService.getMoviesByType(MovieType.PHIM_LE, true, page, size);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "web/phim-le";
    }
    @GetMapping("/phim-bo")
    public String getPhimBo(Model model,
                            @RequestParam(required = false, defaultValue = "1") Integer page,
                            @RequestParam(required = false, defaultValue = "12") Integer size) {
        Page<Movie> pageData = movieService.getMoviesByType(MovieType.PHIM_BO, true, page, size);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "web/phim-bo";
    }

    @GetMapping("/blog")
    public String getBlogList(Model model,
                              @RequestParam(required = false, defaultValue = "1") Integer page,
                              @RequestParam(required = false, defaultValue = "12") Integer size) {
        Page<Blog> pageData = blogService.getBlogList(page, size);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "web/blog";
    }
    // Chi tiết phim
    @GetMapping("/phim/{id}/{slug}")
    public String getPhimDetailPage(Model model, @PathVariable Integer id, @PathVariable String slug) {
        Movie movie = movieService.getMovie(id, slug, true);
        List<Movie> relatedMovieList = movieService.getRelatedMovies(id, movie.getType(), true, 6);

        model.addAttribute("movie", movie);
        model.addAttribute("relatedMovieList", relatedMovieList);
        return "web/chi-tiet-phim";
    }
//     Chi tiết phim
    @GetMapping("/blog/{id}/{slug}")
    public String getBlogDetailPage(Model model, @PathVariable Integer id, @PathVariable String slug) {
        Blog blog = blogService.getBlog(id, slug, true);
        List<Blog> relatedBlogList = blogService.getRelatedBlogs(id, true, 6);

        model.addAttribute("blog", blog);
        model.addAttribute("relatedBlogList", relatedBlogList);
        return "web/blog-detail";
    }
}
