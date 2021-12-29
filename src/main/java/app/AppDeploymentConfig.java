package app;

import lombok.Data;

import java.util.List;

@Data
public class AppDeploymentConfig {
    private List<Deployable> verticleConfigs;
    private List<String> guiceModules;
}
