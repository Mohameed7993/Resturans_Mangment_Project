package controllers;

import java.net.URL;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import common.Approvedtype;
import common.ItemList;
import common.Message;
import common.MessageType;

import common.OrdersList;
import common.StatusType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OrdersDetailsController implements Initializable {
	public static OrdersList orders=null;
	
	
	public LocalDateTime ArrivalTime;
	public static String orderTime;
	public static Integer orderPackageNumber;
	public String time;
	
	public static ArrayList<ItemList> Items=new ArrayList<>();

	public static ItemList AddItem;
	
    public int orderPrice;
    @FXML
    private ImageView image;

    @FXML
    private Button BackButton;

    @FXML
    private Button ConfirmButton;

    @FXML
    private Text Orderdetailstxt;

    @FXML
    private Text resturantnametxt;

    @FXML
    private Text resturantfield;

    @FXML
    private Text reqdatetxt;

    @FXML
    private Text deleiverytxt;

    @FXML
    private Text totalpricetxt;

    @FXML
    private Text totalmesstxt;

    @FXML
    private Text Citytxt;
    
    @FXML
    private Text addresstxt;

    @FXML
    private Text streettxt;

    @FXML
    private Text HouseNumbertxt;

    @FXML
    private Text requesteddatefield;

    @FXML
    private Text delevfield;

    @FXML
    private Text totalpricefield;

    @FXML
    private Text cityfield;

    @FXML
    private Text streetfield;

    @FXML
    private Text huosefiled;

    @FXML
    private Text credittxt;

    @FXML
    private Text empnametxt;

    @FXML
    private Text empidtxt;

    @FXML
    private Text creditfield;

    @FXML
    private Text empnamefield;

    @FXML
    private Text empidfield;

    @FXML
    void BackButtonAction(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
    	PaymentMethodController AFrame=new PaymentMethodController();
		try {
			AFrame.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void ConfirmButtonAction(ActionEvent event) {
    	time=PaymentMethodController.dtf.format(PaymentMethodController.now);
    	if(PaymentMethodController.flagDate==1)
    		
    	orderTime=(time);
    	else 
    		orderTime=PaymentMethodController.Time;
    	
    	orderPackageNumber=null;
    	if(!PaymentMethodController.DeleiveryType.equals("TakeAway")) {
    		orders= new OrdersList(ChatClient.userlogged.getId(),ChooseResturantController.resturant.getResturantName(),orderPackageNumber,orderTime
    				,time,String.valueOf(orderPrice),PaymentMethodController.address.toString(),PaymentMethodController.DeleiveryType
    				,"UnReady","0","UnApproved");
 
    	}
    	else {
    		orders= new OrdersList(ChatClient.userlogged.getId(),ChooseResturantController.resturant.getResturantName(),orderPackageNumber,orderTime
    				,time,String.valueOf(orderPrice),"NoAddress",PaymentMethodController.DeleiveryType
    				,"UnReady","0","UnApproved");
    	}
    		
    	ClientUI.chat.accept(new Message(MessageType.OrdersListToDataBase, orders.getCustomer_ID()+" "+orders.getResturant()+" "+orders.getOrderPackageNumber()
    	+" "+orders.getRequestDate()+" "+orders.getOrderedDate()+" "+orders.getTotalPrice()+" "+orders.getAddress()+" "+orders.getDeleiveryService()
    	+" "+orders.getStatus()+" "+orders.getArrivalTime()+" "+orders.getApprovalRecieving()));
    
    	ClientUI.chat.accept(new Message(MessageType.GetOrder,ChatClient.userlogged.getId()));
    	

     	for(int i=0;i<ItemDetailsController.itemList.size();i++)
    	{
    		
    	     if(OptionalSelectionController.sel.size()!=0) {
    		          AddItem=new ItemList(ItemDetailsController.itemList.get(i).getTypeMeal() , ItemDetailsController.itemList.get(i).getDishes(),
    				  ItemDetailsController.itemList.get(i).getExtras().toString().replaceAll(" ",""), ItemDetailsController.itemList.get(i).getQuantity()
    				  ,ItemDetailsController.itemList.get(i).getTotalPrice(),ChatClient.order2.getOrderPackageNumber());
    		   Items.add(AddItem);
    	       }
    	     else {
    		         AddItem=new ItemList(ItemDetailsController.itemList.get(i).getTypeMeal() , ItemDetailsController.itemList.get(i).getDishes(),
    				"NoExtra", ItemDetailsController.itemList.get(i).getQuantity()
   				   ,ItemDetailsController.itemList.get(i).getTotalPrice(),ChatClient.order2.getOrderPackageNumber());
    	 	Items.add(AddItem);
    	        }  
    }
    	
    	
    	for(int i=0;i<Items.size();i++)
    	{
    		ClientUI.chat.accept(new Message(MessageType.itemsListtoDataBase, Items.get(i).getTheMeal()+" "+ Items.get(i).getTheDish()+" "+
    		Items.get(i).getIngredient()+" "+ Items.get(i).getQuantity()+" "+ Items.get(i).getPrice()+" "+ Items.get(i).getPackageID()));
    	}
    	
    	
    	MyCartController.numberitem=0;
    	ItemDetailsController.itemList.clear();
    	Items.clear();
    
    	 ((Node) event.getSource()).getScene().getWindow().hide();// get stage
    	CustomerHomeController AFrame=new CustomerHomeController();
		try {
			AFrame.start(CustomerHomeController.stage1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
 
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		creditfield.setText(ChatClient.w4ccard.getCreditCardNumber());
		
		resturantfield.setText(ChooseResturantController.resturant.getResturantName());
		requesteddatefield.setText(orderTime);
		delevfield.setText(PaymentMethodController.DeleiveryType);
		orderPrice=ItemDetailsController.TotalPrice+PaymentMethodController.pricedeleivery;
		totalpricefield.setText(String.valueOf(orderPrice));
		
		if(PaymentMethodController.DeleiveryType.equals("TakeAway")||PaymentMethodController.DeleiveryType.equals("Robot")) {
			addresstxt.setVisible(false);
			Citytxt.setVisible(false);
			streettxt.setVisible(false);
			HouseNumbertxt.setVisible(false);
			cityfield.setVisible(false);
			streetfield.setVisible(false);
			huosefiled.setVisible(false);
		}
		else {
			cityfield.setText(PaymentMethodController.address.getCity());
			streetfield.setText(PaymentMethodController.address.getStreet());
			huosefiled.setText(PaymentMethodController.address.getHouseNumber());
		}
		if(ChatClient.w4ccard.getAccountType().equals("private"))
		{
			empidtxt.setVisible(false);
			empidfield.setVisible(false);
			empnamefield.setVisible(false);
			empnametxt.setVisible(false);
		}
		else {
			empidfield.setText(ChatClient.bussiness.getEmployerId());
			empnamefield.setText(ChatClient.bussiness.getEmployerName());
		}
		
		
	}
	
	public void start(Stage stage)  throws Exception {
		// TODO Auto-generated method stub
		
		Parent root = FXMLLoader.load(getClass().getResource("/View/OrdersDetails.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Payment");
		stage.setScene(scene);

		stage.show();
	}

}
