import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class WorkerThread extends Thread {

	Socket cs;
	public WorkerThread(Socket cs){
		this.cs = cs;
	}
	@Override
	public void run() {
		System.out.println("Worker Thread Starting");
		BufferedReader r;
		PrintStream ps;
		try {
			r = new BufferedReader(new InputStreamReader(cs.getInputStream()));
			ps = new PrintStream(cs.getOutputStream());
			String line;
			while((line = r.readLine()) != null)
			{
				line = line.toUpperCase();
				ps.println("MTSERVER: " +line);
				Thread.sleep(10);
			}
			System.out.println("Client Disconneted");
			r.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Worker thread Exiting");
	}

	
}
