package courier.create;

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

public class CreateExistCourierTest {
    CourierManager courierManager = new CourierManager();
    private Courier courier;

    @Before
    public void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        courier = ObjectGenerator.generateCourier();
        courierManager.createCourier(courier.login(), courier.password(), courier.firstName());
    }

    @Test
    @DisplayName("Создание существующего курьера")
    @Step("Создание существующего курьера")
    public void createExistCourier() {
        courierManager
                .createCourier(courier.login(), courier.password(), courier.firstName())
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(409);
    }

    @After
    public void delete() {
        Integer id = courierManager.login(courier.login(), courier.password()).extract().body().path("id");
        if (id != null) {
            courierManager.delete(id).body("ok", equalTo(true));
        }
    }
}
