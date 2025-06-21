<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		<form action="${pageContext.request.contextPath}/gbc3" method="get">
			<table>
				<tr>
					<td>비밀번호</td>
					<td>
						<input type="password" name="password" value="">
					</td>
					<td>
						<input type="hidden" name="action" value="delete">
						<input type="hidden" name="no" value=${param.no }>
						<button type="">삭제</button>
					</td>
				</tr>
			</table>
		</form>
		
		<br><br>
		<a href="${pageContext.request.contextPath}/gbc3?action=list">메인으로 돌아가기</a>
	</body>
	
</html>