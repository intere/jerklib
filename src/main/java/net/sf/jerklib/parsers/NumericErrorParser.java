package net.sf.jerklib.parsers;

import net.sf.jerklib.events.IRCEvent;
import net.sf.jerklib.events.NumericErrorEvent;


public class NumericErrorParser implements CommandParser
{
	public IRCEvent createEvent(IRCEvent event)
	{
		return new NumericErrorEvent
		(
				event.arg(0), 
				event.getRawEventData(), 
				event.getSession()
		); 
	}
}
