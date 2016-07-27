package cn.szse.users.filterstrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.szse.users.pojo.User;
import cn.szse.users.pojo.SearchRule.Rule;

public class BornOnFilterStrategy extends AbstractFilter {
	@Override
	protected List<User> lessAndEqual(Rule rule, List<User> users) {
		if(users == null || users.isEmpty()) {
			return users;
		}
		
		List<User> result = new ArrayList<User>();
		for(User u : users) {
			if(compareDate(u.getBornOn(), String.valueOf(rule.getValue())) <= 0) {
				result.add(u);
			}
		}
		
		sort(result);
		
		return result;
	}
	
	@Override
	protected List<User> largeAndEqual(Rule rule, List<User> users) {
		if(users == null || users.isEmpty()) {
			return users;
		}
		
		List<User> result = new ArrayList<User>();
		for(User u : users) {
			if(compareDate(u.getBornOn(), String.valueOf(rule.getValue())) >= 0) {
				result.add(u);
			}
		}
		
		sort(result);
		
		return result;
	}
	
	private static int compareDate(String date1, String date2) {
		int d1 = convert2Number(date1);
		int d2 = convert2Number(date2);
		
		if(d1 > d2) {
			return 1;
		} else if(d1 == d2) {
			return 0;
		} else {
			return -1;
		}
	}
	
	private static int convert2Number(String date) {
		String[] dateArr = date.split("-");
		StringBuffer sb = new StringBuffer();
		for(String d : dateArr) {
			sb.append(d);
		}
		
		return Integer.parseInt(sb.toString());
	}
	
	private static void sort(List<User> users) {
		Collections.sort(users, new Comparator<User>() {

			@Override
			public int compare(User o1, User o2) {
				return BornOnFilterStrategy.compareDate(o1.getBornOn(), o2.getBornOn());
			}
			
		});
	}
}
