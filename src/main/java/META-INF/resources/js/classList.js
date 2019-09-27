$(function() {
    alert("加载js");
    $(".vcodeImg").click(function() {
        $("img.vcodeImg").attr("src", "vcode.png?n=" + Math.random());
    })
});