package net.sf.jerklib.parsers;

import net.sf.jerklib.events.IRCEvent;
import net.sf.jerklib.events.InviteEvent;



public class InviteParser implements CommandParser
{
	/* :r0bby!n=wakawaka@guifications/user/r0bby INVITE scripy1 :#jerklib2 */
	/* :yaloki!~yaloki@localhost INVITE SuSEmeet #test */
	public IRCEvent createEvent(IRCEvent event)
	{
		return new InviteEvent
		(
			event.arg(0), 
			event.getRawEventData(), 
			event.getSession()
		); 
	}
}
