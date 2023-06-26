/*
* Класс с методами-запросами
* для переиспользования
* */

package requestSamples;

import dataForTests.Headers;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.courierLogIn.request.ReqCourierLogIn;

import java.util.HashMap;
import java.util.Optional;

import static dataForTests.Headers.defaultHeaders;
import static dataForTests.URLsAndAPIs.DELETE_COURIER;
import static dataForTests.URLsAndAPIs.LOG_IN;
import static org.hamcrest.CoreMatchers.equalTo;

public class RequestSamples {
    private static String id;

    @Step("Make a Post request")
    public static Response makePostRequest(String path, Object json) {
        return RestAssured.given()
                .headers(defaultHeaders)
                .body(json)
                .post(path)
                .andReturn();
    }

    @Step("Make a Delete request")
    public static Response makeDeleteRequest(String path, int id) {
        return RestAssured.given()
                .headers(defaultHeaders)
                .delete(path + "" + id)
                .andReturn();
    }

    @Step("Make a Get request")
    public static Response makeGetRequest(String path) {
        return RestAssured.given()
                .headers(defaultHeaders)
                .get(path)
                .andReturn();
    }

    @Step("Delete a user")
    public static void deleteUser(String login, String password) {
        id = makePostRequest(LOG_IN, new ReqCourierLogIn(login, password))
                .getBody()
                .path("id")
                .toString();

        makeDeleteRequest(DELETE_COURIER, Integer.parseInt(id))
                .then()
                .assertThat()
                .body("ok", equalTo(true));
    }
}
