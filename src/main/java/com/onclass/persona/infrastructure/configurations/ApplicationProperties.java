package com.onclass.persona.infrastructure.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {
    private Security security = new Security();

    @Data
    public static class Security {
        private boolean enabled = true;
    }

    // Propiedades específicas del microservicio Person
}
