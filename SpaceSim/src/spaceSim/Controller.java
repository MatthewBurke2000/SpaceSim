package spaceSim;

import java.util.LinkedList;

/*
 * 
 * Controller
 * 
 */

public class Controller
{
	/*
	 * Class Instance Variables
	 * 
	 */
	
	private SQLDatabase database;
	
	/*
	 * Class Constants
	 * 
	 */
	
	
	
	/*
	 * Constructor Method
	 * 
	 */
	
	public Controller()
	{
		database = new SQLDatabase();
	}
	
	/*
	 * Database Methods
	 * 
	 */
	
	public LinkedList<NoseCone> getAllNoseCones() {
		
		LinkedList<NoseCone> noseCones = new LinkedList<NoseCone>();
		
		
		
		return noseCones;
	}
}
