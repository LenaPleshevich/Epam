<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${informationAboutCourse}</title>
<link rel="stylesheet" type="text/css" href="myStyle.css">

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.historyOfTeachers" var="historyOfTeachers"/>
<fmt:message bundle="${loc}" key="local.emptyListTeachers" var="emptyListTeachers"/>
<fmt:message bundle="${loc}" key="local.course" var="course"/>
<fmt:message bundle="${loc}" key="local.currentTeacher" var="currentTeacher"/>
<fmt:message bundle="${loc}" key="local.messageIncorrectIdTeacher" var="messageIncorrectIdTeacher"/>
<fmt:message bundle="${loc}" key="local.nameCourse" var="nameCourse"/>
<fmt:message bundle="${loc}" key="local.statusCourse" var="statusCourse"/>
<fmt:message bundle="${loc}" key="local.informationAboutCourse" var="informationAboutCourse"/>
<fmt:message bundle="${loc}" key="local.maxNumberStudents" var="maxNumberStudents"/>
<fmt:message bundle="${loc}" key="local.startDateOfCourse" var="startDateCourse"/>
<fmt:message bundle="${loc}" key="local.endDateOfCourse" var="endDateCourse"/>
<fmt:message bundle="${loc}" key="local.description" var="description"/>
<fmt:message bundle="${loc}" key="local.assignCourse" var="assignCourse"/>
<fmt:message bundle="${loc}" key="local.assign" var="assign"/>
<fmt:message bundle="${loc}" key="local.backToListCourse" var="backToListCourse"/>


<script type="text/javascript">
<!--

function validate_form ( )
{
	valid = true;

        if ( document.contact_form.idTeacher.value == ""){
        	alert ("${messageIncorrectIdTeacher}");
            valid = false;
        } else {
        	var regexpNumber = /^\d+$/;
        	if (!regexpNumber.test(document.contact_form.idTeacher.value)){
    			alert ("${messageIncorrectIdTeacher}");
    			valid = false;
    		} else {
    			if (document.contact_form.idTeacher.value < 0){
    				alert ("${messageIncorrectIdTeacher}");
        			valid = false;
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
<h1 id="HeaderIndexPage" >${informationAboutCourse}</h1>
<table id="tableCourses" align="center">
	<tr>
		<td>
		 	${nameCourse}:
		</td>
		<td>
			${course.nameCourse}
		</td>
	</tr>
	<tr>
		<td>
			${statusCourse}:
		</td>
		<td>
			${course.statusCourse}
		</td>
	</tr>
	<tr>
		<td>
			${maxNumberStudents}:
		</td>
		<td>
			${maxNumberStudents}
		</td>
	</tr>
	<tr>
		<td>
			${currentTeacher}:
		</td>
		<td>
			${course.idCourse}%
		</td>
	</tr>
	<c:choose>
		<c:when test="${size > 0}">
			<tr>
				<td>
					${historyOfTeachers}:
				</td>
			</tr>
			<c:forEach var="teacher" items="${teachers}">
				<tr>
					<td>
					</td>
					<td>
						${teacher}%
					</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td>
					${emptyListTeachers}
				</td>
			</tr>
		</c:otherwise>
	</c:choose>
	<tr>
		<td>
			${assignTeacher}
		</td>
		<td>
			<form name="contact_form" action="Controller" method="post" onsubmit="return validate_form ( );" >
				<input type="hidden" name="command" value="assign_teacher">
				<input type="hidden" name="idCourse" value="${course.idCourse}">
				<input type="hidden" name="pageNumber" value="${pageNumber}">
				<input type="text" name="idTeacher" class="mytext">
				<input type="submit" name="assignTeacher" value="${assign}" class="mybutton">
			</form>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="show_courses">
				<input type="hidden" name="pageNumber" value="${pageNumber}">
				<input type="submit" name="showUsers" value="${backToListUser}" class="mybutton">
			</form>			
		</td>
	</tr>
</table>
</body>
</html>