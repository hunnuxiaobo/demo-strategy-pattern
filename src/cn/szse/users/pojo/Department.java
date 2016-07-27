package cn.szse.users.pojo;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Department implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 852163281211211681L;
	private String id;
	private String name;
	
	public Department() {}
	public Department(String id, String name) {
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
