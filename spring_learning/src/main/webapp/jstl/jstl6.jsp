<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%
	String subject = "공지사항 3월 이벤트가 진행 되었습니다. 많은 관심 부탁 드립니다.";
	String data = "냉장고,에어컨,김치냉장고,서랍";	
	
	//jsp에서 생성된 값을 top.jsp에 출력할 경우 attribute로 셋팅 후 전달가능
	String mid = "hong";
	request.setAttribute("mid", mid);
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 외부 페이지 로드</title>
</head>
<body>
<header>
<nav>
<!-- fn (length, substring, indexOf, replace, trim) -->
<cr:set var="subject" value="<%=subject%>"/>
<cr:set var="sub" value="${fn:length(subject)}"/>
<cr:if test="${sub > 10}">
	<cr:set var="jum" value="..."/>
</cr:if>
게시판 제목 : ${fn:substring(subject,0,10)} ${jum}<br>


<!-- 해당 페이지 jstl 변수를 생성하여 값을 top.jsp로 이관되지 않음 -->
<cr:set var="a" value="연습"/>
<!-- 
import : 외부 페이지 로드
url(속성) : 외부페이지 경로 및 파일명
param(import 속성태그 안에만 적용) : 파라미터 이름으로 해당 페이지에 전달하는 방식 (name)
-->
<cr:import url="./top.jsp">
	<cr:param name="user" value="홍길동"/>
	<cr:param name="z" value="${a}"></cr:param>
</cr:import>
</nav>
</header>
<main>
<!-- fn에 jsp 변수값을 직접 적용하지 못함 -->
<cr:set var="data" value="<%=data%>"/>
<cr:set var="product" value="${fn:split(data,',')}"/>
<cr:forEach var="pd" items="${product}">
${pd}
</cr:forEach>

<cr:import url="./main.jsp"></cr:import>
</main>
<footer>
<cr:import url="./bottom.jsp">

</cr:import>
</footer>
</body>
</html>


