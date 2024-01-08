package com.example.day_08;

import com.example.day_08.entity.Movie;
import com.example.day_08.model.enums.MovieType;
import com.example.day_08.repository.MovieRepository;
import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Random;

@SpringBootTest
class Day08ApplicationTests {
	@Autowired
	private MovieRepository movieRepository;

	@Test
	void contextLoads() {
		Faker faker = new Faker();
		Slugify slugify = Slugify.builder().build();
		Random random = new Random();
		for (int i = 0; i < 100 ; i++) {

			String title = faker.book().title();
			boolean status = faker.bool().bool();
			Date createdAt = faker.date().birthday();
			Date publishedAt = null;
			if(status) {
				publishedAt = createdAt;
			}
			Movie movie = Movie.builder()
					.title(title)
					.slug(slugify.slugify(title))
					.description(faker.lorem().paragraph())
					.poster(faker.company().logo())
					.releaseYear(faker.number().numberBetween(2021,2024))
					.view(faker.number().numberBetween(1000, 10000))
					.rating(faker.number().randomDouble(1,6,10))
					.type(MovieType.values()[random.nextInt(MovieType.values().length)])
					.status(status)
					.createdAt(createdAt)
					.updatedAt(createdAt)
					.publishedAt(publishedAt)
					.build();

			movieRepository.save(movie);
			
		}
	}
	@Test
	void test_movie_repo() {


	}

}
