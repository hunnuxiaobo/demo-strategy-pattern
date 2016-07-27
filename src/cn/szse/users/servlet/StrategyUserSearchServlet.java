package cn.szse.users.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.szse.users.pojo.JsonListResult;
import cn.szse.users.pojo.SearchRule;
import cn.szse.users.pojo.User;
import cn.szse.users.service.IUserService;
import cn.szse.users.service.impl.BetterUserService;

@WebServlet("/strategySearch")
public class StrategyUserSearchServlet  extends BaseServlet {

	private static final long serialVersionUID = 5093202934817718349L;
	
	private IUserService userService = new BetterUserService();
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) {
		try {
			//construct form bean
			SearchRule searchRule = this.parseAsBean(req, SearchRule.class);
			
			//searching
			List<User> users = userService.searchUser(searchRule);
			
			//generate result
			JsonListResult<User> result = new JsonListResult<User>(users);
			
			//write to client
			this.wirteAsJson(resp, result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
