package controllers;

import java.net.URL;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PaymentMethodController implements Initializable{
	
	
	
	public  static LocalDateTime now = LocalDateTime.now();
	public  static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
	public  static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
	
	public static String DeleiveryType=null;
	
	public static Integer pricedeleivery;
	
	public static Integer orderPrice;
	
	public static Address address=null;
	
	public static String Time;
	
	public static String accountpayment;
	public static String HelpTime;
	
	public long differenceInMilliSeconds ;
	public long differenceInHours;
	public long differenceInMinutes;
	
	public static int flagDate=1; //0-> other, 1->now
	
	public Date date1;
	public Date date2;
	

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
    private TextField CityField;

    @FXML
    private Text streettxt;

    @FXML
    private Text housenumbertxt;

    @FXML
    private Text citytxt;

    @FXML
    private TextField streetField;

    @FXML
    private TextField houseNumberField;

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
    private TextField orderdateField;
    
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
    private ImageView Visa;
    
    @FXML
    private ImageView Pick1;

    @FXML
    private ImageView Pick2;

    @FXML
    private ImageView Pick3;

    @FXML
    private ImageView Pick4;
    
    @FXML
    private Button Shadow;

    @FXML
    private Button Shadow1;
    
    @FXML
    private Text Star;
    
    @FXML
    private Button payprivatebutton1;
    
    @FXML
    void otherbuttonAction(ActionEvent event) {
    	orderdateField.setVisible(true);
    	flagDate=0;
    	Shadow.setVisible(false);
		Shadow1.setVisible(true);
		Star.setVisible(true);
    	
    }
  
    
    @FXML
    void nowbuttonAction(ActionEvent event) {
    	orderdateField.setVisible(false);
    	flagDate=1;
    	Shadow.setVisible(true);
		Shadow1.setVisible(false);
		Star.setVisible(false);
    	
    	
    }
    @FXML
    void BackButtonAction(ActionEvent event) {
    	address=null;
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
    	DeleiveryType=("SharedDeleivery");
    	Visa.setVisible(false);
    	Pick1.setVisible(false);
    	Pick2.setVisible(false);
    	Pick3.setVisible(false);
    	Pick4.setVisible(true);
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
    	Visa.setVisible(false);
    	Pick1.setVisible(false);
    	Pick2.setVisible(false);
    	Pick3.setVisible(true);
    	Pick4.setVisible(false);
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
    	Visa.setVisible(false);
    	Pick1.setVisible(false);
    	Pick2.setVisible(true);
    	Pick3.setVisible(false);
    	Pick4.setVisible(false);
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
    	DeleiveryType=("TakeAway");
    	Pick1.setVisible(true);
    	Pick2.setVisible(false);
    	Pick3.setVisible(false);
    	Pick4.setVisible(false);
    	Visa.setVisible(true);
    	enterAdresstxt.setVisible(false);
		citytxt.setVisible(false);
		streettxt.setVisible(false);
		housenumbertxt.setVisible(false);
		CityField.setVisible(false);
		streetField.setVisible(false);
		houseNumberField.setVisible(false);
    }
    
    ////////////////////////////////////////////////////////////////////////////////
    
    void TimerMath (Date data1,Date data2) {
    	   differenceInMilliSeconds = Math.abs(date2.getTime() - date1.getTime());
		   differenceInHours = (differenceInMilliSeconds / (60 * 60 * 1000))  % 24;
      // Calculating the difference in Minutes
		   differenceInMinutes= (differenceInMilliSeconds / (60 * 1000)) % 60;
    }
    
    ////////////////////////////////////////////////////////////////////////////////
    @FXML
    void paybussinessbuttonAction(ActionEvent event) {
    	accountpayment="buissiness";
    	
    	
    	if(flagDate==0) {
    			 if(orderdateField.getText().equals("")||orderdateField.getText().matches("[a-zA-Z_]+")) {
    				  Alert a = new Alert(AlertType.ERROR);
    		            a.setContentText("Error");
    		            a.setHeaderText("your details that you insert is Wrong!");
    		            a.showAndWait();
    		    		}
    			 else {
    				 Time= orderdateField.getText();
	    	    	 try {
	    				date1=simpleDateFormat.parse(Time);
	    			    date2=simpleDateFormat.parse(dtf.format(now));
	    			    TimerMath( date1,date2);
	    			} catch (ParseException e1) {
	    				// TODO Auto-generated catch block
	    				e1.printStackTrace();
	    			}
	    	    	 if((differenceInHours<2)) {
	    	    		  Alert a = new Alert(AlertType.ERROR);
	    		            a.setContentText("Error");
	    		            a.setHeaderText("At least after two hour from now");
	    		            a.showAndWait();
	    	    	 }
	    	    	 else {
	    	    		 if(DeleiveryType==null)
	    	    	    	{
	    	    	    		 Alert a = new Alert(AlertType.ERROR);
	    	    		            a.setContentText("Error");
	    	    		            a.setHeaderText("Choose deleivery service");
	    	    		            a.showAndWait();
	    	    	    	}
	    	    	    	else {
	    	    	    	if(DeleiveryType.equals("Robot")||DeleiveryType.equals("Deleivery")||DeleiveryType.equals("SharedDeleivery"))
	    	    	    	{
	    	    	    		if(CityField.getText().equals("")||streetField.getText().equals("")||houseNumberField.getText().equals("")) {
	    	    	    			 Alert a = new Alert(AlertType.ERROR);
	    	    			            a.setContentText("Error");
	    	    			            a.setHeaderText("your details that you insert is Wrong!");
	    	    			            a.showAndWait();
	    	    	    		}
	    	    	    		else {address =new Address(CityField.getText(), streetField.getText(), houseNumberField.getText());}
	    	    	    	}
	    	    	
	    	    			switch (DeleiveryType) {
	    	    			case "Deleivery":
	    	    				pricedeleivery=25;
	    	    				break;
	    	    			case "SharedDeleivery":
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
	    	        		 Stage stage = new Stage();
	    	        	OrdersDetailsController AFrame=new OrdersDetailsController();
	    	    		try {
	    	    			AFrame.start(stage);
	    	    		} catch (Exception e) {
	    	    			// TODO Auto-generated catch block
	    	    			e.printStackTrace();
	    	    		}
	    	    	 }
	    	    	 
	    	    	
	    	    	 }
    			 }
    	}
    			
    				
    			 
    			 else {
    				 if(DeleiveryType==null)
    			    	{
    			    		 Alert a = new Alert(AlertType.ERROR);
    				            a.setContentText("Error");
    				            a.setHeaderText("Choose deleivery service");
    				            a.showAndWait();
    			    	}
    			    	else {
    			    	if(DeleiveryType.equals("Robot")||DeleiveryType.equals("Deleivery")||DeleiveryType.equals("SharedDeleivery"))
    			    	{
    			    		if(CityField.getText().equals("")||streetField.getText().equals("")||houseNumberField.getText().equals("")) {
    			    			 Alert a = new Alert(AlertType.ERROR);
    					            a.setContentText("Error");
    					            a.setHeaderText("your details that you insert is Wrong!");
    					            a.showAndWait();
    			    		}
    			    		else {address =new Address(CityField.getText(), streetField.getText(), houseNumberField.getText());}
    			    	}
    				 
    				 switch (DeleiveryType) {
	    				case "Deleivery":
	    					pricedeleivery=25;
	    					break;
	    				case "SharedDeleivery":
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
	    	    		 Stage stage = new Stage();
	    	    	OrdersDetailsController AFrame=new OrdersDetailsController();
	    			try {
	    				AFrame.start(stage);
	    			} catch (Exception e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
    			 }
    		}
    }
    	
	
    @FXML
    void payprivatebuttonAction(ActionEvent event) {
    	accountpayment="private";
    	
    	if(flagDate==0) {
    			 if(orderdateField.getText().equals("")||orderdateField.getText().matches("[a-zA-Z_]+")) {
    				  Alert a = new Alert(AlertType.ERROR);
    		            a.setContentText("Error");
    		            a.setHeaderText("your details that you insert is Wrong!");
    		            a.showAndWait();
    		    		}
    			 else {
    				 Time= orderdateField.getText();
	    	    	 try {
	    				date1=simpleDateFormat.parse(Time);
	    			    date2=simpleDateFormat.parse(dtf.format(now));
	    			    TimerMath( date1,date2);
	    			} catch (ParseException e1) {
	    				// TODO Auto-generated catch block
	    				e1.printStackTrace();
	    			}
	    	    	 if((differenceInHours<2)) {
	    	    		  Alert a = new Alert(AlertType.ERROR);
	    		            a.setContentText("Error");
	    		            a.setHeaderText("At least after two hour from now");
	    		            a.showAndWait();
	    	    	 }
	    	    	 else {
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
	    	    	
	    	    			switch (DeleiveryType) {
	    	    			case "Deleivery":
	    	    				pricedeleivery=25;
	    	    				break;
	    	    			case "SharedDeleivery":
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
	    	        		 Stage stage = new Stage();
	    	        	OrdersDetailsController AFrame=new OrdersDetailsController();
	    	    		try {
	    	    			AFrame.start(stage);
	    	    		} catch (Exception e) {
	    	    			// TODO Auto-generated catch block
	    	    			e.printStackTrace();
	    	    		}
	    	    	 }
	    	    	 
	    	    	
	    	    	 }
    			 }
    	}
    			
    				
    			 
    			 else {
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
    				 
    				 switch (DeleiveryType) {
	    				case "Deleivery":
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
	    	    		 Stage stage = new Stage();
	    	    	OrdersDetailsController AFrame=new OrdersDetailsController();
	    			try {
	    				AFrame.start(stage);
	    			} catch (Exception e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
    			 }
    		}
    
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	if(address!=null) {
    		enterAdresstxt.setVisible(true);
    		citytxt.setVisible(true);
    		streettxt.setVisible(true);
    		housenumbertxt.setVisible(true);
    		CityField.setVisible(true);
    		streetField.setVisible(true);
    		houseNumberField.setVisible(true);
    		
    		CityField.setText(address.getCity());
    		streetField.setText(address.getStreet());
    		houseNumberField.setText(address.getHouseNumber());
    		
    		
    		
    		
    	}
		Shadow.setVisible(false);
		Shadow1.setVisible(false);
		Star.setVisible(false);
		Pick1.setVisible(false);
    	Pick2.setVisible(false);
    	Pick3.setVisible(false);
    	Pick4.setVisible(false);
		if(ChatClient.w4ccard.getAccountType().equals("business")) {
		payprivatebutton1.setVisible(false);
		payprivatebutton.setVisible(true);
		paybussinessbutton.setVisible(true);
		ScanerQrController.Wallet=ChatClient.bussiness.getCeiling();
		walletTxt.setText(": "+ScanerQrController.Wallet+"$");
		}
		
		orderdateField.setVisible(false);
		if(ChatClient.w4ccard.getAccountType().equals("private")) {
			payprivatebutton.setVisible(false);
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
