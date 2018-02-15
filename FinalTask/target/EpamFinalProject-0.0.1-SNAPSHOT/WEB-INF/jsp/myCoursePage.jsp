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
	<fmt:message bundle="${loc}" key="local.addTask" var="addTask"/>
	<fmt:message bundle="${loc}" key="local.informationAboutCourse" var="informationAboutCourse"/>
	<fmt:message bundle="${loc}" key="local.backToMyCourses" var="backToMyCourses"/>
	<fmt:message bundle="${loc}" key="local.nameCourse" var="nameCourse"/>
	<fmt:message bundle="${loc}" key="local.leaveCourse" var="leaveCourse"/>
	<fmt:message bundle="${loc}" key="local.nameTask" var="nameTask"/>
	<fmt:message bundle="${loc}" key="local.idTask" var="idTask"/>
	<fmt:message bundle="${loc}" key="local.idCourse" var="idCourse"/>
	<fmt:message bundle="${loc}" key="local.summary" var="summary"/>
	<fmt:message bundle="${loc}" key="local.assignmentTime" var="assignmentTime"/>
	<fmt:message bundle="${loc}" key="local.deadline" var="deadline"/>
	<fmt:message bundle="${loc}" key="local.readMore" var="readMore"/>
	<fmt:message bundle="${loc}" key="local.statusCourse" var="statusCourse"/>
	<fmt:message bundle="${loc}" key="local.go" var="go"/>
	<fmt:message bundle="${loc}" key="local.of" var="of"/>
	<fmt:message bundle="${loc}" key="local.messageIncorrectNumberPage" var="messageIncorrectNumberPage"/>
	<fmt:message bundle="${loc}" key="local.emptyListTasks" var="emptyListTasks"/>

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
		}//-->
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
<h1 id="HeaderIndexPage" >${name}</h1>
<table id="tableForPanel" align="center">
	<tr>
			<c:choose>
				<c:when test="${sessionScope.user.idRoleUser== 2}">
					<td colspan="5">
						<form action="Controller" method="post">
							<input type="hidden" name="command" value="leave_course">
							<input type="hidden" name="idCourse" value="${sessionScope.course.idCourse}">
							<input type="hidden" name="pageNumber" value="${currentNumberPage}">
							<input type="submit" name="leaveCourse" value="${leaveCourse}" class="mybutton">
						</form>
					</td>
				</c:when>
				<c:when test="${sessionScope.user.idRoleUser== 3}">
					<td colspan="5">
						<form action="Controller" method="post">
							<input type="hidden" name="pageNumber" value="${currentNumberPage}">
							<input type="hidden" name="command" value="go_to_add_task">
							<input type="hidden" name="idCourse" value="${sessionScope.course.idCourse}">
							<input type="submit" name="addTask" value="${addTask}" class="mybutton">
						</form>
					</td>

				</c:when>
			</c:choose>
	</tr>
</table>
<c:choose>
	<c:when test="${tasks.size() > 0}">

		<table id="tableCourses" align="center">
			<tr>
				<th>${nameTask}</th>
				<th>${summary}</th>
				<th>${assignmentTime}</th>
				<th>${deadline}</th>
			</tr>
			<c:forEach var="task" items="${tasks}">
				<tr>
					<td>${task.nameTask}</td>
					<td>${task.summary}</td>
					<td>${task.assignmentTime}</td>
					<td>${task.deadline}</td>
					<td>
						<c:choose>
							<c:when test="${sessionScope.user.idRoleUser== 2}">
								<form action="Controller" method="post">
									<input type="hidden" name="pageNumber" value="${currentNumberPage}">
									<input type="hidden" name="command" value="show_my_task">
									<input type="hidden" name="idTask" value="${task.idTask}">
									<input type="hidden" name="idCourse" value="${sessionScope.course.idCourse}">
									<input type="submit" name="showTasks" value="${readMore}" class="mybutton">
								</form>
							</c:when>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="5">
					<form name="contact_form" action="Controller" method="post" onsubmit="return validate_form ( );" >
						<input type="hidden" name="command" value="show_my_course">
						<input type="hidden" name="idCourse" value="${sessionScope.course.idCourse}">
						<input type="text" name="pageNumber" value="${currentNumberPage}" class="mynumber">
						<input type="hidden" name="numberOfPage" value="${numberOfPage}">
							${of} ${numberOfPage}
						<input type="submit" name="showMyTasks" value="${go}" class="mybutton">
					</form>
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<form action="Controller" method="post">
						<input type="hidden" name="command" value="show_my_courses">
						<input type="submit" name="showCourses" value="${ backToMyCourses}" class="mybutton">
						<input type="hidden" name="pageNumber" value="1">
					</form>
				</td>
			</tr>
		</table>
	</c:when>
	<c:otherwise>
		<h2>${emptyListTasks}</h2>
		<form action="Controller" method="post">
			<table id="specialTable">
				<tr>
					<td colspan="5">
						<input type="hidden" name="command" value="show_my_courses">
						<input type="submit" name="showCourses" value="${ backToMyCourses}" class="mybutton">
						<input type="hidden" name="pageNumber" value="${1}">

					</td>
				</tr>
			</table>
		</form>
	</c:otherwise>
</c:choose>
</body>
</html>