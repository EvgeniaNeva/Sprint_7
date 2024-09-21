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

public class LoginCourierTestWithoutParams {
    CourierManager courierManager = new CourierManager();
    private Courier courier;

    @Before
    @Step("Создание курьера для теста")
    public void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        courier = ObjectGenerator.generateCourier();
        sendCreateRequest();
    }

    @Test
    @DisplayName("Авторизация курьера без логина")
    public void loginCourierWithoutLogin() {
        sendLoginRequest("", courier.password());
    }

    @Test
    @DisplayName("Авторизация курьера без пароля")
    public void loginCourierWithoutPassword() {
        sendLoginRequest(courier.login(), "");
    }

    @After
    @Step("Удаление созданного курьера")
    public void delete() {
        Integer id = courierManager.login(courier.login(), courier.password()).extract().body().path("id");
        if (id != null) {
            courierManager.delete(id);
        }
    }

    private void sendCreateRequest() {
        courierManager.createCourier(courier.login(), courier.password(), courier.firstName());
    }

    private void sendLoginRequest(String login, String password) {
        courierManager
                .login(login, password)
                .body("message", equalTo("Недостаточно данных для входа"));
    }
}
