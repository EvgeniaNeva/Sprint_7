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

public class CreateCourierTest {
    CourierManager courierManager = new CourierManager();

    private Courier courier;

    @Before
    public void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    @DisplayName("Создание курьера")
    @Step("Создание курьера")
    public void createCourierWithAllParas() {
        courier = ObjectGenerator.generateCourier();
        sendRequest();
    }

    @Test
    @DisplayName("Создание курьера с обязательными параметрами")
    @Step("Создание курьера с обязательными параметрами")
    public void createCourierWithRequiredParams() {
        courier = ObjectGenerator.generateCourierWithoutFirstName();
        sendRequest();
    }

    @After
    public void delete() {
        Integer id = courierManager.login(courier.login(), courier.password()).extract().body().path("id");
        if (id != null) {
            courierManager.delete(id).body("ok", equalTo(true));
        }
    }

    private void sendRequest() {
        courierManager
                .createCourier(courier.login(), courier.password(), courier.firstName())
                .statusCode(201)
                .assertThat().body("ok", equalTo(true));
    }
}
