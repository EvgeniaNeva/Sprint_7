package courier.create;

import ru.practicum.yandex.constants.Constants;
import ru.practicum.yandex.ObjectGenerator;
import ru.practicum.yandex.network.CourierManager;
import ru.practicum.yandex.courier.Courier;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class CreateCourierTestWithoutParams {
    private final CourierManager courierManager = new CourierManager();
    private Courier courier;

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.DOMEN_URL;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    @DisplayName("Создание курьера без логина и пароля")
    public void createCourierEmpty() {
        courier = ObjectGenerator.generateCourierWithoutAll();
        sendRequest();
    }

    @Test
    @DisplayName("Создание курьера без логина")
    public void createCourierWithoutLogin() {
        courier = ObjectGenerator.generateCourierWithoutLogin();
        sendRequest();
    }

    @Test
    @DisplayName("Создание курьера без пароля")
    public void createCourierWithoutPassword() {
        courier = ObjectGenerator.generateCourierWithoutPassword();
        sendRequest();
    }

    private void sendRequest() {
        String textError = "Недостаточно данных для создания учетной записи";
        courierManager
                .createCourier(courier.login(), courier.password(), courier.firstName())
                .body("message", equalTo(textError))
                .and()
                .statusCode(400);
    }
}
