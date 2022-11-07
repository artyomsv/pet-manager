package com.stukans.cfm.web.rest;

import com.stukans.cfm.domain.FeedRecord;
import com.stukans.cfm.domain.User;
import com.stukans.cfm.service.FeedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.web.util.ResponseUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing users.
 * <p>
 * This class accesses the {@link User} entity, and needs to fetch its collection of authorities.
 * <p>
 * For a normal use-case, it would be better to have an eager relationship between User and Authority,
 * and send everything to the client side: there would be no View Model and DTO, a lot less code, and an outer-join
 * which would be good for performance.
 * <p>
 * We use a View Model and a DTO for 3 reasons:
 * <ul>
 * <li>We want to keep a lazy association between the user and the authorities, because people will
 * quite often do relationships with the user, and we don't want them to get the authorities all
 * the time for nothing (for performance reasons). This is the #1 goal: we should not impact our users'
 * application because of this use-case.</li>
 * <li> Not having an outer join causes n+1 requests to the database. This is not a real issue as
 * we have by default a second-level cache. This means on the first HTTP call we do the n+1 requests,
 * but then all authorities come from the cache, so in fact it's much better than doing an outer join
 * (which will get lots of data from the database, for each HTTP call).</li>
 * <li> As this manages users, for security reasons, we'd rather have a DTO layer.</li>
 * </ul>
 * <p>
 * Another option would be to have a specific JPA entity graph to handle this case.
 */
@RestController
@RequestMapping("/api/feed-records")
public class CatFeedResource {

    private final FeedService feedService;

    public CatFeedResource(FeedService feedService) {
        this.feedService = feedService;
    }

    @PostMapping
    public ResponseEntity<FeedRecord> createFeedRecord(@RequestBody FeedRecord record) throws URISyntaxException {
        FeedRecord save = feedService.save(record);
        return ResponseEntity
            .created(new URI("/api/cat/" + save.getId()))
            .body(save);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedRecord> getFeedRecord(@PathVariable("id") String id) {
        return feedService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedRecord> updateFeedRecord(@PathVariable("id") String id, @RequestBody FeedRecord record) {
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(feedService.update(record)));
    }

    @GetMapping
    public ResponseEntity<List<FeedRecord>> getFeedRecordByDate(@RequestParam LocalDate date) {
        List<FeedRecord> allByDate = feedService.findAllByDate(Instant.from(date.atStartOfDay().atOffset(ZoneOffset.UTC)));
        return new ResponseEntity<>(allByDate, HttpStatus.OK);
    }
}
