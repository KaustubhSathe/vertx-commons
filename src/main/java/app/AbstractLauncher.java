package app;

import io.vertx.core.Launcher;
import io.vertx.core.Vertx;
import io.vertx.core.impl.cpu.CpuCoreSensor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractLauncher extends Launcher {
    public static final Integer NUM_OF_CORES = CpuCoreSensor.availableProcessors();

    @Getter
    protected Vertx vertx;


}
