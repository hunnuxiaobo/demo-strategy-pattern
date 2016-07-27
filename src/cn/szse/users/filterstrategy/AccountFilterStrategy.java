package cn.szse.users.filterstrategy;

import java.util.ArrayList;
import java.util.List;

import cn.szse.users.pojo.SearchRule.Rule;
import cn.szse.users.pojo.User;

public class AccountFilterStrategy extends AbstractFilter {
	@Override
	protected List<User> equal(Rule rule, List<User> users) {
		if(users == null || users.isEmpty()) {
			return users;
		}
		
		List<User> result = new ArrayList<User>();
		for(User u : users) {
			if(rule.getValue().equals(u.getAccount())) {
				result.add(u);
			}
		}
		
		return result;
	}
}
