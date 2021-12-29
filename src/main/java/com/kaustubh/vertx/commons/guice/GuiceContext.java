package com.kaustubh.vertx.commons.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class GuiceContext {
    private static volatile GuiceContext instance = null;

    private Injector injector;

    private GuiceContext(Module... modules){
        injector = Guice.createInjector(modules);
    }

    public static synchronized void initialize(Module... modules){
        if(instance != null){
            throw new RuntimeException("Application context is already initialized.");
        }else{
            instance = new GuiceContext(modules);
        }
    }

    public static synchronized void reset(){
        instance = null;
    }

    private static GuiceContext instance(){
        if(instance != null){
            return instance;
        }
        throw new RuntimeException("Application context not initialized");
    }

    public static <T> T getInstance(Class<T> clazz){
        return instance().injector.getInstance(clazz);
    }
}
