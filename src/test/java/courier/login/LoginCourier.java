package courier.login;

import io.qameta.allure.Step;
import ru.practicum.yandex.ObjectGenerator;
import ru.practicum.yandex.network.CourierManager;
import ru.practicum.yandex.courier.Courier;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class LoginCourier {
    CourierManager courierManager = new CourierManager();

    private Courier courier;

    @Before
    public void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        courier = ObjectGenerator.generateCourier();
    }

    @Test
    @DisplayName("Логин курьера")
    @Step("Логин курьера")
    public void loginCourier() {
        courierManager.createCourier(courier.login(), courier.password(), courier.firstName());
        courierManager.login(courier.login(), courier.password()).body("id", notNullValue());
    }

    @After
    public void delete() {
        Integer id = courierManager.login(courier.login(), courier.password()).extract().body().path("id");
        if (id != null) {
            courierManager.delete(id).body("ok", equalTo(true));
        }
    }
}
