package controllers;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import common.Message;
import common.MessageType;
import common.TybeMeal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ResturantMenuController  implements Initializable {

	ObservableList<TybeMeal> tm;
    @FXML
    private ImageView Image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;

    @FXML
    private Button BackButton;

    @FXML
    private Text ResturantName;

    @FXML
    private Button AddToCartButton;

    @FXML
    private ComboBox<String> TybeMealCom;

    @FXML
    private ComboBox<String> DishCom;

    @FXML
    private ComboBox<String> SelctionCom;

    @FXML
    private TextField PriceField;

    @FXML
    private Text Pricetxt;

    @FXML
    private Button MyCartButton;

    
    @FXML
    void AddToCartButtonAction(ActionEvent event) {

    }

    @FXML
    void BackButtonAction(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
    	ChooseResturantController AFrame=new ChooseResturantController();
		try {
			AFrame.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void MyCartButtonAction(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		ResturantName.setText(ChooseResturantController.resturant.getResturantName()+" Menu");
		
		TybeMealCom.getItems().clear();
		ClientUI.chat.accept(new Message(MessageType.ViewTybeMeallist,ChooseResturantController.resturant.getResturantID())); //// sending id resturant to get tybe meal list.
		tm=FXCollections.observableArrayList(ChatClient.tybemeal);
		for(TybeMeal t : tm)
			TybeMealCom.getItems().add(t.getTybeMeal());
	
	}

	public void start(Stage stage)  throws Exception {
		// TODO Auto-generated method stub
		
		Parent root = FXMLLoader.load(getClass().getResource("/View/ResturantMenu.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Menu");
		
		stage.setScene(scene);

		stage.show();
	}

}

