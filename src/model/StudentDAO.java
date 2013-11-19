package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
	private static final String URL = "jdbc:derby://localhost:9999/CSE;user=student;password=secret";

	public StudentDAO() throws ClassNotFoundException {
		Class.forName("org.apache.derby.jdbc.ClientDriver");
	}

	public List<StudentBean> retrieve(String namePrefix, double minGPA,
			String sort_by) throws SQLException {
		List<StudentBean> list = new ArrayList<StudentBean>();

		try (Connection conn = DriverManager.getConnection(URL);
				Statement statement = conn.createStatement()) {
			statement.executeUpdate("SET SCHEMA roumani");
			String sql = "SELECT * FROM SIS WHERE (SURNAME like ?) AND gpa > ? ORDER BY "
					+ sort_by;
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + namePrefix + "%");
			ps.setDouble(2, minGPA);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				StudentBean sb = new StudentBean();
				sb.setName(rs.getString("SURNAME") + ", "
						+ rs.getString("GIVENNAME"));
				sb.setCourses(rs.getInt("COURSES"));
				sb.setMajor(rs.getString("MAJOR"));
				sb.setGpa(rs.getDouble("GPA"));
				list.add(sb);
			}
		}

		return list;
	}

	public List<String> getSortColumns() throws SQLException {
		String sql = "SELECT surname, major, gpa, courses from SIS where GPA < 0";
		List<String> cols = new ArrayList<String>();
		try (Connection conn = DriverManager.getConnection(URL);
				Statement statement = conn.createStatement()) {
			statement.executeUpdate("SET SCHEMA roumani");
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			ResultSetMetaData rsm = rs.getMetaData();
			for(int i =1 ; i<= rsm.getColumnCount(); i++){
				cols.add(rsm.getColumnName(i));
			}
			
			return cols;
		}

	}

}
