package com.example.day_08.repository;

import com.example.day_08.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
}
