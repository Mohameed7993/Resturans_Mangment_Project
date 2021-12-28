package common;

import java.io.Serializable;

public class Resturants implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private String ResturantName;
	private String Status;
	private String PhoneNumber;
	private String ResturantID;
	private String BranchID;
	private String Location;
	public Resturants(String resturantName, String status, String phoneNumber, String resturantID, String branchID,
			String location) {
		super();
		ResturantName = resturantName;
		Status = status;
		PhoneNumber = phoneNumber;
		ResturantID = resturantID;
		BranchID = branchID;
		Location = location;
	}

	public String getResturantName() {
		return ResturantName;
	}
	public void setResturantName(String resturantName) {
		ResturantName = resturantName;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public String getResturantID() {
		return ResturantID;
	}
	public void setResturantID(String resturantID) {
		ResturantID = resturantID;
	}
	public String getBranchID() {
		return BranchID;
	}
	public void setBranchID(String branchID) {
		BranchID = branchID;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}

	


}