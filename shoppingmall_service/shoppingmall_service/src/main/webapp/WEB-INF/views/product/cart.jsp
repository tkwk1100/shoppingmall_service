<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/assets/css/cart.css">
        <script src="/assets/js/cart.js"></script>
    <title>Document</title>
</head>
<body>
    <h1>장바구니</h1>
    <div class="cart_container">
        <div class="container_left">
            <table>
                <thead>
                    <tr>
                        <td coispa="2">
                            <input type="checkbox" id="check_all">
                        <label for="check_all">
                        </label>
                            <span class="count">
                                <span>전체선택</span>(<span class="sel">0</span>/<span class="total">${list.size()}</span>)
                        </span>
                        </td>
                        <td>
                            <button class="delete_selected">선택삭제</button>
                        </td>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${list.size() == 0}">
                        <tr>
                            <td colspan="6" class="nodata">장바구니에 담긴 상품이 없습니다.</td>
                        </tr>
                    </c:if>
                    <c:forEach items="${list}" var="item">
                    <tr class="cart_prod" data-seq="${item.pi_seq}" data-mi-seq="${member.mi_seq}">
                        <td>
                            <input type="checkbox" data-seq="${item.pi_seq}" id="sel${item.pi_seq}">
                            <label for="sel${item.pi_seq}">선택</label>
                        </td>
                        <td>
                            <img src="/image/${item.pi_img_uri}">
                        </td>
                        <td>
                            <p>[${item.seller_name}] ${item.pi_name}</p>
                        </td>
                        <td data-final-price="${item.discounted_price}" data-origin-price="${item.pi_price}">
                            <button class="minus" data-seq="${item.pi_seq}" data-user-seq="${member.mi_seq}">-</button>
                            <!-- <span class="cnt">${item.sc_count}</span> -->
                            
                            <input type="text" min ="0" class="cnt" value="${item.sc_count}" 
                            style="display: inline-block; width:50px;"data-seq="${item.pi_seq}"
                            data-user-seq="${member.mi_seq}" data-stock="${item.pi_stock}">
                            
                            <button class="plus" data-seq="${item.pi_seq}" data-user-seq="${member.mi_seq}" data-stock="${item.pi_stock}">+</button>
                        </td>
                        <td>
                            <span class="final_price">${item.discounted_price * item.sc_count}</span>
                            <span>원</span>
                            <c:if test="${item.pi_discount_rate != 0}">
                                <br>
                            <span class="origin_price">${item.pi_price * item.sc_count}</span>
                            <span>원</span>
                            </c:if>
                            <c:if test="${item.pi_discount_rate == 0}">
                                
                            <span class="origin_price" style="display:none;">${item.pi_price * item.sc_count}</span>
                            <span style="display:none;">원</span>
                            </c:if>
                        </td>
                        <td>
                            <button class="delete" data-seq="${item.pi_seq}" data-user-seq="${member.mi_seq}">&times;</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="container_right">
            <div class="address">
                <h3><img src="/assets/images/ico_delivery_setting.svg">배송지</h3>
                <p>${member.mi_address}</p>
                <button id="change_address">배송지 변경</button>on
            </div>
            <div class="total_payment">
                <table>
                    <tbody>
                        <tr>
                            <rd>상품금액</rd>
                            <rd id="total_price">
                                <span>0</span>원
                            </rd>
                        </tr>
                        <tr>
                            <td>상품할인금액</td>
                            <td id="total_discount">
                                <span>-222</span>원
                            </td>
                        </tr>
                        <tr>
                            <td>배송비</td>
                            <td id="total_deilvery_price">
                            <span>0</span>원
                        </td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td>결제예정금액</td>
                                <td class="payment">
                                    <span>55555</span>원
                                </td>
                        </tr>
                        <!-- <tr>
                            <td colspan="2">
                                <span>적립</span>
                                <span>구매 시 <span class="point">111</span>원 적립</span>
                            </td>
                        </tr> -->
                    </tfoot>
                </table>
            </div>
            <div class="order">
                <button id="order_dtn">주문하기</button>
                <ul>
                    <li>쿠폰/적립금은 주문서에서 사용 가능합니다.</li>
                    <li>'입금확인'상태일 때는 주문 내역 상세에서 직접 주문취소가 가능합니다.</li>
                    <li>'입금확인'이후 상태에는 고객센터로 문의해주세요.</li>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>