package controllers;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import common.Message1;
import common.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ManagerHomeController implements Initializable {

	@FXML
	private Button logout_btn;

	@FXML
	void changePermissions(ActionEvent event) {
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
	void viewManagerReports(ActionEvent event) {
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
	void createBusinessAccount(ActionEvent event) {
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
	void resturantRegistration(ActionEvent event) {
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
	void createPrivateAccount(ActionEvent event) {
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
	void approveEmployers(ActionEvent event) {
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
	void viewMonthlyReports(ActionEvent event) {
		// ViewMonthlyReportsController
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
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/View/ManagerHome.fxml"));

		Scene scene = new Scene(root);
		primaryStage.setTitle("ManagerHome");
		primaryStage.setScene(scene);

		primaryStage.centerOnScreen();

		primaryStage.show();
	}

}
