// package edu.duke.ece651.project.team5.frontend.controller;

// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.test.web.servlet.MockMvc;

// import com.fasterxml.jackson.core.type.TypeReference;
// import com.fasterxml.jackson.databind.ObjectMapper;

// import edu.duke.ece651.project.team5.frontend.utils.JsonOutputGenerator;
// import edu.duke.ece651.project.team5.frontend.utils.PdfOutputGenerator;
// import edu.duke.ece651.project.team5.frontend.utils.XmlOutputGenerator;
// import edu.duke.ece651.project.team5.shared.request.LoginRequest;
// import edu.duke.ece651.project.team5.shared.response.LoginResponse;

// @WebMvcTest(ProfessorController.class)
// public class ProfessorControllerTest {
//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private TokenStore tokenStore;

//     @MockBean
//     private ObjectMapper objectMapper;

//     @MockBean
//     private JsonOutputGenerator jsonOutputGenerator;

//     @MockBean
//     private XmlOutputGenerator xmlOutputGenerator;
//     @MockBean
//     private PdfOutputGenerator pdfOutputGenerator;

//     @Test
//     public void testShowProfessorPage() throws Exception {

//         // when(objectMapper.writeValueAsString(any(LoginRequest.class))).thenReturn("requestBody");
//         // when(objectMapper.readValue(any(String.class),
//         // any(TypeReference.class))).thenReturn(loginResponse);
//         // LoginResponse loginResponse = new LoginResponse();
//         // loginResponse.setToken("token");
//         // loginResponse.setLegalName("John Doe");
//         // loginResponse.setRole("ROLE_PROFESSOR");

//         // when(objectMapper.writeValueAsString(any(LoginRequest.class))).thenReturn("requestBody");
//         // when(objectMapper.readValue(any(String.class),
//         // any(TypeReference.class))).thenReturn(loginResponse);

//         mockMvc.perform(get("/professor/dashboard"));
//     }

// }
