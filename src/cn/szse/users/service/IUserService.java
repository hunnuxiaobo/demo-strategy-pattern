package cn.szse.users.service;

import java.util.List;

import cn.szse.users.pojo.SearchRule;
import cn.szse.users.pojo.User;

public interface IUserService {
	List<User> searchUser(SearchRule searchRule);
}
