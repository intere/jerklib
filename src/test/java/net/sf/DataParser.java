import net.sf.jerklib.events.EventToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;




public class DataParser
{
	
	public void filterData(File file , ParserRunnable runnable) throws IOException
	{
		List<String> tokens = new ArrayList<String>();
		InputStream is = this.getClass().getResourceAsStream("/undernet.data");        
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String s = "";
		StringBuilder builder = null;
		int i = 0;
		int added = 0;
		while((s = reader.readLine()) != null)
        
        {
			switch(i)
			{
				case 0:
				{
					builder = new StringBuilder();
					builder.append(s + "\r\n");
					break;
				}
				case 1:
				{
					builder.append(s + "\r\n");
					break;
				}
				case 2:
				{
					if(runnable.matches(new EventToken(s)))
					{
						builder.append("# Event index: " + added + "\r\n");
						builder.append(s + "\r\n");
						tokens.add(builder.toString());
						added++;
					}
					break;
				}
			}
			if(i ==3) i = 0;
			else i++;
		}
		writeData(file, tokens);
	}
	
	
	private void writeData(File file , List<String>data) throws IOException
	{
		FileWriter writer = new FileWriter(file);
		for(String token : data)
		{
			writer.write(token + "\r\n");
		}
		writer.flush();
		writer.close();
	}
	
	public static void main(String[] args)
	{
		DataParser parser = new DataParser();
		
		try
		{
			parser.filterData(new File(args[0] , "privmsg.data"), new CommandRunnable("PRIVMSG"));
			parser.filterData(new File(args[0] , "join.data"), new CommandRunnable("JOIN"));
			parser.filterData(new File(args[0] , "quit.data"), new CommandRunnable("QUIT"));
			parser.filterData(new File(args[0] , "mode.data"), new CommandRunnable("MODE"));
			parser.filterData(new File(args[0] , "part.data"), new CommandRunnable("PART"));
			parser.filterData(new File(args[0] , "topic.data"), new CommandRunnable("TOPIC"));
			parser.filterData(new File(args[0] , "invite.data"), new CommandRunnable("INVITE"));
			parser.filterData(new File(args[0] , "nick.data"), new CommandRunnable("NICK"));
			parser.filterData(new File(args[0] , "kick.data"), new CommandRunnable("KICK"));
			parser.filterData(new File(args[0] , "pong.data"), new CommandRunnable("PONG"));
			
			parser.filterData(new File(args[0] , "notice.data"), new RegexRunnable("((^NOTICE.+)|(^\\S+\\s+NOTICE.+))"));
			parser.filterData(new File(args[0] , "ping.data"), new RegexRunnable("^PING.+"));
			parser.filterData(new File(args[0] , "away.data"), new RegexRunnable("^\\S+\\s+(301|305|306).+"));
			
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	public static interface ParserRunnable
	{
		boolean matches(EventToken token);
	}
	
	public static class CommandRunnable implements ParserRunnable
	{
		protected String command;
		
		public CommandRunnable(String command)
		{
			this.command = command;
		}
		public boolean matches(EventToken token)
		{
			return token.command().equals(command);
		}
	}
	
	
	public static class RegexRunnable extends CommandRunnable
	{
		RegexRunnable(String command)
		{
			super(command);
		}
		
		public boolean matches(EventToken token)
		{
			return token.getRawEventData().matches(super.command);
		}
	}
	
}
