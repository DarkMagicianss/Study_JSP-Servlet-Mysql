package cn.ajax;


import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("province")
public class Province {
	private String name;
	@XStreamImplicit
	private List<City> citys;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<City> getCitys() {
		return citys;
	}
	public void setCitys(List<City> citys) {
		this.citys = citys;
	}
}
