package common;

import java.io.Serializable;

public class TybeMeal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Resturant_ID;
	private String TybeMeal_ID;
	private Dish Tybe_Meal;
	
	
	public String getResturant_ID() {
		return Resturant_ID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Resturant_ID == null) ? 0 : Resturant_ID.hashCode());
		result = prime * result + ((Tybe_Meal == null) ? 0 : Tybe_Meal.hashCode());
		result = prime * result + ((TybeMeal_ID == null) ? 0 : TybeMeal_ID.hashCode());
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
		TybeMeal other = (TybeMeal) obj;
		if (Resturant_ID == null) {
			if (other.Resturant_ID != null)
				return false;
		} else if (!Resturant_ID.equals(other.Resturant_ID))
			return false;
		if (Tybe_Meal == null) {
			if (other.Tybe_Meal != null)
				return false;
		} else if (!Tybe_Meal.equals(other.Tybe_Meal))
			return false;
		if (TybeMeal_ID == null) {
			if (other.TybeMeal_ID != null)
				return false;
		} else if (!TybeMeal_ID.equals(other.TybeMeal_ID))
			return false;
		return true;
	}
	public void setResturant_ID(String resturant_ID) {
		Resturant_ID = resturant_ID;
	}
	public String getTybeMeal_ID() {
		return TybeMeal_ID;
	}
	public void setTybeMeal_ID(String tybeMeal_ID) {
		TybeMeal_ID = tybeMeal_ID;
	}
	public Dish getTybeMeal() {
		return Tybe_Meal;
	}
	public void setTybeMeal(Dish tybeMeal) {
		Tybe_Meal = tybeMeal;
	}
	public TybeMeal(String resturant_ID, String tybeMeal_ID, Dish tybeMeal) {
		super();
		Resturant_ID = resturant_ID;
		TybeMeal_ID = tybeMeal_ID;
		Tybe_Meal = tybeMeal;
	}

}
