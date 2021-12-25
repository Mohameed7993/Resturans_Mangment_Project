package controllers;

import java.io.IOException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ScanerQrController implements Initializable {
	
	
	public static String Wallet=null;

    @FXML
    private ImageView Image1;

    @FXML
    private Button BackButton;

    @FXML
    private ImageView Image2;

    @FXML
    private ImageView Image4;

    @FXML
    private ImageView Image3;

    @FXML
    private Button scanButton;
    
    @FXML
    private Text Identify1;

    @FXML
    private Text Identify2;
    
    @FXML
    private TextField w4c_code;
    

    @FXML
    void BackButtonAction(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
    	CustomerHomeController AFrame=new CustomerHomeController();
		try {
			AFrame.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void scanButtonAction(ActionEvent event) {
    	ClientUI.chat.accept(new Message(MessageType.w4cCard,ChatClient.accounts.getW4C_QrCode()));
    	if(ChatClient.w4ccard.getAccountType().equals("business")) {
    		ClientUI.chat.accept(new Message(MessageType.bussinessAccounts,ChatClient.accounts.getW4C_QrCode()));
    	}
    		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
    		CustomerDetailsController AFrame=new CustomerDetailsController();
			try {
				AFrame.start(stage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    
    	

    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		w4c_code.setText(ChatClient.accounts.getW4C_QrCode());
		
	}
	
	public void start(Stage stage) throws Exception{
		// TODO Auto-generated method stub
		Parent root= FXMLLoader.load(getClass().getResource("/View/ScanerQr.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Scaner Qr Code Home");
		stage.setScene(scene);
		stage.setX(500);
		stage.show();
	}
	}

