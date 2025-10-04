package com.eccomerce.auth.infraestructure.entry_points;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eccomerce.auth.domain.model.Usuario;
import com.eccomerce.auth.domain.usecase.UsuarioUseCase;
import com.eccomerce.auth.infraestructure.driver_adapters.jpa_repository.UsuarioData;
import com.eccomerce.auth.infraestructure.mapper.UsuarioMapper;

import lombok.RequiredArgsConstructor;

@RestController //Indica a Spring que es un controlador, osea que esta clase manejará las solicitudes HTTP entrantes y enviará respuestas HTTP al cliente.
@RequestMapping("/api/ecommerce/usuario")//Indicar ruta //en donde se consume la API de forma local
@RequiredArgsConstructor //Crear Constructor 

public class UsuarioController{

    private final UsuarioMapper usuarioMapper;
    private final UsuarioUseCase usuarioUseCase;

    @PostMapping("/save") //Modificar-Guardar //PostMapping es una anotación en Spring que se utiliza para mapear solicitudes HTTP POST a métodos específicos en un controlador.
    public ResponseEntity<Usuario> saveUsuario(@RequestBody UsuarioData usuarioData){ //Request Body captura de afuera Json a Data por el Mapper
        Usuario usuarioConvertido = usuarioMapper.toUsuario(usuarioData);
        Usuario usuario = usuarioUseCase.guardarUsuario(usuarioConvertido);

        if(usuario.getId() != null){
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }

        return new ResponseEntity<>(usuario, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findByIdUsuario(@PathVariable Long id){
        Usuario usuario = usuarioUseCase.buscarUsuarioPorId(id);
        if(usuario.getId() != null){ //se hace esta validacion para evitar errores
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }
        return new ResponseEntity<>(usuario, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id){
        usuarioUseCase.eliminarUsuarioPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //NO_CONTENT es un código de estado HTTP que indica que la solicitud se ha procesado correctamente, pero no hay contenido que devolver en la respuesta.
    }

    @PutMapping("/update")
    public ResponseEntity<Usuario> actualizarUsuario(@RequestBody UsuarioData usuarioData){ 
        try{
            Usuario usuario = usuarioMapper.toUsuario(usuarioData);
            Usuario usuarioValidadoActualizado = usuarioUseCase.actualizarUsuario(usuario);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }catch (Exception error){
            return  ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> loginUsuario(@RequestBody String email, String password){
        try{
            String resultado = usuarioUseCase.loginUsuario(email, password);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }catch(Exception error){
            return ResponseEntity.notFound().build();
    }
    
    }

    //@RequestBody convierte el Json a UsuarioData
    //Se debe obtener más metadatos, en este caso es HTTP Status
    //dentro de UsuarioData se necesitan metadatos adicionales, donde devuelva un response entity 
    //body es el objeto que se envía en la respuesta HTTP
}
//se debe devolver un response entity, ya que se necesita más metadatos
//ResponseEntity es una clase en Spring que representa toda la respuesta HTTP, incluyendo el cuerpo, los encabezados y el estado.
//El request body es un objeto que contiene los datos enviados por el cliente en una solicitud HTTP, generalmente en formato JSON o XML, y se utiliza para enviar información al servidor para su procesamiento.