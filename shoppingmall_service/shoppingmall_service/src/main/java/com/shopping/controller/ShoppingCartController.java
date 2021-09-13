package com.shopping.controller;

import java.util.List;

import com.shopping.service.ShoppingCartService;
import com.shopping.vo.ProductInfoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShoppingCartController {
    @Autowired
    ShoppingCartService service;
    @GetMapping("/cart")
    public String getShoppingCartDefault() {return "redirect:/login";}
    //http://localhost/cart/user002
    @GetMapping("/cart/{member_id}")
    public String getShoppingCart(
    @PathVariable @Nullable String member_id, 
    //iii = 사용자의 db상의 seq 번호
    @RequestParam Integer iii,
    Model model
    ) {
        if(member_id == null){
            return "redirect:/login";
        }
        List<ProductInfoVO> list = service.selectCatrProduct(iii);
        model.addAttribute("list", list);
        return "/product/cart";
    }
}
