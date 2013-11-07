package model;

public class StudentBean
{
	public StudentBean()
	{
		
	}
	
	private String name;
	private String major;
	private String courses;
	private String gpa;
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getMajor()
	{
		return major;
	}
	public void setMajor(String major)
	{
		this.major = major;
	}
	public String getCourses()
	{
		return courses;
	}
	public void setCourses(String courses)
	{
		this.courses = courses;
	}
	public String getGpa()
	{
		return gpa;
	}
	public void setGpa(String gpa)
	{
		this.gpa = gpa;
	}
	
	
}
