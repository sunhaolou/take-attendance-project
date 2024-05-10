package edu.duke.ece651.project.team5.admin.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.*;
import edu.duke.ece651.project.team5.shared.model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.util.JSONPObject;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
/**
 * This class implements the InputProcessor interface and provides methods for processing CSV input.
 * It includes methods for checking the format of name, email, netId, and phone number,
 * as well as parsing a roster CSV file and creating JSON objects from the CSV records.
 */
public class CsvInputProcessor implements InputProcessor {
  
  /**
   * Checks the format of a name.
   *
   * @param name the name to be checked
   * @return true if the name matches the format, false otherwise
   */
  @Override
  public Boolean checkNameFormat(String name){
    return name.matches("([a-zA-Z])+( ([a-zA-Z])+)+");
  }
  
  /**
   * Checks the format of an email address.
   *
   * @param email the email address to be checked
   * @return {@code true} if the email address has a valid format, {@code false} otherwise
   */
  @Override
  public Boolean checkEmailFormat(String email){
    return email.matches("\\S+@\\S+");
  }
  /**
   * Checks the format of a given netId.
   *
   * @param netId the netId to be checked
   * @return true if the netId matches the required format, false otherwise
   */
  @Override
  public Boolean checkNetIdFormat(String netId){
    return netId.matches("([a-zA-Z])+(\\d)+");
  }
  /**
   * Checks the format of a phone number.
   *
   * @param phone the phone number to check
   * @return true if the phone number matches the format, false otherwise
   */
  @Override
  public Boolean checkPhoneFormat(String phone){
    return phone.matches("\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d");
  }

  /**
   * Parses a CSV file containing roster information and returns a list of JSON objects.
   *
   * @param file_name the path of the CSV file to be parsed
   * @return a list of JSON objects representing the records in the CSV file
   * @throws IllegalArgumentException if the file name is null or empty
   * @throws NoSuchFileException if the specified file does not exist
   */
  @Override
  public List<JSONObject> parseRosterCsv(String file_name) throws IllegalArgumentException, NoSuchFileException, IOException {
    List<JSONObject> record = new ArrayList<JSONObject>();
    try (Reader reader = Files.newBufferedReader(Paths.get(file_name));
       CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

      for (CSVRecord csvRecord : csvParser) {
        // Accessing values by the names assigned to each column
        record.add(createJsonObject(csvRecord));
      }
    } catch (NoSuchFileException e) {
      throw e;
    }
    catch (IOException e) {
      e.printStackTrace();
      throw e;
    } 
    return record;
  }

  /**
   * A class that represents a JSON object. It provides methods to manipulate and access the properties of the JSON object.
   */
  private JSONObject createJsonObject(CSVRecord csvRecord) throws IllegalArgumentException {
    JSONObject person = new JSONObject();
        
    String netId = csvRecord.get("netId");
    if (!checkNetIdFormat(netId)){
      throw new IllegalArgumentException("csv file is invalid - netId is invalid");
    }
    person.put("netId", netId);
    String password = csvRecord.get("password");
    person.put("password", password);
    String legalName = csvRecord.get("legalName");
    if (!checkNameFormat(legalName)){
      throw new IllegalArgumentException("csv file is invalid - legalName is invalid");
    }
    person.put("legalName", legalName);
    String email = csvRecord.get("email");
    if (!checkEmailFormat(email)){
      throw new IllegalArgumentException("csv file is invalid - email is invalid");
    }
    person.put("email", email);
    Boolean hasPhone = false;
    String phone = "";
    try {
      phone = csvRecord.get("phone");
      hasPhone = true;
    }
    catch (IllegalArgumentException e) {
      // hasPhone = false;
    }
    if (hasPhone){
      phone = csvRecord.get("phone");
      if (!checkPhoneFormat(phone)){
        throw new IllegalArgumentException("csv file is invalid - phone exists but phone number is invalid");
      }
    }
    person.put("phone", phone);
    String preferredName = "";
    try {
      preferredName = csvRecord.get("preferredName");
      
    }
    catch (IllegalArgumentException e) {
    }
    person.put("preferredName", preferredName);
    return person;
  }
}


