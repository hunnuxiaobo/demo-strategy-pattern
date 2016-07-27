package cn.szse.users.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SearchRule implements Serializable {

	private static final long serialVersionUID = -6068843112508261506L;
	private List<Rule> rules;
	
	public List<Rule> getRules() {
		return rules;
	}

	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	public static class Rule {
		private String field;
		private String oper;
		private Object value;
		public String getField() {
			return field;
		}
		public void setField(String field) {
			this.field = field;
		}
		public String getOper() {
			return oper;
		}
		public void setOper(String oper) {
			this.oper = oper;
		}
		public Object getValue() {
			return value;
		}
		public void setValue(Object value) {
			this.value = value;
		}
	}
	
	public static enum  Oper {
		EQUAL("="),
		LESS("<"),
		LESS_EQUAL("<="),
		LARGE(">"),
		LARGE_EQUAL(">="),
		LIKE("like"),
		IN("in");
		
		private static Map<String, Oper> map = new HashMap<String, Oper>();
		static{
			Oper[] ops = Oper.values();
			for(Oper op : ops) {
				map.put(op.value(), op);
			}
		}
		
		private String keyword;
		private Oper(String keyword) {
			this.keyword = keyword;
		}
		public static Oper parse(String keyword) {
			Oper oper = map.get(keyword);
			if(oper == null) 
				throw new IllegalArgumentException("invalid parameter [keyword], can't be parsed to Oper object");
			return oper;
		}
		public String value() {
			return this.keyword;
		}
		public String toString() {
			return this.keyword;
		}
	}
}
