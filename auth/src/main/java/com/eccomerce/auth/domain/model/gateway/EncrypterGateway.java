package com.eccomerce.auth.domain.model.gateway;

public interface EncrypterGateway {

    String encrypt(String password);
    Boolean checkpass(String passUser, String passBD);
}
