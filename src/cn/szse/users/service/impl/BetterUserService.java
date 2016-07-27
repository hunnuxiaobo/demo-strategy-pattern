package cn.szse.users.service.impl;

import java.util.List;

import cn.szse.users.cache.UserCache;
import cn.szse.users.filterstrategy.FilterExecutor;
import cn.szse.users.pojo.SearchRule;
import cn.szse.users.pojo.User;
import cn.szse.users.service.IUserService;

public class BetterUserService implements IUserService {

	@Override
	public List<User> searchUser(SearchRule searchRule) {
		
		FilterExecutor exec = new FilterExecutor(searchRule);
		
		List<User> users = UserCache.getUsers();
		
		List<User> result = exec.filterByRules(users);
		
		return result;
	}

}
