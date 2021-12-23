package common;

import java.io.Serializable;

public class OrdersList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String Customer_ID;
	private String Resturant;
	private String OrderPackageNumber;
	private String RequestDate;
	private String OrderedDate;
	private String TotalPrice;
	private String Address ;
	private String DeleiveryService;
	private String Status;
	private String ArrivalTime;
	private String ApprovalRecieving;
	
	public OrdersList(String customer_ID, String resturant, String orderPackageNumber, String requestDate,
			String orderedDate, String totalPrice, String address, String deleiveryService, String status,
			String arrivalTime, String approvalRecieving) {
		super();
		Customer_ID = customer_ID;
		Resturant = resturant;
		OrderPackageNumber = orderPackageNumber;
		RequestDate = requestDate;
		OrderedDate = orderedDate;
		TotalPrice = totalPrice;
		Address = address;
		DeleiveryService = deleiveryService;
		Status = status;
		ArrivalTime = arrivalTime;
		ApprovalRecieving = approvalRecieving;
	}

	public String getCustomer_ID() {
		return Customer_ID;
	}

	public void setCustomer_ID(String customer_ID) {
		Customer_ID = customer_ID;
	}

	public String getResturant() {
		return Resturant;
	}

	public void setResturant(String resturant) {
		Resturant = resturant;
	}

	public String getOrderPackageNumber() {
		return OrderPackageNumber;
	}

	public void setOrderPackageNumber(String orderPackageNumber) {
		OrderPackageNumber = orderPackageNumber;
	}

	public String getRequestDate() {
		return RequestDate;
	}

	public void setRequestDate(String requestDate) {
		RequestDate = requestDate;
	}

	public String getOrderedDate() {
		return OrderedDate;
	}

	public void setOrderedDate(String orderedDate) {
		OrderedDate = orderedDate;
	}

	public String getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		TotalPrice = totalPrice;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getDeleiveryService() {
		return DeleiveryService;
	}

	public void setDeleiveryService(String deleiveryService) {
		DeleiveryService = deleiveryService;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getArrivalTime() {
		return ArrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		ArrivalTime = arrivalTime;
	}

	public String getApprovalRecieving() {
		return ApprovalRecieving;
	}

	public void setApprovalRecieving(String approvalRecieving) {
		ApprovalRecieving = approvalRecieving;
	}

}