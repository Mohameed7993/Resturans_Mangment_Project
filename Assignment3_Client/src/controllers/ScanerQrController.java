package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ScanerQrController implements Initializable {

    @FXML
    private ImageView Image1;

    @FXML
    private Button BackButton;

    @FXML
    private ImageView Image2;

    @FXML
    private ImageView Image4;

    @FXML
    private ImageView Image3;

    @FXML
    private Button scanButton;

    @FXML
    void BackButtonAction(ActionEvent event) {

    }

    @FXML
    void scanButtonAction(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void start(Stage stage) throws Exception{
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("/View/ScanerQr.fxml"));
		
		Scene scene = new Scene(root);
		stage.setTitle("Scaner Qr Code Home");
		stage.setScene(scene);
		
		stage.show();
		
	}

}
