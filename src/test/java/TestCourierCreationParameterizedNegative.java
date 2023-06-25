/*
 * 1. Создание курьера
 * Включает проверки:
 * "чтобы создать курьера, нужно передать в ручку все обязательные поля;",
 * "запрос возвращает правильный код ответа;",
 * "если одного из полей нет, запрос возвращает ошибку;"
 * */

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pojo.courierCreation.request.ReqCourierCreation;

import static dataForTests.URLs.url;
import static helper.StringGenerator.generateString;
import static org.hamcrest.CoreMatchers.equalTo;
import static requestSamples.RequestSamples.makePostRequest;

@RunWith(Parameterized.class)
public class TestCourierCreationParameterizedNegative {

    private static String logIn;
    private static String password;
    private static String firstName;
    private ReqCourierCreation reqCourierCreation;

    @Parameterized.Parameters
    public static Object[][] getSumData() {
        return new Object[][]{
                {new ReqCourierCreation(null, generateString(4), generateString(4))},
                {new ReqCourierCreation(generateString(4), null, generateString(4))},
                {new ReqCourierCreation(null, null, generateString(4))},
                {new ReqCourierCreation(null, null, null)}
        };
    }

    public TestCourierCreationParameterizedNegative(ReqCourierCreation reqCourierCreation) {
        this.reqCourierCreation = reqCourierCreation;
    }

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = url.get("Main host");
    }

    @Test
    @Description("Создание одного курьера и проверка на то, что запрос работает только с обязательными полями {0}")
    public void creatingOneCourierWithNoAllRequiredFields() {
        makePostRequest("/api/v1/courier", reqCourierCreation, null)
                .then()
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}
