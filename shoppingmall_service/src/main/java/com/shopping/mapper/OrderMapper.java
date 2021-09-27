package com.shopping.mapper;

import java.util.List;

import com.shopping.vo.OrderInfoVO;
import com.shopping.vo.OrderProductVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    public void insertOrderInfo(OrderInfoVO vo); // 주문정보 추가 (고객이 주문했을 때)
    public void deleteOrderInfo(Integer seq); // 주문정보 삭제 (고객이 주문을 취소했을 때)
    public List<OrderProductVO> selectOrderInfo(Integer mi_seq); // 주문정보 조회 (고객이 주문정보를 확인할 때)
    public void updateOrderStatus(Integer seq, Integer status); // 주문상태 변경 (판매자가 배송을 준비 완료 했을 때)
    public void updateOrderDeliveryStatus(Integer seq, Integer status); // 배송상태 변경 (배송사에서 배송 상태가 변경되었을 때)
}
