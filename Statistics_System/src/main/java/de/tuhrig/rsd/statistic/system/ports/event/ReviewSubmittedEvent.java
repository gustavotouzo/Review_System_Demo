package de.tuhrig.rsd.statistic.system.ports.event;

import lombok.Data;

@Data
public class ReviewSubmittedEvent {
    private String reviewId;
}