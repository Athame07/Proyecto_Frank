package com.eccomerce.auth.infraestructure.mapper;

import org.springframework.stereotype.Component;

import com.eccomerce.auth.domain.model.Usuario;
import com.eccomerce.auth.infraestructure.driver_adapters.jpa_repository.UsuarioData;

@Component //Clase de configuracion dentro del proyecto, en otras palabras, es una clase que se utiliza para convertir datos entre diferentes representaciones o formatos.
//por ejemplo, convertir un objeto de una entidad de base de datos a un objeto de dominio o viceversa.

public class UsuarioMapper {

    public Usuario toUsuario(UsuarioData usuarioData){  //Pasa de usuarioData a usuario
        return new Usuario(
                usuarioData.getId(),
                usuarioData.getNombre(),
                usuarioData.getEmail(),
                usuarioData.getPassword(),
                usuarioData.getRole(),
                usuarioData.getEdad()
        );
    }

    public UsuarioData toData(Usuario usuario){ //Pasa de usuario a usuarioData
        return new UsuarioData(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getRole(),
                usuario.getEdad()
        );
    }

}
