package controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import common.Client;
import common.Message1;
import common.MessageType;
import common.waiting_account;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


public class WaitingAccountsController implements Initializable {
	
	public static ObservableList<waiting_account> WaitingAccounts;
	public static waiting_account selectedAccount; 

	
	
	 @FXML
	    private TableColumn<waiting_account, String> BranchCol;

	    @FXML
	    private TableColumn<waiting_account, Integer> Ceilingcol;

	    @FXML
	    private TableColumn<waiting_account, String> FirstName;

	    @FXML
	    private TableColumn<waiting_account, String> IDcol;

	    @FXML
	    private TableColumn<waiting_account, String> LastName;

	    @FXML
	    private TableColumn<waiting_account, String> Phonecol;

	    @FXML
	    private TableColumn<waiting_account, String> creditcol;

	    @FXML
	    private TextField keyword;

	    @FXML
	    private TableView<waiting_account> waitingtbl;
	

    
    
    
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
    void ApproveAccount(ActionEvent event) {
    	
    	if(waitingtbl.getSelectionModel().getSelectedItem()==null)
    	{
    		Alert a = new Alert(AlertType.ERROR);
			a.setContentText("You have to select an order!");
			a.setHeaderText("Error");
			a.showAndWait();
    	}else {
    		
    		selectedAccount= waitingtbl.getSelectionModel().getSelectedItem();
    		ClientUI.chat.accept(new Message1(MessageType.ApproveaccountB, selectedAccount));
    		initialize(null, null);
    		
    	}
    	

    }
	


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ClientUI.chat.accept(new Message1(MessageType.GetEmployees, ChatClient.HR.getCompanyName()));
		
		FirstName.setCellValueFactory(new PropertyValueFactory<waiting_account, String>("FirstName"));
		LastName.setCellValueFactory(new PropertyValueFactory<waiting_account, String>("LastName"));
		IDcol.setCellValueFactory(new PropertyValueFactory<waiting_account, String>("ID"));
		Phonecol.setCellValueFactory(new PropertyValueFactory<waiting_account, String>("PhoneNumber"));
		creditcol.setCellValueFactory(new PropertyValueFactory<waiting_account, String>("CreditCard"));
		Ceilingcol.setCellValueFactory(new PropertyValueFactory<waiting_account, Integer>("ceiling"));
		
		BranchCol.setCellValueFactory(new PropertyValueFactory<waiting_account, String>("Address"));
		


		
		
		
		WaitingAccounts=FXCollections.observableArrayList(ChatClient.WaitingAccounts);
		waitingtbl.setItems(WaitingAccounts);
		
	}
	
	
	
	
	
	
	
	
	
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/View/WaitingAccounts.fxml"));
		
		Scene scene = new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.show();

	}
	
	
	
	

}