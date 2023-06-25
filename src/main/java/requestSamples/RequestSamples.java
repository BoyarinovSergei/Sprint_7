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

import static org.hamcrest.CoreMatchers.equalTo;

public class RequestSamples {
    private static String id;

    @Step("Make a Post request")
    public static Response makePostRequest(String path, Object json, HashMap<String, String> customHeaders) {
        return RestAssured.given()
                .headers(Optional.ofNullable(customHeaders).orElse(Headers.defaultHeaders))
                .body(json)
                .post(path)
                .andReturn();
    }

    @Step("Make a Delete request")
    public static Response makeDeleteRequest(String path, int id, HashMap<String, String> customHeaders) {
        return RestAssured.given()
                .headers(Optional.ofNullable(customHeaders).orElse(Headers.defaultHeaders))
                .delete(path + "" + id)
                .andReturn();
    }

    @Step("Make a Get request")
    public static Response makeGetRequest(String path, HashMap<String, String> customHeaders) {
        return RestAssured.given()
                .headers(Optional.ofNullable(customHeaders).orElse(Headers.defaultHeaders))
                .get(path)
                .andReturn();
    }

    @Step("Delete a user")
    public static void deleteUser(String login, String password) {
        id = makePostRequest(
                "/api/v1/courier/login",
                new ReqCourierLogIn(login, password),
                null).getBody().path("id").toString();

        makeDeleteRequest("api/v1/courier/", Integer.parseInt(id), null)
                .then()
                .assertThat()
                .body("ok", equalTo(true));
    }
}
