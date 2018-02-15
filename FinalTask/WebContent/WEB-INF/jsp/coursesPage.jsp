<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="mytag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${listOfCourses}</title>
<link rel="stylesheet" type="text/css" href="myStyle.css">
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.listOfCourses" var="listOfCourses"/>
<fmt:message bundle="${loc}" key="local.readMore" var="readMore"/>
<fmt:message bundle="${loc}" key="local.assignTeacher" var="assignTeacher"/>
<fmt:message bundle="${loc}" key="local.registrationOnCourse" var="registrationOnCourse"/>
<fmt:message bundle="${loc}" key="local.go" var="go"/>
<fmt:message bundle="${loc}" key="local.of" var="of"/>
<fmt:message bundle="${loc}" key="local.messageIncorrectNumberPage" var="messageIncorrectNumberPage"/>
<fmt:message bundle="${loc}" key="local.backToHomePage" var="backToHomePage"/>
<fmt:message bundle="${loc}" key="local.maxNumberStudents" var="maxNumberStudents"/>
<fmt:message bundle="${loc}" key="local.startDateOfCourse" var="startDateCourse"/>
<fmt:message bundle="${loc}" key="local.endDateOfCourse" var="endDateCourse"/>
<fmt:message bundle="${loc}" key="local.description" var="description"/>
<fmt:message bundle="${loc}" key="local.nameCourse" var="nameCourse"/>
<fmt:message bundle="${loc}" key="local.statusCourse" var="statusCourse"/>
<fmt:message bundle="${loc}" key="local.idCourse" var="idCourse"/>
<fmt:message bundle="${loc}" key="local.emptyListCourses" var="emptyListCourses"/>

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
<h1 id="HeaderIndexPage" >${listOfCourses}</h1>
<c:choose>
	<c:when test="${courses.size() > 0}">
		<table id="tableCourses" align="center">
			<tr>
				<th>${nameCourse}</th>
				<th>${statusCourse}</th>
				<th>${startDateCourse}</th>
				<th>${endDateCourse}</th>
				<th>${maxNumberStudents}</th>
				<th>${description}</th>
			</tr>
			<c:forEach var="course" items="${courses}">
				<tr>
					<td>${course.nameCourse }</td>
					<td>${course.statusCourse }</td>
					<td>${course.startDateCourse}</td>
					<td>${course.endDateCourse}</td>
					<td>${course.maxNumberStudentsCourse}</td>
					<td>${course.description}</td>
					<td>
						<form action="Controller" method="post">
							<input type="hidden" name="pageNumber" value="${currentNumberPage}">
							<input type="hidden" name="isLogged" value="${sessionScope.isLogged}">
							<input type="hidden" name="command" value="show_course">
							<input type="hidden" name="idCourse" value="${course.idCourse}">
							<input type="submit" name="showCourse" value="${readMore}" class="mybutton">
						</form>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="6">
					<form name="contact_form" action="Controller" method="post" onsubmit="return validate_form ( );" >
						<input type="hidden" name="command" value="show_courses">
						<input type="text" name="pageNumber" value="${currentNumberPage}" class="mynumber">
						<input type="hidden" name="numberOfPage" value="${numberOfPage}">
							${of} ${numberOfPage}
						<input type="submit" name="showMyCourses" value="${go}" class="mybutton">
					</form>
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<form action="Controller" method="post">
						<input type="hidden" name="command" value="back_to_home_page">
						<input type="submit" name="BackToTheHomePage" value="${ backToHomePage}" class="mybutton">
					</form>
				</td>
			</tr>
		</table>
	</c:when>
	<c:otherwise>
		<h2>${emptyListCourses}</h2>
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