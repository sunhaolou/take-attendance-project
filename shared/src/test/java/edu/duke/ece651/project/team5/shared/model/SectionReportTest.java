package edu.duke.ece651.project.team5.shared.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class SectionReportTest {
  @Test
    public void testAddRecordAndGetAttendance() {
        SectionReport sectionReport = new SectionReport();
        
        // 添加记录
        sectionReport.addRecord("student1", "90");
        sectionReport.addRecord("student2", "85");
        
        // 检查记录
        Map<String, String> expectedAttendance = new HashMap<>();
        expectedAttendance.put("student1", "90");
        expectedAttendance.put("student2", "85");
        assertEquals(expectedAttendance, sectionReport.getAttendance());
    }

    @Test
    public void testSetAttendanceAndGetAttendance() {
        SectionReport sectionReport = new SectionReport();
        
        // 设置出勤记录
        Map<String, String> attendance = new HashMap<>();
        attendance.put("student1", "90");
        attendance.put("student2", "85");
        sectionReport.setAttendance(attendance);
        
        // 检查记录
        assertEquals(attendance, sectionReport.getAttendance());
    }
}
