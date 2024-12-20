package com.adriansanz.notasBackend.configuracion;

import com.adriansanz.notasBackend.seguridad.TokenFiltro;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracionSeguridad {

    @Bean
    public FilterRegistrationBean<TokenFiltro> registrarTokenFiltro(TokenFiltro tokenFiltro) {
        FilterRegistrationBean<TokenFiltro> registro = new FilterRegistrationBean<>();
        registro.setFilter(tokenFiltro);
        registro.addUrlPatterns("/api/*");
        return registro;
    }
}