// File: src/main/java/com/danlearnings/bookcatalog/api/config/WebConfig.java

package com.danlearnings.bookcatalog.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Indica ao Spring que esta é uma classe de configuração
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Aplica a configuração a todos os endpoints que começam com /api/
                .allowedOrigins("http://localhost:4200") // Permite requisições vindas do nosso frontend Angular
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite estes métodos HTTP
                .allowedHeaders("*") // Permite todos os cabeçalhos
                .allowCredentials(false); // Não precisamos de credenciais (cookies) por enquanto
    }
}