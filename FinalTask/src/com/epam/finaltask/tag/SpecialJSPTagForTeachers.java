package com.epam.finaltask.tag;

import com.epam.finaltask.controller.RequestParameterName;
import com.epam.finaltask.dao.entity.Course;
import com.epam.finaltask.dao.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * 	This class is an implementation of a teacher's tag
 */
public class SpecialJSPTagForTeachers extends TagSupport {
	private static final String PARAMETER_ADMIN = "1";
	private static final String PARAMETER_ASSIGN_TEACHER= "assignTeacher";
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getRootLogger();
	
	private JSPListBean list;
	
	public JSPListBean getList(){
		return list;
	}
	
	public void setList(JSPListBean list){
		this.list = list;
	}
	
	@Override
	public int doStartTag() throws JspException {
		int size = new Integer(list.getSize());
		try{
			JspWriter out = pageContext.getOut();
			HttpSession session = pageContext.getSession();
			User user;

			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
			int pageNumber = Integer.parseInt(request.getParameter(RequestParameterName.PAGE_NUMBER));
			String assignTeacher = pageContext.getAttribute(PARAMETER_ASSIGN_TEACHER).toString();
			for (int i = 0; i < size; i++){
				user = list.getElement();
				out.write("<tr>");
				out.write("<td>" + user.getEmailUser() + 		"</td>");
				out.write("<td>" + user.getFirstNameUser() + 	"</td>");
				out.write("<td>" + user.getLastNameUser() + 	"</td>");
				out.write("<td>" + user.getNameRole() + 	"</td>");
				if (session.getAttribute(RequestParameterName.ROLE).toString().equals(PARAMETER_ADMIN)){
					out.write("<td>");
					out.write("<form action=\"Controller\" method=\"post\">");
					out.write("<input type=\"hidden\" name=\"pageNumber\" value=\"" + pageNumber + "\">");
					out.write("<input type=\"hidden\" name=\"command\" value=\"assign_teacher\">");
					out.write("<input type=\"hidden\" name=\"idUser\" value=\"" + user.getIdUser() +"\">");
					out.write("<input type=\"submit\" name=\"assignTeacher\" value=\"" + assignTeacher + "\" class=\"mybutton\">");
					out.write("</form>");
					out.write("</td>");
				}
				out.write("</tr>");
			}
		} catch (IOException e){
			logger.error("IOException is thrown when run teacher's tag", e);
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}
}
