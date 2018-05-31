package javatraining.training.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * Created by Adela Vasilache on 31.05.2018
 */
@Data
@Configuration
@PropertySource("classpath:templates.properties")
public class TemplatePath {
    @Autowired
    Environment env;
    @Autowired
    private ThymeleafProperties properties;

    @Value("${spring.thymeleaf.templates_root}")
    private String templatesRoot;

    @Bean
    public ITemplateResolver defaultTemplateResolver() {
        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setSuffix(properties.getSuffix());
        resolver.setPrefix(templatesRoot);
        resolver.setTemplateMode(properties.getMode());
        resolver.setCacheable(properties.isCache());

        return resolver;
    }

    public String getTemplate(String fieldName) {
        return env.getProperty(fieldName);
    }
}

