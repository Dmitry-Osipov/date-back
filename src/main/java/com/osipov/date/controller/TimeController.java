package com.osipov.date.controller;

import com.osipov.date.dto.TimePeriodResponse;
import com.osipov.date.dto.TimeRangeRequest;
import com.osipov.date.service.DateTimeCalculatorService;
import com.osipov.date.utils.TimeMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/time")
public class TimeController {
    private final TimeMapper mapper;
    private final DateTimeCalculatorService service;

    @PostMapping
    public TimePeriodResponse calculateDifference(@RequestBody @Valid TimeRangeRequest request) {
        return mapper.toDto(service.calculateDifference(mapper.toEntity(request)));
    }
}
