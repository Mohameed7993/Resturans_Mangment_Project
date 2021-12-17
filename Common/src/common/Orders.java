package common;

import java.io.Serializable;

public class Orders implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String resturantID;
	private String customerID ;
	private String itemsID ;
	private String requestedDate ;
	private String OrderedDate ;
	private String totalPrice ;
	private String accountType ;
	private String accountpayment ;
	private String address ;
	private String deleiveryService ;
	
	
	public Orders(String resturantID, String customerID, String itemsID, String requestedDate, String orderedDate,
			String totalPrice, String accountType, String accountpayment, String address, String deleiveryService) {
		super();
		this.resturantID = resturantID;
		this.customerID = customerID;
		this.itemsID = itemsID;
		this.requestedDate = requestedDate;
		OrderedDate = orderedDate;
		this.totalPrice = totalPrice;
		this.accountType = accountType;
		this.accountpayment = accountpayment;
		this.address = address;
		this.deleiveryService = deleiveryService;
	}


	public String getResturantID() {
		return resturantID;
	}


	public void setResturantID(String resturantID) {
		this.resturantID = resturantID;
	}


	public String getCustomerID() {
		return customerID;
	}


	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}


	public String getItemsID() {
		return itemsID;
	}


	public void setItemsID(String itemsID) {
		this.itemsID = itemsID;
	}


	public String getRequestedDate() {
		return requestedDate;
	}


	public void setRequestedDate(String requestedDate) {
		this.requestedDate = requestedDate;
	}


	public String getOrderedDate() {
		return OrderedDate;
	}


	public void setOrderedDate(String orderedDate) {
		OrderedDate = orderedDate;
	}


	public String getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}


	public String getAccountType() {
		return accountType;
	}


	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}


	public String getAccountpayment() {
		return accountpayment;
	}


	public void setAccountpayment(String accountpayment) {
		this.accountpayment = accountpayment;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getDeleiveryService() {
		return deleiveryService;
	}


	public void setDeleiveryService(String deleiveryService) {
		this.deleiveryService = deleiveryService;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((OrderedDate == null) ? 0 : OrderedDate.hashCode());
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result + ((accountpayment == null) ? 0 : accountpayment.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((customerID == null) ? 0 : customerID.hashCode());
		result = prime * result + ((deleiveryService == null) ? 0 : deleiveryService.hashCode());
		result = prime * result + ((itemsID == null) ? 0 : itemsID.hashCode());
		result = prime * result + ((requestedDate == null) ? 0 : requestedDate.hashCode());
		result = prime * result + ((resturantID == null) ? 0 : resturantID.hashCode());
		result = prime * result + ((totalPrice == null) ? 0 : totalPrice.hashCode());
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
		Orders other = (Orders) obj;
		if (OrderedDate == null) {
			if (other.OrderedDate != null)
				return false;
		} else if (!OrderedDate.equals(other.OrderedDate))
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (accountpayment == null) {
			if (other.accountpayment != null)
				return false;
		} else if (!accountpayment.equals(other.accountpayment))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (customerID == null) {
			if (other.customerID != null)
				return false;
		} else if (!customerID.equals(other.customerID))
			return false;
		if (deleiveryService == null) {
			if (other.deleiveryService != null)
				return false;
		} else if (!deleiveryService.equals(other.deleiveryService))
			return false;
		if (itemsID == null) {
			if (other.itemsID != null)
				return false;
		} else if (!itemsID.equals(other.itemsID))
			return false;
		if (requestedDate == null) {
			if (other.requestedDate != null)
				return false;
		} else if (!requestedDate.equals(other.requestedDate))
			return false;
		if (resturantID == null) {
			if (other.resturantID != null)
				return false;
		} else if (!resturantID.equals(other.resturantID))
			return false;
		if (totalPrice == null) {
			if (other.totalPrice != null)
				return false;
		} else if (!totalPrice.equals(other.totalPrice))
			return false;
		return true;
	}
	
	
	
	

}
