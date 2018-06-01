package lib;

import java.awt.Color;

public class DebugEvent extends Event
{
	// private final Class<?> origin;
	private final String className;
	private final String report;
	private final State result;
	
	public DebugEvent(Class<?> sender, String report, State result)
	{
		this.className = sender.getName();
		this.report = report;
		this.result = result;
	}
	
	public DebugEvent(Class<?> sender, String report)
	{	
		this.className = sender.getName();
		this.report = report;
		this.result = State.INFO;
	}
	
	public DebugEvent(String report)
	{	
		this.className = "unknown";
		this.report = report;
		this.result = State.INFO;
	}
	
	public String getSender()
	{
		return className;
	}
	
	public String getReport()
	{
		return report;
	}
	
	public State getResult()
	{
		return result;
	}
	
	public enum State 
	{
		INFO 		(new Color(0,0,0), new Color(255,255,255)), // for useful information
		REFLECTION 	(new Color(55,55,255), new Color(255,255,255)), // for optimasition purposes (like execution of method x took x ns)
		INCOMPLETE 	(new Color(55,55,255), new Color(255,255,255)), // if a task started and is incomplete
		SUCCESSFUL 	(new Color(25,215,25), new Color(255,255,255)),
		WARNING 		(new Color(245,215,0), new Color(255,255,255)),
		DENIED 		(new Color(215,25,25), new Color(255,255,255)),
		FAILED 		(new Color(215,25,25), new Color(215,25,25));
		
		public final Color foregroundColor;
		public final Color backgroundColor;
		
		private State(Color fc, Color bc)
		{
			foregroundColor = fc;
			backgroundColor = bc;
		}
	}
	
}
