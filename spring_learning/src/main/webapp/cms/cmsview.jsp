<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CMS 상담신청 내역 확인 페이지</title>
</head>
<body>
제목 : ${csubject}<br>
상담자명 : ${cuser}<br>
상담목적 : 
<!-- 
disabled : 해당 내용을 더 이상 수정 및 변경을 못하게 하는 속성 (checkbox, radio, input) 
단점 : 예를 들어서 name 속성 있어서 Back-end로 값을 전송할 때 disable 처리시 값을 전송하지 않음
-->
<input type="checkbox" value="cms1" disabled> 심리상담
<input type="checkbox" value="cms2" disabled> 학교생활
<input type="checkbox" value="cms3" disabled> 가정
<input type="checkbox" value="cms4" disabled> 학업 및 진로
<input type="checkbox" value="cms5" disabled> 대인관계
</body>
</html>