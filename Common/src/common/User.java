package common;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String passWord;
	private UserType type;
	private boolean isLoged;
	private String W4C_QrCode;
	public User(String userName, String passWord, UserType type, boolean isLoged, String W4C_QrCode) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.type = type;
		this.isLoged = isLoged;
		this.W4C_QrCode=W4C_QrCode;
	}
	
	public String getW4C_QrCode() {
		return W4C_QrCode;
	}
	public void setW4C_QrCode(String w4c_QrCode) {
		this.W4C_QrCode = w4c_QrCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	@Override
	public int hashCode() {
		return Objects.hash(isLoged, passWord, type, userName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return isLoged == other.isLoged && Objects.equals(passWord, other.passWord) && type == other.type
				&& Objects.equals(userName, other.userName);
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}
	public boolean isLoged() {
		return isLoged;
	}
	public void setLoged(boolean isLoged) {
		this.isLoged = isLoged;
	}

}
