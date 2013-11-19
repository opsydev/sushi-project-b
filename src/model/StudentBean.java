package model;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"name", "major", "courses", "gpa"})
public class StudentBean {
	private String name;
	private String major;
	private double gpa;
	private int courses;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	public int getCourses() {
		return courses;
	}
	public void setCourses(int courses) {
		this.courses = courses;
	}
}
