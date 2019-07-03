package in.nozama.kafka.order.updater.service;

import in.nozama.service.model.Order;

public interface OrderInfoUpdaterService {

    void sendOrderInfo(Order message);

}
