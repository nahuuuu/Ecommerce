package com.ecommerce.service.interfaces;

import com.ecommerce.dto.OrderDetailDTO;
import com.ecommerce.dto.OrderDetailRequest;
import com.ecommerce.entity.OrderDetailEntity;

import java.util.List;

public interface IOrderDetailService {

    OrderDetailDTO createOrderDetail(OrderDetailRequest orderDetailRequest);

    String deleteOrderDetail(Long id);

    //OrderDetailEntity updateOrderDetail(UpdateOrderDetailRequest orderDetailRequest);

    List<OrderDetailEntity> findByPurchase(Long purchaseId);
}
