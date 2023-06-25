package pojo.orderCreation.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class ReqOrderCreation {
    public String firstName;
    public String lastName;
    public String address;
    public int metroStation;
    public String phone;
    public int rentTime;
    public String deliveryDate;
    public String comment;
    public ArrayList<String> color;

    public ReqOrderCreation(ArrayList<String> color) {
        this.color = color;
        this.firstName = "Naruto";
        this.lastName = "Uchiha";
        this.address = "Konoha, 142 apt.";
        this.metroStation = 4;
        this.phone = "+7 800 355 35 35";
        this.rentTime = 5;
        this.deliveryDate = "2020-06-06";
        this.comment = "Saske, come back to Konoha";
    }
}
