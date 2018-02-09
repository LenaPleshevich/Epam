<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${registrationPage}</title>
<link rel="stylesheet" type="text/css" href="myStyle.css">
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.registration" var="registration"/>
<fmt:message bundle="${loc}" key="local.email" var="email"/>
<fmt:message bundle="${loc}" key="local.password" var="password"/>
<fmt:message bundle="${loc}" key="local.passwordAgain" var="passwordAgain"/>
<fmt:message bundle="${loc}" key="local.firstName" var="firstName"/>
<fmt:message bundle="${loc}" key="local.lastName" var="lastName"/>
<fmt:message bundle="${loc}" key="local.backToHomePage" var="backToHomePage"/>
<fmt:message bundle="${loc}" key="local.validateEmailMessage" var="validateEmailMessage"/>
<fmt:message bundle="${loc}" key="local.validatePasswordMessage" var="validatePasswordMessage"/>
<fmt:message bundle="${loc}" key="local.validateDontMatchPassword" var="validateDontMatchPassword"/>
<fmt:message bundle="${loc}" key="local.validateFirstNameMessage" var="validateFirstNameMessage"/>
<fmt:message bundle="${loc}" key="local.validateLastNameMessage" var="validateLastNameMessage"/>
<fmt:message bundle="${loc}" key="local.messageBusyEmail" var="validateMessageBusyEmail"/>
<fmt:message bundle="${loc}" key="local.messageIncorrectNames" var="validateMessageIncorrectNames"/>
<fmt:message bundle="${loc}" key="local.registrationPage" var="registrationPage"/>
<script type="text/javascript">
<!--

function validate_form ( )
{
	valid = true;

        if ( document.contact_form.email.value == ""){
        	alert ("${validateEmailMessage}");
            valid = false;
        } else {
        	var regexpEmail = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
    		if (!regexpEmail.test(document.contact_form.email.value)){
    			alert ("${validateEmailMessage}");
    			valid = false;
    		} else {
    			if ( document.contact_form.firstPassword.value == ""){
    				alert ("${validatePasswordMessage}");
    				valid = false;
    			} else {
    				if(document.contact_form.firstPassword.value != document.contact_form.secondPassword.value){
						alert ("${validateDontMatchPassword}");
						valid = false;
					} else {
						if (document.contact_form.firstName.value == ""){
							alert ("${validateFirstNameMessage}");
							valid = false;
						} else {
							if(document.contact_form.lastName.value == ""){
								alert("${validateLastNameMessage}");
								valid = false;
							}
						}
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
<h1 id="HeaderIndexPage">${registration}</h1>
<table id="tableRegistration">
	<form name="contact_form" method="post" action="Controller" onsubmit="return validate_form ( );">	
		<th colspan="2" class="myfont">
			<c:if test="${messageBusyEmail=='true'}">${validateMessageBusyEmail}</c:if>
			<c:if test="${messageIncorrectPassword=='true'}">${validateDontMatchPassword}</c:if>
			<c:if test="${messageIncorrectNames=='true'}">${validateMessageIncorrectNames}</c:if>
		</th>
		<tr>
			<td class="myfont">
				${email}
			</td>
			<td align="center">
				<input type="text" name="email" class="mytext">
			</td>
		</tr>
		<tr>
			<td class="myfont">
				${password}
			</td>
			<td align="center">
				<input type="password" name="firstPassword" class="mytext">
			</td>
		</tr>
		<tr>
			<td class="myfont">
				${passwordAgain}
			</td>
			<td align="center">
				<input type="password" name="secondPassword" value="" class="mytext">
			</td>
		</tr>
		<tr>
			<td class="myfont">
				${firstName}
			</td>
			<td align="center">
			 <input type="text" name="firstName" value="" class="mytext">
			</td>
		</tr>
		<tr>
			<td class="myfont">
				${lastName}
			</td>
			<td align="center">
				<input type="text" name="lastName" value="" class="mytext">
			</td>
		</tr>
		<tr>
			<td align="center">
				<input type="hidden" name="command" value="registration_user">
				<input type="submit" name="Registration" value="${ registration}" class="mybutton">
			</td>		
	</form>
			<td align="center">
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="back_to_home_page">
					<input type="submit" name="BackToTheHomePage" value="${ backToHomePage}" class="mybutton">
				</form>
			</td>	
	</tr>
</table>
</body>
</html>