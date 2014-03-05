package net.sf.jerklib.parsers;

import net.sf.jerklib.events.IRCEvent;



public interface InternalEventParser
{
	public IRCEvent receiveEvent(IRCEvent e);
}
