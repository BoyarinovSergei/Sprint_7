/*
 * 4. Список заказов
 * Включает проверки:
 * "Проверь, что в тело ответа возвращается список заказов.",
 * */

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pojo.orderReceiving.response.RespGetOrdersRoot;

import static dataForTests.URLs.url;
import static requestSamples.RequestSamples.makeGetRequest;

public class TestGetOrderPositive {

    private RespGetOrdersRoot respGetOrdersRoot;

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = url.get("Main host");
    }

    @Test
    @Description("Получение всех заказов и проверка на наличие списка заказов")
    public void successfulCourierCreation() {
        respGetOrdersRoot = makeGetRequest("/api/v1/orders", null)
                .as(RespGetOrdersRoot.class);

        Assert.assertTrue(respGetOrdersRoot.getOrders().size() > 0);
    }
}
