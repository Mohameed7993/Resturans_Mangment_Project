package controllers;

import java.io.IOException;
import java.net.URL;
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
			System.out.println(OL.getOrderPackageNumber());
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
			}
			
		});
		
	}

}