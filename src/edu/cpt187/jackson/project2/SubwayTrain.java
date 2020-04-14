/** AUTHOR		:	[ Andrew Jackson ]
 *  COURSE		:	[ CPT 187 ]
 *  PURPOSE		:	[ To give attributes and behaviors of a Subway Train. ]
 *  STARTDATE	:	[ 03/20/2020 ] **/

package edu.cpt187.jackson.project2;

import java.util.Random;

public class SubwayTrain {
	private final int ZERO = 0;
	private final int TWO = 2;
	private int numberOfStations = 0;
	private int maximumStops = 0;
	private int homeStation = 0;
	private String[] stations;
	private int createCounter = 0;
	private int runCounter = 0;
	private int maximumCapacity = 0;
	private int stopCounter = 0;
	private int currentStationIndex = 0;
	private int numberOnBoard = 0;
	private int numberLoaded = 0;
	private int numberUnloaded = 0;
	private Random ranNumberGenerator = new Random();
	
	public void SubwayTrian() {}
	//////////////////////////
	// SETTERS
	//////////////////////////
	public void setNumberOfStations(int borrowedNoOfStations) 
	{
		numberOfStations = borrowedNoOfStations;
		
	} // setNumberOfStations
	public void setMaximumStops() 
	{
		maximumStops  = (numberOfStations * TWO) - TWO;
	} // setMaximumStops
	public void setHomeStation(int borrowedHomeStation) 
	{
		homeStation = borrowedHomeStation;
	} // setHomeStation
	public void setStations() 
	{
		int localIndex = 0;
		stations = new String[numberOfStations];
		while(localIndex < stations.length) 
		{
			stations[localIndex] = String.valueOf(homeStation + localIndex);
			localIndex++;
		}
	} // setStations
	public void setMaximumCapacity(int borrowedMaxCapacity) 
	{
		maximumCapacity = borrowedMaxCapacity;
	} // setMaximumCapacity
	public void setMoveStation()
	{
		stopCounter++;
		if(stopCounter < numberOfStations) 
		{
			currentStationIndex = stopCounter; 
		}
		else 
		{
			currentStationIndex = currentStationIndex - 1;
		}
	} // setMoveStations
	public void setLoadPassengers()
	{
		int localPassengersWaiting = getRandomNumber();
		if(localPassengersWaiting > maximumCapacity - numberOnBoard) 
		{
			numberLoaded = maximumCapacity - numberOnBoard;
		}
		else 
		{
			numberLoaded = localPassengersWaiting;
		}
		numberOnBoard = numberOnBoard + numberLoaded;
	} // setLoadPassengers
	public void setUnloadPassengers() 
	{
		int localPassengersWaiting = getRandomNumber();
		if(localPassengersWaiting > numberOnBoard) 
		{
			numberUnloaded = numberOnBoard;
		}
		else 
		{
			numberUnloaded = localPassengersWaiting;
		}
		numberOnBoard = numberOnBoard - numberUnloaded;
	} // setUnloadPassengers
	public void setUnloadAllPassengers() 
	{
		numberUnloaded = numberUnloaded - numberOnBoard;
	} // setUnloadAllPassengers
	public void setResetSimulation() 
	{
		stopCounter = ZERO;
		currentStationIndex = ZERO;
		runCounter++;
		
	} // setResetSimulation
	//////////////////////////
	// GETTERS
	//////////////////////////
	public int getRandomNumber() 
	{
		return ranNumberGenerator.nextInt(maximumCapacity * TWO);
	} // getRandomNumber
	public int getNumberOfStations() 
	{
		return numberOfStations;
	} // getNumberOfStations
	public int getMaximumStops() 
	{
		return maximumStops;
	} // getMaximumStops
	public int getHomeStation() 
	{
		return homeStation;
	} // getHomeStation
	public String[] getStations() 
	{
		return stations;
	} // getStations
	public int getCreateCounter() 
	{
		return createCounter;
	} // getCreateCounter
	public int getRunCounter() 
	{
		return runCounter;
	} // getRunCounter
	public int getMaximumCapacity() 
	{
		return maximumCapacity;
	} // getMaximumCapacity
	public int getStopCounter() 
	{
		return stopCounter;
	} // getStopCounter
	public int getCurrentStation() 
	{
		return Integer.parseInt(stations[currentStationIndex]);
	} // getCurrentStation
	public int getNumberOnBoard() 
	{
		return numberOnBoard;
	} // getNumberOnBoard
	public int getNumberLoaded() 
	{
		return numberLoaded;
	} // getNumberLoaded
	public int getNumberUnloaded() 
	{
		return numberUnloaded;
	} // getNumberUnloaded
}