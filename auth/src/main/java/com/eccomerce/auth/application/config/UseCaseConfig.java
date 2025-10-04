package com.eccomerce.auth.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.eccomerce.auth.domain.model.gateway.EncrypterGateway;
import com.eccomerce.auth.domain.model.gateway.UsuarioGateway;
import com.eccomerce.auth.domain.usecase.UsuarioUseCase;

@Configuration
public class UseCaseConfig {
    @Bean //Indica que un método produce un bean que debe ser gestionado por el contenedor Spring.
    //En otras palabras, esta anotación se utiliza para definir un método que crea y configura
    //uN Bean es un objeto que es instanciado, ensamblado y gestionado por un contenedor de Inversión de Control (IoC) en el contexto de un framework como Spring.
    public UsuarioUseCase usuarioUseCase(UsuarioGateway usuarioGateway, EncrypterGateway encrypterGateway){ {
        return new UsuarioUseCase(usuarioGateway, encrypterGateway);
    }
    }
}