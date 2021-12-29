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
import common.Business;
import common.Dish;
import common.ItemList;
import common.MessageType;
import common.OrdersList;
import common.Refund;
import common.Resturants;
import common.Selection;
import common.TybeMeal;
import common.UserType;
import common.Users;
import common.W4C_Card;



public class mysqlConnection {
	static Connection conn;
	private static Users user=null;
	public static String db_name;
	public static String db_user;
	public static String db_pass;
	public static Integer PaCkage;
	private static Boolean Flag=false;
	
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
		//Users user=null;
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
	
	public static Business getBussinessInformationfromDB (String w4c_code) {
		PreparedStatement ps;
		ResultSet res;
		Business business=null;
		try {
			ps = mysqlConnection.conn.prepareStatement("Select * From bitemedb.business where w4cCode=?");
			ps.setString(1, w4c_code);
			ps.execute();
			res=ps.getResultSet();
			if(!res.next())
				return null;
			business=new Business(res.getString(1),res.getString(2),res.getString(3),res.getString(4));
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return business;
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
	
	public static void SetItemsOfOrderinDB( String theMeal, String theDish, String ingredient, Integer quantity, Integer price,
			String packageID) {
		PreparedStatement ps;
		try
		{
			ps=mysqlConnection.conn.prepareStatement("Insert into bitemedb.item_list values (?,?,?,?,?,?)");
			ps.setString(1, theMeal);
			ps.setString(2, theDish);
			ps.setString(3, ingredient);
			ps.setInt(4, quantity);
			ps.setInt(5, price);
			ps.setString(6, packageID);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void setOrderinDB(String customer_ID, String resturant, String orderPackageNumber, String requestDate,
			String orderedDate, String totalPrice, String address, String deleiveryService, String status,
			String arrivalTime, String approvalRecieving) {
		PreparedStatement ps;
		try {
			ps=mysqlConnection.conn.prepareStatement("Insert Into bitemedb.order_list (Customer_ID, Resturant, RequestDate,"
					+ " OrderDate, TotalPrice, Address, DeleiveryService, Status, ArrivalTime, ApprovalRecieving) Values (?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1,customer_ID );
			ps.setString(2,resturant );
			ps.setString(3, requestDate);
			ps.setString(4, orderedDate);
			ps.setString(5, totalPrice);
			ps.setString(6,address );
			ps.setString(7, deleiveryService);
			ps.setString(8,status );
			ps.setString(9,arrivalTime );
			ps.setString(10,approvalRecieving);
			ps.execute();
			
			//res=ps.getResultSet();
			//System.out.println();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public static void updatecelling(String Celling,String w4ccode) {
		PreparedStatement ps;
		try {
			ps=mysqlConnection.conn.prepareStatement("update bitemedb.business set Ceiling=? where w4cCode=?");
			ps.setString(1, Celling);
			ps.setString(2, w4ccode);
			ps.execute();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public static  OrdersList getOrder(String CustomerId) {
		PreparedStatement ps;
		ResultSet res;
		int max=0;
		OrdersList order=null;
		try {
				ps = mysqlConnection.conn.prepareStatement("Select * From bitemedb.order_list where Customer_ID=?");
				ps.setString(1, CustomerId);
				ps.execute();
				
				res=ps.getResultSet();
				while(res.next()) {
					if(res.getInt(3)>max)
						max=res.getInt(3);
				}
				
				ps = mysqlConnection.conn.prepareStatement("Select * From bitemedb.order_list where OrderPackageNumber=?");
				ps.setInt(1,max);
				ps.execute();
				res=ps.getResultSet();
				res.next();
				order=new OrdersList(res.getString(1),res.getString(2),res.getInt(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7)
						,res.getString(8),res.getString(9),res.getString(10),res.getString(11));
				return order;
		}catch (SQLException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		     }
		return order;
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
		account =new Account (res.getString(1), res.getString(2),res.getString(3),res.getString(4), res.getString(5),res.getString(6),res.getString(7));
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
	
	
	   public static ArrayList<Resturants> getResturantsListFromDB(String Location){
			ArrayList<Resturants> list = new ArrayList<Resturants>();
			Resturants temp;
			Statement statment;
			ResultSet res;
			try {
				statment=mysqlConnection.conn.createStatement();
				res=statment.executeQuery("SELECT * FROM bitemedb.resturants");
				while (res.next()) {
					if(res.getString(6).equals(Location)) {
					temp=new Resturants(res.getString(1), res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6));
					list.add(temp);
					}
				}
					res.close();
			} catch (SQLException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		     }
		       return list;
		}
		
		
	   public static ArrayList<OrdersList> BuildOrderTable() {
		   //TABLE VIEW AND DATA
		ArrayList<OrdersList> Order_list = new ArrayList<OrdersList>(); 
		PreparedStatement ps;
		ResultSet res;
		String S=new String("Take It");
		if(Flag==true) {
			try {
				ps = mysqlConnection.conn.prepareStatement("update bitemedb.order_list set Status=? where OrderPackageNumber =? ");
				ps.setString(1, S);
				ps.setInt(2, PaCkage);
				ps.execute();
				Flag=false;
				
			//	res=ps.getResultSet();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {

			ps = mysqlConnection.conn.prepareStatement("Select * from bitemedb.order_list where Customer_ID =? ");
			ps.setString(1, user.getId());
			ps.execute();
			res=ps.getResultSet();
			while(res.next()) 
				 {

				if(res.getInt(3)==39) {
				}
					Order_list.add(new OrdersList(res.getString(1), res.getString(2), res.getInt(3),
							res.getString(4), res.getString(5), res.getString(6), res.getString(7),
								res.getString(8),res.getString(9),res.getString(10),res.getString(11)));
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Order_list;
	}
	
		public static ArrayList<ItemList> BuildItemList(Integer OrderPackageNumber) {
			ArrayList<ItemList> Item_list = new ArrayList<ItemList>(); 
			ResultSet res;
			Connection conn;
			String PackageID;
			try {
				conn = mysqlConnection.conn;
				res=conn.createStatement().executeQuery("Select * from  bitemedb.item_list");
				while(res.next()) {
					PackageID=(res.getString(6));
					if(PackageID.equals(String.valueOf(OrderPackageNumber))) {
					Item_list.add(new ItemList( res.getString(1), res.getString(2), res.getString(3),Integer.parseInt(res.getString(4)), Integer.parseInt(res.getString(5)),(res.getInt(6))));
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return Item_list;
		}
		
		public static ArrayList<OrdersList> EditBuildOrderTable(Integer Package) {
			PaCkage=Package;
			Flag=true;
			return BuildOrderTable();
		}

		public static void RefundBuild(String CustomerID, String ResturantID, String refund) {
			Refund Refund1=null; 
			PreparedStatement ps;
			PreparedStatement ps1;
			ResultSet res;
			ResultSet res2;
			int Sum;
			try {
				ps = mysqlConnection.conn.prepareStatement("SELECT * FROM bitemedb.refund where Customer_ID =? and ResturantId =?");
				ps.setString(1, CustomerID);
				ps.setString(2, ResturantID);
				ps.execute();
				res=ps.getResultSet();
					if(!res.next()) { //if next null
							ps1=mysqlConnection.conn.prepareStatement("Insert into bitemedb.refund Values (?,?,?)");
							ps1.setString(1,CustomerID);
							ps1.setString(2, ResturantID);
							ps1.setString(3, refund);
							ps1.execute();
							     ps1=mysqlConnection.conn.prepareStatement("SELECT * FROM bitemedb.refund where Customer_ID =? and ResturantId =?");
							     ps1.setString(1,CustomerID);
								 ps1.setString(2, ResturantID);
								 ps1.execute();
								 res2=ps.getResultSet();
								 //Refund1=new Refund(res2.getString(1), res2.getString(2), res2.getString(3));
					}
					else {
						Sum=Integer.valueOf(res.getString(3))+Integer.valueOf(refund);
						ps1=mysqlConnection.conn.prepareStatement("update bitemedb.refund set Refund=? where Customer_ID =? and ResturantId =? ");
						ps1.setString(1, String.valueOf(Sum));
						ps1.setString(2,CustomerID);
						ps1.setString(3, ResturantID);
						ps1.execute();
								ps1=mysqlConnection.conn.prepareStatement("SELECT * FROM bitemedb.refund where Customer_ID =? and ResturantId =?");
							     ps1.setString(1,CustomerID);
								 ps1.setString(2, ResturantID);
								 ps1.execute();
								 res2=ps.getResultSet();
								 //Refund1=new Refund(res2.getString(1), res2.getString(2), res2.getString(3));
					}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//return Refund1;
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
