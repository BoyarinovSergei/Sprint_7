/*
 * 1. Создание курьера
 * Включает проверки:
 * "курьера можно создать;",
 * "запрос возвращает правильный код ответа;",
 * "успешный запрос возвращает ok: true;"
 * */

import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.courierCreation.request.ReqCourierCreation;
import static org.apache.http.HttpStatus.*;

import static dataForTests.URLsAndAPIs.CREATE_COURIER;
import static helper.StringGenerator.generateString;
import static org.hamcrest.CoreMatchers.equalTo;
import static requestSamples.DeleteUsers.deleteUser;
import static requestSamples.RequestSamples.makePostRequest;

public class TestCourierCreationPositive extends SetDefaultURL {

    private String logIn;
    private String password;
    private String firstName;

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
        makePostRequest(CREATE_COURIER,
                new ReqCourierCreation(logIn, password, firstName))
                .then()
                .statusCode(SC_CREATED)
                .assertThat()
                .body("ok", equalTo(true));
    }

    @After
    @Description("Удаление созданного курьера")
    public void deleteCourier() {
        deleteUser(logIn, password);
    }
}
