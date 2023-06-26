/*
 * 2. Логин курьера
 * Включает проверки:
 * "курьер может авторизоваться;",
 * "успешный запрос возвращает id.",
 * */

import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.courierCreation.request.ReqCourierCreation;
import pojo.courierLogIn.request.ReqCourierLogIn;

import static dataForTests.URLsAndAPIs.CREATE_COURIER;
import static dataForTests.URLsAndAPIs.LOG_IN;
import static helper.StringGenerator.generateString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static requestSamples.RequestSamples.deleteUser;
import static requestSamples.RequestSamples.makePostRequest;

public class TestLogInPositive extends SetDefaultURL {
    private String logIn;
    private String password;
    private String firstName;


    @Before
    @Description("Генерация данных и создание курьера")
    public void generateData() {
        logIn = generateString(10);
        password = generateString(10);
        firstName = generateString(10);

        makePostRequest(CREATE_COURIER,
                new ReqCourierCreation(logIn, password, firstName));
    }

    @Test
    @Description("Проверка на возможность выполнения log in данными курьера")
    public void successfulLogIn() {
        makePostRequest(LOG_IN, new ReqCourierLogIn(logIn, password))
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
