package com.osipov.date.service;

import com.osipov.date.entity.TimePeriod;
import com.osipov.date.entity.TimeRange;

public interface DateTimeCalculatorService {
    TimePeriod calculateDifference(TimeRange range);
}
