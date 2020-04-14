/** AUTHOR		:	[ Andrew Jackson ]
 *  COURSE		:	[ CPT 187 ]
 *  PURPOSE		:	[ To create and run a train simulator. ]
 *  STARTDATE	:	[ 03/20/2020 ] **/

package edu.cpt187.jackson.project2;

import java.util.Scanner;

public class MainClass {
	public static final String[] MENU_OPTIONS = {"Create a Metropolitan Train simulation", "Run a Metropolitan Train simulation", "Quit"};
	public static final String[] MENU_CHARS = {"[A] for", "[B] for","[Q] for"};
	public static void main(String[] args) 
	{
		//NEW Scanner OPEN
		Scanner input = new Scanner(System.in);
		// LOCAL VARIABLES
		String userName = "";
		char menuSelection = ' ';
		// INITIALIZE SUPPORT CLASS
		SubwayTrain currentTrain = new SubwayTrain();
		// WELCOME BANNER
		displayWelcomeBanner();
		// GET USER NAME
		userName = getUserName(input);
		// SIMULATION SELECTION MENU
		menuSelection = validateMenuSelection(input);
		// WHILE SELECTION IS NOT 'Q'/"QUIT"
		while(menuSelection != 'Q') 
		{
			if(menuSelection == 'A') 
			{
				currentTrain.setNumberOfStations(validateNumberOfStations(input));
				currentTrain.setHomeStation(validateHomeStation(input));
				currentTrain.setMaximumCapacity(validateMaximumCapacity(input));
				currentTrain.setMaximumStops();
				currentTrain.setStations();
			}// menuSelection == 'A'
			else {
				if(currentTrain.getMaximumCapacity() <= 0) 
				{
					displaySimulationError();
				} // currentTrain.getMaximumCapacity() <= 0
				else 
				{
					currentTrain.setLoadPassengers();
					displayLoadingAnnouncement(currentTrain.getNumberLoaded());
					displayDepartingAnnouncement(currentTrain.getCurrentStation(), currentTrain.getNumberOnBoard());
					currentTrain.setMoveStation();
					while(currentTrain.getStopCounter() < currentTrain.getMaximumStops()) 
					{
						displayArrivalAnnouncement(currentTrain.getCurrentStation(), currentTrain.getNumberOnBoard());
						currentTrain.setUnloadPassengers();
						displayUnloadingAnnouncement(currentTrain.getNumberUnloaded(), currentTrain.getNumberOnBoard());
						currentTrain.setLoadPassengers();
						displayLoadingAnnouncement(currentTrain.getNumberLoaded());
						displayDepartingAnnouncement(currentTrain.getCurrentStation(), currentTrain.getNumberOnBoard());
						currentTrain.setMoveStation();
					} // currentTrain.getStopCounter() < currentTrain.getMaxStops()
					displayArrivalAnnouncement(currentTrain.getCurrentStation(), currentTrain.getNumberOnBoard());
					currentTrain.setUnloadAllPassengers();
					displayUnloadingAnnouncement(currentTrain.getNumberUnloaded(), currentTrain.getNumberOnBoard());
					displayEndOfSimReport(currentTrain.getMaximumCapacity(), currentTrain.getNumberOfStations(), currentTrain.getStations(), currentTrain.getStopCounter());
					currentTrain.setResetSimulation();
				} //  END - else / currentTrain.getMaximumCapacity() <= 0
			} //  END - else / menuSelection == 'A'
			menuSelection = validateMenuSelection(input);
		} // menuSelection != 'Q'
		if(currentTrain.getCreateCounter() > 0) 
		{
			displayUsageReport(userName, currentTrain.getCreateCounter(), currentTrain.getRunCounter());
		}
		displayFarewellMessage();
		input.close();
	}
	////////////////////
	// DISPLAYS
	////////////////////
	public static void displayWelcomeBanner() 
	{
		// WELCOME BANNER
		System.out.println("Metropolis Subway Train Simulation");
		System.out.println("~~~~ ~~~~ ~~~~ ~~~~ ~~~~ ~~~~ ~~~~");
		System.out.println("Hello and welcome to the Metropolis Subway Train Simulation.");
		System.out.println("This program is used to simulate train rides to station,");
		System.out.println("loading and unloading passengers at each station.");
		System.out.println("To start create a new train simulation.");
	} // END - displayWelcomeBanner
	public static void displayFarewellMessage() 
	{
		// FAREWELL MESSAGE
		System.out.println("\nThanks for using the Metropolis Subway Train,");
		System.out.println("Simulation. Until next time, come again.");
	} // END - displayFarewellMessage
	public static void displaySimulationMenu() 
	{
		// SIMULATION MENU
		System.out.println("\nSIMULATION MENU");
		System.out.println("_____________________");
		System.out.printf("\n%-10s%-5s",MENU_CHARS[0],MENU_OPTIONS[0]);
		System.out.printf("\n%-10s%-5s",MENU_CHARS[1],MENU_OPTIONS[1]);
		System.out.printf("\n%-10s%-5s\n",MENU_CHARS[2],MENU_OPTIONS[2]);
	} // displaySimulationMenu
	public static void displaySimulationError() 
	{
		// WARNING / NOTICE
		System.out.println("\n      NOTICE");
		System.out.println("_____________________\n");
		System.out.println("A simulation must be created before a simulation can be run.\n");
	} // displaySimulationError
	public static void displayLoadingAnnouncement(int borrowedNumberLoaded) 
	{
		// LOADING ANNOUNCEMENT
		System.out.println("\nLoading Announcement");
		System.out.println("_____________________");
		System.out.printf("\n%-15s%-10d%s\n","Now loading:", borrowedNumberLoaded, "Passengers");
	} // END - displayEndOfSimReport
	public static void displayDepartingAnnouncement(int borrowedCurrentStation, int borrowedNumberOnBoard) 
	{
		// DEPARTING ANNOUNCEMENT
		System.out.println("\nDeparting Announcement");
		System.out.println("_____________________");
		System.out.printf("\n%-15s%-10s","Leaving", "Passengers");
		System.out.printf("\n%-15s%-10s","Station", "Onboard");
		System.out.printf("\n%-15d%-10d\n",borrowedCurrentStation, borrowedNumberOnBoard);
	} // END - displayEndOfSimReport
	public static void displayArrivalAnnouncement(int borrowedCurrentStation, int borrowedNumberOnBoard) 
	{
		// ARRIVAL ANNOUNCEMENT
		System.out.println("\nArrival Announcement");
		System.out.println("_____________________");
		System.out.printf("\n%-15s%-10s","Arriving at", "Passengers");
		System.out.printf("\n%-15s%-10s","Station", "Onboard");
		System.out.printf("\n%-15d%-10d\n", borrowedCurrentStation, borrowedNumberOnBoard);
	} // END - displayEndOfSimReport
	public static void displayUnloadingAnnouncement(int borrowedNumberUnloaded, int borrowedNumberOnBoard) 
	{
		// UNLOADING ANNOUNCEMENT
		System.out.println("\nUnloading Announcement");
		System.out.println("_____________________");
		System.out.printf("\n%-15s%-10s","   Now   ", "Passengers");
		System.out.printf("\n%-15s%-10s","Unloading", " Onboard  ");
		System.out.printf("\n%-15d%-10d\n", borrowedNumberUnloaded, borrowedNumberOnBoard);
	} // END - displayEndOfSimReport
	public static void displayEndOfSimReport(int borrowedMaximumCapacity, int borrowedNumberOfStations, String[] borrowedStations, int borrowedStopCounter) 
	{
		// SIMULATION REPORT
		System.out.println("\nEnd of Simulation Report");
		System.out.println("_____________________");
		System.out.printf("\n%-10s%-10s", "| Maximum capacity |", " Number of stations |");
		System.out.printf("\n%10d%20d", borrowedMaximumCapacity, borrowedNumberOfStations);
		System.out.printf("\n%-10s%-10s", "|  Stations names  |", "    Total stops     |");
		System.out.printf("\n%5s%6s%6s%13d", borrowedStations[0], borrowedStations[1], borrowedStations[2], borrowedStopCounter);
	} // END - displayEndOfSimReport
	public static void displayUsageReport(String borrowedUserName, int borrowedCreateCounter, int borrowedRunCounter) 
	{
		// USAGE REPORT
		System.out.println("\nUsage Report");
		System.out.println("_____________________");
		System.out.printf("\n%-10s%-5s","User name: ", borrowedUserName);
		System.out.printf("\n%-10s%-5d","Create counter", borrowedCreateCounter);
		System.out.printf("\n%-10s%-5d\n","Run counter", borrowedRunCounter);
	} // END - displayUsageReport
	//////////////////////////
	// VALUE RETURN
	//////////////////////////
	public static String getUserName(Scanner borrowedInput) 
	{
		// LOCAL VARIABLES
		String localUserName = "";
		// FIRST NAME
	    System.out.println("\nWhat is your first name?");
	    localUserName = borrowedInput.nextLine();
	    return localUserName;
	} // END - getUserName 
	////////////////////
	// VALIDATION
	////////////////////
	public static int validateNumberOfStations(Scanner borrowedInput) 
	{
		// LOCAL VARIABLES
		int localNumberOfStations = 0;
		// GET NUMBER OF STATIONS
		System.out.printf("\nWhat is the number of stations?");
		localNumberOfStations = borrowedInput.nextInt();
		while(localNumberOfStations <= 0) 
		{
			// ERROR MESSAGE
	    	System.out.print("The number of stations can not be zero.");
			// GET NUMBER OF STATIONS
			System.out.printf("\nWhat is the number of stations?");
			localNumberOfStations = borrowedInput.nextInt();
		}
		return localNumberOfStations;
	} // END - validateNumberOfStations
	public static int validateHomeStation(Scanner borrowedInput) 
	{
		// LOCAL VARIABLES
		int localHomeStation = 0;
		// GET HOME STATION
		System.out.printf("\nWhat is the home station number?");
		localHomeStation = borrowedInput.nextInt();
		while(localHomeStation <= 0) 
		{
			// ERROR MESSAGE
	    	System.out.print("The home station can not be zero.");
			// GET HOME STATION
			System.out.printf("\nWhat is the home station number?");
			localHomeStation = borrowedInput.nextInt();
		}
	    return localHomeStation;
	} // END - validateHomeStation
	public static int validateMaximumCapacity(Scanner borrowedInput) 
	{
		// LOCAL VARIABLES
		int localMaxCapacity = 0;
		// GET MAXIMUM CAPACITY
		System.out.printf("\nWhat is the maximum capacity?");
		localMaxCapacity = borrowedInput.nextInt();
		while(localMaxCapacity <= 0) 
		{
			// ERROR MESSAGE
	    	System.out.print("Maximum capacity can not be zero.");
			// GET MAXIMUM CAPACITY
			System.out.printf("\nWhat is the maximum capacity?");
			localMaxCapacity = borrowedInput.nextInt();
		}
	    return localMaxCapacity;
	} // END - validateMaximumCapacity
	public static int validateNumberWaiting(Scanner borrowedInput) 
	{
		// LOCAL VARIABLES
		int localNumberWaiting = 0;
		// GET NUMBER WAITING
		System.out.printf("\nHow many people are waiting?");
		localNumberWaiting = borrowedInput.nextInt();
		while(localNumberWaiting <= 0) 
		{
			// ERROR MESSAGE
	    	System.out.print("The number of people waiting can not be zero.");
			// GET NUMBER WAITING
			System.out.printf("\nHow many people are waiting?");
			localNumberWaiting = borrowedInput.nextInt();
		}
	    return localNumberWaiting;
	} // END - validateNumberWaiting
	public static int validateNumberLeaving(Scanner borrowedInput) 
	{
		// LOCAL VARIABLES
		int localNumberLeaving = 0;
		// GET NUMBER WAITING
		System.out.printf("\nHow many people are leaving?");
		localNumberLeaving = borrowedInput.nextInt();
		while(localNumberLeaving <= 0) 
		{
			// ERROR MESSAGE
	    	System.out.print("Number of people leaving cannot be zero.");
			// GET NUMBER WAITING
			System.out.printf("\nHow many people are leaving?");
			localNumberLeaving = borrowedInput.nextInt();
		}
	    return localNumberLeaving;
	} // END - validateNumberLeaving
	public static char validateMenuSelection(Scanner borrowedInput) 
	{
		// LOCAL VARIABLES
		char simSelection = ' ';
		// SIMULATION MENU
		displaySimulationMenu();
		// LOCAL SELECTION
		System.out.printf("\nPlease enter your selection here: ");
		simSelection = borrowedInput.next().toUpperCase().charAt(0);
	    // WHILE localSelection
	    while(simSelection != 'A' && simSelection != 'B' && simSelection != 'Q') 
	    {
	    	// ERROR MESSAGE
	    	System.out.print("\n~~Unfortunately, that is not a valid selection.~~\n");
	    	// SIMULATION MENU
			displaySimulationMenu();
			// LOCAL SELECTION
		    System.out.printf("\nPlease enter your selection here: ");
		    simSelection = borrowedInput.next().toUpperCase().charAt(0);
	    } // END - simSelection
	    return simSelection;
	} // END - validateMenuSelection
}