package com.kaustubh.vertx.commons.guice;

import com.google.inject.AbstractModule;


public abstract class VertxAbstractModule extends AbstractModule {
    @Override
    protected void configure(){
        bindConfiguration();
    }

    protected abstract void bindConfiguration();
}
