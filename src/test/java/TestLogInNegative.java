/*
 * 2. Логин курьера
 * Включает проверки:
 * "если авторизоваться под несуществующим пользователем, запрос возвращает ошибку;",
 * "система вернёт ошибку, если неправильно указать логин или пароль;",
 * */

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import pojo.courierLogIn.request.ReqCourierLogIn;

import static dataForTests.URLs.url;
import static org.hamcrest.CoreMatchers.equalTo;
import static requestSamples.RequestSamples.makePostRequest;

public class TestLogInNegative {

    private static String logIn;
    private static String password;

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = url.get("Main host");
        logIn = "generateString(10)";
        password = "generateString(10)";
    }

    @Test
    @Description("Проверка на возможность выполнения log in данными курьера")
    public void successfulLogIn() {
        makePostRequest("/api/v1/courier/login", new ReqCourierLogIn(logIn, password), null)
                .then().statusCode(404)
                .assertThat()
                .body("code", equalTo(404))
                .body("message", equalTo("Учетная запись не найдена"));
    }
}