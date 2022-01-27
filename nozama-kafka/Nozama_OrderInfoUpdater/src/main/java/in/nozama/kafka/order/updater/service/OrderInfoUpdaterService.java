package in.nozama.kafka.order.updater.service;

import in.nozama.service.entity.Order;

public interface OrderInfoUpdaterService {

    void sendOrderInfo(Order message);

}
