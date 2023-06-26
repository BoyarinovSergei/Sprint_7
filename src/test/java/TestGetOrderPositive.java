/*
 * 4. Список заказов
 * Включает проверки:
 * "Проверь, что в тело ответа возвращается список заказов.",
 * */

import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.Test;
import pojo.orderReceiving.response.RespGetOrdersRoot;

import static dataForTests.URLsAndAPIs.GET_ORDERS;
import static requestSamples.RequestSamples.makeGetRequest;

public class TestGetOrderPositive extends SetDefaultURL {

    private RespGetOrdersRoot respGetOrdersRoot;

    @Test
    @Description("Получение всех заказов и проверка на наличие списка заказов")
    public void successfulCourierCreation() {
        respGetOrdersRoot = makeGetRequest(GET_ORDERS)
                .as(RespGetOrdersRoot.class);

        Assert.assertTrue(respGetOrdersRoot.getOrders().size() > 0);
    }
}
