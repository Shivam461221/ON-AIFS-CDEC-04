package com.cloud.rest.models;

public class Student {
	
	private Integer id;
	private String name;
	private String course;
	private Integer age;
	
	public Student(Integer id, String name, String course, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.course = course;
		this.age = age;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public int getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	
	
	
	
}
