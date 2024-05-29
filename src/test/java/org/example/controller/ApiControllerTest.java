package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiControllerTest {
    Logger logger = LoggerFactory.getLogger(ApiControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testMessage() throws Exception {
        String json = Files.readString(Path.of("src/test/resources/request.json"));

        logger.info("JSON: " + json);

        var request = post("/new/message")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        var result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo("OK");
    }
}
