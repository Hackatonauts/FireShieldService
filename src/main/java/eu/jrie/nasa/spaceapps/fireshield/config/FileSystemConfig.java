package eu.jrie.nasa.spaceapps.fireshield.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileSystemConfig implements WebMvcConfigurer {

    public static final String IMG_URL = "report/img";
    public static final String IMG_PATH = "C:\\Users\\jakub\\Dev\\nasa_space_apps\\img";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler(IMG_URL + "/**")
                .addResourceLocations("file:" + IMG_PATH)
                .setCachePeriod(36000);
    }
}