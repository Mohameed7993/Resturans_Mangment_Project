package common;

import java.io.Serializable;

public class ItemInCart implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer itemNumber;
	private String  TypeMeal;
	private String Dishes;
	private String Extras;
	private Integer TotalPrice;
	private Integer quantity;
	private String IdIteam;
	public ItemInCart(Integer itemNumber, String typeMeal, String dishes, String extras, Integer totalPrice,
			Integer quantity, String idIteam) {
		super();
		this.itemNumber = itemNumber;
		TypeMeal = typeMeal;
		Dishes = dishes;
		Extras = extras;
		TotalPrice = totalPrice;
		this.quantity = quantity;
		IdIteam = idIteam;
	}
	public Integer getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(Integer itemNumber) {
		this.itemNumber = itemNumber;
	}
	public String getTypeMeal() {
		return TypeMeal;
	}
	public void setTypeMeal(String typeMeal) {
		TypeMeal = typeMeal;
	}
	public String getDishes() {
		return Dishes;
	}
	public void setDishes(String dishes) {
		Dishes = dishes;
	}
	public String getExtras() {
		return Extras;
	}
	public void setExtras(String extras) {
		Extras = extras;
	}
	public Integer getTotalPrice() {
		return TotalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		TotalPrice = totalPrice;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getIdIteam() {
		return IdIteam;
	}
	public void setIdIteam(String idIteam) {
		IdIteam = idIteam;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Dishes == null) ? 0 : Dishes.hashCode());
		result = prime * result + ((Extras == null) ? 0 : Extras.hashCode());
		result = prime * result + ((IdIteam == null) ? 0 : IdIteam.hashCode());
		result = prime * result + ((TotalPrice == null) ? 0 : TotalPrice.hashCode());
		result = prime * result + ((TypeMeal == null) ? 0 : TypeMeal.hashCode());
		result = prime * result + ((itemNumber == null) ? 0 : itemNumber.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
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
		ItemInCart other = (ItemInCart) obj;
		if (Dishes == null) {
			if (other.Dishes != null)
				return false;
		} else if (!Dishes.equals(other.Dishes))
			return false;
		if (Extras == null) {
			if (other.Extras != null)
				return false;
		} else if (!Extras.equals(other.Extras))
			return false;
		if (IdIteam == null) {
			if (other.IdIteam != null)
				return false;
		} else if (!IdIteam.equals(other.IdIteam))
			return false;
		if (TotalPrice == null) {
			if (other.TotalPrice != null)
				return false;
		} else if (!TotalPrice.equals(other.TotalPrice))
			return false;
		if (TypeMeal == null) {
			if (other.TypeMeal != null)
				return false;
		} else if (!TypeMeal.equals(other.TypeMeal))
			return false;
		if (itemNumber == null) {
			if (other.itemNumber != null)
				return false;
		} else if (!itemNumber.equals(other.itemNumber))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}

}
