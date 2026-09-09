package com.hotstar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "Hotstar Backend is Running";
    }

    @GetMapping("/api/hello")
    public Map<String, String> hello() {

        Map<String, String> response = new HashMap<>();

        response.put("application", "Hotstar");
        response.put("message", "Hello from Spring Boot");
        response.put("status", "success");

        return response;
    }

    @GetMapping("/api/movies")
    public String movies() {

        return """
                [
                  {
                    "id": 1,
                    "name": "Movie One"
                  },
                  {
                    "id": 2,
                    "name": "Movie Two"
                  },
                  {
                    "id": 3,
                    "name": "Movie Three"
                  }
                ]
                """;
    }
}
