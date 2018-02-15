<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${informationAboutTour}</title>
<link rel="stylesheet" type="text/css" href="myStyle.css">

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
	<fmt:message bundle="${loc}" key="local.maxNumberStudents" var="maxNumberStudents"/>
	<fmt:message bundle="${loc}" key="local.startDateOfCourse" var="startDateCourse"/>
	<fmt:message bundle="${loc}" key="local.endDateOfCourse" var="endDateCourse"/>
	<fmt:message bundle="${loc}" key="local.description" var="description"/>
	<fmt:message bundle="${loc}" key="local.validateStartDateMessage" var="validateStartDateMessage"/>
	<fmt:message bundle="${loc}" key="local.validateEndDateMessage" var="validateEndDateMessage"/>
	<fmt:message bundle="${loc}" key="local.validateMaxNumberStudentsMessage" var="validateMaxNumberStudentsMessage"/>
	<fmt:message bundle="${loc}" key="local.validateDescriptionMessage" var="validateDescriptionMessage"/>
	<fmt:message bundle="${loc}" key="local.validateNameCourseMessage" var="validateNameCourseMessage"/>
	<fmt:message bundle="${loc}" key="local.nameCourse" var="nameCourse"/>
	<fmt:message bundle="${loc}" key="local.statusCourse" var="statusCourse"/>
	<fmt:message bundle="${loc}" key="local.changeCourse" var="changeCourse"/>
	<fmt:message bundle="${loc}" key="local.backToListCourse" var="backToListCourse"/>

<script type="text/javascript">
<!--

function validate_form ( ) {
	valid = true;
	if ( document.contact_form.start_date.value == ""){
		alert ("${validateStartDateMessage}");
		valid = false;
	} else {
		var regexpDate = /^\d{2}-\d{2}-\d{4}$/
		if (!regexpDate.test(document.contact_form.start_date.value)){
		alert ("${validateStartDateMessage}");
		valid = false;
		} else {
			if ( document.contact_form.end_date.value == ""){
				alert ("${validateEndDateMessage}");
				valid = false;
			} else {
				if (!regexpDate.test(document.contact_form.end_date.value)){
					alert ("${validateEndDateMessage}");
					valid = false;
				} else {
					if ( document.contact_form.students_number.value == ""){
						alert ("${validateMaxNumberStudentsMessage}");
						valid = false;
					} else {
						if ( document.contact_form.students_number.value <= 0){
							alert ("${validateMaxNumberStudentsMessage}");
							valid = false;
						} else {
							if ( document.contact_form.description.value == ""){
								alert ("${validateDescriptionMessage}");
								valid = false;
							} else {
								if ( document.contact_form.name.value == ""){
									alert("${validateNameCourseMessage}");
									valid = false;
								} else {
									var regexpNameCourse = "[a-zA-Z]+";
									if (!regexpNameCourse.test(document.contact_form.name.value)){
										alert("${validateNameCourseMessage}");
										valid = false;
									} else {
										if ( document.contact_form.status.value == ""){
											alert("${validateStatusCourseMessage}");
											valid = false;
										}
									}
								}
							}
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
<h1 id="HeaderIndexPage" >${changeCourse}</h1>
<table id="tableCourses" align="center">
	<tr>
		<td colspan="6">
			${nameCourse} - ${course.nameCourse}
		</td>
	</tr>
	<form name="contact_form" method="post" action="Controller" onsubmit="return validate_form ( );">
		<tr>
			<td align="center">
			 	${startDateCourse}: <input type="text" name="start_date" value="${course.startDateCourse}" class="mytext">
			</td>
			<td align="center">
			${endDateCourse}: <input type="text" name="end_date" value="${course.endDateCourse}" class="mytext">
			</td>
		</tr>
		<tr>
			<td align="center">
				${maxNumberStudents}:<input type="text" name="students_number" value="${course.maxNumberStudentsCourse}"class="mytext">
			</td>
			<td align="center">
				${statusCourse}: <input type="text" name="status" value="${course.statusCourse}" class="mytext">
			</td>
		</tr>
		<tr>
			<td colspan="6">
				${description}: <input type="text" name="description" value="${course.description}" class="mybigtext">
			</td>
		</tr>
		<tr>
			<td colspan="6">
				<input type="hidden" name="command" value="change_course">
				<input type="hidden" name="idCourse" value="${course.idCourse}">
				<input type="hidden" name="pageNumber" value="${pageNumber}">
				<input type="submit" name="changeCourse" value="${changeCourse}" class="mybutton">
			</td>
		</tr>
	</form>
	<tr>
		<td colspan="6">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="show_courses">
				<input type="hidden" name="pageNumber" value="${pageNumber}">
				<input type="submit" name="showCourses" value="${backToListCourse}" class="mybutton">
			</form>
		</td>
	</tr>
</table>
</body>
</html>