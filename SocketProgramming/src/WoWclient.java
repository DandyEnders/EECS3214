import java.io.*;
import java.net.*;
 
/**
 * This program demonstrates the WOWclient.
 *
 *
 * @author Jinho Hwang
 */
public class WoWclient {
 
    public static void main(String[] args) {
    	// Error, wrong syntax
        if (args.length != 2) {
            System.out.println("SyntaxError: WoWclient <hostname> <port>");
            return;
        }
        
    	// Now assumed we have 2 arguments.
        
    	// Hostname, port parsing
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
 
        try {
        	// Make ip address
            InetAddress address = InetAddress.getByName(hostname);
            // Make socket
            DatagramSocket socket = new DatagramSocket();
 
            while (true) {
            	 // Making UDP request by sending 1 byte of packet.
                DatagramPacket request = new DatagramPacket(new byte[1], 1, address, port);
                socket.send(request);
                
                // Making buffer for client to get WoW quote from server.
                byte[] buffer = new byte[1024];
                // Makes response to buffer
                DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                socket.receive(response);
                
                // Get string from buffer as much as response's length.
                String quote = new String(buffer, 0, response.getLength());
                
                // Print the quote.
                System.out.println(quote);
                
                // Wait 5 seconds (5000ms)
                Thread.sleep(5000);
            }
 
        } catch (SocketTimeoutException e) {
            System.out.println("Timeout Error: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Client Error: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
        	System.out.println("Intruppted: " + e.getMessage());
            e.printStackTrace();
        }
    }
}