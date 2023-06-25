package pojo.orderReceiving.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class RespGetOrdersRoot {
    public ArrayList<Order> orders;
    public PageInfo pageInfo;
    public ArrayList<AvailableStation> availableStations;
}
