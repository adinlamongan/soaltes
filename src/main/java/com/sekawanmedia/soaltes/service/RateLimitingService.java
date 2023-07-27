package com.sekawanmedia.soaltes.service;

import io.github.bucket4j.Bucket;

public interface RateLimitingService {

    Bucket resolveBucket(int userId);
    void deleteIfExists(int userId);


}
