package RestProductos.configuracion;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MiConfiguracion {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    // cutre
/*    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer(){
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //WebMvcConfigurer.super.addCorsMappings(registry);
                registry.addMapping("/**");
            }
        };
    }*/

    // Más limpio
    @Bean
    public WebMvcConfigurer corsConfigurer2() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/productoDTO/**")
                        .allowedOrigins("http://localhost:9998")
                        .allowedMethods("GET")
                        .maxAge(3600);
            }
        };
    }


}
