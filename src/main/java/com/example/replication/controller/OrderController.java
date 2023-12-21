package com.example.replication.controller;

import com.example.replication.controller.dto.AllOrdersRequest;
import com.example.replication.entity.ShopeeOrder;
import com.example.replication.service.serviceimpl.OrderService;
import com.example.replication.util.cache.Cache;
import com.example.replication.util.entity.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final Cache cache;
    private final OrderService orderService;

    @PostMapping("/allOrders")
    public ResponseEntity getAllOrders(@RequestBody AllOrdersRequest allOrdersRequest) {
        PageImpl<ShopeeOrder> orderPage = orderService.getAllShopeeOrders(allOrdersRequest);
        return Response.data(orderPage);
    }

}
