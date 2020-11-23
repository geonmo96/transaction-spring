<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>buy_ticket_end - 결제가 완료 되었습니다.</h4>
	[<a href="buy_ticket">티켓 구매</a>] &nbsp;&nbsp;
	[<a href="result">결제 확인</a>]<hr>
	1성공, 0실패<br>
	사용자 디비 결제 확인 : ${arr[0] }<br>
	시스템 디비 결제 확인 : ${arr[1] }<br>
</body>
</html>