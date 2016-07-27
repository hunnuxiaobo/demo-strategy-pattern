package cn.szse.users.pojo;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class JsonListResult<T> implements Serializable {

	private static final long serialVersionUID = -3252245211184334223L;

	private List<T> entities;
	
	public JsonListResult() {}
	public JsonListResult(List<T> entities) {
		this.entities = entities;
	}
	public List<T> getEntities() {
		return entities;
	}
	public void setEntities(List<T> entities) {
		this.entities = entities;
	}
}
