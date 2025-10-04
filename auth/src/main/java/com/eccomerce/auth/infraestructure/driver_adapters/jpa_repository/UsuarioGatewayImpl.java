package com.eccomerce.auth.infraestructure.driver_adapters.jpa_repository;

import org.springframework.stereotype.Repository;

import com.eccomerce.auth.domain.model.Usuario;
import com.eccomerce.auth.domain.model.gateway.UsuarioGateway;
import com.eccomerce.auth.infraestructure.mapper.UsuarioMapper;

import lombok.RequiredArgsConstructor;

@Repository //Indica que la clase es un componente de acceso a datos en el contexto de Spring. //en otras palabras, esta clase es responsable de interactuar con la base de datos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) relacionadas con la entidad Usuario.
@RequiredArgsConstructor //Genera un constructor con los campos finales (final) de la clase, facilitando la inyección de dependencias.


public class UsuarioGatewayImpl implements UsuarioGateway {
    private final UsuarioMapper usuarioMapper;
    private final UsuarioDataJpaRepository repository; //Consulta bd

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        UsuarioData usuarioData = usuarioMapper.toData(usuario);
        return usuarioMapper.toUsuario(repository.save(usuarioData));
    }

    @Override
    public void eliminarUsuario(Long id) {
        if (!repository.existsById(id)) { //el ! es para negar la condicion 
            throw new RuntimeException("Error en ID: " + id);}
            repository.deleteById(id);
    } //Override 

    @Override
    public Usuario buscarPorId(Long id) {
        //return usuarioMapper.toUsuario(repository.findById(id).get());
         return repository.findById(id)
                    .map(usuarioData -> usuarioMapper.toUsuario(usuarioData))
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        //Polimorfismo: map, orElseThrow y programacion por flujos
        //map: transforma el valor contenido en el Optional si está presente, aplicando la función proporcionada (en este caso, convertir UsuarioData a Usuario).
        //orElseThrow: si el Optional está vacío (es decir, no se encontró una entidad con el ID dado), lanza una excepción personalizada (en este caso, RuntimeException con el mensaje "Usuario no encontrado").
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        UsuarioData usuarioDataActualizar = usuarioMapper.toData(usuario); //Se esta mapeando el objeto usuarioDataActualizar
        if (!repository.existsById(usuarioDataActualizar.getId())) { //! es para que entre al if cuando no exista el ID
            throw new RuntimeException("Error en ID: " + usuarioDataActualizar.getId() + " no existe");
        }
        return usuarioMapper.toUsuario(repository.save(usuarioDataActualizar));
    } //Implementacion de incriptacion al actualizar usuario   

    @Override
    public String loginUsuario(String email, String password) {
        if (!repository.existsByEmail(email)) {
            throw new RuntimeException("Error en email: " + email + " no existe");
        }
        return "Login exitoso";
    } 
}
//UsuarioGatewayImpl es la implementacion de la interfaz UsuarioGateway

//Polimorfismo de forma resumida es la capacidad de un objeto de una clase derivada (subclase) para ser tratado como un objeto de su clase base (superclase), permitiendo que el mismo método o función se comporte de manera diferente según el tipo del objeto que lo invoca.

//envuelve un objeto en un <optional>, de la Base de datos retorna un ID a Usuario Data
//<optional> posee un objeto, que en este caso es Usuario, en este caso retorna un UsuarioData pero requiere un Usuario
//Hay un metodo de JPA que es para actua