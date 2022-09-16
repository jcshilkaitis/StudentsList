package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Joe Shilkaitis - jshilkaitis
 * CIS175 - Fall 2022
 * Sep 15, 2022
 */
@Entity
@Table(name="Students")
public class StudentInfo {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	
	@Column(name="FirstName")
	private String firstName;
	
	@Column(name="LastName")
	private String lastName;
	
	@Column(name="Age")
	private int age;
	
	@Column(name="Belt")
	private String belt;

	public StudentInfo() {
	}

	public StudentInfo(String firstName, String lastName, int age, String belt) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.belt = belt;
	}

	public String returnStudentInfo() {
		return "Name: " + this.firstName + " " + this.lastName + "\n" + "Age: " + this.age + "\n" + "Rank: "
				+ this.belt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getRank() {
		return belt;
	}


	public void setRank(String belt) {
		this.belt = belt;
	}
}
