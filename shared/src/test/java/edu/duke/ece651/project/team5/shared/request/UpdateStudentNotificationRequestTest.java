package edu.duke.ece651.project.team5.shared.request;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UpdateStudentNotificationRequestTest {
    @Test
    void testSetterAndGetter() {
        UpdateStudentNotificationRequest request = new UpdateStudentNotificationRequest();

        String courseSectionId = "CS101";
        String notificationMethod = "email";

        request.setCourseSectionId(courseSectionId);
        request.setNotificationMethod(notificationMethod);

        assertEquals(courseSectionId, request.getCourseSectionId());
        assertEquals(notificationMethod, request.getNotificationMethod());
    }

    @Test
    void testConstructor() {
        String courseSectionId = "CS101";
        String notificationMethod = "email";

        UpdateStudentNotificationRequest request = new UpdateStudentNotificationRequest(courseSectionId, notificationMethod);

        assertEquals(courseSectionId, request.getCourseSectionId());
        assertEquals(notificationMethod, request.getNotificationMethod());
    }
}
