package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO
{
	final String database_url = "jdbc:derby://localhost:9999/CSE;user=student;password=secret;";

	// instantiate the connection with derby database in the constructor class
	public StudentDAO() throws ClassNotFoundException
	{	
		Class.forName("org.apache.derby.jdbc.ClientDriver");
	}
	
	public List retrieve(String namePrefix, double gpa) throws SQLException
	{
		Connection connection = DriverManager.getConnection(database_url);
		Statement statement = connection.createStatement();
		System.out.println("I got connection to database");
		statement.executeUpdate(" set schema roumani");
		ResultSet rs = statement.executeQuery("Select courses from sis where gpa >= gpa");
		StudentBean student = new StudentBean();
		List<String> list = new ArrayList<String>();
		while(rs.next())
		{
			student.setCourses(rs.getString("courses"));
			list.add(student.getCourses());
			rs.next();
		}
		return list;
	}
}

