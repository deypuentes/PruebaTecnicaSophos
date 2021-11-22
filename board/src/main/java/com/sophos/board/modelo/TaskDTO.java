package com.sophos.board.modelo;

public class TaskDTO {
	
	private int  id;
	 private String taskname;
	 private String description;
	 private String state;
	 private int idPerson;
	 private String userName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getIdPerson() {
		return idPerson;
	}
	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	 
	 
	 
	 
	 

}
