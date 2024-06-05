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
    private final RateLimitProperties properties;

    public RateLimiterService(RateLimitProperties properties) {
        this.properties = properties;
    }


    public Bucket resolveBucket(String apiKey) {
        return bucketCache.computeIfAbsent(apiKey, this::newBucket);
    }


    private Bucket newBucket(String s) {
        var refillInterval = Refill.intervally(properties.getRefillPerMinute(),  Duration.of(1, ChronoUnit.MINUTES));
        var limit = Bandwidth.classic(properties.getBandwithCapacity(), refillInterval);
        return Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

}
