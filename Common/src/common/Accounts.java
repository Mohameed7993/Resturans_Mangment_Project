package common;

import java.io.Serializable;

public class Accounts  implements Serializable {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
   private String ID;
   private String FirstName;
   private String LasName;
   private String PhoneNumber;
   private String Email;
   private String CreditCardNumber;
   private String W4C_QrCode;
   private String AccoountTybe;
   
public Accounts(String iD, String firstName, String lasName, String phoneNumber, String email, String creditCardNumber,
		String w4c_QrCode, String accoountTybe) {
	super();
	this.ID = iD;
	this.FirstName = firstName;
	this.LasName = lasName;
	this.PhoneNumber = phoneNumber;
	this.Email = email;
	this.CreditCardNumber = creditCardNumber;
	this.W4C_QrCode = w4c_QrCode;
	this.AccoountTybe = accoountTybe;
}

public String getID() {
	return ID;
}

public String getFirstName() {
	return FirstName;
}

public String getLasName() {
	return LasName;
}

public String getPhoneNumber() {
	return PhoneNumber;
}

public String getEmail() {
	return Email;
}

public String getCreditCardNumber() {
	return CreditCardNumber;
}

public String getW4C_QrCode() {
	return W4C_QrCode;
}

public String getAccoountTybe() {
	return AccoountTybe;
}
public String toString() {
	return (getFirstName()+" "+getLasName()+" "+ getPhoneNumber()+" "+getID()+" "+getEmail()+" "+getCreditCardNumber()+" "+getAccoountTybe());
}
   
}
