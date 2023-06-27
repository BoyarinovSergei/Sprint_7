/*
 * 1. Создание курьера
 * Включает проверки:
 * "нельзя создать двух одинаковых курьеров",
 * "запрос возвращает правильный код ответа",
 * "если создать пользователя с логином, который уже есть, возвращается ошибка."
 * */

import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.courierCreation.request.ReqCourierCreation;

import static dataForTests.URLsAndAPIs.CREATE_COURIER;
import static helper.StringGenerator.generateString;
import static org.apache.http.HttpStatus.SC_CONFLICT;
import static org.hamcrest.CoreMatchers.equalTo;
import static requestSamples.DeleteUsers.deleteUser;
import static requestSamples.RequestSamples.makePostRequest;

public class TestCourierCreationNegative extends SetDefaultURL {
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
    @Description("Создание двух идентичных курьеров подряд и проверка на то, что второй запрос получил в ответ предупреждение")
    public void creatingTwoIdenticalCouriers() {
        makePostRequest(CREATE_COURIER, new ReqCourierCreation(logIn, password, firstName));

        makePostRequest(CREATE_COURIER, new ReqCourierCreation(logIn, password, firstName))
                .then()
                .statusCode(SC_CONFLICT)
                .assertThat()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @After
    @Description("Удаление созданного курьера")
    public void deleteCourier() {
        deleteUser(logIn, password);
    }
}
