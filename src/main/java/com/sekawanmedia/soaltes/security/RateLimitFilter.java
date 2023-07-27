package com.sekawanmedia.soaltes.security;

import com.sekawanmedia.soaltes.service.RateLimitingService;
import com.sekawanmedia.soaltes.utils.UserAktif;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RateLimitFilter extends OncePerRequestFilter {

    @Autowired
    private UserAktif userAktif;
    @Autowired
    private RateLimitingService rateLimitingService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            final Bucket tokenBucket = rateLimitingService.resolveBucket(userAktif.getUserId());
            final ConsumptionProbe probe = tokenBucket.tryConsumeAndReturnRemaining(1);

            if (!probe.isConsumed()) {
                response.sendError(HttpStatus.TOO_MANY_REQUESTS.value(),
                        "Request limit linked to your current plan has been exhausted");
            }
        }
        filterChain.doFilter(request, response);

    }
}
