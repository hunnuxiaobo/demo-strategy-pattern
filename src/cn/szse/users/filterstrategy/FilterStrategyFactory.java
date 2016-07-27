package cn.szse.users.filterstrategy;

import java.util.HashMap;
import java.util.Map;

public class FilterStrategyFactory {
	private static final Map<String, Filter> filterStrategy = 
			new HashMap<String, Filter>();
	static {
		filterStrategy.put("account", new AccountFilterStrategy());
		filterStrategy.put("name", new NameFilterStrategy());
		filterStrategy.put("age", new AgeFilterStrategy());
		filterStrategy.put("bornOn", new BornOnFilterStrategy());
		filterStrategy.put("departmentId", new DepartmentIdFilterStrategy());
	}
	
	public static Filter buildFilter(String fieldID) {
		Filter filter = filterStrategy.get(fieldID);
		if(filter == null)
			throw new RuntimeException("has no fieldID="+fieldID+"'s filter defined");
		return filter;
	}
}
