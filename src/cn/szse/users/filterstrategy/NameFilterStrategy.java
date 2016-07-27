package cn.szse.users.filterstrategy;

import java.util.ArrayList;
import java.util.List;

import cn.szse.users.pojo.User;
import cn.szse.users.pojo.SearchRule.Rule;

public class NameFilterStrategy extends AbstractFilter {
	@Override
	protected List<User> like(Rule rule, List<User> users) {
		if(users == null || users.isEmpty()) {
			return users;
		}
		
		List<User> result = new ArrayList<User>();
		for(User u : users) {
			if(u.getName().toUpperCase().contains(String.valueOf(rule.getValue()).toUpperCase())) {
				result.add(u);
			}
		}
		
		return result;
	}
}
