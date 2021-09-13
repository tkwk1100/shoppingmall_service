package com.shopping.api;

import java.security.Provider.Service;
import java.util.LinkedHashMap;
import java.util.Map;

import com.shopping.service.OrderService;
import com.shopping.vo.OrderInfoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderAPIController {
    @Autowired OrderService service;
    @PostMapping("/order/add")
    public Map<String, Object> postOrderAdd(@RequestBody OrderInfoVO vo) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        service.insertOrderInfo(vo);
        resultMap.put("status", true);
        return resultMap;
    }
    @DeleteMapping("/delete/order")
    public Map<String, Object> deleteOrder(@RequestBody Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        service.deleteOrderInfo(seq);
        resultMap.put("status", true);
        return resultMap;
    }
    @PatchMapping("/order/status")
    public Map<String, Object> patchOrder(@RequestBody Integer seq, @RequestBody Integer status) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        service.updateOrderStatus(seq, status);
        resultMap.put("status", true);
        return resultMap;
    }
    @PatchMapping("/order/status/deilvery")
    public Map<String, Object> patchOrderDeilvery(@RequestBody Integer seq,  @RequestBody Integer status) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        service.updateOrderDeilveryStatus(seq, status);
        resultMap.put("status", true);
        return resultMap;
    }
}
