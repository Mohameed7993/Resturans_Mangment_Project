package client;
import controllers.BiteMeLoginController;
import controllers.ClientConnectionController;
import javafx.application.Application;
import javafx.stage.Stage;


/**Basel
 * 
 * @author win10
 *
 */
public class ClientUI extends Application {
	public static ClientController chat; //only one instance

	public static void main( String args[] ) throws Exception
	   { 
		//abosale7
		    launch(args);  
	   } // end main 
	
	
	 
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// chat= new ClientController("asdsad", 5555);
		// TODO Auto-generated method stub
						  		
		ClientConnectionController aFrame=new ClientConnectionController();
		aFrame.start(primaryStage);
	}
	
	
}
