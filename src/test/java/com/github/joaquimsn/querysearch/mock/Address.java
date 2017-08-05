package com.github.joaquimsn.querysearch.mock;

public class Address {
	private Long id;
	private String state;
	private String city;
	private String zipcode;
	private String streetAddress;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", state=" + state + ", city=" + city + ", zipcode=" + zipcode + ", streetAddress="
				+ streetAddress + "]";
	}
}
