package cn.ajax;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("city")
public class City {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
