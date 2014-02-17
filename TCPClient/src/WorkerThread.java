import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class WorkerThread extends Thread {

	Socket s;
	int workerId;
	
	public WorkerThread(Socket s, int workerId){
		this.s = s;
		this.workerId = workerId;
	}
	@Override
	public void run() {
		System.out.println("Worker Thread" + workerId + " Starting");
			System.out.println("Client connected");
			PrintStream w;
			try {
				
				w = new PrintStream(s.getOutputStream());
				for(int i=0; i<100; i++)
				{
					w.println("Client " + workerId + "here");
					BufferedReader r = new BufferedReader(new InputStreamReader(s.getInputStream()));
					String line;
					if((line = r.readLine()) != null)
						System.out.println(line);
				}
				w.close();
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		System.out.println("Worker thread" + workerId +" Exiting");
	}

	
}
