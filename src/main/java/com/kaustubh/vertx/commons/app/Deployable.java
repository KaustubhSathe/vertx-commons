package com.kaustubh.vertx.commons.app;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kaustubh.vertx.commons.guice.GuiceContext;
import io.vertx.core.AbstractVerticle;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Deployable {
    private AbstractVerticle verticle;
    private VerticleConfig config = VerticleConfig.DEFAULT_CONFIG;
    private Class<? extends AbstractVerticle> verticleClass;
    private String name;

    public Deployable(Class<? extends AbstractVerticle> verticleClass) {
        this.verticleClass = verticleClass;
    }

    public Deployable(VerticleConfig config, Class<? extends AbstractVerticle> verticleClass) {
        this.config = config;
        this.verticleClass = verticleClass;
    }

    @JsonCreator
    public Deployable(@JsonProperty("verticleConfig") VerticleConfig config,
                      @JsonProperty("verticleClass") String verticleClass,
                      @JsonProperty("verticleName") String name) throws ClassNotFoundException {
        this.config = config;
        Class clazz = Class.forName(verticleClass);
        if (clazz.isAssignableFrom(AbstractVerticle.class)) {
            this.verticleClass = clazz;
        } else {
            log.error("Invalid Verticle Class Name: {}, Not extending io.vertx.core.AbstractVerticle.class", verticleClass);
            throw new RuntimeException("Invalid Verticle Class Name: " + verticleClass + ", Not extending io.vertx.core.AbstractVerticle.class");
        }
        this.name = name;
    }

    public AbstractVerticle getVerticle(){
        return verticle = GuiceContext.getInstance(verticleClass);
    }
}
