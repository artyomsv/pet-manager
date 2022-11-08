package com.stukans.pm.domain;

import java.time.Instant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * An authority (a security role) used by Spring Security.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "feed_record")
public class FeedRecord extends AbstractAuditingEntity<String> {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private Instant date;
    private String person;
    private String food;
    private int foodGiven;
    private int foodEaten;

    @Version
    private int version;
}
