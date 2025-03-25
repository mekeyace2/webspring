<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//Controller에서 보낸 값을 받는 역활
	String pdnm = (String)request.getAttribute("pdnm");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
상품명 : <%=pdnm%> <br>
bbb View jsp 파일
</body>



</html>