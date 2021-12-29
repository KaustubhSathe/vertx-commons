package com.kaustubh.vertx.commons.app;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.kaustubh.vertx.commons.utils.ConfigProvider;

import java.io.IOException;

public class AppDeploymentConfigProvider implements Provider<AppDeploymentConfig>{
    @Inject
    private ConfigProvider configProvider;

    @Override
    public AppDeploymentConfig get(){
        try{
            return configProvider.getConfig(getAppDeploymentConfigDir(),getAppDeploymentConfigFileName(), AppDeploymentConfig.class);
        }catch(IOException e){
            throw new RuntimeException("Failed to load application deployment config.");
        }
    }

    private String getAppDeploymentConfigDir(){
        return System.getProperty("com.kaustubh.vertx.commons.app.deployment.config.dir", "config/com.kaustubh.vertx.commons.app");
    }

    private String getAppDeploymentConfigFileName(){
        return System.getProperty("com.kaustubh.vertx.commons.app.deployment.config.filename","com.kaustubh.vertx.commons.app-deployment");
    }
}
