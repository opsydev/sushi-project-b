package tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class StudentBeanRow extends SimpleTagSupport{
	private String name, major;
	private int courses;
	private double gpa;
	
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

	public int getCourses() {
		return courses;
	}

	public void setCourses(int courses) {
		this.courses = courses;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public void doTag() throws JspException, IOException
    {
            JspWriter os = this.getJspContext().getOut();
            
            //Figure out which arrow to use
            int g = (int)((gpa*10)-50);
            String arrow = "&#9679;"; // Set arrow to the circle
            for(int i = 0;i < Math.abs(g); i ++){
            	if(g<0){
                	arrow += "&#9666;" ;
                }else if(g>0){
                	arrow ="&#9656;"+ arrow;
                }
            }
            
            String color = g<0? "#f00" : "#00f";
            os.write("<tr>");
            os.write("<td>"+this.name+"</td>");
            os.write("<td>"+this.major+"</td>");
            os.write("<td>"+this.courses+"</td>");
            os.write("<td>"+this.gpa+"</td>");
            os.write("<td style=color:"+color +">"+arrow+"</td>");
            os.write("</tr>");
            
    }

}
