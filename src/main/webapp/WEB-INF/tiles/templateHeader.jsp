<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<link href="${pageContext.request.contextPath }/resources/css/bootstrap.css" rel="stylesheet">
		<script src="${pageContext.request.contextPath }/resources/js/jquery.js"></script>
		<!--개인 디자인 추가-->
		<link href="${pageContext.request.contextPath }/resources/css/style.css" rel="stylesheet">
		<script src="${pageContext.request.contextPath }/resources/js/bootstrap.js"></script>
	</head>
	<body>

		<!-- user 화면 템플릿 -->

		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />

	</body>
</html>