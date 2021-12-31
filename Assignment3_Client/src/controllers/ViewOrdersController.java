package controllers;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import client.ChatClient;
import client.ClientUI;
import common.DishForResturant;
import common.Message1;
import common.MessageType;
import common.OrdersForRes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewOrdersController implements Initializable {

	public static ObservableList<OrdersForRes> Orders;

	public static OrdersForRes selctedOrder;
	

	public static boolean approvebool;
	
	
    @FXML
    private TextField keyword;

	
/////////////////////////////////////////////////////////////////////////////////////
public static final String ACCOUNT_SID = "ACb6bc51f8ca05c4418ecf8b6d280e7768";
public static final String AUTH_TOKEN = "7dd4f6a98cf75305855d72605a7a7b90";
/////////////////////////////////////////////////////////////////////////////////////
	

	

	@FXML
	private TableColumn<OrdersForRes, String> Status;

	@FXML
	private TableColumn<OrdersForRes, String> Total;

	@FXML
	private TableColumn<OrdersForRes, Integer> orderNum;

	@FXML
	private TableView<OrdersForRes> orders_table;

	@FXML
	private TableColumn<OrdersForRes, String> time;

	

	@FXML
	void StatusUpdate(ActionEvent event) {
		
		if(orders_table.getSelectionModel().getSelectedItem().getStatus().equals("Ready")) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("this order is ready!");
			a.setHeaderText("Error");
			a.showAndWait();
			
		}else if (orders_table.getSelectionModel().getSelectedItem() != null) {
			
	   ClientUI.chat.accept(new Message1(MessageType.UpdateStatus, orders_table.getSelectionModel().getSelectedItem().getOrderNumber()+","+"Ready"));
	   Integer ordernumber =orders_table.getSelectionModel().getSelectedItem().getOrderNumber();
	/*	String number =("+972"+ChatClient.GetCustomerDetails.getPhoneNumber());
		System.out.println(number);
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	        Message message1 = Message.creator(
	                new com.twilio.type.PhoneNumber(number),//////to
	                new com.twilio.type.PhoneNumber("+15739933793"),////from
	                "BiteMe Company:\n"
	                + "Your order number '"+ordernumber+"' Is Ready\n You will receive it soon.Thanks ")//message body
	            .create();*/
		
		} else {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("You have to select an order!");
			a.setHeaderText("Error");
			a.showAndWait();
		}
		initialize(null, null);

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
	

		ClientUI.chat.accept(new Message1(MessageType.GetResturantOrders, ChatClient.resturant));

		System.out.println("done with server");
		orderNum.setCellValueFactory(new PropertyValueFactory<OrdersForRes, Integer>("orderNumber"));
		time.setCellValueFactory(new PropertyValueFactory<OrdersForRes, String>("requestTime"));
		Total.setCellValueFactory(new PropertyValueFactory<OrdersForRes, String>("totalPrice"));
		Status.setCellValueFactory(new PropertyValueFactory<OrdersForRes, String>("status"));
		

		

		

		Orders = FXCollections.observableArrayList(ChatClient.orders);
		

		

		orders_table.setOnMouseClicked(mouseEvent -> {
			if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
				if (orders_table.getSelectionModel().getSelectedItem() != null) {
					

					
				}
			}
		});
		
		orders_table.setItems(Orders);

		
		

		

	}

	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/View/ordersList.fxml"));


		Scene scene = new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.show();

	}

}