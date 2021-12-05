package common;

import java.io.Serializable;

public class Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private String resturant;
	private int orderNumber;
	private String time;
	private String phoneNumber;
	private String typeOfOrder;
	private String address;

	public Order(String resturant, int orderNumber, String time, String phoneNumber, String typeOfOrder,
			String address) {

		this.resturant = resturant;
		this.orderNumber = orderNumber;
		this.time = time;
		this.phoneNumber = phoneNumber;
		this.typeOfOrder = typeOfOrder;
		this.address = address;
	}

	public String getResturant() {
		return resturant;
	}

	public void setResturant(String resturant) {
		this.resturant = resturant;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTypeOfOrder() {
		return typeOfOrder;
	}

	public void setTypeOfOrder(String typeOfOrder) {
		this.typeOfOrder = typeOfOrder;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
