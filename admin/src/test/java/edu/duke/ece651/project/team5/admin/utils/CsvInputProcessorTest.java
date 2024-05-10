package edu.duke.ece651.project.team5.admin.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import java.nio.file.NoSuchFileException;

public class CsvInputProcessorTest {
  @Test
  public void test_checkFormat() {

    InputProcessor inputProcessor = new CsvInputProcessor();

    assertTrue(inputProcessor.checkEmailFormat("hiep@duke.edu"));
    assertFalse(inputProcessor.checkEmailFormat("hiep"));
    assertTrue(inputProcessor.checkNameFormat("Hiep Nguyen"));
    assertTrue(inputProcessor.checkNameFormat("Hiep Hoang Nguyen"));
    assertFalse(inputProcessor.checkNameFormat("Hiep"));

    assertTrue(inputProcessor.checkNetIdFormat("hhn3"));
    assertFalse(inputProcessor.checkNetIdFormat("2"));

    assertTrue(inputProcessor.checkPhoneFormat("9198889999"));
    assertFalse(inputProcessor.checkPhoneFormat("sdf"));
    // assertFalse(inputProcessor.checkPhoneFormat(null));

  }
  @Test
  public void test_parseRosterCsv() {
    InputProcessor input = new CsvInputProcessor();
    assertThrows(NoSuchFileException.class, () -> input.parseRosterCsv("src/test/resources/roster_invalid"));
    assertDoesNotThrow(() -> input.parseRosterCsv("src/test/resources/test_roster.csv"));
    assertThrows(IllegalArgumentException.class, () -> input.parseRosterCsv("src/test/resources/wrongEmail.csv"));
    assertThrows(IllegalArgumentException.class, () -> input.parseRosterCsv("src/test/resources/wrongLegalName.csv"));
    assertThrows(IllegalArgumentException.class, () -> input.parseRosterCsv("src/test/resources/wrongPhone.csv"));
    assertThrows(IllegalArgumentException.class, () -> input.parseRosterCsv("src/test/resources/wrongNetId.csv"));
    assertDoesNotThrow(() -> input.parseRosterCsv("src/test/resources/complete.csv"));
    assertThrows(IllegalArgumentException.class, () -> input.parseRosterCsv("src/test/resources/missingHeader.csv"));
    assertDoesNotThrow(()-> input.parseRosterCsv("src/test/resources/noPhone.csv"));
  }

}
