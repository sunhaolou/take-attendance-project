package edu.duke.ece651.project.team5.frontend.controller;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.duke.ece651.project.team5.shared.response.LoginResponse;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TokenStore tokenStore;

    @MockBean
    private ObjectMapper objectMapper;

    @MockBean
    private HttpClient client;

    @BeforeEach
    public void setup() throws Exception {
        // Create a mock HttpResponse specifically for String body type
        HttpResponse<String> mockResponse = mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body())
                .thenReturn("{\"token\":\"12345\",\"role\":\"ROLE_PROFESSOR\",\"legalName\":\"John Doe\"}");

        // Mock the HttpClient to return our mocked HttpResponse
        when(client.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        // Mocking ObjectMapper behavior
        when(objectMapper.readValue(anyString(), any(Class.class)))
                .thenReturn(new LoginResponse("12345", "John Doe", "ROLE_PROFESSOR"));
        when(objectMapper.writeValueAsString(any())).thenReturn("{\"userNetId\":\"user\",\"password\":\"pass\"}");
    }

    @Test
    public void testShowLoginPage() throws Exception {
        mockMvc.perform(get("/auth/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void testHandleLogin() throws Exception {
        mockMvc.perform(post("/auth/login")
                .param("userNetId", "user")
                .param("password", "pass"));
                // .andExpect(status().is3xxRedirection())
                // .andExpect(redirectedUrl("/professor/dashboard"));
    }
}
