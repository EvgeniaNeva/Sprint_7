package ru.practicum.yandex.network;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import ru.practicum.yandex.constants.Constants;

import static io.restassured.RestAssured.given;

public class NetworkManager {
    public RequestSpecification request() {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(Constants.DOMEN_URL);
    }
}
