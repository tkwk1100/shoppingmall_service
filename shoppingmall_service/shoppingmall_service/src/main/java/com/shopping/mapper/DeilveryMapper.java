package com.shopping.mapper;

import com.shopping.vo.DeilveryInfoVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeilveryMapper {
    public DeilveryInfoVO selectDeilveryInfoBySeq(Integer seq);
}
