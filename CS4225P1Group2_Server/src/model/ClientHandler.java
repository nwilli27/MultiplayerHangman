package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {

	private String username;
	
	protected Socket socket;
	private ObjectInputStream incomingMessages;
	private ObjectOutputStream outgoingMessages;
	
	public ClientHandler(String username, Socket clientSocket, ObjectOutputStream output) {
		this.username = username;
		this.socket = clientSocket;
		this.outgoingMessages = output;
	}

	public String getUsername() {
		return this.username;
	}
	
	public void notifyCharacterGuessed(String guessedCharacter) {
		// maybe?
	}
	
	public void sendMessage(String message) {
		 
		
	}
	
	

	public ObjectOutputStream getOutgoingMessages() {
		return outgoingMessages;
	}

	@Override
	public void run() {
		
		String received;
		System.out.println(this.username + " is here.");
		
		this.setupStreams();
		
        //while (true) {
        	
        	//try {
        		
        		// receive the string 
                // received = dis.readUTF(); 
                  
//                System.out.println(received); 
//                
//                
//                if(received.equals("logout")){ 
//                    this.isloggedin=false; 
//                    this.s.close(); 
//                    break; 
//                } 
//               
//  
//                // search for the recipient in the connected devices list. 
//                // ar is the vector storing client of active users 
//                for (ClientHandler mc : Server.ar)  
//                { 
//                    // if the recipient is found, write on its 
//                    // output stream 
//                    if (mc.name.equals(recipient) && mc.isloggedin==true)  
//                    { 
//                        mc.dos.writeUTF(this.name+" : "+MsgToSend); 
//                        break; 
//                    } 
//                } 
        		
        		
//        	} catch (IOException e) { 
//                
//              e.printStackTrace(); 
//        	} 

        	
//        }

	}
	
	private void setupStreams() {
		
		try {
			this.incomingMessages = new ObjectInputStream(this.socket.getInputStream());
			//this.outgoingMessages = new ObjectOutputStream(this.socket.getOutputStream());
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
