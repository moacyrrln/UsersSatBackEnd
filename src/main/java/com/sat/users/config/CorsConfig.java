// E:\dev\SAT/users_back_end\src\main\java\com\sat/users\config\CorsConfig.java
package com.sat.users.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("http://localhost:4200")
        .allowedMethods("*") // Permitir todos os métodos para teste
        .allowedHeaders("*")
        .allowCredentials(true);
  }
}
