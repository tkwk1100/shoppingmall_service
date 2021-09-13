<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" href="/assets/css/order.css">
<html lang="en">

<heads>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body class="iu">
    <h1>주문 정보</h1>
    <div class="order_list">
        <c:forEach items="${order_list}" var="item">
            <div class="order_item">
                <div class="order_info">
                    <p class="order_date">
                        <fmt:formatDate value="${item.oi_reg_dt}" pattern="yyyy년 MM월 dd일 (EE)" />
                        <span class="order_status">
                            <c:if test="${item.oi_delivery_status == 0}">배송준비중</c:if>
                            <c:if test="${item.oi_delivery_status == 1}">배송중</c:if>
                            <c:if test="${item.oi_delivery_status == 2}">배송완료</c:if>
                        </span>
                    </p>
                    <div class="item_info">
                        <img src="/image/${item.pi_img_uri}">
                        <div class="item_detail">
                            <p>${item.pi_name}</p>
                            <p>${item.final_price}원 <span>${item.oi_prod_count}개</span></p>
                        </div>
                    </div>
                </div>
                <div class="oredr_bottons">
                    <a href="#" id="delivery_deatil">배송조회</a>
                    <a href="#" id="delivery_return">교환, 반품 신청</a>
                    <c:if test="${item.oi_delivery_status == 2 && item.review_written == false}">
                        <a href="/review?mi_seq=${member.mi_seq}&pi_seq=${item.pi_seq}&oi_seq=${item.oi_seq}" id="review">리뷰 작성</a>
                    </c:if>
                </div>
            </div>
        </c:forEach>
    </div>
</body>

</html>