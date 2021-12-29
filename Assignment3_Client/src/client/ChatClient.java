// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import ocsf.client.*;
import client.*;
import common.Account;
import common.Business;
import common.ChatIF;
import common.Dish;
import common.DishForResturant;
import common.HoumanResources;
import common.ItemInCart;
import common.ItemList;
import common.Message;
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
import common.Users;
import common.W4C_Card;
import common.waiting_account;
import controllers.AddEmployerController;
import controllers.AddItemController;
import controllers.EditOptionalIngredientsController;
import controllers.WaitingOrdersController;

import java.io.*;
import java.util.ArrayList;

/** majd
 * This class overrides some of the methods defined in the abstract superclass
 * in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient extends AbstractClient {
	// Instance variables **********************************************
	//customer
	public static Users userlogged;
	public static W4C_Card w4ccard;
	public static Account accounts;
	public static Business bussiness;
	public static OrdersList order;
	public static OrdersList order2;
	public static ItemList items;
	public static ArrayList<Resturants> resturants;
	public static ArrayList<TybeMeal> tybemeal;
	public static ArrayList<Dish> dish;
	public static ArrayList<Selection> selection;
	public static ArrayList<OrdersList> OrderBuild ;
	public static ArrayList<ItemList> ItemBuild ;
	public static Refund RefundBuild ;
	//resturant
	public static Resturant resturant;
	public static ArrayList<DishForResturant> dishes;
	public static ArrayList<OrdersForRes> orders;
	public static ArrayList<OptionalIngredients> optionalIngredients;
	public static ArrayList<OrdersForRes> WaitingOrders;
	public static ArrayList<OrderDish> OrdersDishes;
	//hr
	public static ArrayList<waiting_account> WaitingAccounts;
	public static HoumanResources HR;
	
	
	
	
	/**
	 * The interface type variable. It allows the implementation of the display
	 * method in the client.
	 */
	ChatIF clientUI;

	public static boolean awaitResponse = false;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the chat client.
	 *
	 * @param host     The server to connect to.
	 * @param port     The port number to connect on.
	 * @param clientUI The interface type variable.
	 */

	public ChatClient(String host, int port, ChatIF clientUI) throws IOException {
		super(host, port); // Call the superclass constructor
		this.clientUI = clientUI;
		// openConnection();
	}

	// Instance methods ************************************************

	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg The message from the server.
	 */
	@SuppressWarnings("unchecked")
	public void handleMessageFromServer(Object msg) {
		
		awaitResponse = false;
		System.out.println("--> handleMessageFromServer");
		Message m = (Message) msg;
		
		//System.out.println((String) m.getObject());

		if (m.getMessageType().equals(MessageType.login)) {
			ArrayList<Object> arr=(ArrayList<Object>)m.getObject();
			userlogged = (Users)(arr.get(0));
			if(m.getObject()==null)
				userlogged=null;
			else {
				switch (userlogged.getType()) {
				case Supplier:
					resturant=(Resturant)arr.get(1);
					break;
				case HR:
					HR=(HoumanResources)arr.get(1);
					System.out.println(HR.getID());
					System.out.println(HR.getCompanyName());
					break;
				default:
					break;
				}
			
			}
			
		}
		
		switch (m.getMessageType()) {
		case scan :
			accounts = (Account) m.getObject(); 
			break;
		case w4cCard:
			w4ccard = (W4C_Card) m.getObject();
			break;
		case ViewResturants:
			resturants=(ArrayList<Resturants>)m.getObject();
			break;
		case ViewTybeMeallist:
			tybemeal=(ArrayList<TybeMeal>)m.getObject();
			break;
		case ViewDishList:
			dish=(ArrayList<Dish>)m.getObject();
			break;
		case ViewSelctionsList:
			selection=(ArrayList<Selection>)m.getObject();
			break;
		case bussinessAccounts:
			bussiness=(Business)m.getObject();
			break;
		case OrdersListToDataBase:
			order=(OrdersList)m.getObject();
			break;
		case GetOrder:
			order2=(OrdersList)m.getObject();
			break;
		case itemsListtoDataBase:
			items=(ItemList)m.getObject();
			break;
		case OrderListBuild:
			OrderBuild = (ArrayList<OrdersList>)m.getObject();
			break;
		case ItemList:
			ItemBuild = (ArrayList<ItemList>)m.getObject();
			break;
			
		case getDishesFromResturant:
			 dishes = (ArrayList<DishForResturant>)m.getObject();
			break;
			
		case getOptionalIngredients:
			optionalIngredients=(ArrayList<OptionalIngredients>)m.getObject();
			break;
			
		case additem:
			AddItemController.add=(boolean)m.getObject();
			break;
		case AddOption:
			EditOptionalIngredientsController.AddOption=(boolean)m.getObject();
			break;
			
		case UpdateItem:
			EditOptionalIngredientsController.Update=(boolean)m.getObject();
			break;
		case GetResturantOrders:
			orders=(ArrayList<OrdersForRes>)m.getObject();
			break;
		case GetWaitingOrders:
			WaitingOrders=(ArrayList<OrdersForRes>)m.getObject();
			break;
		case approveItem:
			WaitingOrdersController.approvebool=(Boolean)m.getObject();
			break;
		case GetOrdersDishes:
			OrdersDishes=(ArrayList<OrderDish>)m.getObject();
			break;
		case GetEmployees:
			WaitingAccounts = (ArrayList<waiting_account>)m.getObject();
			break;
		case AddEmployer:
			AddEmployerController.added=(Boolean)m.getObject();
			break;
			
			
			
			
	
		/*case:
			break;*/
			
		default:
			break;
		}
		
		
		
		
		

		
		
		
	
		
	
		}
		/*if (m.getMessageType().equals(MessageType.RefundAdd)) {
			RefundBuild = (Refund)m.getObject();
		}*/



	/**
	 * This method handles all data coming from the UI
	 *
	 * @param message The message from the UI.
	 */

	public void handleMessageFromClientUI(Object message) {
		try {
			openConnection();// in order to send more than one message
			awaitResponse = true;
			sendToServer(message);
			// wait for response
			while (awaitResponse) {
				try {
					Thread.sleep(100);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			clientUI.display("Could not send message to server: Terminating client." + e);
			quit();
		}
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}
}
//End of ChatClient class
