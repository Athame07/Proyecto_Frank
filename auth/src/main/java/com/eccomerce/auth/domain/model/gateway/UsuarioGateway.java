package com.eccomerce.auth.domain.model.gateway;

//Importa la clase Optional de la biblioteca Apache EL (Expression Language). Sin embargo, en este contexto, parece que debería importarse java.util.Optional en lugar de org.apache.el.stream.Optional.

import com.eccomerce.auth.domain.model.Usuario;

public interface UsuarioGateway {

    Usuario guardarUsuario(Usuario usuario);
    void eliminarUsuario(Long id);
    Usuario buscarPorId(Long id);
    Usuario actualizarUsuario(Usuario usuario); //Objeto Usuario retornara el usuario)
    String loginUsuario(String email, String password);

}
