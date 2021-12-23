package controllers;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import common.Message;
import common.MessageType;
import common.Orders;
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
	public static Orders orders=null;
	public static LocalDateTime now = LocalDateTime.now();  
	
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
    	if(!PaymentMethodController.DeleiveryType.equals("Take-Away")&&!PaymentMethodController.DeleiveryType.equals("Robot")) {
    	orders=new Orders(ChooseResturantController.resturant.getResturantID(),ChatClient.userlogged.getId(),ItemDetailsController.AdditemList.getIdIteam(),
    			PaymentMethodController.orderTime, PaymentMethodController.dtf.format(now),String.valueOf(orderPrice),ChatClient.w4ccard.getAccountType(),
    			PaymentMethodController.accountpayment,PaymentMethodController.address.toString(), PaymentMethodController.DeleiveryType);
    	}
    	else {
    		orders=new Orders(ChooseResturantController.resturant.getResturantID(),ChatClient.userlogged.getId(),ItemDetailsController.AdditemList.getIdIteam(),
        			PaymentMethodController.orderTime, PaymentMethodController.dtf.format(now),String.valueOf(orderPrice),ChatClient.w4ccard.getAccountType(),
        			PaymentMethodController.accountpayment,"NoAddress", PaymentMethodController.DeleiveryType);
    	}
    	
    	ClientUI.chat.accept(new Message(MessageType.OrdersListToDataBase,orders.getResturantID()+ " " + orders.getCustomerID()+ " " + orders.getItemsID()+ " " + 
    	orders.getRequestedDate()+ " " + orders.getOrderedDate()+ " " + orders.getTotalPrice()
    	+ " " + orders.getAccountType()+ " " + orders.getAccountpayment()+ " " + orders.getAddress()+ " " + orders.getDeleiveryService()));
    	for(int i=0;i<ItemDetailsController.itemList.size();i++)
    	{
    		ClientUI.chat.accept(new Message(MessageType.itemsListtoDataBase, orders.getItemsID()+" "+ItemDetailsController.itemList.get(i).getIdIteam()+" "+
    	ItemDetailsController.itemList.get(i).getDishes()+" "+ItemDetailsController.itemList.get(i).getExtras()));
    	}
    	
    	//ItemDetailsController.itemList
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		creditfield.setText(ChatClient.w4ccard.getCreditCardNumber());
		
		resturantfield.setText(ChooseResturantController.resturant.getResturantName());
		requesteddatefield.setText(PaymentMethodController.orderTime);
		delevfield.setText(PaymentMethodController.DeleiveryType);
		orderPrice=ItemDetailsController.TotalPrice+PaymentMethodController.pricedeleivery;
		totalpricefield.setText(String.valueOf(orderPrice));
		
		if(PaymentMethodController.DeleiveryType.equals("Take-Away")||PaymentMethodController.DeleiveryType.equals("Robot")) {
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
