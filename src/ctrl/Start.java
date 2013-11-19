package ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.util.*;

import model.SIS;
import model.StudentBean;

/**
 * Servlet implementation class Start
 */
@WebServlet("/Start")
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		loadDropdown(request);
		request.getRequestDispatcher("Form.jspx").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String location;
		loadDropdown(request);
		if(request.getParameter("get_report") !=null || request.getParameter("generate_xml")!=null){
			//Get info
			String namePrefix = request.getParameter("namePrefix");
			String min_gpa = request.getParameter("min_gpa");
			String sort_by = request.getParameter("sort_by");
			request.setAttribute("user_name_prefix", namePrefix);
			request.setAttribute("user_min_gpa", min_gpa);
			
			
			try{
				location = "Form.jspx";
				SIS m = (SIS) this.getServletContext().getAttribute("model");
				List<StudentBean> l = m.retrieve(namePrefix, min_gpa, sort_by);
				request.setAttribute("students", l);		
				request.setAttribute("is_post", true);		
				if(request.getParameter("generate_xml") !=null){
					location="Done.jspx";
					String f = "export/"+request.getSession().getId()+".xml";
					String filename = this.getServletContext().getRealPath(f);
					System.out.println("output location: " + filename);
					m.export(namePrefix, min_gpa, sort_by, filename);
					request.setAttribute("xml_file", f);
				}
				
			} catch(SQLException sqle){
				request.setAttribute("error", "Error with the database. Please check your values. If they are correct, please contact the programmer.");
				location = "Form.jspx";
			}catch(NumberFormatException nfe){
				request.setAttribute("error", "Errors in form. Fix them please.");
				location = "Form.jspx";
			}catch(Exception e){
				request.setAttribute("error", "Errors in form. Fix them please\n" + e);
				location = "Form.jspx";
			}	
		} else {
			location = "Form.jspx";
		}
		request.getRequestDispatcher(location).forward(request, response);
	}

	@Override
	public void init() throws ServletException {
		super.init();
		try{
			SIS model = new SIS();
			this.getServletContext().setAttribute("model", model);
			
		} catch(ClassNotFoundException e){
			System.out.println("Class not loaded " + e);
		}
		
	}
	
	private void loadDropdown(HttpServletRequest request){
		SIS m = (SIS) this.getServletContext().getAttribute("model");
		
		List<String> sort_cols = (List<String>) request.getSession().getAttribute("sort_cols");
	
		if(sort_cols==null || sort_cols.size()==0) sort_cols = m.getSortColumns();
		
		
		request.getSession().setAttribute("sort_cols", sort_cols);
	}

}
