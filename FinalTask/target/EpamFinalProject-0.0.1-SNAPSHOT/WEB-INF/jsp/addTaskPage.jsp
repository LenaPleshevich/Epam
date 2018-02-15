<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${addTask}</title>
<link rel="stylesheet" type="text/css" href="myStyle.css">
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.backToHomePage" var="backToHomePage"/>
<fmt:message bundle="${loc}" key="local.addTask" var="addTask"/>

	<fmt:message bundle="${loc}" key="local.backToMyTasks" var="backToMyTasks"/>
	<fmt:message bundle="${loc}" key="local.summary" var="summary"/>
	<fmt:message bundle="${loc}" key="local.assignmentTime" var="assignmentTime"/>
	<fmt:message bundle="${loc}" key="local.deadline" var="deadline"/>
	<fmt:message bundle="${loc}" key="local.nameTask" var="nameTask"/>
<fmt:message bundle="${loc}" key="local.validateStartDateMessage" var="validateStartDateMessage"/>
<fmt:message bundle="${loc}" key="local.validateEndDateMessage" var="validateEndDateMessage"/>
<fmt:message bundle="${loc}" key="local.validateMaxNumberStudentsMessage" var="validateMaxNumberStudentsMessage"/>
<fmt:message bundle="${loc}" key="local.validateDescriptionMessage" var="validateDescriptionMessage"/>
<fmt:message bundle="${loc}" key="local.validateNameCourseMessage" var="validateNameCourseMessage"/>


<script type="text/javascript">
<!--

function validate_form ( )
{
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
<h1 id="HeaderIndexPage">${addCourse}</h1>
<table id="tableRegistration">
	<form name="contact_form" method="post" action="Controller" onsubmit="return validate_form ( );">	
		<tr>
			<td class="myfont">
				${nameTask}
			</td>
			<td align="center">
				<input type="text" name="nameTask" class="mytext">
			</td>
		</tr>
		<tr>
			<td class="myfont">
				${assignmentTime}
			</td>
			<td align="center">
				<input type="text" name="assignmentTime" class="mytext" placeholder="DD-MM-YYYY">
			</td>
		</tr>
		<tr>
			<td class="myfont">
				${deadline}
			</td>
			<td align="center">
				<input type="text" name="deadline" class="mytext" placeholder="DD-MM-YYYY">
			</td>
		</tr>
		<tr>
			<td class="myfont">
				${summary}
			</td>
			<td align="center">
			 <input type="text" name="summary" class="mybigtext">
			</td>
		</tr>

		<tr>
			<td align="center">
				<input type="hidden" name="command" value="add_task">
				<input type="submit" name="addTask" value="${addTask}" class="mybutton">
			</td>		
	</form>
			<td align="center">
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="show_teachers_courses">
					<input type="hidden" name="pageNumber" value="${1}">
					<input type="hidden" name="idCourse" value="${sessionScope.idCourse}">
					<input type="submit" name="BackToMyTasks" value="${backToMyTasks}" class="mybutton">
				</form>
			</td>	
	</tr>
</table>
</body>
</html>