package com.stukans.pm.service;

import com.stukans.pm.IntegrationTest;
import com.stukans.pm.domain.FeedRecord;
import com.stukans.pm.repository.FeeRecordRepository;
import com.stukans.pm.security.AuthoritiesConstants;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

@IntegrationTest
@WithMockUser(authorities = AuthoritiesConstants.ANONYMOUS)
class FeedServiceTest {

    public final Instant NOW = toInstant(2020, 1, 25);

    @Autowired
    private FeedService feedService;

    @Autowired
    private FeeRecordRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void name() {
        feedService.save(getFeedRecord("Person1", 10, 10, NOW));
        feedService.save(getFeedRecord("Person2", 20, 20, NOW));
        FeedRecord person3 = feedService.save(getFeedRecord("Person3", 30, 30, NOW));
        feedService.save(getFeedRecord("Person4", 40, 40, NOW));

        Optional<FeedRecord> optionalFeedRecord = feedService.findById(person3.getId());
        Assertions.assertTrue(optionalFeedRecord.isPresent());
        FeedRecord record = optionalFeedRecord.get();
        Assertions.assertNotNull(record.getCreatedBy());
        Assertions.assertNotNull(record.getLastModifiedBy());
        Assertions.assertNotNull(record.getCreatedDate());
        Assertions.assertNotNull(record.getLastModifiedDate());
        Assertions.assertEquals("Person3", record.getPerson());
        Assertions.assertEquals("Royal Canin", record.getFood());
        Assertions.assertEquals(30, record.getFoodGiven());
        Assertions.assertEquals(30, record.getFoodEaten());
    }

    @Test
    void findByDate() {
        feedService.save(getFeedRecord("Person", 10, 10, toInstant(2020, 1, 25)));
        feedService.save(getFeedRecord("Person", 10, 10, toInstant(2020, 1, 25)));
        feedService.save(getFeedRecord("Person", 10, 10, toInstant(2020, 1, 25)));
        feedService.save(getFeedRecord("Person", 10, 10, toInstant(2020, 1, 25)));
        feedService.save(getFeedRecord("Person", 10, 10, toInstant(2020, 1, 26)));
        feedService.save(getFeedRecord("Person", 10, 10, toInstant(2020, 1, 26)));
        feedService.save(getFeedRecord("Person", 10, 10, toInstant(2020, 1, 27)));

        Assertions.assertEquals(4, feedService.findAllByDate(toInstant(2020, 1, 25)).size());
        Assertions.assertEquals(2, feedService.findAllByDate(toInstant(2020, 1, 26)).size());
        Assertions.assertEquals(1, feedService.findAllByDate(toInstant(2020, 1, 27)).size());
        Assertions.assertEquals(0, feedService.findAllByDate(toInstant(2020, 1, 28)).size());
    }

    private Instant toInstant(int year, int month, int day) {
        return Instant.from(LocalDate.of(year, month, day).atStartOfDay().atOffset(ZoneOffset.UTC));
    }

    private static FeedRecord getFeedRecord(String person, int foodGiven, int foodEaten, Instant date) {
        FeedRecord record = new FeedRecord();
        record.setDate(date);
        record.setPerson(person);
        record.setFood("Royal Canin");
        record.setFoodGiven(foodGiven);
        record.setFoodEaten(foodEaten);
        return record;
    }
}
