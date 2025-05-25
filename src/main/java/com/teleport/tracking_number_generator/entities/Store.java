package com.teleport.tracking_number_generator.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "T_STORE")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ORIGIN_COUNTRY_ID", length = 2)
    private String originCountryId;

    @Column(name = "DESTINATION_COUNTRY_ID", length = 2)
    private String destinationCountryId;

    @Column(name = "WEIGHT", precision = 10, scale = 3)
    private BigDecimal weight;

    @Column(name = "CREATED_AT")
    private OffsetDateTime createdAt;

    @Column(name = "CUSTOMER_ID")
    private UUID customerId;

    @Column(name = "CUSTOMER_NAME")
    private String customerName;

    @Column(name = "CUSTOMER_SLUG")
    private String customerSlug;

    @Column(name = "TRACKING_NUMBER", unique = true)
    private String trackingNumber;

    // ===== Constructors =====
    public String getOriginCountryId() {
        return originCountryId;
    }

    public String getDestinationCountryId() {
        return destinationCountryId;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerSlug() {
        return customerSlug;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    // ===== Setters =====

    public void setId(Long id) {
        this.id = id;
    }

    public void setOriginCountryId(String originCountryId) {
        this.originCountryId = originCountryId;
    }

    public void setDestinationCountryId(String destinationCountryId) {
        this.destinationCountryId = destinationCountryId;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerSlug(String customerSlug) {
        this.customerSlug = customerSlug;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}
