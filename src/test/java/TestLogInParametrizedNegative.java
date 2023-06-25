/*
 * 2. Логин курьера
 * */

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pojo.courierLogIn.request.ReqCourierLogIn;

import static dataForTests.URLs.url;
import static helper.StringGenerator.generateString;
import static org.hamcrest.CoreMatchers.equalTo;
import static requestSamples.RequestSamples.makePostRequest;

@RunWith(Parameterized.class)
public class TestLogInParametrizedNegative {

    private ReqCourierLogIn reqCourierLogIn;

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {new ReqCourierLogIn(generateString(5), "")},
                {new ReqCourierLogIn("", generateString(4))},
                {new ReqCourierLogIn("", "")}
        };
    }

    public TestLogInParametrizedNegative(ReqCourierLogIn reqCourierLogIn) {
        this.reqCourierLogIn = reqCourierLogIn;
    }

    @BeforeClass
    public static void generateData() {
        RestAssured.baseURI = url.get("Main host");
    }

    @Test
    @Description("Проверка на невозможность авторизации без обязательных полей")
    public void unsuccessfulLogIn() {
        makePostRequest("/api/v1/courier/login", reqCourierLogIn, null)
                .then()
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"))
                .body("code", equalTo(400));
    }
}
