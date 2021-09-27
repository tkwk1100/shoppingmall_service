// assets/js/detail.js
$(function(){
    let count = 1;
    // * 1 해주는 이유는, 문자열에서 숫자값으로 강제로 형태 변환을 해주기 위함.
    let price = $(".discounted").html().replace(",", "") * 1;
    $(".total_price b").html(price);
    
    let point_rate = $(".save_point").attr("data-point-rate");
    let point = Math.floor(price * point_rate / 100);
    $(".save_point b").html(point+"원");
    $("#plus").click(function(){
        count++;
        if(count >= $(".stock").html()) count = $(".stock").html();
        $("#count").html(count);
        $(".total_price b").html(price * count);
        point = Math.floor(price * point_rate / 100 * count);
        $(".save_point b").html(point+"원");
    })
    $("#minus").click(function(){
        count--;
        if(count < 1) count = 1;
        $("#count").html(count);
        $(".total_price b").html(price * count);
        point = Math.floor(price * point_rate / 100 * count);
        $(".save_point b").html(point+"원");
    });
    $("#shopping_bag").click(function(){
        if(memberInfo.seq == "" || memberInfo.seq == null || memberInfo.seq == undefined) {
            alert("로그인 후 사용하실 수 있습니다.");
            return;
        }
        let data = {
            sc_mi_seq:memberInfo.seq,
            sc_pi_seq:$(".detail_container").attr("data-prod-seq"),
            sc_count:$("#count").html()
        }
        $.ajax({
            type:"post",
            url:"/cart/add",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(r) {
                alert(r.message);
                location.reload();
            }
        })
    })
})