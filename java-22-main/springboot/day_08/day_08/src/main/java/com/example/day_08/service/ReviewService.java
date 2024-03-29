package com.example.day_08.service;

import com.example.day_08.entity.Movie;
import com.example.day_08.entity.Review;
import com.example.day_08.entity.User;
import com.example.day_08.exception.BadRequestException;
import com.example.day_08.exception.ResourceNotFoundException;
import com.example.day_08.model.request.UpsertReviewRequest;
import com.example.day_08.repository.MovieRepository;
import com.example.day_08.repository.ReviewRepository;
import com.example.day_08.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository serRepository;
    private final MovieRepository movieRepository;
    private final HttpSession session;

    // Lấy danh sách review theo id phim
    public List<Review> getReviewsByMovie(Integer id) {
        return reviewRepository.findByMovie_IdOrderByCreatedAtDesc(id);
    }

    // Tạo review
    public Review createReview(UpsertReviewRequest request) {
        User currentUser = (User) session.getAttribute("currentUser");

//        // Tìm kiếm user theo id
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy user có id: " + userId));

        // Kiểm tra movieId có tồn tại không?
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy movie có id: " + request.getMovieId()));

        // Tạo review
        Review review = Review.builder()
                .comment(request.getComment())
                .rating(request.getRating())
                .user(currentUser)
                .movie(movie)
                .build();

        return reviewRepository.save(review);
    }

    // Cập nhật review
    public Review updateReview(Integer id, UpsertReviewRequest request) {
        User currentUser = (User) session.getAttribute("currentUser");

//        // Tìm kiếm user theo id
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy user có id: " + userId));

        // Kiểm tra movieId có tồn tại không?
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy movie có id: " + request.getMovieId()));

        // Kiểm tra review có tồn tại không?
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy review có id: " + id));

        // Kiểm tra xem review có phải của user đang login không? Nếu không báo lỗi
        if (!review.getUser().getId().equals(currentUser.getId())) {
            throw new BadRequestException("Bạn không có quyền cập nhật review này");
        }

        // Kiểm tra xem review có phải của movie không? Nếu không báo lỗi
        if (!review.getMovie().getId().equals(movie.getId())) {
            throw new BadRequestException("Review này không phải của movie này");
        }

        // Cập nhật review
        review.setComment(request.getComment());
        review.setRating(request.getRating());

        return reviewRepository.save(review);
    }

    // Xóa review
    public void deleteReview(Integer id) {
        // Lấy thông tin user từ trong session với key currentUser
        User currentUser = (User) session.getAttribute("currentUser");


        // Tìm kiếm user theo id
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy user có id: " + userId));

        // Kiểm tra review có tồn tại không?
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy review có id: " + id));

        // Kiểm tra xem review có phải của user đang login không? Nếu không báo lỗi
        if (!review.getUser().getId().equals(currentUser.getId())) {
            throw new BadRequestException("Bạn không có quyền xóa review này");
        }

        //xoa review
        reviewRepository.delete(review);
    }
}