package net.sf.jerklib.parsers;

import net.sf.jerklib.Session;
import net.sf.jerklib.events.IRCEvent;
import net.sf.jerklib.events.NickChangeEvent;


public class NickParser implements CommandParser
{
	public IRCEvent createEvent(IRCEvent event)
	{
		Session session = event.getSession();
		return new NickChangeEvent
		(
				event.getRawEventData(), 
				session, 
				event.getNick(), // old
				event.arg(0)// new nick
		); 
	}
}
