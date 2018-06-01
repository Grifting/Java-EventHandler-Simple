package examples;

import lib.Event;
import lib.handler.EventHandler;
import lib.handler.EventListener;

public class ExampleListener implements EventListener
{
	public ExampleListener()
	{
		EventHandler.addListener(ExampleEvent.class, this);
	}

	public void onUpdate(Event e)
	{
		if (e instanceof ExampleEvent)
		{
			exampleEventFired(((ExampleEvent) e));

			return;
		}
	}

	private void exampleEventFired(ExampleEvent e)
	{
		System.out.println("Example event fired! Event: " + e.getExampleParamter());
	
		// EventHandler.removeListener(this); // try removeListener to remove an active EventListener
	}
}
