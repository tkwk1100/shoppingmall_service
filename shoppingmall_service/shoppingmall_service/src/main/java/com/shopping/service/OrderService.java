package com.shopping.service;

import java.text.DecimalFormat;
import java.util.List;

import com.shopping.mapper.OrderMapper;
import com.shopping.mapper.ReviewMapper;
import com.shopping.vo.OrderInfoVO;
import com.shopping.vo.OrderProductVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired OrderMapper mapper;
    @Autowired ReviewMapper r_mapper;
    public List<OrderProductVO> selectOrderInfo(Integer mi_seq){
        List<OrderProductVO> list = mapper.selectOrderInfo(mi_seq);
        DecimalFormat formatter = new DecimalFormat("###,###");
        for(OrderProductVO item : list) {
            //1.
            Integer discount_rate = item.getPi_discount_rate();
            Integer point_rate = item.getPi_point_rate();
            Integer origin_price = item.getPi_price();
            Integer count = item.getOi_prod_count();
            //2. 
            Integer final_price = (int)(origin_price - origin_price * discount_rate / 100.0) * count;
            Integer final_point = (int)(final_price * point_rate / 100.0);
            //3.
            String formatted_final_price = formatter. format(final_price);
            String formatted_final_point = formatter. format(final_point);
            String formatted_origin_price = formatter. format(origin_price * count);
            //4. 3까지 진해된 값을 item 객체에 저장한다. 
            item.setFinal_price(formatted_final_price);
            item.setFinal_point(formatted_final_point);
            item.setOrigin_price(formatted_origin_price);
            //5. 리뷰 작성 여부를 확인하여 값을 지정한다. 
            Integer oi_seq = item.getOi_seq();
            Boolean written = r_mapper.selectReviewCntByOrderId(oi_seq) == 1;
            item.setReview_written(written);
        }

        return list;
    }
    public void insertOrderInfo(OrderInfoVO vo){
        mapper.insertOrderInfo(vo);
    }
    public void deleteOrderInfo(Integer seq){
        mapper.deleteOrderInfo(seq);
    }
    public void updateOrderStatus(Integer seq, Integer status){
        mapper.updateOrderStatus(seq, status);
    }
    public void updateOrderDeilveryStatus(Integer seq, Integer status){
        mapper.updateOrderDeilveryStatus(seq, status);
    }
}
