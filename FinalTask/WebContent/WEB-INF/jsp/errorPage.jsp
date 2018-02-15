<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${errorPage}</title>
<link rel="stylesheet" type="text/css" href="myStyle.css">
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.backToHomePage" var="backToHomePage"/>
<fmt:message bundle="${loc}" key="local.happenedError" var="happenedError"/>
<fmt:message bundle="${loc}" key="local.errorPage" var="errorPage"/>
<fmt:message bundle="${loc}" key="local.messageAboutError" var="messageAboutError"/>
<fmt:message bundle="${loc}" key="local.messageAboutIncorrectAction" var="messageAboutIncorrectAction"/>
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
<form action="Controller" method="post">
	<table id="tableLoginOnFailPage">
		<tr>
			<td class="myfont" align="center">
				<c:choose>
					<c:when test="${messageIncorrectAction=='true'}">
						${messageAboutIncorrectAction}
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${messageAboutError=='true'}">
								${messageAboutError}
					    	</c:when>
							<c:otherwise>		
								${happenedError}	
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>				
			</td>
		</tr>
		<tr>
			<td align="center">
				<input type="hidden" name="command" value="back_to_home_page">
				<input type="submit" name="BackToTheHomePage" value="${backToHomePage}" class="mybutton">
			</td>
		</tr>
	</table>
</form>	
</body>
</html>