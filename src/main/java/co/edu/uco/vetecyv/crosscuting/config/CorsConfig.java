package co.edu.uco.vetecyv.crosscuting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // Permitir cualquier origen (puedes limitarlo si quieres)
        config.addAllowedOriginPattern("*");

        // Métodos HTTP permitidos
        config.addAllowedMethod("*");

        // Headers permitidos
        config.addAllowedHeader("*");

        // Permitir credenciales (opcional)
        config.setAllowCredentials(true);

        // Registrar configuración para todos los endpoints
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
