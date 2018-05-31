package net.grifting.events.handler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import net.grifting.events.DebugEvent;
import net.grifting.events.Event;

public class EventHandler
{
	private static Map<EventListener, Set<Class<? extends Event>>> listenerSets = new HashMap<>();

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

	public static synchronized Boolean hasListener(EventListener listener)
	{
		return (listenerSets.containsKey(listener)) ? true : false;
	}

	public static synchronized void removeListener(EventListener listener)
	{
		if (hasListener(listener))
			listenerSets.remove(listener);
	}

	// private static List<EventThread> eventThreads = new ArrayList<>();
	
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
