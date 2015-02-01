package spaceSim;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application
{
	public static void main(String[] args)
	{
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		// Create an instance (object) of the Controller class
		Controller rocket = new Controller();
		
		// Create an instance (object) of the View class
		// and pass it the instance of the Controller class
		View view = new View(primaryStage, rocket);
	}
}
