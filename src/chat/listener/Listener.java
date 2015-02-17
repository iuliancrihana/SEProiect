package chat.listener;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;

/**
 * 
 * @author slv
 *
 */


class ListenToServer extends Thread{

JTextPane outputDestination = null;
Socket establishedSocket = null;

public void kickStartprep(Socket establishedSocket, JTextPane outputDestination){
    this.establishedSocket = establishedSocket;
    this.outputDestination = outputDestination;
}


@Override
public void run(){

    UpdateServerStatusWindow("Thread is running!", outputDestination);


    BufferedReader inFromServer = null;

  try{
  inFromServer = new BufferedReader(new InputStreamReader(establishedSocket.getInputStream()));

      while (inFromServer.readLine().isEmpty() == false){
        UpdateServerStatusWindow(inFromServer.readLine(), outputDestination);  
      }


  }
  catch (Exception e){
      UpdateServerStatusWindow(e.toString(), outputDestination);
  }
}


public void UpdateServerStatusWindow(String message, JTextPane destination){

    StyledDocument doc = destination.getStyledDocument();
    try
{
doc.insertString(doc.getLength(), '\n' + message, null);    
}
catch(Exception e){ 
    JOptionPane.showMessageDialog(null, "There was an error updating the server     message output window in the TCP Listner!");
    JOptionPane.showMessageDialog(null, e);

}

}
}

/** 
 * in main apelam ListenToServer serverListener = new ListenToServer();
 * serverListener.kickStartprep(establishedConnection, ServerMessageOutput);
	serverListener.start();
 */
