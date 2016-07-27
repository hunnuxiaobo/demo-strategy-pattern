package cn.szse.users.filterstrategy;

import java.util.List;

import cn.szse.users.pojo.SearchRule.Oper;
import cn.szse.users.pojo.SearchRule.Rule;
import cn.szse.users.pojo.User;

/**
 * Template method
 * @author bxiao
 *
 */
public class AbstractFilter implements Filter {

	@Override
	public List<User> filter(Rule rule, List<User> users) {
		if(users == null || users.isEmpty())
			return users;
		
		switch(Oper.parse(rule.getOper())) {
			case EQUAL:
				return equal(rule, users);
			case LIKE:
				return like(rule, users);
			case LESS:
				return less(rule, users);
			case LESS_EQUAL:
				return lessAndEqual(rule, users);
			case LARGE:
				return large(rule, users);
			case LARGE_EQUAL:
				return largeAndEqual(rule, users);
			case IN:
				return in(rule, users);
			default:
				throw new RuntimeException("Invalid rule opt:"+rule.getOper());
		}
	}

	protected List<User> equal(Rule rule, List<User> users) {
		throw new RuntimeException("not implements.");
	}
	
	protected List<User> like(Rule rule, List<User> users) {
		throw new RuntimeException("not implements.");
	}
	
	protected List<User> less(Rule rule, List<User> users) {
		throw new RuntimeException("not implements.");
	}
	
	protected List<User> lessAndEqual(Rule rule, List<User> users) {
		throw new RuntimeException("not implements.");
	}
	
	protected List<User> large(Rule rule, List<User> users) {
		throw new RuntimeException("not implements.");
	}
	
	protected List<User> largeAndEqual(Rule rule, List<User> users) {
		throw new RuntimeException("not implements.");
	}
	
	protected List<User> in(Rule rule, List<User> users) {
		throw new RuntimeException("not implements.");
	}
}
