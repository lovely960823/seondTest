// html页面加载完成才会加载本方法
$(function (){

	/*var year = $("#year").text();*/

	setYear();
	// 每一秒执行一次
	setInterval("setYear()","1000");

});

/**
 * 
 * 获取当前时间
 */
function getNow(s) {
    return s < 10 ? '0' + s: s;
}

function setYear() {
	 var date=new Date;
	 var year=date.getFullYear(); 
 	 var month=date.getMonth()+1;
 	 var day = date.getUTCDate();
	 var hours = date.getUTCHours();
 	 var minute = date.getUTCMinutes();
 	 var seconds = date.getSeconds();
	 
	$("#year").html(year+'年');
	$("#month").html(month+'月'+day+'日');
	
	$("#time").html(hours+8+":"+minute+":"+getNow(seconds));
	

}
