package com.github.projetoifsc.estagios.app.security.ratelimit;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private final RateLimiterService rateLimiterService;

    public RateLimitFilter(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var requestURI = request.getRequestURI();
        if (requestURI.startsWith("/api/")) {
            var userIP = request.getRemoteAddr();
            Bucket bucket = rateLimiterService.resolveBucket(userIP);
            ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
            if (!probe.isConsumed()) {
                String waitForRefill = String.valueOf(probe.getNanosToWaitForRefill() / 1_000_000_000);
                throw new RateLimitException("Exceeded rate limit of requests. Try again in " + waitForRefill + " seconds.");
            }
            filterChain.doFilter(request, response);
        }
    }


}
