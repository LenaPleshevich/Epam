<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="mytag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${listOfTeachers}</title>
<link rel="stylesheet" type="text/css" href="myStyle.css">
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.listOfTeachers" var="listOfTeachers"/>
<fmt:message bundle="${loc}" key="local.readMore" var="readMore"/>
<fmt:message bundle="${loc}" key="local.emptyListTeachers" var="emptyListTeachers"/>
<fmt:message bundle="${loc}" key="local.go" var="go"/>
<fmt:message bundle="${loc}" key="local.of" var="of"/>
<fmt:message bundle="${loc}" key="local.messageIncorrectNumberPage" var="messageIncorrectNumberPage"/>
<fmt:message bundle="${loc}" key="local.backToHomePage" var="backToHomePage"/>
<fmt:message bundle="${loc}" key="local.firstName" var="firstName"/>
<fmt:message bundle="${loc}" key="local.lastName" var="lastName"/>
<fmt:message bundle="${loc}" key="local.email" var="email"/>
<fmt:message bundle="${loc}" key="local.assignTeacher" var="assignTeacher"/>
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
<h1 id="HeaderIndexPage" >${listOfTeachers}</h1>

<c:choose>
	<c:when test="${teachers.size() > 0}">
		<table id="tableCourses" align="center">
			<tr>
				<th>${firstName}</th>
				<th>${lastName}</th>
				<th>${email}</th>
			</tr>
			<c:forEach var="teacher" items="${teachers}">
				<tr>
					<td>${teacher.firstNameUser }</td>
					<td>${teacher.lastNameUser }</td>
					<td>${teacher.emailUser}</td>
					<td>
						<form action="Controller" method="post">
							<input type="hidden" name="pageNumber" value="${currentNumberPage}">
							<input type="hidden" name="command" value="assign_teacher">
							<input type="hidden" name="idCourse" value="${idCourse}">
							<input type="hidden" name="idTeacher" value="${teacher.idUser}">
							<input type="submit" name="assignTeacher" value="${assignTeacher}" class="mybutton">
						</form>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4">
					<form name="contact_form" action="Controller" method="post" onsubmit="return validate_form ( );" >
						<input type="hidden" name="command" value="show_teachers">
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
		<h2>${emptyListTeachers}</h2>
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