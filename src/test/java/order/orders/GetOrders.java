package order.orders;

import ru.practicum.yandex.constants.Constants;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import ru.practicum.yandex.network.OrderManager;
import ru.practicum.yandex.order.OrderResponse;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrders {
    private final OrderManager orderManager = new OrderManager();

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.DOMEN_URL;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    @DisplayName("Получение списка заказов")
    public void getOrders() {
        OrderResponse orderResponse = orderManager.getOrders().as(OrderResponse.class);
        MatcherAssert.assertThat(orderResponse, notNullValue());
    }
}
