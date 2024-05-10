// package edu.duke.ece651.project.team5.shared.model;
// import static org.junit.jupiter.api.Assertions.*;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// public class ReportTest {
//     private Report report;

//     @BeforeEach
//     public void setup() {
//         report = new Report("Alice123", "2022-01-01", "Completed");
//     }

//     @Test
//     public void testGetStatus() {
//         assertEquals("Completed", report.getStatus());
//     }

//     @Test
//     public void testGetNetId() {
//         assertEquals("Alice123", report.getNetId());
//     }

//     @Test
//     public void testGetTime() {
//         assertEquals("2022-01-01", report.getTime());
//     }

//     @Test
//     public void testDefaultConstructor() {
//         Report defaultReport = new Report();
//         assertEquals("", defaultReport.getStatus());
//         assertEquals("", defaultReport.getNetId());
//         assertEquals("", defaultReport.getTime());
//     }
// }