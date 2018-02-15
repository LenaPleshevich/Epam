package com.epam.finaltask.dao.entity;

/**
 * 	This class represents the entity of the User
 */

public class User {
	private Integer idUser;
	private String 	emailUser;
	private String	passwordUser;
	private Integer	idRoleUser;
	private NameRole nameRole;
	private String	firstNameUser;
	private String	lastNameUser;
	
	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getPasswordUser() {
		return passwordUser;
	}

	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}

	public NameRole getNameRole() {
		return nameRole;
	}

	public void setNameRole(NameRole nameRole) {
		this.nameRole = nameRole;
	}

	public Integer getIdRoleUser() {
		return idRoleUser;
	}

	public void setIdRoleUser(Integer idRoleUser) {
		this.idRoleUser = idRoleUser;
	}

	public String getFirstNameUser() {
		return firstNameUser;
	}

	public void setFirstNameUser(String firstNameUser) {
		this.firstNameUser = firstNameUser;
	}
	public String getLastNameUser() {
		return lastNameUser;
	}
	public void setLastNameUser(String lastNameUser) {
		this.lastNameUser = lastNameUser;
	}
}
