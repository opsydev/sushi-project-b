package model;

import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;

public class SIS {
	
	private StudentDAO dao;
	public SIS() throws ClassNotFoundException{
		dao = new StudentDAO();
	}
	
	public List<StudentBean> retrieve(String np, String g, String sb) throws Exception{
		double gpa = Double.parseDouble(g);
		
		if(gpa<0 || gpa > 9) throw new Exception("GPA must be in range [0,9]");
		sb = verifySort(sb);
		
		return dao.retrieve(np.trim(), gpa, sb);
		
	}
	
	public void export(String namePrefix, String minGPA, String scope,
			String filename) throws Exception {

		double g = Double.parseDouble(minGPA);
		List<StudentBean> list = retrieve(namePrefix, minGPA, scope);
		scope = verifySort(scope);
		ListWrapper lw = new ListWrapper(namePrefix, g, scope, list);

//		System.out.println("Exporting to \n" + filename);

		JAXBContext jc = JAXBContext.newInstance(lw.getClass());
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

		StringWriter sw = new StringWriter();
		sw.write("\n");
		marshaller.marshal(lw, new StreamResult(sw));

		System.out.println(sw.toString()); // for debugging
		FileWriter fw = new FileWriter(filename);
		fw.write("<?xml version=\"1.0\"?>");
		fw.write("<?xml-stylesheet type=\"text/xsl\" href=\"SIS.xsl\"?>");
		fw.write(sw.toString());
		fw.close();

	}
	
	public List<String> getSortColumns(){
		List<String> cols = new ArrayList<String>();
		
		try{
			cols = dao.getSortColumns();
		} catch(Exception e){
			//Use default surname
			cols.add("SURNAME");
		}
		
		
		return cols;
	}

	private String verifySort(String sb){
		switch(sb){
		case "MAJOR":
		case "GPA":
		case "COURSES":
			break;
		default:
			sb = "SURNAME";
		}
		return sb;
	}
}
