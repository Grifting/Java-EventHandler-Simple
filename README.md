# Java-EventHandler-Simple

This is a simple EventBus System implemented in Java

How to use:

Execute right in the constructor of you object:

EventHandler.addListener({THE EVENT CLASS}, this);

and implement the EventListener.
To register an object as an active event-listener.


Check in the overriden method if the event is an instance of your event.

You can fire events synchronic or asynchronic.
An asynchronic event will create a thread which will die after it servered every event-listener.

Fire events by executing:

EventHandler.fireEvent(YOUREVENT);

Warning:
There is currently no limit of event handler threads.
