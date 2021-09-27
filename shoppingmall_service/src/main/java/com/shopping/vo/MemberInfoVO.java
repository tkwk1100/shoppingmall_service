package com.shopping.vo;

import lombok.Data;

@Data
public class MemberInfoVO {
    private Integer mi_seq;
    private String mi_id;
    private String mi_name;
    private String mi_email;
    private String mi_address;
    private String mi_birth;
    private String mi_pwd;
    private Integer mi_gen;
    private String mi_phone;
    private String mi_pay_card;
    private String mi_pay_account;
    private Integer mi_grade;
    private Integer mi_point;
    private Integer mi_status;
}
