package com.github.projetoifsc.estagios.app.security.ratelimit;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//@Component
//@ConfigurationProperties("rate-limit")
public class RateLimitProperties {

//    @Value()
    private long bandwithCapacity;
    private long refillPerMinute;

    public long getBandwithCapacity() {
        return bandwithCapacity;
    }

    public void setBandwithCapacity(long bandwithCapacity) {
        this.bandwithCapacity = bandwithCapacity;
    }

    public long getRefillPerMinute() {
        return refillPerMinute;
    }

    public void setRefillPerMinute(long refillPerMinute) {
        this.refillPerMinute = refillPerMinute;
    }
}
