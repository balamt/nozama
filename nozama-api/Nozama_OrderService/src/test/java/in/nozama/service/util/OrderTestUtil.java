package in.nozama.service.util;

import in.nozama.service.model.*;

public class OrderTestUtil {

    public static final String GET_ORDER_BY_ID_RESPONSE = "{\"orderid\":1,\"user\":{\"userid\":100," +
            "\"fullname\":\"Jack\",\"email\":\"jack@test.com\",\"mobile\":\"98989809898\"," +
            "\"password\":null,\"gender\":\"MALE\",\"address\":null,\"usertype\":\"BASIC\"," +
            "\"links\":[]},\"items\":[],\"status\":\"NEW\",\"finalprice\":120.0,\"totalPrice\":120.0," +
            "\"discountApplied\":false,\"discountPercentage\":0.0,\"createdDate\":null,\"updatedDate\":null}";

    public static Order createOrderModel(Long id, Status status, User user, Double totalPrice, Double finalPrice,
                                         Double discountPercentage,
                                         Boolean isDiscountApplied){
        Order order = new Order();
        order.setOrderid(id);
        order.setStatus(status);
        if(user != null){
            order.setUser(user);
        }else{
            User user1 = new User();
            user1.setUserid(100L);
            user1.setFullname("Jack");
            user1.setEmail("jack@test.com");
            user1.setGender(Gender.MALE);
            user1.setMobile("98989809898");
            user1.setUsertype(UserType.BASIC);
            order.setUser(user1);
        }
        order.setTotalPrice(totalPrice);
        order.setFinalprice(finalPrice);
        order.setDiscountPercentage(discountPercentage);
        order.setDiscountApplied(isDiscountApplied);

        return order;
    }



}

