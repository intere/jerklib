package net.sf.jerklib.parsers;

import net.sf.jerklib.Channel;
import net.sf.jerklib.Session;
import net.sf.jerklib.events.IRCEvent;
import net.sf.jerklib.events.QuitEvent;
import java.util.List;



public class QuitParser implements CommandParser
{
	public QuitEvent createEvent(IRCEvent event)
	{
		Session session = event.getSession();
		String nick = event.getNick();
		List<Channel> chanList = event.getSession().removeNickFromAllChannels(nick);
		return new QuitEvent
		(
			event.getRawEventData(), 
			session, 
			event.arg(0), // message
			chanList
		);
	}
}
