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
import common.DishForResturant;
import common.HoumanResources;
import common.ItemList;
import common.MessageType;
import common.OptionalIngredients;
import common.OrderDish;
import common.OrdersForRes;
import common.OrdersList;
import common.Refund;
import common.Resturant;
import common.Resturants;
import common.Selection;
import common.TybeMeal;
import common.UserType;
import common.Users;
import common.W4C_Card;
import common.waiting_account;



public class mysqlConnection {
	static Connection conn;
	private static Users user1=null;
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
 
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static Object checkUserLogIn(String userName,String passWord) {
		PreparedStatement ps;
		ResultSet res;
		ArrayList<Object> arr=new ArrayList<>();
		Users user=null;
		try {
			ps = mysqlConnection.conn.prepareStatement("Select * From bitemedb.users where username=? and password=?");
			ps.setString(1, userName);
			ps.setString(2, passWord);
			ps.execute();
			res=ps.getResultSet();
			if(!res.next())
				return null;
			user=new Users(res.getString(1),res.getString(2),res.getString(3),true ,UserType.valueOf(res.getString(5))
					,res.getString(6),res.getString(7),res.getString(8),res.getString(9),res.getString(10));
			System.out.println(res.toString());
			ps = mysqlConnection.conn.prepareStatement("UPDATE bitemedb.users SET IsloggedIN =? where username=?");
			ps.setInt(1, 0); //// changed to 1
			ps.setString(2, userName);
			ps.execute();
			
			arr.add(user);///0
			user1=user;
			/////////////////////////////
			switch (user.getType()) {
			case Supplier:
				Resturant resturant = null;
				System.out.println(user.getId());
				ps = mysqlConnection.conn.prepareStatement("Select * From bitemedb.resturants where ResturantId=?");

				ps.setString(1, user.getId());
				ps.execute();
				res = ps.getResultSet();
				System.out.println(res.next());
				

				resturant = new Resturant(res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5));
				System.out.println(resturant);
				arr.add(resturant);///////1
				break;
		        case HR:
				HoumanResources HR = null;
				ps=mysqlConnection.conn.prepareStatement("Select * From bitemedb.human_resources where ID=?");
				ps.setString(1, user.getId());
				ps.execute();
				res=ps.getResultSet();
				res.next();
				
				HR = new HoumanResources(res.getString(1), res.getString(2));
				arr.add(HR);//////////2
				break;
			}
			
			
			
			
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
	public static void setOrderinDB(String customer_ID, String resturant, String requestDate, String orderedDate,
			String totalPrice, String address, String deleiveryService, String status, String arrivalTime,
			String approvalRecieving, String branchlocation, String year, String month, String day,
			String arrivedToCustomerTime, String orderReadyTime)  {
		PreparedStatement ps;
		try {
			ps=mysqlConnection.conn.prepareStatement("Insert Into bitemedb.order_list (Customer_ID, Resturant, RequestDate,"
					+ " OrderDate, TotalPrice, Address, DeleiveryService, Status, ArrivalTime, ApprovalRecieving,"
					+ "Branch,year,month,day,ArrivedToCustomerTime,OrderReadyTime) Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1,customer_ID );
			ps.setString(2,resturant );
			ps.setString(3, requestDate);
			ps.setString(4, orderedDate);
			ps.setString(5, totalPrice);
			ps.setString(6,address );
			ps.setString(7, deleiveryService);
			ps.setInt(8,3 );
			ps.setString(9,arrivalTime);
			ps.setInt(10,2);
			ps.setString(11, branchlocation);
			ps.setString(12,year);
			ps.setString(13,month);
			ps.setInt(14,Integer.valueOf(day));
			ps.setString(15,arrivedToCustomerTime);
			ps.setString(16,orderReadyTime);
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
				System.out.println(res.getInt(3));
				order=new OrdersList(res.getString(1),res.getString(2),res.getInt(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7)
						,res.getString(8),res.getString(9),res.getString(10),res.getString(11),res.getString(12),res.getString(13),res.getString(14),res.getInt(15)
						,res.getString(16),res.getString(17));
				
				System.out.println(res.getString(1)+" "+res.getString(2)+" "+res.getInt(3)+" "+res.getString(4)+" "+res.getString(5)+" "+res.getString(6)+" "+res.getString(7)
						+" "+res.getString(8)+" "+res.getString(9)+" "+res.getString(10)+" "+res.getString(11)+" "+res.getString(12)+" "+res.getString(13)+" "+res.getString(14)+" "+
						res.getInt(15)+" "+res.getString(16)+" "+res.getString(17));
				
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
					if(res.getString(5).equals(Location)) {
					temp=new Resturants(res.getString(1), res.getString(2),res.getString(3),res.getString(4),res.getString(5));
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
		
		
	 /* public static ArrayList<OrdersList> BuildOrderTable() {
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
			ps.setString(1, user1.getId());
			ps.execute();
			res=ps.getResultSet();
			while(res.next()) 
				 {

				if(res.getInt(3)==39) {
				}
					Order_list.add(new OrdersList(res.getString(1), res.getString(2), res.getInt(3),
							res.getString(4), res.getString(5), res.getString(6), res.getString(7),
								res.getString(8),res.getString(9),res.getString(10),res.getString(11),res.getString(12)),);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Order_list;
	}*/
	
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
		
		/*public static ArrayList<OrdersList> EditBuildOrderTable(Integer Package) {
			PaCkage=Package;
			Flag=true;
			return BuildOrderTable();
		}*/

		public static void RefundBuild(String CustomerID, String ResturantID, String refund) {
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
								
					}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		public static Refund getRefundFromdb (String customerid,String resturantid) {
			PreparedStatement ps;
			PreparedStatement ps1;
			ResultSet res;
			ResultSet res2;
			Refund ref=null;
			try {
				ps = mysqlConnection.conn.prepareStatement("SELECT * FROM bitemedb.refund where Customer_ID =? and ResturantId =?");
				ps.setString(1, customerid);
				ps.setString(2, resturantid);
				ps.execute();
				res=ps.getResultSet();
				if(!res.next())
				{
					ps1=mysqlConnection.conn.prepareStatement("Insert into bitemedb.refund Values (?,?,?)");
					ps1.setString(1,customerid);
					ps1.setString(2, resturantid);
					ps1.setString(3, String.valueOf(0));
					ps1.execute();
							 ps1=mysqlConnection.conn.prepareStatement("SELECT * FROM bitemedb.refund where Customer_ID =? and ResturantId =?");
						     ps1.setString(1,customerid);
							 ps1.setString(2, resturantid);
							 ps1.execute();
							 res2=ps.getResultSet();
							 if(!res2.next())
								 return null;
							 else
							 ref=new Refund(res2.getString(1),res2.getString(2),res2.getString(3));
				}
				ref=new Refund(res.getString(1),res.getString(2),res.getString(3));
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();}
			return ref;
		}
		
		public static void UpdateRefund (String customerid,String resturantid,String ref) {
			PreparedStatement ps;
			ResultSet res;
			try {
				System.out.println(ref);
				ps=mysqlConnection.conn.prepareStatement("update bitemedb.refund set Refund=? where Customer_ID =? and ResturantId =? ");
				ps.setString(1, ref);
				ps.setString(2,customerid);
				ps.setString(3, resturantid);
				ps.execute();
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();}
		}
		
		
		
		
		
		
		public static void userLogOut(Users user) {
			PreparedStatement ps;
			try {
				ps = mysqlConnection.conn.prepareStatement("UPDATE bitemedb.users SET isLoggedIn =0 where username=?");
				ps.setString(1, user.getUserName());
				ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		public static ArrayList<DishForResturant> getResturantDishes(String resturantId) {

			PreparedStatement ps,ps1,ps2;
			ResultSet res, res1,res2;
			String TypeID,DishID;
			ArrayList<DishForResturant> dishes = new ArrayList<DishForResturant>();
			DishForResturant dish;
			StringBuilder stringBuilder = new StringBuilder();//////////////////////////////////////////////////////////////////////////
			
			
			try {
				ps = mysqlConnection.conn.prepareStatement("SELECT * FROM bitemedb.typemeal where ResturantId=?");
				ps.setString(1, resturantId);
				ps.execute();
				res= ps.getResultSet();
				while(res.next()) {//while there are more types for this restaurant
					TypeID=res.getString(2);
					ps1= mysqlConnection.conn.prepareStatement("SELECT * FROM bitemedb.dish where Typemealid=?");
					ps1.setString(1, TypeID);
					ps1.execute();
					res1=ps1.getResultSet();
					while(res1.next())
					{
						DishID=res1.getString(2);
						ps2=mysqlConnection.conn.prepareStatement("SELECT * FROM bitemedb.selection where DishId=?");
						ps2.setString(1, DishID);
						ps2.execute();
						res2=ps2.getResultSet();
						while(res2.next())
						{
							if(res2.getInt(4)==0) {
							stringBuilder.append(res2.getString(3));/////////////////////////////////////////////////////
							stringBuilder.append("\n");}else {
								stringBuilder.append(res2.getString(3)+" +"+res2.getInt(4));
								stringBuilder.append("\n");
							}
							
						}
						res2.close();
						
						dish=new DishForResturant(resturantId,res.getString(3),
								res1.getString(3),stringBuilder.toString(),res1.getString(2),res1.getInt(4));
						dishes.add(dish);
						stringBuilder =new StringBuilder();
						
					}//while res1 dishes
					
				}//while res types
			}catch(SQLException e) {
				e.printStackTrace();
			}/////////////////////////////////////////////////////////////////////////////////////////////
			
			

			return dishes;
		}
		
		

		
		public static void deleteDish(DishForResturant dish) {
			PreparedStatement ps,ps1;
		ResultSet res,res1;
		String typeID;
			
			try {
				System.out.println(123);
				System.out.println(Integer.valueOf(dish.getMealId()));
				ps=mysqlConnection.conn.prepareStatement("SELECT * FROM bitemedb.dish where DishId=?");
				ps.setInt(1, Integer.valueOf(dish.getMealId()));
				ps.execute();
				res=ps.getResultSet();
				res.next();
				typeID=res.getString(1);
				System.out.println(res.getInt(1));
				
				
				//
				////
				// 
				
				
				
				////////////////////////////////////////////// we don't dalete the types becuase there is only one appearing in DB if we delete it we will lose all of the dishes
				/////////////////////////////////////////////for getting the menu the program os not taking types with no dishes(so it can't affect on the menu view)  :)..
				ps = mysqlConnection.conn.prepareStatement("DELETE FROM bitemedb.selection WHERE DishId=?");
				ps.setString(1, dish.getMealId());
				ps.execute();
				ps = mysqlConnection.conn.prepareStatement("DELETE FROM bitemedb.dish WHERE DishId=?");
				ps.setString(1, dish.getMealId());
				ps.execute();
				
				ps1=mysqlConnection.conn.prepareStatement("SELECT * FROM bitemedb.dish where DishId=?");
				ps1.setInt(1, Integer.valueOf(dish.getMealId()));
				ps1.execute();
				res1=ps1.getResultSet();
				
			if(!res1.next()) {
				ps = mysqlConnection.conn.prepareStatement("DELETE FROM bitemedb.typemeal WHERE TypemealId=?");
				ps.setInt(1, Integer.valueOf(typeID));}
				
				res1.close();
				res.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static ArrayList<OptionalIngredients>getOptionalIngredients(String dishID){
			PreparedStatement ps;
			ResultSet res;
			ArrayList<OptionalIngredients> optionalIngredients=new ArrayList<OptionalIngredients>();
			try {
				ps = mysqlConnection.conn.prepareStatement("SELECT * FROM bitemedb.selection where dishID=?");
				ps.setString(1, dishID);
				ps.execute();
				res = ps.getResultSet();
				while (res.next()) {
					optionalIngredients.add(new OptionalIngredients(res.getString(3), res.getInt(4), dishID, res.getInt(2)));
				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return optionalIngredients;
		}

		public static boolean AddItem(DishForResturant dish) {//we have the restaurant id!!
			PreparedStatement ps,ps1,ps2;
			ResultSet res,res1;
			String[] split=dish.getOptionalIngredients().split("\n");
			try {
				
				ps=mysqlConnection.conn.prepareStatement("SELECT * FROM bitemedb.typemeal where ResturantId=? and TybeMeal=?");
				ps.setString(1, dish.getResturantId());
				ps.setString(2, dish.getMealType());
				ps.execute();
				res=ps.getResultSet();//getting the the meal type from DB be in case it exists
				
				if(!res.next()) {//if there is no type like this we adding it to TypeMeal table
				ps= mysqlConnection.conn.prepareStatement("insert into bitemedb.typemeal(ResturantId,TybeMeal) values(?,?)");
				ps.setString(1, dish.getResturantId());
				ps.setString(2, dish.getMealType());
				ps.execute();
				}
				 
				
				
				ps=mysqlConnection.conn.prepareStatement("SELECT * FROM bitemedb.typemeal where ResturantId=? and TybeMeal=?");
				ps.setString(1, dish.getResturantId());
				ps.setString(2, dish.getMealType());///////get the new type meal id
				ps.execute();
				
				res=ps.getResultSet();
				res.next();
				ps1= mysqlConnection.conn.prepareStatement("insert into bitemedb.dish(TypemealId,Dish,DishPrice) values(?,?,?)");
				ps1.setString(1,res.getString(2));//get the meal type id/////////////
				ps1.setString(2, dish.getMealName());
				ps1.setInt(3, dish.getPrice());//add the dish and its price,, here we have new dish id that we didn't saved yet
				ps1.execute();
				
				ps1=mysqlConnection.conn.prepareStatement("SELECT * FROM bitemedb.dish where TypemealId=? and Dish=?");
				ps1.setInt(1,res.getInt(2));
				ps1.setString(2,dish.getMealName());
				ps1.execute();
				
				res1=ps1.getResultSet();//get the new dish id
				res1.next();
				
				
				
				if(!dish.getOptionalIngredients().equals("")) {
				for(int i=0; i<split.length;i++)
				{
					String[] split2=split[i].split(",");
					ps2= mysqlConnection.conn.prepareStatement("insert into bitemedb.selection (DishId,Selection,SelectionPrice) values(?,?,?)");
					ps2.setString(1,res1.getString(2));
					ps2.setString(2,split2[0]);
					ps2.setString(3,split2[1]);
					ps2.execute();
					
				}}
				res.close();
				res1.close();
				
				
				
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println(e.getMessage());
				return false;
			}


			
			// TODO Auto-generated method stub
			return true;
		}

		public static boolean AddOption(OptionalIngredients Option) {
			PreparedStatement ps;
			try {
				ps=mysqlConnection.conn.prepareStatement("insert into bitemedb.selection (DishId,Selection,SelectionPrice) values(?,?,?)");
				ps.setString(1, Option.getDishID());
				ps.setString(2, Option.getOption());
				ps.setInt(3, Option.getPrice());
				ps.execute();
			} catch (SQLException e) {
				// TODO: handle exception
			e.printStackTrace();
			return false;
			}
			System.out.println("hiiiiiiiiii bitch");
			return true;
		}

		public static void deleteOption(int Option) {
			PreparedStatement ps;
			try {
				ps=mysqlConnection.conn.prepareStatement("DELETE FROM bitemedb.selection WHERE SelectionId=?");
				ps.setInt(1, Option);
				ps.execute();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		public static Boolean updateItem(DishForResturant dish) {
			PreparedStatement ps;
			ResultSet res;
		
			try {
				
				
				// ps= mysqlConnection.conn.prepareStatement("update bitemedb.order_list set ApprovalRecieving=? ,ApproveTime=?  where OrderPackageNumber=?");
				ps= mysqlConnection.conn.prepareStatement(" update bitemedb.dish set Dish=?,DishPrice=? where DishId=? ");
				ps.setString(1, dish.getMealName());
				ps.setInt(2, dish.getPrice());
				ps.setString(3, dish.getMealId());
				ps.execute();
				
				
				
				
				
				
				
				 
				
				
				
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
			
			return true;
		}////////////////////////////////////////update

		/////////////////////////////////////////////////////////////////////////////////////////////////
		public static ArrayList<OrdersForRes> GetResturantOrders(Resturant resturant) {
			ArrayList<OrdersForRes> orders = new ArrayList<>();
			PreparedStatement ps;
			ResultSet res;
		
			try {
				
				ps= mysqlConnection.conn.prepareStatement("SELECT * FROM bitemedb.order_list where Resturant=? and ApprovalRecieving=? and Branch=?");
				ps.setString(1, resturant.getId());
				ps.setString(2, "Approved");
				ps.setString(3, resturant.getBranch());
				ps.execute();
				 res=ps.getResultSet();
				
				 while(res.next())
				 {
					 
					
					 
					 orders.add(new OrdersForRes(res.getString(1), resturant.getResturantName(), res.getInt(3), res.getString(4), res.getString(5), res.getString(6),
							
							 res.getString(7), res.getString(8), res.getString(9), res.getString(10), res.getString(11)));
					 
					 
					 
					 
					
				 }
				 res.close();
				
				 
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		
			
			
			
			
			
			
			
			return orders;
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////

		public static boolean ApproveItem(int object) {
			PreparedStatement ps;
			ResultSet res;
			try {
				ps= mysqlConnection.conn.prepareStatement("SELECT * FROM bitemedb.order_list where OrderPackageNumber=?");
				ps.setInt(1, object);
				ps.execute();
				res=ps.getResultSet();
				res.next();
				  if(res.getString(11).equals("Approved"))
					  return false;
				  
				  ps= mysqlConnection.conn.prepareStatement("update bitemedb.order_list set ApprovalRecieving=? ,Status=?  where OrderPackageNumber=?");
				  ps.setString(1, "Approved");
				 ps.setInt(2, 2);
				  ps.setInt(3, object);
				  ps.execute();
				  
				  //UPDATE `bitemedb`.`dish` SET `dishprice` = ? WHERE (`dishID` = ?)
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
			
			return true;
		}

		public static ArrayList<OrderDish> GetOrdersDishes(int OrderNum) {
			ArrayList<OrderDish> OrderDishes= new ArrayList<>();
			PreparedStatement ps;
			ResultSet res;
			 try {
				ps=mysqlConnection.conn.prepareStatement("select * from bitemedb.item_list where PackageID=?");
				ps.setInt(1, OrderNum);
				ps.execute();
				res=ps.getResultSet();
				while(res.next()) {
					OrderDishes.add(new OrderDish(res.getString(1), res.getString(2), res.getString(3), res.getInt(5), res.getInt(4)));
					
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			 return OrderDishes;
			
		}

		public static void UpdateStatus(int OrderNumber, String Status) {
			PreparedStatement ps;
			
			try {
				 ps= mysqlConnection.conn.prepareStatement("Update bitemedb.order_list set Status=? where OrderPackageNumber=? ");
				 ps.setString(1, Status);
				 ps.setInt(2, OrderNumber);
				 ps.execute();
				 
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			 
			
		}

		public static ArrayList<waiting_account> GetWaitingAccounts(String CompanyName) {
			
			PreparedStatement ps;
			ResultSet res;
			ArrayList<waiting_account> accounts=new ArrayList<>();
			try {
				ps = mysqlConnection.conn.prepareStatement("select * from bitemedb.waiting_accounts where CompanyName=?");
				ps.setString(1, CompanyName);
				ps.execute();
				 res= ps.getResultSet();
				
					 
					 while(res.next()) {
					 accounts.add(new waiting_account(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), CompanyName,res.getString(7) , res.getString(8), res.getInt(9), res.getString(10)));
					 System.out.println(res.getString(6)+res.getString(1));
					 }
					
				 
				 res.close();
			} catch (SQLException e) {
			e.printStackTrace();
			}
			
			
			return accounts;
		}
		
		

		public static void ApproveBusinessAccount(waiting_account account) {
			
			PreparedStatement ps;
			ResultSet res;
			String W4C;
			 try {
				 
				  
				 ps= mysqlConnection.conn.prepareStatement("delete from bitemedb.waiting_accounts where ID=? ");
				 ps.setString(1, account.getID());
				 ps.execute();
				 
				 
				  
				 ps =mysqlConnection.conn.prepareStatement("select * from bitemedb.w4c_card where CreditCard=?");
				 ps.setString(1, account.getCreditCard());
				 ps.execute();
				 res=ps.getResultSet();
				 
				 if(!res.next()) {//if there is no private account for this customer
					
					 ps=mysqlConnection.conn.prepareStatement("insert into bitemedb.w4c_card (CreditCard,accounttype) values(?,?)");
					 ps.setString(1, account.getCreditCard());
					 ps.setString(2, "business");
					 ps.execute();
					 
					 
					 ps =mysqlConnection.conn.prepareStatement("select * from bitemedb.w4c_card where CreditCard=?");
					 ps.setString(1, account.getCreditCard());
					 ps.execute();
					 res=ps.getResultSet();
					 res.next();
					 W4C=String.valueOf(res.getInt(1));
					
				 }else {// if there is private account for this customer
					 W4C=String.valueOf(res.getInt(1));
					 
				 }
				
				 
				 ps=mysqlConnection.conn.prepareStatement("insert into bitemedb.account values(?,?,?,?,?,?)");
				 ps.setString(1, account.getID());
				 ps.setString(2, account.getFirstName());
				 ps.setString(3, account.getLastName());
				 ps.setString(4, account.getPhoneNumber());
				 ps.setString(5, account.getEmail());
				 ps.setString(6, W4C);
				 ps.execute();
				
				 ps=mysqlConnection.conn.prepareStatement("insert into bitemedb.business values(?,?,?,?)");
				 ps.setString(1, W4C);
				 ps.setString(2, account.getEmplyerID());
				 ps.setString(3, account.getCompanyName());
				 ps.setString(4, String.valueOf(account.getCeiling()));
				 ps.execute();
				 
				 
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}

		public static boolean AddEmployer(String ID, String Name,String HRID) {
			PreparedStatement ps;
			ResultSet res;
			try {
				ps=mysqlConnection.conn.prepareStatement("select * from bitemedb.users where ID=? and userType=? ");
				ps.setString(1, ID);
				ps.setString(2, "UnKnown");
				ps.execute();
				res=ps.getResultSet();
				if(!res.next()) {
					return false;
				}else {
					ps=mysqlConnection.conn.prepareStatement("update bitemedb.users set userType=? where ID=?");
					ps.setString(1, "Employer");
					ps.setString(2, ID);
					ps.execute();
					
					ps=mysqlConnection.conn.prepareStatement("insert into bitemedb.employer values(?,?,?,?)");
					ps.setString(1, ID);
					ps.setString(2, Name);
					ps.setString(3, HRID);
					ps.setInt(4, 1);
					ps.execute();
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return true;
		}

		public static ArrayList<OrdersForRes> GetWaitingOrders(Resturant resturant) {
			
			ArrayList<OrdersForRes> orders = new ArrayList<>();
			PreparedStatement ps;
			ResultSet res;
		
			try {
				
				ps= mysqlConnection.conn.prepareStatement("SELECT * FROM bitemedb.order_list where Resturant=? and ApprovalRecieving=? and Branch=?");
				ps.setString(1, resturant.getId());
				ps.setString(2, "Waiting for approve");
				ps.setString(3, resturant.getBranch());
				ps.execute();
				 res=ps.getResultSet();
				
				 while(res.next())
				 {
					 
					
					 
					 orders.add(new OrdersForRes(res.getString(1), resturant.getResturantName(), res.getInt(3), res.getString(4), res.getString(5), res.getString(6),
							
							 res.getString(7), res.getString(8), res.getString(9), res.getString(10), res.getString(11)));
					 
		
				 }
				 res.close();
				
				 
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	
			return orders;
		}
		
		
		public static Account getCustomerDetails (String customerId) {
			PreparedStatement ps;
			ResultSet res;
			Account cus=null;
			try {
				ps=mysqlConnection.conn.prepareStatement("SELECT * From bitemedb.account Where ID=?");
				ps.setString(1, customerId);
				ps.execute();
				res=ps.getResultSet();
				if(!res.next())
					return null;
				cus=new Account (res.getString(1), res.getString(2),res.getString(3),res.getString(4), res.getString(5),res.getString(6),res.getString(7));
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return cus;
		}

		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
