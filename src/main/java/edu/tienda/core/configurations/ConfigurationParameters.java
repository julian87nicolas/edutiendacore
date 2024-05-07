package edu.tienda.core.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "app")
public class ConfigurationParameters {
    private String name;
    private String country;
    private String author;
    private String language;

    @Override
    public String toString() {
        return "ConfigurationParameters{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", author='" + author + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
