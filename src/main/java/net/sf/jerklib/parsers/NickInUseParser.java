package net.sf.jerklib.parsers;

import net.sf.jerklib.events.IRCEvent;
import net.sf.jerklib.events.NickInUseEvent;



public class NickInUseParser implements CommandParser
{
	public IRCEvent createEvent(IRCEvent event)
	{
		return new NickInUseEvent
		(
				event.arg(1),
				event.getRawEventData(), 
				event.getSession()
		); 
	}
}
