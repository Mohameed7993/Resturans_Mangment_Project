package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import common.DishForResturant;
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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class AddItemController implements Initializable{

    public static boolean add;
    @FXML
    private TextField meal;

    @FXML
    private TextArea optionals;

    @FXML
    private TextField price;

    @FXML
    private TextField type;
    
    @FXML
    private Text success;
	
	
	
	
	
	
	@FXML
	void back(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		UpdateMenuListForResturantController updateMenu = new UpdateMenuListForResturantController();
		try {
			updateMenu.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	 @FXML
	    void add(ActionEvent event) {
		 
		 
		 if (type.getText().equals("") || meal.getText().equals("") || price.getText().equals("")) {
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("Error");
				a.setHeaderText("You must add meal details!");
				a.showAndWait();
			} else if (optionals.getText().equals("")) {

				DishForResturant dish = new DishForResturant(ChatClient.resturant.getId(), type.getText(), meal.getText(),
						optionals.getText(), null, Integer.valueOf(price.getText()));
				ClientUI.chat.accept(new Message1(MessageType.additem, dish));

				if (!add) {

					success.setVisible(add);
					Alert a = new Alert(AlertType.ERROR);
					a.setContentText("Error");
					a.setHeaderText("can't add this item!");
					a.showAndWait();

				} else {
					success.setVisible(add);
				}

			}

			else {
				String[] split1 = optionals.getText().split(",");
				if (split1.length % 2 == 1) {
					Alert a = new Alert(AlertType.ERROR);
					a.setContentText("Error");
					a.setHeaderText("enter option as\n''option,12 \n another,20...''");
					a.showAndWait();
				} else {
					DishForResturant dish = new DishForResturant(ChatClient.resturant.getId(), type.getText(),
							meal.getText(), optionals.getText(), null, Integer.valueOf(price.getText()));
					ClientUI.chat.accept(new Message1(MessageType.additem, dish));
					if (!add) {

						success.setVisible(add);
						Alert a = new Alert(AlertType.ERROR);
						a.setContentText("Error");
						a.setHeaderText("can't add this item!");
						a.showAndWait();

					} else {
						success.setVisible(add);
					}
				}
			}
		 
	 }
	
	
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		success.setVisible(false);
		
	}
    public void start(Stage primaryStage) throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("/View/aaa.fxml"));

    	Scene scene = new Scene(root);
    	primaryStage.setTitle("ManagerHome");
    	primaryStage.setScene(scene);

		primaryStage.centerOnScreen();

    	primaryStage.show();
    }
}