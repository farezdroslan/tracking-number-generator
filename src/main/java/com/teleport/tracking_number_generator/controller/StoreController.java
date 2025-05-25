package com.teleport.tracking_number_generator.controller;

import com.teleport.tracking_number_generator.entities.Store;
import com.teleport.tracking_number_generator.models.StoreModel;
import com.teleport.tracking_number_generator.models.TrackNumber;
import com.teleport.tracking_number_generator.service.StoreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
@Tag(name = "Tracking Number Generator", description = "API to generate unique tracking numbers based on order details")
public class StoreController {

    private final StoreService storeService;
    private static final Logger log = LoggerFactory.getLogger(StoreController.class);
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@Valid @RequestBody StoreModel storeRequest) {
        log.info("Incoming POST request - Origin Country ID: {}, Destination Country ID: {}",
                storeRequest.getOriginCountryId(), storeRequest.getDestinationCountryId());

        try {
            StoreModel store = new StoreModel();
            store.setOriginCountryId(storeRequest.getOriginCountryId());
            store.setDestinationCountryId(storeRequest.getDestinationCountryId());
            store.setWeight(storeRequest.getWeight());
            store.setCreatedAt(storeRequest.getCreatedAt());
            store.setCustomerId(storeRequest.getCustomerId());
            store.setCustomerName(storeRequest.getCustomerName());
            store.setCustomerSlug(storeRequest.getCustomerSlug());

            Store inserted = storeService.insert(store);
            return ResponseEntity.status(HttpStatus.CREATED).body(inserted);
        } catch (Exception e) {
            log.error("Unexpected error while generating tracking number", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to insert"));
        }
    }

    @GetMapping("/next-tracking-number")
    public ResponseEntity<?> getTrackingNumber(
            @RequestParam("originCountryId") @NotBlank(message = "Origin country ID is required") String originCountryId,
            @RequestParam("destinationCountryId") @NotBlank(message = "Destination country ID is required") String destinationCountryId,
            @RequestParam @NotNull(message = "Weight is required") @DecimalMin(value = "0.001", inclusive = true) BigDecimal weight,
            @RequestParam @NotNull(message = "Created at timestamp is required") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime createdAt,
            @RequestParam @NotNull(message = "Customer ID is required") UUID customerId,
            @RequestParam @NotBlank(message = "Customer name is required") String customerName,
            @RequestParam @NotBlank(message = "Customer slug is required") @Pattern(regexp = "^[a-z0-9]+(-[a-z0-9]+)*$", message = "Must be kebab-case") String customerSlug) {
        log.info("Incoming request with origin_country_id: {}, destination_country_id: {}, created_at: {}",
                originCountryId, destinationCountryId, createdAt);
        // Map to StoreModel
        StoreModel model = new StoreModel();
        model.setOriginCountryId(originCountryId);
        model.setDestinationCountryId(destinationCountryId);
        model.setWeight(weight);
        model.setCustomerId(customerId);
        model.setCustomerName(customerName);
        model.setCustomerSlug(customerSlug);

        Optional<TrackNumber> result = storeService.getOrGenerateTrackingNumber(model);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Tracking number not found"));
        }
        TrackNumber trackedNumber = result.get();
        return ResponseEntity.status(HttpStatus.OK).body(trackedNumber);
    }

//    @PutMapping("/update/{id}")
//    public void update(
//            @PathVariable("id") Long id,
//            @RequestParam(name = "createdAt", required = false)
//            String createdAt, @RequestParam String name) {
//        log.info("createdAt : {}", createdAt);
//        storeService.update(id, createdAt, name);
//    }
}
