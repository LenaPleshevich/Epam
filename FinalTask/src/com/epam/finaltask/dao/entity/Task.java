package com.epam.finaltask.dao.entity;
/**
 * This class represents the entity of the Task
 */
public class Task {
    private TaskStatus taskStatus;
    private Integer idTask;
    private Integer idCourse;
    private String summary;
    private String nameTask;
    private String assignmentTime;
    private String deadline;

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getDeadline() {
        String[] arr = deadline.split("-");
        return arr[2]+ "-" + arr[1] + "-" + arr[0];
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public Integer getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Integer idCourse) {
        this.idCourse = idCourse;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public String getAssignmentTime() {
        String[] arr = assignmentTime.split("-");
        return arr[2]+ "-" + arr[1] + "-" + arr[0];
    }

    public void setAssignmentTime(String assignmentTime) {
        this.assignmentTime = assignmentTime;
    }

}
