package controllers;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

import java.util.Observable;
import java.util.ResourceBundle;

import common.Client;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import server.ServerUI;
import server.mysqlConnection;

public class ServerConnectionController implements Initializable {

	public static ObservableList<Client> clients_list = FXCollections.observableArrayList();

	@FXML
	private Button Connect_btn;

	@FXML
	private Button disconnect_btn;

	@FXML
	private TextField ip_txt;

	@FXML
	private TextField port_txt;

	@FXML
	private TextField DBName_txt;

	@FXML
	private TextField DBUser_txt;

	@FXML
	private PasswordField Password_txt;
	@FXML
	private TableView<Client> user_tbl;

	@FXML
	private TableColumn<Client, String> ip;

	@FXML
	private TableColumn<Client, String> host;

	@FXML
	private TableColumn<Client, String> status;

	@FXML
	private Label lable_txt;
	private boolean flag = true;

	@FXML
	void connect(ActionEvent event) {
		flag = false;
		String str = "";
		mysqlConnection.db_name = DBName_txt.getText();
		mysqlConnection.db_user = DBUser_txt.getText();
		mysqlConnection.db_pass = Password_txt.getText();

		ServerUI.sv.setPort(Integer.parseInt(port_txt.getText()));
		try {
			ServerUI.sv.listen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (ServerUI.sv.isListening()) {
			lable_txt.setText("Server listening for connections on port " + port_txt.getText());
		} else
			lable_txt.setText("Not connected to Server!");

		Connect_btn.setDisable(!flag);
		DBName_txt.setDisable(true);
		DBUser_txt.setDisable(true);
		Password_txt.setDisable(true);
		disconnect_btn.setDisable(flag);
		port_txt.setDisable(true);
		clients_list.addListener(new ListChangeListener<Client>() {

			@Override
			public void onChanged(Change<? extends Client> arg0) {
				// TODO Auto-generated method stub
				System.out.println("change");

				initialize(null, null);

			}
		});

		initialize(null, null);
	}

	@FXML
	void disconnect(ActionEvent event) {
		flag = true;
		try {
			ServerUI.sv.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connect_btn.setDisable(!flag);
		DBName_txt.setDisable(false);
		DBUser_txt.setDisable(false);
		Password_txt.setDisable(false);
		disconnect_btn.setDisable(flag);
		port_txt.setDisable(false);
		lable_txt.setText("Server has stopped listening for connections.");
		clients_list.clear();
		initialize(null, null);
	}

	@FXML
	void close(ActionEvent event) {
		System.exit(0);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		InetAddress serverIp;
		try {
			serverIp = InetAddress.getLocalHost();
			ip_txt.setText(serverIp.getHostAddress());

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Connect_btn.setDisable(!flag);
		disconnect_btn.setDisable(flag);
		port_txt.setText("" + ServerUI.DEFAULT_PORT);
		DBName_txt.setText("jdbc:mysql://localhost/bitemedb?serverTimezone=IST");
		DBUser_txt.setText("root");
		Password_txt.setText("Mohamed1499*");
		ip.setCellValueFactory(new PropertyValueFactory<Client, String>("Ip"));
		host.setCellValueFactory(new PropertyValueFactory<Client, String>("Host"));
		status.setCellValueFactory(new PropertyValueFactory<Client, String>("Status"));
		user_tbl.setItems(clients_list);
	}

	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/View/ServerConnection.fxml"));

		Scene scene = new Scene(root);
		primaryStage.setTitle("Server");
		primaryStage.setScene(scene);

		primaryStage.show();
	}

}
