package edu.duke.ece651.project.team5.frontend;

import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.json.JSONObject;

import java.net.Socket;
import java.net.UnknownHostException;

public class TextUserTest {
  @Test
  void testSetPassword() throws UnknownHostException, IOException {
    Socket socket = new Socket();
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader socketIn = input;
    PrintWriter socketOut = new PrintWriter(System.out);
    TextUser textuser = new TextUser("", "", input, socket, socketIn, socketOut);
    textuser.takeAction();
    textuser.exportAttendance();
    textuser.setPassword("fefawe");
  }

  @Test
  void testSetUserNetID() throws IOException {
    Socket socket = new Socket();
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader socketIn = input;
    PrintWriter socketOut = new PrintWriter(System.out);
    TextUser textuser = new TextUser("", "", input, socket, socketIn, socketOut);
    textuser.setUserNetID("ansdf");
  }
}
