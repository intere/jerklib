package net.sf.jerklib.events;

import net.sf.jerklib.Session;

/**
 * The event fired when a connection to a server is lost (disconnected).
 * 
 * @author mohadib
 *
 */
public class ConnectionLostEvent extends IRCEvent
{
	private Exception e;
	
	public ConnectionLostEvent(String data , Session session , Exception e)
	{
		super(data,session,Type.CONNECTION_LOST);
		this.e = e;
	}
	
	public Exception getException()
	{
		return e;
	}
}
