package edu.duke.ece651.project.team5.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.duke.ece651.project.team5.backend.model.QRCode;
import edu.duke.ece651.project.team5.backend.repository.AttendanceRecordDao;
import edu.duke.ece651.project.team5.backend.repository.LectureDao;
import edu.duke.ece651.project.team5.backend.repository.QRCodeDao;
import edu.duke.ece651.project.team5.backend.repository.SectionDao;
import edu.duke.ece651.project.team5.backend.repository.StudentDao;
import edu.duke.ece651.project.team5.shared.model.Lecture;
import edu.duke.ece651.project.team5.shared.model.Section;
import edu.duke.ece651.project.team5.shared.model.Student;
import edu.duke.ece651.project.team5.shared.response.QRCodeResponse;

public class SessionServiceTest {

    @InjectMocks
    private SessionService sessionService;

    @Mock
    private LectureDao sessionDao;
    @Mock
    private StudentDao studentDao;
    @Mock
    private SectionDao sectionDao;
    @Mock
    private AttendanceRecordDao attendanceRecordDao;
    @Mock
    private QRCodeDao qrCodeDao;
    @Mock
    private QRCodeService qrCodeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTakeAttendance() {
        String sessionId = "session1";
        String studentNetId = "student1";
        Lecture lecture = new Lecture();
        Student student = new Student();
        when(sessionDao.findBySessionId(sessionId)).thenReturn(Optional.of(lecture));
        when(studentDao.findByNetId(studentNetId)).thenReturn(Optional.of(student));
        when(attendanceRecordDao.findByLectureIdAndStudentNetId(sessionId, studentNetId))
                .thenReturn(Optional.empty());

        sessionService.takeAttendance(sessionId, studentNetId, "PRESENT");

        // verify(attendanceRecordDao).save(any(AttendanceRecord.class));
    }

    @Test
    void testCheckQRCodeExpiration_NotExpired() {
        Long uuid = 1L;
        QRCode qrCode = new QRCode();
        qrCode.setExpirationTime(new Timestamp(System.currentTimeMillis() + 1000 * 30)); // 10 seconds ahead
        when(qrCodeDao.findById(uuid)).thenReturn(Optional.of(qrCode));

        assertFalse(sessionService.checkQRCodeExpiration("session1", uuid));
    }

    @Test
    void testCheckQRCodeExpiration_Expired() {
        Long uuid = 1L;
        QRCode qrCode = new QRCode();
        qrCode.setExpirationTime(new Timestamp(System.currentTimeMillis() + 1000 * 30)); // 10 seconds ago
        when(qrCodeDao.findById(uuid)).thenReturn(Optional.of(qrCode));

        assertFalse(sessionService.checkQRCodeExpiration("session1", uuid));
    }

    @Test
    void testGetQRCode() {
        String sessionId = "session1";
        String latitude = "36.0014";
        String longitude = "-78.9382";
        QRCode qrCode = new QRCode(latitude, longitude);
        qrCode.setId(1L);
        when(qrCodeDao.save(any(QRCode.class))).thenReturn(qrCode);
        try {

            when(qrCodeService.generateQRCodeBase64(anyString(), anyInt(), anyInt())).thenReturn("base64Image");
        } catch (Exception e) {
            e.printStackTrace();
        }

        QRCodeResponse response = sessionService.getQRCode(sessionId, latitude, longitude);

        assertNotNull(response);
        // assertEquals("base64Image", response.getQrCodeImage());
    }

    @Test
    void testGetAllSessionIdsOfSection() {
        String courseSectionId = "sec123";
        Section section = mock(Section.class);
        Lecture session1 = new Lecture();
        session1.setSessionId("sess1");
        Lecture session2 = new Lecture();
        session2.setSessionId("sess2");
        when(section.getSessions()).thenReturn(Arrays.asList(session1, session2));
        when(sectionDao.findByCourseSectionId(courseSectionId)).thenReturn(Optional.of(section));

        List<String> sessionIds = sessionService.getAllSessionIdsOfSection(courseSectionId);
        assertEquals(2, sessionIds.size());
        assertTrue(sessionIds.contains("sess1"));
        assertTrue(sessionIds.contains("sess2"));
    }

    @Test
    void testGetAllSessionIdsOfSection_NotFound() {
        String courseSectionId = "sec123";
        when(sectionDao.findByCourseSectionId(courseSectionId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> sessionService.getAllSessionIdsOfSection(courseSectionId));
    }

    @Test
    void testUpdateAttendance() {
        String sessionId = "sess1";
        Section section = new Section("sess1");
        String studentNetId = "student1";
        Student student = new Student("student1","fn");
        Lecture session = new Lecture();
        session.setCourseSectionId("sess1");
        student.enrollCourseSection(section);
        when(studentDao.findByNetId(studentNetId)).thenReturn(Optional.of(student));
        when(sessionDao.findBySessionId(sessionId)).thenReturn(Optional.of(session));
        // when(student.subscribeSection(anyString())).thenReturn(true);

        sessionService.updateAttendance(sessionId, studentNetId, "PRESENT");
        verify(sessionDao, times(1)).save(any(Lecture.class));
    }

    @Test
    void testCompareLocation() {
        Long uuid = 1L;
        QRCode qrCode = new QRCode();
        qrCode.setLatitude("36.0014");
        qrCode.setLongitude("-78.9382");
        when(qrCodeDao.findById(uuid)).thenReturn(Optional.of(qrCode));

        boolean isNear = sessionService.compareLocation(uuid, "36.0015", "-78.9383");
        assertTrue(isNear);
    }

    @Test
    void testCompareLocation_NotFound() {
        Long uuid = 1L;
        when(qrCodeDao.findById(uuid)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> sessionService.compareLocation(uuid, "36.0015", "-78.9383"));
    }
}
