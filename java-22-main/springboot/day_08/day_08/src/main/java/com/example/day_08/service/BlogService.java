//package com.example.day_08.service;
//
//import com.example.day_08.entity.Blog;
//import com.example.day_08.repository.BlogRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class BlogService {
//    private final BlogRepository blogRepository;
//
//    public Page<Blog> getBlogById(Integer id, Boolean status, Integer page, Integer size) {
//        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("publishedAt").descending());
//        return blogRepository.findBlogsByIdAndStatus(id, status, pageRequest);
//    }
//
//    public Page<Blog> getBlogList(Integer page, Integer size) {
//        Pageable pageable = PageRequest.of(page - 1, size); // Page numbers start from 0
//        return blogRepository.findAll(pageable);
//    }
//
//    public Blog getBlog(Integer id, String slug, boolean status) {
//        return blogRepository.findByIdAndSlugAndStatus(id, slug, status).orElse(null);
//    }
//
//    public List<Blog> getRelatedBlogs(Integer currentBlogId, boolean status, int count) {
//        return blogRepository.findRelatedBlogs(currentBlogId, status, PageRequest.of(0, count));
//    }
//
//    public Page<Blog> getHotBlogs(Boolean status, Integer page, Integer size) {
//        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("view").descending()); // page trong jpa bắt đầu từ 0
//        return blogRepository.findByStatus(status, pageRequest);
//    }
//
////    public List<Blog> getRelatedBlogs(Integer id, Integer type, Boolean status, Integer size) {
////        return blogRepository
////                .findByTypeAndStatusAndRatingGreaterThanEqualAndIdNotOrderByRatingDescViewDescPublishedAtDesc(type, status, 5.0, id)
////                .stream()
////                .limit(size)
////                .toList();
////    }
//}
