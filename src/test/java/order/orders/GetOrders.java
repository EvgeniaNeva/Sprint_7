package order.orders;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import ru.practicum.yandex.network.OrderManager;
import ru.practicum.yandex.order.OrderResponse;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.emptyArray;

public class GetOrders {
    private final OrderManager orderManager = new OrderManager();

    @Before
    public void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    @DisplayName("Получение списка заказов")
    public void getOrders() {
        OrderResponse orderResponse = orderManager.getOrders().as(OrderResponse.class);
        MatcherAssert.assertThat(orderResponse.getArrayOrders(), not(emptyArray()));
    }
}
