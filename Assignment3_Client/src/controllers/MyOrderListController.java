package controllers;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import common.ItemList;
import common.Message;
import common.MessageType;
import common.OrdersList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
public class MyOrderListController implements Initializable {

	public static OrdersList OL;
	public static OrdersList ItemSelected;

	public static String TimeAccepted;
	public static String ArrivelTime;
	public static Integer refund;
	public   long differenceInMilliSeconds ;
	public   long differenceInHours;
	public   long differenceInMinutes;
	public   Date TimeAccepted1;
	public  Date ArrivelTime1;
	@FXML
    private Button BackBtn;

    @FXML
    private TableColumn<OrdersList,String> ColAddress;

    @FXML
    private TableColumn<OrdersList,String> ColApproval;

    @FXML
    private TableColumn<OrdersList,String> ColArrivalTime;

    @FXML
    private TableColumn<OrdersList,String> ColPackageNumber;

    @FXML
    private TableColumn<OrdersList,String> ColPrice;

    @FXML
    private TableColumn<OrdersList,String> ColResturant;

    @FXML
    private TableColumn<OrdersList,String> ColService;

    @FXML
    private TableColumn<OrdersList,String> ColStatus;

    @FXML
    private TableColumn<OrdersList,String> ColTime;

    @FXML
    private TableView<OrdersList> OrderList1;

    @FXML
    private Button ViewOrderBtn;
    
    @FXML
    private Button AcceptBtn;
    
    ////////////////////////////////////////////////////////////////////////////////
    
    void TimerMath (Date date1,Date date2) {
  	
  	   differenceInMilliSeconds = (date2.getTime() - date1.getTime());
		   differenceInHours = (differenceInMilliSeconds / (60 * 60 * 1000))  % 24;
    // Calculating the difference in Minutes
		   differenceInMinutes= (differenceInMilliSeconds / (60 * 1000)) % 60;
  }
  
  ////////////////////////////////////////////////////////////////////////////////
    
    @FXML
    void AcceptedOrder(ActionEvent event) {
    
    	LocalDateTime now = LocalDateTime.now();
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");   	   
    	ItemSelected =OrderList1.getSelectionModel().getSelectedItem();
		ClientUI.chat.accept(new Message(MessageType.OrderListBuildEdit, ItemSelected.getOrderPackageNumber()));
		Order_list.remove(ItemSelected);
		ArrivelTime=ItemSelected.getArrivalTime();
		TimeAccepted=dtf.format(now);
		try {
			TimeAccepted1=simpleDateFormat.parse(TimeAccepted);
			ArrivelTime1=simpleDateFormat.parse(ArrivelTime);
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refund=Integer.valueOf(ItemSelected.getTotalPrice())/2;
		System.out.println(refund);
		if(TimeAccepted1.after(ArrivelTime1)) {
			ClientUI.chat.accept(new Message(MessageType.RefundAdd,ItemSelected.getCustomer_ID()+" "+ItemSelected.getResturant()+" "+String.valueOf(refund)));
			System.out.println(refund);
		}
		
		
		initialize(null, null);
		

    }

		public static ObservableList<OrdersList> Order_list = FXCollections.observableArrayList();

	    @FXML
	    void BackAction(ActionEvent event) {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
			CustomerHomeController CHC=new CustomerHomeController();
			try {
				CHC.start(stage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @FXML
	    void ViewOrderAction(ActionEvent event) {
	    	
	    	if(OrderList1.getSelectionModel().getSelectedItem()==null) {
	    		Alert a = new Alert(AlertType.ERROR);
   				a.setContentText("Error");
   				a.setHeaderText("Please Pick an Order Package First ");
   				a.showAndWait();	  
   				}
	    	else {
			//Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
	    		Stage stage=new Stage();
	    	ClientUI.chat.accept(new Message(MessageType.ItemList, OL.getOrderPackageNumber()));
			ObservableList<ItemList> Item_List = FXCollections.observableArrayList(ChatClient.ItemBuild);
			ViewOrderController VOC=new ViewOrderController();
			try {
				VOC.start(stage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    }
	



	public void start(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/View/OrderList(ForCustomer).fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Customer Order List");
		stage.setScene(scene);
		
		stage.show();

		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		AcceptBtn.setDisable(true);
	    ColAddress.setCellValueFactory(new PropertyValueFactory<OrdersList, String>("Address"));
		ColApproval.setCellValueFactory(new PropertyValueFactory<OrdersList, String>("ApprovalRecieving"));
		ColArrivalTime.setCellValueFactory(new PropertyValueFactory<OrdersList, String>("ArrivalTime"));
		ColPackageNumber.setCellValueFactory(new PropertyValueFactory<OrdersList, String>("OrderPackageNumber"));
		ColPrice.setCellValueFactory(new PropertyValueFactory<OrdersList, String>("TotalPrice"));
		ColResturant.setCellValueFactory(new PropertyValueFactory<OrdersList, String>("Resturant"));
		ColStatus.setCellValueFactory(new PropertyValueFactory<OrdersList, String>("Status"));
		ColService.setCellValueFactory(new PropertyValueFactory<OrdersList, String>("DeleiveryService"));
		ColTime.setCellValueFactory(new PropertyValueFactory<OrdersList, String>("OrderedDate"));
		ClientUI.chat.accept(new Message(MessageType.OrderListBuild, null));
		Order_list=FXCollections.observableArrayList(ChatClient.OrderBuild); 
		OrderList1.setItems(Order_list);
		OrderList1.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				OL=OrderList1.getSelectionModel().getSelectedItem();
				if(OL.getStatus().equals("Ready")) 
					AcceptBtn.setDisable(false);		
				else
					AcceptBtn.setDisable(true);
				
			}
		});
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}