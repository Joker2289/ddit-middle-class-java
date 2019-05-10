package main;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;

import java.util.*;
								
public class ChatServerImpl extends UnicastRemoteObject implements ChatServer {

   Vector clientList = new Vector();

   public ChatServerImpl() throws RemoteException{} 

   public static void main(String args[]){

       try{

          System.out.println("ChatServerImpl.main:creating registry");
          
          System.setProperty("java.rmi.server.hostname","192.168.0.9");

          ChatServerImpl Server = new ChatServerImpl();    
          
          Registry reg = LocateRegistry.createRegistry(8888);

          reg.rebind("RMIChatServer", Server);
         // Naming.rebind("RMIChatServer", Server);

          System.out.println("ChatServerImpl is running...");

       } catch (Exception e){

          System.out.println("ChatServerImpl error: " + e.getMessage());

          e.printStackTrace();

       }

   }

   public void addClient(ChatClient client, String name) throws RemoteException{

       clientList.addElement(client);

       say(name + "입장 했습니다");

       System.out.println("New Client! Number of client = " + (clientList.size()));

   }

   public void disconnect(ChatClient client, String name) throws RemoteException{

       int i = clientList.indexOf(client);

       if (i >= 0){

          say(name + "퇴장했습니다");

          clientList.removeElementAt(i);

          System.out.println("A Client exited! Number of client = " + clientList.size());

          

       }else {

          System.out.println("No such a client in Server! ");

       }

   }

   public void say(String msg) throws RemoteException {

       int numOfConnect = clientList.size();

       for(int i = 0; i < numOfConnect; i++){

          ((ChatClient)clientList.elementAt(i)).printMessage(msg);

       }

   }

}
