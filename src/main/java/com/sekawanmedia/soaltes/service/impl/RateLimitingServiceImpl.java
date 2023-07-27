package com.sekawanmedia.soaltes.service.impl;

import com.sekawanmedia.soaltes.exception.ResourceNotFoundException;
import com.sekawanmedia.soaltes.model.UserPlanQuery;
import com.sekawanmedia.soaltes.repository.UserPlanRepo;
import com.sekawanmedia.soaltes.service.RateLimitingService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@AllArgsConstructor
public class RateLimitingServiceImpl implements RateLimitingService {

    private UserPlanRepo userPlanRepo;
    private final Map<Integer, Bucket> bucketCache = new ConcurrentHashMap<>();


    @Override
    public Bucket resolveBucket(int userId) {
        return bucketCache.computeIfAbsent(userId, this::newBucket);
    }

    @Override
    public void deleteIfExists(int userId) {

    }

    private Bucket newBucket(int userId) {
        UserPlanQuery  userPlanQuery = userPlanRepo.findUserPlanByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("user plan not found"));

        Integer limitPerHour = userPlanQuery.getLimitPerHour();

        return Bucket4j.builder()
                .addLimit(Bandwidth.classic(limitPerHour, Refill.intervally(limitPerHour, Duration.ofHours(1))))
                .build();
    }
}
