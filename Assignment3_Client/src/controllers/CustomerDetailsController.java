package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import client.ChatClient;
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

public class CustomerDetailsController implements Initializable {

    @FXML
    private ImageView Image;

    @FXML
    private Button Exitbutton;

    @FXML
    private Button ConfirmButton;

    @FXML
    private Text IDtxt;

    @FXML
    private Text FirstNametxt;

    @FXML
    private Text LastNametxt;

    @FXML
    private Text PhoneNumbertxt;

    @FXML
    private Text Emailtxt;

    @FXML
    private Text Credittxt;

    @FXML
    private Text AccountTybetxt;

    @FXML
    private Text FirstNameField;

    @FXML
    private Text LastNameField;

    @FXML
    private Text IDField;

    @FXML
    private Text PhoneNumberField;

    @FXML
    private Text EmailField;

    @FXML
    private Text AccountField;

    @FXML
    private Text CreditField;

    @FXML
    private Text YourDetailstxt;

    @FXML
    void ConfirmButtonAction(ActionEvent event) {
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
    void ExitbuttonAction(ActionEvent event) {
    	
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
    	ScanerQrController AFrame=new ScanerQrController();
		try {
			AFrame.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		YourDetailstxt.setText(ChatClient.u.getUserName()+" Details");
		FirstNameField.setText(ChatClient.account.getFirstName());
		LastNameField.setText(ChatClient.account.getLasName());
		IDField.setText(ChatClient.account.getID());
		PhoneNumberField.setText(ChatClient.account.getPhoneNumber());
		EmailField.setText(ChatClient.account.getEmail());
		CreditField.setText(ChatClient.account.getCreditCardNumber());
		AccountField.setText(ChatClient.account.getAccoountTybe());
		
	}
	
	public void start(Stage stage) throws Exception{
		// TODO Auto-generated method stub
		Parent root= FXMLLoader.load(getClass().getResource("/View/CustomerDetails.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Scaner Qr Code Home");
		stage.setScene(scene);
		
		stage.show();
		
	}

}
