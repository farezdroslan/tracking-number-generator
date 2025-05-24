package com.teleport.tracking_number_generator.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreModel {

    private Long id;

//    @NotBlank(message = "Origin country ID is required")
//    @Pattern(regexp = "^[A-Z]{2}$", message = "Origin country ID must be 2 uppercase letters (ISO 3166-1 alpha-2)")
    private String originCountryId;

//    @NotBlank(message = "Destination country ID is required")
//    @Pattern(regexp = "^[A-Z]{2}$", message = "Destination country ID must be 2 uppercase letters (ISO 3166-1 alpha-2)")
    private String destinationCountryId;

//    @NotNull(message = "Weight is required")
//    @DecimalMin(value = "0.001", inclusive = true, message = "Weight must be at least 0.001 kg")
//    @DecimalMax(value = "999999.999", message = "Weight cannot exceed 999999.999 kg")
    private BigDecimal weight;

//    @NotNull(message = "Created at is required")
    private OffsetDateTime createdAt;

//    @NotNull(message = "Customer ID is required")
    private UUID customerId;

//    @NotBlank(message = "Customer name is required")
    private String customerName;

//    @NotBlank(message = "Customer slug is required")
//    @Pattern(regexp = "^[a-z0-9]+(-[a-z0-9]+)*$", message = "Customer slug must be lowercase, hyphen-separated, no spaces or special characters")
    private String customerSlug;

    private String trackingNumber;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginCountryId() {
        return originCountryId;
    }

    public void setOriginCountryId(String originCountryId) {
        this.originCountryId = originCountryId;
    }

    public String getDestinationCountryId() {
        return destinationCountryId;
    }

    public void setDestinationCountryId(String destinationCountryId) {
        this.destinationCountryId = destinationCountryId;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSlug() {
        return customerSlug;
    }

    public void setCustomerSlug(String customerSlug) {
        this.customerSlug = customerSlug;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}
