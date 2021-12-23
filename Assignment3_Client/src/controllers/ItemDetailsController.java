package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import common.ItemInCart;
import common.ItemList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ItemDetailsController implements Initializable {
	
public static ArrayList<ItemInCart> itemList =new ArrayList<>();

public static ArrayList<ItemList> Items=new ArrayList<>();

public static ItemList AddItem;

public static ItemInCart AdditemList;

public  static Integer TotalPrice;

public static Integer Quantity;

public static String orderPackageNumber;


public String replaceAll(String regex, String replacement) {  
    return Pattern.compile(regex).matcher((CharSequence) this).replaceAll(replacement);  
}

    @FXML
    private ImageView image;

    @FXML
    private Button BackButton;

    @FXML
    private Button addButton;

    @FXML
    private Text resturantnametxt;

    @FXML
    private Text ItemDetailstxt;

    @FXML
    private Text tybemealtxt;

    @FXML
    private Text dishtxt;

    @FXML
    private Text extrastxt;

    @FXML
    private Text tybemealfield;
    
    @FXML
    private Text Quantitytxt;

    @FXML
    private TextField QuantityField;

    @FXML
    private Text dishfield;

    @FXML
    private Text extrasField;

    @FXML
    void BackButtonAction(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
    	OptionalSelectionController AFrame=new OptionalSelectionController();
		try {
			AFrame.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch blocks
			e.printStackTrace();
		}
    }

    @FXML
    void addButtonAction(ActionEvent event) {// add to cart
    	/////////////////////////////////////
    	orderPackageNumber="12586";
    	//////////////////////////////////////
    	OptionalSelectionController.sel.remove("");
    	if(QuantityField.getText().matches("[a-zA-Z_]+")||QuantityField.getText().equals("")) {
    		Alert a = new Alert(AlertType.ERROR);
            a.setContentText("Error");
            a.setHeaderText("Quantity is Wrong!");
            a.showAndWait();
    	}
    	else {
    		Quantity=Integer.parseInt(QuantityField.getText());
    	MyCartController.numberitem++;
    	TotalPrice=OptionalSelectionController.totalPrice*Quantity;
 if(OptionalSelectionController.sel.size()!=0) {
	 
    	AdditemList =new ItemInCart (MyCartController.numberitem,TybeMealController.tybe_meal.getTypeMeal(),DishController.dish.getDish()
    			,OptionalSelectionController.sel.toString(),TotalPrice,Quantity,"121314");

    	
    	AddItem=new ItemList( TybeMealController.tybe_meal.getTypeMeal(), DishController.dish.getDish(),OptionalSelectionController.sel.toString().replaceAll(" ","")
    			,ItemDetailsController.Quantity, ItemDetailsController.TotalPrice,orderPackageNumber,"11");
    	
 }
 
 else {  AdditemList =new ItemInCart (MyCartController.numberitem,TybeMealController.tybe_meal.getTypeMeal(),DishController.dish.getDish()
 			,"No Extra",TotalPrice,Quantity,"121314");
    AddItem=new ItemList( TybeMealController.tybe_meal.getTypeMeal(), DishController.dish.getDish(),"NoExtra"
			,ItemDetailsController.Quantity, ItemDetailsController.TotalPrice,orderPackageNumber,"11");
 }

 itemList.add(AdditemList);
 Items.add(AddItem);
 
 TotalPrice=0;
 for(int i=0;i<itemList.size();i++)
 {
	 TotalPrice+=itemList.get(i).getTotalPrice();
 }
    	
    	OptionalSelectionController.totalPrice=0;
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
    	MyCartController AFrame=new MyCartController();
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
		resturantnametxt.setText(ChooseResturantController.resturant.getResturantName());
		
		tybemealfield.setText(TybeMealController.tybe_meal.getTypeMeal());
		
		dishfield.setText(DishController.dish.getDish()+", Price:"+DishController.dish.getDishPrice());
		
		if(OptionalSelectionController.sel.size()==0)
			extrasField.setText("No Extras");
		
		else extrasField.setText(OptionalSelectionController.sel.toString());
		
	}

		public void start(Stage stage)  throws Exception {
		// TODO Auto-generated method stub
		
		Parent root = FXMLLoader.load(getClass().getResource("/View/ItemDetails.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Item Details");
		stage.setScene(scene);

		stage.show();
	}

}
