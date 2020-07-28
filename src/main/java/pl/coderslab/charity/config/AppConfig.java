package pl.coderslab.charity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import pl.coderslab.charity.converter.CategoryConverter;
import pl.coderslab.charity.converter.InstitutionConverter;

@Configuration
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver =
                new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addConverter(getInstitutionConverter());
        registry.addConverter(getCategoryConverter());

    }

    @Bean
    public InstitutionConverter getInstitutionConverter(){
        return new InstitutionConverter();
    }

    @Bean
    public CategoryConverter getCategoryConverter(){
        return new CategoryConverter();
    }


}
