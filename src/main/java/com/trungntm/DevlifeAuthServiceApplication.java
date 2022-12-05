package com.trungntm;

import com.trungntm.app.provisioning.keycloak.KeycloakInitializerConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigurationProperties({KeycloakInitializerConfigurationProperties.class})
public class DevlifeAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevlifeAuthServiceApplication.class, args);
    }

}
