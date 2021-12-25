package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import client.ChatClient;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class CustomerHomeController implements Initializable {

    @FXML
    private ImageView Cart;

    @FXML
    private Button Logoutbtn;

    @FXML
    private Button MakeAnOrderBTN;

    @FXML
    private Button MyCartButton;

    @FXML
    private Button MyOrderBTN;

    @FXML
    private Button MyOrderListBTN;

    @FXML
    private Text User;

    
    @FXML
    void LogoutAction(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
    	BiteMeLoginController AFrame=new BiteMeLoginController();
		try {
			AFrame.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void MyOrder(ActionEvent event) {
    	MyOrderList(event);
    }
    
    @FXML
    void MakeAnOrder(ActionEvent event) {
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
    void MyCartAction(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
   	 MyCartController AFrame=new MyCartController();
        try {
			AFrame.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @FXML
    void MyOrderList(ActionEvent event) {
       	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
    	MyOrderListController AFrame=new MyOrderListController();
		try {
			AFrame.start(stage); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public void start(Stage stage) throws Exception{
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("/View/HomeCustomerV2.fxml"));
		
		Scene scene = new Scene(root);
		stage.setTitle("CoustomerHome");
		stage.setScene(scene);
		stage.setX(300);
		//stage.setY(0);
		stage.show();
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		User.setText(ChatClient.userlogged.getUserName());
		if(ItemDetailsController.itemList.size()==0) {
			MyCartButton.setVisible(false);
		    Cart.setVisible(false);
		}
		else { MyCartButton.setVisible(true); Cart.setVisible(true);}
	}
}