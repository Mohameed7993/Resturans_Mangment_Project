package server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.print.attribute.standard.DateTimeAtCompleted;

import common.Account;
import common.Dish;
import common.MessageType;
import common.Order;
import common.Resturants;
import common.Selection;
import common.TybeMeal;
import common.UserType;
import common.Users;
import common.W4C_Card;



public class mysqlConnection {
	static Connection conn;

	public static String db_name;
	public static String db_user;
	public static String db_pass;
	public static void connectToDB()   {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			System.out.println("Driver definition succeed");
		} catch (Exception ex) {
			/* handle the error */
			System.out.println("Driver definition failed");
		}
		
			try {
				conn = DriverManager.getConnection(db_name, db_user, db_pass);
			} catch (SQLException ex) {/* handle any errors */
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
			}
			System.out.println("SQL connection succeed");
		
	}
 
	
	public static Users checkUserLogIn(String userName,String passWord) {//////////////////////////////////////////////////////////////////////////////////////update
		PreparedStatement ps;
		ResultSet res;
		Users user=null;
		try {
			ps = mysqlConnection.conn.prepareStatement("Select * From bitemedb.users where username=? and password=?");
			ps.setString(1, userName);
			ps.setString(2, passWord);
			ps.execute();
			res=ps.getResultSet();
			if(!res.next())
				return null;
			user=new Users(res.getString(1),res.getString(2),res.getString(3),true ,UserType.valueOf(res.getString(5)));
			ps = mysqlConnection.conn.prepareStatement("UPDATE bitemedb.users SET IsloggedIN =? where username=?");
			ps.setInt(1, 0); //// changed to 1
			ps.setString(2, userName);
			ps.execute();
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public static W4C_Card getW4cInformationfromDB (String w4c_code) {
		PreparedStatement ps;
		ResultSet res;
		W4C_Card w4c=null;
		try {
			ps = mysqlConnection.conn.prepareStatement("Select * From bitemedb.w4c_card where W4CCode=?");
			ps.setString(1, w4c_code);
			ps.execute();
			res=ps.getResultSet();
			if(!res.next())
				return null;
			w4c=new W4C_Card(res.getString(1),res.getString(2),res.getString(3));
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return w4c;
	}
	
	public static Account getAccountListFromDB(String ID){
	Account account=null;
	PreparedStatement ps;
	ResultSet res;
	try {
		
	ps = mysqlConnection.conn.prepareStatement("Select * From bitemedb.account where ID=?");
	ps.setString(1,ID );
	ps.execute();
	
	res=ps.getResultSet();
	if(!res.next())
		return null;
	account =new Account (res.getString(1), res.getString(2),res.getString(3),res.getString(4), res.getString(5),res.getString(6));
	return account;
	} catch (SQLException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
     }
	return account;
}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

	
	   public static ArrayList<TybeMeal> getTybeMealListFromDB(String ResturantID){
		   ArrayList<TybeMeal> tybemeal =new ArrayList<TybeMeal>();
		   TybeMeal temp;
		   PreparedStatement ps;
			ResultSet res;
			try {
				
				ps=mysqlConnection.conn.prepareStatement("SELECT * FROM bitemedb.typemeal where ResturantId=?");
				ps.setString(1,ResturantID);
				ps.execute();
				
				res=ps.getResultSet();
				while(res.next()) {
				   temp=new TybeMeal(res.getString(1),res.getString(2),res.getString(3));
				   tybemeal.add(temp);
				}
				res.close();
				
				
			}catch (SQLException e) {
				   // TODO Auto-generated catch block
				   e.printStackTrace();
			     }
			return tybemeal;
		   
	   }
	   
	   
	   public static ArrayList<Dish> getDishListFromDB(String TybeMealID){
		   ArrayList<Dish> Dish =new ArrayList<Dish>();
		   Dish temp;
		   PreparedStatement ps;
			ResultSet res;
			try {
				
				ps=mysqlConnection.conn.prepareStatement("SELECT * FROM bitemedb.dish where TypemealId=?");
				ps.setString(1,TybeMealID);
				ps.execute();
				
				res=ps.getResultSet();
				while(res.next()) {
				   temp=new Dish(res.getString(1),res.getString(2),res.getString(3),res.getInt(4));
				   Dish.add(temp);
				}
				res.close();
				
				
			}catch (SQLException e) {
				   // TODO Auto-generated catch block
				   e.printStackTrace();
			     }
			return Dish;
		   
	   }
	   
	   public static ArrayList<Selection> getSelectionListFromDB(String DishID){
		   ArrayList<Selection> selection =new ArrayList<Selection>();
		   Selection temp;
		   PreparedStatement ps;
			ResultSet res;
			try {
				
				ps=mysqlConnection.conn.prepareStatement("SELECT * FROM bitemedb.selection where DishId=?");
				ps.setString(1,DishID);
				ps.execute();
				
				res=ps.getResultSet();
				while(res.next()) {
				   temp=new Selection(res.getString(1),res.getString(2),res.getString(3),res.getInt(4));
				   selection.add(temp);
				}
				res.close();
				
				
			}catch (SQLException e) {
				   // TODO Auto-generated catch block
				   e.printStackTrace();
			     }
			return selection;
		   
	   }
	
	
		public static ArrayList<Resturants> getResturantsListFromDB(){
		ArrayList<Resturants> list = new ArrayList<Resturants>();
		Resturants temp;
		Statement statment;
		ResultSet res;
		try {
			statment=mysqlConnection.conn.createStatement();
			res=statment.executeQuery("SELECT * FROM bitemedb.resturants");
			while (res.next()) {
				temp=new Resturants(res.getString(1), res.getString(2),res.getString(3),res.getString(4));
				list.add(temp);
			}
				res.close();
		} catch (SQLException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	     }
	       return list;
	}
		
		
	
	/*public static void updateAddress(int orderNumber, String address) {
		PreparedStatement ps;

		try {
			ps = mysqlConnection.conn
					.prepareStatement("UPDATE assignement2db.order SET OrderAddress =? where OrderNumber=?");
			ps.setString(1, address);
			ps.setInt(2, orderNumber);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/
}
