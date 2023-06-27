package requestSamples;

import io.qameta.allure.Step;
import pojo.courierLogIn.request.ReqCourierLogIn;

import static dataForTests.URLsAndAPIs.DELETE_COURIER;
import static dataForTests.URLsAndAPIs.LOG_IN;
import static org.hamcrest.CoreMatchers.equalTo;
import static requestSamples.RequestSamples.makeDeleteRequest;
import static requestSamples.RequestSamples.makePostRequest;

public class DeleteUsers {
    private static String id;

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
