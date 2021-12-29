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
import common.ItemInCart;
import common.ItemList;
import common.Message;
import common.MessageType;
import common.OrdersList;
import common.Refund;
import common.Resturants;
import common.Selection;
import common.TybeMeal;
import common.Users;
import common.W4C_Card;

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
			userlogged = (Users) m.getObject(); }
		
		if (m.getMessageType().equals(MessageType.scan)) {
			accounts = (Account) m.getObject(); }
		
		if (m.getMessageType().equals(MessageType.w4cCard)) {
			w4ccard = (W4C_Card) m.getObject(); }
		
		if(m.getMessageType().equals(MessageType.ViewResturants)) {
			resturants=(ArrayList<Resturants>)m.getObject();
		}
		
		if(m.getMessageType().equals(MessageType.ViewTybeMeallist)) {
			tybemeal=(ArrayList<TybeMeal>)m.getObject();
		}
		if(m.getMessageType().equals(MessageType.ViewDishList)) {
			dish=(ArrayList<Dish>)m.getObject();
		}
		if(m.getMessageType().equals(MessageType.ViewSelctionsList)) {
			selection=(ArrayList<Selection>)m.getObject();
		}
		if(m.getMessageType().equals(MessageType.bussinessAccounts)) {
			bussiness=(Business)m.getObject();
		}
		if(m.getMessageType().equals(MessageType.OrdersListToDataBase)) {
			order=(OrdersList)m.getObject();
		}
		if(m.getMessageType().equals(MessageType.GetOrder)) {
			order2=(OrdersList)m.getObject();
		}
		if(m.getMessageType().equals(MessageType.itemsListtoDataBase)) {
			items=(ItemList)m.getObject();
		}
		
		if (m.getMessageType().equals(MessageType.OrderListBuild)) {
			OrderBuild = (ArrayList<OrdersList>)m.getObject();
		}
		if (m.getMessageType().equals(MessageType.ItemList)) {
			ItemBuild = (ArrayList<ItemList>)m.getObject();
		}
		/*if (m.getMessageType().equals(MessageType.RefundAdd)) {
			RefundBuild = (Refund)m.getObject();
		}*/
	}

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
