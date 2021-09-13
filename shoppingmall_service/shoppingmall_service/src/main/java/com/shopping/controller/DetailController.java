package com.shopping.controller;

import com.shopping.mapper.DeilveryMapper;
import com.shopping.mapper.ReviewMapper;
import com.shopping.service.ProductService;
import com.shopping.vo.DeilveryInfoVO;
import com.shopping.vo.ProductInfoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DetailController {
    @Autowired
    ProductService service; 
    @Autowired
    DeilveryMapper d_mapper;
    @Autowired
    ReviewMapper r_mapper;

    @GetMapping("/detail")
    public String getDetail(@RequestParam Integer prod_seq, Model model){
        ProductInfoVO item = service.selectProductBySeq(prod_seq);
        model.addAttribute("product", item);
        Integer i = item.getPi_di_seq(); // 제품의 배송사 번호를 가져온다.
        DeilveryInfoVO deilvery = d_mapper.selectDeilveryInfoBySeq(i);//배송사 정보 가져오기
        model.addAttribute("deilvery", deilvery);

        Integer pi_seq = item.getPi_seq();
        Double rate = r_mapper.selectProdReviewRateAvg(pi_seq);
        if(rate == null) rate = 0.0;
        Integer i_rate = (int)Math.round(rate);

        model.addAttribute("rate", i_rate);

        return "/detail/info";
    }
}
