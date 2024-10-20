package ru.practicum.yandex;

import org.apache.commons.lang3.RandomStringUtils;
import ru.practicum.yandex.courier.Courier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class ObjectGenerator {
    @org.jetbrains.annotations.NotNull
    public static Courier generateCourier() {
        final Courier courier = new Courier();
        courier.withParams(RandomStringUtils.randomAlphanumeric(5), RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphanumeric(5));
        return courier;
    }

    public static @NotNull Courier generateCourierWithoutPassword() {
        final Courier courier = new Courier();
        courier.withOutPassword(RandomStringUtils.randomAlphanumeric(5), RandomStringUtils.randomAlphanumeric(5));
        return courier;
    }

    public static @NotNull Courier generateCourierWithoutLogin() {
        final Courier courier = new Courier();
        courier.withOutLogin(RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphanumeric(5));
        return courier;
    }

    public static @NotNull Courier generateCourierWithoutFirstName() {
        final Courier courier = new Courier();
        courier.withOutFirstName(RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphanumeric(5));
        return courier;
    }

    @Contract(value = " -> new", pure = true)
    public static @NotNull Courier generateCourierWithoutAll() {
        return new Courier();
    }
}
