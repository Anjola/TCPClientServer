Design of the Benchmark Client 
The benchmark client was designed by creating an array of a test number of threads. 
In this case, the tests were run for values from 1 thread to 10 threads. Each of these 
threads sent 100 connections to their respective servers at every test interval. 
The time taken for all the threads to complete was recorded at every number of thread level. 
That is when the benchmark test was run with one thread, the time taking for it to complete in 
ms was recorded for both the simple server and the MT server. The time was measured using 
the System.getCurrentTimeMillis() function in java. The measurements were taken just before starting
all threads and immediately after joining the threads. TO make the servers do more work, the sleep 
method was called in each of the servers so as to make the performance distinction clearer.
Below are some snippets from the run of the benhmark client.  


Snippet from Simple Server benchmark test output 
/*
SimpleServer CLIENT 6HERE
SimpleServer CLIENT 6HERE
Worker thread6 Exiting
SimpleServer CLIENT 7HERE
SimpleServer CLIENT 7HERE
SimpleServer CLIENT 7HERE
SimpleServer CLIENT 7HERE
SimpleServer CLIENT 7HERE
SimpleServer CLIENT 7HERE

*/


Snippet from  MTServer benchmark test output
/*
MTSERVER: CLIENT 4HERE
MTSERVER: CLIENT 7HERE
MTSERVER: CLIENT 5HERE
MTSERVER: CLIENT 6HERE
MTSERVER: CLIENT 9HERE
Worker thread5 Exiting
Worker thread9 Exiting
Worker thread6 Exiting
MTSERVER: CLIENT 3HERE
MTSERVER: CLIENT 8HERE
Worker thread3 Exiting
Worker thread8 Exiting
MTSERVER: CLIENT 2HERE
Worker thread2 Exiting
MTSERVER: CLIENT 4HERE
MTSERVER: CLIENT 7HERE
Worker thread7 Exiting
MTSERVER: CLIENT 4HERE
MTSERVER: CLIENT 4HERE
Worker thread4 Exiting
*/


Outputs of the Test Results combined 

**RUNTIMES IN MS FOR VARYING NUMBER OF THREADS**
For 1 Threads MTServer thread runtime: Simple Server threads runtime = 1073:1067
For 2 Threads MTServer thread runtime: Simple Server threads runtime = 1078:2125
For 3 Threads MTServer thread runtime: Simple Server threads runtime = 1087:3247
For 4 Threads MTServer thread runtime: Simple Server threads runtime = 1078:4259
For 5 Threads MTServer thread runtime: Simple Server threads runtime = 1068:5337
For 6 Threads MTServer thread runtime: Simple Server threads runtime = 1106:6458
For 7 Threads MTServer thread runtime: Simple Server threads runtime = 1048:7335
For 8 Threads MTServer thread runtime: Simple Server threads runtime = 1046:8361
For 9 Threads MTServer thread runtime: Simple Server threads runtime = 1038:9422
For 10 Threads MTServer thread runtime: Simple Server threads runtime = 1077:10513
**AVERAGE TIME IN MS PER THREAD**
For 1 Threads MTServer AVG runtime: Simple Server AVG runtime = 1073.00:1067.00
For 2 Threads MTServer AVG runtime: Simple Server AVG runtime = 539.00:1062.50
For 3 Threads MTServer AVG runtime: Simple Server AVG runtime = 362.33:1082.33
For 4 Threads MTServer AVG runtime: Simple Server AVG runtime = 269.50:1064.75
For 5 Threads MTServer AVG runtime: Simple Server AVG runtime = 213.60:1067.40
For 6 Threads MTServer AVG runtime: Simple Server AVG runtime = 184.33:1076.33
For 7 Threads MTServer AVG runtime: Simple Server AVG runtime = 149.71:1047.86
For 8 Threads MTServer AVG runtime: Simple Server AVG runtime = 130.75:1045.12
For 9 Threads MTServer AVG runtime: Simple Server AVG runtime = 115.33:1046.89
For 10 Threads MTServer AVG runtime: Simple Server AVG runtime = 107.70:1051.30


As seen from the simple server output snippet, the threads are served in order 
client 6(thread 6) finishes and then 7 starts and so on. IT should be noted though
that when the threads were idle, no client switching occurred, The scenerio was different 
though for the MTServer as seen in it's output snippet. The threads seem to be served in random
fashion. This is because when one thread is idle, the JVM switches and serves another client in
that time interval. The combination of the results above is thus expected. 
As seen in the performance comparison outputs, the MTServer performs better for more than one
thread in all cases. For one thread, it makes sense that the simple server performs better as 
the overhead of switching is not compensated with the benefits of multitasking. 
For more than one thread though, from the values, it can be seen that the MTSever is approximately
n times faster than the simpleServer where n is the number of threads. This makes sense as the MTServer
can serve other clients while one client is idle meanwhile, the simple server can only serve the next 
thread when the previous thread is completely done. 
