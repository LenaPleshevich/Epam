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
<fmt:message bundle="${loc}" key="local.informationAboutCourse" var="informationAboutCourse"/>
	<fmt:message bundle="${loc}" key="local.maxNumberStudents" var="maxNumberStudents"/>
	<fmt:message bundle="${loc}" key="local.startDateOfCourse" var="startDateCourse"/>
	<fmt:message bundle="${loc}" key="local.endDateOfCourse" var="endDateCourse"/>
	<fmt:message bundle="${loc}" key="local.description" var="description"/>
	<fmt:message bundle="${loc}" key="local.nameCourse" var="nameCourse"/>
	<fmt:message bundle="${loc}" key="local.statusCourse" var="statusCourse"/>
	<fmt:message bundle="${loc}" key="local.myCourses" var="myCourses"/>
	<fmt:message bundle="${loc}" key="local.emptyListCourses" var="emptyListCourses"/>
	<fmt:message bundle="${loc}" key="local.readMore" var="readMore"/>
	<fmt:message bundle="${loc}" key="local.idTeacher" var="idTeacher"/>
	<fmt:message bundle="${loc}" key="local.go" var="go"/>
	<fmt:message bundle="${loc}" key="local.of" var="of"/>
	<fmt:message bundle="${loc}" key="local.backToHomePage" var="backToHomePage"/>
	<fmt:message bundle="${loc}" key="local.backToListCourse" var="backToListCourse"/>
	<fmt:message bundle="${loc}" key="local.idCourse" var="idCourse"/>
	<fmt:message bundle="${loc}" key="local.teacher" var="teacher"/>
	<fmt:message bundle="${loc}" key="local.assignTeacher" var="assignTeacher"/>
	<fmt:message bundle="${loc}" key="local.deleteCourse" var="deleteCourse"/>
	<fmt:message bundle="${loc}" key="local.changeCourse" var="changeCourse"/>
	<fmt:message bundle="${loc}" key="local.registrationOnCourse" var="registrationOnCourse"/>
	<fmt:message bundle="${loc}" key="local.courseAlreadyRegistration" var="courseAlreadyRegistration"/>
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
<table id="specialTableForCoursePage" align="center" >
	<tr>
		<c:choose>
			<c:when test="${sessionScope.isLogged== 'true'}">
				<c:choose>
					<c:when test="${sessionScope.user.idRoleUser== 2}">
						<c:choose>
							<c:when test="${isCheckingRegistration=='true'}">
								<td class="myfont">
										${courseAlreadyRegistration}
								</td>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${course.statusCourse=='набор'}">
										<td>
											<form action="Controller" method="post">
												<input type="hidden" name="command" value="go_to_registration_on_course">
												<input type="hidden" name="idCourse" value="${course.idCourse}">
												<input type="hidden" name="pageNumber" value="${numberOfPage}">
												<input type="submit" name="RegistrationOnCourse" value="${registrationOnCourse}" class="mybutton">
											</form>
										</td>
									</c:when>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:when test="${sessionScope.user.idRoleUser== 1}">
						<td>
							<form action="Controller" method="post">
								<input type="hidden" name="command" value="show_teachers">
								<input type="hidden" name="idCourse" value="${course.idCourse}">
								<input type="hidden" name="pageNumber" value="${1}">
								<input type="hidden" name="Assignment" value="${true}">
								<input type="submit" name="AssignmentTeacherOnCourse" value="${assignTeacher}" class="mybutton">
							</form>
						</td>
						<td>
							<form action="Controller" method="post">
								<input type="hidden" name="command" value="go_to_change_course">
								<input type="hidden" name="idCourse" value="${course.idCourse}">
								<input type="hidden" name="pageNumber" value="${numberOfPage}">
								<input type="submit" name="ChangeCourse" value="${changeCourse}" class="mybutton">
							</form>
						</td>
						<td>
							<form action="Controller" method="post">
								<input type="hidden" name="command" value="delete_course">
								<input type="hidden" name="idCourse" value="${course.idCourse}">
								<input type="hidden" name="pageNumber" value="${numberOfPage}">
								<input type="submit" name="DeleteCourse" value="${deleteCourse}" class="mybutton">
							</form>
						</td>
					</c:when>
				</c:choose>
			</c:when>
		</c:choose>
	</tr>
</table>
<table id="tableInfo" align="center">
	<tr>
		<th class="mybigfont" align="center">
		${course.nameCourse}
		</th>
	</tr>

	<tr>
		 <th class="myfont" align="center">
		 ${statusCourse}
		 </th>
	</tr>
	<tr>
		 <td class="myfont" align="center">
		 ${course.statusCourse}
		</td>
	</tr>
	<tr>
		<th class="myfont" align="center">
			${description}
		</th>
	</tr>
	<tr>
		<td class="myfont" align="center">
		${course.description}
		</td>
	</tr>
	<tr>
		<c:choose>
			<c:when test="${course.idTeacher > 0}">
				<td  class="myfont" align="center">
						${teacher}: ${sessionScope.teacher.firstNameUser} ${sessionScope.teacher.lastNameUser}
				</td>
			</c:when>

		</c:choose>
	</tr>
	<tr align="center">
		<td >
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="show_courses">
				<input type="hidden" name="pageNumber" value="${numberOfPage}">
				<input type="submit" name="showCourses" value="${backToListCourse}" class="mybutton">
			</form>
		</td>
	</tr>
</table>
</body>
</html>