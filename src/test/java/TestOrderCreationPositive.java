/*
 * 3. Создание заказа
 * Включает проверки:
 * "можно указать один из цветов — BLACK или GREY;",
 * "можно указать оба цвета;",
 * "можно совсем не указывать цвет;",
 * "тело ответа содержит track.",
 * */

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pojo.orderCreation.request.ReqOrderCreation;

import java.util.ArrayList;

import static dataForTests.URLs.url;
import static org.hamcrest.CoreMatchers.notNullValue;
import static requestSamples.RequestSamples.makePostRequest;

@RunWith(Parameterized.class)
public class TestOrderCreationPositive {

    private ReqOrderCreation reqOrderCreation;

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {new ReqOrderCreation(new ArrayList<String>() {{
                    add("BLACK");
                    add("GREY");
                }})},
                {new ReqOrderCreation(new ArrayList<String>() {{
                    add("");
                    add("GREY");
                }})},
                {new ReqOrderCreation(new ArrayList<String>() {{
                    add("BLACK");
                    add("");
                }})},
                {new ReqOrderCreation(new ArrayList<String>() {{
                    add("");
                    add("");
                }})}
        };
    }

    public TestOrderCreationPositive(ReqOrderCreation reqOrderCreation) {
        this.reqOrderCreation = reqOrderCreation;
    }

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = url.get("Main host");
    }

    @Test
    @Description("Проверка на возможность добавления цветов 'BLACK', 'GREY', под отдельности, вместе и не указывать цвета")
    public void successfulColorChoice() {
        makePostRequest("/api/v1/orders", reqOrderCreation, null)
                .then()
                .statusCode(201)
                .assertThat()
                .body("track", notNullValue());
    }
}