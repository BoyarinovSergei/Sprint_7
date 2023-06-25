package pojo.orderReceiving.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PageInfo {
    public int page;
    public int total;
    public int limit;
}
