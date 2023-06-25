/*
 * 2. Логин курьера
 * Включает проверки:
 * "курьер может авторизоваться;",
 * "успешный запрос возвращает id.",
 * */

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pojo.courierCreation.request.ReqCourierCreation;
import pojo.courierLogIn.request.ReqCourierLogIn;

import static dataForTests.URLs.url;
import static helper.StringGenerator.generateString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static requestSamples.RequestSamples.deleteUser;
import static requestSamples.RequestSamples.makePostRequest;

public class TestLogInPositive {
    private String logIn;
    private String password;
    private String firstName;

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = url.get("Main host");
    }

    @Before
    @Description("Генерация данных и создание курьера")
    public void generateData() {
        logIn = generateString(10);
        password = generateString(10);
        firstName = generateString(10);

        makePostRequest("/api/v1/courier",
                new ReqCourierCreation(logIn, password, firstName), null);
    }

    @Test
    @Description("Проверка на возможность выполнения log in данными курьера")
    public void successfulLogIn() {
        makePostRequest("/api/v1/courier/login", new ReqCourierLogIn(logIn, password), null)
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", notNullValue());
    }

    @After
    @Description("Удаление созданного курьера")
    public void deleteCourier() {
        deleteUser(logIn, password);
    }
}
