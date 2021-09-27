$(function(){
    $("#save").click(function(){
        if($(".review_rate_input input:checked").val() == null) {
            alert("평점을 선택하세요.");
            return;
        }
        // if($("#review_detail_content").val() == '') {
        //     alert("리뷰 내용을 입력하세요.");
        //     return;
        // }
        if(!confirm("리뷰를 등록하시겠습니까?")){
            return;
        }

        let oi_seq = getParameterByName('oi_seq');

        let pi_seq = $("#product").val();
        let mi_seq = $("#member").val();
        let content = $("#review_detail_content").val();
        let rate = $(".review_rate_input input:checked").val();

        let si_seq = $("#seller").val();
        let seller_rate = $("#seller_rate_area input:checked").val();

        let data = {
            rev_pi_seq:pi_seq,
            rev_mi_seq:mi_seq,
            rev_oi_seq:oi_seq,
            rev_content:content,
            rev_rate:rate,
            si_seq:si_seq,
            seller_rate:seller_rate
        }
        $.ajax({
            type:"post",
            url:"/review/write",
            contentType:"application/json",
            data:JSON.stringify(data),
            success:function(r) {
                alert(r.message);
                history.back();
            }
        })
    });
    $("#cancel").click(function(){
        if(!confirm("이전 페이지로 돌아갑니다.\n(작성중인 내용인 모두 삭제됩니다.)")){
            return;
        }
        history.back();
    });
})

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
    results = regex.exec(location.search);
    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}