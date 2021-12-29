package app;

import com.google.inject.Inject;
import com.google.inject.Provider;
import utils.ConfigProvider;

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
        return System.getProperty("app.deployment.config.dir", "config/app");
    }

    private String getAppDeploymentConfigFileName(){
        return System.getProperty("app.deployment.config.filename","app-deployment");
    }
}
