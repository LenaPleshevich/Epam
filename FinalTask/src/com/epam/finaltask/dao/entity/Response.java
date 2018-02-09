package com.epam.finaltask.dao.entity;

/**
 * This class represents the entity of the Response
 */
public class Response {
    private Integer idResponse;
    private Task task;
    private User user;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getIdResponse() {
        return idResponse;
    }

    public void setIdResponse(int idResponse) {
        this.idResponse = idResponse;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Response{" +
                "idResponse=" + idResponse +
                ", text='" + text + '\'' +
                '}';
    }
}
