package com.example.day_08.service;

import com.example.day_08.entity.User;
import com.example.day_08.exception.BadRequestException;
import com.example.day_08.model.request.LoginRequest;
import com.example.day_08.model.request.RegisterRequest;
import com.example.day_08.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final HttpSession session;

    public void login(LoginRequest request) {
        log.info("Login: {}", request);

        // Tìm user theo email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Tài khoản hoặc mật khẩu không đúng"));

        // Kiểm tra password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Tài khoản hoặc mật khẩu không đúng");
        }

        // Lưu thông tin user vào trong session
        session.setAttribute("currentUser", user);
    }

    public void register(RegisterRequest request) {
        // check email exist
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email da ton tai");
        }

        // check password
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException("Mat khau khong khop");
        }

        //ma hoa password
        String encodedPassword = passwordEncoder.encode(request.getPassword());


        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(encodedPassword)
                .build();

        userRepository.save(user);
    }

    public void logout() {
        session.setAttribute("currentUser", null);
    }
}