
package edu.duke.ece651.project.team5.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.duke.ece651.project.team5.shared.model.*;;

@Repository
public interface ProfessorDao extends JpaRepository<Professor, Long> {

    Optional<Professor> findByNetId(String netId);
  


}