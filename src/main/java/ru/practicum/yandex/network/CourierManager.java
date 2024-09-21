package ru.practicum.yandex.network;

import ru.practicum.yandex.constants.Constants;
import ru.practicum.yandex.courier.Courier;
import io.restassured.response.ValidatableResponse;

public class CourierManager extends NetworkManager {
    public ValidatableResponse createCourier(String login, String password, String firstName) {
        Courier courier = new Courier();
        courier.setLogin(login);
        courier.setPassword(password);
        courier.setFirstName(firstName);
        return request()
                .body(courier)
                .when()
                .post(Constants.ADD_COURIER_URL)
                .then();
    }

    public ValidatableResponse login(String login, String password) {
        Courier courier = new Courier();
        courier.setLogin(login);
        courier.setPassword(password);
        return request()
                .body(courier)
                .when()
                .post(Constants.LOGIN_COURIER_URL)
                .then();
    }

    public ValidatableResponse delete(int id) {
        return request()
                .pathParam("id", id)
                .when()
                .delete(Constants.DELETE_COURIER_URL + "{id}")
                .then();
    }
}
