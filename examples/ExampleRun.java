package examples;

import lib.handler.EventHandler;

public class ExampleRun
{
	public static void main(String[] args)
	{
		new ExampleListener(); // instatiated an object that listenes for an event

		EventHandler.fireEvent(new ExampleEvent("Some Paramter: First Event Fired!")); // fired an event
		
		
		new ExampleListener(); // added a second object that is alos a listener for an event

		EventHandler.fireEvent(new ExampleEvent("Some Paramter: Second Event Fired!")); // fired a second event (now both listener will receive this event)

	}
}
