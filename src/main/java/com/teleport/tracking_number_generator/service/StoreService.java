package com.teleport.tracking_number_generator.service;

import com.teleport.tracking_number_generator.entities.Store;
import com.teleport.tracking_number_generator.models.StoreModel;
import com.teleport.tracking_number_generator.models.TrackNumber;
import com.teleport.tracking_number_generator.repository.StoreRepository;
import com.teleport.tracking_number_generator.util.TrackNumberGen;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

import java.time.OffsetDateTime;
import java.util.List;

@Transactional
@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<Store> getTrackingNumber(StoreModel store) {

        return storeRepository.findWithCriteria(
                store.getOriginCountryId(),
                store.getDestinationCountryId(),
                store.getWeight(),
                store.getCustomerId(),
                store.getCustomerName(),
                store.getCustomerSlug()
        );

    }

    public Store insert(StoreModel storeModel) {
        List<Store> existingRecord = storeRepository.findByOriginCountryIdAndDestinationCountryIdAndWeightAndCreatedAtAndCustomerIdAndCustomerNameAndCustomerSlug(
                storeModel.getOriginCountryId(),
                storeModel.getDestinationCountryId(),
                storeModel.getWeight(),
                storeModel.getCreatedAt(),
                storeModel.getCustomerId(),
                storeModel.getCustomerName(),
                storeModel.getCustomerSlug()
        );

        if (!existingRecord.isEmpty()) {
            return existingRecord.get(0);
        }

        Store newStore = new Store();
        newStore.setOriginCountryId(storeModel.getOriginCountryId());
        newStore.setDestinationCountryId(storeModel.getDestinationCountryId());
        newStore.setWeight(storeModel.getWeight());
        newStore.setCreatedAt(storeModel.getCreatedAt());
        newStore.setCustomerId(storeModel.getCustomerId());
        newStore.setCustomerName(storeModel.getCustomerName());
        newStore.setCustomerSlug(storeModel.getCustomerSlug());
        storeRepository.save(newStore);

        return newStore;
    }

    public Optional<TrackNumber> getOrGenerateTrackingNumber(StoreModel storeModel) {
        // get
        List<Store> existRecords = storeRepository.findWithCriteria(
                storeModel.getOriginCountryId(),
                storeModel.getDestinationCountryId(),
                storeModel.getWeight(),
                storeModel.getCustomerId(),
                storeModel.getCustomerName(),
                storeModel.getCustomerSlug()
        );

        Optional<Store> validRecord = existRecords.stream()
                .filter(store -> store.getTrackingNumber() != null)
                .findFirst();

        if (validRecord.isPresent()) {
            TrackNumber trackingNumberRecord = new TrackNumber();
            trackingNumberRecord.setTrackingNumber(validRecord.get().getTrackingNumber());
            trackingNumberRecord.setCreatedAt(validRecord.get().getCreatedAt());
            return Optional.of(trackingNumberRecord);
        }

        Optional<Store> noTrackingNumberRecord = existRecords.stream().findFirst();
        if (noTrackingNumberRecord.isPresent()) {
            Store store = noTrackingNumberRecord.get();
            store.setTrackingNumber(TrackNumberGen.generate());
            Store updatedRecord = storeRepository.save(store);
            TrackNumber updatedTrackingNumber = new TrackNumber();
            updatedTrackingNumber.setTrackingNumber(updatedRecord.getTrackingNumber());
            updatedTrackingNumber.setCreatedAt(updatedRecord.getCreatedAt());
            return Optional.of(updatedTrackingNumber);
        }
        return Optional.empty();
    }

//    public void update(Long id, String createdAt, String name) {
//        Optional<Store> optStore = storeRepository.findById(id);
//
//        if (optStore.isEmpty()) {
//            throw new RuntimeException("Store not found");
//        }
//        String createdAtFixed = createdAt.replace(" ", "+");
//        Store store = optStore.get();
//        store.setCreatedAt(OffsetDateTime.parse(createdAtFixed));
//        store.setCustomerName(name);
//
//        storeRepository.save(store);
//    }
}
