package ru.practicum.yandex.courier;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Courier {
    private String login;
    private String password;
    private String firstName;

    public void withParams(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public void withOutLogin(String password, String firstName) {
        this.password = password;
        this.firstName = firstName;
    }

    public void withOutPassword(String login, String firstName) {
        this.login = login;
        this.firstName = firstName;
    }

    public void withOutFirstName(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String login() {
        return this.login;
    }

    public String password() {
        return this.password;
    }

    public String firstName() {
        return this.firstName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
