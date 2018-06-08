package javatraining.training.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.springframework.web.servlet.resource.WebJarsResourceResolver;


/**
 * Created by Adela Vasilache on 31.05.2018
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
    @Value("${build.version}")
    private String buildVersion;

    @Value("${build.timestamp}")
    private String buildTimestamp;

    @Autowired
    MessageSource messageSource;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String version = buildVersion + buildTimestamp;
        VersionResourceResolver versionResourceResolver = new VersionResourceResolver().addFixedVersionStrategy(version, "/**/*.js")
                .addContentVersionStrategy(version, "/static/**/*.css", "/static/img/**");
        registry.addResourceHandler("/static/**").addResourceLocations("/resources/", "/webjars/", "classpath:/static/").setCachePeriod(60 * 60 * 24)
                .resourceChain(true).addResolver(versionResourceResolver).addResolver(new WebJarsResourceResolver());
    }
}