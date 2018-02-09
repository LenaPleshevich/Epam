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
 * 	This class is an implementation of a course's tag
 */
public class SpecialJSPTagForCourse extends TagSupport {
	private static final String PARAMETER_READ_MORE= "readMore";
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getRootLogger();
	
	private JSPListCourseBean list;
	
	public JSPListCourseBean getList(){
		return list;
	}
	
	public void setList(JSPListCourseBean list){
		this.list = list;
	}
	
	@Override
	public int doStartTag() throws JspException {
		int size = new Integer(list.getSize());
		logger.error("List size" + size );
		try{
			JspWriter out = pageContext.getOut();
			Course course;
			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
			int pageNumber = Integer.parseInt(request.getParameter(RequestParameterName.PAGE_NUMBER));
			String readMore = pageContext.getAttribute(PARAMETER_READ_MORE).toString();
			logger.error("List size" + size );
			for (int i = 0; i < size; i++){
				course = list.getElement();
				out.write("<tr>");
				out.write("<td>" + course.getNameCourse() + 		"</td>");
				out.write("<td>" + course.getStatusCourse() + 		"</td>");
				out.write("<td>" + course.getStartDateCourse() + 	"</td>");
				out.write("<td>" + course.getEndDateCourse() + 		"</td>");
				out.write("<td>" + course.getMaxNumberStudentsCourse() + 		"</td>");
				out.write("<td>" + course.getDescription() + 		"</td>");
				out.write("<td>" + course.getIdCourse() + 		"</td>");
				out.write("<td>");
					out.write("<form action=\"Controller\" method=\"post\">");
					out.write("<input type=\"hidden\" name=\"pageNumber\" value=\"" + pageNumber + "\">");
					out.write("<input type=\"hidden\" name=\"command\" value=\"show_course\">");
					out.write("<input type=\"hidden\" name=\"idCourse\" value=\"" + course.getIdCourse() +"\">");
					out.write("<input type=\"submit\" name=\"goToShowCourse\" value=\"" + readMore + "\" class=\"mybutton\">");
					out.write("</form>");
					out.write("</td>");
				out.write("</tr>");
			}
		} catch (IOException e){
			logger.error("IOException is thrown when run course's tag", e);
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}
}
