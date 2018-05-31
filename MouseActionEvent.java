package net.grifting.events;

import java.awt.event.MouseEvent;

public class MouseActionEvent extends Event
{
	private final MouseEvent args;
	private final Action action;
	
	public MouseActionEvent(MouseEvent args, Action action)
	{
		this.args = args;
		this.action = action;
	}

	public MouseEvent getArgs()
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
