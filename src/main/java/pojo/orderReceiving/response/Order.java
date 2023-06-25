package pojo.orderReceiving.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class Order {
    public int id;
    public Object courierId;
    public String firstName;
    public String lastName;
    public String address;
    public String metroStation;
    public String phone;
    public int rentTime;
    public String deliveryDate;
    public int track;
    public ArrayList<String> color;
    public String comment;
    public String createdAt;
    public String updatedAt;
    public int status;
}
