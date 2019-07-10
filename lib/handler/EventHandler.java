package lib.handler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import lib.DebugEvent;
import lib.Event;

public class EventHandler
{
	/*
	 * stores the EventListener and which event an object listens to
	 */
	private static Map<EventListener, Set<Class<? extends Event>>> listenerSets = new HashMap<>();
	
	/*
	 * fires an synchron event
	 * 
	 * @param e an instatiated event object
	 */
	public static void fireEvent(Event e)
	{
		synchronized (listenerSets)
		{
			Map<EventListener, Set<Class<? extends Event>>> linkedList = new LinkedHashMap<>(listenerSets);
			Iterator<EventListener> it = linkedList.keySet().iterator();
			while (it.hasNext())
			{
				EventListener listener = it.next();
				if (linkedList.get(listener).contains(e.getClass()))
					listener.onUpdate(e); // replace with annotation seeking in next built
			}
		}
	}
	
	/*
	 * to subscribe to an specific event
	 * 
	 * @param eventToNotify a class which is an event
	 * @param listener an object which is an eventListener
	 */
	public static synchronized void addListener(Class<? extends Event> eventToNotify, EventListener listener)
	{
		if (hasListener(listener))
		{
			listenerSets.get(listener).add(eventToNotify);
			EventHandler.fireEvent(new DebugEvent("Event subscriber register " + listener.getClass().getName()
					+ " to event " + eventToNotify.getSimpleName() + " subscribed to "
					+ (listenerSets.get(listener).size() - 1) + " more event(s)"));
			return;
		}

		Set<Class<? extends Event>> eventsToNotify = new HashSet<>();
		eventsToNotify.add(eventToNotify);
		listenerSets.put(listener, eventsToNotify);

		EventHandler.fireEvent(new DebugEvent(EventHandler.class,
				"Event subscriber register " + listener.getClass().getName() + " to event "
						+ eventToNotify.getSimpleName() + " subscribed to " + (listenerSets.get(listener).size() - 1)
						+ " more event(s)"));
	}

	/*
	 * a method which checks if an instatiated object listenes for events
	 * 
	 * @param listener an instatiated eventListener
	 * @return bool true when it subscribed to events false when not
	 */
	public static synchronized Boolean hasListener(EventListener listener)
	{
		return (listenerSets.containsKey(listener)) ? true : false;
	}
	
	// ToDo: check if an listener listenes for an specific event

	/*
	 * removes an active eventlistener completely from the events
	 * 
	 * @param  listener an active eventListener object
	 * 
	 */
	public static synchronized void removeListener(EventListener listener)
	{
		if (hasListener(listener))
			listenerSets.remove(listener);
	}
	
	// ToDo: unsubscribe from specific events

	// private static List<EventThread> eventThreads = new ArrayList<>();
	
	/*
	 * fires an asynchronous event, by creating an event handler thread
	 * 
	 * @param e an instatiated event object
	 */
	public static synchronized void fireAsyncEvent(Event e)
	{
		EventThread et = new EventThread(e);
		// eventThreads.add(et);
		et.start();
	}
	
	static class EventThread extends Thread
	{
		private Event e;

		public EventThread(Event e)
		{
			super("EventThread");

			this.e = e;
		}

		@Override
		public void run()
		{
			fireEvent(e);
		}
	}

}
