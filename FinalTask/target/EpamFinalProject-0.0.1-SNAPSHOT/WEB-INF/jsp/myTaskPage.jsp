<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${informationAboutTask}</title>
<link rel="stylesheet" type="text/css" href="myStyle.css">

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>

	<fmt:message bundle="${loc}" key="local.informationAboutTask" var="informationAboutTask"/>
	<fmt:message bundle="${loc}" key="local.backToMyTasks" var="backToMyTasks"/>
	<fmt:message bundle="${loc}" key="local.nameCourse" var="nameCourse"/>
	<fmt:message bundle="${loc}" key="local.leaveCourse" var="leaveCourse"/>
	<fmt:message bundle="${loc}" key="local.nameTask" var="nameTask"/>
	<fmt:message bundle="${loc}" key="local.summary" var="summary"/>
	<fmt:message bundle="${loc}" key="local.assignmentTime" var="assignmentTime"/>
	<fmt:message bundle="${loc}" key="local.deadline" var="deadline"/>
	<fmt:message bundle="${loc}" key="local.readMore" var="readMore"/>
	<fmt:message bundle="${loc}" key="local.executeTask" var="executeTask"/>
	<fmt:message bundle="${loc}" key="local.taskStatus" var="taskStatus"/>
	<fmt:message bundle="${loc}" key="local.result" var="result"/>
	<fmt:message bundle="${loc}" key="local.feedback" var="feedback"/>
	<fmt:message bundle="${loc}" key="local.mark" var="mark"/>
	<fmt:message bundle="${loc}" key="local.passed" var="PASSED"/>

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
<h1 id="HeaderIndexPage" >${informationAboutTask}</h1>
<c:choose>
	<c:when test="${task.taskStatus=='PASSED'}">
			<table id="tableCourses" align="center">
				<tr>
					<td colspan="5">
							${result}
					</td>

				</tr>
				<tr>
					<th>${feedback}</th>
					<th>${mark}</th>
				</tr>
				<tr>
					<td>${sessionScope.result.feedback}</td>
					<td>${sessionScope.result.mark}</td>
				</tr>
			</table>
	</c:when>
</c:choose>
<table id="tableCourses" align="center">
		<tr>
			<th>${nameTask}</th>
			<th>${summary}</th>
			<th>${assignmentTime}</th>
			<th>${deadline}</th>
			<th>${taskStatus}</th>
		</tr>

		<tr>
			<td>${task.nameTask}</td>
			<td>${task.summary}</td>
			<td>${task.assignmentTime}</td>
			<td>${task.deadline}</td>
			<td>${task.taskStatus}</td>
		</tr>


	<c:if test="${task.taskStatus=='NOT_COMPLETED'}">

		<tr>
			<td colspan="5">
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="go_to_execute_task">
					<input type="hidden" name="idTask" value="${task.idTask}">
					<input type="hidden" name="pageNumber" value="${pageNumber}">
					<input type="submit" name="executeTask" value="${executeTask}" class="mybutton">
				</form>
			</td>
		</tr>
	</c:if>
	<tr>
		<td colspan="5">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="show_my_course">
				<input type="hidden" name="pageNumber" value="${pageNumber}">
				<input type="hidden" name="idCourse" value="${idCourse}">
				<input type="submit" name="showTasks" value="${backToMyTasks}" class="mybutton">
			</form>
		</td>
	</tr>
</table>



</body>
</html>