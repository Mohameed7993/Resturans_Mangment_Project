package common;

import java.io.Serializable;

public class Resturants implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private String ResturantName;
	private String Status;
	private String PhoneNumber;
	private String ResturantID;
	
	
	public Resturants(String resturantName, String status, String phoneNumber, String resturantID) {
		super();
		ResturantName = resturantName;
		Status = status;
		PhoneNumber = phoneNumber;
		ResturantID = resturantID;
	}

	public String getResturantID() {
		return ResturantID;
	}

	public void setResturantID(String resturantID) {
		ResturantID = resturantID;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((PhoneNumber == null) ? 0 : PhoneNumber.hashCode());
		result = prime * result + ((ResturantID == null) ? 0 : ResturantID.hashCode());
		result = prime * result + ((ResturantName == null) ? 0 : ResturantName.hashCode());
		result = prime * result + ((Status == null) ? 0 : Status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resturants other = (Resturants) obj;
		if (PhoneNumber == null) {
			if (other.PhoneNumber != null)
				return false;
		} else if (!PhoneNumber.equals(other.PhoneNumber))
			return false;
		if (ResturantID == null) {
			if (other.ResturantID != null)
				return false;
		} else if (!ResturantID.equals(other.ResturantID))
			return false;
		if (ResturantName == null) {
			if (other.ResturantName != null)
				return false;
		} else if (!ResturantName.equals(other.ResturantName))
			return false;
		if (Status == null) {
			if (other.Status != null)
				return false;
		} else if (!Status.equals(other.Status))
			return false;
		return true;
	}
	

	
	

}
