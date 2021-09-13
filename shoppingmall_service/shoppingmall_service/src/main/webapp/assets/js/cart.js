// cart.js
$(function () {
    calcPayment();

    $(".delete").click(function () {
        if (!confirm("삭제하시겠습니까?")) return;

        let pi_seq = $(this).attr("data-seq");
        let mi_seq = $(this).attr("data-user-seq");

        $.ajax({
            type: "delete",
            url: "/cart/remove?pi_seq=" + pi_seq + "&mi_seq=" + mi_seq,
            success: function (r) {
                alert(r.message);
                location.reload();
                // console.log();
                // 동작확인할것
            }
        })

        // alert("상품번호 : "+pi_seq+", 회원번호 : "+mi_seq);
    });

    $(".plus").click(function () {
        let pi_seq = $(this).attr("data-seq");
        let mi_seq = $(this).attr("data-user-seq");
        // let cnt = $(this).parent().find(".cnt").html()*1;
        let cnt = $(this).parent().find(".cnt").val() * 1;
        let stock = $(this).attr("data_stock")
        cnt++
        if (cnt > stock) {
            alert("최대 주문 수량은" + stock + "개 입니다.")
            cnt = stock;
            return;
        }
        // $(this).parent().find(".cnt").html(cnt);
        $(this).parent().find(".cnt").val(cnt);
        let $this = $(this);
        $.ajax({
            type: "patch",
            url: "/cart/increase?pi_seq=" + pi_seq + "&mi_seq=" + mi_seq,
            success: function () {
                let final_price = $this.parent().attr("data-final-price")
                let origin_price = $this.parent().attr("data-origin-price")

                $this.parent().parent().find(".final_price").html(final_price * cnt);
                $this.parent().parent().find(".origin_price").html(origin_price * cnt);
                calcPayment();

            }
        })
        // alert("제품번호 : "+pi_seq+", 회원번호 : "+mi_seq+", 수량 : "+cnt);
    })
    $(".minus").click(function () {
        let pi_seq = $(this).attr("data-seq");
        let mi_seq = $(this).attr("data-user-seq");
        // let cnt = $(this).parent().find(".cnt").html()*1;
        let cnt = $(this).parent().find(".cnt").val() * 1;
        cnt--
        if (cnt < 1) {
            cnt = 1;
            return;
        };
        // $(this).parent().find(".cnt").html(cnt);
        $(this).parent().find(".cnt").val(cnt);

        let $this = $(this);
        $.ajax({
            type: "patch",
            url: "/cart/decrease?pi_seq=" + pi_seq + "&mi_seq=" + mi_seq,
            success: function () {
                let final_price = $this.parent().attr("data-final-price")
                let origin_price = $this.parent().attr("data-origin-price")

                $this.parent().parent().find(".final_price").html(final_price * cnt);
                $this.parent().parent().find(".origin_price").html(origin_price * cnt);
                calcPayment();

            }
        })
    });

    $(".cnt").keydown(function (e) {
        if (e.keyCode == 13) {
            let cnt = Number($(this).val());
            console.log(cnt);
            if (isNaN(cnt)) {
                cnt = 1;
                alert("숫자만 입력하세요");
            }
            // let cnt = $(this).val()*1;
            let pi_seq = $(this).attr("data-seq");
            let mi_seq = $(this).attr("data-user-seq");
            let stock = $(this).attr("data-stock") * 1;
            // alert(cnt+","+pi_seq+","+mi_seq+","+stock);
            if (cnt > stock) {
                alert("최대 구매수량은 " + stock + "개 입니다.");
                cnt = stock;
            }
            if (cnt < 1) {
                alert("최소 구매수량은 1개 입니다.")
                cnt = 1;
            }
            $(this).val(cnt);
            let $this = $(this);
            $.ajax({
                type: "patch",
                url: "/cart/change?pi_seq=" + pi_seq + "&mi_seq=" + mi_seq + "&cnt=" + cnt,
                success: function () {
                    alert("수량이 변경되었습니다.");

                    let final_price = $this.parent().attr("data-final-price")
                    let origin_price = $this.parent().attr("data-origin-price")

                    $this.parent().parent().find(".final_price").html(final_price * cnt);
                    $this.parent().parent().find(".origin_price").html(origin_price * cnt);
                    calcPayment();
                }
            })
        }
    })

    function calcPayment() {
        let total = 0;
        for (let i = 0; i < $(".origin_price").length; i++) {
            let price = $(".origin_price").eq(i).html() * 1;
            total += price;
        }
        $("#total_price > span").html(total);

        let discount_total = 0;
        for (let i = 0; i < $(".final_price").length; i++) {
            let price = $(".final_price").eq(i).html() * 1;
            discount_total += price;
        }

        let total_discount = total - discount_total;
        $("#total_discount > span").html("-" + total_discount);
        $(".payment").html(discount_total);
    }
    $("#order_dtn").click(function () {
        let len = $(".cart_prod").length;
        if (len == 0) {
            alert("장바구니에 상품이 없습니다.");
            return
        }
        if (!confirm("상품을 주문하시겠습니까?")) {
            return;
        }
        for (let i = 0; i < len; i++) {
            let mi_seq = $(".cart_prod").eq(i).attr("data-mi-seq");
            let pi_seq = $(".cart_prod").eq(i).attr("data-seq");
            let count =$(".cart_prod").eq(i).find(".cnt").val();

            // alert("mi_seq : "+mi_seq+",pi_seq : "+pi_seq+", count : "+count);
            $.ajax({
                type: "delete",
                url: "/cart/remove?mi_seq="+mi_seq+"&pi_seq="+pi_seq,
                success:function(r){
                    console.log(r.message);
                }
            })
            let data = {
                oi_mi_seq:mi_seq,
                oi_pi_seq:pi_seq,
                oi_delivery_no:"0000000000000",
                oi_prod_count:count
            }
            $.ajax({
                type:"post",
                url:"/order/add",
                data:JSON.stringify(data),
                contentType:"application/json",
                success:function(r){
                    console.log(r.message);
                }
            })
        }
    })
})