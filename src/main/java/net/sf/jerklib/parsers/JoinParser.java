package net.sf.jerklib.parsers;

import net.sf.jerklib.Channel;
import net.sf.jerklib.Session;
import net.sf.jerklib.events.IRCEvent;
import net.sf.jerklib.events.JoinCompleteEvent;
import net.sf.jerklib.events.JoinEvent;


/**
 * @author mohadib
 *
 */
public class JoinParser implements CommandParser
{

	// :r0bby!n=wakawaka@guifications/user/r0bby JOIN :#jerklib
	// :mohadib_!~mohadib@68.35.11.181 JOIN &test

	public IRCEvent createEvent(IRCEvent event)
	{
		Session session = event.getSession();

		if (!event.getNick().equalsIgnoreCase(event.getSession().getNick())) 
		{ 
			//someone else joined a channel we are in
			return new JoinEvent(event.getRawEventData(), session, session.getChannel(event.arg(0))); 
		}
		
		//we joined a channel
		return new JoinCompleteEvent(event.getRawEventData(), event.getSession(), new Channel(event.arg(0), event.getSession()));
	}
}
