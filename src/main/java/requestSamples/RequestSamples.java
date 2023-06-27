/*
 * Класс с методами-запросами
 * для переиспользования
 * */

package requestSamples;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static dataForTests.Headers.defaultHeaders;

public class RequestSamples {

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
}
