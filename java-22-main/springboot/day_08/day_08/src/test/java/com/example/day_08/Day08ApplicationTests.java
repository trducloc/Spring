package com.example.day_08;

import com.example.day_08.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day08ApplicationTests {
	@Autowired
	private MovieRepository movieRepository;

	@Test
	void test_movie_repo() {}

}
