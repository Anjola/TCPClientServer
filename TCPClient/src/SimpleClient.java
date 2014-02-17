import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Time;
import java.util.Timer;


public class SimpleClient {
	public static void main(String[] args)
	{

			
		try {
			Socket s = new Socket("localhost",3333);
			System.out.println("Client connected");
			PrintStream ps = new PrintStream(s.getOutputStream());
			ps.println("This is simple client");
			BufferedReader r = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String line;
			if((line = r.readLine()) != null)
				System.out.println(line);
			
			r.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
