package controllers;

import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class BiteMeLoginController implements Initializable {

	@FXML
	private TextField Username;

	@FXML
	private PasswordField Password;

	@FXML
	private Button Loginbtn;

	@FXML
	private Button Loginbtn1;

	@FXML
	void LoginAction(ActionEvent event) {
		
		if (Username.getText().equals("") || Password.getText().equals("")) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Error");
			a.setHeaderText("Enter username and passWord");
			a.showAndWait();
		}
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		ClientUI.chat.accept(new Message(MessageType.login, Username.getText() + " " + Password.getText()));
		if (ChatClient.u != null) {
			switch (ChatClient.u.getType()) {
			case Customer:
			CustomerHomeController AFrame=new CustomerHomeController();
				try {
					AFrame.start(stage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case CEO:
				System.out.println(ChatClient.u.getPassWord() + "2");
				break;
			case BranchManager:
				System.out.println(ChatClient.u.getPassWord() + "3");
				break;
			case Supplier:
				System.out.println(ChatClient.u.getPassWord() + "4");
				break;
			default:
				break;
			}
		}
		else {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Error");
			a.setHeaderText("Your username or password is wrong");
			a.showAndWait();
		}


	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/View/BiteMeLogin.fxml"));

		Scene scene = new Scene(root);
		primaryStage.setTitle("BiteMeLogin");
		primaryStage.setScene(scene);

		primaryStage.show();
	}

}
