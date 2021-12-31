package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import common.Message1;
import common.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddEmployerController implements Initializable {
    public static boolean added=false;
	
	@FXML
	private TextField Identity;

	@FXML
	private TextField Name;

	@FXML
	private Text success;

	@FXML
	void add(ActionEvent event) {
		if (Identity.getText().equals("") || Name.getText().equals("")) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Some details are missing!");
			a.setHeaderText("Error");
			a.showAndWait();
		}else {
			ClientUI.chat.accept(new Message1(MessageType.AddEmployer,Identity.getText()+" "+Name.getText()+" "+ChatClient.HR.getID()));
			success.setVisible(added);
			
		}

	}

	@FXML
	void back(ActionEvent event) {
		((Node) event.getSource()).getScene().getWindow().hide();// get stage
		/*Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		HRHomeController HRHome = new HRHomeController();
		try {
			HRHome.start(stage);
		} catch (Exception e) {
			// TODO: handle exception
		}*/

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		success.setVisible(false);
	}

	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/View/AddNewEmployer.fxml"));

		Scene scene = new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.show();

	}

}