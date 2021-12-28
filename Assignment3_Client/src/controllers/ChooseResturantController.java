package controllers;
import java.net.URL;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import common.ItemInCart;
import common.Message;
import common.MessageType;
import common.Resturants;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ChooseResturantController implements Initializable {

	public static Stage stage1;
    
	ObservableList<Resturants> resturants;

	public static Resturants resturant;

	@FXML
	public  TableView<Resturants> TablelistID;

	@FXML
	private TableColumn<Resturants, String> ResturanNameCol;

	@FXML
	private TableColumn<Resturants, String> StatusCol;

	@FXML
	private TableColumn<Resturants, String> PhoneNumberCol;

	@FXML
	private ImageView Image1;

	@FXML
	private Button BackButton;

	@FXML
	private ImageView Image2;

	@FXML
	private ImageView Image3;

	@FXML
	private ImageView Image4;

	@FXML
	private ImageView Image5;

	@FXML
	private Button ViewMenuButton;
	
    @FXML
    private Text ResturantFrom;
    
	@FXML
	private Button MyCartButton;

	@FXML
	void BackButtonAction(ActionEvent event) {

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		CustomerDetailsController AFrame=new CustomerDetailsController();
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
	void ViewMenuButtonAction(ActionEvent event) {
		MyCartController.Flag=true;
		if(TablelistID.getSelectionModel().getSelectedItem()!=null) {
			if(TablelistID.getSelectionModel().getSelectedItem().getStatus().equals("Open")) 
			{
				if(ItemDetailsController.itemList.size()!=0) {
					if(!(MyCartController.yourOrderIsInResturant.equals(resturant.getResturantName()))) {
						Alert a = new Alert(AlertType.ERROR);
						a.setContentText("Error");
						a.setHeaderText("you Cant Orderd from Deffirent Returant!"+"you most finish your order from resturant:"
								+MyCartController.yourOrderIsInResturant);
						a.showAndWait();
					} 
					else {
						Stage stage =new Stage();
						TybeMealController AFrame=new TybeMealController();
						try {
							AFrame.start(stage);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();                    	 
						}
					}
				}
				else {
					MyCartController.yourOrderIsInResturant=TablelistID.getSelectionModel().getSelectedItem().getResturantName();
					//Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
					Stage stage=new Stage();
					TybeMealController AFrame=new TybeMealController();
					try {
						AFrame.start(stage);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else {
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("Error");
				a.setHeaderText("This resturant is Close, Please choose one Open");
				a.showAndWait();
			}
		}
		else {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Error");
			a.setHeaderText("should you Select a resturant:");
			a.showAndWait();
		}
	}



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(ItemDetailsController.itemList.size()==0) {
			MyCartButton.setVisible(false);
			Image5.setVisible(false);
		}
		else { MyCartButton.setVisible(true); Image5.setVisible(true);}
		ResturantFrom.setText("From :" + ChatClient.accounts.getLocation() );
		ResturanNameCol.setCellValueFactory(new PropertyValueFactory<Resturants,String>("ResturantName"));
		StatusCol.setCellValueFactory(new PropertyValueFactory<Resturants,String>("Status"));
		PhoneNumberCol.setCellValueFactory(new PropertyValueFactory<Resturants,String>("PhoneNumber"));

		ClientUI.chat.accept(new Message(MessageType.ViewResturants,ChatClient.accounts.getLocation()));
		resturants=FXCollections.observableArrayList(ChatClient.resturants);
		TablelistID.setItems(resturants);

		TablelistID.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				resturant=TablelistID.getSelectionModel().getSelectedItem();

			}

		});

	}

	public void start(Stage primaryStage) throws Exception {
		stage1=primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("/View/ChooseResturant.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Resturants List");

		primaryStage.setScene(scene);

		primaryStage.show();
	}

}