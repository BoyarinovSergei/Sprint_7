/*
 * 1. Создание курьера
 * Включает проверки:
 * "курьера можно создать;",
 * "запрос возвращает правильный код ответа;",
 * "успешный запрос возвращает ok: true;"
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

public class TestCourierCreationPositive {

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
    @Description("Создание одного курьера с корректными данными и проверка кода ответа на корректность и тела ответа")
    public void successfulCourierCreation() {
        makePostRequest("/api/v1/courier",
                new ReqCourierCreation(logIn, password, firstName), null)
                .then()
                .statusCode(201)
                .assertThat()
                .body("ok", equalTo(true));
    }

    @After
    @Description("Удаление созданного курьера")
    public void deleteCourier() {
        deleteUser(logIn, password);
    }
}
