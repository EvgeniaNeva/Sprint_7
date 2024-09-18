package order.create;

import io.qameta.allure.junit4.DisplayName;
import ru.practicum.yandex.constants.Constants;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import ru.practicum.yandex.network.OrderManager;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrder {
    private final OrderManager orderManager = new OrderManager();
    private int track;

    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String[] color;

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.DOMEN_URL;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    public CreateOrder(String firstName, String lastName, String address, String metroStation, String phone,
                       Integer rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters(name = "Test №{index} - Color = {7}")
    public static Object[][] getData() {
        return new Object[][]{
                {"Иван", "Иванов", "Яенево", "Домодедовская", "79111111111", 1, "2024-09-20", "Черный", new String[]{"BLACK"}},
                {"Дмитрий", "Дмитриев", "Балашиха", "Красногвардейская", "79222222222", 2, "2024-09-21", "Серый", new String[]{"GREY"}},
                {"Сергей", "Сергеев", "Бутово", "Царицыно", "79333333333", 3, "2024-09-22", "Черный и Серый", new String[]{"BLACK", "GREY"}},
                {"Василий", "Васильев", "Одинцово", "Академическая", "79444444444", 4, "2024-09-23", "не указан", new String[]{}},
                {"Артем", "Артемов", "Котельники", "Театральная", "79555555555", 5, "2024-09-24", null, null}
        };
    }

    @Test
    @DisplayName("Создание заказа")
    public void createOrder() {
        track = orderManager.create(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color).extract().body().path("track");
        MatcherAssert.assertThat(track, notNullValue());
    }

    @After
    public void cancelOrder() {
        orderManager.cancel(track);
    }
}
