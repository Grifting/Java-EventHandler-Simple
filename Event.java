package net.grifting.events;

public abstract class Event
{
	private Boolean executed = false;
	
	public void executed()
	{
		executed = true;
	}
	
	public Boolean isExecuted()
	{
		return executed;
	}
	
}
