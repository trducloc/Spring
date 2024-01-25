package com.example.day_08.repository;

import com.example.day_08.entity.Blog;
import com.example.day_08.model.enums.MovieType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

    Optional<Blog> findByIdAndSlugAndStatus(Integer id, String slug, Boolean status);

    List<Blog> findByTypeAndStatusAndRatingGreaterThanEqualAndIdNotOrderByRatingDescViewDescPublishedAtDesc(MovieType type, Boolean status, Double rating, Integer id);

    @Query("SELECT b FROM Blog b WHERE (:id is null or b.id = :id) and (:status is null or b.status = :status)")
    Page<Blog> findBlogsByIdAndStatus(@Param("id") Integer id, @Param("status") Boolean status, Pageable pageable);

    Page<Blog> findByStatus(Boolean status, Pageable pageable);

    List<Blog> findRelatedBlogs(Integer currentBlogId, boolean status, PageRequest of);
}