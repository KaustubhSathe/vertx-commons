package com.kaustubh.vertx.commons.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class VerticleConfig {
    public static final VerticleConfig DEFAULT_CONFIG = new VerticleConfig();

    @JsonProperty
    private int instances;

    @JsonProperty("thread-pool-size")
    private int threadPoolSize;

    // 0 - standard verticle, 1 - worker verticle, 2 - multithreaded worker verticle
    @JsonProperty("verticle-type")
    private int verticleType;
}
