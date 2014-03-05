package net.sf.jerklib.parsers;

import net.sf.jerklib.events.IRCEvent;



public interface CommandParser
{
	public IRCEvent createEvent(IRCEvent event);
}
