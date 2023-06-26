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

import static dataForTests.URLsAndAPIs.LOG_IN;
import static dataForTests.URLsAndAPIs.MAIN_HOST;
import static org.hamcrest.CoreMatchers.equalTo;
import static requestSamples.RequestSamples.makePostRequest;

public class TestLogInNegative {

    private static String logIn;
    private static String password;


    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = MAIN_HOST;
        logIn = "generateString(10)";
        password = "generateString(10)";
    }

    @Test
    @Description("Проверка на возможность выполнения log in данными курьера")
    public void successfulLogIn() {
        makePostRequest(LOG_IN, new ReqCourierLogIn(logIn, password))
                .then().statusCode(404)
                .assertThat()
                .body("code", equalTo(404))
                .body("message", equalTo("Учетная запись не найдена"));
    }
}