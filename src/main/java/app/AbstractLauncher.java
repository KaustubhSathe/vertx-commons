package app;

import com.google.inject.Module;
import io.vertx.core.Launcher;
import io.vertx.core.Vertx;
import io.vertx.core.impl.cpu.CpuCoreSensor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public abstract class AbstractLauncher extends Launcher {
    public static final Integer NUM_OF_CORES = CpuCoreSensor.availableProcessors();

    protected abstract Deployable[] getVerticlesToDeploy();

    protected abstract Module[] getGoogleGuiceModules();

    private List<Module> getALlGoogleGuiceModules

}
