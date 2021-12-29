package controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import common.Message;
import common.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HRHomeController implements Initializable {
	
	@FXML
    private Text HRWelcome;

    @FXML
    void GetEmployees(ActionEvent event) {
    	System.out.println(1);
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	System.out.println(2);

    	WaitingAccountsController waitingcontroller = new WaitingAccountsController();
    	try {
			waitingcontroller.start(stage);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	

    }

    @FXML
    void NewEmployer(ActionEvent event) {
    	Stage stage=new Stage();
    	
    	AddEmployerController AddEmployer = new AddEmployerController();
    	
    	try {
			AddEmployer.start(stage);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    	

    }

    @FXML
    void logOut(ActionEvent event) {
    	Alert alert = new Alert(AlertType.WARNING, "Are you sure you want to logout?", ButtonType.YES, ButtonType.NO);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			// ... user chose YES
			ClientUI.chat.accept(new Message(MessageType.logout, ChatClient.userlogged));
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/View/HRHome.fxml"));

		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);

		primaryStage.centerOnScreen();

		primaryStage.show();

	}

}