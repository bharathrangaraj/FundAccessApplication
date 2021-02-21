package com.fundaccess.com.fundaccess.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class TimeCubeList {

	private List<TimeCube> timeCube;

	@XmlElement(name = "Cube")
	public List<TimeCube> getTimeCube() {
		return timeCube;
	}

	public void setTimeCube(List<TimeCube> timeCube) {
		this.timeCube = timeCube;
	}

}
