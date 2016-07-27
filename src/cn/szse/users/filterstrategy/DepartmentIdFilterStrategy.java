package cn.szse.users.filterstrategy;

import java.util.ArrayList;
import java.util.List;

import cn.szse.users.pojo.User;
import cn.szse.users.pojo.SearchRule.Rule;

public class DepartmentIdFilterStrategy extends AbstractFilter {
	@SuppressWarnings("unchecked")
	@Override
	protected List<User> in(Rule rule, List<User> users) {
		if(users == null || users.isEmpty()) {
			return users;
		}
		
		List<User> result = new ArrayList<User>();
		List<String> dpIds = (List<String>)rule.getValue();
		for(User u : users) {
			if(dpIds.contains(u.getDepartmentId())) {
				result.add(u);
			}
		}
		
		return result;
	}
}
