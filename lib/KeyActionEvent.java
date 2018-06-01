package lib;

import java.awt.event.KeyEvent;

public class KeyActionEvent extends Event
{
	private final KeyEvent args;
	private final Action action;
	
	public KeyActionEvent(KeyEvent args, Action action)
	{
		this.args = args;
		this.action = action;
	}

	public KeyEvent getArgs()
	{
		return args;
	}
	
	public Action getAction()
	{
		return action;
	}
	
	public enum Action
	{
		PRESS,
		RELEASE,
		TYPE;
	}
}
