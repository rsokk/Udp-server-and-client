package udpserver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Data {

    byte[] buffer;
    PrintWriter log;
    MulticastSocket serverSocket;
    DatagramPacket recivePacket;
    String value;
    final static int TIMEOUT = 1000;
    final static int PORT = 9876;
    public List<String> list;

    public Data() {
    	
        list = new ArrayList<>();
        buffer = new byte[1024];
        
        try {
       	
    	        log = new PrintWriter("log.csv");
    			
    	        } catch (FileNotFoundException ex) {
    				 Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
    			}
    	   
    	        log.write("Quality" + ";" + "Timestamp" + ";" + "Value\n"
    	                + ";;;;" + "Standarddeviation" + ";" + "=STDAV.P(C:C)" + "\n"
    	                + ";;;;" + "Gjennomsnitt" + ";" + "=GJENNOMSNITT(C:C)" + "\n");
       
        try {
            serverSocket = new MulticastSocket(PORT);
        } catch (IOException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String GetData() {
        
        recivePacket = new DatagramPacket(buffer, buffer.length);

        while (true) {
            try {
                serverSocket.setSoTimeout(TIMEOUT);
                serverSocket.receive(recivePacket);

                value = new String(recivePacket.getData(), "UTF-8").trim().replace(" ", "").replace(",", ".");                
                String str = new String(recivePacket.getData(), "UTF-8");
                log.append("0xC0 " + ";" + new java.util.Date().toString() + ";" + str);
                log.flush();
                list.add(value);
                

                return value;

            } catch (SocketTimeoutException e) {
                log.append("0x00 " + ";" + new java.util.Date().toString() + ";" + value );
                log.flush();
                return value;

            } catch (SocketException ex) {
                Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
