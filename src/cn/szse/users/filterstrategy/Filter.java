package cn.szse.users.filterstrategy;

import java.util.List;

import cn.szse.users.pojo.SearchRule.Rule;
import cn.szse.users.pojo.User;

/**
 * Interface of strategy which defined the algorithm
 * @author bxiao
 *
 */
public interface Filter {
	List<User> filter(Rule rule, List<User> users);
}
