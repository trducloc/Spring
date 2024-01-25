package com.example.day_08;

import com.example.day_08.entity.Blog;
import com.example.day_08.entity.Movie;
import com.example.day_08.entity.User;
import com.example.day_08.model.enums.MovieType;
import com.example.day_08.model.enums.UserRole;
import com.example.day_08.repository.BlogRepository;
import com.example.day_08.repository.MovieRepository;
import com.example.day_08.repository.UserRepository;
import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


@SpringBootTest

public class InitDataTest {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BlogRepository blogRepository;

    @Test
    void save_users() {
        Faker faker = new Faker();

        for (int i = 0; i < 50; i++) {
            String name = faker.name().fullName();
            String email = faker.internet().emailAddress();
            String password = faker.internet().password();
            String avatar = generateLinkImage(name);
            UserRole role = UserRole.values()[faker.random().nextInt(UserRole.values().length)];
            Date createdAt = faker.date().birthday();

            long randomDuration = faker.random().nextLong();
            Date updatedAt = new Date(createdAt.getTime() - TimeUnit.DAYS.toMillis(randomDuration));


            User user = User.builder()
                    .name(name)
                    .email(email)
                    .password(password)
                    .avatar(avatar)
                    .role(role)
                    .createdAt(createdAt)
                    .updatedAt(updatedAt)
                    .build();

            userRepository.save(user);
        }
    }

    @Test
    void save_blogs() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();

        for (int i = 0; i < 50; i++) {
            User adminUser = getRandomAdminUser();
            if (adminUser != null) {
                String title = faker.book().title();
                String content = faker.lorem().paragraph();
                String thumbnail = generateLinkImage(title);
                boolean status = faker.bool().bool();
                Date publishedAt = status ? new Date() : null;
                Date createdAt = new Date();
                Date updatedAt = createdAt;

                Blog blog = Blog.builder()
                        .title(title)
                        .slug(slugify.slugify(title))
                        .description(faker.lorem().paragraph())
                        .content(content)
                        .thumbnail(thumbnail)
                        .status(status)
                        .publishedAt(publishedAt)
                        .createdAt(createdAt)
                        .updatedAt(updatedAt)
                        .user(adminUser)
                        .build();

                blogRepository.save(blog);
            }
        }
    }

    private User getRandomAdminUser() {
        List<User> adminUsers = userRepository.findByRole(UserRole.ADMIN);
        if (!adminUsers.isEmpty()) {
            Random random = new Random();
            return adminUsers.get(random.nextInt(adminUsers.size()));
        }
        return null;
    }
    @Test
    void save_movies() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        Random random = new Random();

        Date start = new Calendar.Builder().setDate(2023, 8, 1).build().getTime();
        Date end = new Date();

        for (int i = 0; i < 100; i++) {
            String title = faker.book().title();
            boolean status = faker.bool().bool();
            Date createdAt = randomDateBetweenTwoDates(start, end);
            Date publishedAt = null;
            if (status) {
                publishedAt = createdAt;
            }

            Movie movie = Movie.builder()
                    .title(title)
                    .slug(slugify.slugify(title))
                    .description(faker.lorem().paragraph())
                    .poster(generateLinkImage(title))
                    .releaseYear(faker.number().numberBetween(2021, 2024))
                    .view(faker.number().numberBetween(1000, 10000))
                    .rating(faker.number().randomDouble(1, 6, 10))
                    .type(MovieType.values()[random.nextInt(MovieType.values().length)])
                    .status(status)
                    .createdAt(createdAt)
                    .updatedAt(createdAt)
                    .publishedAt(publishedAt)
                    .build();

            movieRepository.save(movie);
        }
    }

    // write method to random date between 2 date
    private Date randomDateBetweenTwoDates(Date startInclusive, Date endExclusive) {
        long startMillis = startInclusive.getTime();
        long endMillis = endExclusive.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);
        return new Date(randomMillisSinceEpoch);
    }

    // generate link author avatar follow struct : https://placehold.co/200x200?text=[...]
    public static String generateLinkImage(String str) {
        return "https://placehold.co/200x200?text=" + str.substring(0, 1).toUpperCase();
    }

}
