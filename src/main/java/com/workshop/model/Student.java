package com.workshop.model;


import java.io.Serializable;
import java.util.Comparator;

public class Student implements Serializable{

	private static final long serialVersionUID = 6718071273578435756L;
	
	private int id;
	private String name;
	private int age;
	
	public Student() {
	}
	
	

	public Student(int id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}


	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

	public static Comparator<Student> idComparator = new Comparator<Student>() {

		public int compare(Student s1, Student s2) {

			int rollno1 = s1.getId();
			int rollno2 = s2.getId();
			// Descending order
			return rollno2-rollno1;
		}};
	
	
}
