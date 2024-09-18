package courier.login;

import ru.practicum.yandex.constants.Constants;
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

public class LoginCourierTestWithWrongParams {
    CourierManager courierManager = new CourierManager();
    private Courier courier;

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.DOMEN_URL;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        courier = ObjectGenerator.generateCourier();
    }

    @Test
    @DisplayName("Авторизация несуществующего курьера")
    public void loginCourierWithNotExistParams() {
        sendLoginRequest(courier.login(), courier.password());
    }

    @Test
    @DisplayName("Авторизация курьера с неверным паролем")
    public void loginCourierWithWrongPassword() {
        sendCreateRequest();
        sendLoginRequest(courier.login(), "password123456789");
    }

    @Test
    @DisplayName("Авторизация курьера с неверным логином")
    public void loginCourierWithWrongParams() {
        sendCreateRequest();
        sendLoginRequest("login123456789", "password123456789");
    }

    @After
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
                .body("message", equalTo("Учетная запись не найдена"));
    }
}
