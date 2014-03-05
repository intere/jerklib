package net.sf.jerklib.parsers;

import net.sf.jerklib.Session;
import net.sf.jerklib.events.IRCEvent;
import net.sf.jerklib.events.ServerInformationEvent;


public class ServerInformationParser implements CommandParser
{
	public IRCEvent createEvent(IRCEvent event)
	{
		Session session = event.getSession();
		session.getServerInformation().parseServerInfo(event.getRawEventData());
		return new ServerInformationEvent(session, event.getRawEventData(), session.getServerInformation());
	}
}
