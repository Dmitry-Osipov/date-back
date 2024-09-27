package com.osipov.date.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TimeRangeRequest(@NotNull LocalDateTime from, @NotNull LocalDateTime to) {
}
