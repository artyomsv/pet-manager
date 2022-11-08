package com.stukans.pm.repository;

import com.stukans.pm.domain.Authority;
import com.stukans.pm.domain.FeedRecord;
import java.time.Instant;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the {@link Authority} entity.
 */
@Repository
public interface FeeRecordRepository extends MongoRepository<FeedRecord, String> {
    @Query("{ 'date' : { '$gte': ?0, '$lt': ?1 } }")
    List<FeedRecord> findByDateBetween(Instant from, Instant to);
}
