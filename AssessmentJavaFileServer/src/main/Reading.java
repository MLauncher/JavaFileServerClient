package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Reading implements Runnable {

	
	DataInputStream input;
	Thread thread;
	Reading(DataInputStream in)
	{
//		output = out;
		input = in;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
	
		String word;
		try {
			
			thread.currentThread().join();
			System.out.println("Reading Thread is running" );

			System.out.println(input.readUTF());
			System.out.println("Reader is running");
			
//			while(!word.equals(null)){
//		        
//	            System.out.println(word);
//	            word = reader.readUTF();
//	            
		
			
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

}
}
