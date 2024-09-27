package com.osipov.date.utils;

import com.osipov.date.dto.TimePeriodResponse;
import com.osipov.date.dto.TimeRangeRequest;
import com.osipov.date.entity.TimePeriod;
import com.osipov.date.entity.TimeRange;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TimeMapper {
    TimeRange toEntity(TimeRangeRequest timeRangeRequest);
    TimePeriodResponse toDto(TimePeriod timePeriod);
}
