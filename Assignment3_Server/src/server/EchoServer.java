package server;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;

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

		Message m = (Message) msg;
		String message[];
		try {
			switch (m.getMessageType()) {
			
			case connect:
				message = ((String) m.getObject()).split(" ");
				ServerConnectionController.clients_list.add(new Client(message[0], message[1], message[2]));
				client.sendToClient(new Message(null, null));
				break;
				
			case disconnect:
				message = ((String) m.getObject()).split(" ");
				ServerConnectionController.clients_list.add(new Client(message[0], message[1], message[2]));
				client.sendToClient(new Message(null, null));
				break;
				
			case login:
				message = ((String) m.getObject()).split(" ");
				Users user=mysqlConnection.checkUserLogIn(message[0], message[1]);
				System.out.println(message[0]+"  " +message[1]);
				client.sendToClient(new Message(MessageType.login, user));
				break;
				
			case w4cCard:
	            message = ((String) m.getObject()).split(" ");
	            W4C_Card w4c=mysqlConnection.getW4cInformationfromDB(message[0]);
	            client.sendToClient(new Message(MessageType.w4cCard, w4c));
				break;
				
			case scan:
				message = ((String) m.getObject()).split(" ");
				Account account=mysqlConnection.getAccountListFromDB(message[0]);
				client.sendToClient(new Message(MessageType.scan, account));
				break;
				
			case ViewResturants:
				ArrayList<Resturants> resturant;
				resturant =mysqlConnection.getResturantsListFromDB();
				client.sendToClient(new Message(MessageType.ViewResturants, resturant));
				break;
				
			case ViewTybeMeallist:
				message = ((String) m.getObject()).split(" ");
				ArrayList<TybeMeal> tybemeal;
				tybemeal=mysqlConnection.getTybeMealListFromDB(message[0]);
				client.sendToClient(new Message(MessageType.ViewTybeMeallist,tybemeal));	
				break;
				
			case ViewDishList:
				message=((String)m.getObject()).split(" ");
				ArrayList<Dish> Dish;
				Dish=mysqlConnection.getDishListFromDB(message[0]);
				client.sendToClient(new Message(MessageType.ViewDishList, Dish));
				break;
				
			case ViewSelctionsList:
				message=((String)m.getObject()).split(" ");
				ArrayList<Selection> selection;
			   	System.out.println(message[0]);	
			   	selection=mysqlConnection.getSelectionListFromDB(message[0]);
				client.sendToClient(new Message(MessageType.ViewSelctionsList, selection));
				break;
				
			case bussinessAccounts:
				message=((String)m.getObject()).split(" ");
				System.out.println(message[0]);
				Business bussiness=mysqlConnection.getBussinessInformationfromDB(message[0]);
				System.out.println(bussiness.getCeiling());
				client.sendToClient(new Message(MessageType.bussinessAccounts, bussiness));
				break;
			case OrdersListToDataBase:
				message=((String)m.getObject()).split(" ");
				System.out.println(message[0]+111111);
				mysqlConnection.setOrderinDB(message[0], message[1], message[2], message[3], message[4], message[5], message[6], message[7], message[8], message[9]);
				client.sendToClient(new Message(MessageType.OrdersListToDataBase, null));
				
				break;
				
			default:
			
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		/*
		 * String s[] = m.getStr().split(" "); // System.out.println(m.getStr());
		 * 
		 * if (s[0].equals("view")) { System.out.println(); orders =
		 * mysqlConnection.getOrdersFromDB(); this.sendToAllClients(new Message("list",
		 * orders)); // client.sendToClient(orders); } else if (s[0].equals("connect"))
		 * { System.out.println(s[0]); ServerConnectionController.clients_list.add(new
		 * Client(s[1], s[2], s[3])); this.sendToAllClients(new Message("connect",
		 * null)); }
		 * 
		 * else if (s[0].equals("disconnect")) { System.out.println(s[0]);
		 * ServerConnectionController.clients_list.add(new Client(s[1], s[2], s[3]));
		 * this.sendToAllClients(new Message("disconnect", null)); } else if
		 * (s[0].equals("updateType")) {
		 * 
		 * if (!s[2].equals("")) { mysqlConnection.updateType(Integer.parseInt(s[1]),
		 * s[2]);
		 * 
		 * this.sendToAllClients(new Message("updated", null)); }
		 * 
		 * } else if (s[0].equals("updateAddress")) {
		 * 
		 * mysqlConnection.updateAddress(Integer.parseInt(s[1]), s[2]);
		 * this.sendToAllClients(new Message("updated", null));
		 * 
		 * }
		 */

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