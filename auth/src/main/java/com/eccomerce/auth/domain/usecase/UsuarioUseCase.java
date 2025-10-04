package com.eccomerce.auth.domain.usecase;


import com.eccomerce.auth.domain.model.Usuario;
import com.eccomerce.auth.domain.model.gateway.EncrypterGateway;
import com.eccomerce.auth.domain.model.gateway.UsuarioGateway;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //Crea constructor solo para los final
public class UsuarioUseCase {

    private final UsuarioGateway usuarioGateway;
    private final EncrypterGateway encrypterGateway;

    public Usuario guardarUsuario(Usuario usuario) {
        if(usuario.getNombre() == null && usuario.getPassword() == null) {
            throw new NullPointerException("Nombre del usuario nulo, ojo con eso manito");
        }
        if(usuario.getEdad() < 18){
            System.out.println("Ojo con eso manito!!");
        }

        String passwordEncrypt = encrypterGateway.encrypt(usuario.getPassword());
        usuario.setPassword(passwordEncrypt); //setea la contraseña encriptada al usuario
        return usuarioGateway.guardarUsuario(usuario);
    }

    public void eliminarUsuarioPorId(Long id) {
        try{
            usuarioGateway.eliminarUsuario(id);
        }catch(Exception error){ 
            System.out.println(error.getMessage());
        }
    }

    public Usuario buscarUsuarioPorId(Long id) {
        try{
            return usuarioGateway.buscarPorId(id);
        } catch(Exception error){
            System.out.println(error.getMessage());
            return new Usuario();
        }
    }

    public Usuario actualizarUsuario(Usuario usuario) {
        if(usuario.getId() == null) {
            throw new IllegalArgumentException("El ID es obligatorio");
        }
        if (usuario.getPassword().length() <= 20) {
            String passwordEncrypt = encrypterGateway.encrypt(usuario.getPassword());
            usuario.setPassword(passwordEncrypt);
        }
        return usuarioGateway.actualizarUsuario(usuario);
    }

    public String loginUsuario(String email, String password) {
        if(email == null || password == null) {
            throw new IllegalArgumentException("Email y contraseña son obligatorios");
        }
        return usuarioGateway.loginUsuario(email, password);
    }
}

//Debuguear es una tecnica que permite a los desarrolladores identificar y corregir errores o problemas en el codigo
//Incriptacion es el proceso de convertir informacion o datos en un codigo para evitar el acceso no autorizado
//En el caso de uso no todos los metodos van a necesitar logica de neogicio, pero si es necesario tenerlos en la interfaz del gateway
//En este caso el metodo actualizarUsuario si tiene logica de negocio, ya que se valida si el usuario existe antes de actualizarlo