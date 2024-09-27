package com.osipov.date.service;

import com.osipov.date.entity.TimePeriod;
import com.osipov.date.entity.TimeRange;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

@Service
public class DateTimeCalculatorServiceImpl implements DateTimeCalculatorService {
    @Override
    public TimePeriod calculateDifference(TimeRange range) {
        TimeRange absRange = range.abs();
        LocalDateTime from = absRange.from();
        LocalDateTime to = absRange.to();

        Period period = Period.between(from.toLocalDate(), to.toLocalDate());
        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();
        LocalDateTime adjustedTime = from
                .plusYears(years)
                .plusMonths(months)
                .plusDays(days);
        Duration duration = Duration.between(adjustedTime, to).abs();

        return new TimePeriod(years, months, days, (int) duration.toHours(), (int) (duration.toMinutes() % 60));
    }
}
