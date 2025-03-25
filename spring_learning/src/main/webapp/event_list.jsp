<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ResultSet rs = (ResultSet)request.getAttribute("rs");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 등록 사용자 리스트 페이지</title>
</head>
<body>
<%
	while(rs.next()){
%>
이벤트 참가자명 : <%=rs.getString("ename")%> <br>
<%
	}
	rs.close();
%>
</body>
</html>