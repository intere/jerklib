package net.sf.jerklib;

import net.sf.jerklib.Channel;
import net.sf.jerklib.events.IRCEvent;
import net.sf.jerklib.events.IRCEvent.Type;
import net.sf.jerklib.events.NickListEvent;
import net.sf.jerklib.tasks.TaskImpl;
import java.io.File;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



import static org.testng.AssertJUnit.assertTrue;


/*
 * the data file for this test only has data from freenodes - hyperion-1.0.2b
 * need to run this with data from other ircds 
 * 
 */

public class NickCacheTest extends EventTestBase
{
	private NickListEvent nle;
	
	public NickCacheTest()
	{
		super("/nickcachedata" , System.getProperty("user.home") + File.separator + "jerklib.tests.user.ouput");
	}
	
	@BeforeTest
	public void init()
	{
		createSession();
		addServerInfo(EventTestBase.ServerInfo.HYPERION);
		addChannel("#ubuntu");
		session.onEvent(new TaskImpl("nle")
		{
			public void receiveEvent(IRCEvent e)
			{
				nle = (NickListEvent)e;
			}
		} , Type.NICK_LIST_EVENT);
		conMan.start(session);
	}
	
	@Test
	public void TestNickCountFromNickListEvent()
	{
		assertTrue(nle.getNicks().size() + "" , nle.getNicks().size() == 1225);
	}
	
	@Test
	public void TestNickCountAfterPartJoinsEtc()
	{
		int size = session.getChannel("#ubuntu").getNicks().size();
		assertTrue(size == 1224);
	}
	
	@Test
	public void testContainsNick()
	{
		Channel chan = session.getChannel("#ubuntu");
		assertTrue(!chan.getNicks().contains("unstable"));
		assertTrue(chan.getNicks().contains("rosco"));
	}
	
	@Test
	public void testNickCaseInsensitivity()
	{
		Channel chan = session.getChannel("#ubuntu");
		assertTrue(chan.getNicks().contains("Rosco"));
		assertTrue(chan.getNicks().contains("RoScO"));
		assertTrue(chan.getNicks().contains("ROSCO"));
		
		assertTrue(chan.getNicks().indexOf("Rosco") != -1);
		assertTrue(chan.getNicks().indexOf("RoScO") != -1);
		assertTrue(chan.getNicks().indexOf("ROSCO") != -1);
	}
	
	
}
