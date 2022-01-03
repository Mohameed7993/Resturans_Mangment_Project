package controllers;

import java.awt.event.TextEvent;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.event.ChangeEvent;

import org.w3c.dom.events.MouseEvent;

import client.ChatClient;
import client.ClientController;
import client.ClientUI;
import common.Client;
import common.DishForResturant;
import common.Message1;
import common.MessageType;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;

public class UpdateMenuListForResturantController implements Initializable {

	public static ObservableList<DishForResturant> dishes;

	public static DishForResturant selectedDish;
	@FXML
	private TableView<DishForResturant> dish_table;

	@FXML
	private TableColumn<DishForResturant, String> dish_type_col;

	@FXML
	private TableColumn<DishForResturant, String> dish_col;

	@FXML
	private TableColumn<DishForResturant, String> optional_ingredients_col;

	@FXML
	private TableColumn<DishForResturant, Integer> price_col;

	@FXML
	private ImageView info;

	@FXML
	private Text info_txt;

	@FXML
	private TextField keyword;
	@FXML
	private Button add;
	
	@FXML
    private Button delbtn;

	@FXML
	void addItem(ActionEvent event) {
		
		//Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		Stage stage=new Stage();
		AddItemController aaa = new AddItemController();
		try {
			aaa.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

	@FXML
	void back(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		ResturantHomeController resturantHomeController = new ResturantHomeController();
		try {
			resturantHomeController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void deleteItem(ActionEvent event) {

		if (dish_table.getSelectionModel().getSelectedItem() == null) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Select what do you want to delete");
			a.setHeaderText("Error");
			a.showAndWait();
		} else {
			System.out.println(dish_table.getSelectionModel().getSelectedItem());
			ClientUI.chat.accept(new Message1(MessageType.deleteDish, dish_table.getSelectionModel().getSelectedItem()));
			initialize(null, null);
		}
	}

	@FXML
	void editItem(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		if (dish_table.getSelectionModel().getSelectedItem() != null) {
			selectedDish = dish_table.getSelectionModel().getSelectedItem();
			EditOptionalIngredientsController editOptionalIngredientsController = new EditOptionalIngredientsController();
			try {
				editOptionalIngredientsController.start(stage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Select what do you want to edit");
			a.setHeaderText("Error");
			a.showAndWait();
		}
	}

	
	@FXML
    void LogOut(ActionEvent event) {
    	Alert alert = new Alert(AlertType.WARNING, 
                "Are you sure you want to logout?", 
                ButtonType.YES, ButtonType.NO);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES){
		// ... user chose YES
			ClientUI.chat.accept(new Message1(MessageType.logout, ChatClient.userlogged));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			BiteMeLoginController biteMeLoginController=new BiteMeLoginController();
			try {
				biteMeLoginController.start(stage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
    }
	
	
	
	   @FXML
	    void menuSettings(ActionEvent event) {
	    	Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
	    	UpdateMenuListForResturantController updateMenuListForResturantController= new UpdateMenuListForResturantController();
	    	try {
				updateMenuListForResturantController.start(stage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @FXML
	    void viewOrderList(ActionEvent event) {
	    	Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
	    	ViewOrdersController ViewOrders =new ViewOrdersController();
	    	try {
	    		System.out.println("try to run view interface");
	    		ViewOrders.start(stage);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	    	

	    }
	    
	    
	    @FXML
	    void Home(ActionEvent event) {
	    	Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
	    	ResturantHomeController Home= new ResturantHomeController();
	    	try {
				Home.start(stage);
			} catch (Exception e) {
				// TODO: handle exception
			}
	    }
	    
	    
	    @FXML
	    void WaitingOrders(ActionEvent event) {
	    	Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
	    	WaitingOrdersController waitingOrders = new WaitingOrdersController();
	    	
	    	try {
				waitingOrders.start(stage);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	    	

	    }
	    
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		ClientUI.chat.accept(new Message1(MessageType.getDishesFromResturant, ChatClient.resturant.getId()));
		
		dish_type_col.setCellValueFactory(new PropertyValueFactory<DishForResturant, String>("mealType"));
		dish_col.setCellValueFactory(new PropertyValueFactory<DishForResturant, String>("mealName"));

	
		price_col.setCellValueFactory(new PropertyValueFactory<DishForResturant, Integer>("price"));
		
		optional_ingredients_col.setCellValueFactory(new PropertyValueFactory<DishForResturant, String>("optionalIngredients"));

		dishes = FXCollections.observableArrayList(ChatClient.dishes);
		dish_table.setItems(dishes);

		///////////////////////////////////////////////////////////////////////// search
		///////////////////////////////////////////////////////////////////////// bar
		///////////////////////////////////////////////////////////////////////// activation
		// initial filtered list
		FilteredList<DishForResturant> FilteredData = new FilteredList<>(dishes, b -> true);

		keyword.textProperty().addListener((observable, oldvalue, newvalue) -> {
			FilteredData.setPredicate(Dish -> {
				if (newvalue.isEmpty() || newvalue == null) {
					return true;
				}

				String searchKeyword = newvalue.toLowerCase();
				if (Dish.getMealType().toLowerCase().indexOf(searchKeyword) > -1) {// >-1 a match has been found
					return true;
				} else if (Dish.getMealName().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				}else if(Dish.getOptionalIngredients().toLowerCase().indexOf(searchKeyword)>-1)
				{
					return true;
				}
				return false;// no match found

			});
		});
		
           delbtn.setDisable(true);
		dish_table.setOnMouseClicked(mouseEvent -> {
			 if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
				 if(dish_table.getSelectionModel().getSelectedItem()!=null) {
					 delbtn.setDisable(false);
					}
			 }
		});
		
		
		//SortedList<Dish> sortedData = new SortedList<>(FilteredData);    //in case we want to sort the searched data

		// bind sorted result with table view
	//	sortedData.comparatorProperty().bind(dish_table.comparatorProperty());

		// apply filtered and sorted data to the table view
		dish_table.setItems(FilteredData);
                           		

	}

	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/View/UpdateMenuForResturant.fxml"));

		Scene scene = new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.show();

	}

}