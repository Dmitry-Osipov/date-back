package com.osipov.date.entity;

import java.time.LocalDateTime;

public record TimeRange(LocalDateTime from, LocalDateTime to) {
    public TimeRange abs() {
        return from.isAfter(to) ? new TimeRange(to, from) : this;
    }
}
