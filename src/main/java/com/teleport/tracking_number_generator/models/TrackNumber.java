package com.teleport.tracking_number_generator.models;

import java.time.OffsetDateTime;

public class TrackNumber {
    private String trackingNumber;
    private OffsetDateTime createdAt;

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
