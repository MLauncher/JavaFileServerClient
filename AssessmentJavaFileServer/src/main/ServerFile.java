package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServerFile {

	static String longassfileString = "";
	
	public static void listFilesForFolder(final File folder) {
		  //Checks to see if is a directory
		 if(!folder.isDirectory())
		 {
		   		//add the folder to the string if is not a directory
			 longassfileString += folder.getName() + ",";
		 }
		  else{
		   		
		   		//gets the amount of files for indexing
		   		
			  int number_of_files = folder.listFiles().length;
		   	//for each item in file
		  for (final File fileEntry : folder.listFiles()) {
			  //if it is put the directory then all its file into the string follow by a comma
			  longassfileString += fileEntry.getName()+",";
			  System.out.println(fileEntry.getName());
		  }
		  }
	}
	
	public static byte[] FcopyImage(File file) throws IOException
	{
		
		
	
		FileInputStream imgstream = new FileInputStream(file);
		
		//Creates the byte array to hold the picture and gets the size of the picture
		// it returns bytes
		byte[] myimage = new byte[(int) file.length()];
		int len = myimage.length;
		System.out.println("Size of image on server " + len);
		int word2;
		int count = 0;
				
				//reads the whole picture of bytes into a new array taht
				//words2 is the read byte from the picture
		imgstream.read(myimage);		
//				
//				while ((word2 =imgstream.read())!=-1)
//				{
//						//count is the current index put the read byte into an array
//						myimage[count] = (byte)word2;
//						//an increment to the next point in the array
//						count++;
//						
//					
//				}
				
		System.out.println(myimage.length);
		//returns a byte array with the file in it 
		return myimage;
	}
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket server = new ServerSocket(10170);
		Socket socket = server.accept();
		
		DataInputStream input = new DataInputStream(socket.getInputStream());
		DataOutputStream output = new DataOutputStream(socket.getOutputStream());
		
		System.out.println(input.toString());
		System.out.println(output.toString());
		
		String developer = input.readUTF();
		System.out.println(output.toString());
		System.out.println("connect" + developer);
		
		output.writeUTF("Input a Command:\n"
				+ "getFileList or download, filename");
		//Starts with the file of a directory
		File file = new File("C:/Users/FileName");
		//Remember that method that got all the files 
		File imagefile = new File("C:/Users/FT9/Pictures/Jinjo2.gif");
		
		listFilesForFolder(file);
		byte[] myimagearray = FcopyImage(imagefile);
		
		
		String command = input.readUTF();
		String commandsplit[] = command.split(",");
		
//		split returns away of string split by the comma
//		
//		///one of the commands is download, filename
//		//the user types download, image.jpg
//		the server gets teh string and splits by the comma
//		string[0] = download and string[1] = image.jpg which we use to search the list
		switch(commandsplit[0])
		{
			case("getfilelist"):
			{
				System.out.println("getFileList command entered");
				//System.out.println(input.readUTF());
				//the protocol
				System.out.println(longassfileString);
				output.writeUTF(longassfileString);
				
				break;
			}
			
			case("download"):
			{
				
				System.out.println("The name you sent was " + commandsplit[1]);
				output.writeChar('y');
				
				System.out.println(input.readUTF());
				//sends the byte array size as a String of the file
				output.writeInt(myimagearray.length);
				System.out.println(input.readUTF());
				
				output.write(myimagearray);
				input.readUTF();
				input.close();
				output.close();
				break;
				
				
			}
			
			
		}
		
		System.out.println(command + "command");
		
		
		
		
		
		
		
		
		
		
		
	}

}
