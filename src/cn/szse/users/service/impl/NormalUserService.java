package cn.szse.users.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.szse.users.cache.UserCache;
import cn.szse.users.pojo.SearchRule;
import cn.szse.users.pojo.SearchRule.Oper;
import cn.szse.users.pojo.SearchRule.Rule;
import cn.szse.users.pojo.User;
import cn.szse.users.service.IUserService;

public class NormalUserService implements IUserService 
{

	@Override
	public List<User> searchUser(SearchRule searchRule) 
	{
		List<User> users = UserCache.getUsers();
		
		if(searchRule != null && searchRule.getRules() != null 
				&& !searchRule.getRules().isEmpty()) 
		{
			List<User> result = new ArrayList<User>();
			
			List<Rule> rules = searchRule.getRules();
			for(User user : users) 
			{
				if(isMatched(rules, user)) //if the user matched given rules
				{
					result.add(user);
				}
			}
			
			return result;
		}
		
		return users;
	}

	private boolean isMatched(List<Rule> rules, User u) 
	{
		for(Rule rule : rules)
		{
			if(rule.getField().equals("account")) 
			{
				if(!isMatchedAccount(rule, u.getAccount()))
				{
					return false;
				}
			} 
			else if(rule.getField().equals("name")) 
			{
				if(!isMatchedName(rule, u.getName()))
				{
					return false;
				}
			}
			else if(rule.getField().equals("age")) 
			{
				if(!isMatchedAge(rule, u.getAge()))
				{
					return false;
				}
			} 
			else if(rule.getField().equals("bornOn")) 
			{
				if(!isMatchedBornOn(rule, u.getBornOn()))
				{
					return false;
				}
			}
			else if(rule.getField().equals("departmentId")) 
			{
				if(!isMatchedDepartmentId(rule, u.getDepartmentId()))
				{
					return false;
				}
			}
			else
			{
				throw new RuntimeException("invalid fieldId:"+rule.getField());
			}
		}
		return true;
	}
	
	private boolean isMatchedAccount(Rule rule, String account) {
		String value = String.valueOf(rule.getValue());
		return value.equalsIgnoreCase(account);
	}
	
	private boolean isMatchedName(Rule rule, String name) {
		String value = String.valueOf(rule.getValue());
		return name.toUpperCase().contains(value.trim().toUpperCase());
	}
	
	private boolean isMatchedAge(Rule rule, int age) {
		if(Oper.LESS_EQUAL.equals(Oper.parse(rule.getOper()))) {
			return age - Integer.valueOf(String.valueOf(rule.getValue())) <= 0;
		} else if(Oper.LARGE_EQUAL.equals(Oper.parse(rule.getOper()))) {
			return age - Integer.valueOf(String.valueOf(rule.getValue())) >= 0;
		}
		throw new RuntimeException("invalid oper:"+rule.getOper()+" of field:"+rule.getField());
	}
	
	private boolean isMatchedBornOn(Rule rule, String bornOn) {
		if(Oper.LESS_EQUAL.equals(Oper.parse(rule.getOper()))) {
			return compareDate(bornOn, String.valueOf(rule.getValue())) <= 0;
		} else if(Oper.LARGE_EQUAL.equals(Oper.parse(rule.getOper()))) {
			return compareDate(bornOn, String.valueOf(rule.getValue())) >= 0;
		}
		throw new RuntimeException("invalid oper:"+rule.getOper()+" of field:"+rule.getField());
	}
	
	@SuppressWarnings("unchecked")
	private boolean isMatchedDepartmentId(Rule rule, String departmentId) {
		List<String> dpIds = (List<String>)rule.getValue();
		return dpIds.contains(departmentId);
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
}
