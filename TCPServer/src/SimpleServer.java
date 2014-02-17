import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;


public class SimpleServer {
	
	public static void main(String[] args) throws IOException{
		ServerSocket ss = null;
		try{
		ss = new ServerSocket(3333);
			System.out.println("Simple ServerSocket created");
			while(true)
			{
				Socket cs = ss.accept(); //accepts connection
				System.out.println("Client created");
				BufferedReader r = new BufferedReader(new InputStreamReader(cs.getInputStream()));
				PrintStream ps = new PrintStream(cs.getOutputStream());
				String line;
				while((line = r.readLine()) != null)
				{
						ps.println("SimpleServer " + line.toUpperCase()); //send line back to client
						Thread.sleep(10);
				}
				System.out.println("Client Disconneted");
				cs.close();//close client socket
			}
		}catch(IOException e){
			//for handling errors
			System.out.print("Error occured, terminating");
			e.printStackTrace();
			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}


