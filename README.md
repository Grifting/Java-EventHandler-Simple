# Java-EventHandler-Simple

This is a simple EventBus System implemented in Java.
Including the EventBus with some ExampleEvents in the lib folder
and code examples in the example folder.
Look at the ExampleRun, ExampleEvent and ExampleListener for more specific examples.

How to use:
Execute right in the constructor of your object:
EventHandler.addListener(TheEventToListenFor.class, this);
and implement the EventListener interface.
To register an object as an active event-listener.
Check in the overriden method if the event is an instance of your event.
You can fire events synchronous or asynchronous.
An asynchronic event will create a thread which will die after it servred every event-listener.
Fire events by executing:
EventHandler.fireEvent(instanceOfYourEvent);

Warning:
There is currently no limit of event handler threads.
