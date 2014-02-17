import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DecimalFormat;


public class BenchmarkClient {

	public static void main(String[] args)
	{
		final int noOfRounds = 10;// Number of times to run test set
		final int[] noOfThreadsPerSet  = {1,2,3,4,5,6,7,8,9,10};//threads per server
		long[] runTimesSimpleServer = new long[noOfRounds]; //store runtimeResults 
		long[] runTimesMTServer = new long[noOfRounds];//store runtimeResults
		for(int i=0; i <noOfRounds; ++i)
		{

			Thread [] SimpleServerThreads = new Thread[noOfThreadsPerSet[i]];//toTestServer
			Thread [] MTServerThreads = new Thread[noOfThreadsPerSet[i]];//toTestMTServer
			long startTime = 0;
			long finishTime = 0;

			try {
				System.out.println("Client connected");
				for(int j = 0; j< noOfThreadsPerSet[i]; ++j)
				{
					Socket s = new Socket("localhost",3333);
					SimpleServerThreads[j] = new WorkerThread(s,j);
				}
				startTime = System.currentTimeMillis();
				for(Thread thread : SimpleServerThreads)
				{
					thread.start();
				}
				for (Thread thread : SimpleServerThreads) {
					thread.join();
				}
				finishTime = System.currentTimeMillis();
				runTimesSimpleServer[i]  = finishTime - startTime;
				for(int j = 0; j< noOfThreadsPerSet[i]; ++j)
				{
					Socket s = new Socket("localhost",4444);
					MTServerThreads[j] = new WorkerThread(s,j);
				}
				startTime =System.currentTimeMillis();
				for(Thread thread : MTServerThreads)
				{
					thread.start();
				}
				for (Thread thread : MTServerThreads) {
					thread.join();
				}
				finishTime = System.currentTimeMillis();
				runTimesMTServer[i]  = finishTime - startTime;


			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("**RUNTIMES IN MS FOR VARYING NUMBER OF THREADS**");
		for(int i=0 ; i<noOfRounds ; i++){
			System.out.println("For " + noOfThreadsPerSet[i] +" Threads MTServer thread runtime: Simple Server threads runtime = "+ 
					runTimesMTServer[i]+ ":" + runTimesSimpleServer[i]);
			}
		System.out.println("**AVERAGE TIME IN MS PER THREAD**");
		for(int i=0 ; i<noOfRounds ; i++){
			DecimalFormat f = new DecimalFormat("##.00");
			System.out.println("For " + noOfThreadsPerSet[i] +" Threads MTServer AVG runtime: Simple Server AVG runtime = "+ 
					f.format((runTimesMTServer[i]*1.0)/noOfThreadsPerSet[i])+ ":" + f.format((runTimesSimpleServer[i] *1.0)/noOfThreadsPerSet[i]));
			}
		}
	}
