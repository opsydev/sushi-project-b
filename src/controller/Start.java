package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Mortgage;
import model.SIS;

/**
 * Servlet implementation class Start
 */
@WebServlet("/Start")
public class Start extends HttpServlet {
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try
		{
			getServletContext().setAttribute("StudentSystem", new SIS());
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
		String send_to;	
		
		SIS sis = (SIS) getServletContext().getAttribute("StudentSystem");
		//Let's check if we are getting a fresh request or not
		if(request.getParameter("report")==null){
			send_to = "UI.jspx";
			request.getRequestDispatcher(send_to).forward(request, response);
		} else {
			System.out.println(" in the exlse body");
			//Do computation namePrefix, String g, String s
			String namePrefix = request.getParameter("namePrefix");
			String g = request.getParameter("minimumGPA");
			String s = request.getParameter("sortBy");
			
			request.getSession().setAttribute("namePrefix", namePrefix);
			request.getSession().setAttribute("sortBy",s);
			request.getSession().setAttribute("minimumGPA",g);
			
			System.out.println("g " + g +"<br />"); 
			System.out.println("s " + s +"<br />"); 
			System.out.println("namePrefix " + namePrefix +"<br />"); 
			List<String> list = new ArrayList<String>();
			try
			{
				list = sis.retrieve(namePrefix, g, s);
				request.setAttribute("sort", list);
				send_to = "Result.jspx";
				System.out.println("send_to " + send_to);
				request.getRequestDispatcher(send_to).forward(request, response);
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			
//			//Let's try computing
//			try {
//				pay = m.calculatePayment(a, i, p);
//				/*pay = pay*100;
//				pay = Math.round(pay);
//				pay = pay/100;*/
//				request.setAttribute("pay", pay);
//				send_to = "/Result.jspx";
//			} catch (Exception e) {
//				request.setAttribute("flash_errors", "Unable to calculate: please recheck your values");
//				send_to = "/UI.jspx";
//			}
		}
	/*	if(request.getParameter("format")!=null){
			if(request.getParameter("format").compareTo("json")==0){
				response.setContentType("text/plain");
				response.getWriter().println(pay);
			}
		}else*/ {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
