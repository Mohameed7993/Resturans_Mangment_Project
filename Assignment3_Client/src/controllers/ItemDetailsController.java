package controllers;

import java.net.URL;
import java.util.ResourceBundle;

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

public class ItemDetailsController implements Initializable {

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
    private Text totalpricetxt;

    @FXML
    private Text tybemealfield;

    @FXML
    private Text dishfield;

    @FXML
    private Text totalpricefield;


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
    void addButtonAction(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		resturantnametxt.setText(ChooseResturantController.resturant.getResturantName());
		tybemealfield.setText(TybeMealController.tybe_meal.getTybeMeal());
		dishfield.setText(DishController.dish.getDish()+", Price:"+DishController.dish.getDishPrice());
		if(OptionalSelectionController.sel.size()==0)
			extrasField.setText("No Extras");
		else extrasField.setText(OptionalSelectionController.sel.toString());
		String TotalPrice=String.valueOf(OptionalSelectionController.totalPrice);  
		totalpricefield.setText(TotalPrice);
		
		
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
