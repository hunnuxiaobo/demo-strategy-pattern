package cn.szse.users.servlet;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.szse.users.cache.DepartmentCache;
import cn.szse.users.pojo.Department;
import cn.szse.users.pojo.JsonListResult;

/**
 * Servlet implementation class LoadDepartmentsServlet
 */
@WebServlet("/loadDepartments")
public class LoadDepartmentsServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadDepartmentsServlet() {
        super();
    }

	public void service(HttpServletRequest req, HttpServletResponse resp) {
		try {
			
			List<Department> departs = DepartmentCache.getDepartments();
			
			this.wirteAsJson(resp, new JsonListResult<Department>(departs));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
