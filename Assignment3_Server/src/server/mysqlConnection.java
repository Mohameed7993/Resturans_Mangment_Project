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

import common.MessageType;
import common.Order;
import common.Resturants;
import common.User;
import common.UserType;



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

	public static User checkUserLogIn(String userName,String passWord) {
		
		PreparedStatement ps;
		ResultSet res;
		User user=null;
		try {
			
			ps = mysqlConnection.conn
					.prepareStatement("Select * From bitemedb.user where username=? and password=?");
			ps.setString(1, userName);
			ps.setString(2, passWord);
			ps.execute();
			res=ps.getResultSet();
			//if(res.getString(1)==null)
			//	return null;
			if(!res.next())
				return null;
			user=new User(res.getString(1), res.getString(2),UserType.valueOf(res.getString(3)) , true);
			ps = mysqlConnection.conn
					.prepareStatement("UPDATE bitemedb.user SET isLogged =? where username=?");
			ps.setInt(1, 1);
			ps.setString(2, userName);
			ps.execute();
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public static ArrayList<Resturants> getResturantsListFromDB(){
		ArrayList<Resturants> list = new ArrayList<Resturants>();
		Resturants temp;
		Statement statment;
		ResultSet res;
		try {
			statment=mysqlConnection.conn.createStatement();
			res=statment.executeQuery("SELECT * FROM bitemedb.resturantslist");
			while (res.next()) {
				temp=new Resturants(res.getString(1), res.getString(2),res.getString(3));
				list.add(temp);
			}
				res.close();
		} catch (SQLException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	     }
	       return list;
	}
	
	/*
	public static void updateType(int orderNumber, String type) {
		PreparedStatement ps;

		try {
			ps = mysqlConnection.conn
					.prepareStatement("UPDATE assignement2db.order SET TypeOfOrder =? where OrderNumber=?");
			ps.setString(1, type);
			ps.setInt(2, orderNumber);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void updateAddress(int orderNumber, String address) {
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
