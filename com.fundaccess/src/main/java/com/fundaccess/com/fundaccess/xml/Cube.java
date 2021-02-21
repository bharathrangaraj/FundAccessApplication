package com.fundaccess.com.fundaccess.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class Cube {

	private String currency;
	private Double rate;

	@XmlAttribute(name = "currency")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@XmlAttribute(name = "rate")
	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

}
