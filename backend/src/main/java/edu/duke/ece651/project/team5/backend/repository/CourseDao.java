package edu.duke.ece651.project.team5.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.duke.ece651.project.team5.shared.model.Course;

@Repository
public interface CourseDao extends JpaRepository<Course, Long> {

}
