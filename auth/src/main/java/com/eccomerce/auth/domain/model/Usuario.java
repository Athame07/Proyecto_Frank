package com.eccomerce.auth.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor

public class Usuario {

    private Long id;
    private String nombre;
    private String email;
    private String password;
    private String role;
    private Integer edad;

}
