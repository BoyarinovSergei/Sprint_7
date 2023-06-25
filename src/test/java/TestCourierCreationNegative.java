/*
* 1. Создание курьера
* Включает проверки:
* "нельзя создать двух одинаковых курьеров",
* "запрос возвращает правильный код ответа",
* "если создать пользователя с логином, который уже есть, возвращается ошибка."
* */

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pojo.courierCreation.request.ReqCourierCreation;

import static dataForTests.URLs.url;
import static helper.StringGenerator.generateString;
import static org.hamcrest.CoreMatchers.equalTo;
import static requestSamples.RequestSamples.deleteUser;
import static requestSamples.RequestSamples.makePostRequest;

public class TestCourierCreationNegative {
    private String logIn;
    private String password;
    private String firstName;

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = url.get("Main host");
    }

    @Before
    @Description("Генерация данных для создания курьера")
    public void generateData() {
        logIn = generateString(10);
        password = generateString(10);
        firstName = generateString(10);
    }

    @Test
    @Description("Создание двух идентичных курьеров подряд и проверка на то, что второй запрос получил в ответ предупреждение")
    public void creatingTwoIdenticalCouriers() {
        makePostRequest("/api/v1/courier", new ReqCourierCreation(logIn, password, firstName), null);

        makePostRequest("/api/v1/courier", new ReqCourierCreation(logIn, password, firstName), null)
                .then()
                .statusCode(409)
                .assertThat()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @After
    @Description("Удаление созданного курьера")
    public void deleteCourier() {
        deleteUser(logIn, password);
    }
}
