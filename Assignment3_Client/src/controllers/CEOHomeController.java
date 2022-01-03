package controllers;

import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CEOHomeController implements Initializable{

	public static String ceoID;
    @FXML
    private Button Loginbtn;


    @FXML
    void logoutAction(ActionEvent event) {
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
    void viewManagerQuarterlyReports(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
    	ViewManagerQuarterlyReportsController viewManagerQuarterlyReportsController=new ViewManagerQuarterlyReportsController();
    	try {
			viewManagerQuarterlyReportsController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void viewManagerReports(ActionEvent event) {
       	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
    	ViewCEOManagerReportsController viewCEOManagerReportsController=new ViewCEOManagerReportsController();
    	try {
    		viewCEOManagerReportsController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void viewQuarterlyHistogram(ActionEvent event) {

    	Stage stage=new Stage();
    	LastQuareterHistogramController lastQuareterHistogramController=new LastQuareterHistogramController();
    	try {
			lastQuareterHistogramController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/View/CEOHome.fxml"));

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);

		primaryStage.centerOnScreen();

		primaryStage.show();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ChatClient.userlogged.setId(ceoID);
		BiteMeLoginController.isCEO=true;
	}

}
