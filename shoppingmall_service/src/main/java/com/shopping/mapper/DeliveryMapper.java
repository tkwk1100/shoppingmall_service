package com.shopping.mapper;

import com.shopping.vo.DeliveryInfoVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeliveryMapper {
    public DeliveryInfoVO selectDeliveryInfoBySeq(Integer seq);
}
