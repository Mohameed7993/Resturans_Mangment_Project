package controllers;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import common.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class CustomerHomeController implements Initializable {

    @FXML
    private ImageView Image1;

    @FXML
    private Text CustomerName;

    @FXML
    private Button LogOutbtn;

    @FXML
    private TextField Makeanorder;

    @FXML
    private TextField myorderlist;

    @FXML
    private ImageView Image2;

    @FXML
    private ImageView Image3;

    @FXML
    private ImageView Image4;

    @FXML
    private ImageView Image5;
    
    @FXML
    private Button MakeAnOrderButton;

    @FXML
    private Button MyOrderListButton;
    

    @FXML
    void LogoutAction(ActionEvent event) {
    
    }
    
    @FXML
    void MakeorderAction(ActionEvent event) {
    	
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
    	ScanerQrController AFrame=new ScanerQrController();
		try {
			AFrame.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

    @FXML
    void OrderListAction(ActionEvent event) {

    }
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		CustomerName.setText("Hello, "+ChatClient.u.getUserName());
	}

	
	public void start(Stage stage) throws Exception{
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("/View/CustomerHome.fxml"));
		
		Scene scene = new Scene(root);
		stage.setTitle("CoustomerHome");
		stage.setScene(scene);
		
		stage.show();
		
	}

}
