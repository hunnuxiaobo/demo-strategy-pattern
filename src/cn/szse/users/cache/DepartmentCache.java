package cn.szse.users.cache;

import java.util.ArrayList;
import java.util.List;

import cn.szse.users.pojo.Department;

public class DepartmentCache {
	private static List<Department> departments= 
			new ArrayList<Department>();
	static {
		departments.add(new Department("1", "电脑工程部"));
		departments.add(new Department("2", "系统规划部"));
		departments.add(new Department("3", "市场监察部"));
		departments.add(new Department("4", "公司管理部"));
		departments.add(new Department("5", "上市推广部"));
		departments.add(new Department("6", "信息管理部"));
		departments.add(new Department("7", "系统运行部"));
		departments.add(new Department("8", "会员管理部"));
		departments.add(new Department("9", "法律部"));
		departments.add(new Department("10", "财务部"));
	}
	
	public static List<Department> getDepartments() {
		List<Department> result = new ArrayList<Department>();
		result.addAll(departments);
		return result;
	}
}
