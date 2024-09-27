package com.osipov.date.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osipov.date.dto.TimePeriodResponse;
import com.osipov.date.dto.TimeRangeRequest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TimeControllerTest {
    private static final String CONTENT_TYPE_JSON = "application/json";
    @Autowired
    private MockMvc sut;
    @Autowired
    private ObjectMapper mapper;

    @Test
    @SneakyThrows
    void calculateDifferenceWithCorrectDataReturnCorrectData() {
        TimeRangeRequest range = new TimeRangeRequest(
                LocalDateTime.of(2020, 1, 1, 12, 10),
                LocalDateTime.of(2024, 10, 4, 18, 28)
        );
        String request = mapper.writeValueAsString(range);
        TimePeriodResponse expected = new TimePeriodResponse(4, 9, 3, 6, 18);

        sut.perform(post("/api/time")
                        .contentType(CONTENT_TYPE_JSON)
                        .content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.years").value(expected.years()))
                .andExpect(jsonPath("$.months").value(expected.months()))
                .andExpect(jsonPath("$.days").value(expected.days()))
                .andExpect(jsonPath("$.hours").value(expected.hours()))
                .andExpect(jsonPath("$.minutes").value(expected.minutes()));
    }

    @Test
    @SneakyThrows
    void calculateDifferenceWithIncorrectDataReturnCorrectData() {
        TimeRangeRequest range = new TimeRangeRequest(
                LocalDateTime.of(2024, 10, 4, 18, 28),
                LocalDateTime.of(2020, 1, 1, 12, 10)
        );
        TimePeriodResponse expected = new TimePeriodResponse(4, 9, 3, 6, 18);
        String request = mapper.writeValueAsString(range);

        sut.perform(post("/api/time")
                        .contentType(CONTENT_TYPE_JSON)
                        .content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.years").value(expected.years()))
                .andExpect(jsonPath("$.months").value(expected.months()))
                .andExpect(jsonPath("$.days").value(expected.days()))
                .andExpect(jsonPath("$.hours").value(expected.hours()))
                .andExpect(jsonPath("$.minutes").value(expected.minutes()));
    }

    @Test
    @SneakyThrows
    void calculateDifferenceWithSameDateReturnCorrectData() {
        TimeRangeRequest range = new TimeRangeRequest(
                LocalDateTime.of(2024, 10, 4, 18, 28),
                LocalDateTime.of(2024, 10, 4, 18, 28)
        );
        TimePeriodResponse expected = new TimePeriodResponse(0, 0, 0, 0, 0);
        String request = mapper.writeValueAsString(range);

        sut.perform(post("/api/time")
                        .contentType(CONTENT_TYPE_JSON)
                        .content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.years").value(expected.years()))
                .andExpect(jsonPath("$.months").value(expected.months()))
                .andExpect(jsonPath("$.days").value(expected.days()))
                .andExpect(jsonPath("$.hours").value(expected.hours()))
                .andExpect(jsonPath("$.minutes").value(expected.minutes()));
    }

    @Test
    @SneakyThrows
    void calculateDifferenceWithIncorrectDateFormatThrowsException() {
        String request = "{\"from\":\"2024-13-04T18:28:00\",\"to\":\"2024-10-04T18:28:00\"}";

        sut.perform(post("/api/time")
                        .contentType(CONTENT_TYPE_JSON)
                        .content(request))
                .andExpect(status().isBadRequest());
    }
}
