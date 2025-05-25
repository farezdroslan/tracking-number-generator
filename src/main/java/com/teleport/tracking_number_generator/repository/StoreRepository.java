package com.teleport.tracking_number_generator.repository;

import com.teleport.tracking_number_generator.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    @Query("SELECT s FROM Store s WHERE s.originCountryId = :originCountryId " +
            "AND s.destinationCountryId = :destinationCountryId " +
            "AND s.weight = :weight " +
            "AND s.customerId = :customerId " +
            "AND s.customerName = :customerName " +
            "AND s.customerSlug = :customerSlug")
    List<Store> findWithCriteria(
            @Param("originCountryId") String originCountryId,
            @Param("destinationCountryId") String destinationCountryId,
            @Param("weight") BigDecimal weight,
            @Param("customerId") UUID customerId,
            @Param("customerName") String customerName,
            @Param("customerSlug") String customerSlug
    );

    List<Store> findByOriginCountryIdAndDestinationCountryIdAndWeightAndCreatedAtAndCustomerIdAndCustomerNameAndCustomerSlug(String originCountryId, String destinationCountryId, BigDecimal weight, OffsetDateTime createdAt, UUID customerId, String customerName, String customerSlug);
}
