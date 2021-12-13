package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import common.Dish;
import common.Message;
import common.MessageType;
import common.Selection;
import common.TybeMeal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OptionalSelectionController implements Initializable{

    ObservableList<Selection> Sel;
	
	public static Selection selection;
	
	
    @FXML
    private ImageView image;

    @FXML
    private Button BackButton;

    @FXML
    private Button nextButton;

    @FXML
    private Text resturantnametxt;

    @FXML
    private Text Optionaltxt;

    @FXML
    private TableView<Selection> OptionalSelectionList;

    @FXML
    private TableColumn<Selection,String> SelectionsCol;

    @FXML
    void BackButtonAction(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
    	DishController AFrame=new DishController();
		try {
			AFrame.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch blocks
			e.printStackTrace();
		}
    }

    @FXML
    void nextButtonAction(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
        resturantnametxt.setText(ChooseResturantController.resturant.getResturantName());
		SelectionsCol.setCellValueFactory(new PropertyValueFactory<Selection,String>("Selction"));
		
		ClientUI.chat.accept(new Message(MessageType.ViewSelctionsList,DishController.dish.getDish_ID()));
		Sel=FXCollections.observableArrayList(ChatClient.selection);
		OptionalSelectionList.setItems(Sel);
		
		OptionalSelectionList.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				if(OptionalSelectionList.getSelectionModel().getSelectedItem()!=null)
				selection=OptionalSelectionList.getSelectionModel().getSelectedItem();
				
			}
		});
		
	}

		public void start(Stage stage)  throws Exception {
		// TODO Auto-generated method stub
		
		Parent root = FXMLLoader.load(getClass().getResource("/View/OptionalSelection.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Optional Selection");
		stage.setScene(scene);

		stage.show();
	}

}

