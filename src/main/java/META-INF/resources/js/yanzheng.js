$(function() {
	$(".vcodeImg").click(function() {
		$("img.vcodeImg").attr("src", "vcode.png?n=" + Math.random());
	})
});
$(".container-fluid.w-50").css({
	"margin-left" : -$(".container-fluid.w-50").width() / 2 + "px",
	"margin-top" : -$(".container-fluid.w-50").height() / 2 + "px"
});