package server;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
				User u=mysqlConnection.checkUserLogIn(message[0], message[1]);
				System.out.println(message[0]+"  " +message[1]);
				client.sendToClient(new Message(MessageType.login, u));
				//System.out.println(user.getW4C_QrCode());
				break;
				
			case scan:
				message = ((String) m.getObject()).split(" ");
				Accounts account=mysqlConnection.getAccountsListFromDB(message[0]);
				//System.out.println(account);
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
				System.out.println(message[0]);
				tybemeal=mysqlConnection.getTybeMealListFromDB(message[0]);
				System.out.println(tybemeal.size());
				client.sendToClient(new Message(MessageType.ViewTybeMeallist,tybemeal));	
				
				break;
			case ViewDishList:
				System.out.println(999);
				message=((String)m.getObject()).split(" ");
		    	System.out.println(message.toString());	
				break;
				
			default:
				//ArrayList<Resturants> rest =mysqlConnection.getResturantsListFromDB();
				//client.sendToClient(msg);
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