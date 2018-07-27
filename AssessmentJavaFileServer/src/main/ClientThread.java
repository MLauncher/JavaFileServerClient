package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread implements Runnable {

	Socket socket;
	String ipaddress;

	Thread thread;
	PrintWriter writer;
	DataInputStream input;
	DataOutputStream output;
	
	public ClientThread(Socket s) throws IOException
	{
		this.socket = s;
		
		
		//PrintWriter writer = new PrintWriter(socket.getOutputStream());
		System.out.println(socket.getLocalSocketAddress());
		//writer.println("Brian PrintWriter");
		input = new DataInputStream((socket.getInputStream()));
		output = new DataOutputStream(socket.getOutputStream());
		ipaddress = socket.getLocalSocketAddress().toString();
	
		
		
		output.writeUTF("Words");
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			
			String word = " ";
			System.out.println("RUN");
			System.out.println("Blank");
//			//Writing write = new Writing(output);
//			Thread tw = new Thread(write);
//			
//			tw.start();
			socket.getOutputStream().write(3);
			
			output.writeUTF("Sup");
			
//			while (input.readUTF() != "Exit")
//			{
//				word = input.readUTF();
//			}
		//output.writeUTF("Sup");
				
		} catch (Exception d)
		{
			d.printStackTrace();
		}
	
	
		System.out.println("Thread is running " + socket.getLocalSocketAddress());
		Thread current = Thread.currentThread();
		
		System.out.println("Client is running");
		//System.out.println("The client streams");
		
		//System.out.println(in.toString());
		//System.out.println(output.toString());
		
		
//		Reading reading = new Reading(in);
//		Thread t = new Thread(reading);
//		t.start();
//		while(t.isAlive())
//		{
//			System.out.println("I'm alive");
//			
//		}
//		System.out.println("Dead!");
		
		
		
		
	}
	
	public boolean testConnection() throws IOException
	{
		
		if (socket.isConnected())
		{
			//Im just trying to check for something to make sure the connection was successful\
			
			return true;
		}
		
		return false;
		
	}

}
