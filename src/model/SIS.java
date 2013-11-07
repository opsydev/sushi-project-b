package model;

import java.util.List;

import javax.servlet.ServletException;

public class SIS
{
	final StudentDAO dao;

	public SIS() throws ClassNotFoundException
	{
		this.dao = new StudentDAO();
	}
	
	 public List<String> retrieve(String namePrefix, String g, String s) throws Exception
	 {
		 try{
			System.out.println("I am in the SIS class");
			 double gpa = Double.parseDouble(g);
			 List<String> resultList =  dao.retrieve(namePrefix, gpa);
			 return resultList;
		 }
		 catch(NumberFormatException E)
		 {
			 throw new ServletException("error with client's input",E);
		 }
		 
		 
	 }
}
