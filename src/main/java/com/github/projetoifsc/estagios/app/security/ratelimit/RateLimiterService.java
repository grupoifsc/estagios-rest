package com.github.projetoifsc.estagios.app.security.ratelimit;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {

    private final Map<String, Bucket> bucketCache = new ConcurrentHashMap<>();

    public Bucket resolveBucket(String apiKey) {
        return bucketCache.computeIfAbsent(apiKey, this::newBucket);
    }

    private Bucket newBucket(String s) {
        var refillInterval = Refill.intervally(10,  Duration.of(1, ChronoUnit.MINUTES));
        var limit = Bandwidth.classic(10, refillInterval);
        return Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

}
