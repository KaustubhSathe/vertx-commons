package com.kaustubh.vertx.commons.guice;

import com.google.inject.AbstractModule;
import io.vertx.core.Vertx;

public abstract class VertxAbstractModule extends AbstractModule {
    public VertxAbstractModule(Vertx vertx){}

    @Override
    protected void configure(){
        bindConfiguration();
    }

    protected abstract void bindConfiguration();
}
