package com.epam.finaltask.dao.entity;

/**
 * This class represents the entity of the Course
 */
public class Course {
    private Integer idCourse;
    private Integer idTeacher;
    private String nameCourse;
    private Integer maxNumberStudentsCourse;
    private String	startDateCourse;
    private String 	endDateCourse;
    private String statusCourse;
    private String description;

    public Integer getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Integer idCourse) {
        this.idCourse = idCourse;
    }

    public Integer getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(Integer idTeacher) {
        this.idTeacher = idTeacher;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public Integer getMaxNumberStudentsCourse() {
        return maxNumberStudentsCourse;
    }

    public void setMaxNumberStudentsCourse(Integer maxNumberStudentsCourse) {
        this.maxNumberStudentsCourse = maxNumberStudentsCourse;
    }

    public String getStartDateCourse() {
        String[] arr = startDateCourse.split("-");

        return arr[2]+ "-" + arr[1] + "-" + arr[0];
    }

    public void setStartDateCourse(String startDateCourse) {
        this.startDateCourse = startDateCourse;
    }

    public String getEndDateCourse() {
        String[] arr = endDateCourse.split("-");

        return arr[2]+ "-" + arr[1] + "-" + arr[0];
    }

    public void setEndDateCourse(String endDateCourse) {
        this.endDateCourse = endDateCourse;
    }

    public String getStatusCourse() {
        return statusCourse;
    }

    public void setStatusCourse(String statusCourse) {
        this.statusCourse = statusCourse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
