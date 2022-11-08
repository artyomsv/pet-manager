package com.stukans.pm.service;

import com.stukans.pm.domain.FeedRecord;
import com.stukans.pm.repository.FeeRecordRepository;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class FeedService {

    private final FeeRecordRepository repository;

    public FeedService(FeeRecordRepository repository) {
        this.repository = repository;
    }

    public FeedRecord save(FeedRecord record) {
        if (record.getId() == null) {
            record.setId(UUID.randomUUID().toString());
        }
        if (record.getDate() == null) {
            record.setDate(Instant.now());
        }
        return repository.save(record);
    }

    public FeedRecord update(FeedRecord record) {
        Optional<FeedRecord> optional = findById(record.getId());
        if (optional.isPresent()) {
            FeedRecord existingRecord = optional.get();
            existingRecord.setPerson(record.getPerson());
            existingRecord.setFood(record.getFood());
            existingRecord.setFoodGiven(record.getFoodGiven());
            existingRecord.setFoodEaten(record.getFoodEaten());
            return save(existingRecord);
        }
        throw new RuntimeException("FeedRecord with " + record.getId() + " not found!");
    }

    public Optional<FeedRecord> findById(String id) {
        return repository.findById(id);
    }

    public List<FeedRecord> findAllByDate(Instant date) {
        return repository.findByDateBetween(date, date.plus(Duration.ofDays(1)));
    }
}
