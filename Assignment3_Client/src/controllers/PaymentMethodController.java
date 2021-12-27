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
	
	
	public static boolean IsDeleiveryShared;
	public  static LocalDateTime now = LocalDateTime.now();
	public  static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
	public  static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
	
	public static String DeleiveryType="null";
	
	public static Integer pricedeleivery;
	
	public static Integer orderPrice;
	
	public static Address address=null;
	
	public static String Time;
	
	public static String accountpayment;
	public static String HelpTime;
	
	public long differenceInMilliSeconds ;
	public long differenceInHours;
	public long differenceInMinutes;
	
	public static int flagDate=2; //0-> other, 1->now
	public static Integer Participants_Number;
	public static Integer Temp;
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
    private Text Discounttxt;

    @FXML
    private Text Star1;

    @FXML
    private Text Star11;

    @FXML
    private Text Star12;
    
    @FXML
    private TextField SharedDelNumfield;

    @FXML
    private Text Star2;
    
    @FXML
    void otherbuttonAction(ActionEvent event) {
    	orderdateField.setVisible(true);
    	flagDate=0;
    	Shadow.setVisible(false);
		Shadow1.setVisible(true);
		Star.setVisible(true);
		Discounttxt.setVisible(true);
    	
    }
  
    
    @FXML
    void nowbuttonAction(ActionEvent event) {
    	orderdateField.setVisible(false);
    	flagDate=1;
    	Shadow.setVisible(true);
		Shadow1.setVisible(false);
		Star.setVisible(false);
		Discounttxt.setVisible(false);
    	
    	
    }
    @FXML
    void BackButtonAction(ActionEvent event) {
    	if(DeleiveryType.equals("TakeAway")) 
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
    	IsDeleiveryShared=true;
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
		Star1.setVisible(true);
		Star11.setVisible(true);
		Star12.setVisible(true);
		Star2.setVisible(true);
    	SharedDelNumfield.setVisible(true);
		
    }

    @FXML
    void deleiveryButtonAction(ActionEvent event) {
    	DeleiveryType=("Deleivery");
    	IsDeleiveryShared=false;
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
		Star1.setVisible(true);
		Star11.setVisible(true);
		Star12.setVisible(true);
		Star2.setVisible(false);
    	SharedDelNumfield.setVisible(false);
	
    }

    @FXML
    void robotbuttonAction(ActionEvent event) {
    	DeleiveryType=("Robot");
    	IsDeleiveryShared=false;
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
		Star1.setVisible(true);
		Star11.setVisible(true);
		Star12.setVisible(true);
		Star2.setVisible(false);
    	SharedDelNumfield.setVisible(false);
		
    }

    @FXML
    void takeawaybuttonAction(ActionEvent event) {
    	DeleiveryType=("TakeAway");
    	IsDeleiveryShared=false;
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
		Star1.setVisible(false);
		Star11.setVisible(false);
		Star12.setVisible(false);
		Star2.setVisible(false);
    	SharedDelNumfield.setVisible(false);
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
    	
    	if(DeleiveryType.equals("SharedDeleivery"))
    	{
    		IsDeleiveryShared=true;
  
    	}
    	accountpayment="buissiness";
    	if(flagDate==0)
    	{
    		if(orderdateField.getText().equals("")||orderdateField.getText().matches("[a-zA-Z_]+")) 
    		{
    			Alert a = new Alert(AlertType.ERROR);
	            a.setContentText("Error");
	            a.setHeaderText("Insert requested Date");
	            a.showAndWait();
    		}else//////////////////////////////////////////////////////////////////////
    		   {
    			 Time= orderdateField.getText();
    	    	 try {
    				date1=simpleDateFormat.parse(Time);
    			    date2=simpleDateFormat.parse(dtf.format(now));
    			    TimerMath( date1,date2);
    			     } catch (ParseException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();}
    	    	 
    	    	       if(differenceInHours<2)
    	    	          {
    	    	    	   Alert a = new Alert(AlertType.ERROR);
	    		            a.setContentText("Error");
	    		            a.setHeaderText("At least after two hour from now");
	    		            a.showAndWait();
    	    	          }
    	    	       else
    	    	       {
    	    	    	   if( DeleiveryType.equals("Robot")||DeleiveryType.equals("Deleivery")||DeleiveryType.equals("SharedDeleivery"))
     	       		           {
     	    	    	          if(CityField.getText().equals("")||streetField.getText().equals("")||houseNumberField.getText().equals("")) 
     	    	    	           {
   	    	    			         Alert a = new Alert(AlertType.ERROR);
   	    			                 a.setContentText("Error");
   	    			                 a.setHeaderText("Insert your address");
   	    			                 a.showAndWait();
     	       		                }
     	    	    	          else if(DeleiveryType.equals("SharedDeleivery"))
     	                           {
     	                        	   if(SharedDelNumfield.getText().equals("")||!SharedDelNumfield.getText().matches("[a-zA-Z_]+")&&IsDeleiveryShared==false)
     	                        	   {
     	                        		 Alert a = new Alert(AlertType.ERROR);
  		                                a.setContentText("Error");
  		                                a.setHeaderText("Participants Number is Wrong!");
  		                                a.showAndWait();
     	                        	   }
     	                        	  else
      	                        	   {
      	                        		   Participants_Number=Integer.valueOf(SharedDelNumfield.getText());
      	               /////////////////////////
      	                        		   System.out.println(Participants_Number);
      	                        		address =new Address(CityField.getText(), streetField.getText(), houseNumberField.getText());
      	                        		System.out.println(address);
        	        	                  if(DeleiveryType.equals("null"))
     	        	                     {
     	        				           Alert a = new Alert(AlertType.ERROR);
     	        	                       a.setContentText("Error");
     	        	                       a.setHeaderText("Choose Deleivery Service");
     	        	                       a.showAndWait(); 
     	        	                     }
     	        	                  else
     	        	                       {
     	        	                            switch (DeleiveryType) 
      	    	                                 {
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
     	        		                        if((Integer.valueOf(ScanerQrController.Wallet)-(orderPrice))<0) 
     	        		                             {
     	        			                            Alert a = new Alert(AlertType.ERROR);
     	        	                                    a.setContentText("Error");
     	        	                                    a.setHeaderText("there is no enough money in your bussiness wallet ");
     	        	                                    a.showAndWait();
     	        		                              }
     	        		                                 else
     	        		                                       {
     	        			                                     Stage stage = new Stage();
     		        	                                         OrdersDetailsController AFrame=new OrdersDetailsController();
     		    		                                         try {
     		    			                                     AFrame.start(stage);}
     		    		                                         catch (Exception e) {
     		    			                                     // TODO Auto-generated catch block
     		    			                                      e.printStackTrace();}  
     	        			                                    }
     	        	                    }
        	                        
      	                        	   }
     	                        	   
     	                           }
     	    	    	       
     		                  }
    	    	    	   else
    	    	    	   {
    	    	    		   if(DeleiveryType.equals("null"))
    	    	        	    {
	    		        				  Alert a = new Alert(AlertType.ERROR);
	    		        	              a.setContentText("Error");
	    		        	              a.setHeaderText("Choose Deleivery Service");
	    		        	              a.showAndWait(); 
    	    	        	    }
    	    	        	    else
    	    	        	    {
    	    	        	       switch (DeleiveryType) 
	    	    	    	        {
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
	    		        		  if((Integer.valueOf(ScanerQrController.Wallet)-(orderPrice))<0) 
	    		        		       {
	    		        			      Alert a = new Alert(AlertType.ERROR);
	    		        	              a.setContentText("Error");
	    		        	              a.setHeaderText("there is no enough money in your bussiness wallet ");
	    		        	              a.showAndWait();
	    		        		      }
	    		        		  else
	    		        		  {
	    		        			  
	    		        				  Stage stage = new Stage();
	    			        	          OrdersDetailsController AFrame=new OrdersDetailsController();
	    			    		          try {
	    			    			        AFrame.start(stage);}
	    			    		            catch (Exception e) {
	    			    			        // TODO Auto-generated catch block
	    			    			        e.printStackTrace();}  
	    		        			   
	    		        			     
	    		        		   }
    	    	        	    }
    	    	    	   }
    	    	    	    
    	    	       }  	    
    	}
    	
    	}///now
    	else{
    		         if( DeleiveryType.equals("Robot")||DeleiveryType.equals("Deleivery")||DeleiveryType.equals("SharedDeleivery"))
		                   {
   	                           if(CityField.getText().equals("")||streetField.getText().equals("")||houseNumberField.getText().equals("")) 
   	                                 {
 			                           Alert a = new Alert(AlertType.ERROR);
		                                a.setContentText("Error");
		                                a.setHeaderText("enter your address");
		                                a.showAndWait();
		                             }
   	                           else if(DeleiveryType.equals("SharedDeleivery"))
   	                           {
   	                        	   if((SharedDelNumfield.getText().equals("")||SharedDelNumfield.getText().matches("[a-zA-Z_]+"))&&IsDeleiveryShared==false)
   	                        	   {
   	                        		 Alert a = new Alert(AlertType.ERROR);
		                                a.setContentText("Error");
		                                a.setHeaderText("Participants Number is Wrong!");
		                                a.showAndWait();
   	                        	   }
   	                        	   else
   	                        	   {
   	                        		   
   	                        		   Participants_Number=Integer.valueOf(SharedDelNumfield.getText());
   	                        		   Temp=Participants_Number;
   	                        		address =new Address(CityField.getText(), streetField.getText(), houseNumberField.getText());
     	        	                  if(DeleiveryType.equals("null"))
  	        	                     {
  	        				           Alert a = new Alert(AlertType.ERROR);
  	        	                       a.setContentText("Error");
  	        	                       a.setHeaderText("Choose Deleivery Service");
  	        	                       a.showAndWait(); 
  	        	                     }
  	        	                  else
  	        	                       {
  	        	                            switch (DeleiveryType) 
   	    	                                 {
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
  	        		                        if((Integer.valueOf(ScanerQrController.Wallet)-(orderPrice))<0) 
  	        		                             {
  	        			                            Alert a = new Alert(AlertType.ERROR);
  	        	                                    a.setContentText("Error");
  	        	                                    a.setHeaderText("there is no enough money in your bussiness wallet ");
  	        	                                    a.showAndWait();
  	        		                              }
  	        		                                 else
  	        		                                       {
  	        			                                     Stage stage = new Stage();
  		        	                                         OrdersDetailsController AFrame=new OrdersDetailsController();
  		    		                                         try {
  		    			                                     AFrame.start(stage);}
  		    		                                         catch (Exception e) {
  		    			                                     // TODO Auto-generated catch block
  		    			                                      e.printStackTrace();}  
  	        			                                    }
  	        	                    }
     	                        
   	                        	   }
   	                           }
   	                         
   	        	                  
                      }
  	   else
  	   {
  		 if(DeleiveryType.equals("null"))
  	    {
 				  Alert a = new Alert(AlertType.ERROR);
 	              a.setContentText("Error");
 	              a.setHeaderText("Choose Deleivery Service");
 	              a.showAndWait(); 
  	    }
  	    else
  	    {
  	       switch (DeleiveryType) 
 	        {
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
 		  if((Integer.valueOf(ScanerQrController.Wallet)-(orderPrice))<0) 
 		       {
 			      Alert a = new Alert(AlertType.ERROR);
 	              a.setContentText("Error");
 	              a.setHeaderText("there is no enough money in your bussiness wallet ");
 	              a.showAndWait();
 		      }
 		  else
 		  {
 			  
 				  Stage stage = new Stage();
     	          OrdersDetailsController AFrame=new OrdersDetailsController();
 		          try {
 			        AFrame.start(stage);}
 		            catch (Exception e) {
 			        // TODO Auto-generated catch block
 			        e.printStackTrace();}  
 			   
 			     
 		   }
  	    }
  	   }
  	    
     }  	    
}
    
    @FXML
    void payprivatebuttonAction(ActionEvent event) {
    	if(DeleiveryType.equals("SharedDeleivery"))
    	{
    		IsDeleiveryShared=true;
    	}
    	accountpayment="private";
    	if(flagDate==0)
    	{
    		if(orderdateField.getText().equals("")||orderdateField.getText().matches("[a-zA-Z_]+")) 
    		{
    			Alert a = new Alert(AlertType.ERROR);
	            a.setContentText("Error");
	            a.setHeaderText("Insert requested Date");
	            a.showAndWait();
    		}else//////////////////////////////////////////////////////////////////////
    		   {
    			 Time= orderdateField.getText();
    	    	 try {
    				date1=simpleDateFormat.parse(Time);
    			    date2=simpleDateFormat.parse(dtf.format(now));
    			    TimerMath( date1,date2);
    			     } catch (ParseException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();}
    	    	 
    	    	       if(differenceInHours<2)
    	    	          {
    	    	    	   Alert a = new Alert(AlertType.ERROR);
	    		            a.setContentText("Error");
	    		            a.setHeaderText("At least after two hour from now");
	    		            a.showAndWait();
    	    	          }
    	    	       else
    	    	       {
    	    	    	   if( DeleiveryType.equals("Robot")||DeleiveryType.equals("Deleivery"))
     	       		           {
     	    	    	          if(CityField.getText().equals("")||streetField.getText().equals("")||houseNumberField.getText().equals("")) 
     	    	    	           {
   	    	    			         Alert a = new Alert(AlertType.ERROR);
   	    			                 a.setContentText("Error");
   	    			                 a.setHeaderText("Insert your address");
   	    			                 a.showAndWait();
     	       		                }
     	    	    	         else {
     	    	    	        	    address =new Address(CityField.getText(), streetField.getText(), houseNumberField.getText());
     	    	    	        	    if(DeleiveryType.equals("null"))
     	    	    	        	    {
        	    		        				  Alert a = new Alert(AlertType.ERROR);
        	    		        	              a.setContentText("Error");
        	    		        	              a.setHeaderText("Choose Deleivery Service");
        	    		        	              a.showAndWait(); 
     	    	    	        	    }
     	    	    	        	    else
     	    	    	        	    {
     	    	    	        	       switch (DeleiveryType) 
        	    	    	    	        {
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
        	    		        				  Stage stage = new Stage();
        	    			        	          OrdersDetailsController AFrame=new OrdersDetailsController();
        	    			    		          try {
        	    			    			        AFrame.start(stage);}
        	    			    		            catch (Exception e) {
        	    			    			        // TODO Auto-generated catch block
        	    			    			        e.printStackTrace();}  
        	    		        			   
        	    		        			     
        	    		        		   
     	    	    	        	    }
     	    	    	        	
     	    		        		  
     	    	    	             }
     		                  }
    	    	    	   else
    	    	    	   {
    	    	    		   if(DeleiveryType.equals("null"))
    	    	        	    {
	    		        				  Alert a = new Alert(AlertType.ERROR);
	    		        	              a.setContentText("Error");
	    		        	              a.setHeaderText("Choose Deleivery Service");
	    		        	              a.showAndWait(); 
    	    	        	    }
    	    	        	    else
    	    	        	    {
    	    	        	       switch (DeleiveryType) 
	    	    	    	        {
	    	   	    			        case "Deleivery":
	    	   	    				      pricedeleivery=25;
	    	   	    				      break;
	    	   	    			       default:
	    	   	    				      pricedeleivery=0;
	    	   	    				      break;
	    	   	    			    }
	    	    	    	   	    orderPrice=ItemDetailsController.TotalPrice+pricedeleivery;
	    		        				  Stage stage = new Stage();
	    			        	          OrdersDetailsController AFrame=new OrdersDetailsController();
	    			    		          try {
	    			    			        AFrame.start(stage);}
	    			    		            catch (Exception e) {
	    			    			        // TODO Auto-generated catch block
	    			    			        e.printStackTrace();}  
	    		        			   
	    		        			     
	    		        		   
    	    	        	    }
    	    	    	   }
    	    	    	    
    	    	       }  	    
    	}
    	
    	}///now
    	else{
    		         if( DeleiveryType.equals("Robot")||DeleiveryType.equals("Deleivery"))
		                   {
   	                           if(CityField.getText().equals("")||streetField.getText().equals("")||houseNumberField.getText().equals("")) 
   	                                 {
 			                           Alert a = new Alert(AlertType.ERROR);
		                                a.setContentText("Error");
		                                a.setHeaderText("enter your address");
		                                a.showAndWait();
		                             }
   	                else {
   	        	              address =new Address(CityField.getText(), streetField.getText(), houseNumberField.getText());
   	        	                  if(DeleiveryType.equals("null"))
	        	                     {
	        				           Alert a = new Alert(AlertType.ERROR);
	        	                       a.setContentText("Error");
	        	                       a.setHeaderText("Choose Deleivery Service");
	        	                       a.showAndWait(); 
	        	                     }
	        	                  else
	        	                       {
	        	                            switch (DeleiveryType) 
 	    	                                 {
	    			                          case "Deleivery":
	    				                          pricedeleivery=25;
	    				                           break;
	    			                         default:
	    				                         pricedeleivery=0;
	    				                         break;
	    			                         }
 	    	   	                            orderPrice=ItemDetailsController.TotalPrice+pricedeleivery;
	        			                                     Stage stage = new Stage();
		        	                                         OrdersDetailsController AFrame=new OrdersDetailsController();
		    		                                         try {
		    			                                     AFrame.start(stage);}
		    		                                         catch (Exception e) {
		    			                                     // TODO Auto-generated catch block
		    			                                      e.printStackTrace();}  
	        			                                    
	        	                    }
   	                        }
                      }
  	   else
  	   {
  		 if(DeleiveryType.equals("null"))
  	    {
 				  Alert a = new Alert(AlertType.ERROR);
 	              a.setContentText("Error");
 	              a.setHeaderText("Choose Deleivery Service");
 	              a.showAndWait(); 
  	    }
  	    else
  	    {
  	       switch (DeleiveryType) 
 	        {
			        case "Deleivery":
				      pricedeleivery=25;
				      break;
			       default:
				      pricedeleivery=0;
				      break;
			    }
 	   	    orderPrice=ItemDetailsController.TotalPrice+pricedeleivery;
 				  Stage stage = new Stage();
     	          OrdersDetailsController AFrame=new OrdersDetailsController();
 		          try {
 			        AFrame.start(stage);}
 		            catch (Exception e) {
 			        // TODO Auto-generated catch block
 			        e.printStackTrace();}  
 			   
 			     
 		   
  	    }
  	   }
  	    
     } 
    
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	if(ChatClient.w4ccard.getAccountType().equals("business")) {
    		payprivatebutton1.setVisible(false);
    		payprivatebutton.setVisible(true);
    		paybussinessbutton.setVisible(true);
    		ScanerQrController.Wallet=ChatClient.bussiness.getCeiling();
    		walletTxt.setText(": "+ScanerQrController.Wallet+"$");
    		}
    	if(ChatClient.w4ccard.getAccountType().equals("private")) {
			payprivatebutton.setVisible(false);
			SharedDelButton.setVisible(false);
			paybussinessbutton.setVisible(false);
			Wallettxt.setVisible(false);
			walletTxt.setVisible(false);
			txt4.setVisible(false);
		}
    	if(flagDate==1) {
        	Shadow.setVisible(true);
    		Shadow1.setVisible(false);
    		orderdateField.setVisible(false);
    		Discounttxt.setVisible(false);
    		Star.setVisible(false);
    	}
    	else if (flagDate==0){
    		System.out.println(Time);///////////////////////////
    		Shadow.setVisible(false);
    		Shadow1.setVisible(true);
    		orderdateField.setVisible(true);
    		orderdateField.setText(Time);
    		Discounttxt.setVisible(true);
    		Star.setVisible(true);
    	}
    	  else 
    	   {
    			Shadow.setVisible(false);
        		Shadow1.setVisible(false);
        		orderdateField.setVisible(false);
        		Discounttxt.setVisible(false);
        		Star.setVisible(false);
        		
    	   }
    	if(IsDeleiveryShared==true)
    	{
    		DeleiveryType="SharedDeleivery";
    		Pick1.setVisible(false);
	    	Pick2.setVisible(false);
	    	Pick3.setVisible(false);
	    	Pick4.setVisible(true);
	    	Visa.setVisible(false);
	    	Star2.setVisible(false);
	    	SharedDelNumfield.setVisible(false);
	        enterAdresstxt.setVisible(true);
	    			citytxt.setVisible(true);
	    			streettxt.setVisible(true);
	    			housenumbertxt.setVisible(true);
	    			CityField.setVisible(true);
	    			streetField.setVisible(true);
	    			houseNumberField.setVisible(true);
	    			Star1.setVisible(true);
	    			Star11.setVisible(true);
	    			Star12.setVisible(true);
	    			    if(address!=null)
	    			     {
	    			    	CityField.setText(address.getCity());
	    		    		streetField.setText(address.getStreet());
	    		    		houseNumberField.setText(address.getHouseNumber());
	    			     }
	    	
    	}
    	else{

        	switch (DeleiveryType) {
    		case "TakeAway":
    			///////pickes
    			Pick1.setVisible(true);
    	    	Pick2.setVisible(false);
    	    	Pick3.setVisible(false);
    	    	Pick4.setVisible(false);
    	    	Visa.setVisible(true);
    	    	////////address
    	    	enterAdresstxt.setVisible(false);
    			citytxt.setVisible(false);
    			streettxt.setVisible(false);
    			housenumbertxt.setVisible(false);
    			CityField.setVisible(false);
    			streetField.setVisible(false);
    			houseNumberField.setVisible(false);
    			Star1.setVisible(false);
    			Star11.setVisible(false);
    			Star12.setVisible(false);
    			break;
    		case "Deleivery":
    		    ///////pickes
    			Pick1.setVisible(false);
    	    	Pick2.setVisible(true);
    	    	Pick3.setVisible(false);
    	    	Pick4.setVisible(false);
    	    	Visa.setVisible(false);
    	        ////////address
    		    enterAdresstxt.setVisible(true);
    			citytxt.setVisible(true);
    			streettxt.setVisible(true);
    			housenumbertxt.setVisible(true);
    			CityField.setVisible(true);
    			streetField.setVisible(true);
    			houseNumberField.setVisible(true);
    			Star1.setVisible(true);
    			Star11.setVisible(true);
    			Star12.setVisible(true);
    			    if(address!=null)
    			     {
    			    	CityField.setText(address.getCity());
    		    		streetField.setText(address.getStreet());
    		    		houseNumberField.setText(address.getHouseNumber());
    			     }
    			    	break;
    		case "Robot":
    		    ///////pickes
    			Pick1.setVisible(false);
    	    	Pick2.setVisible(false);
    	    	Pick3.setVisible(true);
    	    	Pick4.setVisible(false);
    	    	Visa.setVisible(false);
    	        ////////address
    		    enterAdresstxt.setVisible(true);
    			citytxt.setVisible(true);
    			streettxt.setVisible(true);
    			housenumbertxt.setVisible(true);
    			CityField.setVisible(true);
    			streetField.setVisible(true);
    			houseNumberField.setVisible(true);
    			Star1.setVisible(true);
    			Star11.setVisible(true);
    			Star12.setVisible(true);
    			    if(address!=null)
    			     {
    			    	CityField.setText(address.getCity());
    		    		streetField.setText(address.getStreet());
    		    		houseNumberField.setText(address.getHouseNumber());
    			     }
    			    	break;
    		case "SharedDeleivery":
    		    ///////pickes
    			Pick1.setVisible(false);
    	    	Pick2.setVisible(false);
    	    	Pick3.setVisible(false);
    	    	Pick4.setVisible(true);
    	    	Visa.setVisible(false);
    	    	Star2.setVisible(true);
    	    	SharedDelNumfield.setVisible(true);
    	    	SharedDelNumfield.setText(String.valueOf(Participants_Number));
    	        ////////address
    		    enterAdresstxt.setVisible(true);
    			citytxt.setVisible(true);
    			streettxt.setVisible(true);
    			housenumbertxt.setVisible(true);
    			CityField.setVisible(true);
    			streetField.setVisible(true);
    			houseNumberField.setVisible(true);
    			Star1.setVisible(true);
    			Star11.setVisible(true);
    			Star12.setVisible(true);
    			    if(address!=null)
    			     {
    			    	CityField.setText(address.getCity());
    		    		streetField.setText(address.getStreet());
    		    		houseNumberField.setText(address.getHouseNumber());
    			     }
    			    	break;
    		default:
    		    ///////pickes
    			Pick1.setVisible(false);
    	    	Pick2.setVisible(false);
    	    	Pick3.setVisible(false);
    	    	Pick4.setVisible(false);
    	    	Visa.setVisible(true);
    	    	 ////////address
    		    enterAdresstxt.setVisible(false);
    			citytxt.setVisible(false);
    			streettxt.setVisible(false);
    			housenumbertxt.setVisible(false);
    			CityField.setVisible(false);
    			streetField.setVisible(false);
    			houseNumberField.setVisible(false);
    			Star1.setVisible(false);
    			Star11.setVisible(false);
    			Star12.setVisible(false);
    			Star2.setVisible(false);
    	    	SharedDelNumfield.setVisible(false);
    			break;
    		}
    	}
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
