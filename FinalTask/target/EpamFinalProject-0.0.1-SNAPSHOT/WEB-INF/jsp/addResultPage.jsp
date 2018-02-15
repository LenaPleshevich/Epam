<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${checkTask}</title>
<link rel="stylesheet" type="text/css" href="myStyle.css">
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.backToHomePage" var="backToHomePage"/>
	<fmt:message bundle="${loc}" key="local.backToListResponse" var="backToListResponse"/>
	<fmt:message bundle="${loc}" key="local.mark" var="mark"/>
	<fmt:message bundle="${loc}" key="local.feedback" var="feedback"/>
	<fmt:message bundle="${loc}" key="local.summary" var="summary"/>
	<fmt:message bundle="${loc}" key="local.nameTask" var="nameTask"/>
	<fmt:message bundle="${loc}" key="local.checkTask" var="checkTask"/>
	<fmt:message bundle="${loc}" key="local.addResult" var="addResult"/>
	<fmt:message bundle="${loc}" key="local.response" var="response"/>
<fmt:message bundle="${loc}" key="local.validateMarkMessage" var="validateMarkMessage"/>
<fmt:message bundle="${loc}" key="local.validateFeedbackMessage" var="validateFeedbackMessage"/>


<script type="text/javascript">
<!--

function validate_form ( )
{
	valid = true;
	if ( document.contact_form.feedback.value == ""){
		alert ("${validateFeedbackMessage}");
        valid = false;
	} else {
		if ( document.contact_form.mark.value == ""){
			alert ("${validateMarkMessage}");
			valid = false;
		} else {
			if ( document.contact_form.mark.value <= 0){
				alert ("${validateMarkMessage}");
				valid = false;
			} else {
				if ( document.contact_form.mark.value > 10){
					alert ("${validateMarkMessage}");
					valid = false;
				}
			}
		}
	}
	return valid;
}

//-->
</script>

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
<h1 id="HeaderIndexPage">${addResult}</h1>
<table id="tableRegistration">
	<form name="contact_form" method="post" action="Controller" onsubmit="return validate_form ( );">
		<tr>
			<td class="myfont">${nameTask}</td>
			<td class="myfont">${task.nameTask}</td>
		</tr>
		<tr>
			<td class="myfont">${summary}</td>
			<td class="myfont">${task.summary}</td>
		</tr>
		<tr>
			<td class="myfont">${response}</td>
			<td class="myfont">${sessionScope.response.text}</td>
		</tr>
		<tr>
			<td class="myfont">
				${mark}
			</td>
			<td align="center">
				<input type="text" name="mark" class="mytext">
			</td>
		</tr>
		<tr>
			<td class="myfont">
				${feedback}
			</td>
			<td align="center">
				<input type="text" name="feedback" class="mytext">
			</td>
		</tr>
		<tr>
			<td align="center">

				<input type="hidden" name="command" value="add_result">
				<input type="hidden" name="idResponse" value="${idResponse}">
				<input type="submit" name="addTask" value="${checkTask}" class="mybutton">
			</td>		
	</form>
			<td align="center">
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="back_to_home_page">
					<input type="submit" name="BackToHomePage" value="${backToHomePage}" class="mybutton">
				</form>
			</td>	
	</tr>
</table>
</body>
</html>