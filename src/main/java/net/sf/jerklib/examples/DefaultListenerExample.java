package net.sf.jerklib.examples;

import net.sf.jerklib.Channel;
import net.sf.jerklib.ConnectionManager;
import net.sf.jerklib.Profile;
import net.sf.jerklib.Session;
import net.sf.jerklib.events.ConnectionCompleteEvent;
import net.sf.jerklib.events.JoinCompleteEvent;
import net.sf.jerklib.events.MessageEvent;
import net.sf.jerklib.listeners.DefaultIRCEventListener;


public class DefaultListenerExample extends DefaultIRCEventListener implements Runnable
{
    public DefaultListenerExample()
    {
    }

    Session session;

    static final String CHANNEL_TO_JOIN = "#jerklib";

    public void run()
    {
        ConnectionManager manager = new ConnectionManager(new Profile("ble", "bleh bleh","ble", "ble_", "ble__"));

        session = manager.requestConnection("irc.freenode.net");

        session.addIRCEventListener(this);
    }

    @Override
    protected void handleJoinCompleteEvent(JoinCompleteEvent event)
    {
        event.getChannel().say("Hello from BaseListenerExample");
    }

    @Override
    protected void handleConnectComplete(ConnectionCompleteEvent event)
    {
        event.getSession().join(CHANNEL_TO_JOIN);
    }

    @Override
    protected void handleChannelMessage(MessageEvent event)
    {
        log.info(event.getChannel().getName() + ":" + event.getNick() + ":" + event.getMessage());
        if ("now die".equalsIgnoreCase(event.getMessage()))
        {
            event.getChannel().say("Okay, fine, I'll die");
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                // *nothing*
            }
            System.exit(0);
        }
    }

    public static void main(String[] args)
    {
        DefaultListenerExample ble = new DefaultListenerExample();
        Thread t = new Thread(ble);
        t.start();
        try
        {
            Thread.sleep(30000L); // give it the axe in 30!
        }
        catch (InterruptedException e)
        {
            // *nothing*
        }
        ble.sayGoodbye();
        try
        {
            Thread.sleep(5000); // let the message be written!
        }
        catch (InterruptedException e)
        {
            // *nothing*
        }
        System.exit(0);
    }

    private void sayGoodbye()
    {
        for (Channel chan : session.getChannels())
        {
            chan.say("I'm melting! (built-in sword of Damocles... or bucket of water, whatever)");
        }
    }
}
