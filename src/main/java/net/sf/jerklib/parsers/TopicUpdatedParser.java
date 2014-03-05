package net.sf.jerklib.parsers;

import net.sf.jerklib.events.IRCEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class TopicUpdatedParser implements CommandParser
{
	public IRCEvent createEvent(IRCEvent event)
	{
		Pattern p = Pattern.compile("^.+?TOPIC\\s+(.+?)\\s+.*$");
		Matcher m = p.matcher(event.getRawEventData());
		m.matches();
		event.getSession().sayRaw("TOPIC " + m.group(1));
		return event;
	}
}
