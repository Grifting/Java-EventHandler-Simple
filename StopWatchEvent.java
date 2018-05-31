package net.grifting.events;

public class StopWatchEvent extends Event
{
	private final long time;
	
	public StopWatchEvent(long time)
	{
		this.time = time;
	}

	public Long getTime()
	{
		return time;
	}
}
