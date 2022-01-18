package in.nozama.service.util;

import in.nozama.service.entity.Order;
import in.nozama.service.entity.Status;

public class OrderTestUtil {

    public static final String GET_ORDER_BY_ID_RESPONSE = "{\"orderid\":1,\"user\":{\"userid\":100," +
            "\"fullname\":\"Jack\",\"email\":\"jack@test.com\",\"mobile\":\"98989809898\"," +
            "\"password\":null,\"gender\":\"MALE\",\"address\":null,\"usertype\":\"BASIC\"," +
            "\"links\":[]},\"items\":[],\"status\":\"NEW\",\"finalprice\":120.0,\"totalPrice\":120.0," +
            "\"discountApplied\":false,\"discountPercentage\":0.0,\"createdDate\":null,\"updatedDate\":null}";

    public static Order createOrderModel(Long id, Status status, Long user, Double totalPrice, Double finalPrice,
                                         Double discountPercentage,
                                         Boolean isDiscountApplied){
        Order order = new Order();
        order.setOrderid(id);
        order.setStatus(status);
        if(user != null){
            order.setUser(user);
        }else{
            order.setUser(1L);
        }
        order.setTotalPrice(totalPrice);
        order.setFinalprice(finalPrice);
        order.setDiscountPercentage(discountPercentage);
        order.setDiscountApplied(isDiscountApplied);

        return order;
    }



}

