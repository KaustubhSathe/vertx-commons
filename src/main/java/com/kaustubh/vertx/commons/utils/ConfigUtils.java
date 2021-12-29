package com.kaustubh.vertx.commons.utils;

import com.typesafe.config.*;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import javax.annotation.Nonnull;

@Slf4j
public final class ConfigUtils {
    public static ConfigRetriever getConfigRetriever(Vertx vertx, String confFilePathFormat){
        ConfigFactory.invalidateCaches();
        ConfigStoreOptions defaultStore = new ConfigStoreOptions().setConfig(
                new JsonObject().put("path", String.format(confFilePathFormat,"default")));
        ConfigStoreOptions environmentStore = new ConfigStoreOptions().setConfig(new JsonObject()
                .put("path", String.format(confFilePathFormat, getAppEnvironment())));

        return ConfigRetriever.create(vertx, new ConfigRetrieverOptions()
                .addStore(defaultStore)
                .addStore(environmentStore));
    }

    public static Config fromConfigFile(@Nonnull String confFilePathFormat){
        ConfigFactory.invalidateCaches();
        val envFile = String.format(confFilePathFormat, getAppEnvironment());
        val defaultFile = String.format(confFilePathFormat, "default");
        Config config = ConfigFactory.load(envFile)
                .withFallback(ConfigFactory.load(defaultFile, ConfigParseOptions.defaults().setAllowMissing(true), ConfigResolveOptions.defaults()))
                .resolve();

        log.debug("Loading config from file {} : {}", confFilePathFormat, config);

        return config;
    }

    public static <T> T fromConfigFile(@Nonnull String confFilePathFormat, Class<T> clazz){
        Config config = fromConfigFile(confFilePathFormat);
        T typedConfig = ConfigBeanFactory.create(config, clazz);
        log.debug("Loaded config: {}", typedConfig);
        return typedConfig;
    }

    private static String getAppEnvironment(){
        return System.getProperty("com.kaustubh.vertx.commons.app.environment", "dev");
    }
}
