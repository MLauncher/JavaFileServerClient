package main;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class ServerThread1 implements Runnable

{
	boolean isRunning = true;
	ServerSocket server;
	Socket socket;
	static DataInputStream in;
	
	static DataOutputStream out;
	static HashMap<String,ClientThread> map = new HashMap<String,ClientThread>();
	Thread thread;
	Executor exe = Executors.newCachedThreadPool();
	
	
	
	public ServerThread1() throws IOException
	{
		this.server = new ServerSocket(10170);
		//String developer = input.readUTF();
		//System.out.println("connect" + developer);
		System.out.println("Server is running");
		
		
	}
	
	
	
	public boolean isRunning() {
		return isRunning;
	}
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) throws IOException {
		this.socket = socket;
	
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true)
			{
				//Server gets the connection from the socket
				socket = server.accept();
				
				//Creates the client thread
				System.out.println(socket.getPort() + "server port");
				ClientThread client = new ClientThread(socket);
				
				//Sets DataInputStream and Outstream which does not seem to work (because of the lack of thread safety?)
				
				in = new DataInputStream(socket.getInputStream());
				out  = new DataOutputStream(socket.getOutputStream());
				
				//Starts the client thread
				Thread t = new Thread(client);
				t.start();
				
				//Starts a reading thread that takes the input for the file
				Reading b = new Reading(in);
				Thread t2 = new Thread(b);
				t2.start();
				
				socket.getInputStream().read();
				map.put(client.ipaddress, client);
				//System.out.println(in.readUTF());
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
//		 while (isRunning) {
//		    	
//		    	
//	            try {
//					
//					
//					if (socket.isConnected())
//					{
//						
//					
//						ClientThread client = new ClientThread(socket);
//						thread = new Thread(client);
//						
//						thread.start();
//						in = new DataInputStream(socket.getInputStream());
//						out = new DataOutputStream(socket.getOutputStream());
//						
//						System.out.println("The server streams");
//						System.out.println(in.toString());
//						System.out.println(out.toString());
//						
//						ReadingAndWritingThread read = new ReadingAndWritingThread(out,in);
//						
//						Thread thread2 =new Thread(read);
//						thread2.start();
//					}
//					
//					
//	            
//	            
//	
//	           
//	        }
//	            catch(IOException i)
//	            {
//	            	i.printStackTrace();
//	            }
//		 }
	
	public void setIn(DataInputStream in) {
		this.in = in;
	}
	public void setOut(DataOutputStream out) {
		this.out = out;
	}


}
