package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientMain {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		
		//Gets the socet
		Socket clientsoc = new Socket("10.1.1.159",10170);
		DataInputStream inputSt = new DataInputStream(clientsoc.getInputStream());
		//ArrayList<String> list_of_ips = new ArrayList<String>();
		
		DataOutputStream outputST = new DataOutputStream(clientsoc.getOutputStream());
		
		//Sends the name of client plus the ipaddress
		outputST.writeUTF("/n Connect " + clientsoc.getLocalSocketAddress());
		//adds ipaddres to the list
		
		System.out.println("OutPut" + outputST.toString());
		System.out.println("Input" + inputSt.toString());
		
		//File output stream for writing file from server to new destination on client
		FileOutputStream fileout = new FileOutputStream("C:/Users/Public/Pictures/Jinjo.gif");
		//Gets messsage from server
		System.out.println(inputSt.readUTF());
		
		//The part where you send commands to server
		Scanner scan = new Scanner(System.in);
		//Size of image we get
		
		int size_of_image;
		
		String command = scan.nextLine().toLowerCase();
		
		outputST.writeUTF(command);
		
		if (command.equals( "getfilelist"))
		{
			
			//System.out.println(inputSt.readChar());
			//outputST.writeUTF("You sent a y");
			System.out.println(inputSt.readUTF());
			System.out.println("Get File list command entered" );
			outputST.writeUTF("Client command received");

			
		}
		
		else if (command.toLowerCase().contains("download"))
		{
			System.out.println("download command entered");
		
			System.out.println(inputSt.readChar());
			outputST.writeUTF("You sent a y");
			
			
			size_of_image = inputSt.readInt();
			System.out.println("Size of image  on client" + size_of_image);
			//int real_size = Integer.parseInt(size_of_image);
			int real_size = size_of_image;
			byte[] mysentimage = new byte[real_size];
			outputST.writeUTF("Got the size");
			System.out.println("Size of image after cast "+ real_size);
			
			
			for (int i = 0; i <= mysentimage.length - 1;i++)
			{
				mysentimage[i] = inputSt.readByte();
			}
			
			//outputST.writeUTF("Got the image! Thanks!");
			System.out.println("Something");
			fileout.write(mysentimage);
			fileout.flush();
		}
		
		System.out.println(command + " command");
		fileout.close();
		inputSt.close();
		outputST.close();
		
	}
	
	


}
