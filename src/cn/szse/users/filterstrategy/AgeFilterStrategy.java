package cn.szse.users.filterstrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.szse.users.pojo.SearchRule.Rule;
import cn.szse.users.pojo.User;

public class AgeFilterStrategy extends AbstractFilter {
	@Override
	protected List<User> lessAndEqual(Rule rule, List<User> users) {
		if(users == null || users.isEmpty()) {
			return users;
		}
		
		List<User> result = new ArrayList<User>();
		for(User u : users) {
			if(u.getAge() <= Integer.valueOf(rule.getValue().toString())) {
				result.add(u);
			}
		}
		
		this.sort(result);
		
		return result;
	}
	
	@Override
	protected List<User> largeAndEqual(Rule rule, List<User> users) {
		if(users == null || users.isEmpty()) {
			return users;
		}
		
		List<User> result = new ArrayList<User>();
		for(User u : users) {
			if(u.getAge() >= Integer.valueOf(rule.getValue().toString())) {
				result.add(u);
			}
		}
		
		this.sort(result);
		
		return result;
	}
	
	private void sort(List<User> users) {
		Collections.sort(users, new Comparator<User>() {

			@Override
			public int compare(User o1, User o2) {
				return o1.getAge() - o2.getAge();
			}
			
		});
	}
}
