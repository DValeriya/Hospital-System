package com.teama.hospitalsystem.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

public class LoginRequest {

    @NotNull
    private BigInteger login;
    @NotBlank
    private String password;

    public BigInteger getLogin() {
        return login;
    }

    public void setLogin(BigInteger login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
