package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import common.Message1;
import common.MessageType;
import common.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ResturanRegistrationController implements Initializable {

	@FXML
	private Button register_btn;

	@FXML
	private Text firstname1_txt;

	@FXML
	private Text lastname1_txt;

	@FXML
	private Text phoneNumber1_txt;

	@FXML
	private Text email1_txt;

	@FXML
	private Text resturantName_txt;

	@FXML
	private TextField IDText;

	@FXML
	private TextField resturantName_txtfield;

	@FXML
	private Text firstname2_txt;

	@FXML
	private Text lastname2_txt;

	@FXML
	private Text email2_txt;

	@FXML
	private Text phoneNumber2_txt;

	@FXML
	private Text phoneNumber_txt;

	@FXML
	private Text location_txt;

	@FXML
	private TextField location_txtfield;
	@FXML
	private TextField phoneNumber_txtfield;
	private Users u;

	@FXML
	void getData(ActionEvent event) {
		if (IDText.getText().equals("")) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("please insert id");
			a.setHeaderText("Error");
			a.showAndWait();
		} else {
			ClientUI.chat.accept(new Message1(MessageType.getDataForUser, IDText.getText()));

			if (!(boolean) ChatClient.temp.get(0)) {
				Alert a = new Alert(AlertType.ERROR);
				String s = (String) ChatClient.temp.get(1);
				a.setContentText(s);
				a.setHeaderText("Error");
				a.showAndWait();
			} else {
				System.out.println(1);
				u = (Users) ChatClient.temp.get(1);
				firstname2_txt.setText(u.getFirstName());
				lastname2_txt.setText(u.getLastName());
				email2_txt.setText(u.getEmail());
				phoneNumber2_txt.setText(u.getPhoneNumber());
				setview(true);
				IDText.setDisable(true);
				System.out.println(2);
			}
		}
	}

	   @FXML
	    void goToApproveEmployers(ActionEvent event) {
	    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
			ApproveEmployersController approveEmployersController = new ApproveEmployersController();
			try {
				approveEmployersController.start(stage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @FXML
	    void goToChangePermission(ActionEvent event) {
	    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
			ChangePermissionController changePermissionController = new ChangePermissionController();
			try {
				changePermissionController.start(stage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @FXML
	    void goToCreateBusinessAccount(ActionEvent event) {
	    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
			CreateBusinessAccountController accountController = new CreateBusinessAccountController();
			try {
				accountController.start(stage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @FXML
	    void goToCreatePrivateAccount(ActionEvent event) {
	    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
			CreatePrivateAccountController accountController = new CreatePrivateAccountController();
			try {
				accountController.start(stage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @FXML
	    void goToHome(ActionEvent event) {
	    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
	    	ManagerHomeController managerHomeController=new ManagerHomeController();
	    	try {
				managerHomeController.start(stage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @FXML
	    void goToResturantRegistration(ActionEvent event) {
	    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
			ResturanRegistrationController resturanRegistrationController = new ResturanRegistrationController();
			try {
				resturanRegistrationController.start(stage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }

	    @FXML
	    void goToUploadReports(ActionEvent event) {
	    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
			UploadQuarterlyReportController uploadQuarterlyReportController = new UploadQuarterlyReportController();
			try {
				uploadQuarterlyReportController.start(stage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @FXML
		void logoutAction(ActionEvent event) {
			Alert alert = new Alert(AlertType.WARNING, "Are you sure you want to logout?", ButtonType.YES, ButtonType.NO);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.YES) {
				// ... user chose YES
				ClientUI.chat.accept(new Message1(MessageType.logout, ChatClient.userlogged));
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				BiteMeLoginController biteMeLoginController = new BiteMeLoginController();
				try {
					biteMeLoginController.start(stage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	    @FXML
	    void goToViewMonthlyReports(ActionEvent event) {

	    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
			ViewMonthlyReportsController viewMonthlyReportsController = new ViewMonthlyReportsController();
			try {
				viewMonthlyReportsController.start(stage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	@FXML
	void register(ActionEvent event) {

		if (resturantName_txtfield.getText().equals("") || phoneNumber_txtfield.getText().equals("")
				|| location_txtfield.getText().equals("")) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("some fields are missing");
			a.setHeaderText("Error");
			a.showAndWait();
		} else {
			ArrayList<Object> arr = new ArrayList<Object>();
			arr.add(u);
			arr.add(resturantName_txtfield.getText());
			arr.add(phoneNumber_txtfield.getText());
			arr.add(location_txtfield.getText());
			arr.add(ChatClient.userlogged.getId());
			ClientUI.chat.accept(new Message1(MessageType.resturantRegistration, arr));
			Alert a = new Alert(AlertType.INFORMATION);
			a.setContentText("the resturant was registered successfully");
			a.setHeaderText("info");
			a.showAndWait();
			initialize(null, null);
		}
	}

	@FXML
	void returnToPage(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		ManagerHomeController managerHomeController = new ManagerHomeController();
		try {
			managerHomeController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setview(boolean flag) {
		phoneNumber_txt.setVisible(flag);
		phoneNumber_txtfield.setVisible(flag);
		resturantName_txt.setVisible(flag);
		resturantName_txtfield.setVisible(flag);
		firstname1_txt.setVisible(flag);
		firstname2_txt.setVisible(flag);
		lastname1_txt.setVisible(flag);
		lastname2_txt.setVisible(flag);
		email1_txt.setVisible(flag);
		email2_txt.setVisible(flag);
		phoneNumber1_txt.setVisible(flag);
		phoneNumber2_txt.setVisible(flag);
		location_txt.setVisible(flag);
		location_txtfield.setVisible(flag);
		register_btn.setVisible(flag);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setview(false);

		IDText.setDisable(false);
		IDText.setText("");
		resturantName_txtfield.setText("");
		phoneNumber_txtfield.setText("");
		location_txtfield.setText("");
		IDText.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.equals("")) {
				newValue = newValue.replaceAll(" ", "");
				IDText.setText(newValue);
			}
			if (!newValue.matches("\\s0-9*")) {
				IDText.setText(newValue.replaceAll("[^\\s0-9]", ""));
			}
		});
		phoneNumber_txtfield.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.equals("")) {
				newValue = newValue.replaceAll(" ", "");
				phoneNumber_txtfield.setText(newValue);
			}
			if (!newValue.matches("\\s0-9*")) {
				phoneNumber_txtfield.setText(newValue.replaceAll("[^\\s0-9]", ""));
			}
		});

		resturantName_txtfield.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.equals("")) {
				newValue = newValue.replaceAll(" ", "");
				resturantName_txtfield.setText(newValue);
			}
			if (!newValue.matches("\\sa-zA-Z*")) {
				resturantName_txtfield.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
			}
		});
		location_txtfield.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.equals("")) {
				newValue = newValue.replaceAll(" ", "");
				location_txtfield.setText(newValue);
			}
			if (!newValue.matches("\\sa-zA-Z*")) {
				location_txtfield.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
			}
		});
	}

	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/View/ResturanRegistration.fxml"));

		Scene scene = new Scene(root);
		primaryStage.setTitle("ManagerHome");
		primaryStage.setScene(scene);

		primaryStage.centerOnScreen();

		primaryStage.show();
	}
}
