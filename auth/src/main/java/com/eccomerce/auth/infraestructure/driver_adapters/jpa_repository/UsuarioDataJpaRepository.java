package com.eccomerce.auth.infraestructure.driver_adapters.jpa_repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDataJpaRepository extends JpaRepository<UsuarioData, Long> { 
        Optional<UsuarioData> findByEmail(String email);
        boolean existsByEmail(String email);
        

    
    
    //Long por el gateway //quiere decir que este repositorio maneja entidades de tipo UsuarioData con claves primarias de tipo Long.
    //JpaRepository proporciona métodos CRUD (Crear, Leer, Actualizar, Eliminar) y otros métodos útiles para interactuar con la base de datos.
    //JpaRepository es una interfaz genérica que extiende PagingAndSortingRepository y QueryByExampleExecutor, proporcionando funcionalidades adicionales para la paginación, ordenación y consultas basadas en ejemplos.

}

//inyeccion (@Autowired) de dependencias (UsuarioDataJpaRepository) en UsuarioGatewayImpl
//Inyección de dependencias es un patrón de diseño que permite a un objeto recibir sus dependencias de fuentes externas en lugar de crearlas por sí mismo, promoviendo la modularidad y facilitando las pruebas unitarias.
//En otras palabras, es una técnica para lograr la inversión de control (IoC) al proporcionar los objetos necesarios a una clase desde el exterior, generalmente a través de un contenedor de IoC o un framework de inyección de dependencias.