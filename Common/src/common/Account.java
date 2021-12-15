package common;
import java.io.Serializable;

public class Account implements Serializable {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
   private String ID;
   private String FirstName;
   private String LasName;
   private String PhoneNumber;
   private String Email;
   private String W4C_QrCode;
public Account(String iD, String firstName, String lasName, String phoneNumber, String email, String w4c_QrCode) {
	super();
	ID = iD;
	FirstName = firstName;
	LasName = lasName;
	PhoneNumber = phoneNumber;
	Email = email;
	W4C_QrCode = w4c_QrCode;
}
public String getID() {
	return ID;
}
public void setID(String iD) {
	ID = iD;
}
public String getFirstName() {
	return FirstName;
}
public void setFirstName(String firstName) {
	FirstName = firstName;
}
public String getLasName() {
	return LasName;
}
public void setLasName(String lasName) {
	LasName = lasName;
}
public String getPhoneNumber() {
	return PhoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	PhoneNumber = phoneNumber;
}
public String getEmail() {
	return Email;
}
public void setEmail(String email) {
	Email = email;
}
public String getW4C_QrCode() {
	return W4C_QrCode;
}
public void setW4C_QrCode(String w4c_QrCode) {
	W4C_QrCode = w4c_QrCode;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((Email == null) ? 0 : Email.hashCode());
	result = prime * result + ((FirstName == null) ? 0 : FirstName.hashCode());
	result = prime * result + ((ID == null) ? 0 : ID.hashCode());
	result = prime * result + ((LasName == null) ? 0 : LasName.hashCode());
	result = prime * result + ((PhoneNumber == null) ? 0 : PhoneNumber.hashCode());
	result = prime * result + ((W4C_QrCode == null) ? 0 : W4C_QrCode.hashCode());
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
	Account other = (Account) obj;
	if (Email == null) {
		if (other.Email != null)
			return false;
	} else if (!Email.equals(other.Email))
		return false;
	if (FirstName == null) {
		if (other.FirstName != null)
			return false;
	} else if (!FirstName.equals(other.FirstName))
		return false;
	if (ID == null) {
		if (other.ID != null)
			return false;
	} else if (!ID.equals(other.ID))
		return false;
	if (LasName == null) {
		if (other.LasName != null)
			return false;
	} else if (!LasName.equals(other.LasName))
		return false;
	if (PhoneNumber == null) {
		if (other.PhoneNumber != null)
			return false;
	} else if (!PhoneNumber.equals(other.PhoneNumber))
		return false;
	if (W4C_QrCode == null) {
		if (other.W4C_QrCode != null)
			return false;
	} else if (!W4C_QrCode.equals(other.W4C_QrCode))
		return false;
	return true;
}
   
   
}
