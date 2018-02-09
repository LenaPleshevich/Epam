<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${loginFail}, Online Training</title>
<link rel="stylesheet" type="text/css" href="myStyle.css">
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.logIn" var="logIn"/>
<fmt:message bundle="${loc}" key="local.wrongEmailAndOrPassword" var="wrongEmailAndOrPassword"/>
<fmt:message bundle="${loc}" key="local.email" var="email"/>
<fmt:message bundle="${loc}" key="local.password" var="password"/>
<fmt:message bundle="${loc}" key="local.logIn" var="logIn"/>
<fmt:message bundle="${loc}" key="local.registration" var="registration"/>
<fmt:message bundle="${loc}" key="local.loginFail" var="loginFail"/>
</head>
<body>
<table id="tableLocalization">
	<tr id="trLocalization">
		<td>
			<form action="Controller" id="formLocalization" method="post">
				<input type="hidden" name="command" value="change_local">
				<input type="hidden" name="local" value="ru">
				<input type="submit" name="ChangeLocalRu" value="Рус" class="buttonLocalization">
			</form>
		</td>
		<td>
			<form action="Controller" id="formLocalization" method="post">
				<input type="hidden" name="command" value="change_local">
				<input type="hidden" name="local" value="en">
				<input type="submit" name="ChangeLocalEn" value="Eng" class="buttonLocalization">
			</form>
		</td>
	</tr>
</table>
<h1 id="HeaderIndexPage">${logIn}</h1>
<table id="tableLoginOnFailPage">
	<form action="Controller" method="post">	
		<th colspan="2" class="myfont">
			${wrongEmailAndOrPassword}
		</th>			 
		<tr>
			<td class="myfont">${email}</td>
			<td align="center" id="tdLogin"><input class="mytext" type="text" name="email" value=""></td>
		</tr>
		<tr>
			<td class="myfont">${password}</td>
			<td align="center" id="tdLogin"><input class="mytext" type="password" name="password" value=""></td>
		</tr>
		<tr>
			<td align="center" id="tdLogin">
				<input type="hidden" name="command" value="login">
				<input type="submit" value="${logIn}" name="SignIn" class="mybutton">
			</td>
		</form>
			<td align="center">
				<form action="Controller" method="post">			
					<input type="hidden" name="command" value="go_to_registration">
					<input align="middle" type="submit" value="${registration}" name="Registration" class="mybutton">
				</form>
			</td>
		</tr>					
</table>
</body>
</html>