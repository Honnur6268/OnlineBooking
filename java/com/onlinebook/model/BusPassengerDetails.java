package com.onlinebook.model;

public class BusPassengerDetails {
	private String BusNumber;
	private String FirstName;
	private String LastName;
	private String Email;
	private String MobileNumber;
	private String Age;
	private String totalSeats;
	private String totalAmount;
	private String seatNumbers;
	

	public String getBusNumber() {
		return BusNumber;
	}

	public void setBusNumber(String busNumber) {
		BusNumber = busNumber;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getMobileNumber() {
		return MobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		MobileNumber = mobileNumber;
	}

	public String getAge() {
		return Age;
	}

	public void setAge(String age) {
		Age = age;
	}

	public String getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(String totalSeats) {
		this.totalSeats = totalSeats;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getSeatNumbers() {
		return seatNumbers;
	}

	public void setSeatNumbers(String seatNumbers) {
		this.seatNumbers = seatNumbers;
	}

	
	
}
