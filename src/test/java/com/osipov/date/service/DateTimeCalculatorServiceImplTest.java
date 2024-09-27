package com.osipov.date.service;

import com.osipov.date.entity.TimePeriod;
import com.osipov.date.entity.TimeRange;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DateTimeCalculatorServiceImplTest {
    @InjectMocks
    private DateTimeCalculatorServiceImpl sut;

    @Test
    void calculateDifferenceWithCorrectDataReturnCorrectData() {
        TimeRange range = new TimeRange(
                LocalDateTime.of(2020, 1, 2, 3, 5),
                LocalDateTime.of(2024, 10, 21, 15, 47)
        );
        TimePeriod expected = new TimePeriod(4, 9, 19, 12, 42);

        TimePeriod actual = assertDoesNotThrow(() -> sut.calculateDifference(range));

        assertEquals(expected, actual);
    }

    @Test
    void calculateDifferenceWithIncorrectDataReturnCorrectData() {
        TimeRange range = new TimeRange(
                LocalDateTime.of(2024, 10, 21, 15, 47),
                LocalDateTime.of(2020, 1, 2, 3, 5)
        );
        TimePeriod expected = new TimePeriod(4, 9, 19, 12, 42);

        TimePeriod actual = assertDoesNotThrow(() -> sut.calculateDifference(range));

        assertEquals(expected, actual);
    }

    @Test
    void calculateDifferenceWithSameDateAndAnotherTimeReturnCorrectData() {
        TimeRange range = new TimeRange(
                LocalDateTime.of(2024, 10, 21, 15, 47),
                LocalDateTime.of(2024, 10, 21, 21, 5)
        );
        TimePeriod expected = new TimePeriod(0, 0, 0, 5, 18);

        TimePeriod actual = assertDoesNotThrow(() -> sut.calculateDifference(range));

        assertEquals(expected, actual);
    }

    @Test
    void calculateDifferenceWithSameDateAndTimeReturnCorrectData() {
        TimeRange range = new TimeRange(
                LocalDateTime.of(2024, 10, 21, 15, 47),
                LocalDateTime.of(2024, 10, 21, 15, 47)
        );
        TimePeriod expected = new TimePeriod(0, 0, 0, 0, 0);

        TimePeriod actual = assertDoesNotThrow(() -> sut.calculateDifference(range));

        assertEquals(expected, actual);
    }
}
