<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><c:if test="${sessionScope.isLogged=='true'}">${sessionScope.user.firstNameUser} ${sessionScope.user.lastNameUser}, </c:if>Online Training</title>
<link rel="stylesheet" type="text/css" href="myStyle.css">

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.welcomeHomePage" var="welcomeHomePage"/>
<fmt:message bundle="${loc}" key="local.welcome" var="welcome"/>
<fmt:message bundle="${loc}" key="local.showMyCourses" var="showMyCourses"/>
<fmt:message bundle="${loc}" key="local.showCourses" var="showCourses"/>
<fmt:message bundle="${loc}" key="local.showUsers" var="showUsers"/>
<fmt:message bundle="${loc}" key="local.logOut" var="logOut"/>
<fmt:message bundle="${loc}" key="local.email" var="email"/>
<fmt:message bundle="${loc}" key="local.password" var="password"/>
<fmt:message bundle="${loc}" key="local.logIn" var="logIn"/>
<fmt:message bundle="${loc}" key="local.registration" var="registration"/>
<fmt:message bundle="${loc}" key="local.oneMessageAboutCompany" var="oneMessage"/>
<fmt:message bundle="${loc}" key="local.twoMessageAboutCompany" var="twoMessage"/>
<fmt:message bundle="${loc}" key="local.services" var="services"/>
<fmt:message bundle="${loc}" key="local.validateEmailMessage" var="validateEmailMessage"/>
<fmt:message bundle="${loc}" key="local.validatePasswordMessage" var="validatePasswordMessage"/>
<fmt:message bundle="${loc}" key="local.addCourse" var="addCourse"/>
<fmt:message bundle="${loc}" key="local.assignTeachers" var="assignTeachers"/>

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
    			if ( document.contact_form.password.value == ""){
    				alert ("${validatePasswordMessage}");
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

<h1 id="HeaderIndexPage" >${welcomeHomePage}</h1>
<table>
	<tr>
		<td>
			<c:choose>
				<c:when test="${sessionScope.isLogged=='true'}">
					<table id="tableLogin">
			    		<tr>
			    			<td class="myfont" align="center">
			    				${welcome}, <b>${sessionScope.user.firstNameUser} ${sessionScope.user.lastNameUser} </b>
			    			</td>
			    		</tr>   
			    		<c:choose>
			    			<c:when test="${sessionScope.user.idRoleUser== 2}">
				    			<tr>
				    				<td id="tdLogin">
				    					<form action="Controller" method="post">
				    						<input type="hidden" name="pageNumber" value="1">
				    						<input type="hidden" name="command" value="show_my_courses">
				    						<input type="submit" name="ShowMyOrders" value="${showMyCourses}" class="myNewButton">
				    					</form>
				    				</td>
				    			</tr>
					    	</c:when>
					    	<c:when test="${sessionScope.user.idRoleUser== 1}">
					    		<tr>
					    			<td id="tdLogin">
					    				<form action="Controller" method="post">
					    					<input type="hidden" name="pageNumber" value="1">
					    					<input type="hidden" name="command" value="show_users">
					    					<input type="submit" name="showUsers" value="${showUsers}" class="myNewButton">
					    				</form>
					    			</td>
					    		</tr>
					    		<tr>
					    			<td id="tdLogin">
					    				<form action="Controller" method="post">
					    					<input type="hidden" name="command" value="show_courses">
					    					<input type="hidden" name="pageNumber" value="1">
					    					<input type="submit" name="showCourses" value="${assignTeachers}" class="myNewButton">
					    				</form>
					    			</td>
					    		</tr>
					    		<tr>
					    			<td id="tdLogin">
					    				<form action="Controller" method="post">
					    					<input type="hidden" name="command" value="go_to_add_course">
					    					<input type="submit" name="addCourse" value="${addCourse}" class="myNewButton">
					    				</form>
					    			</td>
					    		</tr>
					    	</c:when>
			    		</c:choose>
			    		<tr>
			    			<td id="tdLogin">
			    				<form action="Controller" method="post">
			    					<input type="hidden" name="command" value="logOut">
			    					<input type="submit" name="LogOut" value="${ logOut}" class="myNewButton">
			    				</form>		
			    			</td>
			    		</tr>    			
			    	</table>    	
			    </c:when>
			<c:otherwise>		
				<table id="tableLogin">
					<form name="contact_form" method="post" action="Controller" onsubmit="return validate_form ( );">				 
						<tr>
							<td class="myfont">${ email }</td>
							<td align="center" id="tdLogin"><input class="mytext" type="text" name="email" value=""></td>
						</tr>
						<tr>
							<td class="myfont">${ password }</td>
							<td align="center" id="tdLogin"><input class="mytext" type="password" name="password" value=""></td>
						</tr>
						<tr>
							<td align="center" id="tdLogin">
								<input type="hidden" name="command" value="login">
								<input type="submit" value="${ logIn}" name="logIn" class="mybutton">
							</td>
						</form>
							<td align="center">
								<form action="Controller" method="post">			
									<input type="hidden" name="command" value="go_to_registration">
									<input align="middle" type="submit" value="${registration}" name="Registration" class="mybutton">
								</form>
							</td>
						</tr>					
					</table>				
				</c:otherwise>
			</c:choose>
		</td>
		<td rowspan="2" class="p">
			<p>	${oneMessage}</p>
			<p>	${twoMessage}</p>
						
		</td>
	</tr>
	<tr>
		<td>
			<table id="tableLogin">
				<tr>
					<td align="center" class="myfont">
						${services}
					</td>
				</tr>
				<tr>
					<td align="center">
						<form action="Controller" method="post">
							<input type="hidden" name="command" value="show_courses">
							<input type="hidden" name="pageNumber" value="1">
							<input type="submit" value="${showCourses}" name="ShowCourses" class="myNewButton">
						</form>	
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>