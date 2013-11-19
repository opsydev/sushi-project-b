package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name="sisReport")
public class ListWrapper {
	@XmlAttribute
	private String namePrefix;
	@XmlAttribute
	private Double minGPA;
	@XmlAttribute
	private String orderBy;
	@XmlElement(name="student")
	private List<StudentBean> list;
	
	public ListWrapper(){
		
	}
	
	public ListWrapper(String namePrefix, Double minGPA, String orderBy,
			List<StudentBean> list) {
		super();
		this.namePrefix = namePrefix;
		this.minGPA = minGPA;
		this.orderBy = orderBy;
		this.list = list;
	}
	
	
}
