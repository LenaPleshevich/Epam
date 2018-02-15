<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="mytag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${listOfResponses}</title>
<link rel="stylesheet" type="text/css" href="myStyle.css">

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.listOfResponses" var="listOfResponses"/>
<fmt:message bundle="${loc}" key="local.readMore" var="readMore"/>
<fmt:message bundle="${loc}" key="local.go" var="go"/>
<fmt:message bundle="${loc}" key="local.of" var="of"/>
<fmt:message bundle="${loc}" key="local.messageIncorrectNumberPage" var="messageIncorrectNumberPage"/>
<fmt:message bundle="${loc}" key="local.backToHomePage" var="backToHomePage"/>
	<fmt:message bundle="${loc}" key="local.nameCourse" var="nameCourse"/>
	<fmt:message bundle="${loc}" key="local.nameTask" var="nameTask"/>
	<fmt:message bundle="${loc}" key="local.checkTask" var="checkTask"/>
	<fmt:message bundle="${loc}" key="local.emptyListResponses" var="emptyListResponses"/>
	<fmt:message bundle="${loc}" key="local.firstName" var="firstName"/>
	<fmt:message bundle="${loc}" key="local.lastName" var="lastName"/>
<script type="text/javascript">
<!--

function validate_form ( )
{
	valid = true;

        if ( document.contact_form.pageNumber.value == ""){
        	alert ("${messageIncorrectNumberPage}");
            valid = false;
        } else {
        	var regexpNumber = /^\d+$/;
        	if (!regexpNumber.test(document.contact_form.pageNumber.value)){
    			alert ("${messageIncorrectNumberPage}");
    			valid = false;
    		} else {
    			if (document.contact_form.pageNumber.value < 1){
    				alert ("${messageIncorrectNumberPage}");
        			valid = false;
    			} else {
    				if (document.contact_form.pageNumber.value > document.contact_form.numberOfPage.value){
        				alert ("${messageIncorrectNumberPage}");
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
<h1 id="HeaderIndexPage" >${listOfResponses}</h1>

<c:choose>
	<c:when test="${responses.size() > 0}">
		<table id="tableCourses" align="center">
			<tr>
				<th>${nameTask}</th>
				<th>${firstName}</th>
				<th>${lastName}</th>
			</tr>
			<c:forEach var="response" items="${responses}">
				<tr>
					<td>${response.task.nameTask }</td>
					<td>${response.user.firstNameUser}</td>
					<td>${response.user.lastNameUser}</td>
					<td>
						<form action="Controller" method="post">
							<input type="hidden" name="pageNumber" value="${currentNumberPage}">
							<input type="hidden" name="command" value="go_to_add_result">
							<input type="hidden" name="idResponse" value="${response.idResponse}">
							<input type="submit" name="checkTask" value="${checkTask}" class="mybutton">
						</form>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4">
					<form name="contact_form" action="Controller" method="post" onsubmit="return validate_form ( );" >
						<input type="hidden" name="command" value="show_my_courses">
						<input type="text" name="pageNumber" value="${currentNumberPage}" class="mynumber">
						<input type="hidden" name="numberOfPage" value="${numberOfPage}">
							${of} ${numberOfPage}
						<input type="submit" name="showMyCourses" value="${go}" class="mybutton">
					</form>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<form action="Controller" method="post">
						<input type="hidden" name="command" value="back_to_home_page">
						<input type="submit" name="BackToTheHomePage" value="${ backToHomePage}" class="mybutton">
					</form>
				</td>
			</tr>
		</table>
	</c:when>
	<c:otherwise>
		<h2>${emptyListResponses}</h2>
		<form action="Controller" method="post">
			<table id="specialTable">
				<tr>
					<td>
						<input type="hidden" name="command" value="back_to_home_page">
						<input type="submit" name="BackToTheHomePage" value="${ backToHomePage}" class="mybutton">
					</td>
				</tr>
			</table>
		</form>
	</c:otherwise>
</c:choose>

</body>
</html>