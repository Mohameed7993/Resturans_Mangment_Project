package controllers;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;


import client.ChatClient;
import client.ClientUI;
import common.OrdersForRes;
import common.Client;
import common.Message1;
import common.MessageType;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

///////////
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

//////////

public class WaitingOrdersController implements Initializable {
	public static OrdersForRes selctedOrder;
	public static boolean approvebool;
	/////////////////////////////////////////////////////////////////////////////////////
	 public static final String ACCOUNT_SID = "ACb6bc51f8ca05c4418ecf8b6d280e7768";
	    public static final String AUTH_TOKEN = "7dd4f6a98cf75305855d72605a7a7b90";
/////////////////////////////////////////////////////////////////////////////////////
	public static ObservableList<OrdersForRes> WaitingOrders;
	
   
	@FXML
    private TableColumn<OrdersForRes, String> Approval;

    @FXML
    private TableColumn<OrdersForRes, String> Total;

    @FXML
    private Button approvebtn;

    @FXML
    private TextField keyword;

    @FXML
    private TableColumn<OrdersForRes, Integer> orderNum;

    @FXML
    private TableView<OrdersForRes> orders_table;

    @FXML
    private Button pkgbtn;

    @FXML
    private TableColumn<OrdersForRes, String> time;

    @FXML
    void Approve(ActionEvent event) {
    	if (orders_table.getSelectionModel().getSelectedItem() != null) {
    		
			selctedOrder = orders_table.getSelectionModel().getSelectedItem();
			System.out.println(selctedOrder.getOrderNumber());
			ClientUI.chat.accept(
					new Message1(MessageType.approveOrder, selctedOrder.getOrderNumber()));
			if (!approvebool) {
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("this order already approved!");
				a.setHeaderText("Error");
				a.showAndWait();
			} else {
				/////////////////////////////////////
				ClientUI.chat.accept(new Message1(MessageType.getCustomer,selctedOrder.getCustomer_ID()));
				String number =("+972"+ChatClient.GetCustomerDetails.getPhoneNumber());
				/*Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
			        Message message1 = Message.creator(
			                new com.twilio.type.PhoneNumber(number),//////to
			                new com.twilio.type.PhoneNumber("+15739933793"),////from
			                "BiteMe Company:\n Thank you for ordering through our app\n"
			                + "Your order number '"+selctedOrder.getOrderNumber()+"' has been successfully received \n"
			                		+ "We'll send you when she's ready,Thanks")//message body
			            .create();*/

			      //  System.out.println(message1.getSid());
				
				////////////////////////////////////

				initialize(null, null);
			}
		} else {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("You have to select an order!");
			a.setHeaderText("Error");
			a.showAndWait();
		}
    }

    @FXML
    void LogOut(ActionEvent event) {
    	Alert alert = new Alert(AlertType.WARNING, "Are you sure you want to logout?", ButtonType.YES, ButtonType.NO);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			// ... user chose YES
			ClientUI.chat.accept(new Message1(MessageType.logout, ChatClient.userlogged));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			BiteMeLoginController biteMeLoginController = new BiteMeLoginController();
			try {
				biteMeLoginController.start(stage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
    }

    @FXML
    void ViewOrder(ActionEvent event) {
    	if (orders_table.getSelectionModel().getSelectedItem() != null) {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			selctedOrder = orders_table.getSelectionModel().getSelectedItem();
			ViewOrderPackController view = new ViewOrderPackController();
			try {
				view.start(stage);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		} else {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("You have to select an order!");
			a.setHeaderText("Error");
			a.showAndWait();
		}
    }

    @FXML
    void back(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		ResturantHomeController home = new ResturantHomeController();
		try {
			home.start(stage);
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ClientUI.chat.accept(new Message1(MessageType.GetWaitingOrders, ChatClient.resturant));
		orderNum.setCellValueFactory(new PropertyValueFactory<OrdersForRes, Integer>("orderNumber"));
		time.setCellValueFactory(new PropertyValueFactory<OrdersForRes, String>("requestTime"));
		Total.setCellValueFactory(new PropertyValueFactory<OrdersForRes, String>("totalPrice"));
		Approval.setCellValueFactory(new PropertyValueFactory<OrdersForRes, String>("approvalStatus"));
		WaitingOrders=FXCollections.observableArrayList(ChatClient.WaitingOrders);
		orders_table.setItems(WaitingOrders);
		System.out.println(99);
		
	}
	
	
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/View/WaitingOrders.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.show();
		
		

		
	}
		

}