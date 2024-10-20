package ru.practicum.yandex.network;

import ru.practicum.yandex.constants.Constants;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import ru.practicum.yandex.order.Order;

public class OrderManager extends NetworkManager {
    public ValidatableResponse create(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        Order order = new Order();
        order.setFirstName(firstName);
        order.setLastName(lastName);
        order.setAddress(address);
        order.setMetroStation(metroStation);
        order.setPhone(phone);
        order.setRentTime(rentTime);
        order.setDeliveryDate(deliveryDate);
        order.setComment(comment);
        order.setColor(color);
        return request()
                .body(order)
                .when()
                .post(Constants.CREATE_ORDER_URL)
                .then();
    }

    public void cancel(int track) {
        request()
                .pathParam("track", track)
                .when()
                .put(Constants.CANCEL_ORDER_URL + "?track=" + "{track}")
                .then();
    }

    public Response getOrders() {
        return request().get(Constants.GET_ORDERS_URL);
    }
}
