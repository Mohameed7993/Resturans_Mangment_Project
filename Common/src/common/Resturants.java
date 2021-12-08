package common;

import java.io.Serializable;

public class Resturants implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String ResturantName;
	private String Status;
	private String PhoneNumber;
	
	
	public Resturants(String resturantName, String status, String phoneNumber) {
		super();
		this.ResturantName = resturantName;
		this.Status = status;
		this.PhoneNumber = phoneNumber;
	}
	
	public String getResturantName() {
		return ResturantName;
	}
	public void setResturantName(String resturantName) {
		this.ResturantName = resturantName;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		this.Status = status;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.PhoneNumber = phoneNumber;
	}
	

}
