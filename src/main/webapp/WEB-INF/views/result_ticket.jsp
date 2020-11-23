<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>result_ticket.jsp</h4>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<table border="1">
		<caption>결과 페이지</caption>
		<c:forEach var="re" items="${result }">
			<tr>
				<th>${re.key }</th>
				<c:forEach var="arr" items="${re.value }">
					<td>${arr.id } : ${arr.ticketnum }</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
	[<a href="buy_ticket">결제 페이지</a>]
</body>
</html>