package net.sf.jerklib.parsers;

import net.sf.jerklib.events.IRCEvent;
import net.sf.jerklib.events.MotdEvent;



public class MotdParser implements CommandParser
{
	public IRCEvent createEvent(IRCEvent event)
	{
		return new MotdEvent
		(
			event.getRawEventData(), 
			event.getSession(), 
			event.arg(1), 
			event.prefix()
		);
	}
}
