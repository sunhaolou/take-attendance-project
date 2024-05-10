package edu.duke.ece651.project.team5.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.duke.ece651.project.team5.shared.model.Lecture;

@Repository
public interface LectureDao extends JpaRepository<Lecture, String> {

    Optional<Lecture> findBySessionId(String sessionId);

}
