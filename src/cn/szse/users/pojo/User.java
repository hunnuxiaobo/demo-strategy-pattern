package cn.szse.users.pojo;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7603077007796679371L;
	private String account;
	private String name;
	private int age;
	private String bornOn;
	private String departmentId;
	
	private Department department;
	
	public User(){}
	public User(String account, String name, int age, String bornOn, String depId) {
		this.account = account;
		this.name = name;
		this.age = age;
		this.bornOn = bornOn;
		this.departmentId = depId;
	}

	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getBornOn() {
		return bornOn;
	}
	public void setBornOn(String bornOn) {
		this.bornOn = bornOn;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	@Override
	public String toString() {
		return this.account+" "+this.name+" "+this.age+" "+this.bornOn+" "+this.departmentId;
	}
}
