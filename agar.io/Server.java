import java.net.*;
import java.io.*;

public class Server extends Thread {
    private static ServerSocket serverSocket;
    Socket server;
    WorldPackage wp;
   
    public Server(WorldPackage wp) {
        this.wp = wp;
        
        try {
            serverSocket = new ServerSocket(2052);
            
            //System.out.println(Inet4Address.getLocalHost().getHostAddress());
            //System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
            server = serverSocket.accept();
            //System.out.println("Connected to " + server.getRemoteSocketAddress());
            
            OutputStream outToClient = server.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outToClient);
            oos.writeObject(wp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CellPackage update(CellPackage s) {
        CellPackage r = null;
        try {
            ObjectInputStream in = new ObjectInputStream(server.getInputStream());
            r = (CellPackage)in.readObject(); 
            //System.out.println("recieved");
            //System.out.println(r);
            
            OutputStream outToClient = server.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outToClient);
            oos.writeObject(s);
            //System.out.println("sent");
        }catch(SocketTimeoutException e) {
            System.out.println("Connection timed out!");
        }catch(IOException e) {
            e.printStackTrace();
        }catch(ClassNotFoundException e) {}
        return r;
    }
    
    public void stopIt() {
        try {
            serverSocket.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}