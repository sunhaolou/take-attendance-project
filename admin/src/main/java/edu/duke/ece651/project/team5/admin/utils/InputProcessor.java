package edu.duke.ece651.project.team5.admin.utils;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

public interface InputProcessor {
  public Boolean checkNameFormat(String name);
  public Boolean checkEmailFormat(String email);
  public Boolean checkNetIdFormat(String netId);
  public Boolean checkPhoneFormat(String phone);
  // public Boolean checkPasswordFormat(String password);
  // public JSONObject createJsonStudent(String line);
  
  public List<JSONObject> parseRosterCsv(String file_name) throws IOException;
}

