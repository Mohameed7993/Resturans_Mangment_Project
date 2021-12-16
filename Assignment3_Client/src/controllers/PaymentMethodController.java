package controllers;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import client.ChatClient;
import common.Address;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PaymentMethodController implements Initializable{
	
	
	
	public static String DeleiveryType=null;
	
	public static Integer pricedeleivery;
	
	public static Integer orderPrice;
	
	public static Address address;
	
	public static String orderTime;
	
	public int flagDate; //0-> other, 1->now
	
	/*public static   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");  
	public static LocalDateTime now = LocalDateTime.now();  
	  // System.out.println(dtf.format(now));  */
	
	
    @FXML
    private ImageView image1; 

    @FXML
    private Button BackButton;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;

    @FXML
    private Text deleiveryservicetxt;

    @FXML
    private Text enterAdresstxt;

    @FXML
    private TextArea CityField;

    @FXML
    private Text streettxt;

    @FXML
    private Text housenumbertxt;

    @FXML
    private Text citytxt;

    @FXML
    private TextArea streetField;

    @FXML
    private TextArea houseNumberField;

    @FXML
    private Text paymenttxt;

    @FXML
    private Button payprivatebutton;

    @FXML
    private Button SharedDelButton;

    @FXML
    private Button takeawaybutton;

    @FXML
    private Button deleiveryButton;

    @FXML
    private Button paybussinessbutton;

    @FXML
    private Button robotbutton;

    @FXML
    private Text requesteddatetxt;

    @FXML
    private TextArea orderdateField;
    
    @FXML
    private Button nowbutton;
    
    @FXML
    private Button otherbutton;

    @FXML
    private Text walletTxt;
    
    @FXML
    private Text Wallettxt;
    
    @FXML
    private Text txt3;

    @FXML
    private Text txt1;

    @FXML
    private Text txt2;

    @FXML
    private Text txt4;
    
    @FXML
    void otherbuttonAction(ActionEvent event) {
    	orderdateField.setVisible(true);
    	flagDate=0;
    	orderTime=orderdateField.getText();
    	

    }
    

    @FXML
    void nowbuttonAction(ActionEvent event) {
    	orderdateField.setVisible(false);
    	flagDate=1;
    	orderTime="Now";
    	
    	
    }
    @FXML
    void BackButtonAction(ActionEvent event) {
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
    void SharedDelButtonAction(ActionEvent event) {
    	DeleiveryType=("Shared-deleivery");
    	enterAdresstxt.setVisible(true);
		citytxt.setVisible(true);
		streettxt.setVisible(true);
		housenumbertxt.setVisible(true);
		CityField.setVisible(true);
		streetField.setVisible(true);
		houseNumberField.setVisible(true);
		
    }

    @FXML
    void deleiveryButtonAction(ActionEvent event) {
    	DeleiveryType=("Deleivery");
    	enterAdresstxt.setVisible(true);
		citytxt.setVisible(true);
		streettxt.setVisible(true);
		housenumbertxt.setVisible(true);
		CityField.setVisible(true);
		streetField.setVisible(true);
		houseNumberField.setVisible(true);
	
    }

    @FXML
    void robotbuttonAction(ActionEvent event) {
    	DeleiveryType=("Robot");
    	enterAdresstxt.setVisible(true);
		citytxt.setVisible(true);
		streettxt.setVisible(true);
		housenumbertxt.setVisible(true);
		CityField.setVisible(true);
		streetField.setVisible(true);
		houseNumberField.setVisible(true);
		
    }

    @FXML
    void takeawaybuttonAction(ActionEvent event) {
    	DeleiveryType=("Take-Away");
    	enterAdresstxt.setVisible(false);
		citytxt.setVisible(false);
		streettxt.setVisible(false);
		housenumbertxt.setVisible(false);
		CityField.setVisible(false);
		streetField.setVisible(false);
		houseNumberField.setVisible(false);
    }
    
    @FXML
    void paybussinessbuttonAction(ActionEvent event) {
    	if(DeleiveryType==null)
    	{
    		 Alert a = new Alert(AlertType.ERROR);
	            a.setContentText("Error");
	            a.setHeaderText("Choose deleivery service");
	            a.showAndWait();
    	}
    	else {
    	if(DeleiveryType.equals("Robot")||DeleiveryType.equals("Deleivery")||DeleiveryType.equals("Shared-deleivery"))
    	{
    		if(CityField.getText().equals("")||streetField.getText().equals("")||houseNumberField.getText().equals("")) {
    			 Alert a = new Alert(AlertType.ERROR);
		            a.setContentText("Error");
		            a.setHeaderText("your details that you insert is Wrong!");
		            a.showAndWait();
    		}
    		else {address =new Address(CityField.getText(), streetField.getText(), houseNumberField.getText());}
    	}
    	if(flagDate==0) {
    			 if(orderdateField.getText().equals("")||orderdateField.getText().matches("[a-zA-Z_]+")||
    		    	Float.valueOf(orderdateField.getText())<0||Float.valueOf(orderdateField.getText())>23) {
    				  Alert a = new Alert(AlertType.ERROR);
    		            a.setContentText("Error");
    		            a.setHeaderText("your details that you insert is Wrong!");
    		            a.showAndWait();
    		    		}
    			 }
   
    		switch (DeleiveryType) {
			case "Deleivery":
				pricedeleivery=25;
				break;
			case "Shared-deleivery":
				pricedeleivery=25;
				break;
			default:
				pricedeleivery=0;
				break;
			}
    		
    		orderPrice=ItemDetailsController.TotalPrice+pricedeleivery;
    		if((Integer.valueOf(ScanerQrController.Wallet)-(orderPrice))<0) {
    			   Alert a = new Alert(AlertType.ERROR);
    	            a.setContentText("Error");
    	            a.setHeaderText("there is no enough money in your bussiness wallet ");
    	            a.showAndWait();
    		}
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
    	OrdersDetailsController AFrame=new OrdersDetailsController();
		try {
			AFrame.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		}
    }
    
    @FXML
    void payprivatebuttonAction(ActionEvent event) {
    	if(DeleiveryType==null)
    	{
    		 Alert a = new Alert(AlertType.ERROR);
	            a.setContentText("Error");
	            a.setHeaderText("Choose deleivery service");
	            a.showAndWait();
    	}
    	else {
    	if(DeleiveryType.equals("Robot")||DeleiveryType.equals("Deleivery"))
    	{
    		if(CityField.getText().equals("")||streetField.getText().equals("")||houseNumberField.getText().equals("")) {
    			 Alert a = new Alert(AlertType.ERROR);
		            a.setContentText("Error");
		            a.setHeaderText("your details that you insert is Wrong!");
		            a.showAndWait();
    		}
    		else {address =new Address(CityField.getText(), streetField.getText(), houseNumberField.getText());}
    	}
    	if(flagDate==0) {
    			 if(orderdateField.getText().equals("")||orderdateField.getText().matches("[a-zA-Z_]+")||
    		    	Float.valueOf(orderdateField.getText())<0||Float.valueOf(orderdateField.getText())>23) {
    				  Alert a = new Alert(AlertType.ERROR);
    		            a.setContentText("Error");
    		            a.setHeaderText("your details that you insert is Wrong!");
    		            a.showAndWait();
    		    		}
    			 }
    	switch (DeleiveryType) {
		case "Deleivery":
			pricedeleivery=25;
			break;
		case "Shared-deleivery":
			pricedeleivery=25;
			break;
		default:
			pricedeleivery=0;
			break;
		}
    	orderPrice=ItemDetailsController.TotalPrice+pricedeleivery;
    	
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
    	OrdersDetailsController AFrame=new OrdersDetailsController();
		try {
			AFrame.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		}
    }

 

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(ChatClient.w4ccard.getAccountType().equals("business")) {
		ScanerQrController.Wallet=ChatClient.bussiness.getCeiling();
		walletTxt.setText(": "+ScanerQrController.Wallet+"$");
		}
		
		orderdateField.setVisible(false);
		if(ChatClient.w4ccard.getAccountType().equals("private")) {
			SharedDelButton.setVisible(false);
			paybussinessbutton.setVisible(false);
			Wallettxt.setVisible(false);
			walletTxt.setVisible(false);
			txt4.setVisible(false);
		}
		
		enterAdresstxt.setVisible(false);
		citytxt.setVisible(false);
		streettxt.setVisible(false);
		housenumbertxt.setVisible(false);
		CityField.setVisible(false);
		streetField.setVisible(false);
		houseNumberField.setVisible(false);
		
	}

	public void start(Stage stage)  throws Exception {
		// TODO Auto-generated method stub
		
		Parent root = FXMLLoader.load(getClass().getResource("/View/PaymentMethod.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Payment");
		stage.setScene(scene);

		stage.show();
	}

}

