package com.eccomerce.auth.infraestructure.security;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eccomerce.auth.domain.model.gateway.EncrypterGateway;

@Service

public class EncrypterGatewayImpl implements  EncrypterGateway {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String encrypt(String password) {
        return encoder.encode(password);
    }

    @Override
    public Boolean checkpass(String passUser, String passBD) {
        return encoder.matches(passUser, passBD); //matches compara la contraseña sin encriptar con la encriptada
        //si solo se va a solicitar el correo y la contraseña se debe hacer la consulta por medio del correo, JPA no contiene esa consulta de email,
        //por lo que se debe crear un metodo personalizado en el repositorio en UsuarioDataJPARepository
    }
}

