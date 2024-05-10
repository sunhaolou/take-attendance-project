package edu.duke.ece651.project.team5.frontend.config;

import org.springframework.context.annotation.Bean;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Config {

    @Bean
    public ObjectMapper objectMapper() {

        return new ObjectMapper();
    }

}
