package edu.duke.ece651.project.team5.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import edu.duke.ece651.project.team5.shared.model.AttendanceRecord;

@Repository
public interface AttendanceRecordDao extends JpaRepository<AttendanceRecord, Long> {
    Optional<AttendanceRecord> findByLectureIdAndStudentNetId(String lectureId, String studentNetId);
    

}
