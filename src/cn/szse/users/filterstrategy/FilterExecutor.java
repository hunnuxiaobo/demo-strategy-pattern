package cn.szse.users.filterstrategy;

import java.util.ArrayList;
import java.util.List;

import cn.szse.users.pojo.SearchRule;
import cn.szse.users.pojo.SearchRule.Rule;
import cn.szse.users.pojo.User;

/**
 * Strategy Context
 * @author bxiao
 *
 */
public class FilterExecutor {
	
	private List<Rule> rules = new ArrayList<Rule>();
	
	public FilterExecutor(SearchRule searchRule) {
		if(searchRule != null && searchRule.getRules() != null 
				&& !searchRule.getRules().isEmpty()) {
			rules.addAll(searchRule.getRules());
		}
	}
	
	public List<User> filterByRules(List<User> users) {
		List<User> result = new ArrayList<User>();
		if(users != null && !users.isEmpty()) {
			result.addAll(users);
			for(Rule r : rules) {
				Filter filter = FilterStrategyFactory.buildFilter(r.getField());
				result = filter.filter(r, result);
			}
		}
		
		return result;
	}
	
}
