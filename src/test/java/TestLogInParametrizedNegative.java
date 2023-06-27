/*
 * 2. Логин курьера
 * */

import io.qameta.allure.Description;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pojo.courierLogIn.request.ReqCourierLogIn;

import static dataForTests.URLsAndAPIs.LOG_IN;
import static helper.StringGenerator.generateString;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.hamcrest.CoreMatchers.equalTo;
import static requestSamples.RequestSamples.makePostRequest;

@RunWith(Parameterized.class)
public class TestLogInParametrizedNegative extends SetDefaultURL {

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

    @Test
    @Description("Проверка на невозможность авторизации без обязательных полей")
    public void unsuccessfulLogIn() {
        makePostRequest(LOG_IN, reqCourierLogIn)
                .then()
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"))
                .body("code", equalTo(SC_BAD_REQUEST));
    }
}
