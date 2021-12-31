package com.kaustubh.vertx.commons.app;

import com.google.inject.Module;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Launcher;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.impl.cpu.CpuCoreSensor;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public abstract class AbstractLauncher extends Launcher {
    public static final Integer NUM_OF_CORES = CpuCoreSensor.availableProcessors();

    protected abstract Module[] getGoogleGuiceModules();

    public AbstractLauncher() {
        super();
    }

    @Override
    public abstract void afterConfigParsed(JsonObject config);

    @Override
    public abstract void beforeStartingVertx(VertxOptions options);

    @Override
    public abstract void afterStartingVertx(Vertx vertx);

    @Override
    public abstract void beforeDeployingVerticle(DeploymentOptions deploymentOptions);

    @Override
    public abstract void beforeStoppingVertx(Vertx vertx);

    @Override
    public abstract void afterStoppingVertx();

    @Override
    public abstract void handleDeployFailed(Vertx vertx, String mainVerticle, DeploymentOptions deploymentOptions, Throwable cause);
}
