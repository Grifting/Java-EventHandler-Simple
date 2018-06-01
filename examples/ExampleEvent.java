package examples;

import lib.Event;

public class ExampleEvent extends Event
{
	private final String exampleParameter;
	
	public ExampleEvent(String exampleParameter) // the paramters can be anything
	{
		this.exampleParameter = exampleParameter;
	}

	public String getExampleParamter()
	{
		return exampleParameter;
	}
}
