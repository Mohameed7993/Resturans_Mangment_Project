package common;

import java.io.Serializable;

public class Price implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Selction_ID;
	private String Price;
	public Price(String selction_ID, String price) {
		super();
		Selction_ID = selction_ID;
		Price = price;
	}
	public String getSelction_ID() {
		return Selction_ID;
	}
	public void setSelction_ID(String selction_ID) {
		Selction_ID = selction_ID;
	}
	public String getPrice() {
		return Price;
	}
	public void setPrice(String price) {
		Price = price;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Price == null) ? 0 : Price.hashCode());
		result = prime * result + ((Selction_ID == null) ? 0 : Selction_ID.hashCode());
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
		Price other = (Price) obj;
		if (Price == null) {
			if (other.Price != null)
				return false;
		} else if (!Price.equals(other.Price))
			return false;
		if (Selction_ID == null) {
			if (other.Selction_ID != null)
				return false;
		} else if (!Selction_ID.equals(other.Selction_ID))
			return false;
		return true;
	}
	
}
