package server;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.mysql.cj.protocol.Message;
import com.sun.media.jfxmedia.events.NewFrameEvent;

import common.*;
import controllers.ServerConnectionController;
import javafx.scene.control.Label;

import ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */

public class EchoServer extends AbstractServer {
	// Class variables *************************************************

	/**
	 * The default port to listen on.
	 */
	// final public static int DEFAULT_PORT = 5555;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the echo server.
	 *
	 * @param port The port number to connect on.
	 * 
	 */

	public EchoServer(int port) {
		super(port);
	}

	// Instance methods ************************************************

	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg    The message received from the client.
	 * @param client The connection from which the message originated.
	 * @param
	 */
	public void handleMessageFromClient(Object msg, ConnectionToClient client) {

		Message1 m = (Message1) msg;
		String message[];
		try {
			switch (m.getMessageType()) {
			
			case connect:
				message = ((String) m.getObject()).split(" ");
				ServerConnectionController.clients_list.add(new Client(message[0], message[1], message[2]));
				client.sendToClient(new Message1(null, null));
				break;
				
			case disconnect:
				message = ((String) m.getObject()).split(" ");
				ServerConnectionController.clients_list.add(new Client(message[0], message[1], message[2]));
				client.sendToClient(new Message1(null, null));
				break;
				
			case login:
				message = ((String) m.getObject()).split(" ");
				Object user=mysqlConnection.checkUserLogIn(message[0], message[1]);
				client.sendToClient(new Message1(MessageType.login, user));
				break;
				
			case w4cCard:
	            message = ((String) m.getObject()).split(" ");
	            W4C_Card w4c=mysqlConnection.getW4cInformationfromDB(message[0]);
	            client.sendToClient(new Message1(MessageType.w4cCard, w4c));
				break;
				
			case scan:
				message = ((String) m.getObject()).split(" ");
				Account account=mysqlConnection.getAccountListFromDB(message[0]);
				client.sendToClient(new Message1(MessageType.scan, account));
				break;
				
			case ViewResturants:
				message = ((String) m.getObject()).split(" ");
				ArrayList<Resturants> resturant;
				resturant =mysqlConnection.getResturantsListFromDB(message[0]);
				client.sendToClient(new Message1(MessageType.ViewResturants, resturant));
				break;
				
			case ViewTybeMeallist:
				message = ((String) m.getObject()).split(" ");
				ArrayList<TybeMeal> tybemeal;
				tybemeal=mysqlConnection.getTybeMealListFromDB(message[0]);
				client.sendToClient(new Message1(MessageType.ViewTybeMeallist,tybemeal));	
				break;
				
			case ViewDishList:
				message=((String)m.getObject()).split(" ");
				ArrayList<Dish> Dish;
				Dish=mysqlConnection.getDishListFromDB(message[0]);
				client.sendToClient(new Message1(MessageType.ViewDishList, Dish));
				break;
				
			case ViewSelctionsList:
				message=((String)m.getObject()).split(" ");
				ArrayList<Selection> selection;	
			   	selection=mysqlConnection.getSelectionListFromDB(message[0]);
				client.sendToClient(new Message1(MessageType.ViewSelctionsList, selection));
				break;
				
			case bussinessAccounts:
				message=((String)m.getObject()).split(" ");
				Business bussiness=mysqlConnection.getBussinessInformationfromDB(message[0]);
				client.sendToClient(new Message1(MessageType.bussinessAccounts, bussiness));
				break;
			case OrdersListToDataBase:
				message=((String)m.getObject()).split(" ");
				mysqlConnection.setOrderinDB(message[0], message[1], message[2], message[3],
						message[4], message[5], message[6], message[7],message[8], message[9],
						message[10],message[11],message[12],(message[13]),message[14],message[15]);
				client.sendToClient(new Message1(MessageType.OrdersListToDataBase, null));
				break;
			case itemsListtoDataBase:
				message=((String)m.getObject()).split(" ");
				mysqlConnection.SetItemsOfOrderinDB(message[0],message[1], message[2], Integer.valueOf(message[3]),Integer.valueOf(message[4]),message[5]);
				client.sendToClient(new Message1(MessageType.itemsListtoDataBase, null));
				break;
		case OrderListBuild:
				message=((String)m.getObject()).split(" ");
				ArrayList<OrdersList> Order_list;
				Order_list=mysqlConnection.BuildOrderTable(message[0]);
				client.sendToClient(new Message1(MessageType.OrderListBuild, Order_list));
				break;
			case ItemList:
				Integer message1;
				ArrayList<ItemList> Item_list;
				message1 =(Integer)m.getObject();
				Item_list=mysqlConnection.BuildItemList(message1);
				client.sendToClient(new Message1(MessageType.ItemList, Item_list));
				break;
			case GetOrder:
				message=((String)m.getObject()).split(" ");
				OrdersList order;
				System.out.println(message[0]);
				order =mysqlConnection.getOrder(message[0]);
				client.sendToClient(new Message1(MessageType.GetOrder, order));
				break;
			case updateCelling:
				message=((String)m.getObject()).split(" ");
				mysqlConnection.updatecelling(message[0],message[1]);
				client.sendToClient(new Message1(MessageType.updateCelling, null));
				break;
			case OrderListBuildEdit:
				message=((String)m.getObject()).split(" ");
				System.out.println(message[0]+" "+message[1]);
				mysqlConnection.EditBuildOrderTable((message[0]),message[1]);
				client.sendToClient(new Message1(MessageType.OrderListBuild, null));
				break;
			case RefundAdd:
				message=((String)m.getObject()).split(" ");
				mysqlConnection.RefundBuild(message[0], message[1], message[2]);
                client.sendToClient(new Message1(MessageType.RefundAdd, null));
				break;
			case logout:
				mysqlConnection.userLogOut((Users) m.getObject());
				client.sendToClient(new Message1(MessageType.logout, ""));
				break;
			case getRefund:
				Refund refund;
				message=((String)m.getObject()).split(" ");
				refund=mysqlConnection.getRefundFromdb(message[0],message[1]);
				client.sendToClient(new Message1(MessageType.getRefund, refund));
				break;
			case updateRegund:
				message=((String)m.getObject()).split(" ");
				mysqlConnection.UpdateRefund(message[0],message[1],message[2]);
				client.sendToClient(new Message1(MessageType.updateRegund,null));
				break;
			case getCustomer:
				message=((String)m.getObject()).split(" ");
				Account customer=mysqlConnection.getCustomerDetails(message[0]);
				client.sendToClient(new Message1(MessageType.getCustomer, customer));
				break;
				
				
			case getDishesFromResturant:
				ArrayList<DishForResturant> dishes = mysqlConnection.getResturantDishes((String) m.getObject());
				client.sendToClient(new Message1(MessageType.getDishesFromResturant, dishes));
				break;
	
			case deleteDish:

				System.out.println(((DishForResturant) m.getObject()).getMealId());
				mysqlConnection.deleteDish((DishForResturant) m.getObject());
				client.sendToClient(new Message1(MessageType.deleteDish, null));
				break;
			case getOptionalIngredients:
				System.out.println(((DishForResturant) m.getObject()));
				ArrayList<OptionalIngredients> optionalIngredients = mysqlConnection.getOptionalIngredients(((DishForResturant) m.getObject()).getMealId());
				client.sendToClient(new Message1(MessageType.getOptionalIngredients, optionalIngredients));
				break;
			case additem:

				boolean add = mysqlConnection.AddItem((DishForResturant) m.getObject());
				client.sendToClient(new Message1(MessageType.additem, add));
				break;
			case AddOption:

				boolean AddOption = mysqlConnection.AddOption((OptionalIngredients) m.getObject());
				client.sendToClient(new Message1(MessageType.AddOption, AddOption));
				break;
			case DeleteOption:

				mysqlConnection.deleteOption(((OptionalIngredients) m.getObject()).getSelectionID());

				client.sendToClient(new Message1(MessageType.DeleteOption, null));

				break;
			case UpdateItem:
				Boolean update;
				update = mysqlConnection.updateItem((DishForResturant) m.getObject());
				client.sendToClient(new Message1(MessageType.UpdateItem, update));
				break;
			case GetResturantOrders:
				
				ArrayList<OrdersForRes> orders = mysqlConnection.GetResturantOrders((Resturant)m.getObject());
				
				client.sendToClient(new Message1(MessageType.GetResturantOrders, orders));
				break;
				
			case GetWaitingOrders:
				ArrayList<OrdersForRes> Waitingorders=mysqlConnection.GetWaitingOrders((Resturant)m.getObject());
				client.sendToClient(new Message1(MessageType.GetWaitingOrders, Waitingorders));
				
				break;
			case approveOrder:
			//	String[] split=m.getObject().toString().split(" ");
				Integer Number=Integer.valueOf(m.getObject().toString());
				boolean approve = mysqlConnection.ApproveOrder(Number);
				client.sendToClient(new Message1(MessageType.approveOrder, approve));
				break;
			case GetOrdersDishes:
				ArrayList<OrderDish> Orderdishes=mysqlConnection.GetOrdersDishes((int)m.getObject());
				client.sendToClient(new Message1(MessageType.GetOrdersDishes, Orderdishes));
				break;
			case UpdateStatus:
				
				String str=(String)m.getObject();
				
				String[]split1=str.split(",");
				System.out.println(split1[0]+split1[1]);
				mysqlConnection.UpdateStatus(Integer.parseInt(split1[0]),split1[1]);
				client.sendToClient(new Message1(MessageType.UpdateStatus, null));
			break;
			
			case GetEmployees:
				ArrayList<waiting_account> accounts =mysqlConnection.GetWaitingAccounts((String)m.getObject());		
				client.sendToClient(new Message1(MessageType.GetEmployees, accounts));
				break;
				
			case ApproveaccountB:
				mysqlConnection.ApproveBusinessAccount((waiting_account)m.getObject());
				client.sendToClient(new Message1(MessageType.ApproveaccountB, null));
				break;
				
			case AddEmployer:
				String[] split2= ((String)m.getObject()).split(" ");
				boolean added=mysqlConnection.AddEmployer(split2[0],split2[1],split2[2]);
				client.sendToClient(new Message1(MessageType.AddEmployer, added));
				break;
				
				
				
			default:
		
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	protected void serverStarted() {
		System.out.println("Server listening for connections on port " + getPort());
		mysqlConnection.connectToDB();

	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops
	 * listening for connections.
	 */
	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
	}
}
//End of EchoServer class