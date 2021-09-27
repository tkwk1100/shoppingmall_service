package com.shopping.mapper;

import java.util.List;
import com.shopping.vo.CategoryVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    public List<CategoryVO> selectCategories();
    public String selectCategoryName(Integer seq);
}
