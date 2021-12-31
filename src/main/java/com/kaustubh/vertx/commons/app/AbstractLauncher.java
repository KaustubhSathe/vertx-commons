package com.kaustubh.vertx.commons.app;

import com.google.inject.Module;
import com.kaustubh.vertx.commons.guice.DefaultModule;
import com.kaustubh.vertx.commons.guice.GuiceContext;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Launcher;
import io.vertx.core.VertxOptions;
import io.vertx.core.impl.cpu.CpuCoreSensor;
import io.vertx.core.json.JsonObject;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public abstract class AbstractLauncher extends Launcher {
    public static final Integer NUM_OF_CORES = CpuCoreSensor.availableProcessors();

//    protected abstract Module[] getGoogleGuiceModules(Vertx vertx);

    private List<Module> getAllGoogleGuiceModules(Vertx vertx){
        List<Module> modules = new ArrayList<>();
        modules.add(new DefaultModule(vertx));
//        modules.addAll(Arrays.asList(getGoogleGuiceModules(vertx)));
        return modules;
    }

    public AbstractLauncher() {
        super();
    }

    @Override
    public void afterConfigParsed(JsonObject config) {
    }

    @Override
    public void beforeStartingVertx(VertxOptions options) {
    }

    @Override
    public void afterStartingVertx(Vertx vertx) {
        GuiceContext.initialize(getAllGoogleGuiceModules(vertx));
    }

    @Override
    public void beforeDeployingVerticle(DeploymentOptions deploymentOptions) {
        deploymentOptions.setInstances(NUM_OF_CORES);
    }

    @Override
    public void beforeStoppingVertx(Vertx vertx) {
    }

    @Override
    public void afterStoppingVertx() {
    }



    @Override
    public void handleDeployFailed(Vertx vertx, String mainVerticle, DeploymentOptions deploymentOptions, Throwable cause) {
    }
}
